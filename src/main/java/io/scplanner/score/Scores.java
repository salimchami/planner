package io.scplanner.score;

import io.scplanner.constraints.Constraint;
import io.scplanner.exceptions.SolutionConfigurationException;

import java.util.List;
import java.util.Set;

public class Scores {

    public <S, F, P> int solutionScore(List<Constraint> constraints, Set<P> planningVars) throws SolutionConfigurationException {
        int score = 0;
        for (Constraint<S, F, P> constraint : constraints) {
            score += constraint.calculateScore(planningVars);
        }
        return score;
    }

}
