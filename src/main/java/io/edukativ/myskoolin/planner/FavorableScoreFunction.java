package io.edukativ.myskoolin.planner;

import java.util.List;

@FunctionalInterface
public interface FavorableScoreFunction<T> {

    Integer apply(List<T> list);
}
