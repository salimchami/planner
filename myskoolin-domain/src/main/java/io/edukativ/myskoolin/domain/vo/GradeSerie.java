package io.edukativ.myskoolin.domain.vo;

import java.util.Comparator;
import java.util.Objects;

/**
 * Grade series.
 */
public class GradeSerie implements Comparable<GradeSerie> {

    private String name;
    private String diminutive;
    private String option;

    public GradeSerie() {
    }

    public GradeSerie(String name) {
        this.name = name;
    }

    public GradeSerie(String name, String diminutive, String option) {
        this.name = name;
        this.diminutive = diminutive;
        this.option = option;
    }

    public GradeSerie(String name, String diminutive) {
        this.name = name;
        this.diminutive = diminutive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiminutive() {
        return diminutive;
    }

    public void setDiminutive(String diminutive) {
        this.diminutive = diminutive;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    @Override
    public int compareTo(GradeSerie o) {
        return Comparator
                .comparing(GradeSerie::getName)
                .compare(this, o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradeSerie that = (GradeSerie) o;
        return name.equals(that.name) &&
                Objects.equals(diminutive, that.diminutive) &&
                Objects.equals(option, that.option);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, diminutive, option);
    }

}
