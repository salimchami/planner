package io.scplanner;

import io.scplanner.entities.Subject;
import io.scplanner.entities.Timeslot;
import io.scplanner.exceptions.SolutionConfigurationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ConstraintTest {

    private static Stream<Arguments> should_calculate_constraint_score_Params() {
        final Subject english = new Subject(1L, "English", 120, 60, 300, 3);
        return Stream.of(
                Arguments.of("total : 3h30", -210,
                        Arrays.asList(
                                new Timeslot(1L, DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(8, 30), english),
                                new Timeslot(2L, DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 0), english),
                                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(9, 30), english),
                                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 30), LocalTime.of(10, 0), english),
                                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(10, 30), english),
                                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(10, 30), LocalTime.of(11, 0), english),
                                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(11, 0), LocalTime.of(11, 30), english)
                        ), english
                ),
                Arguments.of("total : 1h30", 90,
                        Arrays.asList(
                                new Timeslot(1L, DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(8, 30), english),
                                new Timeslot(2L, DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 0), english),
                                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(9, 30), english)
                        ), english
                ),
                Arguments.of("total : 0, subjects null", -120,
                        Arrays.asList(
                                new Timeslot(1L, DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(8, 30), null),
                                new Timeslot(2L, DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 0), null),
                                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(9, 30), null)
                        ), english
                )
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("should_calculate_constraint_score_Params")
    void should_calculate_constraint_score(String title, int expectedScore, List<Timeslot> planningVariables, Subject subject) throws SolutionConfigurationException {
        Constraint<Subject, Timeslot> constraint =
                new Constraint<>("Max Subject Duration By Day",
                        ScoreLevel.HARD, Subject.class,
                        Subject::maxMinutesPerDayPenalty,
                        Timeslot::favorableScore,
                        Timeslot.class);
        final int score = constraint.calculateScore(planningVariables);
        assertThat(score).isEqualTo(expectedScore);
    }
}
