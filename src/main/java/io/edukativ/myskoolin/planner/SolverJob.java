package io.edukativ.myskoolin.planner;

import io.edukativ.myskoolin.planner.declarations.BasePlanningVariables;
import io.edukativ.myskoolin.planner.declarations.ModifiablePlanningVariables;
import io.edukativ.myskoolin.planner.declarations.SolutionId;
import io.edukativ.myskoolin.planner.exceptions.SolutionConfigurationException;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <S> Solution
 * @param <I> Solution Id
 * @param <V> Planning variable
 */
public class SolverJob<S, V> {

    private String basePackage;
    private final S initialSolution;
    private S finalBestSolution;
    private List<V> basePlanningVariables;
    private List<V> planningVariables;
    private List<String> solutionsIds;

    public SolverJob(String basePackage, S solution) {
        this.basePackage = basePackage;
        this.initialSolution = solution;
        solutionsIds = new ArrayList<>();
    }

    public S getFinalBestSolution() {
        return finalBestSolution;
    }

    public void terminateEarly() {
        this.finalBestSolution = this.initialSolution;
    }

    public void startSolving() throws SolutionConfigurationException {

        basePlanningVariables = (List<V>) Reflection.findValueByAnnotation(initialSolution, BasePlanningVariables.class);
        planningVariables = (List<V>) Reflection.findValueByAnnotation(initialSolution, ModifiablePlanningVariables.class);
        solutionsIds.add((String) Reflection.findValueByAnnotation(initialSolution, SolutionId.class));

        this.finalBestSolution = this.initialSolution;
    }

    public boolean isSolving(String id) {
        return false;
    }
}
