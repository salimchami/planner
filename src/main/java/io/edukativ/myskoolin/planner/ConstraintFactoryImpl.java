package io.edukativ.myskoolin.planner;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ConstraintFactoryImpl implements ConstraintFactory {

    private Class<?> factClass;
    private Class<?> planningVariableClass;
    private final List<Function<?, ?>> filters;

    public ConstraintFactoryImpl() {
        this.filters = new ArrayList<>();
    }

    @Override
    public <A> ConstraintFactory withFact(Class<A> factClass) {
        this.factClass = factClass;
        return this;
    }

    @Override
    public <P> ConstraintFactory fromMultiple(Class<P> planningVariableClass) {
        this.planningVariableClass = planningVariableClass;
        return this;
    }

    @Override
    public <A, R> ConstraintFactory filter(Function<A, R> filter) {
        this.filters.add(filter);
        return this;
    }

    @Override
    public <A, P> Constraint<A, P> apply(String constraintName, ScoreLevel score, PenaltyFunction<A, P> penaltyFunction) {
        return new Constraint(constraintName, score, penaltyFunction, factClass, planningVariableClass);
    }
}
