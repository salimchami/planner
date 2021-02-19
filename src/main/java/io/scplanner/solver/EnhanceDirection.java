package io.scplanner.solver;

import io.scplanner.constraints.Constraint;
import io.scplanner.exceptions.SolutionConfigurationException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

public enum EnhanceDirection {

    ADD,
    REMOVE,
    SKIP;

    public static <S, F, P> EnhanceDirection of(Constraint<S, F, P> constraint, Set<P> refPlanningVariables, Set<P> factPlanningVariables, F fact) throws SolutionConfigurationException {
        Set<P> factPlanningVariablesCopy = new HashSet<>(factPlanningVariables);
        Set<P> refPlanningVariablesCopy = new HashSet<>(refPlanningVariables);
        int initialScore = constraint.calculateScore(factPlanningVariablesCopy);
        if (tryModify(ADD, constraint, fact, factPlanningVariablesCopy, refPlanningVariablesCopy, initialScore)) {
            return ADD;
        } else if (tryModify(REMOVE, constraint, fact, factPlanningVariablesCopy, refPlanningVariablesCopy, initialScore)) {
            return REMOVE;
        }
        return SKIP;
    }

    private static <S, F, P> boolean tryModify(EnhanceDirection direction, Constraint<S, F, P> constraint, F fact, Set<P> factPlanningVariables, Set<P> refPlanningVariables, int initialScore)
            throws SolutionConfigurationException {
        if (factPlanningVariables.isEmpty()) {
            return direction == ADD;
        }
        int scoreAfterAdd = scoreAfterModifyingPlanningVariables(direction, constraint, refPlanningVariables, factPlanningVariables, fact);
        return scoreAfterAdd >= initialScore;
    }

    private static <S, F, P> int scoreAfterModifyingPlanningVariables(EnhanceDirection direction, Constraint<S, F, P> constraint, Set<P> refPlanningVariables, Set<P> factPlanningVariables, F fact)
            throws SolutionConfigurationException {
        HashSet<P> refPlanningVariablesCopy = new HashSet<>(refPlanningVariables);
        HashSet<P> factPlanningVariablesCopy = new HashSet<>(factPlanningVariables);
        modifyRefPlanningVariables(direction, constraint, fact, refPlanningVariablesCopy, factPlanningVariablesCopy);
        return constraint.calculateScore(factPlanningVariablesCopy);
    }

    private static <S, F, P> void modifyRefPlanningVariables(EnhanceDirection direction, Constraint<S, F, P> constraint, F fact, HashSet<P> refPlanningVariablesCopy, HashSet<P> factPlanningVariablesCopy) throws SolutionConfigurationException {
        int loopMinNumber = factPlanningVariablesCopy.size();
        int loopMaxNumber = BigDecimal.valueOf(factPlanningVariablesCopy.size()).multiply(BigDecimal.valueOf(1.3)).setScale(0, RoundingMode.UP).intValue();
        for (int i = loopMinNumber; i <= loopMaxNumber; i++) {
            if (direction.equals(ADD)) {
                PlanningVariablesModifier.addPlanningVariable(constraint, fact, refPlanningVariablesCopy, factPlanningVariablesCopy);
            } else if (direction.equals(REMOVE)) {
                PlanningVariablesModifier.removePlanningVariable(constraint, fact, factPlanningVariablesCopy);
            }
        }
    }
}

