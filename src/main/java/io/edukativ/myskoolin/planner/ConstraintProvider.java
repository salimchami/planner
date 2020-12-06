package io.edukativ.myskoolin.planner;

public interface ConstraintProvider {

    <U, T> Constraint<U, T>[] defineConstraints(ConstraintFactory constraintFactory);
}
