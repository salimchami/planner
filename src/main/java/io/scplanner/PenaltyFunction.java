package io.scplanner;

import java.util.List;

@FunctionalInterface
public interface PenaltyFunction<F, P> {

    Integer apply(F u, List<P> list);
}
