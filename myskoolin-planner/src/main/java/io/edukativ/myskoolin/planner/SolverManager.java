package io.edukativ.myskoolin.planner;

import java.util.function.Function;

public class SolverManager<Solution, SolutionId> {

    private Solution solution;

    public SolverManager() {
    }

    public void terminateEarly(SolutionId timeTableId) {

    }

    public SolverStatus getSolverStatus(SolutionId solutionId) {
        return null;
    }

    public SolverJob<Solution, SolutionId> solveAndListen(SolutionId name, Solution solution, Function<Solution, SolutionId> saveFunction) {
        saveFunction.apply(solution);
        return new SolverJob<>();
    }
}
