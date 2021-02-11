package io.scplanner;

import java.util.List;
import java.util.function.Function;


public interface  ConstraintFilter<F, P> extends Function<F, P> {

    boolean apply(F f, List<P> list);
}
