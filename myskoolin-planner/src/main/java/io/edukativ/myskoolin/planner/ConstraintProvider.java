package io.edukativ.myskoolin.planner;

public interface ConstraintProvider {

    ConstraintContract[] defineConstraints(ConstraintFactory constraintFactory);
}
