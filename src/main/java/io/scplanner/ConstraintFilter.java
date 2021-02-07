package io.scplanner;

import java.util.List;

public interface ConstraintFilter<F, P> {

    boolean apply(F f, List<P> list);
}
