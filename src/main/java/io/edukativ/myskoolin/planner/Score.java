package io.edukativ.myskoolin.planner;

public class Score {

    private final Long level;

    public Score(Long level) {
        this.level = level;
    }

    public boolean isFeasible() {
        return level > 0;
    }
}
