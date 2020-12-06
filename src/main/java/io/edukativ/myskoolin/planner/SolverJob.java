package io.edukativ.myskoolin.planner;

import io.edukativ.myskoolin.planner.declarations.BasePlanningVariables;
import io.edukativ.myskoolin.planner.declarations.ConstraintsProvider;
import io.edukativ.myskoolin.planner.declarations.ModifiablePlanningVariables;
import io.edukativ.myskoolin.planner.declarations.SolutionId;
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
    private final List<Constraint<?, ?>> constraints;

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
