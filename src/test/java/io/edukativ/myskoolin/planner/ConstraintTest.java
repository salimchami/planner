package io.edukativ.myskoolin.planner;

import io.edukativ.myskoolin.planner.entities.Subject;
import io.edukativ.myskoolin.planner.entities.Timeslot;
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

    private static Stream<Arguments> should_calculate_constraint_score_Params() {
        final Subject english = new Subject(1L, "English", 120, 60, 300, 3);
        return Stream.of(
                Arguments.of(-210, english,
                        Arrays.asList(
                                new Timeslot(1L, DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(8, 30), english),
                                new Timeslot(2L, DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 0), english),
                                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(9, 30), english),
                                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 30), LocalTime.of(10, 0), english),
                                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(10, 30), english),
                                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(10, 30), LocalTime.of(11, 0), english),
                                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(11, 0), LocalTime.of(11, 30), english)
                        )
                ),
                Arguments.of(90, english,
                        Arrays.asList(
                                new Timeslot(1L, DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(8, 30), english),
                                new Timeslot(2L, DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 0), english),
                                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(9, 30), english)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("should_calculate_constraint_score_Params")
    void should_calculate_constraint_score(int expectedScore, Subject english, List<Timeslot> planningVariables) {
        Constraint<Subject, Timeslot> constraint =
                new Constraint<>("Subject Duration By Day",
                        ScoreLevel.HARD, (Subject subject, List<Timeslot> timeslots) ->
                        subject.maxMinutesPerDayPenalty(Timeslot.totalDurationInMinutes(timeslots)),
                        (List<Timeslot> timeslots) -> Timeslot.totalDurationInMinutes(timeslots),
                        Subject.class, Timeslot.class);
        List<Subject> facts = singletonList(english);
        final int score = constraint.calculateScore(facts, planningVariables);
        assertThat(score).isEqualTo(expectedScore);
    }
}
