package io.edukativ.myskoolin.infrastructure.medical;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Objects;

public class DrugVO {

    private String name;
    private String comment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "Drug{" +
                    "name='" + name + '\'' +
                    ", comment='" + comment + '\'' +
                    '}';
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DrugVO drug = (DrugVO) o;
        return Objects.equals(name, drug.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
