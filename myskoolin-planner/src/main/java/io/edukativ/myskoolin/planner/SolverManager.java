package io.edukativ.myskoolin.planner;

import io.edukativ.myskoolin.planner.declarations.PlanningSolution;
import io.edukativ.myskoolin.planner.declarations.SolutionId;
import io.edukativ.myskoolin.planner.exceptions.SolutionInitException;
import io.edukativ.myskoolin.planner.exceptions.SolutionSolvingException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.function.Function;

public class SolverManager<S, I> {

    private final Map<I, S> solutions;
    private final Map<I, SolverStatus> statuses;

    public SolverManager() {
        solutions = new HashMap<>();
        statuses = new HashMap<>();
    }

    public void terminateEarly(I timeTableId) throws SolutionSolvingException {
        if (!solutions.containsKey(timeTableId)) {
            throw new SolutionSolvingException(String.format("The solution with the id '%s' doesn't exists.", timeTableId));
        }
        solutions.remove(timeTableId);
        statuses.remove(timeTableId);
    }

    public SolverStatus getSolverStatus(I solutionId) {
        return statuses.get(solutionId);
    }

    public void solveAndListen(I id, S solution, Function<S, I> saveFunction) throws SolutionInitException, SolutionSolvingException {
        addSolution(id, solution);
        final SolverJob<S, I> solverJob = new SolverJob<>(solution);
        solverJob.startSolving();
        statuses.put(id, SolverStatus.SOLVING);
        waitForSolving(id, solverJob);
        saveFunction.apply(solverJob.getFinalBestSolution());
    }

    private void waitForSolving(I id, SolverJob<S, I> solverJob) throws SolutionSolvingException {
        CountDownLatch latch = new CountDownLatch(1);
        while (solverJob.isSolving(id)) {
            try {
                latch.await();
            } catch (InterruptedException e) {
                throw new SolutionSolvingException(String.format("Error while solving problem with the id '%s'.", id), e);
            }
        }
        latch.countDown();
    }

    private void addSolution(I id, S solution) throws SolutionInitException {
        if (!solution.getClass().isAnnotationPresent(PlanningSolution.class)) {
            throw new SolutionInitException(String.format("The solution with the id '%s' is not well configured. Please add @PlanningSolution annotation on the class.", id));
        }
        if (Arrays.stream(solution.getClass().getDeclaredFields()).noneMatch(field -> field.isAnnotationPresent(SolutionId.class))) {
            throw new SolutionInitException(String.format("The solution with the id '%s' is not well configured. Please add @SolutionId annotation on the class id.", id));
        }
        if (solutions.containsKey(id)) {
            throw new SolutionInitException(String.format("The solution with the same id '%s' is already added. Solving status : '%s'.", id, statuses.get(id)));
        }

        solutions.put(id, solution);
        statuses.put(id, SolverStatus.NOT_STARTED);
    }
}
