package io.scplanner;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ConstraintFactoryImpl implements ConstraintFactory {

    private String constraintName;
    private Class<?> factClass;
    private Class<?> planningVariableClass;
    private final List<Function<?, ?>> filters;
    private UnaryOperator<?> favorableScore;

    public ConstraintFactoryImpl() {
        this.filters = new ArrayList<>();
    }


    @Override
    public ConstraintFactory name(String constraintName) {
        this.constraintName = constraintName;
        return this;
    }

    @Override
    public <F> ConstraintFactory withFact(Class<F> factClass) {
        this.factClass = factClass;
        return this;
    }

    @Override
    public <P> ConstraintFactory fromMultiple(Class<P> planningVariableClass) {
        this.planningVariableClass = planningVariableClass;
        return this;
    }

    @Override
    public <F, P> ConstraintFactory filter(Function<F, P> filter) {
        this.filters.add(filter);
        return this;
    }

    @Override
    public <F, P> Constraint<F, P> apply(ScoreLevel score, PenaltyFunction<F, P> penaltyFunction, FavorableScoreFunction<P> favorableScoreFunction) {
        return new Constraint(constraintName, score, factClass, penaltyFunction, favorableScoreFunction, planningVariableClass);
    }
}
