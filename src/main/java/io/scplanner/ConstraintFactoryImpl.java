package io.scplanner;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class ConstraintFactoryImpl<S, F, P> implements ConstraintFactory<S, F, P> {

    private String constraintName;
    private final Supplier<S> instantiator;
    private final List<Function<F, List<P>>> filters = new ArrayList<>();
    private Class<F> factClass;
    private Class<P> planningVariableClass;

    public ConstraintFactoryImpl(Supplier<S> instantiator) {
        this.instantiator = instantiator;
    }

    @Override
    public ConstraintFactory<S, F, P> name(String constraintName) {
        this.constraintName = constraintName;
        return this;
    }

    @Override
    public ConstraintFactory<S, F, P> withFact(Class<F> factClass) {
        this.factClass = factClass;
        return this;
    }

    @Override
    public ConstraintFactory<S, F, P> withPlanningVariables(Class<P> planningVariableClass) {
        this.planningVariableClass = planningVariableClass;
        return this;
    }

    @Override
    public ConstraintFactoryImpl<S, F, P> filter(Function<F, List<P>> filter) {
        filters.add(filter);
        return this;
    }

    public Constraint<S, F, P> apply(ScoreLevel scoreLevel, PenaltyFunction<F, P> penaltyFunction, FavorableScoreFunction<P> favorableScoreFunction) {
        return new Constraint<S, F, P>(this.constraintName,
                scoreLevel,
                this.instantiator.get(),
                this.factClass,
                this.planningVariableClass,
                this.filters,
                penaltyFunction,
                favorableScoreFunction
        );
    }
}
