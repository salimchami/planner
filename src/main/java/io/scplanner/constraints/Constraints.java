package io.scplanner.constraints;

import io.scplanner.annotations.ConstraintsProvider;
import io.scplanner.exceptions.SolutionConfigurationException;
import io.scplanner.reflection.Reflection;

import java.util.Arrays;
import java.util.List;

public class Constraints {

    public <S> List<Constraint> loadConstraints(String basePackage, S initialSolution) throws SolutionConfigurationException {
        ConstraintProvider constraintProvider = (ConstraintProvider) Reflection.instantiateClassInPackage(basePackage, ConstraintsProvider.class);
        ConstraintFactory constraintFactory = new ConstraintFactoryImpl(() -> initialSolution);
        return Arrays.asList(constraintProvider.defineConstraints(initialSolution, constraintFactory));
    }


}
