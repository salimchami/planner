package io.edukativ.myskoolin.planner;

import io.edukativ.myskoolin.planner.declarations.PlanningVariableFact;
import io.edukativ.myskoolin.planner.exceptions.SolutionConfigurationException;
import io.edukativ.myskoolin.planner.reflection.Reflection;

import java.util.*;

public class Constraint<F, P> {

    public Constraint(String constraintName) {
        this.constraintName = constraintName;
    }

    private final String constraintName;
    private ScoreLevel scoreLevel;
    private PenaltyFunction<F, P> penaltyFunction;
    private FavorableScoreFunction<P> favorableScoreFunction;
    private Class<F> factClass;
    private Class<P> planningVariableClass;

    public Constraint(String constraintName, ScoreLevel scoreLevel, PenaltyFunction<F, P> penaltyFunction, FavorableScoreFunction<P> favorableScoreFunction,
                      Class<F> factClass, Class<P> planningVariableClass) {
        this.constraintName = constraintName;
        this.scoreLevel = scoreLevel;
        this.penaltyFunction = penaltyFunction;
        this.favorableScoreFunction = favorableScoreFunction;
        this.factClass = factClass;
        this.planningVariableClass = planningVariableClass;
    }

    public String getConstraintName() {
        return constraintName;
    }

    public ScoreLevel getScoreLevel() {
        return scoreLevel;
    }

    public PenaltyFunction<F, P> getPenaltyFunction() {
        return penaltyFunction;
    }

    public Class<F> getFactClass() {
        return factClass;
    }

    public Class<P> getPlanningVariableClass() {
        return planningVariableClass;
    }

    public int calculateScore(List<P> planningVariables) throws SolutionConfigurationException {
        Map<Object, List<P>> planningVariablesByFacts = planningVariablesByFacts(planningVariables);
        int penalty = planningVariablesByFacts.entrySet().stream()
                .mapToInt(planningVariablesByFactEntry -> penaltyFunction.apply((F) planningVariablesByFactEntry.getKey(), planningVariablesByFactEntry.getValue()))
                .sum();
        if (penalty > 0) {
            return -penalty;
        } else {
            return favorableScoreFunction.apply(planningVariables);
        }
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
