package io.scplanner.solver;

import io.scplanner.constraints.Constraint;
import io.scplanner.exceptions.SolutionConfigurationException;
import io.scplanner.readers.PlanningVariableReader;
import io.scplanner.utils.CollectionUtils;

import java.util.Set;

public class SolutionEnhancer {

    public <S, F, P> Set<P> improveByConstraint(Constraint<S, F, P> constraint, F fact, Set<P> refPlanningVariables) throws SolutionConfigurationException {
        final Set<P> refPlanningVariablesCopy = CollectionUtils.copySet(refPlanningVariables);
        Set<P> factPlanningVariables = PlanningVariableReader.factPlanningVariablesFrom(constraint, refPlanningVariablesCopy, fact);
        int loopCount = 0;
        int loopMax = refPlanningVariablesCopy.size() * 2;
        while (constraint.calculateScore(factPlanningVariables) < 0 && loopCount < loopMax) {
            DirectionFinder direction = DirectionFinder.of(constraint, refPlanningVariablesCopy, fact);
            switch (direction) {
                case ADD:
                    PlanningVariablesModifier.addPlanningVariableFromRef(constraint, fact, refPlanningVariablesCopy, factPlanningVariables);
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
        replaceFactPlanningVariablesInRef(constraint, fact, factPlanningVariables.size(), refPlanningVariablesCopy);
        return refPlanningVariablesCopy;
    }

    private <S, F, P> void replaceFactPlanningVariablesInRef(Constraint<S, F, P> constraint, F fact, int nbFactsToAdd, Set<P> refPlanningVariables) throws SolutionConfigurationException {
        PlanningVariablesModifier.removeAllFactsFromPlanningVariables(fact, refPlanningVariables);
        for (int i = 0; i < nbFactsToAdd; i++) {
            PlanningVariablesModifier.addFactInFreeRefPlanningVariables(constraint, fact, refPlanningVariables);
        }
    }
}
