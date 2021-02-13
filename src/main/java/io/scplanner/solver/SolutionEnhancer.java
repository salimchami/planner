package io.scplanner.solver;

import io.scplanner.constraints.Constraint;
import io.scplanner.exceptions.SolutionConfigurationException;
import io.scplanner.reflection.Reflection;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class SolutionEnhancer {


    public <F, P> List<P> improveByConstraint(Constraint constraint, F fact, List<P> refPlanningVariables) throws SolutionConfigurationException {
        List<P> factPlanningVariables = new ArrayList<>();
        for (P planningVariable : refPlanningVariables) {
            if (Reflection.objectFieldByType(planningVariable, constraint.getFactClass()) != null) {
                factPlanningVariables.add(planningVariable);
            }
        }
        return null;
    }
}
