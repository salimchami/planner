package io.scplanner.solver;

import io.scplanner.annotations.PlanningVariableId;
import io.scplanner.constraints.Constraint;
import io.scplanner.exceptions.SolutionConfigurationException;
import io.scplanner.reflection.Reflection;

import java.util.Set;

public class SolutionEnhancer {

    public <S, F, P> Set<P> improveByConstraint(Constraint<S, F, P> constraint, F fact, Set<P> refPlanningVariables) throws SolutionConfigurationException {
        Set<P> factPlanningVariables = PlanningVariablesModifier.factPlanningVariablesFrom(constraint, refPlanningVariables);
        int loopCount = 0;
        int loopMax = refPlanningVariables.size() * 2;
        while (constraint.calculateScore(factPlanningVariables) < 0 && loopCount < loopMax) {
            EnhanceDirection direction = EnhanceDirection.of(constraint, refPlanningVariables, fact);
            switch (direction) {
                case ADD:
                    PlanningVariablesModifier.addPlanningVariable(constraint, fact, refPlanningVariables, factPlanningVariables);
                    break;
                case REMOVE:
                    PlanningVariablesModifier.removePlanningVariable(fact, factPlanningVariables);
                    break;
                case SKIP:
                default:
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
}
