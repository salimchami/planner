package io.scplanner;

public interface ConstraintProvider {

    <U, T> Constraint<U, T>[] defineConstraints(ConstraintFactory constraintFactory);
}
