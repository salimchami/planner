package io.edukativ.myskoolin.domain.timetabling;

import io.edukativ.myskoolin.domain.schoolclasses.SchoolClassSPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.optaplanner.core.api.score.ScoreManager;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.api.solver.SolverManager;
import org.optaplanner.core.config.solver.SolverConfig;
import org.optaplanner.core.config.solver.SolverManagerConfig;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class TimeTablesGenerationOptaplannerTest {
    private TimeTablesGeneration sut;

    private ScoreManager<SchoolClassTimeTable> scoreManager;
    private SolverManager<SchoolClassTimeTable, String> solverManager;

    @BeforeEach
    void setUp() {
        SolverConfig solverConfig = SolverConfig.createFromXmlResource("solverConfig.xml");
        solverManager = SolverManager.create(solverConfig, new SolverManagerConfig());
        scoreManager = ScoreManager.create(SolverFactory.create(solverConfig));
        SchoolClassSPI schoolClassSPI = mock(SchoolClassSPI.class);
        TimeTableSPI timeTableSPI = mock(TimeTableSPI.class);

        sut = new TimeTablesGeneration(solverManager, scoreManager, schoolClassSPI, timeTableSPI);
    }

    @Test
    @Disabled
    void name() {
        assertThat(solverManager).isNotNull();
    }
}
