package io.edukativ.myskoolin.planner;

import io.edukativ.myskoolin.planner.exceptions.SolutionConfigurationException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public final class ReflectionUtils {

    private ReflectionUtils() {
        // private constructor
    }

    static Class<?> findFieldClass(Field field) {
        final Class<?> fieldType = field.getType();
        if (Collection.class.isAssignableFrom(fieldType)) {
            ParameterizedType collectionType = (ParameterizedType) field.getGenericType();
            return (Class<?>) collectionType.getActualTypeArguments()[0];
        } else {
            return fieldType;
        }
    }

    static void checkClassAnnotation(Class<?> clazz, String exceptionMessage, Class<? extends Annotation> classAnnotation) throws SolutionConfigurationException {
        if (!clazz.isAnnotationPresent(classAnnotation)) {
            throw new SolutionConfigurationException(exceptionMessage);
        }
    }

    static void checkFieldsAnnotations(Field[] fields, List<Class<? extends Annotation>> annotations, String exceptionMessage, boolean withDuplicatedAnnotations) throws SolutionConfigurationException {
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

    static Field findFieldByAnnotation(Class<?> clazz, Class<? extends Annotation> annotation) throws SolutionConfigurationException {
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
                        String.format("Field annotation %s not found.", annotation.getName())
                ));
    }
}
