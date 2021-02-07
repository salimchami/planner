package io.edukativ.myskoolin.planner;

import io.edukativ.myskoolin.planner.declarations.*;
import io.edukativ.myskoolin.planner.exceptions.SolutionConfigurationException;
import io.edukativ.myskoolin.planner.reflection.Reflection;

import java.lang.reflect.Field;
import java.util.Arrays;

import static io.edukativ.myskoolin.planner.reflection.Reflection.checkClassAnnotation;

public class SolutionAnnotations<S> {

    private final S solution;

    public SolutionAnnotations(S solution) {
        this.solution = solution;
    }

    public void check() throws SolutionConfigurationException {
        checkClassAnnotation(solution.getClass(),
                String.format("The solution Class is not well configured. Please add @%s annotation on the class.", PlanningSolution.class.getName()), PlanningSolution.class
        );
        checkSolutionFieldsAnnotations(solution);
        checkBaseVariablesClassAnnotations(solution);
        checkModifiableVariablesClassAnnotations(solution);
        checkFactsClassAnnotations(solution);
    }

    private void checkFactsClassAnnotations(S solution) throws SolutionConfigurationException {
        final Field field = Reflection.fieldByAnnotation(solution.getClass(), Facts.class);
        checkClassAnnotation(Reflection.fieldTypeClass(field), String.format("The Facts Class is not well configured. Please add @%s annotation on the class.",
                Fact.class.getName()), Fact.class);
        Reflection.checkFieldsAnnotations(Reflection.fieldTypeClass(field).getDeclaredFields(), Arrays.asList(FactId.class, FactItem.class), String.format(
                "The Fact Class is not well configured. The class must have one field annotated with %s and one or multiple annotations '%s'.",
                FactId.class.getName(), FactItem.class.getName()), true);
    }

    private void checkBaseVariablesClassAnnotations(S solution) throws SolutionConfigurationException {
        final Field field = Reflection.fieldByAnnotation(solution.getClass(), BasePlanningVariables.class);
        checkClassAnnotation(Reflection.fieldTypeClass(field), String.format("The Base Planning Variable Class is not well configured. Please add @%s annotation on the class.",
                PlanningVariable.class.getName()), PlanningVariable.class);
        Reflection.checkFieldsAnnotations(Reflection.fieldTypeClass(field).getDeclaredFields(), Arrays.asList(PlanningVariableId.class, PlanningVariableItem.class), String.format(
                "The Modifiable Planning Variable Class is not well configured. The class must have one field annotated with %s and one or multiple annotations '%s'.",
                PlanningVariableId.class.getName(), PlanningVariableItem.class.getName()), true);
    }

    private void checkModifiableVariablesClassAnnotations(S solution) throws SolutionConfigurationException {
        final Field field = Reflection.fieldByAnnotation(solution.getClass(), ModifiablePlanningVariables.class);
        checkClassAnnotation(Reflection.fieldTypeClass(field), String.format("The Modifiable Planning Variable Class is not well configured. Please add @%s annotation on the class.",
                PlanningVariable.class.getName()), PlanningVariable.class);
        Reflection.checkFieldsAnnotations(Reflection.fieldTypeClass(field).getDeclaredFields(), Arrays.asList(PlanningVariableId.class, PlanningVariableItem.class), String.format(
                "The Modifiable Planning Variable Class is not well configured. The class must have one field annotated with %s and one or multiple annotations '%s'.",
                PlanningVariableId.class.getName(), PlanningVariableItem.class.getName()), true);
    }

    private void checkSolutionFieldsAnnotations(S solution) throws SolutionConfigurationException {
        final Field[] solutionDeclaredFields = solution.getClass().getDeclaredFields();
        Reflection.checkFieldsAnnotations(solutionDeclaredFields, Arrays.asList(SolutionId.class, BasePlanningVariables.class, ModifiablePlanningVariables.class, Facts.class),
                String.format("The Solution Class is not well configured. The class must have three fields (only) with these annotations '%s', '%s', '%s', '%s'.",
                        SolutionId.class.getName(), BasePlanningVariables.class.getName(), ModifiablePlanningVariables.class.getName(), Facts.class.getName()), false);
    }

}
