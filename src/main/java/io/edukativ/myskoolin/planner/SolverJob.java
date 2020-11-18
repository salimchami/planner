package io.edukativ.myskoolin.planner;

import io.edukativ.myskoolin.planner.declarations.PlanningVariable;
import io.edukativ.myskoolin.planner.exceptions.SolutionConfigurationException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

/**
 * @param <S> Solution
 * @param <I> Solution Id
 * @param <V> Planning variable
 */
public class SolverJob<S, I, V> {

    private String basePackage;
    private final S initialSolution;
    private S finalBestSolution;
    private List<V> basePlanningVariables;
    private List<V> planningVariables;

    public SolverJob(String basePackage, S solution) {
        this.basePackage = basePackage;
        this.initialSolution = solution;
    }

    public S getFinalBestSolution() {
        return finalBestSolution;
    }

    public void terminateEarly() {
        this.finalBestSolution = this.initialSolution;
    }

    public void startSolving() throws SolutionConfigurationException {


//        final Field basePlanningVariablesField = Reflection.findFieldByAnnotation(initialSolution.getClass(), BasePlanningVariables.class);
//        final Field modifiablePlanningVariablesField = Reflection.findFieldByAnnotation(initialSolution.getClass(), ModifiablePlanningVariables.class);

//        try {
//            final PropertyDescriptor propertyDescriptor = new PropertyDescriptor(basePlanningVariablesField.getName(), basePlanningVariablesField.getType());
//            propertyDescriptor.getReadMethod().invoke(initialSolution);
////            final List<V> o = (List<V>) basePlanningVariablesField.get(initialSolution);
////            System.out.println(o);
//        } catch (IllegalAccessException | IntrospectionException | InvocationTargetException e) {
//            throw new SolutionConfigurationException("Error while reading solution fields instances.", e);
//        }
        this.finalBestSolution = this.initialSolution;
    }

    public boolean isSolving(I id) {
        return false;
    }
}
