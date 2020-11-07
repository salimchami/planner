package io.edukativ.myskoolin.planner;

import io.edukativ.myskoolin.planner.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SolverTest {

    private TimeTablesSolver sut;
    private String schoolClassId;

    @BeforeEach
    void setUp() {
        SchoolClassSPI schoolClassSPI = mock(SchoolClassSPI.class);
        ScoreManager<TimeTable> scoreManager = new ScoreManager<>();
        SolverManager<TimeTable, String> solverManager = new SolverManager<>();
        sut = new SchoolClassTimeTablesSolver(solverManager, scoreManager, schoolClassSPI);
        schoolClassId = "Sixième";
        when(schoolClassSPI.findById(schoolClassId)).thenReturn(Optional.of(new SchoolClass(schoolClassId)));
    }

    @Test
    void name() {
        schoolClassId = "Sixième";
        sut.solveForSchoolClass(schoolClassId, Arrays.asList(
                new Subject(1L, "Mathematiques", 120, 60, 300, 3),
                new Subject(1L, "Français", 120, 60, 300, 3)
        ));
    }
}
