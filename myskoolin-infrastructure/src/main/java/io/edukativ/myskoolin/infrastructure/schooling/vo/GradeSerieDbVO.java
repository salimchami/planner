package io.edukativ.myskoolin.infrastructure.schooling.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.ComparisonChain;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

/**
 * Grade series.
 */
public class GradeSerieDbVO implements Serializable, Comparable<GradeSerieDbVO> {

    public static final String MONGO_FIELD_NAME = "name";
    public static final String MONGO_FIELD_DIMINUTIVE = "diminutive";
    public static final String MONGO_FIELD_OPTION = "option";

    @Field(MONGO_FIELD_NAME)
    @JsonProperty(MONGO_FIELD_NAME)
    private String name;

    @Field(MONGO_FIELD_DIMINUTIVE)
    @JsonProperty(MONGO_FIELD_DIMINUTIVE)
    private String diminutive;

    @Field(MONGO_FIELD_OPTION)
    @JsonProperty(MONGO_FIELD_OPTION)
    private String option;

    public GradeSerieDbVO() {
    }

    public GradeSerieDbVO(String name) {
        this.name = name;
    }

    public GradeSerieDbVO(String name, String diminutive, String option) {
        this.name = name;
        this.diminutive = diminutive;
        this.option = option;
    }

    public GradeSerieDbVO(String name, String diminutive) {
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
    public int compareTo(GradeSerieDbVO o) {
        if (o == null) {
            return 1;
        }
        if (this.getName() == null ^ o.getName() == null) {
            return (this.getName() == null) ? -1 : 1;
        }

        if (this.getName() == null && o.getName() == null) {
            return 0;
        }

        return ComparisonChain
                .start()
                .compare(this.name, o.getName())
                .result();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradeSerieDbVO that = (GradeSerieDbVO) o;
        return name.equals(that.name) &&
                Objects.equals(diminutive, that.diminutive) &&
                Objects.equals(option, that.option);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, diminutive, option);
    }

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {

            return "GradeSerie{" +
                    "name='" + name + '\'' +
                    ", diminutive='" + diminutive + '\'' +
                    ", option='" + option + '\'' +
                    '}';
        }
    }
}
