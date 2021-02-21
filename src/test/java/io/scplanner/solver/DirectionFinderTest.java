package io.scplanner.solver;

import io.scplanner.constraints.Constraint;
import io.scplanner.entities.Subject;
import io.scplanner.entities.TimeTable;
import io.scplanner.entities.Timeslot;
import io.scplanner.exceptions.SolutionConfigurationException;
import io.scplanner.providers.TimeSlotsProvider;
import io.scplanner.score.ScoreLevel;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class DirectionFinderTest {

    private static Stream<Arguments> shouldSearchForCorrectEnhanceDirectionParams() {
        final Subject english = new Subject(1L, "English", 120, 60, 300, 3);
        final Subject maths = new Subject(2L, "Maths", 120, 60, 300, 3);
        Set<Timeslot> correctPlanningVariables = new HashSet<>(Arrays.asList(
                new Timeslot(1L, DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(8, 30), english),
                new Timeslot(2L, DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 0), english),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(9, 30), english),
                new Timeslot(4L, DayOfWeek.MONDAY, LocalTime.of(9, 30), LocalTime.of(10, 0), english),
                new Timeslot(5L, DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(10, 30), maths),
                new Timeslot(6L, DayOfWeek.MONDAY, LocalTime.of(10, 30), LocalTime.of(11, 0), maths),
                new Timeslot(7L, DayOfWeek.MONDAY, LocalTime.of(11, 0), LocalTime.of(11, 30), maths),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(11, 30), LocalTime.of(12, 0), maths),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(14, 0), LocalTime.of(14, 30), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(14, 30), LocalTime.of(15, 0), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(15, 0), LocalTime.of(15, 30), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(15, 30), LocalTime.of(16, 0), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(16, 0), LocalTime.of(16, 30), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(16, 30), LocalTime.of(17, 0), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(17, 0), LocalTime.of(17, 30), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(17, 30), LocalTime.of(18, 0), null)
        ));
        Set<Timeslot> emptyPlanningVariables = new HashSet<>(Arrays.asList(
                new Timeslot(1L, DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(8, 30), english),
                new Timeslot(2L, DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 0), maths),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(9, 30), null),
                new Timeslot(4L, DayOfWeek.MONDAY, LocalTime.of(9, 30), LocalTime.of(10, 0), null),
                new Timeslot(5L, DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(10, 30), null),
                new Timeslot(6L, DayOfWeek.MONDAY, LocalTime.of(10, 30), LocalTime.of(11, 0), null),
                new Timeslot(7L, DayOfWeek.MONDAY, LocalTime.of(11, 0), LocalTime.of(11, 30), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(11, 30), LocalTime.of(12, 0), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(14, 0), LocalTime.of(14, 30), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(14, 30), LocalTime.of(15, 0), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(15, 0), LocalTime.of(15, 30), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(15, 30), LocalTime.of(16, 0), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(16, 0), LocalTime.of(16, 30), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(16, 30), LocalTime.of(17, 0), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(17, 0), LocalTime.of(17, 30), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(17, 30), LocalTime.of(18, 0), null)
        ));
        Set<Timeslot> overflowPlanningVariables = new HashSet<>(Arrays.asList(
                new Timeslot(1L, DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(8, 30), english),
                new Timeslot(2L, DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 0), english),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(9, 30), english),
                new Timeslot(4L, DayOfWeek.MONDAY, LocalTime.of(9, 30), LocalTime.of(10, 0), english),
                new Timeslot(5L, DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(10, 30), english),
                new Timeslot(6L, DayOfWeek.MONDAY, LocalTime.of(10, 30), LocalTime.of(11, 0), english),
                new Timeslot(7L, DayOfWeek.MONDAY, LocalTime.of(11, 0), LocalTime.of(11, 30), english),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(11, 30), LocalTime.of(12, 0), english),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(14, 0), LocalTime.of(14, 30), english),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(14, 30), LocalTime.of(15, 0), english),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(15, 0), LocalTime.of(15, 30), maths),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(15, 30), LocalTime.of(16, 0), maths),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(16, 0), LocalTime.of(16, 30), maths),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(16, 30), LocalTime.of(17, 0), maths),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(17, 0), LocalTime.of(17, 30), maths),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(17, 30), LocalTime.of(18, 0), maths)
        ));

        return Stream.of(
//                Arguments.of("exact number of subjects", 4, DirectionFinder.SKIP, english, correctPlanningVariables),
//                Arguments.of("empty subjects", 1, DirectionFinder.ADD, english, emptyPlanningVariables),
//                Arguments.of("overflow subjects", 10, DirectionFinder.REMOVE, english, overflowPlanningVariables)
                Arguments.of("empty subjects", 0, DirectionFinder.ADD, english, TimeSlotsProvider.emptyTimeSlots())
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("shouldSearchForCorrectEnhanceDirectionParams")
    void shouldSearchForCorrectEnhanceDirection(String testName, int factsCount, DirectionFinder expectedDirection, Subject subject, Set<Timeslot> timeslots) throws SolutionConfigurationException {
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
                .filter(subject1 -> subject1.equals(subject))
                .count())
                .isEqualTo(factsCount);
    }
}
