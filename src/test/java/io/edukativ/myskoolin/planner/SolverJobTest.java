package io.edukativ.myskoolin.planner;

import io.edukativ.myskoolin.planner.entities.Subject;
import io.edukativ.myskoolin.planner.entities.TimeTable;
import io.edukativ.myskoolin.planner.entities.Timeslot;
import io.edukativ.myskoolin.planner.exceptions.SolutionConfigurationException;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class SolverJobTest {

    private SolverJob<TimeTable, String, Timeslot> sut;

    @Test
    void startSolving() throws SolutionConfigurationException {
        final Subject english = new Subject(1L, "English", 120, 60, 300, 3);
        List<Timeslot> baseTimeslots = Arrays.asList(
                new Timeslot(1L, DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(8, 30), english),
                new Timeslot(2L, DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 0), english),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(9, 30), english));

        final TimeTable timeTable = new TimeTable(baseTimeslots, singletonList(english));
        sut = new SolverJob<>("io.edukativ.myskoolin.planner", timeTable);
        sut.startSolving();
        sut.terminateEarly();
        assertThat(durationOfSubject(english, sut.getFinalBestSolution())).isGreaterThan(60L);
    }

    private Long durationOfSubject(Subject francais, TimeTable timeTable) {
        return timeTable.getTimeslots().stream()
                .filter(timeslot -> timeslot.getSubject().equals(francais))
                .mapToLong(Timeslot::durationInMinutes).sum();
    }
}
