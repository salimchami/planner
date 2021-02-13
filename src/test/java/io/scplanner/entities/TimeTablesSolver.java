package io.scplanner.entities;

import io.scplanner.solver.SolverStatus;
import io.scplanner.exceptions.SolutionConfigurationException;
import io.scplanner.exceptions.SolutionSolvingException;

import java.util.List;

public interface TimeTablesSolver {

    void stopSolving(String timeTableId) throws SolutionSolvingException;

    void solveForSchoolClass(String schoolClassId, List<Subject> subjects) throws SolutionConfigurationException, SolutionSolvingException;

    SolverStatus solverStatus(String timeTableId);
}
