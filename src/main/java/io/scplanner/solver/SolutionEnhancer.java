package io.scplanner.solver;

import io.scplanner.annotations.PlanningVariableFact;
import io.scplanner.annotations.PlanningVariableId;
import io.scplanner.constraints.Constraint;
import io.scplanner.exceptions.SolutionConfigurationException;
import io.scplanner.reflection.Reflection;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class SolutionEnhancer {

    public <F, P> Set<P> improveByConstraint(Constraint constraint, F fact, Set<P> refPlanningVariables) throws SolutionConfigurationException {
        Set<P> factPlanningVariables = factPlanningVariables(constraint, refPlanningVariables);
        int loopCount = 0;
        while (constraint.calculateScore(factPlanningVariables) < 0 && loopCount < refPlanningVariables.size() * 2) {
            EnhanceDirection direction = EnhanceDirection.of(constraint, fact, factPlanningVariables);
            if (direction == EnhanceDirection.ADD) {
                addPlanningVariable(constraint, fact, refPlanningVariables, factPlanningVariables);
            } else {

            }
            loopCount++;
        }
        replaceFactPlanningVariablesInRef(refPlanningVariables, factPlanningVariables);
        return refPlanningVariables;
    }

    private <P> void replaceFactPlanningVariablesInRef(Set<P> refPlanningVariables, Set<P> factPlanningVariables) throws SolutionConfigurationException {
        for (P factPv : factPlanningVariables) {
            for (P refPv : refPlanningVariables) {
                final Object refPvId = Reflection.valueByAnnotation(refPv, PlanningVariableId.class);
                final Object factPvId = Reflection.valueByAnnotation(factPv, PlanningVariableId.class);
                if (refPvId == factPvId || refPvId.equals(factPvId)) {
                    refPv = factPv;
                }
            }
        }
    }

    private <F, P> void addPlanningVariable(Constraint constraint, F fact, Set<P> refPlanningVariables, Set<P> factPlanningVariables) throws SolutionConfigurationException {
        final Optional<P> optFreePlanningVariable = freePlanningVariable(constraint, refPlanningVariables);
        if (optFreePlanningVariable.isPresent()) {

            final P planningVariable = optFreePlanningVariable.get();
            Reflection.assignFieldByAnnotations(fact, planningVariable, PlanningVariableFact.class);
            factPlanningVariables.add(planningVariable);
        }
    }

    private <P> Set<P> factPlanningVariables(Constraint constraint, Set<P> refPlanningVariables) throws SolutionConfigurationException {
        Set<P> factPlanningVariables = new HashSet<>();
        for (P planningVariable : refPlanningVariables) {
            if (Reflection.objectFieldByType(planningVariable, constraint.getFactClass()) != null) {
                factPlanningVariables.add(planningVariable);
            }
        }
        return factPlanningVariables;
    }

    private <P> Optional<P> freePlanningVariable(Constraint constraint, Set<P> refPlanningVariables) throws SolutionConfigurationException {
        for (P planningVariable : refPlanningVariables) {
            if (Reflection.objectFieldByType(planningVariable, constraint.getFactClass()) == null) {
                return Optional.of(planningVariable);
            }
        }
        return Optional.empty();
    }

}
