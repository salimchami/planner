package io.edukativ.myskoolin.planner;

import io.edukativ.myskoolin.planner.entities.Subject;
import io.edukativ.myskoolin.planner.entities.Timeslot;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ConstraintTest {

    @Test
    void should_calculate_constraint_score() {
        Constraint<Subject, Timeslot> constraint =
                new Constraint<>("Subject Duration By Day",
                        ScoreLevel.HARD, (Subject subject, List<Timeslot> timeslots) ->
                        subject.maxMinutesPerDayPenalty(Timeslot.totalDurationInMinutes(timeslots)),
                        Subject.class, Timeslot.class);
        final Subject english = new Subject(1L, "English", 120, 60, 300, 3);
        List<Subject> facts = singletonList(english);
        List<Timeslot> planningVariables = Arrays.asList(
                new Timeslot(1L, DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(8, 30), english),
                new Timeslot(2L, DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 0), english),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(9, 30), english),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 30), LocalTime.of(10, 0), english),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(10, 30), english),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(10, 30), LocalTime.of(11, 0), english),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(11, 0), LocalTime.of(11, 30), english)
        );
        final int score = constraint.calculateScore(facts, planningVariables);
        assertThat(score).isEqualTo(-210);
    }
}
