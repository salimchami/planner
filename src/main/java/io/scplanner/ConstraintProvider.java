package io.scplanner;

public interface ConstraintProvider<S> {

    <F, P> Constraint<F, P>[] defineConstraints(S solution, ConstraintFactory<F, P> constraintFactory);
}
