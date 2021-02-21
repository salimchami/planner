package io.scplanner.solver;

import io.scplanner.annotations.PlanningVariableFact;
import io.scplanner.constraints.Constraint;
import io.scplanner.exceptions.SolutionConfigurationException;
import io.scplanner.reflection.Reflection;
import io.scplanner.utils.ObjectUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public final class PlanningVariablesModifier {

    private PlanningVariablesModifier() {
        // private constructor
    }

    public static <S, F, P> void addPlanningVariable(Constraint<S, F, P> constraint, F fact, Set<P> refPlanningVariables, Set<P> factPlanningVariables) throws SolutionConfigurationException {
        final Optional<P> optFreePlanningVariable = freePlanningVariable(constraint, refPlanningVariables);
        if (optFreePlanningVariable.isPresent()) {
            final P planningVariable = optFreePlanningVariable.get();
            Reflection.assignFieldByAnnotations(fact, planningVariable, PlanningVariableFact.class);
            factPlanningVariables.add(planningVariable);
        } else {
            System.out.println("No planning variable available to add one. Is there enough base planning variables ?");
        }
    }

    public static <F, P> void removePlanningVariable(F fact, Set<P> factPlanningVariables) throws SolutionConfigurationException {
        for (P pv : factPlanningVariables) {
            if (fact.equals(Reflection.valueByAnnotation(pv, PlanningVariableFact.class))) {
                factPlanningVariables.remove(pv);
                break;
            }
        }
    }

    private static <S, F, P> Optional<P> freePlanningVariable(Constraint<S, F, P> constraint, Set<P> refPlanningVariables) throws SolutionConfigurationException {
        for (P planningVariable : refPlanningVariables) {
            if (Reflection.objectFieldByType(planningVariable, constraint.getFactClass()) == null) {
                return Optional.of(planningVariable);
            }
        }
        return Optional.empty();
    }

    public static  <P> Set<P> factPlanningVariablesFrom(Constraint constraint, Set<P> refPlanningVariables) throws SolutionConfigurationException {
        Set<P> factPlanningVariables = new HashSet<>();
        for (P planningVariable : refPlanningVariables) {
            if (Reflection.objectFieldByType(planningVariable, constraint.getFactClass()) != null) {
                factPlanningVariables.add(ObjectUtils.newInstance(planningVariable));
            }
        }
        return factPlanningVariables;
    }
}
