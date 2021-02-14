package io.scplanner.solver;

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
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class EnhanceDirectionTest {

    private static Stream<Arguments> shouldSearchForCorrectEnhanceDirectionParams() {
        final Subject english = new Subject(1L, "English", 120, 60, 300, 3);
        Set<Timeslot> planningVariables1 = new HashSet<>(Arrays.asList(
                new Timeslot(1L, DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(8, 30), english),
                new Timeslot(2L, DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 0), english),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(9, 30), english)
        ));
        Set<Timeslot> planningVariables2 = new HashSet<>(Arrays.asList(
                new Timeslot(1L, DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(8, 30), null),
                new Timeslot(2L, DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 0), null),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(9, 30), null),
                new Timeslot(4L, DayOfWeek.MONDAY, LocalTime.of(9, 30), LocalTime.of(10, 0), null),
                new Timeslot(5L, DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(10, 30), null),
                new Timeslot(6L, DayOfWeek.MONDAY, LocalTime.of(10, 30), LocalTime.of(11, 0), null),
                new Timeslot(7L, DayOfWeek.MONDAY, LocalTime.of(11, 0), LocalTime.of(11, 30), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(11, 30), LocalTime.of(12, 0), null)
        ));
        Set<Timeslot> planningVariables3 = new HashSet<>(Arrays.asList(
                new Timeslot(1L, DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(8, 30), english),
                new Timeslot(2L, DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 0), english),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(9, 30), english),
                new Timeslot(4L, DayOfWeek.MONDAY, LocalTime.of(9, 30), LocalTime.of(10, 0), english),
                new Timeslot(5L, DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(10, 30), english),
                new Timeslot(6L, DayOfWeek.MONDAY, LocalTime.of(10, 30), LocalTime.of(11, 0), english),
                new Timeslot(7L, DayOfWeek.MONDAY, LocalTime.of(11, 0), LocalTime.of(11, 30), english),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(11, 30), LocalTime.of(12, 0), english)
        ));

        return Stream.of(
//                Arguments.of("exact number of subjects", EnhanceDirection.SKIP, english, planningVariables1),
                Arguments.of("empty subjects", EnhanceDirection.ADD, english, planningVariables2)
//                Arguments.of("overflow subjects", EnhanceDirection.REMOVE, english, planningVariables3)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("shouldSearchForCorrectEnhanceDirectionParams")
    void shouldSearchForCorrectEnhanceDirection(String testName, EnhanceDirection expectedDirection, Subject subject, Set<Timeslot> timeslots) throws SolutionConfigurationException {
        final TimeTable timeTable = new TimeTable(timeslots, singletonList(subject));
        final Constraint<TimeTable, Subject, Timeslot> constraint = new Constraint<>("Max Subject Duration By Day",
                ScoreLevel.HARD,
                timeTable,
                Subject.class,
                Subject::correctDuration,
                Subject::correctDurationPerDayPenalty,
                Timeslot::totalDurationInMinutes);
        final EnhanceDirection direction = EnhanceDirection.of(constraint, subject, timeslots);
        assertThat(direction).isEqualTo(expectedDirection);
    }
}
