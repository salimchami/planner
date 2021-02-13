package io.scplanner.solver;

import io.scplanner.exceptions.SolutionConfigurationException;
import io.scplanner.exceptions.SolutionSolvingException;
import io.scplanner.solver.SolverStatus;

import java.util.function.Function;

public interface IWantToManageSolver<S, I, V> {

    void terminateEarly(I timeTableId) throws SolutionSolvingException;

    SolverStatus getSolverStatus(I solutionId);

    void solveAndListen(I id, S solution, Function<S, String> saveFunction) throws SolutionConfigurationException, SolutionSolvingException;
}
