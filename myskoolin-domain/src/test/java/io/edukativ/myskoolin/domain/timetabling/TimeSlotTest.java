package io.edukativ.myskoolin.domain.timetabling;

import io.edukativ.myskoolin.domain.commons.vo.EnumDays;
import io.edukativ.myskoolin.domain.commons.vo.EnumPartsOfDay;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class TimeSlotTest {


    @ParameterizedTest
    @MethodSource("isOverlappingParams")
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
}
