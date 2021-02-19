package io.scplanner.solver;

import io.scplanner.annotations.PlanningVariableId;
import io.scplanner.constraints.Constraint;
import io.scplanner.exceptions.SolutionConfigurationException;
import io.scplanner.reflection.Reflection;

import java.util.HashSet;
import java.util.Set;

public class SolutionEnhancer {

    public <S, F, P> Set<P> improveByConstraint(Constraint<S, F, P> constraint, F fact, Set<P> refPlanningVariables) throws SolutionConfigurationException {
        Set<P> factPlanningVariables = factPlanningVariables(constraint, refPlanningVariables);
        int loopCount = 0;
        while (constraint.calculateScore(factPlanningVariables) < 0 && loopCount < refPlanningVariables.size() * 2) {
            EnhanceDirection direction = EnhanceDirection.of(constraint, refPlanningVariables, factPlanningVariables, fact);
            switch (direction) {
                case ADD:
                    PlanningVariablesModifier.addPlanningVariable(constraint, fact, refPlanningVariables, factPlanningVariables);
                    break;
                case REMOVE:
                    PlanningVariablesModifier.removePlanningVariable(constraint, fact, factPlanningVariables);
                    break;
                case SKIP:
                    break;
            }
            loopCount++;
        }
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

    private <P> Set<P> factPlanningVariables(Constraint constraint, Set<P> refPlanningVariables) throws SolutionConfigurationException {
        Set<P> factPlanningVariables = new HashSet<>();
        for (P planningVariable : refPlanningVariables) {
            if (Reflection.objectFieldByType(planningVariable, constraint.getFactClass()) != null) {
                factPlanningVariables.add(planningVariable);
            }
        }
        return factPlanningVariables;
    }

}
