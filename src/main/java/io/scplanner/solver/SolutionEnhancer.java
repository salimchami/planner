package io.scplanner.solver;

import io.scplanner.annotations.PlanningVariableFact;
import io.scplanner.constraints.Constraint;
import io.scplanner.exceptions.SolutionConfigurationException;
import io.scplanner.reflection.Reflection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SolutionEnhancer {

    public <F, P> List<P> improveByConstraint(Constraint constraint, F fact, List<P> refPlanningVariables) throws SolutionConfigurationException {
        List<P> factPlanningVariables = factPlanningVariables(constraint, refPlanningVariables);
        int loopCount = 0;
        while (constraint.calculateScore(factPlanningVariables) < 0 && loopCount < refPlanningVariables.size() * 2) {
            EnhanceDirection direction = searchForDirection(constraint, fact, factPlanningVariables);
            if (direction == EnhanceDirection.ADD) {
                addPlanningVariable(constraint, fact, refPlanningVariables, factPlanningVariables);
            } else {

            }
            loopCount++;
        }
        return factPlanningVariables;
    }

    private <F, P> void addPlanningVariable(Constraint constraint, F fact, List<P> refPlanningVariables, List<P> factPlanningVariables) throws SolutionConfigurationException {
        final Optional<P> optFreePlanningVariable = freePlanningVariable(constraint, refPlanningVariables);
        if (optFreePlanningVariable.isPresent()) {

            final P planningVariable = optFreePlanningVariable.get();
            Reflection.assignFieldByAnnotations(fact, planningVariable, PlanningVariableFact.class);
            factPlanningVariables.add(planningVariable);
        }
    }

    private <P> List<P> factPlanningVariables(Constraint constraint, List<P> refPlanningVariables) throws SolutionConfigurationException {
        List<P> factPlanningVariables = new ArrayList<>();
        for (P planningVariable : refPlanningVariables) {
            if (Reflection.objectFieldByType(planningVariable, constraint.getFactClass()) != null) {
                factPlanningVariables.add(planningVariable);
            }
        }
        return factPlanningVariables;
    }

    private <P> Optional<P> freePlanningVariable(Constraint constraint, List<P> refPlanningVariables) throws SolutionConfigurationException {
        for (P planningVariable : refPlanningVariables) {
            if (Reflection.objectFieldByType(planningVariable, constraint.getFactClass()) == null) {
                return Optional.of(planningVariable);
            }
        }
        return Optional.empty();
    }

    private <F, P> EnhanceDirection searchForDirection(Constraint constraint, F fact, List<P> factPlanningVariables) throws SolutionConfigurationException {
        int count = 0;
        for (P planningVariable : factPlanningVariables) {
            if (Reflection.objectFieldByType(planningVariable, constraint.getFactClass()) != null) {
                count++;
            }
        }
        return EnhanceDirection.ADD;
    }
}
