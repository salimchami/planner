package io.edukativ.myskoolin.planner;

public class Constraint<U, T> {

    public Constraint(String constraintName) {
        this.constraintName = constraintName;
    }

    private final String constraintName;
    private ScoreLevel score;
    private PenaltyFunction<U, T> penaltyFunction;
    private Class<U> factClass;
    private Class<T> planningVariableClass;

    public Constraint(String constraintName, ScoreLevel score, PenaltyFunction<U, T> penaltyFunction,
                      Class<U> factClass, Class<T> planningVariableClass) {
        this.constraintName = constraintName;
        this.score = score;
        this.penaltyFunction = penaltyFunction;
        this.factClass = factClass;
        this.planningVariableClass = planningVariableClass;
    }

    public String getConstraintName() {
        return constraintName;
    }

    public ScoreLevel getScore() {
        return score;
    }

    public PenaltyFunction<U, T> getPenaltyFunction() {
        return penaltyFunction;
    }

    public Class<U> getFactClass() {
        return factClass;
    }

    public Class<T> getPlanningVariableClass() {
        return planningVariableClass;
    }
}
