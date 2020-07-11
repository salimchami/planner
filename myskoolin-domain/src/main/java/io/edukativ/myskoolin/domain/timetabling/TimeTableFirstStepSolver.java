package io.edukativ.myskoolin.domain.timetabling;

import io.edukativ.myskoolin.domain.commons.MyskoolinLoggerSPI;
import io.edukativ.myskoolin.domain.schoolclasses.SchoolClass;
import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoom;
import io.edukativ.myskoolin.domain.subjects.Subject;
import io.edukativ.myskoolin.domain.teachers.Teacher;
import io.edukativ.myskoolin.domain.teachers.TeachersBySubject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TimeTableFirstStepSolver {

    private final List<SchoolRoom> schoolRooms;
    private final List<Subject> subjects;
    private final List<Teacher> teachers;
    private final MyskoolinLoggerSPI logger;
    private final SchoolClass schoolClass;
    private final List<Lesson> lessons;
    private TimeSlot currentTimeSlot;
    private final TimeTableOptions timeTableOptions;

    public TimeTableFirstStepSolver(List<SchoolRoom> schoolRooms, List<Subject> subjects,
                                    List<Teacher> teachers,
                                    SchoolClass schoolClass, TimeTableOptions timeTableOptions,
                                    MyskoolinLoggerSPI logger) {
        this.timeTableOptions = timeTableOptions;
        currentTimeSlot = timeTableOptions.firstWeekTimeSlot();
        this.schoolRooms = schoolRooms;
        this.subjects = subjects;
        this.teachers = teachers;
        this.logger = logger;
        this.schoolClass = schoolClass;
        this.lessons = new ArrayList<>();
    }

    public List<Lesson> generateFirstStepTimeTable() {
        logger.info(String.format("generating first step time table for school class %s", schoolClass.getName()));
        subjects.sort(Comparator.comparing(
                Subject::getMinutesPerWeek
        ).reversed()
                .thenComparing(
                        Subject::getCoursesFrequencyPerWeek
                ).reversed())
        ;
        for (Subject subject : subjects) {
            lessons.addAll(generateLessonsBySubject(subject, timeTableOptions, teachers, schoolRooms));
        }
        return lessons;
    }

    private List<Lesson> generateLessonsBySubject(Subject subject, TimeTableOptions timeTableOptions, List<Teacher> teachers,
                                                  List<SchoolRoom> schoolRooms) {
        Integer totalDurationToGenerate = subject.getMinutesPerWeek();
        long subjectTotalDuration = 0;
        while (subjectTotalDuration < totalDurationToGenerate) {
            final Long id = nextLessonId();
            TimeSlot timeSlot = new TimeSlot(id, subject.timetableName(), currentTimeSlot.getDay(),
                    currentTimeSlot.getStartTime(), currentTimeSlot.getEndTime(), subject.getBgColor(), subject.getColor());
            final Lesson lesson = new Lesson(
                    id, schoolRoomBySubject(subject, schoolRooms), subject,
                    teacherBySubjectAndSchoolClass(subject, schoolClass, teachers), timeSlot, schoolClass);
            lessons.add(lesson);
            currentTimeSlot = currentTimeSlot.next(timeTableOptions.getCoursesTimeSlots());
            subjectTotalDuration = subjectTotalDuration(subject);
        }
        return lessons;
    }

    private long subjectTotalDuration(Subject subject) {
        return lessons
                .stream()
                .filter(lesson -> lesson.getSubject().equals(subject))
                .map(Lesson::getTimeSlot)
                .map(TimeSlot::durationInMinutes)
                .mapToLong(Long::longValue).sum();
    }

    private Teacher teacherBySubjectAndSchoolClass(Subject subject, SchoolClass schoolClass, List<Teacher> teachers) {
        return schoolClass.getTeachersBySubjects().stream()
                .filter(teachersBySubject -> teachersBySubject.getSubject().equals(subject))
                .map(TeachersBySubject::getTeachers)
                .flatMap(teachersForSubject -> teachers.stream().filter(teachersForSubject::contains))
                .findFirst()
                .orElseThrow();
    }

    private SchoolRoom schoolRoomBySubject(Subject subject, List<SchoolRoom> schoolRooms) {
        return schoolRooms.stream()
                .filter(schoolRoom -> subject.getSchoolRoomsTypes().contains(schoolRoom.getType()))
                .findFirst()
                .orElseThrow();
    }

    private Long nextLessonId() {
        return lessons.stream().mapToLong(Lesson::getId).max().orElse(1);
    }
}
