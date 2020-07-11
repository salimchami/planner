package io.edukativ.myskoolin.domain.timetabling;

import io.edukativ.myskoolin.domain.commercial.Client;
import io.edukativ.myskoolin.domain.commons.vo.EnumDays;
import io.edukativ.myskoolin.domain.commons.vo.EnumPartsOfDay;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class TimeSlotTest {


    @ParameterizedTest
    @MethodSource("isOverlappingParams")
    @Disabled
    void isOverlapping(EnumDays day1, Time startTime1, Time endTime1,
                       EnumDays day2, Time startTime2, Time endTime2,
                       boolean expectedOverlapping) {
        TimeSlot timeSlot1 = new TimeSlot(day1, startTime1, endTime1);
        TimeSlot timeSlot2 = new TimeSlot(day2, startTime2, endTime2);
        final boolean isOverlapping = timeSlot1.isOverlapping(timeSlot2);
        assertThat(isOverlapping).isEqualTo(expectedOverlapping);
    }

    private static Stream<Arguments> isOverlappingParams() {
        return Stream.of(
                Arguments.of(
                        EnumDays.MONDAY,
                        new Time(8, 0, 0, EnumPartsOfDay.AM),
                        new Time(9, 0, 0, EnumPartsOfDay.AM),
                        EnumDays.MONDAY,
                        new Time(9, 0, 0, EnumPartsOfDay.AM),
                        new Time(10, 0, 0, EnumPartsOfDay.AM),
                        false
                ),
                Arguments.of(
                        EnumDays.MONDAY,
                        new Time(8, 0, 0, EnumPartsOfDay.AM),
                        new Time(9, 0, 0, EnumPartsOfDay.AM),
                        EnumDays.MONDAY,
                        new Time(11, 0, 0, EnumPartsOfDay.AM),
                        new Time(12, 0, 0, EnumPartsOfDay.AM),
                        false
                ),
                Arguments.of(
                        EnumDays.MONDAY,
                        new Time(8, 0, 0, EnumPartsOfDay.AM),
                        new Time(9, 0, 0, EnumPartsOfDay.AM),
                        EnumDays.MONDAY,
                        new Time(8, 30, 0, EnumPartsOfDay.AM),
                        new Time(9, 30, 0, EnumPartsOfDay.AM),
                        true
                ),
                Arguments.of(
                        EnumDays.MONDAY,
                        new Time(8, 0, 0, EnumPartsOfDay.AM),
                        new Time(9, 0, 0, EnumPartsOfDay.AM),
                        EnumDays.MONDAY,
                        new Time(8, 0, 0, EnumPartsOfDay.AM),
                        new Time(9, 0, 0, EnumPartsOfDay.AM),
                        true
                ),
                Arguments.of(
                        EnumDays.MONDAY,
                        new Time(8, 0, 0, EnumPartsOfDay.AM),
                        new Time(9, 0, 0, EnumPartsOfDay.AM),
                        EnumDays.TUESDAY,
                        new Time(8, 0, 0, EnumPartsOfDay.AM),
                        new Time(9, 0, 0, EnumPartsOfDay.AM),
                        false
                )
        );
    }

    @Test
    void next() {
        final Time startTime = new Time(8, 0, 0, EnumPartsOfDay.AM);
        final Time endTime = new Time(9, 0, 0, EnumPartsOfDay.AM);
        TimeSlot timeSlot = new TimeSlot(EnumDays.MONDAY, startTime, endTime);

        final List<TimeSlot> timeSlots = Client.defaultCoursesTimeSlots();
        final TimeSlot next = timeSlot.next(timeSlots);

        assertThat(next.getDay()).isEqualByComparingTo(EnumDays.MONDAY);
        assertThat(next.getStartTime()).isEqualByComparingTo(endTime);
    }

    @Test
    void isBefore1() {
        final Time startTime1 = new Time(8, 0, 0, EnumPartsOfDay.AM);
        final Time endTime1 = new Time(9, 0, 0, EnumPartsOfDay.AM);
        TimeSlot timeSlot1 = new TimeSlot(EnumDays.MONDAY, startTime1, endTime1);

        final Time startTime2 = new Time(9, 0, 0, EnumPartsOfDay.AM);
        final Time endTime2 = new Time(10, 0, 0, EnumPartsOfDay.AM);
        TimeSlot timeSlot2 = new TimeSlot(EnumDays.MONDAY, startTime2, endTime2);

        assertThat(timeSlot1.isBefore(timeSlot2)).isTrue();
    }


    @Test
    void isBefore2() {
        final Time startTime1 = new Time(8, 0, 0, EnumPartsOfDay.AM);
        final Time endTime1 = new Time(9, 0, 0, EnumPartsOfDay.AM);
        TimeSlot timeSlot1 = new TimeSlot(EnumDays.MONDAY, startTime1, endTime1);

        final Time startTime2 = new Time(11, 0, 0, EnumPartsOfDay.AM);
        final Time endTime2 = new Time(12, 0, 0, EnumPartsOfDay.AM);
        TimeSlot timeSlot2 = new TimeSlot(EnumDays.MONDAY, startTime2, endTime2);

        assertThat(timeSlot1.isBefore(timeSlot2)).isTrue();
    }

    @Test
    void isBefore3() {
        final Time startTime1 = new Time(8, 0, 0, EnumPartsOfDay.AM);
        final Time endTime1 = new Time(9, 0, 0, EnumPartsOfDay.AM);
        TimeSlot timeSlot1 = new TimeSlot(EnumDays.MONDAY, startTime1, endTime1);

        final Time startTime2 = new Time(8, 0, 0, EnumPartsOfDay.AM);
        final Time endTime2 = new Time(9, 0, 0, EnumPartsOfDay.AM);
        TimeSlot timeSlot2 = new TimeSlot(EnumDays.THURSDAY, startTime2, endTime2);

        assertThat(timeSlot1.isBefore(timeSlot2)).isTrue();
    }

    @Test
    void isNotBefore() {
        final Time startTime1 = new Time(8, 0, 0, EnumPartsOfDay.AM);
        final Time endTime1 = new Time(9, 0, 0, EnumPartsOfDay.AM);
        TimeSlot timeSlot1 = new TimeSlot(EnumDays.MONDAY, startTime1, endTime1);

        final Time startTime2 = new Time(9, 0, 0, EnumPartsOfDay.AM);
        final Time endTime2 = new Time(10, 0, 0, EnumPartsOfDay.AM);
        TimeSlot timeSlot2 = new TimeSlot(EnumDays.MONDAY, startTime2, endTime2);

        assertThat(timeSlot2.isBefore(timeSlot1)).isFalse();
    }
}
