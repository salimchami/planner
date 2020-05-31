package io.edukativ.myskoolin.infrastructure.schooling.vo;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

public class ResidentialSchoolDbVO implements Serializable {

    public static final String MONGO_FIELD_REGISTERED = "registred";
    public static final String MONGO_FIELD_NAME = "name";
    public static final String MONGO_FIELD_PERIOD_START = "period_start";
    public static final String MONGO_FIELD_PERIOD_END = "period_end";

    @Field(MONGO_FIELD_REGISTERED)
    private Boolean registered;

    @Field(MONGO_FIELD_NAME)
    private String name;

    @Field(MONGO_FIELD_PERIOD_START)
    private ZonedDateTime periodStart;

    @Field(MONGO_FIELD_PERIOD_END)
    private ZonedDateTime periodEnd;

    public ResidentialSchoolDbVO() {
    }

    public ResidentialSchoolDbVO(Boolean registered, String name, ZonedDateTime periodStart, ZonedDateTime periodEnd) {
        this.registered = registered;
        this.name = name;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
    }

    public Boolean getRegistered() {
        return registered;
    }

    public void setRegistered(Boolean registered) {
        this.registered = registered;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(ZonedDateTime periodStart) {
        this.periodStart = periodStart;
    }

    public ZonedDateTime getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(ZonedDateTime periodEnd) {
        this.periodEnd = periodEnd;
    }

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "ResidentialSchool{" +
                    "registered=" + registered +
                    ", name='" + name + '\'' +
                    ", periodStart=" + periodStart +
                    ", periodEnd=" + periodEnd +
                    '}';
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResidentialSchoolDbVO that = (ResidentialSchoolDbVO) o;
        return registered.equals(that.registered) &&
                Objects.equals(name, that.name) &&
                Objects.equals(periodStart, that.periodStart) &&
                Objects.equals(periodEnd, that.periodEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registered, name, periodStart, periodEnd);
    }
}
