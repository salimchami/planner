package io.edukativ.myskoolin.domain.timetabling.constraints;

import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoom;
import io.edukativ.myskoolin.domain.subjects.Subject;
import io.edukativ.myskoolin.domain.teachers.Teacher;
import io.edukativ.myskoolin.domain.timetabling.Lesson;
import io.edukativ.myskoolin.domain.timetabling.TimeSlot;
import io.edukativ.myskoolin.domain.timetabling.solving.ScoreConstraintVerifierTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class ConstraintsProviderGlobalConflictsTest extends ScoreConstraintVerifierTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("conflictParams")
    @Disabled
    void roomConflict(String description, int expectedConflictPenalty, SchoolRoom firstSchoolRoom,
                      SchoolRoom secondSchoolRoom, Teacher firstTeacher, Teacher secondTeacher,
                      Subject firstSubject, Subject secondSubject, TimeSlot firstTimeSlot,
                      TimeSlot secondTimeSlot) {

        Lesson lesson1 = new Lesson(1L, firstSchoolRoom, firstSubject, firstTeacher, firstTimeSlot, schoolClass1);
        Lesson lesson2 = new Lesson(2L, secondSchoolRoom, secondSubject, secondTeacher, secondTimeSlot, schoolClass1);
//        constraintVerifier.verifyThat(TimeTableConstraintsProvider::globalConflictsForSameSchoolClass)
//                .given(lesson1, lesson2)
//                .penalizesBy(expectedConflictPenalty);
    }

    private static Stream<Arguments> conflictParams() {
        prepareParams();
        return Stream.of(
                Arguments.of("== schoolRoom, == teacher, == subject, == timeSlot", 4, schoolRoom1, schoolRoom1, teacher1, teacher1, subject1, subject1, timeSlot1, timeSlot1),
                Arguments.of("== schoolRoom, == teacher, <> subject, == timeSlot", 4, schoolRoom1, schoolRoom1, teacher1, teacher1, subject1, subject2, timeSlot1, timeSlot1),
                Arguments.of("== schoolRoom, <> teacher, == subject, == timeSlot", 4, schoolRoom1, schoolRoom1, teacher1, teacher2, subject1, subject1, timeSlot1, timeSlot1),
                Arguments.of("== schoolRoom, <> teacher, <> subject, == timeSlot", 4, schoolRoom1, schoolRoom1, teacher1, teacher2, subject1, subject2, timeSlot1, timeSlot1),
                Arguments.of("<> schoolRoom, == teacher, == subject, == timeSlot", 4, schoolRoom1, schoolRoom2, teacher1, teacher1, subject1, subject1, timeSlot1, timeSlot1),
                Arguments.of("<> schoolRoom, == teacher, <> subject, == timeSlot", 4, schoolRoom1, schoolRoom2, teacher1, teacher1, subject1, subject2, timeSlot1, timeSlot1),
                Arguments.of("<> schoolRoom, <> teacher, == subject, == timeSlot", 4, schoolRoom1, schoolRoom2, teacher1, teacher2, subject1, subject1, timeSlot1, timeSlot1),
                Arguments.of("<> schoolRoom, <> teacher, <> subject, == timeSlot", 4, schoolRoom1, schoolRoom2, teacher1, teacher2, subject1, subject2, timeSlot1, timeSlot1),

                Arguments.of("== schoolRoom, == teacher, == subject, <> timeSlot", 0, schoolRoom1, schoolRoom1, teacher1, teacher1, subject1, subject1, timeSlot1, timeSlot2),
                Arguments.of("== schoolRoom, == teacher, <> subject, <> timeSlot", 0, schoolRoom1, schoolRoom1, teacher1, teacher1, subject1, subject2, timeSlot1, timeSlot2),
                Arguments.of("== schoolRoom, <> teacher, == subject, <> timeSlot", 0, schoolRoom1, schoolRoom1, teacher1, teacher2, subject1, subject1, timeSlot1, timeSlot2),
                Arguments.of("== schoolRoom, <> teacher, <> subject, <> timeSlot", 0, schoolRoom1, schoolRoom1, teacher1, teacher2, subject1, subject2, timeSlot1, timeSlot2),
                Arguments.of("<> schoolRoom, == teacher, == subject, <> timeSlot", 0, schoolRoom1, schoolRoom2, teacher1, teacher1, subject1, subject1, timeSlot1, timeSlot2),
                Arguments.of("<> schoolRoom, == teacher, <> subject, <> timeSlot", 0, schoolRoom1, schoolRoom2, teacher1, teacher1, subject1, subject2, timeSlot1, timeSlot2),
                Arguments.of("<> schoolRoom, <> teacher, == subject, <> timeSlot", 0, schoolRoom1, schoolRoom2, teacher1, teacher2, subject1, subject1, timeSlot1, timeSlot2),
                Arguments.of("<> schoolRoom, <> teacher, <> subject, <> timeSlot", 0, schoolRoom1, schoolRoom2, teacher1, teacher2, subject1, subject2, timeSlot1, timeSlot2)
        );
    }
}
