package io.scplanner.solver;

import io.scplanner.constraints.Constraint;
import io.scplanner.exceptions.SolutionConfigurationException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public enum EnhanceDirection {

    ADD,
    REMOVE,
    SKIP;

    public static <S, F, P> EnhanceDirection of(Constraint<S, F, P> constraint, Set<P> refPlanningVariables, Set<P> factPlanningVariables, F fact) throws SolutionConfigurationException {
        Set<P> factPlanningVariablesCopy = new HashSet<>(factPlanningVariables);
        Set<P> refPlanningVariablesCopy = new HashSet<>(refPlanningVariables);
        int initialScore = constraint.calculateScore(factPlanningVariablesCopy);
        return tryAdd(fact, factPlanningVariablesCopy, refPlanningVariablesCopy, initialScore)
                .orElseGet(() -> tryRemove(fact, factPlanningVariablesCopy, refPlanningVariablesCopy, initialScore)
                        .orElse(SKIP));
    }

    private static <F, P> Optional<EnhanceDirection> tryAdd(F fact, Set<P> factPlanningVariables, Set<P> refPlanningVariables, int initialScore) {
        int scoreAfterAdd = scoreAfterAddingPlanningVariables(refPlanningVariables, factPlanningVariables, fact);
        if (scoreAfterAdd >= initialScore) {
            return Optional.of(ADD);
        }
        return Optional.empty();
    }

    private static <F, P> Optional<EnhanceDirection> tryRemove(F fact, Set<P> factPlanningVariables, Set<P> refPlanningVariables, int initialScore) {
        int scoreAfterRemove = scoreAfterRemovingPlanningVariables(refPlanningVariables, factPlanningVariables, fact);
        if (scoreAfterRemove >= initialScore) {
            return Optional.of(REMOVE);
        }
        return Optional.empty();
    }

    private static <P, F> int scoreAfterRemovingPlanningVariables(Set<P> refPlanningVariables, Set<P> factPlanningVariables, F fact) {
        for(int i = factPlanningVariables.size(); i <= factPlanningVariables.size() / 2; i--) {
            System.out.println("adding one timeslot");
        }
        return 0;
    }

    private static <P, F> int scoreAfterAddingPlanningVariables(Set<P> refPlanningVariables, Set<P> factPlanningVariables, F fact) {
        for(int i = factPlanningVariables.size(); i <= factPlanningVariables.size() / 2; i--) {

            System.out.println("adding one timeslot");
        }
        return 0;
    }
}
