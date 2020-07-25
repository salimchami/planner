package io.edukativ.myskoolin.domain.timetabling;

import io.edukativ.myskoolin.domain.timetabling.constraints.ConstraintProviderTest;
import io.edukativ.myskoolin.domain.timetabling.constraints.TimeTableConstraintsProvider;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.optaplanner.test.api.score.stream.ConstraintVerifier;

import java.util.stream.Stream;

class ConstraintsProviderTimeSlotConflictsTest extends ConstraintProviderTest {

    private final ConstraintVerifier<TimeTableConstraintsProvider, SchoolClassTimeTable> constraintVerifier
            = ConstraintVerifier.build(new TimeTableConstraintsProvider(), SchoolClassTimeTable.class, Lesson.class);

    @ParameterizedTest(name = "{0}")
    @MethodSource("conflictParams")
    @Disabled
    void timeSlotOverlappingConflict(String description, int expectedTimeSlotConflictPenalty, TimeSlot firstTimeSlot,
                      TimeSlot secondTimeSlot) {

        Lesson lesson1 = new Lesson(1L, null, null, null, firstTimeSlot, null);
        Lesson lesson2 = new Lesson(2L, null, null, null, secondTimeSlot, null);
        constraintVerifier.verifyThat(TimeTableConstraintsProvider::timeSlotsOverlappingConflictPenalty)
                .given(lesson1, lesson2)
                .penalizesBy(expectedTimeSlotConflictPenalty);
    }

    private static Stream<Arguments> conflictParams() {
        prepareParams();
        return Stream.of(
                Arguments.of("== timeSlot", 4, timeSlot1, timeSlot1),
                Arguments.of("<> timeSlot", 0, timeSlot1, timeSlot2)
        );
    }
}
