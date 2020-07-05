package io.edukativ.myskoolin.domain.timetabling;

import io.edukativ.myskoolin.domain.commons.vo.EnumDays;
import io.edukativ.myskoolin.domain.commons.vo.EnumPartsOfDay;
import io.edukativ.myskoolin.domain.grades.Grade;
import io.edukativ.myskoolin.domain.providers.GlobalTestProvider;
import io.edukativ.myskoolin.domain.providers.GradeTestProvider;
import io.edukativ.myskoolin.domain.providers.SchoolRoomTestProvider;
import io.edukativ.myskoolin.domain.providers.UserTestProvider;
import io.edukativ.myskoolin.domain.providers.subjects.SubjectTestProvider;
import io.edukativ.myskoolin.domain.schoolclasses.SchoolClass;
import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoom;
import io.edukativ.myskoolin.domain.subjects.Subject;
import io.edukativ.myskoolin.domain.teachers.Teacher;
import io.edukativ.myskoolin.domain.timetabling.constraints.TimeTableConstraintsProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.optaplanner.test.api.score.stream.ConstraintVerifier;

import java.util.List;
import java.util.stream.Stream;

public class TTSolverTest {
    private static SchoolRoom schoolRoom1;
    private static SchoolClass schoolClass;
    private static SchoolRoom schoolRoom2;
    private static Subject subject1;
    private static Subject subject2;
    private static Teacher teacher1;
    private static Teacher teacher2;
    private static TimeSlot timeSlot1;
    private static TimeSlot timeSlot2;

    private final ConstraintVerifier<TimeTableConstraintsProvider, SchoolClassTimeTable> constraintVerifier
            = ConstraintVerifier.build(new TimeTableConstraintsProvider(), SchoolClassTimeTable.class, Lesson.class);

    @ParameterizedTest(name = "{0}")
    @MethodSource("conflictParams")
    public void roomConflict(String description, SchoolRoom firstSchoolRoom, SchoolRoom secondSchoolRoom,
                             Subject firstSubject, Subject secondSubject, Teacher firstTeacher,
                             Teacher secondTeacher, TimeSlot firstTimeSlot, TimeSlot secondTimeSlot,
                             SchoolClass schoolClass,
                             int expectedRoomConflictPenalty, int expectedSchoolClassConflictPenalty,
                             int expectedSchoolClassSubjectVarietyPenalty, int expectedTeacherConflictPenalty,
                             int expectedTeacherRoomStabilityPenalty, int expectedTeacherTimeEfficiencyPenalty,
                             int expectedTimeSlotConflictPenalty) {

        Lesson lesson1 = new Lesson(1L, firstSchoolRoom, firstSubject, firstTeacher, firstTimeSlot, schoolClass);
        Lesson lesson2 = new Lesson(2L, secondSchoolRoom, secondSubject, secondTeacher, secondTimeSlot, schoolClass);

        constraintVerifier.verifyThat(TimeTableConstraintsProvider::roomConflict)
                .given(lesson1, lesson2)
                .penalizesBy(expectedRoomConflictPenalty);
        constraintVerifier.verifyThat(TimeTableConstraintsProvider::schoolClassConflict)
                .given(lesson1, lesson2)
                .penalizesBy(expectedSchoolClassConflictPenalty);
        constraintVerifier.verifyThat(TimeTableConstraintsProvider::schoolClassSubjectVariety)
                .given(lesson1, lesson2)
                .penalizesBy(expectedSchoolClassSubjectVarietyPenalty);
        constraintVerifier.verifyThat(TimeTableConstraintsProvider::teacherConflict)
                .given(lesson1, lesson2)
                .penalizesBy(expectedTeacherConflictPenalty);
//        constraintVerifier.verifyThat(TimeTableConstraintsProvider::teacherRoomStability)
//                .given(lesson1, lesson2)
//                .penalizesBy(expectedTeacherRoomStabilityPenalty);
//        constraintVerifier.verifyThat(TimeTableConstraintsProvider::teacherTimeEfficiency)
//                .given(lesson1, lesson2)
//                .penalizesBy(expectedTeacherTimeEfficiencyPenalty);
//        constraintVerifier.verifyThat(TimeTableConstraintsProvider::timeSlotConflict)
//                .given(lesson1, lesson2)
//                .penalizesBy(expectedTimeSlotConflictPenalty);
    }


    private static Stream<Arguments> conflictParams() {
        prepareParams();
        return Stream.of(
                Arguments.of("== schoolRoom, == subjects, == teacher, == timeSlot", schoolRoom1, schoolRoom1, subject1,
                        subject1, teacher1, teacher1, timeSlot1, timeSlot1, schoolClass, 1, 1, 0, 1, 1, 1, 1),
                Arguments.of("== schoolRoom, == subjects, == teacher, <> timeSlot", schoolRoom1, schoolRoom1, subject1,
                        subject1, teacher1, teacher1, timeSlot1, timeSlot2, schoolClass, 0, 0, 1, 0, 1, 1, 1),
                Arguments.of("== schoolRoom, == subjects, <> teacher, == timeSlot", schoolRoom1, schoolRoom1, subject1,
                        subject1, teacher1, teacher2, timeSlot1, timeSlot1, schoolClass, 1, 1, 0, 0, 1, 1, 1),
                Arguments.of("== schoolRoom, == subjects, <> teacher, <> timeSlot", schoolRoom1, schoolRoom1, subject1,
                        subject1, teacher1, teacher2, timeSlot1, timeSlot2, schoolClass, 0, 0, 1, 0, 1, 1, 1),
                Arguments.of("== schoolRoom, <> subjects, == teacher, == timeSlot", schoolRoom1, schoolRoom1, subject1,
                        subject2, teacher1, teacher1, timeSlot1, timeSlot1, schoolClass, 1, 1, 0, 1, 1, 1, 1),
                Arguments.of("== schoolRoom, <> subjects, == teacher, <> timeSlot", schoolRoom1, schoolRoom1, subject1,
                        subject2, teacher1, teacher1, timeSlot1, timeSlot2, schoolClass, 0, 0, 0, 0, 1, 1, 1),
                Arguments.of("== schoolRoom, <> subjects, <> teacher, == timeSlot", schoolRoom1, schoolRoom1, subject1,
                        subject2, teacher1, teacher2, timeSlot1, timeSlot1, schoolClass, 1, 1, 0, 0, 1, 1, 1),
                Arguments.of("== schoolRoom, <> subjects, <> teacher, <> timeSlot", schoolRoom1, schoolRoom1, subject1,
                        subject2, teacher1, teacher2, timeSlot1, timeSlot2, schoolClass, 0, 0, 0, 0, 1, 1, 1),
                Arguments.of("<> schoolRoom, == subjects, == teacher, == timeSlot", schoolRoom1, schoolRoom2, subject1,
                        subject1, teacher1, teacher1, timeSlot1, timeSlot1, schoolClass, 0, 1, 0, 1, 1, 1, 1),
                Arguments.of("<> schoolRoom, == subjects, == teacher, <> timeSlot", schoolRoom1, schoolRoom2, subject1,
                        subject1, teacher1, teacher1, timeSlot1, timeSlot2, schoolClass, 0, 0, 1, 0, 1, 1, 1),
                Arguments.of("<> schoolRoom, == subjects, <> teacher, == timeSlot", schoolRoom1, schoolRoom2, subject1,
                        subject1, teacher1, teacher2, timeSlot1, timeSlot1, schoolClass, 0, 1, 0, 0, 1, 1, 1),
                Arguments.of("<> schoolRoom, == subjects, <> teacher, <> timeSlot", schoolRoom1, schoolRoom2, subject1,
                        subject1, teacher1, teacher2, timeSlot1, timeSlot2, schoolClass, 0, 0, 1, 0, 1, 1, 1),
                Arguments.of("<> schoolRoom, <> subjects, == teacher, == timeSlot", schoolRoom1, schoolRoom2, subject1,
                        subject2, teacher1, teacher1, timeSlot1, timeSlot1, schoolClass, 0, 1, 0, 1, 1, 1, 1),
                Arguments.of("<> schoolRoom, <> subjects, == teacher, <> timeSlot", schoolRoom1, schoolRoom2, subject1,
                        subject2, teacher1, teacher1, timeSlot1, timeSlot2, schoolClass, 0, 0, 0, 0, 1, 1, 1),
                Arguments.of("<> schoolRoom, <> subjects, <> teacher, == timeSlot", schoolRoom1, schoolRoom2, subject1,
                        subject2, teacher1, teacher2, timeSlot1, timeSlot1, schoolClass, 0, 1, 0, 0, 1, 1, 1),
                Arguments.of("<> schoolRoom, <> subjects, <> teacher, <> timeSlot", schoolRoom1, schoolRoom2, subject1,
                        subject2, teacher1, teacher2, timeSlot1, timeSlot2, schoolClass, 0, 0, 0, 0, 1, 1, 1)
        );
    }

    private static void prepareParams() {
        List<Grade> grades = GradeTestProvider.allGrades();
        List<SchoolRoom> allSchoolRooms = SchoolRoomTestProvider.allSchoolRooms();
        List<Subject> subjects = SubjectTestProvider.allSubjects();
        List<Teacher> teachers = UserTestProvider.teacherUsers(GlobalTestProvider.CLIENT_ID, subjects, grades);
        schoolClass = new SchoolClass();
        schoolClass.setClientId(GlobalTestProvider.CLIENT_ID);
        schoolClass.setId("1");
        schoolRoom1 = allSchoolRooms.stream().filter(sr -> sr.getName().equals("100")).findFirst().get();
        schoolRoom2 = allSchoolRooms.stream().filter(sr -> sr.getName().equals("101")).findFirst().get();
        subject1 = subjects.stream().filter(s -> s.getId().equals(GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_FRANCAIS_ID)).findFirst().get();
        subject2 = subjects.stream().filter(s -> s.getId().equals(GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_MATHS_ID)).findFirst().get();
        teacher1 = teachers.stream().filter(t -> t.getId().equals(GlobalTestProvider.TEACHER_MATHS_1_ID)).findFirst().get();
        teacher2 = teachers.stream().filter(t -> t.getId().equals(GlobalTestProvider.TEACHER_MATHS_2_ID)).findFirst().get();
        timeSlot1 = new TimeSlot(1L, EnumDays.MONDAY,
                new Time(8, 0, 0, EnumPartsOfDay.AM),
                new Time(9, 0, 0, EnumPartsOfDay.AM)
        );
        timeSlot2 = new TimeSlot(2L, EnumDays.MONDAY,
                new Time(9, 0, 0, EnumPartsOfDay.AM),
                new Time(10, 0, 0, EnumPartsOfDay.AM)
        );
    }
}
