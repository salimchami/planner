package io.edukativ.myskoolin.planner;

import java.util.List;

public class Constraint<F, P> {

    public Constraint(String constraintName) {
        this.constraintName = constraintName;
    }

    private final String constraintName;
    private ScoreLevel scoreLevel;
    private PenaltyFunction<F, P> penaltyFunction;
    private FavorableScoreFunction<P> favorableScore;
    private Class<F> factClass;
    private Class<P> planningVariableClass;

    public Constraint(String constraintName, ScoreLevel scoreLevel, PenaltyFunction<F, P> penaltyFunction, FavorableScoreFunction<P> favorableScoreFunction,
                      Class<F> factClass, Class<P> planningVariableClass) {
        this.constraintName = constraintName;
        this.scoreLevel = scoreLevel;
        this.penaltyFunction = penaltyFunction;
        this.favorableScore = favorableScoreFunction;
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

    public int calculateScore(List<F> facts, List<P> planningVariables) {
        final int total = facts.stream().mapToInt(fact -> penaltyFunction.apply(fact, planningVariables)).sum();
        if (total > 0) {
            return -total;
        } else {
            return favorableScore.apply(planningVariables);
        }
    }
}
