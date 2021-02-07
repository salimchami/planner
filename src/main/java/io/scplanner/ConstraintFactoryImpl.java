package io.scplanner;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ConstraintFactoryImpl<F, P> implements ConstraintFactory<F, P> {

    private String constraintName;
    private Class<?> factClass;
    private Class<?> planningVariableClass;
    private ConstraintFilter<F, List<P>> filter;
    private Function<F, List<P>> listFilter;

    @Override
    public ConstraintFactory name(String constraintName) {
        this.constraintName = constraintName;
        return this;
    }

    @Override
    public ConstraintFactory<F, P> withFact(Class<F> factClass) {
        this.factClass = factClass;
        return this;
    }

    @Override
    public ConstraintFactory<F, P> fromMultiple(Class<P> planningVariableClass) {
        this.planningVariableClass = planningVariableClass;
        return this;
    }

    @Override
    public ConstraintFactory<F, P> filter(ConstraintFilter<F, List<P>> filter) {
        this.filter = filter;
        return this;
    }

    @Override
    public Constraint<F, P> apply(ScoreLevel score, PenaltyFunction<F, P> penaltyFunction, FavorableScoreFunction<P> favorableScoreFunction) {
//        return new Constraint(this.constraintName,
//                score,
//                this.factClass,
//                this.planningVariableClass,
//                this.filter,
//                this.listFilter,
//                penaltyFunction,
//                favorableScoreFunction);
        return null;
    }
}
