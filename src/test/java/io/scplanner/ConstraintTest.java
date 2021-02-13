package io.scplanner;

import io.scplanner.constraints.Constraint;
import io.scplanner.entities.Subject;
import io.scplanner.entities.TimeTable;
import io.scplanner.entities.Timeslot;
import io.scplanner.exceptions.SolutionConfigurationException;
import io.scplanner.score.ScoreLevel;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ConstraintTest {

    private static Stream<Arguments> should_calculate_constraint_score_with_subject_filter_Params() {
        final Subject english = new Subject(1L, "English", 120, 60, 300, 3);
        List<Timeslot> planningVariables1 = Arrays.asList(
                new Timeslot(1L, DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(8, 30), english),
                new Timeslot(2L, DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 0), english),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(9, 30), english),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 30), LocalTime.of(10, 0), english),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(10, 30), english),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(10, 30), LocalTime.of(11, 0), english),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(11, 0), LocalTime.of(11, 30), english)
        );
        List<Timeslot> planningVariables2 = Arrays.asList(
                new Timeslot(1L, DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(8, 30), english),
                new Timeslot(2L, DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 0), english),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(9, 30), english)
        );
        List<Timeslot> planningVariables3 = Arrays.asList(
                new Timeslot(1L, DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(8, 30), null),
                new Timeslot(2L, DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 0), null),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(9, 30), null)
        );
        return Stream.of(
                Arguments.of("total : 3h30", -210, new TimeTable(planningVariables1, singletonList(english))),
                Arguments.of("total : 1h30", 90, new TimeTable(planningVariables2, singletonList(english))),
                Arguments.of("total : 0, subjects null", -120, new TimeTable(planningVariables3, singletonList(english)))
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("should_calculate_constraint_score_with_subject_filter_Params")
    void should_calculate_constraint_score_with_subject_max_filter(String title,
                                           int expectedScore,
                                           TimeTable timeTable) throws SolutionConfigurationException {
        Constraint<TimeTable, Subject, Timeslot> constraint =
                new Constraint<>("Max Subject Duration By Day",
                        ScoreLevel.HARD,
                        timeTable,
                        Subject.class,
                        Subject::correctDuration,
                        Subject::correctDurationPerDayPenalty,
                        Timeslot::totalDurationInMinutes);
        final int score = constraint.calculateScore(timeTable.getBaseTimeslots());
        assertThat(score).isEqualTo(expectedScore);
    }
}
