package io.edukativ.myskoolin.planner;

import io.edukativ.myskoolin.planner.declarations.PlanningVariable;
import io.edukativ.myskoolin.planner.exceptions.SolutionConfigurationException;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

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
    private Reflections reflections;
    private final S initialSolution;
    private S finalBestSolution;
    private List<V> basePlanningVariables;
    private List<V> planningVariables;

    public SolverJob(String basePackage, S solution) {
        this.basePackage = basePackage;
        this.initialSolution = solution;
        this.reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(this.basePackage))
                .setScanners(new SubTypesScanner(),
                        new TypeAnnotationsScanner())
                .filterInputsBy(new FilterBuilder().includePackage(this.basePackage)));
    }

    public S getFinalBestSolution() {
        return finalBestSolution;
    }

    public void terminateEarly() {
        this.finalBestSolution = this.initialSolution;
    }

    public void startSolving() throws SolutionConfigurationException {
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(PlanningVariable.class);

        final Set<Field> fieldsAnnotatedWith = this.reflections.getFieldsAnnotatedWith(PlanningVariable.class);
        System.out.println(fieldsAnnotatedWith);


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
