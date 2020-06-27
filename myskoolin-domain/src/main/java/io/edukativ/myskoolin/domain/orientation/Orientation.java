package io.edukativ.myskoolin.domain.orientation;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Orientation implements Serializable {

    private List<String> objectives;

    public List<String> getObjectives() {
        return objectives;
    }

    public void setObjectives(List<String> objectives) {
        this.objectives = objectives;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orientation that = (Orientation) o;
        return objectives.equals(that.objectives);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectives);
    }

    @Override
    public String toString() {
        return "Orientation{" +
                "objectives=" + objectives +
                '}';
    }

    // talents
    // aspirations
    // parcours de formation
    // list rendez vous
    // objectives
}
