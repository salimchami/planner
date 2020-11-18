package io.edukativ.myskoolin.planner;

public interface ConstraintContract {

    ConstraintFactory getConstraintFactory();

    String getConstraintPackage();

    String getConstraintName();

    default String getConstraintId() {
        return getConstraintPackage() + "/" + getConstraintName();
    }
}
