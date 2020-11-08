package io.edukativ.myskoolin.planner;

import io.edukativ.myskoolin.planner.declarations.*;
import io.edukativ.myskoolin.planner.exceptions.SolutionConfigurationException;
import io.edukativ.myskoolin.planner.exceptions.SolutionSolvingException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.function.Function;

import static io.edukativ.myskoolin.planner.ReflectionUtils.*;

public class SolverManager<S, I> implements IWantToManageSolver<S, I> {

    private final Map<I, S> solutions;
    private final Map<I, SolverStatus> statuses;

    public SolverManager() {
        solutions = new HashMap<>();
        statuses = new HashMap<>();
    }

    @Override
    public void terminateEarly(I timeTableId) throws SolutionSolvingException {
        if (!solutions.containsKey(timeTableId)) {
            throw new SolutionSolvingException(String.format("The solution with the id '%s' doesn't exists.", timeTableId));
        }
        solutions.remove(timeTableId);
        statuses.remove(timeTableId);
    }

    @Override
    public SolverStatus getSolverStatus(I solutionId) {
        return statuses.get(solutionId);
    }

    @Override
    public void solveAndListen(I id, S solution, Function<S, I> saveFunction) throws SolutionConfigurationException, SolutionSolvingException {
        addSolution(id, solution);
        final SolverJob<S, I> solverJob = new SolverJob<>(solution);
        solverJob.startSolving();
        statuses.put(id, SolverStatus.SOLVING);
        waitForSolving(id, solverJob);
        saveFunction.apply(solverJob.getFinalBestSolution());
    }

    // #####################################################
    // #################### Internal methods ###############
    // #####################################################

    private void waitForSolving(I id, SolverJob<S, I> solverJob) throws SolutionSolvingException {
        CountDownLatch latch = new CountDownLatch(1);
        while (solverJob.isSolving(id)) {
            try {
                latch.await();
            } catch (InterruptedException e) {
                throw new SolutionSolvingException(String.format("Error while solving problem with the id '%s'.", id), e);
            }
        }
        latch.countDown();
    }

    private void addSolution(I id, S solution) throws SolutionConfigurationException {
        checkSolutionAnnotations(solution);
        checkIfSolutionExists(id);

        solutions.put(id, solution);
        statuses.put(id, SolverStatus.NOT_STARTED);
    }

    private void checkSolutionAnnotations(S solution) throws SolutionConfigurationException {
        checkClassAnnotation(solution.getClass(),
                String.format("The solution Class is not well configured. Please add @%s annotation on the class.", PlanningSolution.class.getName()), PlanningSolution.class
        );

        checkSolutionFieldsAnnotations(solution);
        checkBaseVariablesClassAnnotations(solution);
        checkModifiableVariablesClassAnnotations(solution);
        checkFactsClassAnnotations(solution);
    }

    private void checkIfSolutionExists(I id) throws SolutionConfigurationException {
        if (solutions.containsKey(id)) {
            throw new SolutionConfigurationException(String.format("The solution with the same id '%s' is already added. Solving status : '%s'.", id, statuses.get(id)));
        }
    }

    private void checkFactsClassAnnotations(S solution) throws SolutionConfigurationException {
        final Field field = findFieldByAnnotation(solution.getClass(), Facts.class);
        checkClassAnnotation(findFieldClass(field), String.format("The Facts Class is not well configured. Please add @%s annotation on the class.",
                Fact.class.getName()), Fact.class);
        checkFieldsAnnotations(findFieldClass(field).getDeclaredFields(), Arrays.asList(FactId.class, FactItem.class), String.format(
                "The Fact Class is not well configured. The class must have one field annotated with %s and one or multiple annotations '%s'.",
                FactId.class.getName(), FactItem.class.getName()), true);
    }

    private void checkBaseVariablesClassAnnotations(S solution) throws SolutionConfigurationException {
        final Field field = findFieldByAnnotation(solution.getClass(), BasePlanningVariables.class);
        checkClassAnnotation(findFieldClass(field), String.format("The Base Planning Variable Class is not well configured. Please add @%s annotation on the class.",
                PlanningVariable.class.getName()), PlanningVariable.class);
        checkFieldsAnnotations(findFieldClass(field).getDeclaredFields(), Arrays.asList(PlanningVariableId.class, PlanningVariableItem.class), String.format(
                "The Modifiable Planning Variable Class is not well configured. The class must have one field annotated with %s and one or multiple annotations '%s'.",
                PlanningVariableId.class.getName(), PlanningVariableItem.class.getName()), true);
    }

    private void checkModifiableVariablesClassAnnotations(S solution) throws SolutionConfigurationException {
        final Field field = findFieldByAnnotation(solution.getClass(), ModifiablePlanningVariables.class);
        checkClassAnnotation(findFieldClass(field), String.format("The Modifiable Planning Variable Class is not well configured. Please add @%s annotation on the class.",
                PlanningVariable.class.getName()), PlanningVariable.class);
        checkFieldsAnnotations(findFieldClass(field).getDeclaredFields(), Arrays.asList(PlanningVariableId.class, PlanningVariableItem.class), String.format(
                "The Modifiable Planning Variable Class is not well configured. The class must have one field annotated with %s and one or multiple annotations '%s'.",
                PlanningVariableId.class.getName(), PlanningVariableItem.class.getName()), true);
    }

    private void checkSolutionFieldsAnnotations(S solution) throws SolutionConfigurationException {
        final Field[] solutionDeclaredFields = solution.getClass().getDeclaredFields();
        checkFieldsAnnotations(solutionDeclaredFields, Arrays.asList(SolutionId.class, BasePlanningVariables.class, ModifiablePlanningVariables.class, Facts.class),
                String.format("The Solution Class is not well configured. The class must have three fields (only) with these annotations '%s', '%s', '%s', '%s'.",
                        SolutionId.class.getName(), BasePlanningVariables.class.getName(), ModifiablePlanningVariables.class.getName(), Facts.class.getName()), false);
    }
}
