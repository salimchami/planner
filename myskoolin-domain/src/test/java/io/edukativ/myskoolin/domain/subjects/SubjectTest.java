package io.edukativ.myskoolin.domain.subjects;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SubjectTest {

    private static Stream<Arguments> maxMinutesPerDayGapParams() {
        return Stream.of(
                Arguments.of("max: 120, duration: 60, penalty: -60", 120, 60, -60),
                Arguments.of("max: 120, duration: 120, penalty: 0", 120, 120, 0),
                Arguments.of("max: 120, duration: 0, penalty: -120", 120, 0, -120),
                Arguments.of("max: 120, duration: 50, penalty: -70", 120, 50, -70),
                Arguments.of("max: 120, duration: 180, penalty: 180", 120, 180, 180),
                Arguments.of("max: 120, duration: 240, penalty: 240", 120, 240, 240)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("maxMinutesPerDayGapParams")
    void maxMinutesPerDayGap(String description, int maxMinutesPerDay, int totalDuration, int expected) {
        Subject subject = new Subject();
        subject.setMaxMinutesPerDay(maxMinutesPerDay);
        int gap = subject.maxMinutesPerDayPenalty(totalDuration);
        assertThat(gap).isEqualTo(expected);
    }
}
