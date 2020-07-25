package io.edukativ.myskoolin.domain.timetabling.constraints;

import io.edukativ.myskoolin.domain.commons.vo.EnumDays;
import io.edukativ.myskoolin.domain.commons.vo.EnumPartsOfDay;
import io.edukativ.myskoolin.domain.timetabling.Lesson;
import io.edukativ.myskoolin.domain.timetabling.Time;
import io.edukativ.myskoolin.domain.timetabling.TimeSlot;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class ConstraintProviderOverlappingTest extends ConstraintProviderTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("conflictParams")
    void roomConflict(String description, int expectedConflictPenalty, TimeSlot firstTimeSlot, TimeSlot secondTimeSlot) {

        Lesson lesson1 = new Lesson(1L, null, null, null, firstTimeSlot, null);
        Lesson lesson2 = new Lesson(2L, null, null, null, secondTimeSlot, null);

        constraintVerifier.verifyThat(TimeTableConstraintsProvider::timeSlotsOverlappingConflictPenalty)
                .given(lesson1, lesson2)
                .penalizesBy(expectedConflictPenalty);
    }

    private static Stream<Arguments> conflictParams() {
        prepareParams();
        return Stream.of(
                Arguments.of("== timeSlot", 60, timeSlot1, timeSlot1),
                Arguments.of("<> timeSlot", 0, timeSlot1, timeSlot2),
                Arguments.of("<> timeSlot", 30, timeSlot1, new TimeSlot(3L, EnumDays.MONDAY,
                        new Time(8, 30, 0, EnumPartsOfDay.AM),
                        new Time(10, 0, 0, EnumPartsOfDay.AM)))
        );
    }

}
