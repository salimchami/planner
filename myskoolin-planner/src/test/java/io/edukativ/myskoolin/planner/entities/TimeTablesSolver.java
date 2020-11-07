package io.edukativ.myskoolin.planner.entities;

import io.edukativ.myskoolin.planner.SolverStatus;

import java.util.List;

public interface TimeTablesSolver {
    void stopSolving(String timeTableId);

    void solveForSchoolClass(String schoolClassId, List<Subject> subjects);

    SolverStatus solverStatus(String timeTableId);
}
