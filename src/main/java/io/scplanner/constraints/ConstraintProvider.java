package io.scplanner.constraints;

public interface ConstraintProvider<S> {

    <F, P> Constraint<S, F, P>[] defineConstraints(S timeTable, ConstraintFactory constraintFactory);
}
