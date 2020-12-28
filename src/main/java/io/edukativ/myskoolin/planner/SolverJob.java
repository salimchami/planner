package io.edukativ.myskoolin.planner;

import io.edukativ.myskoolin.planner.declarations.*;
import io.edukativ.myskoolin.planner.exceptions.SolutionConfigurationException;
import io.edukativ.myskoolin.planner.reflection.Reflection;
import io.edukativ.myskoolin.planner.utils.CollectionUtils;

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
        basePlanningVariables = (List<V>) Reflection.valueByAnnotation(initialSolution, BasePlanningVariables.class);
        planningVariables = (List<V>) Reflection.valueByAnnotation(initialSolution, ModifiablePlanningVariables.class);
        solutionsIds.add((I) Reflection.valueByAnnotation(initialSolution, SolutionId.class));
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
        Reflection.copyByAnnotations(this.initialSolution, BasePlanningVariables.class, ModifiablePlanningVariables.class);
        this.finalBestSolution = this.initialSolution;
        // TODO : while score < 0
        if (score < 0) {
            improveSolution();
        }
        // TODO : set facts to planning variables in final solution
        // initializeFinalSolutionFacts();
    }

    private void initializeFinalSolutionFacts() throws SolutionConfigurationException {
        final List<V> modifiablePlanningVariables = (List<V>) Reflection.valueByAnnotation(this.finalBestSolution, ModifiablePlanningVariables.class);
        for (V modifiablePlanningVariable : modifiablePlanningVariables) {
            List<?> facts = (List<?>) Reflection.valueByAnnotation(this.finalBestSolution, Facts.class);
            final Object fact = CollectionUtils.randomSetElement(facts);
            Reflection.copyFieldByAnnotations(fact, modifiablePlanningVariable, PlanningVariableFact.class);
        }
        //Reflection.copyFieldByAnnotations(this.finalBestSolution, );
    }

    private <F, P> int solutionScore(List<P> planningVars) throws SolutionConfigurationException {
        int score = 0;
        for (Constraint<F, P> constraint : constraints) {
            score += constraint.calculateScore(planningVars);
        }
        return score;
    }

    private <F> void improveSolution() throws SolutionConfigurationException {
        final List<F> refFacts = (List<F>) Reflection.valueByAnnotation(initialSolution, Facts.class);
        constraints.forEach(constraint -> {

        });
        finalBestSolution = initialSolution;
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
