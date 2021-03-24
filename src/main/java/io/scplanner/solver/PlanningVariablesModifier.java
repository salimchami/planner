package io.scplanner.solver;

import io.scplanner.annotations.PlanningVariableFact;
import io.scplanner.constraints.Constraint;
import io.scplanner.exceptions.SolutionConfigurationException;
import io.scplanner.exceptions.SolutionSolvingException;
import io.scplanner.readers.FactReader;
import io.scplanner.readers.PlanningVariableReader;
import io.scplanner.reflection.Reflection;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.Set;

public final class PlanningVariablesModifier {

    private PlanningVariablesModifier() {
        // private constructor
    }

    public static <S, F, P> void addPlanningVariableFromRef(Constraint<S, F, P> constraint, F fact, Set<P> refPlanningVariables, Set<P> factPlanningVariables)
            throws SolutionConfigurationException, SolutionSolvingException {
        final Optional<P> optFreeRefPlanningVariable = PlanningVariableReader.freePlanningVariable(constraint, refPlanningVariables);
        if (optFreeRefPlanningVariable.isPresent()) {
            final P planningVariable = optFreeRefPlanningVariable.get();
            Reflection.assignFieldByAnnotations(fact, planningVariable, PlanningVariableFact.class);
            factPlanningVariables.add(planningVariable);
        } else {
            throw new SolutionSolvingException("No planning variable available to add one. Is there enough base planning variables ?");
        }
    }

    public static <F, P> void removePlanningVariable(F fact, Set<P> factPlanningVariables) throws SolutionConfigurationException {
        Object factId = FactReader.id(fact);
        for (P pv : factPlanningVariables) {
            final Object pvFact = Reflection.valueByAnnotation(pv, PlanningVariableFact.class);
            if (factId.equals(FactReader.id(pvFact))) {
                factPlanningVariables.remove(pv);
                break;
            }
        }
    }

    public static <F, P> void removeAllFactsFromPlanningVariables(F fact, Set<P> planningVariables) throws SolutionConfigurationException {
        Object factId = FactReader.id(fact);
        for (P pv : planningVariables) {
            final Object pvFact = Reflection.valueByAnnotation(pv, PlanningVariableFact.class);
            if (pvFact != null && factId.equals(FactReader.id(pvFact))) {
                try {
                    BeanUtils.copyProperty(pv, Reflection.fieldByType(pv.getClass(), fact.getClass()).getName(), null);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new SolutionConfigurationException("Can't remove fact property in planning variable.");
                }
            }
        }
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
