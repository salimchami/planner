package io.scplanner.constraints;

import java.util.Set;

@FunctionalInterface
public interface FavorableScoreFunction<T> {

    Integer apply(Set<T> list);
}
