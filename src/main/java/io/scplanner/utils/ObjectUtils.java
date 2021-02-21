package io.scplanner.utils;

import io.scplanner.exceptions.SolutionConfigurationException;

import java.lang.reflect.InvocationTargetException;

public final class ObjectUtils {

    private ObjectUtils() {
        // prifvate constructor
    }

    /**
     * Object cloner
     * <p>The Object to clone must contain newInstance method :
     * <pre>
     * public TheObject newInstance() {
     *   return new TheObject(this.arg1, this.arg2...);
     * }
     * </pre>
     * </p>
     * @param t The object to clone
     * @return cloned object.
     */
    public static <T> T newInstance(T t) throws SolutionConfigurationException {
        try {
            return (T) t.getClass().getDeclaredMethod("newInstance").invoke(t);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new SolutionConfigurationException("Planning variable doesn't have newInstance() method. Please implement it.");
        }
    }
}
