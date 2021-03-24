package io.scplanner.readers;

import io.scplanner.constraints.Constraint;
import io.scplanner.exceptions.SolutionConfigurationException;
import io.scplanner.reflection.Reflection;
import io.scplanner.utils.ObjectUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public final class PlanningVariableReader {


    private PlanningVariableReader() {
        // private constructor
    }

    public static <F, P> Object fact(P planningVariable, Class<F> factClass) throws SolutionConfigurationException {
    //return Reflection.valueByAnnotation(planningVariable, PlanningVariableFact.class);
        return Reflection.objectFieldByType(planningVariable, factClass);
    }

    public static <S, F, P> Set<P> factPlanningVariablesFrom(Constraint<S, F, P> constraint, Set<P> refPlanningVariables, F fact) throws SolutionConfigurationException {
        Set<P> factPlanningVariables = new HashSet<>();
        for (P planningVariable : refPlanningVariables) {
            final Object pvFact = fact(planningVariable, constraint.getFactClass());
            if (pvFact != null && FactReader.id(fact).equals(FactReader.id(pvFact))) {
                factPlanningVariables.add(ObjectUtils.newInstance(planningVariable));
            }
        }
        return factPlanningVariables;
    }

    public static <S, F, P> Optional<P> freePlanningVariable(Constraint<S, F, P> constraint, Set<P> refPlanningVariables) throws SolutionConfigurationException {
        for (P planningVariable : refPlanningVariables) {
            if (Reflection.objectFieldByType(planningVariable, constraint.getFactClass()) == null) {
                return Optional.of(planningVariable);
            }
        }
        return Optional.empty();
    }

}
