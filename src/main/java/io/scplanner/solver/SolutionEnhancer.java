package io.scplanner.solver;

import io.scplanner.constraints.Constraint;
import io.scplanner.exceptions.SolutionConfigurationException;

import java.util.Set;

public class SolutionEnhancer {

    public <S, F, P> Set<P> improveByConstraint(Constraint<S, F, P> constraint, F fact, Set<P> refPlanningVariables) throws SolutionConfigurationException {
        Set<P> factPlanningVariables = PlanningVariablesModifier.factPlanningVariablesFrom(constraint, refPlanningVariables);
        int loopCount = 0;
        int loopMax = refPlanningVariables.size() * 2;
        while (constraint.calculateScore(factPlanningVariables) < 0 && loopCount < loopMax) {
            DirectionFinder direction = DirectionFinder.of(constraint, refPlanningVariables, fact);
            switch (direction) {
                case ADD:
                    PlanningVariablesModifier.addPlanningVariableFromRef(constraint, fact, refPlanningVariables, factPlanningVariables);
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
        replaceFactPlanningVariablesInRef(constraint, fact, factPlanningVariables.size(), refPlanningVariables);
        return refPlanningVariables;
    }

    private <S, F, P> void replaceFactPlanningVariablesInRef(Constraint<S, F, P> constraint, F fact, int nbFactsToAdd, Set<P> refPlanningVariables) throws SolutionConfigurationException {
        PlanningVariablesModifier.removeAllFactsFromPlanningVariables(fact, refPlanningVariables);
        for (int i = 0; i < nbFactsToAdd; i++) {
            PlanningVariablesModifier.addFactInFreeRefPlanningVariables(constraint, fact, refPlanningVariables);
        }
    }
}
