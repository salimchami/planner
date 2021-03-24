package io.scplanner.solver.direction;

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

import java.util.Set;
import java.util.stream.Stream;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class DirectionFinderPartsTest {

    private static Stream<Arguments> shouldCalculateBetterScoreFromPartsParams() {
        return Stream.of(
                Arguments.of(0, DirectionFinder.ADD, SubjectsTestProvider.english, CorrectTimeSlotsTestProvider.timeSlots())
        );
    }

    @ParameterizedTest
    @MethodSource("shouldCalculateBetterScoreFromPartsParams")
    void shouldCalculateBetterScoreFromParts(BetterDirectionPlanningVariable<Timeslot> expectedPV,
                                             DirectionFinder direction,
                                             Subject subject,
                                             Set<Timeslot> timeslots) throws SolutionConfigurationException {
        final TimeTable timeTable = new TimeTable(timeslots, singletonList(subject));
        final Constraint<TimeTable, Subject, Timeslot> constraint = new Constraint<>("Max Subject Duration, total and by Day",
                ScoreLevel.HARD,
                timeTable,
                Subject.class,
                Subject::correctDuration,
                Subject::correctDurationPenalty,
                Timeslot::totalDurationInMinutes
        );

        final BetterDirectionPlanningVariable<Timeslot> betterPV = new DirectionFinderParts<>(direction, constraint, subject, timeslots).betterPlanningVariableFromParts();
        assertThat(betterPV).isEqualTo(expectedPV);
    }
}
