package io.scplanner.constraints;

@FunctionalInterface
public interface ConstraintFilter<F, P> {

    Boolean apply(F f, P p);
}
