package io.scplanner.readers;

import io.scplanner.annotations.FactId;
import io.scplanner.exceptions.SolutionConfigurationException;
import io.scplanner.reflection.Reflection;

public final class FactReader {

    private FactReader() {
        // private constructor
    }

    public static <F> Object id(F fact) throws SolutionConfigurationException {
        return Reflection.valueByAnnotation(fact, FactId.class);
    }
}
