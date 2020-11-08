package io.edukativ.myskoolin.planner;

public class SolverJob<S, I> {

    private final S initialSolution;
    private S finalBestSolution;

    public SolverJob(S solution) {
        this.initialSolution = solution;
    }

    public S getFinalBestSolution() {
        return finalBestSolution;
    }

    public void terminateEarly() {
        this.finalBestSolution = this.initialSolution;
    }

    public void startSolving() {
        this.finalBestSolution = this.initialSolution;


    }

    public boolean isSolving(I id) {
        return false;
    }
}
