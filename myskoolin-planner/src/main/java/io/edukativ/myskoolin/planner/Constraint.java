package io.edukativ.myskoolin.planner;

public class Constraint implements ConstraintContract{

    private ConstraintFactory constraintFactory;
    private String constraintPackage;
    private String constraintName;

    @Override
    public ConstraintFactory getConstraintFactory() {
        return constraintFactory;
    }

    @Override
    public String getConstraintPackage() {
        return constraintPackage;
    }

    @Override
    public String getConstraintName() {
        return constraintName;
    }

//    public ScoreImpactType getScoreImpactType() {
//        return scoreImpactType;
//    }
}
