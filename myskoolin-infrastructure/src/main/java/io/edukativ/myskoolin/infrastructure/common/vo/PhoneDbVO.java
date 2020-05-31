package io.edukativ.myskoolin.infrastructure.common.vo;

import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

public class PhoneDbVO implements Serializable {

    public static final String MONGO_FIELD_NUMBER = "number";
    public static final String MONGO_FIELD_NAME = "name";
    @Field(MONGO_FIELD_NUMBER)
    private String number;
    @Field(MONGO_FIELD_NAME)
    private String name;

    public PhoneDbVO() {
    }

    public PhoneDbVO(String number, String name) {
        this.number = number;
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
