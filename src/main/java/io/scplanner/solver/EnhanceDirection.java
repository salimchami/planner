package io.scplanner.solver;

import io.scplanner.constraints.Constraint;
import io.scplanner.exceptions.SolutionConfigurationException;
import io.scplanner.reflection.Reflection;

import java.util.Set;

public enum EnhanceDirection {

    ADD,
    REMOVE,
    SKIP;

    public static <F, P> EnhanceDirection of(Constraint constraint, F fact, Set<P> factPlanningVariables) throws SolutionConfigurationException {
        int count = 0;
        for (P planningVariable : factPlanningVariables) {
            if (Reflection.objectFieldByType(planningVariable, constraint.getFactClass()) != null) {
                count++;
            }
        }
        return EnhanceDirection.ADD;
    }
}
