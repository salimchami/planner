package io.edukativ.myskoolin.infrastructure.common.vo;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

public class AcademicYearVO implements Serializable {

    private int startYear;
    private int endYear;
    private ZonedDateTime startDateMin;
    private ZonedDateTime startDateMax;
    private ZonedDateTime endDate;

    public AcademicYearVO() {
    }

    public AcademicYearVO(AcademicYearVO year) {
        this.startYear = year.getStartYear();
        this.endYear = year.getEndYear();
        this.startDateMin = year.getStartDateMin();
        this.startDateMax = year.getStartDateMax();
        this.endDate = year.getEndDate();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicYearVO that = (AcademicYearVO) o;
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
