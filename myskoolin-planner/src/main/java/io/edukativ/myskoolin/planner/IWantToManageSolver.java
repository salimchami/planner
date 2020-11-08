package io.edukativ.myskoolin.planner;

import io.edukativ.myskoolin.planner.exceptions.SolutionConfigurationException;
import io.edukativ.myskoolin.planner.exceptions.SolutionSolvingException;

import java.util.function.Function;

public interface IWantToManageSolver<S, I> {

    void terminateEarly(I timeTableId) throws SolutionSolvingException;

    SolverStatus getSolverStatus(I solutionId);

    void solveAndListen(I id, S solution, Function<S, I> saveFunction) throws SolutionConfigurationException, SolutionSolvingException;
}
