package io.scplanner;

import io.scplanner.annotations.Facts;
import io.scplanner.annotations.PlanningVariableFact;
import io.scplanner.exceptions.SolutionConfigurationException;
import io.scplanner.reflection.Reflection;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class Constraint<S, F, P> {

    private final S solution;
    private final Class<F> factClass;
    private final String constraintName;
    private ScoreLevel scoreLevel;
    private PenaltyFunction<F, P> penaltyFunction;
    private FavorableScoreFunction<P> favorableScoreFunction;
    private Class<P> planningVariableClass;
    private final List<ConstraintFilter<F, List<P>>>  filters = new ArrayList<>();

    public Constraint(String constraintName,
                      ScoreLevel scoreLevel,
                      S solution,
                      Class<F> factClass,
                      Class<P> planningVariableClass,
                      List<ConstraintFilter<F, List<P>>> filters,
                      PenaltyFunction<F, P> penaltyFunction,
                      FavorableScoreFunction<P> favorableScoreFunction) {
        this.constraintName = constraintName;
        this.scoreLevel = scoreLevel;
        this.solution = solution;
        this.factClass = factClass;
        this.planningVariableClass = planningVariableClass;
        this.filters.addAll(filters);
        this.penaltyFunction = penaltyFunction;
        this.favorableScoreFunction = favorableScoreFunction;
    }

    public Constraint(String constraintName,
                      ScoreLevel scoreLevel,
                      S solution,
                      Class<F> factClass,
                      Class<P> planningVariableClass,
                      ConstraintFilter<F, List<P>> filter,
                      PenaltyFunction<F, P> penaltyFunction,
                      FavorableScoreFunction<P> favorableScoreFunction) {
        this.constraintName = constraintName;
        this.scoreLevel = scoreLevel;
        this.solution = solution;
        this.factClass = factClass;
        this.planningVariableClass = planningVariableClass;
        this.filters.add(filter);
        this.penaltyFunction = penaltyFunction;
        this.favorableScoreFunction = favorableScoreFunction;
    }

    public int calculateScore(S solution, List<P> planningVariables) throws SolutionConfigurationException {
        Map<Object, List<P>> planningVariablesByFacts = planningVariablesByFacts(planningVariables);
        if (planningVariablesByFacts.isEmpty()) {
            return factClassInstanceFromSolution(solution)
                    .map(fact -> -penaltyFunction.apply(fact, planningVariables))
                    .orElseThrow(() -> new SolutionConfigurationException("No fact class found in solution instance."));
        }
        int penalty = penalty(planningVariablesByFacts);
        if (penalty > 0) {
            return -penalty;
        } else {
            return favorableScoreFunction.apply(planningVariables);
        }
    }

    private Optional<F> factClassInstanceFromSolution(S solution) throws SolutionConfigurationException {
        final List<F> facts = (List) Reflection.valueByAnnotation(solution, Facts.class);
        return facts.stream().filter(fact -> fact.getClass().getName().equals(factClass.getName())).findFirst();
    }

    private int penalty(Map<Object, List<P>> planningVariablesByFacts) {
        return planningVariablesByFacts.entrySet().stream()
                .mapToInt(planningVariablesByFactEntry ->
                        penaltyFunction.apply((F) planningVariablesByFactEntry.getKey(), planningVariablesByFactEntry.getValue()))
                .sum();
    }

    private Map<Object, List<P>> planningVariablesByFacts(List<P> planningVariables) throws SolutionConfigurationException {
        Map<Object, List<P>> planningVariablesByFacts = new HashMap<>();
        for (P planningVariable : planningVariables) {
            final Object fact = Reflection.valueByAnnotation(planningVariable, PlanningVariableFact.class);
            if (fact != null) {
                if (planningVariablesByFacts.containsKey(fact)) {
                    planningVariablesByFacts.get(fact).add(planningVariable);
                } else {
                    planningVariablesByFacts.put(fact, new ArrayList<>(Collections.singletonList(planningVariable)));
                }
            }
        }
        return planningVariablesByFacts;
    }
}
