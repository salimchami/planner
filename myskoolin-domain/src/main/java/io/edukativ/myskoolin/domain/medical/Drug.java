package io.edukativ.myskoolin.domain.medical;

import java.util.Objects;

public class Drug {

    private String name;
    private String comment;

    public Drug() {
    }

    public Drug(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

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
        return "Drug{" +
                "name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drug drug = (Drug) o;
        return Objects.equals(name, drug.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
