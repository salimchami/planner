package io.scplanner.utils;

import io.scplanner.exceptions.SolutionConfigurationException;

import java.util.HashSet;
import java.util.Set;

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
     * @see ObjectUtils
     * @param set The Set to clone
     * @throws SolutionConfigurationException if the Set objects to clone doesn't contains newInstance method
     * @return cloned Set.
     */
    public static <T> Set<T> copySet(Set<T> set) throws SolutionConfigurationException {
        Set<T> result = new HashSet<>();
        for (T t : set) {
            T newInstance = ObjectUtils.newInstance(t);
            result.add(newInstance);
        }
        return result;
    }

}
