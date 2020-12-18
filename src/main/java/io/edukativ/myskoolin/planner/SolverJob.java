package io.edukativ.myskoolin.planner;

import io.edukativ.myskoolin.planner.declarations.*;
import io.edukativ.myskoolin.planner.exceptions.SolutionConfigurationException;
import io.edukativ.myskoolin.planner.reflection.Reflection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @param <S> Solution
 * @param <I> Solution Id
 * @param <V> Planning variable
 */
public class SolverJob<S, I, V> {

    private final String basePackage;
    private final S initialSolution;
    private S finalBestSolution;
    private final List<V> basePlanningVariables;
    private final List<V> planningVariables;
    private final List<I> solutionsIds;
    private final List<Constraint> constraints;

    public SolverJob(String basePackage, S solution) throws SolutionConfigurationException {
        this.basePackage = basePackage;
        this.initialSolution = solution;
        solutionsIds = new ArrayList<>();
        basePlanningVariables = (List<V>) Reflection.findValueByAnnotation(initialSolution, BasePlanningVariables.class);
        planningVariables = (List<V>) Reflection.findValueByAnnotation(initialSolution, ModifiablePlanningVariables.class);
        solutionsIds.add((I) Reflection.findValueByAnnotation(initialSolution, SolutionId.class));
        constraints = new ArrayList<>();
        loadConstraints();
    }

    public S getFinalBestSolution() {
        return finalBestSolution;
    }

    public void terminateEarly() {
        this.finalBestSolution = this.initialSolution;
    }

    public void startSolving() throws SolutionConfigurationException {
        int score = solutionScore(basePlanningVariables);
        // TODO : while score < 0
        if (score < 0) {
            improveSolution();
        }
    }

    private <F, P> int solutionScore(List<P> planningVars) throws SolutionConfigurationException {
        int score = 0;
        for (Constraint<F, P> constraint : constraints) {
            final List<F> facts = (List<F>) Reflection.findValueByAnnotation(initialSolution, Facts.class);
            score += constraint.calculateScore(facts, planningVars);
        }
        return score;
    }

    private void improveSolution() {
        constraints.forEach(constraint -> {

        });
        this.finalBestSolution = this.initialSolution;
    }

    private void loadConstraints() throws SolutionConfigurationException {
        ConstraintProvider constraintProvider = (ConstraintProvider) Reflection.instantiateClassInPackage(basePackage, ConstraintsProvider.class);
        ConstraintFactory constraintFactory = new ConstraintFactoryImpl();
        this.constraints.addAll(Arrays.asList(constraintProvider.defineConstraints(constraintFactory)));
    }

    public boolean isSolving(I id) {
        return solutionsIds.contains(id);
    }
}
