package io.scplanner.utils;

import io.scplanner.exceptions.SolutionConfigurationException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public final class CollectionUtils {

    private CollectionUtils() {
        // private constructor
    }

    /**
     * Set cloner
     * <p>The Set objects to clone must contain newInstance method :
     * <pre>
     * public TheObject newInstance() {
     *   return new TheObject(this.arg1, this.arg2...);
     * }
     * </pre>
     * </p>
     *
     * @param set The Set to clone
     * @return cloned Set.
     * @throws SolutionConfigurationException if the Set objects to clone doesn't contains newInstance method
     * @see ObjectUtils
     */
    public static <T> Set<T> copySet(Set<T> set) throws SolutionConfigurationException {
        Set<T> result = new HashSet<>();
        for (T t : set) {
            T newInstance = ObjectUtils.newInstance(t);
            result.add(newInstance);
        }
        return result;
    }

    public static <T> List<List<T>> batches(List<T> source, int length) {
        if (length <= 0)
            throw new IllegalArgumentException("length = " + length);
        int size = source.size();
        if (size <= 0)
            return emptyList();
        int fullChunks = (size - 1) / length;
        return IntStream
                .range(0, fullChunks + 1)
                .mapToObj(n -> source.subList(n * length, n == fullChunks ? size : (n + 1) * length))
                .collect(toList());
    }
}
