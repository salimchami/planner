package io.edukativ.myskoolin.planner;

public class SolverJob<S, I> {

    private final S solution;
    private S finalBestSolution;

    public SolverJob(S solution) {
        this.solution = solution;
        this.finalBestSolution = solution;
    }

    public S getFinalBestSolution() {
        return finalBestSolution;
    }

    public void terminateEarly() {

    }

    public void startSolving() {


    }

    public boolean isSolving(I id) {
        return false;
    }
}
