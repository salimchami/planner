package io.edukativ.myskoolin.planner.utils;

import java.util.Collection;
import java.util.Random;

public final class CollectionUtils {

    private CollectionUtils() {
        // private constructor
    }

    public static <E> E randomSetElement(Collection<E> collection) {
        return collection.stream().skip(new Random().nextInt(collection.size())).findFirst().orElse(null);
    }
}
