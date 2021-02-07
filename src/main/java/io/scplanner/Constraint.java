package io.scplanner;

import io.scplanner.annotations.PlanningVariableFact;
import io.scplanner.exceptions.SolutionConfigurationException;
import io.scplanner.reflection.Reflection;

import java.util.*;
import java.util.function.Supplier;

public class Constraint<F, P> {

    private final Class<F> factClass;
    private final String constraintName;
    private ScoreLevel scoreLevel;
    private PenaltyFunction<F, P> penaltyFunction;
    private FavorableScoreFunction<P> favorableScoreFunction;
    private Class<P> planningVariableClass;
    private final ConstraintFilter<F, P> filter;

    public Constraint(String constraintName,
                      ScoreLevel scoreLevel,
                      Class<F> fact,
                      Class<P> planningVariableClass,
                      ConstraintFilter<F, P> filter,
                      PenaltyFunction<F, P> penaltyFunction,
                      FavorableScoreFunction<P> favorableScoreFunction) {
        this.constraintName = constraintName;
        this.scoreLevel = scoreLevel;
        this.factClass = fact;
        this.planningVariableClass = planningVariableClass;
        this.filter = filter;
        this.penaltyFunction = penaltyFunction;
        this.favorableScoreFunction = favorableScoreFunction;
    }

    public int calculateScore(List<P> planningVariables) throws SolutionConfigurationException {
        Map<Object, List<P>> planningVariablesByFacts = planningVariablesByFacts(planningVariables);
        if (planningVariablesByFacts.isEmpty()) {
            return -penaltyFunction.apply(factClassInstanceFromSolution(), planningVariables);
        }
        int penalty = penalty(planningVariablesByFacts);
        if (penalty > 0) {
            return -penalty;
        } else {
            return favorableScoreFunction.apply(planningVariables);
        }
    }

    private F factClassInstanceFromSolution() {
        return null;
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
