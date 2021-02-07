package io.scplanner;

public interface ConstraintProvider {

    <S, U, T> Constraint<U, T>[] defineConstraints(S solution, ConstraintFactory constraintFactory);
}
