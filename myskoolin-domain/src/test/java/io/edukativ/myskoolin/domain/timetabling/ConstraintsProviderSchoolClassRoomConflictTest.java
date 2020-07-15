package io.edukativ.myskoolin.domain.timetabling;

import io.edukativ.myskoolin.domain.schoolclasses.SchoolClass;
import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoom;
import io.edukativ.myskoolin.domain.subjects.Subject;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.optaplanner.test.api.score.stream.ConstraintVerifier;

import java.util.stream.Stream;

public class ConstraintsProviderSchoolClassRoomConflictTest extends ConstraintProviderTest {

    private final ConstraintVerifier<TimeTableConstraintsProvider, SchoolClassTimeTable> constraintVerifier
            = ConstraintVerifier.build(new TimeTableConstraintsProvider(), SchoolClassTimeTable.class, Lesson.class);

    @ParameterizedTest(name = "{0}")
    @MethodSource("conflictParams")
    public void roomConflict(String description, SchoolRoom firstSchoolRoom, SchoolRoom secondSchoolRoom,
                             Subject firstSubject, Subject secondSubject, SchoolClass firstSchoolClass, SchoolClass secondSchoolClass,
                             TimeSlot firstTimeSlot, TimeSlot secondTimeSlot,
                             int expectedRoomConflictPenalty) {

        Lesson lesson1 = new Lesson(1L, firstSchoolRoom, firstSubject, null, firstTimeSlot, firstSchoolClass);
        Lesson lesson2 = new Lesson(1L, secondSchoolRoom, secondSubject, null, secondTimeSlot, secondSchoolClass);
        constraintVerifier.verifyThat(TimeTableConstraintsProvider::conflictBetweenTimeTables)
                .given(lesson1, lesson2)
                .penalizesBy(expectedRoomConflictPenalty);
    }

    private static Stream<Arguments> conflictParams() {
        prepareParams();
        return Stream.of(
                Arguments.of("== schoolRoom, == subject, == schoolClass, == timeSlot", schoolRoom1, schoolRoom1, subject1, subject1, schoolClass1, schoolClass1, timeSlot1, timeSlot1, 0),
                Arguments.of("== schoolRoom, == subject, == schoolClass, <> timeSlot", schoolRoom1, schoolRoom1, subject1, subject1, schoolClass1, schoolClass1, timeSlot1, timeSlot2, 0),

                Arguments.of("== schoolRoom, == subject, <> schoolClass, == timeSlot", schoolRoom1, schoolRoom1, subject1, subject1, schoolClass1, schoolClass2, timeSlot1, timeSlot1, 2),
                Arguments.of("== schoolRoom, == subject, <> schoolClass, <> timeSlot", schoolRoom1, schoolRoom1, subject1, subject1, schoolClass1, schoolClass2, timeSlot1, timeSlot2, 0),
                Arguments.of("== schoolRoom, <> subject, == schoolClass, == timeSlot", schoolRoom1, schoolRoom1, subject1, subject2, schoolClass1, schoolClass1, timeSlot1, timeSlot1, 2),
                Arguments.of("== schoolRoom, <> subject, == schoolClass, <> timeSlot", schoolRoom1, schoolRoom1, subject1, subject2, schoolClass1, schoolClass1, timeSlot1, timeSlot2, 0),
                Arguments.of("== schoolRoom, <> subject, <> schoolClass, == timeSlot", schoolRoom1, schoolRoom1, subject1, subject2, schoolClass1, schoolClass2, timeSlot1, timeSlot1, 2),
                Arguments.of("== schoolRoom, <> subject, <> schoolClass, <> timeSlot", schoolRoom1, schoolRoom1, subject1, subject2, schoolClass1, schoolClass2, timeSlot1, timeSlot2, 0),
                Arguments.of("<> schoolRoom, == subject, == schoolClass, == timeSlot", schoolRoom1, schoolRoom2, subject1, subject1, schoolClass1, schoolClass1, timeSlot1, timeSlot1, 2),
                Arguments.of("<> schoolRoom, == subject, == schoolClass, <> timeSlot", schoolRoom1, schoolRoom2, subject1, subject1, schoolClass1, schoolClass1, timeSlot1, timeSlot2, 0),
                Arguments.of("<> schoolRoom, == subject, <> schoolClass, == timeSlot", schoolRoom1, schoolRoom2, subject1, subject1, schoolClass1, schoolClass2, timeSlot1, timeSlot1, 0),
                Arguments.of("<> schoolRoom, == subject, <> schoolClass, <> timeSlot", schoolRoom1, schoolRoom2, subject1, subject1, schoolClass1, schoolClass2, timeSlot1, timeSlot2, 0),
                Arguments.of("<> schoolRoom, <> subject, == schoolClass, == timeSlot", schoolRoom1, schoolRoom2, subject1, subject2, schoolClass1, schoolClass1, timeSlot1, timeSlot1, 2),
                Arguments.of("<> schoolRoom, <> subject, == schoolClass, <> timeSlot", schoolRoom1, schoolRoom2, subject1, subject2, schoolClass1, schoolClass1, timeSlot1, timeSlot2, 0),
                Arguments.of("<> schoolRoom, <> subject, <> schoolClass, == timeSlot", schoolRoom1, schoolRoom2, subject1, subject2, schoolClass1, schoolClass2, timeSlot1, timeSlot1, 0),
                Arguments.of("<> schoolRoom, <> subject, <> schoolClass, <> timeSlot", schoolRoom1, schoolRoom2, subject1, subject2, schoolClass1, schoolClass2, timeSlot1, timeSlot2, 0)
        );
    }
}
