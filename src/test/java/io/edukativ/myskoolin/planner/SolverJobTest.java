package io.edukativ.myskoolin.planner;

import io.edukativ.myskoolin.planner.entities.Subject;
import io.edukativ.myskoolin.planner.entities.TimeTable;
import io.edukativ.myskoolin.planner.entities.Timeslot;
import io.edukativ.myskoolin.planner.exceptions.SolutionConfigurationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SolverJobTest {

    private SolverJob<TimeTable, String, Timeslot> sut;

    @Test
    void startSolving() throws SolutionConfigurationException {
        final Subject francais = new Subject(1L, "Français", 120, 60, 300, 3);
        List<Timeslot> baseTimeslots = Arrays.asList(
                new Timeslot(1L, DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(8, 30), francais),
                new Timeslot(2L, DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 0), francais),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(9, 30), francais));

        final TimeTable timeTable = new TimeTable(baseTimeslots, singletonList(francais));
        sut = new SolverJob<>("io.edukativ.myskoolin.planner", timeTable);
        sut.startSolving();
        assertThat(durationOfSubject(francais, sut.getFinalBestSolution())).isGreaterThan(60L);
    }

    private Long durationOfSubject(Subject francais, TimeTable timeTable) {
        return timeTable.getTimeslots().stream()
                .filter(timeslot -> timeslot.getSubject().equals(francais))
                .mapToLong(Timeslot::durationInMinutes).sum();
    }
}
