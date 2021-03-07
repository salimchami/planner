package io.scplanner.solver;

import io.scplanner.constraints.Constraint;
import io.scplanner.entities.Subject;
import io.scplanner.entities.TimeTable;
import io.scplanner.entities.Timeslot;
import io.scplanner.exceptions.SolutionConfigurationException;
import io.scplanner.providers.CorrectTimeSlotsTestProvider;
import io.scplanner.providers.SubjectsTestProvider;
import io.scplanner.score.ScoreLevel;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class DirectionFinderTest {

    private static Stream<Arguments> shouldSearchForCorrectEnhanceDirectionParams() {
        return Stream.of(
                Arguments.of("exact number of subjects", 9, DirectionFinder.SKIP, SubjectsTestProvider.english, CorrectTimeSlotsTestProvider.timeSlots())
//                Arguments.of("empty subjects", 0, DirectionFinder.ADD, SubjectsTestProvider.english, EmptyTimeSlotsTestProvider.timeSlots()),
//                Arguments.of("overflow subjects", 26, DirectionFinder.REMOVE, SubjectsTestProvider.english, OverflowTimeSlotsForEnglishAndFrenchTestProvider.timeSlots())
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("shouldSearchForCorrectEnhanceDirectionParams")
    void shouldSearchForCorrectEnhanceDirection(String testName,
                                                int factsCount,
                                                DirectionFinder expectedDirection,
                                                Subject subject,
                                                Set<Timeslot> timeslots) throws SolutionConfigurationException {
        final TimeTable timeTable = new TimeTable(timeslots, singletonList(subject));
        final Constraint<TimeTable, Subject, Timeslot> constraint = new Constraint<>("Max Subject Duration By Day",
                ScoreLevel.HARD,
                timeTable,
                Subject.class,
                Subject::correctDuration,
                Subject::correctDurationPerDayPenalty,
                Timeslot::totalDurationInMinutes);
        final DirectionFinder direction = DirectionFinder.of(
                constraint,
                timeslots,
                subject
        );
        assertThat(direction).isEqualTo(expectedDirection);
        assertThat(timeslots.stream()
                .map(Timeslot::getSubject)
                .filter(Objects::nonNull)
                .filter(s -> s.equals(subject))
                .count())
                .isEqualTo(factsCount);
    }
}
