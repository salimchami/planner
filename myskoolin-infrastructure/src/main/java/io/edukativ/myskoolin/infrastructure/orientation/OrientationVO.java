package io.edukativ.myskoolin.infrastructure.orientation;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class OrientationVO implements Serializable {

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
        OrientationVO that = (OrientationVO) o;
        return objectives.equals(that.objectives);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectives);
    }

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {

            return "Orientation{" +
                    "objectives=" + objectives +
                    '}';
        }
    }

    // talents
    // aspirations
    // parcours de formation
    // list rendez vous
    // objectives
}
