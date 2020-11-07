package io.edukativ.myskoolin.planner.entities;

import io.edukativ.myskoolin.planner.ScoreManager;
import io.edukativ.myskoolin.planner.SolverJob;
import io.edukativ.myskoolin.planner.SolverManager;
import io.edukativ.myskoolin.planner.SolverStatus;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class SchoolClassTimeTablesSolver implements TimeTablesSolver {

    private final SolverManager<TimeTable, String> solverManager;
    private final ScoreManager<TimeTable> scoreManager;
    private final SchoolClassSPI schoolClassSPI;
    private TimeTableSPI timeTableSPI;

    public SchoolClassTimeTablesSolver(SolverManager<TimeTable, String> solverManager,
                                       ScoreManager<TimeTable> scoreManager,
                                       SchoolClassSPI schoolClassSPI) {
        this.solverManager = solverManager;
        this.scoreManager = scoreManager;
        this.schoolClassSPI = schoolClassSPI;
    }

    @Override
    public void stopSolving(String timeTableId) {
        solverManager.terminateEarly(timeTableId);
    }

    @Override
    public void solveForSchoolClass(String schoolClassId, List<Subject> subjects) {
        Optional<SchoolClass> optSchoolClass = schoolClassSPI.findById(schoolClassId);
        optSchoolClass.ifPresent(schoolClass -> {
            final List<Timeslot> baseTimeslots = baseTimeTable();
            final List<Timeslot> timeslots = generateFirstStepTimeTable();
            final TimeTable schoolClassTimeTable = new TimeTable(baseTimeslots, timeslots, subjects);
            solveSaveAndListen(schoolClass, schoolClassTimeTable);
        });
    }

    @Override
    public SolverStatus solverStatus(String timeTableId) {
        return solverManager.getSolverStatus(timeTableId);
    }

    private List<Timeslot> baseTimeTable() {
        return null;
    }

    private List<Timeslot> generateFirstStepTimeTable() {
        return null;
    }

    private void solveSaveAndListen(SchoolClass schoolClass, TimeTable schoolClassTimeTable) {
        final SolverJob<TimeTable, String> solverJob =
                solverManager.solveAndListen(schoolClass.getName(), schoolClassTimeTable, this::saveTimeTable);
        saveTimeTable(solverJob.getFinalBestSolution());
    }

    private String saveTimeTable(TimeTable timeTable) {
        timeTable.setLastGenerationDate(Instant.now());
        System.out.println(timeTable);
        return "";
    }
}
