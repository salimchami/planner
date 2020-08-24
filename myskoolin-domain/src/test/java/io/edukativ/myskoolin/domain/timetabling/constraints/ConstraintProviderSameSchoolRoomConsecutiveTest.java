package io.edukativ.myskoolin.domain.timetabling.constraints;

import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoom;
import io.edukativ.myskoolin.domain.subjects.Subject;
import io.edukativ.myskoolin.domain.timetabling.Lesson;
import io.edukativ.myskoolin.domain.timetabling.TimeSlot;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class ConstraintProviderSameSchoolRoomConsecutiveTest extends ConstraintProviderTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("conflictParams")
    void subjectsDayDurationPenalty(String description, int expectedConflictPenalty,
                                    SchoolRoom schoolRoom1, SchoolRoom schoolRoom2,
                                    Subject subject1, Subject subject2,
                                    TimeSlot firstTimeSlot, TimeSlot secondTimeSlot) {

        Lesson lesson1 = new Lesson(1L, schoolRoom1, subject1, null, firstTimeSlot, null);
        Lesson lesson2 = new Lesson(2L, schoolRoom2, subject2, null, secondTimeSlot, null);

        constraintVerifier.verifyThat(TimeTableConstraintsProvider::sameSchoolRoomIfConsecutiveLessonsPenalty)
                .given(lesson1, lesson2)
                .penalizesBy(expectedConflictPenalty);
    }

    private static Stream<Arguments> conflictParams() {
        prepareParams();
        return Stream.of(
                Arguments.of("<> timeslots, == schoolRoom", 0, schoolRoom1, schoolRoom1, subject1, subject1, timeSlot1, timeSlot2),
                Arguments.of("<> timeslots, <> schoolRoom", 1, schoolRoom1, schoolRoom2, subject1, subject1, timeSlot1, timeSlot2)
        );
    }

}
