package io.scplanner.constraints;

import io.scplanner.score.ScoreLevel;

import java.util.Set;
import java.util.function.Supplier;

public class ConstraintFactoryImpl<S, F, P> implements ConstraintFactory<S, F, P> {

    private String constraintName;
    private final Supplier<S> instantiator;
    private ConstraintFilter<F, Set<P>> filter;
    private Class<F> factClass;

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
    public ConstraintFactoryImpl<S, F, P> filter(ConstraintFilter<F, Set<P>> filter) {
        this.filter = filter;
        return this;
    }

    public Constraint<S, F, P> apply(ScoreLevel scoreLevel, PenaltyFunction<F, P> penaltyFunction, FavorableScoreFunction<P> favorableScoreFunction) {
        return new Constraint<>(this.constraintName,
                scoreLevel,
                this.instantiator.get(),
                this.factClass,
                this.filter,
                penaltyFunction,
                favorableScoreFunction
        );
    }
}
