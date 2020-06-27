package io.edukativ.myskoolin.infrastructure.common.vo;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

public class AcademicYearDbVO implements Serializable {

    public static final String MONGO_FIELD_START_YEAR = "start_year";
    public static final String MONGO_FIELD_END_YEAR = "end_year";
    public static final String MONGO_FIELD_START_DATE_MIN = "start_date_min";
    public static final String MONGO_FIELD_START_DATE_MAX = "start_date_max";
    public static final String MONGO_FIELD_END_DATE = "end_date";
    @Field(MONGO_FIELD_START_YEAR)
    private int startYear;

    @Field(MONGO_FIELD_END_YEAR)
    private int endYear;

    @Field(MONGO_FIELD_START_DATE_MIN)
    private ZonedDateTime startDateMin;

    @Field(MONGO_FIELD_START_DATE_MAX)
    private ZonedDateTime startDateMax;

    @Field(MONGO_FIELD_END_DATE)
    private ZonedDateTime endDate;

    public AcademicYearDbVO(int startYear, int endYear, ZonedDateTime startDateMin, ZonedDateTime startDateMax, ZonedDateTime endDate) {
        this.startYear = startYear;
        this.endYear = endYear;
        this.startDateMin = startDateMin;
        this.startDateMax = startDateMax;
        this.endDate = endDate;
    }

    public AcademicYearDbVO() {
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public ZonedDateTime getStartDateMin() {
        return startDateMin;
    }

    public void setStartDateMin(ZonedDateTime startDateMin) {
        this.startDateMin = startDateMin;
    }

    public ZonedDateTime getStartDateMax() {
        return startDateMax;
    }

    public void setStartDateMax(ZonedDateTime startDateMax) {
        this.startDateMax = startDateMax;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public AcademicYearDbVO(AcademicYearDbVO year) {
        this.startYear = year.getStartYear();
        this.endYear = year.getEndYear();
        this.startDateMin = year.getStartDateMin();
        this.startDateMax = year.getStartDateMax();
        this.endDate = year.getEndDate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicYearDbVO that = (AcademicYearDbVO) o;
        return startYear == that.startYear &&
                endYear == that.endYear &&
                startDateMin.equals(that.startDateMin) &&
                Objects.equals(startDateMax, that.startDateMax) &&
                endDate.equals(that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startYear, endYear, startDateMin, startDateMax, endDate);
    }

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "AcademicYear{" +
                    "startYear=" + startYear +
                    ", endYear=" + endYear +
                    ", startDateMin=" + startDateMin +
                    ", startDateMax=" + startDateMax +
                    ", endDate=" + endDate +
                    '}';
        }
    }
}
