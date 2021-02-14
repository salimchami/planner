package io.scplanner.constraints;

import java.util.Set;

@FunctionalInterface
public interface PenaltyFunction<F, P> {

    Integer apply(F u, Set<P> list);
}
