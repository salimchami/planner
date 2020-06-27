package io.edukativ.myskoolin.infrastructure.grades;

import java.io.Serializable;

public class GradeSerieVO implements Serializable {

    private String name;
    private String diminutive;
    private String option;

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
}
