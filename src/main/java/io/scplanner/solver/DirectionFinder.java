package io.scplanner.solver;

import io.scplanner.constraints.Constraint;
import io.scplanner.exceptions.SolutionConfigurationException;
import io.scplanner.exceptions.SolutionSolvingException;
import io.scplanner.readers.PlanningVariableReader;
import io.scplanner.utils.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

public enum DirectionFinder {

    ADD,
    REMOVE,
    SKIP;

    public static <S, F, P> DirectionFinder of(Constraint<S, F, P> constraint, Set<P> refPlanningVariables, F fact) throws SolutionConfigurationException {
        Set<P> refPlanningVariablesCopy = CollectionUtils.copySet(refPlanningVariables);
        final Set<P> factPlanningVariables = PlanningVariableReader.factPlanningVariablesFrom(constraint, refPlanningVariablesCopy, fact);
        int initialScore = constraint.calculateScore(factPlanningVariables);
        if (testAdd(constraint, fact, refPlanningVariablesCopy, factPlanningVariables, initialScore)) {
            return ADD;
        } else if (testRemove(constraint, fact, refPlanningVariablesCopy, factPlanningVariables, initialScore)) {
            return REMOVE;
        }
        return SKIP;
    }

    private static <S, F, P> boolean testRemove(Constraint<S, F, P> constraint, F fact, Set<P> refPlanningVariablesCopy, Set<P> factPlanningVariables, int initialScore) throws SolutionConfigurationException {
        final boolean betterScoreAfterModifyingPV = betterScoreAfterModifyingPV(REMOVE, constraint, refPlanningVariablesCopy, factPlanningVariables, fact, initialScore);
        return betterScoreAfterModifyingPV;
    }

    private static <S, F, P> boolean testAdd(Constraint<S, F, P> constraint, F fact, Set<P> refPlanningVariablesCopy, Set<P> factPlanningVariables, int initialScore) throws SolutionConfigurationException {
        final boolean betterScoreAfterModifyingPV = betterScoreAfterModifyingPV(ADD, constraint, refPlanningVariablesCopy, factPlanningVariables, fact, initialScore);
        return factPlanningVariables.isEmpty() || betterScoreAfterModifyingPV;
    }

    private static <S, F, P> boolean betterScoreAfterModifyingPV(DirectionFinder direction, Constraint<S, F, P> constraint,
                                                                 Set<P> refPlanningVariables, Set<P> factPlanningVariables, F fact, int initialScore)
            throws SolutionConfigurationException {
        try {
            final Set<P> factPlanningVariablesCopy = CollectionUtils.copySet(factPlanningVariables);
            modifyRefPlanningVariables(direction, constraint, fact, refPlanningVariables, factPlanningVariablesCopy);
            int scoreAfterAdd = constraint.calculateScore(factPlanningVariablesCopy);
            return scoreAfterAdd >= initialScore;
        } catch (SolutionSolvingException e) {
            return false;
        }
    }

    private static <S, F, P> void modifyRefPlanningVariables(DirectionFinder direction, Constraint<S, F, P> constraint, F fact,
                                                             Set<P> refPlanningVariablesCopy, Set<P> factPlanningVariablesCopy) throws SolutionConfigurationException, SolutionSolvingException {
        int loopMinNumber = factPlanningVariablesCopy.size();
        int loopMaxNumber = BigDecimal.valueOf(factPlanningVariablesCopy.size())
                .multiply(BigDecimal.valueOf(1.3))
                .setScale(0, RoundingMode.UP)
                .intValue();
        for (int i = loopMinNumber; i <= loopMaxNumber; i++) {
            if (direction.equals(ADD)) {
                PlanningVariablesModifier.addPlanningVariableFromRef(constraint, fact, refPlanningVariablesCopy, factPlanningVariablesCopy);
            } else if (direction.equals(REMOVE)) {
                PlanningVariablesModifier.removePlanningVariable(fact, factPlanningVariablesCopy);
            }
        }
    }
}

