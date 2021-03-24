package io.scplanner.entities;

import io.scplanner.providers.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SubjectTest {

    @ParameterizedTest
    @MethodSource("shouldFindCorrectDurationPenaltyParams")
    void shouldFindCorrectDurationPenalty(int expectedScorePenalty, Set<Timeslot> timeSlots) {
        final int scorePenalty = SubjectsTestProvider.english.correctDurationPenalty(timeSlots);
        assertThat(scorePenalty).isEqualTo(expectedScorePenalty);
    }

    private static Stream<Arguments> shouldFindCorrectDurationPenaltyParams() {
        return Stream.of(
                Arguments.of(270, EmptyTimeSlotsTestProvider.timeSlots()),
                Arguments.of(0, CorrectTimeSlotsTestProvider.timeSlots()),
                Arguments.of(210, CorrectTotalButNotByDayTimeSlotsTestProvider.timeSlots()),
                Arguments.of(780, OverflowTimeSlotsForEnglishAndFrenchTestProvider.timeSlots())
        );
    }

    @ParameterizedTest
    @MethodSource("shouldCheckIfDurationIsCorrectParams")
    void shouldCheckIfDurationIsCorrect(boolean isCorrect, Set<Timeslot> timeSlots) {
        final boolean correctDuration = SubjectsTestProvider.english.correctDuration(timeSlots);
        assertThat(correctDuration).isEqualTo(isCorrect);
    }

    private static Stream<Arguments> shouldCheckIfDurationIsCorrectParams() {
        return Stream.of(
                Arguments.of(false, EmptyTimeSlotsTestProvider.timeSlots()),
                Arguments.of(true, CorrectTimeSlotsTestProvider.timeSlots()),
                Arguments.of(false, OverflowTimeSlotsForEnglishAndFrenchTestProvider.timeSlots())
        );
    }
}
