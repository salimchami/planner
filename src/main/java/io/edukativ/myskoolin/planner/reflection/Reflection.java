package io.edukativ.myskoolin.planner.reflection;

import io.edukativ.myskoolin.planner.declarations.PlanningVariableFact;
import io.edukativ.myskoolin.planner.exceptions.SolutionConfigurationException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public final class Reflection {

    private Reflection() {
        // private constructor
    }

    public static Class<?> fieldTypeClass(Field field) {
        final Class<?> fieldType = field.getType();
        if (Collection.class.isAssignableFrom(fieldType)) {
            ParameterizedType collectionType = (ParameterizedType) field.getGenericType();
            return (Class<?>) collectionType.getActualTypeArguments()[0];
        } else {
            return fieldType;
        }
    }

    public static void checkClassAnnotation(Class<?> clazz, String exceptionMessage, Class<? extends Annotation> classAnnotation) throws SolutionConfigurationException {
        if (!clazz.isAnnotationPresent(classAnnotation)) {
            throw new SolutionConfigurationException(exceptionMessage);
        }
    }

    public static void checkFieldsAnnotations(Field[] fields, List<Class<? extends Annotation>> annotations, String exceptionMessage, boolean withDuplicatedAnnotations) throws SolutionConfigurationException {
        List<Class<? extends Annotation>> collect = Arrays.stream(fields)
                .map(Field::getDeclaredAnnotations)
                .flatMap(Arrays::stream)
                .map(Annotation::annotationType)
                .filter(annotations::contains)
                .collect(toList());
        if ((withDuplicatedAnnotations && !collect.containsAll(annotations)) || (!withDuplicatedAnnotations && !collect.equals(annotations))) {
            throw new SolutionConfigurationException(exceptionMessage);
        }
    }

    public static Field fieldByAnnotation(Class<?> clazz, Class<? extends Annotation> annotation) throws SolutionConfigurationException {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> {
                    final Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
                    return Arrays.stream(declaredAnnotations)
                            .map(Annotation::annotationType)
                            .collect(toList())
                            .contains(annotation);
                })
                .findFirst()
                .orElseThrow(() -> new SolutionConfigurationException(
                        String.format("Field annotation %s not found in class %s.", annotation.getName(), clazz.getName())
                ));
    }

    public static <S> Object valueByAnnotation(S classInstance, Class<? extends Annotation> annotation) throws SolutionConfigurationException {
        final Field field = fieldByAnnotation(classInstance.getClass(), annotation);
        try {
            String fieldName = field.getName();
            final Method method = classInstance.getClass().getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
            return method.invoke(classInstance);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new SolutionConfigurationException(String.format("%s field value with annotation %s not found.", classInstance.getClass().getName(), annotation.getName()), e);
        }
    }

    public static Object instantiateClassInPackage(String packageName, Class<? extends Annotation> searchedAnnotation) throws SolutionConfigurationException {
        final String exceptionMessage = String.format("No Class with annotation %s found. Please add it in defined [sub] package %s.", searchedAnnotation, packageName);
        final Class<?> theClass = ClassFinder.find(packageName).stream()
                .filter(aClass -> Arrays.stream(aClass.getDeclaredAnnotations())
                        .map(Annotation::annotationType)
                        .collect(toList())
                        .contains(searchedAnnotation))
                .findFirst()
                .orElseThrow(() -> new SolutionConfigurationException(exceptionMessage));
        try {
            return theClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new SolutionConfigurationException(exceptionMessage);
        }
    }

    public static <V> List<Object> valuesByAnnotation(List<V> list, Class<? extends Annotation> annotation) throws SolutionConfigurationException {
        List<Object> values = new ArrayList<>();
        for (V value : list) {
            values.add(valueByAnnotation(value, annotation));
        }
        return values;
    }

    public static <S> void copyByAnnotations(S classInstance, Class<? extends Annotation> sourcePropertyAnnotation, Class<? extends Annotation> destPropertyAnnotation)
            throws SolutionConfigurationException {
        Object sourceValue = valueByAnnotation(classInstance, sourcePropertyAnnotation);
        final Field destField = fieldByAnnotation(classInstance.getClass(), destPropertyAnnotation);
        destField.setAccessible(true);
        try {
            final Method method = classInstance.getClass().getDeclaredMethod("set" + destField.getName().substring(0, 1).toUpperCase() + destField.getName().substring(1), List.class);
            method.invoke(classInstance, sourceValue);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException e) {
            throw new SolutionConfigurationException(String.format("Error while copying values from %s to %s for instance class %s.", sourcePropertyAnnotation.getName(), destPropertyAnnotation.getName(), classInstance.getClass().getName()), e);
        }
    }

    public static <S, V> void copyFieldByAnnotations(S classInstance, V destinationCLassInstance, Class<? extends Annotation> destAnnotation) {

    }
}
