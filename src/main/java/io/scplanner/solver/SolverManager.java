package io.scplanner.solver;

import io.scplanner.configuration.SolutionConfiguration;
import io.scplanner.exceptions.SolutionConfigurationException;
import io.scplanner.exceptions.SolutionSolvingException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.function.Function;

import static java.lang.StackWalker.Option.RETAIN_CLASS_REFERENCE;

public class SolverManager<S, I, V> implements IWantToManageSolver<S, I, V> {

    private final Map<I, S> solutions;
    private final Map<I, SolverStatus> statuses;

    public SolverManager() {
        solutions = new HashMap<>();
        statuses = new HashMap<>();
    }

    @Override
    public void terminateEarly(I timeTableId) throws SolutionSolvingException {
        if (!solutions.containsKey(timeTableId)) {
            throw new SolutionSolvingException(String.format("The solution with the id '%s' doesn't exists.", timeTableId));
        }
        solutions.remove(timeTableId);
        statuses.remove(timeTableId);
    }

    @Override
    public SolverStatus getSolverStatus(I solutionId) {
        return statuses.get(solutionId);
    }

    @Override
    public void solveAndListen(I id, S solution, Function<S, String> saveFunction) throws SolutionConfigurationException, SolutionSolvingException {
        addSolution(id, solution);
        final String basePackage = StackWalker.getInstance(RETAIN_CLASS_REFERENCE).getCallerClass().getPackageName();
        final SolverJob<S, I, V> solverJob = new SolverJob<>(basePackage, solution);
        solverJob.solve();
        statuses.put(id, SolverStatus.SOLVING);
        waitForSolving(id, solverJob);
        saveFunction.apply(solverJob.getFinalBestSolution());
    }

    // #####################################################

    private void waitForSolving(I id, SolverJob<S, I, V> solverJob) throws SolutionSolvingException {
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

    private void addSolution(I id, S solution) throws SolutionConfigurationException {
        checkIfSolutionExists(id);
        new SolutionConfiguration<>(solution).check();

        solutions.put(id, solution);
        statuses.put(id, SolverStatus.NOT_STARTED);
    }

    private void checkIfSolutionExists(I id) throws SolutionConfigurationException {
        if (solutions.containsKey(id)) {
            throw new SolutionConfigurationException(String.format("The solution with the same id '%s' is already added. Solving status : '%s'.", id, statuses.get(id)));
        }
    }
}
