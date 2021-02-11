package io.scplanner;

import java.util.List;
import java.util.function.Function;

@FunctionalInterface
public interface ConstraintFilter<F, P> {

    Boolean apply(F f, P p);
}
