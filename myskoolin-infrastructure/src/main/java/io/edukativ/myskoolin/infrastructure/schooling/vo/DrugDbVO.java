package io.edukativ.myskoolin.infrastructure.schooling.vo;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

public class DrugDbVO {

    public static final String MONGO_FIELD_NAME = "name";
    public static final String MONGO_FIELD_COMMENT = "comment";

    @Field(MONGO_FIELD_NAME)
    private String name;

    @Field(MONGO_FIELD_COMMENT)
    private String comment;

    public DrugDbVO() {
    }

    public DrugDbVO(String name, String comment) {
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
        DrugDbVO drug = (DrugDbVO) o;
        return Objects.equals(name, drug.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
