package io.scplanner.entities;

import io.scplanner.providers.CorrectTimeSlotsTestProvider;
import io.scplanner.providers.EmptyTimeSlotsTestProvider;
import io.scplanner.providers.OverflowTimeSlotsForEnglishAndFrenchTestProvider;
import io.scplanner.providers.SubjectsTestProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SubjectTest {

    @ParameterizedTest
    @MethodSource("shouldFindCorrectDurationPerDayPenaltyParams")
    void shouldFindCorrectDurationPerDayPenalty(int expectedScorePenalty, Set<Timeslot> timeSlots) {
        final int scorePenalty = SubjectsTestProvider.english.correctDurationPerDayPenalty(timeSlots);
        assertThat(scorePenalty).isEqualTo(expectedScorePenalty);
    }

    private static Stream<Arguments> shouldFindCorrectDurationPerDayPenaltyParams() {
        return Stream.of(
                Arguments.of(120, EmptyTimeSlotsTestProvider.timeSlots()),
                Arguments.of(0, CorrectTimeSlotsTestProvider.timeSlots()),
                Arguments.of(300, OverflowTimeSlotsForEnglishAndFrenchTestProvider.timeSlots())
        );
    }

    @ParameterizedTest
    @MethodSource("shouldFindCorrectDurationPenaltyParams")
    void shouldFindCorrectDurationPenalty(boolean expectedScorePenalty, Set<Timeslot> timeSlots) {
        final boolean correctDuration = SubjectsTestProvider.english.correctDuration(timeSlots);
        assertThat(correctDuration).isEqualTo(expectedScorePenalty);
    }

    private static Stream<Arguments> shouldFindCorrectDurationPenaltyParams() {
        return Stream.of(
                Arguments.of(false, EmptyTimeSlotsTestProvider.timeSlots()),
                Arguments.of(true, CorrectTimeSlotsTestProvider.timeSlots()),
                Arguments.of(false, OverflowTimeSlotsForEnglishAndFrenchTestProvider.timeSlots())
        );
    }
}
