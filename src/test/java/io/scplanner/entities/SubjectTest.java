package io.scplanner.entities;

import io.scplanner.providers.OverflowTimeSlotsForEnglishAndFrenchTestProvider;
import io.scplanner.providers.SubjectsTestProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SubjectTest {

    private static Stream<Arguments> nameParams() {
        return Stream.of(
//                Arguments.of(120, EmptyTimeSlotsTestProvider.timeSlots()),
                Arguments.of(120, OverflowTimeSlotsForEnglishAndFrenchTestProvider.timeSlots())
//                Arguments.of(0, CorrectTimeSlotsTestProvider.timeSlots())
        );
    }

    @ParameterizedTest
    @MethodSource("nameParams")
    void name(int expectedScorePenalty, Set<Timeslot> timeSlots) {
        final int scorePenalty = SubjectsTestProvider.english.correctDurationPerDayPenalty(timeSlots);
        assertThat(scorePenalty).isEqualTo(expectedScorePenalty);
    }
}
