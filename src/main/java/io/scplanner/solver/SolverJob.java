package io.scplanner.solver;

import io.scplanner.annotations.BasePlanningVariables;
import io.scplanner.annotations.Facts;
import io.scplanner.annotations.ModifiablePlanningVariables;
import io.scplanner.annotations.SolutionId;
import io.scplanner.constraints.Constraint;
import io.scplanner.constraints.Constraints;
import io.scplanner.exceptions.SolutionConfigurationException;
import io.scplanner.reflection.Reflection;
import io.scplanner.score.Scores;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @param <S> Solution
 * @param <I> Solution Id
 * @param <V> Planning variable
 */
public class SolverJob<S, I, V> {

    private final SolutionEnhancer solutionEnhancer;
    private final Scores scores;

    private final S initialSolution;
    private S finalBestSolution;
    private final Set<V> basePlanningVariables;
    private Set<V> planningVariables;
    private final List<I> solutionsIds;
    private final List<Constraint> constraints;

    public SolverJob(String basePackage, S solution) throws SolutionConfigurationException {
        this.initialSolution = solution;
        solutionsIds = new ArrayList<>();
        basePlanningVariables = (Set<V>) Reflection.valueByAnnotation(initialSolution, BasePlanningVariables.class);
        planningVariables = (Set<V>) Reflection.valueByAnnotation(initialSolution, ModifiablePlanningVariables.class);
        solutionsIds.add((I) Reflection.valueByAnnotation(initialSolution, SolutionId.class));

        Constraints constraintsLoader = new Constraints();
        this.constraints = constraintsLoader.loadConstraints(basePackage, solution);
        this.solutionEnhancer = new SolutionEnhancer();
        this.scores = new Scores();
    }

    public S getFinalBestSolution() {
        return finalBestSolution;
    }

    public void solve() throws SolutionConfigurationException {
        int score = scores.solutionScore(constraints, basePlanningVariables);
        Reflection.copyByAnnotations(this.initialSolution, BasePlanningVariables.class, ModifiablePlanningVariables.class);
        this.finalBestSolution = this.initialSolution;
        if (score <= 0) {
            improveSolution();
        }
    }

    private <F> void improveSolution() throws SolutionConfigurationException {
        final List<F> refFacts = (List<F>) Reflection.valueByAnnotation(initialSolution, Facts.class);
        for (F fact : refFacts) {
            for (Constraint constraint : constraints) {
                if (constraint.getFactClass().equals(fact.getClass())) {
                    this.planningVariables = solutionEnhancer.improveByConstraint(constraint, fact, basePlanningVariables);
                    Reflection.assignFieldByAnnotations(this.planningVariables, finalBestSolution, ModifiablePlanningVariables.class);
                }
            }
        }
    }

    public boolean isSolving(I id) {
        return solutionsIds.contains(id);
    }
}
