package io.edukativ.myskoolin.domain.timetabling;

import io.edukativ.myskoolin.domain.commercial.Client;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class TimeSlotTest {


    @ParameterizedTest
    @MethodSource("isOverlappingParams")
    @Disabled
    void isOverlapping(DayOfWeek day1, LocalTime startTime1, LocalTime endTime1,
                       DayOfWeek day2, LocalTime startTime2, LocalTime endTime2,
                       boolean expectedOverlapping) {
        TimeSlot timeSlot1 = new TimeSlot(day1, startTime1, endTime1);
        TimeSlot timeSlot2 = new TimeSlot(day2, startTime2, endTime2);
        final boolean isOverlapping = timeSlot1.isOverlapping(timeSlot2);
        assertThat(isOverlapping).isEqualTo(expectedOverlapping);
    }

    private static Stream<Arguments> isOverlappingParams() {
        return Stream.of(
                Arguments.of(
                        DayOfWeek.MONDAY,
                        LocalTime.of(8, 0, 0),
                        LocalTime.of(9, 0, 0),
                        DayOfWeek.MONDAY,
                        LocalTime.of(9, 0, 0),
                        LocalTime.of(10, 0, 0),
                        false
                ),
                Arguments.of(
                        DayOfWeek.MONDAY,
                        LocalTime.of(8, 0, 0),
                        LocalTime.of(9, 0, 0),
                        DayOfWeek.MONDAY,
                        LocalTime.of(11, 0, 0),
                        LocalTime.of(12, 0, 0),
                        false
                ),
                Arguments.of(
                        DayOfWeek.MONDAY,
                        LocalTime.of(8, 0, 0),
                        LocalTime.of(9, 0, 0),
                        DayOfWeek.MONDAY,
                        LocalTime.of(8, 30, 0),
                        LocalTime.of(9, 30, 0),
                        true
                ),
                Arguments.of(
                        DayOfWeek.MONDAY,
                        LocalTime.of(8, 0, 0),
                        LocalTime.of(9, 0, 0),
                        DayOfWeek.MONDAY,
                        LocalTime.of(8, 0, 0),
                        LocalTime.of(9, 0, 0),
                        true
                ),
                Arguments.of(
                        DayOfWeek.MONDAY,
                        LocalTime.of(8, 0, 0),
                        LocalTime.of(9, 0, 0),
                        DayOfWeek.TUESDAY,
                        LocalTime.of(8, 0, 0),
                        LocalTime.of(9, 0, 0),
                        false
                )
        );
    }

    @Test
    void next() {
        final LocalTime startTime = LocalTime.of(8, 0, 0);
        final LocalTime endTime = LocalTime.of(9, 0, 0);
        TimeSlot timeSlot = new TimeSlot(DayOfWeek.MONDAY, startTime, endTime);

        final List<TimeSlot> timeSlots = Client.defaultCoursesTimeSlots();
        final TimeSlot next = timeSlot.next(timeSlots);

        assertThat(next.getDay()).isEqualByComparingTo(DayOfWeek.MONDAY);
        assertThat(next.getStartTime()).isEqualTo(endTime);
    }

    @Test
    void isBefore1() {
        final LocalTime startTime1 = LocalTime.of(8, 0, 0);
        final LocalTime endTime1 = LocalTime.of(9, 0, 0);
        TimeSlot timeSlot1 = new TimeSlot(DayOfWeek.MONDAY, startTime1, endTime1);

        final LocalTime startTime2 = LocalTime.of(9, 0, 0);
        final LocalTime endTime2 = LocalTime.of(10, 0, 0);
        TimeSlot timeSlot2 = new TimeSlot(DayOfWeek.MONDAY, startTime2, endTime2);

        assertThat(timeSlot1.isBefore(timeSlot2)).isTrue();
    }


    @Test
    void isBefore2() {
        final LocalTime startTime1 = LocalTime.of(8, 0, 0);
        final LocalTime endTime1 = LocalTime.of(9, 0, 0);
        TimeSlot timeSlot1 = new TimeSlot(DayOfWeek.MONDAY, startTime1, endTime1);

        final LocalTime startTime2 = LocalTime.of(11, 0, 0);
        final LocalTime endTime2 = LocalTime.of(12, 0, 0);
        TimeSlot timeSlot2 = new TimeSlot(DayOfWeek.MONDAY, startTime2, endTime2);

        assertThat(timeSlot1.isBefore(timeSlot2)).isTrue();
    }

    @Test
    void isBefore3() {
        final LocalTime startTime1 = LocalTime.of(8, 0, 0);
        final LocalTime endTime1 = LocalTime.of(9, 0, 0);
        TimeSlot timeSlot1 = new TimeSlot(DayOfWeek.MONDAY, startTime1, endTime1);

        final LocalTime startTime2 = LocalTime.of(8, 0, 0);
        final LocalTime endTime2 = LocalTime.of(9, 0, 0);
        TimeSlot timeSlot2 = new TimeSlot(DayOfWeek.THURSDAY, startTime2, endTime2);

        assertThat(timeSlot1.isBefore(timeSlot2)).isTrue();
    }

    @Test
    void isNotBefore() {
        final LocalTime startTime1 = LocalTime.of(8, 0, 0);
        final LocalTime endTime1 = LocalTime.of(9, 0, 0);
        TimeSlot timeSlot1 = new TimeSlot(DayOfWeek.MONDAY, startTime1, endTime1);

        final LocalTime startTime2 = LocalTime.of(9, 0, 0);
        final LocalTime endTime2 = LocalTime.of(10, 0, 0);
        TimeSlot timeSlot2 = new TimeSlot(DayOfWeek.MONDAY, startTime2, endTime2);

        assertThat(timeSlot2.isBefore(timeSlot1)).isFalse();
    }
}
