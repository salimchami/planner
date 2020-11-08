package io.edukativ.myskoolin.planner;

import io.edukativ.myskoolin.planner.declarations.BasePlanningVariables;
import io.edukativ.myskoolin.planner.exceptions.SolutionConfigurationException;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @param <S> Solution
 * @param <I> Solution Id
 * @param <V> Planning variable
 */
public class SolverJob<S, I, V> {

    private final S initialSolution;
    private S finalBestSolution;
    private List<V> basePlanningVariables;
    private List<V> planningVariables;

    public SolverJob(S solution) {
        this.initialSolution = solution;
    }

    public S getFinalBestSolution() {
        return finalBestSolution;
    }

    public void terminateEarly() {
        this.finalBestSolution = this.initialSolution;
    }

    public void startSolving() throws SolutionConfigurationException {
        final Field basePlanningVariables = ReflectionUtils.findFieldByAnnotation(initialSolution.getClass(), BasePlanningVariables.class);
        try {
            final Object o = basePlanningVariables.get(finalBestSolution);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        this.finalBestSolution = this.initialSolution;


    }

    public boolean isSolving(I id) {
        return false;
    }
}
