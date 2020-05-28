package io.edukativ.schooling.myskoolin.domain.timetabling;

import io.edukativ.schooling.myskoolin.domain.commons.exceptions.NotFoundException;
import io.edukativ.schooling.myskoolin.domain.entity.*;
import io.edukativ.schooling.myskoolin.domain.vo.*;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class TimeTablesGeneration {

    private SchoolResourcesNeeds schoolResourcesNeeds;
    private int loopAttemptsNb;
    private final Map<Grade, TimeTableOptions> timeTableOptionsByGrade;
    private Instant lastGenerationDate;
    private List<TimeTableGenerationData> timeTableGenerationData;
    private Map<EnumDays, List<TimeSlot>> refCoursesByDay;
    private List<User> allTeachers;
    private List<SchoolRoom> allSchoolRooms;

    public TimeTablesGeneration(Map<Grade, TimeTableOptions> timeTableOptionsMap, List<SchoolClass> schoolClasses,
                                List<Subject> allSubjects, List<User> allTeachers,
                                List<SchoolRoom> allSchoolRooms) {
        this.loopAttemptsNb = 10;
        this.allTeachers = allTeachers;
        this.allSchoolRooms = allSchoolRooms;
        this.timeTableOptionsByGrade = timeTableOptionsMap;
        timeTableGenerationData = new ArrayList<>();
        schoolClasses.forEach(schoolClass -> {
            final TimeTableOptions timeTableOptions = timeTableOptionsMap.get(schoolClass.getGrade());
            List<EnumDays> refDays = timeTableOptions.refDays();
            List<Subject> subjects = Subject.subjectByGrade(allSubjects, schoolClass.getGrade());
            timeTableGenerationData.add(new TimeTableGenerationData(refDays, schoolClass, subjects));
        });
    }

    public Instant getLastGenerationDate() {
        return lastGenerationDate;
    }

    public List<TimeTableGenerationData> getTimeTableGenerationData() {
        return timeTableGenerationData;
    }

    // ########################################################################################################
    // ########################################################################################################
    // ########################################################################################################
    // ########################################################################################################
    // ########################################################################################################

    public void generateTimeTables() {
        for (TimeTableGenerationData timeTable : timeTableGenerationData) {
            final Grade grade = timeTable.getSchoolClass().getGrade();
            this.refCoursesByDay = this.timeTableOptionsByGrade.get(grade).refCoursesByDay(timeTable.getRefDays());
            List<User> teachersByGrade = Teacher.teachersByGrade(allTeachers, timeTable.getSchoolClass().getGrade());
            generateTimeTable(timeTable, teachersByGrade);
        }
        this.lastGenerationDate = Instant.now();
    }

    private void generateTimeTable(TimeTableGenerationData timeTable, List<User> teachersByGrade) {
        int generationAttemps = 0;
        while (!timeTable.validation(timeTableOptionsByGrade.get(timeTable.getSchoolClass().getGrade()), allSchoolRooms).isValid() && generationAttemps < loopAttemptsNb) {
            resetTimeTable(timeTable, teachersByGrade, allSchoolRooms);
            timeTable.getSubjects().forEach(subject -> {
//                List<User> teachersForSubject = Teacher.teachersBySubjects(teachersByGrade, subject.getId());
                User teacherForSubject = teacherUserById(timeTable, teachersByGrade, subject);
                List<SchoolRoom> schoolRoomsByType = SchoolRoom.schoolRoomsByType(allSchoolRooms, subject);
                generateTimeTableForSubject(timeTable, subject, teacherForSubject, schoolRoomsByType);
            });
            generationAttemps++;
        }
    }

    private User teacherUserById(TimeTableGenerationData timeTableGenerationData, List<User> teachersByGrade, Subject subject) {
        return teachersByGrade.stream().filter(user -> user.getId().equals(timeTableGenerationData.getSchoolClass().getTeacherBySubject(subject.getId()))).findFirst().orElseThrow(() -> new NotFoundException("timetables generation : no teacher user found from id."));
    }

    private void generateTimeTableForSubject(TimeTableGenerationData timeTableGenerationData, Subject subject, User teacherForSubject, List<SchoolRoom> schoolRoomsByType) {
        int generationAttempsPerSubject = 0;
        int addedDurationForSubject = 0;
        while (addedDurationForSubject < subject.getMinutesPerWeek() && generationAttempsPerSubject < loopAttemptsNb) {
            int totalWeekRemainingMinutesToAdd = subject.getMinutesPerWeek() - addedDurationForSubject;
            boolean half = totalWeekRemainingMinutesToAdd < 60;
            boolean standardAndHalf = totalWeekRemainingMinutesToAdd == 90;
            generateTimeSlot(timeTableGenerationData, teacherForSubject, schoolRoomsByType, subject, totalWeekRemainingMinutesToAdd, half, standardAndHalf);
            addedDurationForSubject = TimeTableGenerationData.addedDurationForSubject(subject, timeTableGenerationData.getSchoolClass().getTimetable());
            generationAttempsPerSubject++;
        }
    }

    private void generateTimeSlot(TimeTableGenerationData timeTableGenerationData, User teacherForSubject, List<SchoolRoom> schoolRoomsByType, Subject subject, int totalWeekRemainingMinutesToAdd, boolean half, boolean standardAndHalf) {
        Integer attempts = 0;
        if (half) {
            boolean addedHalfTimeSlot = tryToAddHalfTimeSlot(timeTableGenerationData, subject, teacherForSubject, schoolRoomsByType);
            if (!addedHalfTimeSlot) {
                tryToAddStandardTimeSlot(timeTableGenerationData, teacherForSubject, schoolRoomsByType, subject, totalWeekRemainingMinutesToAdd, true, standardAndHalf, attempts);
            }
        } else {
            tryToAddStandardTimeSlot(timeTableGenerationData, teacherForSubject, schoolRoomsByType, subject, totalWeekRemainingMinutesToAdd, false, standardAndHalf, attempts);
        }
    }

    private boolean tryToAddHalfTimeSlot(TimeTableGenerationData timeTableGenerationData, Subject subject, User teacherForSubject, List<SchoolRoom> schoolRoomsByType) {
        final List<TimeSlot> existingHalfTimeSlots = timeTableGenerationData.searchForHalfTimeSlots();
        for (TimeSlot existingHalfTimeSlot : existingHalfTimeSlots) {
            Optional<SchoolRoom> schoolRoom = SchoolRoom.searchForSchoolRoom(schoolRoomsByType, existingHalfTimeSlot);
            if (schoolRoom.isPresent()) {
                instantiateAndAddTimeSlot(timeTableGenerationData, subject, teacherForSubject, schoolRoom.get(), true, existingHalfTimeSlot.getDay(),
                        existingHalfTimeSlot.getStartTime(), existingHalfTimeSlot.getEndTime(), existingHalfTimeSlot.getDate());
                return true;
            }
        }
        return false;
    }

    private void instantiateAndAddTimeSlot(TimeTableGenerationData timeTableGenerationData, Subject subject, User teacher, SchoolRoom schoolRoom,
                                           boolean half, EnumDays day, Time startTime, Time endTime, ZonedDateTime date) {
        final TimeSlot timeSlot = new TimeSlot(subject.timetableName(), "", "", false, day, startTime, endTime,
                date, subject.getBgColor(), subject.getColor(), false, half, schoolRoom.getId(), timeTableGenerationData.getSchoolClass().getId(), subject.getId(), teacher.getId());
        timeTableGenerationData.getSchoolClass().getTimetable().add(timeSlot);
        Integer totalAddedDurationForSubject = timeTableGenerationData.getDurationsValidations().get(subject);
        if (totalAddedDurationForSubject == null) {
            totalAddedDurationForSubject = 0;
        }
        teacher.getTeacher().getTimetable().add(timeSlot);
        schoolRoom.getTimetable().add(timeSlot);
        timeTableGenerationData.getDurationsValidations().put(subject, totalAddedDurationForSubject + (half ? 30 : timeSlot.durationInMinutes().intValue()));
    }

    private void tryToAddStandardTimeSlot(TimeTableGenerationData timeTableGenerationData, User teacherForSubject, List<SchoolRoom> schoolRoomsByType,
                                          Subject subject, int totalWeekRemainingMinutesToAdd, boolean half, boolean standardAndHalf, Integer attempts) {
        final Optional<EnumDays> optDay = searchForDay(timeTableGenerationData, subject, teacherForSubject, schoolRoomsByType);
        if (optDay.isPresent()) {
            final EnumDays day = optDay.get();
            List<Time> startTimes = searchForStartTimesInDay(day, timeTableGenerationData);
            boolean added = addStandardTimeSlotWithFinalTimeSlot(timeTableGenerationData, teacherForSubject, schoolRoomsByType, subject, day, startTimes, totalWeekRemainingMinutesToAdd, half, standardAndHalf);
            if (!added && attempts <= loopAttemptsNb) {
                attempts++;
                tryToAddStandardTimeSlot(timeTableGenerationData, teacherForSubject, schoolRoomsByType, subject, totalWeekRemainingMinutesToAdd, half, standardAndHalf, attempts);
            }
        }
    }

    private boolean addStandardTimeSlotWithFinalTimeSlot(TimeTableGenerationData timeTableGenerationData, User teacherForSubject, List<SchoolRoom> schoolRooms,
                                                         Subject subject, EnumDays day, List<Time> startTimes, int totalWeekRemainingMinutesToAdd, boolean half, boolean standardAndHalf) {
        int finalMinutesToAdd = standardAndHalf ? totalWeekRemainingMinutesToAdd : searchForFinalMinutesToAdd(subject, totalWeekRemainingMinutesToAdd);
        Optional<TimeSlot> optFinalTimeSlot = searchForFinalStartAndEndTime(timeTableGenerationData, day, startTimes, finalMinutesToAdd, subject, teacherForSubject, schoolRooms);
        if (optFinalTimeSlot.isPresent()) {
            final TimeSlot finalTimeSlot = optFinalTimeSlot.get();
            if (standardAndHalf) {
                final Time oneHourEndTime = new Time(
                        finalTimeSlot.getStartTime().getHour() + 1,
                        finalTimeSlot.getStartTime().getMinutes(),
                        finalTimeSlot.getStartTime().getMinutes(),
                        finalTimeSlot.getStartTime().getPartOfDay()
                );
                final Time halfHourEndTime = new Time(
                        oneHourEndTime.getHour() + 1,
                        oneHourEndTime.getMinutes(),
                        oneHourEndTime.getMinutes(),
                        oneHourEndTime.getPartOfDay()
                );
                TimeSlot oneHourTimeSlot = new TimeSlot(finalTimeSlot, finalTimeSlot.getStartTime(), oneHourEndTime, false);
                TimeSlot halfHourTimeSlot = new TimeSlot(finalTimeSlot, oneHourEndTime, halfHourEndTime, true);
                searchForSchoolRoomsAndInstantiate(timeTableGenerationData, teacherForSubject, schoolRooms, subject, day, false, oneHourTimeSlot);
                searchForSchoolRoomsAndInstantiate(timeTableGenerationData, teacherForSubject, schoolRooms, subject, day, true, halfHourTimeSlot);
            } else {
                searchForSchoolRoomsAndInstantiate(timeTableGenerationData, teacherForSubject, schoolRooms, subject, day, half, finalTimeSlot);
            }
            return true;
        }
        return false;
    }

    private void searchForSchoolRoomsAndInstantiate(TimeTableGenerationData timeTableGenerationData, User teacherUser, List<SchoolRoom> schoolRooms, Subject subject, EnumDays day, boolean half, TimeSlot finalTimeSlot) {
        finalTimeSlot.setHalf(half);
        SchoolRoom schoolRoom = schoolRooms
                .stream()
                .filter(possibleSchoolRoom -> finalTimeSlot.getSchoolRoomId().equals(possibleSchoolRoom.getId()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("school room nout found"));
        instantiateAndAddTimeSlot(timeTableGenerationData, subject, teacherUser, schoolRoom, half, day, finalTimeSlot.getStartTime(), finalTimeSlot.getEndTime(), finalTimeSlot.getDate());
    }

    public Optional<TimeSlot> searchForFinalStartAndEndTime(TimeTableGenerationData timeTableGenerationData, EnumDays day, List<Time> startTimes, int finalMinutesToAdd, Subject subject, User teacherForSubject, List<SchoolRoom> schoolRooms) {
        List<TimeSlot> staticTimeSlotsForDay = timeTableGenerationData.timeSlotsByDay(day);
        for (Time startTime : startTimes) {
            final TimeSlot possibleTimeSlot = new TimeSlot(day, startTime, startTime.endTimeFromStartTimeBySubject(subject.getMaxMinutesPerDay(), finalMinutesToAdd));
            final boolean overlappingTimeBreaks = possibleTimeSlot.isOverlappingRefTimeBreaks(day, refCoursesByDay.get(day),
                    timeTableOptionsByGrade.get(timeTableGenerationData.getSchoolClass().getGrade()).getCoursesStartTime(), timeTableOptionsByGrade.get(timeTableGenerationData.getSchoolClass().getGrade()).getCoursesEndTime());
            final boolean overlappingStaticTimeSlots = possibleTimeSlot.isOverlapping(day, staticTimeSlotsForDay);
            if (!overlappingStaticTimeSlots && !overlappingTimeBreaks) {
                Optional<SchoolRoom> schoolRoom = SchoolRoom.searchForSchoolRoom(schoolRooms, possibleTimeSlot);
                if (schoolRoom.isPresent()) {
                    possibleTimeSlot.setSchoolRoomId(schoolRoom.get().getId());
                    possibleTimeSlot.setTeacherId(teacherForSubject.getId());
                    return Optional.of(possibleTimeSlot);
                }
            }
        }
        return Optional.empty();
    }

    private int searchForFinalMinutesToAdd(Subject subject, int totalWeekRemainingMinutesToAdd) {
        if (totalWeekRemainingMinutesToAdd > subject.getMaxMinutesPerDay()) {
            return subject.getMaxMinutesPerDay();
        } else {
            return subject.getMinMinutesPerDay();
        }
    }

    private List<Time> searchForStartTimesInDay(EnumDays day, TimeTableGenerationData timeTableGenerationData) {
        List<TimeSlot> freeTimeSlots = freeTimeSlotsByDay(timeTableGenerationData, day);
        return freeTimeSlots.stream()
                .map(TimeSlot::getStartTime)
                .collect(Collectors.toList());
    }

    private List<TimeSlot> freeTimeSlotsByDay(TimeTableGenerationData timeTableGenerationData, EnumDays day) {
        final List<TimeSlot> staticSchoolClassTimeSlotsForDate = timeTableGenerationData.timeSlotsByDay(day);
        return refCoursesByDay.get(day).stream()
                .filter(clientRefTimeSlot -> {
                    final boolean overlappingSchoolClassTimeSlots = clientRefTimeSlot.isOverlapping(day, staticSchoolClassTimeSlotsForDate);
                    return !overlappingSchoolClassTimeSlots;
                })
                .collect(Collectors.toList());
    }

    private Optional<EnumDays> searchForDay(TimeTableGenerationData timeTableGenerationData, Subject subject, User teacherForSubject, List<SchoolRoom> schoolRooms) {
        final List<EnumDays> freeDaysForSubject = searchForFreeDaysForSubject(subject, timeTableGenerationData, teacherForSubject, schoolRooms);
        if (!freeDaysForSubject.isEmpty()) {
            return freeDayForSubjectAttempts(subject.getMaxMinutesPerDay().longValue(), freeDaysForSubject, refCoursesByDay, timeTableGenerationData);
        }
        return Optional.empty();
    }

    private Optional<EnumDays> freeDayForSubjectAttempts(Long durationInMinutesToInsert, List<EnumDays> freeDaysForSubject, Map<EnumDays, List<TimeSlot>> refCoursesMap, TimeTableGenerationData timeTableGenerationData) {
        if (freeDaysForSubject.isEmpty()) {
            return Optional.empty();
        }
        for (EnumDays selectedDay : freeDaysForSubject) {
            if (isThereFreeTimeslotForSubject(durationInMinutesToInsert, selectedDay, refCoursesMap, timeTableGenerationData)) {
                return Optional.of(selectedDay);
            }
        }
        return Optional.empty();
    }

    protected boolean isThereFreeTimeslotForSubject(Long durationInMinutesToInsert, EnumDays selectedDay, Map<EnumDays, List<TimeSlot>> refCoursesMap, TimeTableGenerationData timeTableGenerationData) {
        final List<TimeSlot> staticTimeSlotsForDay = timeTableGenerationData.timeSlotsByDay(selectedDay);
        final List<TimeSlot> refCourses = refCoursesMap.get(selectedDay);
        long refTotalMinutesInSelectedDay = refCourses.stream()
                .map(TimeSlot::durationInMinutes)
                .mapToLong(Long::longValue).sum();
        long currentTotalMinutesByDay = staticTimeSlotsForDay.stream()
                .map(TimeSlot::durationInMinutes)
                .mapToLong(Long::longValue).sum();
        return (refTotalMinutesInSelectedDay - currentTotalMinutesByDay) >= durationInMinutesToInsert;
    }

    private List<EnumDays> searchForFreeDaysForSubject(Subject subjectToAdd, TimeTableGenerationData timeTableGenerationData, User teacherForSubject, List<SchoolRoom> schoolRooms) {
        if (checkRemainingMinutesToAdd(subjectToAdd, timeTableGenerationData)) {
            List<EnumDays> existingDaysInStaticTimeTable = timeTableGenerationData.getSchoolClass().getTimetable().stream()
                    .filter(timeSlot -> subjectToAdd.getId().equals(timeSlot.getSubjectId()))
                    .map(TimeSlot::getDay)
                    .collect(Collectors.toList());

            List<EnumDays> possibleTeachersDays = possibleTeachersDays(timeTableGenerationData, subjectToAdd, teacherForSubject);
            List<EnumDays> possibleSchoolRoomsDays = possibleSchoolRoomsDays(timeTableGenerationData, subjectToAdd, schoolRooms);

            return Arrays.stream(EnumDays.values())
                    .filter(refCoursesByDay::containsKey)
                    .filter(day -> !existingDaysInStaticTimeTable.contains(day))
                    .filter(possibleTeachersDays::contains)
                    .filter(possibleSchoolRoomsDays::contains)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public List<EnumDays> possibleSchoolRoomsDays(TimeTableGenerationData timeTableGenerationData, Subject subjectToAdd, List<SchoolRoom> schoolRooms) {
        List<EnumDays> days = new ArrayList<>();
        for (SchoolRoom possibleSchoolRoom : schoolRooms) {
            if (possibleSchoolRoom.getTimetable().isEmpty()) {
                days.addAll(new ArrayList<>(refCoursesByDay.keySet()));
            } else {
                final Map<EnumDays, List<TimeSlot>> schoolRoomsTimetableByDay = organizeByDay(possibleSchoolRoom.getTimetable());
                if (schoolRoomsTimetableByDay.size() != refCoursesByDay.keySet().size()) {
                    addInverseDays(refCoursesByDay.keySet(), schoolRoomsTimetableByDay.keySet(), days);
                }
                for (Map.Entry<EnumDays, List<TimeSlot>> timeslotsByDayEntry : schoolRoomsTimetableByDay.entrySet()) {
                    EnumDays day = timeslotsByDayEntry.getKey();
                    if (schoolRoomsTimetableByDay.get(day).stream()
                            .filter(timeSlot -> timeSlot.getSchoolClassId().equals(timeTableGenerationData.getSchoolClass().getId()))
                            .noneMatch(timeSlot -> timeSlot.getSubjectId().equals(subjectToAdd.getId()))
                            && schoolRoomsTimetableByDay.get(day).stream().allMatch(timeslot -> timeslot.isOverlapping(day, new ArrayList<TimeSlot>(refCoursesByDay.get(day))))) {
                        days.add(day);
                    }
                }
            }
        }
        return days.stream().distinct().collect(Collectors.toList());
    }

    private void addInverseDays(Set<EnumDays> refDays, Set<EnumDays> currentDays, List<EnumDays> days) {
        refDays.forEach(day -> {
            if (!currentDays.contains(day)) {
                days.add(day);
            }
        });
    }

    private static Map<EnumDays, List<TimeSlot>> organizeByDay(List<TimeSlot> timeslots) {
        Map<EnumDays, List<TimeSlot>> timeSlotsPerDay = new EnumMap<>(EnumDays.class);
        Arrays.stream(EnumDays.values()).forEach(day -> {
            final List<TimeSlot> timeSlots = timeslots
                    .stream()
                    .filter(timeSlot -> day.equals(timeSlot.getDay()))
                    .collect(Collectors.toList());
            if (!timeSlots.isEmpty()) {
                timeSlotsPerDay.put(day, timeSlots);
            }
        });
        return timeSlotsPerDay;
    }

    public List<EnumDays> possibleTeachersDays(TimeTableGenerationData timeTableGenerationData, Subject subjectToAdd, User teacherForSubject) {
        List<EnumDays> days = new ArrayList<>();
        if (teacherForSubject.getTeacher().getTimetable().isEmpty()) {
            days.addAll(new ArrayList<>(refCoursesByDay.keySet()));
        } else {
            final Map<EnumDays, List<TimeSlot>> teachersTimetableByDay = organizeByDay(teacherForSubject.getTeacher().getTimetable());
            if (teachersTimetableByDay.size() != refCoursesByDay.keySet().size()) {
                addInverseDays(refCoursesByDay.keySet(), teachersTimetableByDay.keySet(), days);
            }
            for (Map.Entry<EnumDays, List<TimeSlot>> timeslotsByDayEntry : teachersTimetableByDay.entrySet()) {
                EnumDays day = timeslotsByDayEntry.getKey();
                if (teachersTimetableByDay.get(day).stream()
                        .filter(timeSlot -> timeSlot.getSchoolClassId().equals(timeTableGenerationData.getSchoolClass().getId()))
                        .noneMatch(timeSlot -> timeSlot.getSubjectId().equals(subjectToAdd.getId()))
                        && teachersTimetableByDay.get(day).stream().allMatch(timeslot -> timeslot.isOverlapping(day, new ArrayList<TimeSlot>(refCoursesByDay.get(day))))) {
                    days.add(day);
                }
            }
        }
        return days.stream().distinct().collect(Collectors.toList());
    }

    private boolean checkRemainingMinutesToAdd(Subject subject, TimeTableGenerationData timeTableGenerationData) {
        // search if number of hours is ok
        final long totalMinutesForSubjectInCurrentTimetable = timeTableGenerationData.getSchoolClass().getTimetable()
                .stream()
                .filter(timeSlot -> timeSlot.getSubjectId().equals(subject.getId()))
                .map(timeSlot -> timeSlot.getEndTime().durationInMinutesFrom(timeSlot.getStartTime()))
                .mapToLong(Long::longValue).sum();
        return totalMinutesForSubjectInCurrentTimetable < subject.getMinutesPerWeek();
    }

    public List<TimeTableValidation> timeTablesAreValid(TimeTableOptions timeTableOptions, List<SchoolRoom> schoolRooms) {
        return timeTableGenerationData
                .stream()
                .map(timeTable -> timeTable.validation(timeTableOptions, schoolRooms))
                .collect(Collectors.toList());
    }

    private void resetTimeTable(TimeTableGenerationData timeTableGenerationData, List<User> teachersByGrade, List<SchoolRoom> allSchoolRooms) {
        final List<String> schoolRoomsIds = timeTableGenerationData.getSchoolClass().getTimetable().stream().map(TimeSlot::getSchoolRoomId).collect(Collectors.toList());
        allSchoolRooms.stream().filter(schoolRoom -> schoolRoomsIds.contains(schoolRoom.getId())).forEach(schoolRoom -> schoolRoom.getTimetable().clear());
        timeTableGenerationData.getSchoolClass().getTimetable()
                .forEach(timeSlot -> teachersByGrade
                        .forEach(user -> {
                                    for (int i = 0; i < user.getTeacher().getTimetable().size(); i++) {
                                        if (user.getTeacher().getTimetable().get(i).equals(timeSlot)) {
                                            user.getTeacher().getTimetable().remove(user.getTeacher().getTimetable().get(i));
                                        }

                                    }
                                }

                        ));
        timeTableGenerationData.reset();
    }
}
