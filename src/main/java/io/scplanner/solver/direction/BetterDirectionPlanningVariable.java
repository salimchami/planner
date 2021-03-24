package io.scplanner.solver.direction;

public class BetterDirectionPlanningVariable<P> {

    private final P planningVariable;
    private final DirectionFinder direction;

    public BetterDirectionPlanningVariable(P planningVariable, DirectionFinder direction) {
        this.planningVariable = planningVariable;
        this.direction = direction;
    }
}
