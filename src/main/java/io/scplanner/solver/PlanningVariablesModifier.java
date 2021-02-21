package io.scplanner.solver;

import io.scplanner.annotations.PlanningVariableFact;
import io.scplanner.constraints.Constraint;
import io.scplanner.exceptions.SolutionConfigurationException;
import io.scplanner.reflection.Reflection;
import io.scplanner.utils.ObjectUtils;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public final class PlanningVariablesModifier {

    private PlanningVariablesModifier() {
        // private constructor
    }

    public static <S, F, P> void addPlanningVariableFromRef(Constraint<S, F, P> constraint, F fact, Set<P> refPlanningVariables, Set<P> factPlanningVariables) throws SolutionConfigurationException {
        final Optional<P> optFreeRefPlanningVariable = freePlanningVariable(constraint, refPlanningVariables);
        if (optFreeRefPlanningVariable.isPresent()) {
            final P planningVariable = optFreeRefPlanningVariable.get();
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

    public static <F, P> void removeAllFactsFromPlanningVariables(F fact, Set<P> planningVariables) throws SolutionConfigurationException {
        for (P pv : planningVariables) {
            if (fact.equals(Reflection.valueByAnnotation(pv, PlanningVariableFact.class))) {
                try {
                    BeanUtils.copyProperty(pv, Reflection.fieldByType(pv.getClass(), fact.getClass()).getName(), null);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new SolutionConfigurationException("Can't remove fact property in planning variable.");
                }
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

    public static <P> Set<P> factPlanningVariablesFrom(Constraint constraint, Set<P> refPlanningVariables) throws SolutionConfigurationException {
        Set<P> factPlanningVariables = new HashSet<>();
        for (P planningVariable : refPlanningVariables) {
            if (Reflection.objectFieldByType(planningVariable, constraint.getFactClass()) != null) {
                factPlanningVariables.add(ObjectUtils.newInstance(planningVariable));
            }
        }
        return factPlanningVariables;
    }

    public static <P, F, S> void addFactInFreeRefPlanningVariables(Constraint<S, F, P> constraint, F fact, Set<P> refPlanningVariables) throws SolutionConfigurationException {
        // TODO search for best planning variable
        for (P planningVariable : refPlanningVariables) {
            if (Reflection.objectFieldByType(planningVariable, constraint.getFactClass()) == null) {
                try {
                    BeanUtils.copyProperty(planningVariable, Reflection.fieldByType(planningVariable.getClass(), constraint.getFactClass()).getName(), fact);
                    break;
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new SolutionConfigurationException("Can't copy fact property in planning variable.");
                }
            }

        }
    }
}
