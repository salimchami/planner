package io.edukativ.myskoolin.planner.entities;

import io.edukativ.myskoolin.planner.SolverStatus;
import io.edukativ.myskoolin.planner.exceptions.SolutionInitException;
import io.edukativ.myskoolin.planner.exceptions.SolutionSolvingException;

import java.util.List;

public interface TimeTablesSolver {

    void stopSolving(String timeTableId) throws SolutionSolvingException;

    void solveForSchoolClass(String schoolClassId, List<Subject> subjects) throws SolutionInitException, SolutionSolvingException;

    SolverStatus solverStatus(String timeTableId);
}
