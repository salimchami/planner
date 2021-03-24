package io.scplanner.solver.direction;

import io.scplanner.constraints.Constraint;
import io.scplanner.exceptions.SolutionConfigurationException;
import io.scplanner.utils.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DirectionFinderParts<S, F, P> {

    private final DirectionFinder direction;
    private final Constraint<S, F, P> constraint;
    private final F fact;
    private final Set<P> planningVariables;
    private final List<List<P>> planningVariablesParts;

    public DirectionFinderParts(DirectionFinder direction, Constraint<S, F, P> constraint, F fact, Set<P> planningVariables) throws SolutionConfigurationException {
        this.direction = direction;
        this.constraint = constraint;
        this.fact = fact;
        this.planningVariables = CollectionUtils.copySet(planningVariables);
        int partsLength = new BigDecimal(planningVariables.size()).divide(BigDecimal.TEN, RoundingMode.HALF_UP).intValue();
        this.planningVariablesParts = CollectionUtils.batches(new ArrayList<>(CollectionUtils.copySet(planningVariables)), partsLength);
    }


    public BetterDirectionPlanningVariable<P> betterPlanningVariableFromParts() {
//        this.planningVariablesParts.stream()
        return null;

//        return constraint.calculateScore(planningVariables);
    }
}
