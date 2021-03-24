package io.scplanner.solver;

import io.scplanner.constraints.Constraint;
import io.scplanner.entities.Subject;
import io.scplanner.entities.TimeTable;
import io.scplanner.entities.Timeslot;
import io.scplanner.exceptions.SolutionConfigurationException;
import io.scplanner.providers.CorrectTimeSlotsTestProvider;
import io.scplanner.providers.EmptyTimeSlotsTestProvider;
import io.scplanner.providers.OverflowTimeSlotsForEnglishAndFrenchTestProvider;
import io.scplanner.providers.SubjectsTestProvider;
import io.scplanner.score.ScoreLevel;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class SolutionEnhancerTest {

    private SolutionEnhancer sut;

    @BeforeEach
    void setUp() {
        sut = new SolutionEnhancer();
    }

    private static Stream<Arguments> should_improve_by_constraint_params() {
        return Stream.of(
                Arguments.of("exact number of subjects", SubjectsTestProvider.english, CorrectTimeSlotsTestProvider.timeSlots()),
                Arguments.of("empty subjects", SubjectsTestProvider.english, EmptyTimeSlotsTestProvider.timeSlots()),
                Arguments.of("overflow subjects", SubjectsTestProvider.english, OverflowTimeSlotsForEnglishAndFrenchTestProvider.timeSlots())
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("should_improve_by_constraint_params")
    void should_improve_by_constraint(String testName, Subject subject, Set<Timeslot> baseTimeslots) throws SolutionConfigurationException {
        final TimeTable timeTable = new TimeTable(baseTimeslots, singletonList(subject));
        Constraint<TimeTable, Subject, Timeslot> constraint =
                new Constraint<>("Max Subject Duration By Day",
                        ScoreLevel.HARD,
                        timeTable,
                        Subject.class,
                        Subject::correctDuration,
                        Subject::correctDurationPenalty,
                        Timeslot::totalDurationInMinutes);
        Set<Timeslot> timeslots = sut.improveByConstraint(constraint, subject, baseTimeslots);
        assertThat(timeslots).hasSameSizeAs(baseTimeslots);
        assertThat(subject.durationOfSubject(timeslots))
                .isCloseTo(subject.getMinutesPerWeek(), Percentage.withPercentage(2));
    }

}
