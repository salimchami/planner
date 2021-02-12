package io.scplanner;

import java.util.List;

@FunctionalInterface
public interface PenaltyFunction<U, T> {

    Integer apply(U u, List<T> list);
}
