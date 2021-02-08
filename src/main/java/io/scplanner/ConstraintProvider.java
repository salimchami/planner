package io.scplanner;

public interface ConstraintProvider<S> {

    <F, P> Constraint<S, F, P>[] defineConstraints(S solution, ConstraintFactory<S, F, P> constraintFactory);
}
