package io.scplanner.solver;

import io.scplanner.constraints.Constraint;
import io.scplanner.entities.Subject;
import io.scplanner.entities.TimeTable;
import io.scplanner.entities.Timeslot;
import io.scplanner.exceptions.SolutionConfigurationException;
import io.scplanner.score.ScoreLevel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SolutionEnhancerTest {

    @Disabled
    @Test
    void should_improve_by_constraint() throws SolutionConfigurationException {
        List<Timeslot> baseTimeslots = Arrays.asList(
                new Timeslot(1L, DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(8, 30), null),
                new Timeslot(2L, DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 0), null),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(9, 30), null),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 30), LocalTime.of(10, 0), null),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(10, 30), null),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(10, 30), LocalTime.of(11, 0), null),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(11, 0), LocalTime.of(11, 30), null),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(11, 30), LocalTime.of(12, 0), null)
        );
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
        List<Timeslot> timeslots = sut.improveByConstraint(constraint, subject, baseTimeslots);
        assertThat(subject.durationOfSubject(timeslots))
                .isLessThan(subject.getMaxMinutesPerDay())
                .isGreaterThan(subject.getMinMinutesPerDay());
    }

}
