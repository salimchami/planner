package io.scplanner.solver;

import io.scplanner.constraints.Constraint;
import io.scplanner.entities.Subject;
import io.scplanner.entities.TimeTable;
import io.scplanner.entities.Timeslot;
import io.scplanner.exceptions.SolutionConfigurationException;
import io.scplanner.score.ScoreLevel;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class SolutionEnhancerTest {

    @Test
    void should_improve_by_constraint() throws SolutionConfigurationException {
        Set<Timeslot> baseTimeslots = new HashSet<>(Arrays.asList(
                new Timeslot(1L, DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(8, 30), null),
                new Timeslot(2L, DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 0), null),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(9, 30), null),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 30), LocalTime.of(10, 0), null),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(10, 30), null),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(10, 30), LocalTime.of(11, 0), null),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(11, 0), LocalTime.of(11, 30), null),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(11, 30), LocalTime.of(12, 0), null)
        ));
        final Subject subject = new Subject(1L, "English", 120, 60, 300, 3);
        final TimeTable timeTable = new TimeTable(baseTimeslots, singletonList(subject));
        SolutionEnhancer sut = new SolutionEnhancer();
        Constraint<TimeTable, Subject, Timeslot> constraint =
                new Constraint<>("Max Subject Duration By Day",
                        ScoreLevel.HARD,
                        timeTable,
                        Subject.class,
                        Subject::correctDuration,
                        Subject::correctDurationPerDayPenalty,
                        Timeslot::totalDurationInMinutes);
        Set<Timeslot> timeslots = sut.improveByConstraint(constraint, subject, baseTimeslots);
        assertThat(timeslots).hasSameSizeAs(baseTimeslots);
        assertThat(subject.durationOfSubject(timeslots))
                .isLessThanOrEqualTo(subject.getMaxMinutesPerDay())
                .isGreaterThanOrEqualTo(subject.getMinMinutesPerDay());
    }

}
