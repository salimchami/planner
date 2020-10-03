package io.edukativ.myskoolin.domain.timetabling.solving;

import io.edukativ.myskoolin.domain.commons.vo.EnumDays;
import io.edukativ.myskoolin.domain.commons.vo.EnumPartsOfDay;
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

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

class TimeTableSolvingTest extends ScoreConstraintVerifierTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("conflictParams")
    void timeSlotsOverlapping(String description, int expectedConflictPenalty,
                              int expectedSchoolRoomTypePenalty, List<Lesson> lessons) {

        List<Grade> grades = GradeTestProvider.allGrades();
        List<SchoolRoom> schoolRooms = SchoolRoomTestProvider.allSchoolRooms();
        List<Subject> subjects = SubjectTestProvider.allSubjects();
        List<Teacher> teachers = UserTestProvider.teacherUsers(GlobalTestProvider.CLIENT_ID, subjects, grades);
        SchoolClassTimeTable timetable = new SchoolClassTimeTable(config, GlobalTestProvider.CLIENT_ID, schoolClass1, Collections.emptyList(),
                schoolRooms, subjects, teachers, lessons);

        scoreVerifier.assertHardWeight(TimeTableConstraintConfiguration.CONSTRAINT_TIMESLOTS_OVERLAPS, expectedConflictPenalty, timetable);
        scoreVerifier.assertHardWeight(TimeTableConstraintConfiguration.CONSTRAINT_SCHOOL_ROOM_TYPE, expectedSchoolRoomTypePenalty, timetable);
    }

    private static Stream<Arguments> conflictParams() {
        prepareParams();
        return Stream.of(
                Arguments.of("<> timeSlot / <> subject / room type OK / <> room", 0, 0,
                        List.of(
                                new Lesson(1L, SchoolRoomTestProvider.normalSchoolRoom100(), SubjectSixiemeTestProvider.francais(), francaisTeacher,
                                        TimeSlotTestProvider.timeSlot(1L, EnumDays.MONDAY, 8, EnumPartsOfDay.AM, 9, EnumPartsOfDay.AM)),
                                new Lesson(2L, SchoolRoomTestProvider.normalSchoolRoom101(), SubjectSixiemeTestProvider.maths(), mathsTeacher,
                                        TimeSlotTestProvider.timeSlot(2L, EnumDays.MONDAY, 9, EnumPartsOfDay.AM, 10, EnumPartsOfDay.AM))
                        )
                ),
                Arguments.of("<> timeSlot (overlapping) / <> subject / room type OK / <> room", -300, 0,
                        List.of(
                                new Lesson(1L, SchoolRoomTestProvider.normalSchoolRoom100(), SubjectSixiemeTestProvider.francais(), francaisTeacher,
                                        TimeSlotTestProvider.timeSlot(1L, EnumDays.MONDAY, 8, 30, EnumPartsOfDay.AM, 9, 10, EnumPartsOfDay.AM)),
                                new Lesson(2L, SchoolRoomTestProvider.normalSchoolRoom101(), SubjectSixiemeTestProvider.maths(), mathsTeacher,
                                        TimeSlotTestProvider.timeSlot(2L, EnumDays.MONDAY, 9, EnumPartsOfDay.AM, 10, EnumPartsOfDay.AM))
                        )
                ),
                Arguments.of("== timeSlot (overlapping) / <> subject / room type OK / <> room", -600, 0,
                        List.of(
                                new Lesson(1L, SchoolRoomTestProvider.normalSchoolRoom100(), SubjectSixiemeTestProvider.francais(), francaisTeacher,
                                        TimeSlotTestProvider.timeSlot(1L, EnumDays.MONDAY, 8, EnumPartsOfDay.AM, 9, EnumPartsOfDay.AM)),
                                new Lesson(2L, SchoolRoomTestProvider.normalSchoolRoom101(), SubjectSixiemeTestProvider.maths(), mathsTeacher,
                                        TimeSlotTestProvider.timeSlot(2L, EnumDays.MONDAY, 8, EnumPartsOfDay.AM, 9, EnumPartsOfDay.AM))
                        )
                ),
                Arguments.of("== timeSlot (overlapping) / == subject / room type OK / <> room", -600, 0,
                        List.of(
                                new Lesson(1L, SchoolRoomTestProvider.normalSchoolRoom100(), SubjectSixiemeTestProvider.francais(), francaisTeacher,
                                        TimeSlotTestProvider.timeSlot(1L, EnumDays.MONDAY, 8, EnumPartsOfDay.AM, 9, EnumPartsOfDay.AM)),
                                new Lesson(2L, SchoolRoomTestProvider.normalSchoolRoom101(), SubjectSixiemeTestProvider.francais(), francaisTeacher,
                                        TimeSlotTestProvider.timeSlot(2L, EnumDays.MONDAY, 8, EnumPartsOfDay.AM, 9, EnumPartsOfDay.AM))
                        )
                ),
                Arguments.of("== timeSlot (overlapping) / == subject / multiple room type !OK / <> room", -600, -1200,
                        List.of(
                                new Lesson(1L, SchoolRoomTestProvider.sciencesSchoolRoomSC1(), SubjectSixiemeTestProvider.francais(), francaisTeacher,
                                        TimeSlotTestProvider.timeSlot(1L, EnumDays.MONDAY, 8, EnumPartsOfDay.AM, 9, EnumPartsOfDay.AM)),
                                new Lesson(2L, SchoolRoomTestProvider.amphitheaterSchoolRoom(), SubjectSixiemeTestProvider.francais(), francaisTeacher,
                                        TimeSlotTestProvider.timeSlot(2L, EnumDays.MONDAY, 8, EnumPartsOfDay.AM, 9, EnumPartsOfDay.AM))
                        )
                ),
                Arguments.of("== timeSlot (overlapping) / == subject / 1 room type !OK / <> room", -600, -600,
                        List.of(
                                new Lesson(1L, SchoolRoomTestProvider.sciencesSchoolRoomSC1(), SubjectSixiemeTestProvider.francais(), francaisTeacher,
                                        TimeSlotTestProvider.timeSlot(1L, EnumDays.MONDAY, 8, EnumPartsOfDay.AM, 9, EnumPartsOfDay.AM)),
                                new Lesson(2L, SchoolRoomTestProvider.normalSchoolRoom101(), SubjectSixiemeTestProvider.francais(), francaisTeacher,
                                        TimeSlotTestProvider.timeSlot(2L, EnumDays.MONDAY, 8, EnumPartsOfDay.AM, 9, EnumPartsOfDay.AM))
                        )
                )
        );
    }
}
