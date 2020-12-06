package io.edukativ.myskoolin.planner;

import io.edukativ.myskoolin.planner.exceptions.SolutionConfigurationException;
import io.edukativ.myskoolin.planner.exceptions.SolutionSolvingException;

import java.util.function.Function;

public interface IWantToManageSolver<S, V> {

    void terminateEarly(String timeTableId) throws SolutionSolvingException;

    SolverStatus getSolverStatus(String solutionId);

    void solveAndListen(String id, S solution, Function<S, String> saveFunction) throws SolutionConfigurationException, SolutionSolvingException;
}
