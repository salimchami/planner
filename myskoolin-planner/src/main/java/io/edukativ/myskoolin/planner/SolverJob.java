package io.edukativ.myskoolin.planner;

public class SolverJob<Solution, SolutionId> {

    private Solution finalBestSolution;

    public SolverJob(Solution solution) {
        this.finalBestSolution = solution;
    }

    public Solution getFinalBestSolution() {
        return finalBestSolution;
    }

    public void terminateEarly() {

    }
}
