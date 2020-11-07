package io.edukativ.myskoolin.planner;

import io.edukativ.myskoolin.planner.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.Mockito.*;

class SolverTest {

    private TimeTablesSolver sut;
    private String schoolClassId;
    private TimeTableSPI timeTableSPI;
    @Captor
    ArgumentCaptor<TimeTable> timetableCaptor = ArgumentCaptor.forClass(TimeTable.class);

    @BeforeEach
    void setUp() {
        SchoolClassSPI schoolClassSPI = mock(SchoolClassSPI.class);
        timeTableSPI = mock(TimeTableSPI.class);
        ScoreManager<TimeTable> scoreManager = new ScoreManager<>();
        SolverManager<TimeTable, String> solverManager = new SolverManager<>();
        sut = new SchoolClassTimeTablesSolver(solverManager, scoreManager, schoolClassSPI, timeTableSPI);
        schoolClassId = "Sixième";
        SchoolClass schoolClass = new SchoolClass(schoolClassId);
        when(schoolClassSPI.findById(schoolClassId)).thenReturn(Optional.of(schoolClass));
    }

    @Test
    void shouldGenerateTimeTable() {
        schoolClassId = "Sixième";
        final Subject mathematiques = new Subject(1L, "Mathematiques", 120, 60, 300, 3);
        final Subject francais = new Subject(1L, "Français", 120, 60, 300, 3);
        final List<Subject> subjects = Arrays.asList(mathematiques, francais);
        sut.solveForSchoolClass(schoolClassId, subjects);
        verify(timeTableSPI, times(2)).save(timetableCaptor.capture());
        final TimeTable timeTable = timetableCaptor.getValue();
        List<Timeslot> francaisTimeSlots = timeTable.getTimeslots().stream().filter(timeslot -> {
            return timeslot.getSubject().equals(francais);
        }).collect(Collectors.toList());
        List<Timeslot> mathsTimeSlots = timeTable.getTimeslots().stream().filter(timeslot -> {
            return timeslot.getSubject().equals(mathematiques);
        }).collect(Collectors.toList());
        assertThat(francaisTimeSlots.stream().mapToLong(Timeslot::durationInMinutes).sum()).isCloseTo(120L, within(30L));
        assertThat(mathsTimeSlots.stream().mapToLong(Timeslot::durationInMinutes).sum()).isCloseTo(120L, within(30L));
    }
}
