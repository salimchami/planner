package io.scplanner;

import io.scplanner.entities.*;
import io.scplanner.exceptions.SolutionConfigurationException;
import io.scplanner.exceptions.SolutionSolvingException;
import io.scplanner.score.ScoreManager;
import io.scplanner.solver.IWantToManageSolver;
import io.scplanner.solver.SolverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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
        IWantToManageSolver<TimeTable, String, Timeslot> solverManager = new SolverManager<>();
        sut = new SchoolClassTimeTablesSolver(solverManager, scoreManager, schoolClassSPI, timeTableSPI);
        schoolClassId = "schoolclass 1";
        SchoolClass schoolClass = new SchoolClass(schoolClassId);
        when(schoolClassSPI.findById(schoolClassId)).thenReturn(Optional.of(schoolClass));
    }

    @Test
    void shouldGenerateTimeTable() throws SolutionConfigurationException, SolutionSolvingException {
        final Subject maths = new Subject(1L, "Maths", 120, 60, 300, 3);
        final Subject english = new Subject(2L, "English", 120, 60, 300, 3);
        final List<Subject> subjects = Arrays.asList(maths, english);

        sut.solveForSchoolClass(schoolClassId, subjects);

        verify(timeTableSPI).save(timetableCaptor.capture());
        final TimeTable timeTable = timetableCaptor.getValue();

        assertThat(durationOfSubject(english, timeTable)).isLessThanOrEqualTo(120L).isGreaterThanOrEqualTo(60L);
    }

    private Long durationOfSubject(Subject francais, TimeTable timeTable) {
        return timeTable.getTimeslots().stream()
                .filter(timeslot -> {
                    return timeslot.getSubject() != null && timeslot.getSubject().equals(francais);
                })
                .mapToLong(Timeslot::durationInMinutes).sum();
    }
}
