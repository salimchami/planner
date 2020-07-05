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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.optaplanner.test.api.score.stream.ConstraintVerifier;

import java.util.List;
import java.util.stream.Stream;

public class TTSolverTest {

    private final ConstraintVerifier<TimeTableConstraintsProvider, SchoolClassTimeTable> constraintVerifier
            = ConstraintVerifier.build(new TimeTableConstraintsProvider(), SchoolClassTimeTable.class, Lesson.class);
    private static List<SchoolRoom> allSchoolRooms;
    private static List<Subject> subjects;
    private static List<Teacher> teachers;
    private static List<Grade> grades;

    @BeforeEach
    void setUp() {
        List<Grade> grades = GradeTestProvider.allGrades();
        allSchoolRooms = SchoolRoomTestProvider.allSchoolRooms();
        subjects = SubjectTestProvider.allSubjects();
        teachers = UserTestProvider.teacherUsers(GlobalTestProvider.CLIENT_ID, subjects, grades);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("roomConflictParams")
    public void roomConflict(String description, SchoolRoom schoolRoom1, SchoolRoom schoolRoom2,
                                                                 Subject subject1, Subject subject2, Teacher teacher1,
                                                                 Teacher teacher2, TimeSlot timeSlot1, TimeSlot timeSlot2,
                                                                 SchoolClass schoolClass, int expectedMatchWeightTotal) {
        Lesson lesson1 = new Lesson(1L, schoolRoom1, subject1, teacher1, timeSlot1, schoolClass);
        Lesson lesson2 = new Lesson(2L, schoolRoom2, subject2, teacher2, timeSlot2, schoolClass);

        constraintVerifier.verifyThat(TimeTableConstraintsProvider::roomConflict)
                .given(lesson1, lesson2)
                .penalizesBy(expectedMatchWeightTotal);
    }

    private static Stream<Arguments> roomConflictParams() {
        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setClientId(GlobalTestProvider.CLIENT_ID);
        schoolClass.setId("1");
        SchoolRoom schoolRoom1 = allSchoolRooms.stream().filter(sr -> sr.getName().equals("100")).findFirst().get();
        SchoolRoom schoolRoom2 = allSchoolRooms.stream().filter(sr -> sr.getName().equals("101")).findFirst().get();
        Subject subject1 = subjects.stream().filter(s -> s.getId().equals(GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_FRANCAIS_ID)).findFirst().get();
        Subject subject2 = subjects.stream().filter(s -> s.getId().equals(GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_MATHS_ID)).findFirst().get();
        Teacher teacher1 = teachers.stream().filter(t -> t.getId().equals(GlobalTestProvider.TEACHER_MATHS_1_ID)).findFirst().get();
        Teacher teacher2 = teachers.stream().filter(t -> t.getId().equals(GlobalTestProvider.TEACHER_MATHS_2_ID)).findFirst().get();
        TimeSlot timeSlot1 = new TimeSlot(EnumDays.MONDAY,
                new Time(8, 0, 0, EnumPartsOfDay.AM),
                new Time(9, 0, 0, EnumPartsOfDay.AM)
        );
        TimeSlot timeSlot2 = new TimeSlot(EnumDays.MONDAY,
                new Time(9, 0, 0, EnumPartsOfDay.AM),
                new Time(10, 0, 0, EnumPartsOfDay.AM)
        );
        return Stream.of(
                Arguments.of("== schoolRoom, <> subjects, == timeSlot, == teacher", schoolRoom1, schoolRoom1, subject1, subject2, teacher1, teacher1, timeSlot1, timeSlot1, schoolClass, 1)
        );
    }

}
