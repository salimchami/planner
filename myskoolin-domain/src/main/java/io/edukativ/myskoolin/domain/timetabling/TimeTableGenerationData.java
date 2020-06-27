package io.edukativ.myskoolin.domain.timetabling;

import io.edukativ.myskoolin.domain.schoolclasses.SchoolClass;
import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoom;
import io.edukativ.myskoolin.domain.subjects.Subject;
import io.edukativ.myskoolin.domain.commons.vo.EnumDays;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class TimeTableGenerationData {

    private SchoolClass schoolClass;
    private List<EnumDays> refDays;
    private List<Subject> subjects;
    private Map<Subject, Integer> durationsValidations;

    public TimeTableGenerationData(List<EnumDays> refDays, SchoolClass schoolClass, List<Subject> subjects) {
        this.refDays = refDays;
        this.schoolClass = schoolClass;
        subjects.sort(comparing(Subject::getCoursesFrequencyPerWeek).reversed());
        durationsValidations = new HashMap<>();
        this.subjects = subjects;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Map<Subject, Integer> getDurationsValidations() {
        if (this.durationsValidations == null) {
            this.durationsValidations = new HashMap<>();
        }
        return durationsValidations;
    }

    public List<EnumDays> getRefDays() {
        return refDays;
    }

    public void setRefDays(List<EnumDays> refDays) {
        this.refDays = refDays;
    }

    //########################################################################################################
    //########################################################################################################
    //########################################################################################################
    //########################################################################################################
    //########################################################################################################

    public TimeTableValidation validation(TimeTableOptions timeTableOptions, List<SchoolRoom> schoolRooms) {
        boolean timeSlotsNotEmpty = !schoolClass.getTimetable().isEmpty();
        boolean allTimeSlotsHaveTeachers = validateTeachers();
        boolean allTimeSlotsHaveSchoolRooms = validateSchoolRooms(schoolRooms);
        boolean durationsOk = validateDurations();
        boolean notOutside = validateTimeSlotsLimits(timeTableOptions);
        boolean standardTimeslotsNotOverlapping = validateStandardTimeslots();
        boolean halfTimeslotsNotOverlapping = validateHalfTimeSlots();
        return new TimeTableValidation(schoolClass.getName(), durationsOk, notOutside, standardTimeslotsNotOverlapping,
                halfTimeslotsNotOverlapping, allTimeSlotsHaveSchoolRooms, allTimeSlotsHaveTeachers, timeSlotsNotEmpty, this.subjects, schoolClass.getTimetable());
    }

    private boolean validateSchoolRooms(List<SchoolRoom> schoolRooms) {
        return schoolClass.getTimetable().stream().allMatch(timeSlot -> {
            Optional<SchoolRoom> timeSlotSchoolRoom = schoolRooms.stream().filter(schoolRoom -> schoolRoom.getId().equals(timeSlot.getSchoolRoomId())).findFirst();
            Optional<Subject> timeSlotSubject = subjects.stream().filter(subject -> subject.getId().equals(timeSlot.getSubject().getId())).findFirst();
            return timeSlotSchoolRoom.isPresent() && timeSlotSubject.isPresent() && timeSlotSubject.get().getSchoolRoomsTypes().contains(timeSlotSchoolRoom.get().getType());
        });
    }

    private boolean validateTeachers() {
        return schoolClass.getTimetable().stream().allMatch(timeSlot -> timeSlot.getTeacherId() != null);
    }

    private boolean validateHalfTimeSlots() {
        final List<TimeSlot> halfs = schoolClass.getTimetable().stream()
                .filter(TimeSlot::isHalf)
                .collect(Collectors.toList());
        for (TimeSlot timeSlot : halfs) {
            if (halfs.stream().filter(timeSlot1 -> timeSlot1.equals(timeSlot)).count() > 2) {
                return false;
            }
        }
        return true;
    }

    private boolean validateStandardTimeslots() {
        return schoolClass.getTimetable().stream()
                .filter(timeSlot -> !timeSlot.isHalf())
                .noneMatch(timeSlot -> timeSlot.isOverlapping(schoolClass.getTimetable()
                        .stream()
                        .filter(staticTimeSlot -> !staticTimeSlot.equals(timeSlot))
                        .collect(Collectors.toList()))
                );
    }

    private boolean validateTimeSlotsLimits(TimeTableOptions timeTableOptions) {
        for (EnumDays day : refDays) {
            final List<TimeSlot> timeSlotsByDay = timeSlotsByDay(day);
            timeSlotsByDay.sort(comparing(TimeSlot::getStartTime));
            boolean outside = !timeSlotsByDay.isEmpty() && timeSlotsOutsideRefCoursesLimits(timeTableOptions, timeSlotsByDay.get(0), timeSlotsByDay.get(timeSlotsByDay.size() - 1));
            if (outside) {
                return false;
            }
        }
        return true;
    }

    private boolean validateDurations() {
        return subjects.stream().allMatch(subject -> {
            final int currentDurationInMinutes = addedDurationForSubject(subject, schoolClass.getTimetable());
            return subject.getMinutesPerWeek().equals(currentDurationInMinutes);
        });
    }

    private boolean timeSlotsOutsideRefCoursesLimits(TimeTableOptions timeTableOptions, TimeSlot firstTimeSlot, TimeSlot lastTimeSlot) {
        return lastTimeSlot.getEndTime().toLocalTime().isAfter(timeTableOptions.getCoursesEndTime().toLocalTime())
                || firstTimeSlot.getStartTime().toLocalTime().isBefore(timeTableOptions.getCoursesStartTime().toLocalTime());
    }

    public static int addedDurationForSubject(Subject subject, List<SchoolClassTimeSlot> timeSlots) {
        return timeSlots.stream()
                .filter(timeSlot -> timeSlot.getSubject().getId().equals(subject.getId()))
                .reduce(0, (partialTotalminutes, timeSlot) ->
                        partialTotalminutes + timeSlotDurationInMinutesWithHalf(timeSlot, LocalDateTime.now()), Integer::sum);
    }

    private static int timeSlotDurationInMinutesWithHalf(TimeSlot timeSlot, LocalDateTime now) {
        LocalDateTime startTime = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(),
                timeSlot.getStartTime().getHour(), timeSlot.getStartTime().getMinutes(), timeSlot.getStartTime().getSeconds());
        LocalDateTime endTime = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(),
                timeSlot.getEndTime().getHour(), timeSlot.getEndTime().getMinutes(), timeSlot.getEndTime().getSeconds());
        long partialResult = (endTime.getLong(ChronoField.MILLI_OF_DAY) / 1000 / 60) - (startTime.getLong(ChronoField.MILLI_OF_DAY) / 1000 / 60);
        int result = (int) partialResult;
        return Boolean.TRUE.equals(timeSlot.isHalf()) ? result / 2 : result;
    }

    public List<TimeSlot> searchForHalfTimeSlots() {
        return schoolClass.getTimetable().stream().filter(TimeSlot::isHalf).distinct().collect(Collectors.toList());
    }

    public List<TimeSlot> timeSlotsByDay(EnumDays day) {
        return schoolClass.getTimetable().stream().filter(timeSlot -> timeSlot.getDay() == day).collect(Collectors.toList());
    }

    public void reset() {
        this.schoolClass.setTimetable(new ArrayList<>());
        this.durationsValidations = new HashMap<>();
    }
}
