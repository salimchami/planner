package io.edukativ.myskoolin.domain.timetabling.solving;

import io.edukativ.myskoolin.domain.grades.Grade;
import io.edukativ.myskoolin.domain.providers.*;
import io.edukativ.myskoolin.domain.providers.subjects.SubjectSixiemeTestProvider;
import io.edukativ.myskoolin.domain.providers.subjects.SubjectTestProvider;
import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoom;
import io.edukativ.myskoolin.domain.subjects.Subject;
import io.edukativ.myskoolin.domain.teachers.Teacher;
import io.edukativ.myskoolin.domain.timetabling.Lesson;
import io.edukativ.myskoolin.domain.timetabling.SchoolClassTimeTable;
import io.edukativ.myskoolin.domain.timetabling.constraints.TimeTableConstraintConfiguration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.DayOfWeek;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

class TimeTableSolvingTest extends ScoreConstraintVerifierTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("conflictParams")
    void timeSlotsOverlapping(String description,
                              int expectedConflictPenalty,
                              int expectedSchoolRoomTypePenalty,
                              int expectedMaxDurationByDayPenalty,
                              List<Lesson> lessons) {

        List<Grade> grades = GradeTestProvider.allGrades();
        List<SchoolRoom> schoolRooms = SchoolRoomTestProvider.allSchoolRooms();
        List<Subject> subjects = SubjectTestProvider.allSubjects();
        List<Teacher> teachers = UserTestProvider.teacherUsers(GlobalTestProvider.CLIENT_ID, subjects, grades);
        SchoolClassTimeTable timetable = new SchoolClassTimeTable(config, GlobalTestProvider.CLIENT_ID, schoolClass1, Collections.emptyList(),
                schoolRooms, subjects, teachers, lessons);

        scoreVerifier.assertHardWeight(TimeTableConstraintConfiguration.CONSTRAINT_TIMESLOTS_OVERLAPS, expectedConflictPenalty, timetable);
        scoreVerifier.assertHardWeight(TimeTableConstraintConfiguration.CONSTRAINT_SCHOOL_ROOM_TYPE, expectedSchoolRoomTypePenalty, timetable);
        scoreVerifier.assertHardWeight(TimeTableConstraintConfiguration.CONSTRAINT_SUBJECT_DURATION_MAX_BY_DAY, expectedMaxDurationByDayPenalty, timetable);
    }

    private static Stream<Arguments> conflictParams() {
        prepareParams();
        final Subject francaisSubject = SubjectSixiemeTestProvider.francais();
        final Subject mathsSubject = SubjectSixiemeTestProvider.maths();
        final SchoolRoom schoolRoom100 = SchoolRoomTestProvider.normalSchoolRoom100();
        final SchoolRoom schoolRoom101 = SchoolRoomTestProvider.normalSchoolRoom101();
        final SchoolRoom sciencesSchoolRoomSC1 = SchoolRoomTestProvider.sciencesSchoolRoomSC1();
        return Stream.of(
                Arguments.of("<> timeSlot / <> subject / room type OK / <> room", 0, 0, 0,
                        List.of(
                                new Lesson(1L, schoolClass1, schoolRoom100, francaisSubject, francaisTeacher,
                                        TimeSlotTestProvider.timeSlot(1L, DayOfWeek.MONDAY, 8, 9)),
                                new Lesson(2L, schoolClass1, schoolRoom101, mathsSubject, mathsTeacher,
                                        TimeSlotTestProvider.timeSlot(2L, DayOfWeek.MONDAY, 9, 10))
                        )
                ),
                Arguments.of("<> timeSlot (overlapping) / <> subject / room type OK / <> room", -300, 0, 0,
                        List.of(
                                new Lesson(1L, schoolClass1, schoolRoom100, francaisSubject, francaisTeacher,
                                        TimeSlotTestProvider.timeSlot(1L, DayOfWeek.MONDAY, 8, 30, 9, 30)),
                                new Lesson(2L, schoolClass1, schoolRoom101, mathsSubject, mathsTeacher,
                                        TimeSlotTestProvider.timeSlot(2L, DayOfWeek.MONDAY, 9, 10))
                        )
                ),
                Arguments.of("== timeSlot (overlapping) / <> subject / room type OK / <> room", -600, 0, 0,
                        List.of(
                                new Lesson(1L, schoolClass1, schoolRoom100, francaisSubject, francaisTeacher,
                                        TimeSlotTestProvider.timeSlot(1L, DayOfWeek.MONDAY, 8, 9)),
                                new Lesson(2L, schoolClass1, schoolRoom101, mathsSubject, mathsTeacher,
                                        TimeSlotTestProvider.timeSlot(2L, DayOfWeek.MONDAY, 8, 9))
                        )
                ),
                Arguments.of("== timeSlot (overlapping) / == subject / room type OK / <> room", -600, 0, 0,
                        List.of(
                                new Lesson(1L, schoolClass1, schoolRoom100, francaisSubject, francaisTeacher,
                                        TimeSlotTestProvider.timeSlot(1L, DayOfWeek.MONDAY, 8, 9)),
                                new Lesson(2L, schoolClass1, schoolRoom101, francaisSubject, francaisTeacher,
                                        TimeSlotTestProvider.timeSlot(2L, DayOfWeek.MONDAY, 8, 9))
                        )
                ),
                Arguments.of("== timeSlot (overlapping) / == subject / multiple room type !OK / <> room", -600, -1200, 0,
                        List.of(
                                new Lesson(1L, schoolClass1, sciencesSchoolRoomSC1, francaisSubject, francaisTeacher,
                                        TimeSlotTestProvider.timeSlot(1L, DayOfWeek.MONDAY, 8, 9)),
                                new Lesson(2L, schoolClass1, SchoolRoomTestProvider.amphitheaterSchoolRoom(), francaisSubject, francaisTeacher,
                                        TimeSlotTestProvider.timeSlot(2L, DayOfWeek.MONDAY, 8, 9))
                        )
                ),
                Arguments.of("== timeSlot (overlapping) / == subject / 1 room type !OK / <> room", -600, -600, 0,
                        List.of(
                                new Lesson(1L, schoolClass1, sciencesSchoolRoomSC1, francaisSubject, francaisTeacher,
                                        TimeSlotTestProvider.timeSlot(1L, DayOfWeek.MONDAY, 8, 9)),
                                new Lesson(2L, schoolClass1, schoolRoom101, francaisSubject, francaisTeacher,
                                        TimeSlotTestProvider.timeSlot(2L, DayOfWeek.MONDAY, 8, 9))
                        )
                ),
                Arguments.of("== timeSlot (overlapping) / == subject / 1 room type !OK / <> room", 0, -1800, -1800,
                        List.of(
                                new Lesson(1L, schoolClass1, sciencesSchoolRoomSC1, francaisSubject, francaisTeacher,
                                        TimeSlotTestProvider.timeSlot(1L, DayOfWeek.MONDAY, 8, 9)),
                                new Lesson(2L, schoolClass1, sciencesSchoolRoomSC1, francaisSubject, francaisTeacher,
                                        TimeSlotTestProvider.timeSlot(2L, DayOfWeek.MONDAY, 9, 10)),
                                new Lesson(3L, schoolClass1, sciencesSchoolRoomSC1, francaisSubject, francaisTeacher,
                                        TimeSlotTestProvider.timeSlot(3L, DayOfWeek.MONDAY, 10, 11)),
                                new Lesson(4L, schoolClass1, schoolRoom101, mathsSubject, mathsTeacher,
                                        TimeSlotTestProvider.timeSlot(4L, DayOfWeek.MONDAY, 2, 3))
                        )
                )
        );
    }
}
