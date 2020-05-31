package io.edukativ.myskoolin.domain.vo;

import java.time.ZonedDateTime;
import java.util.Objects;

public class AcademicYear {

    private int startYear;
    private int endYear;
    private ZonedDateTime startDateMin;
    private ZonedDateTime startDateMax;
    private ZonedDateTime endDate;

    public AcademicYear(int startYear, int endYear, ZonedDateTime startDateMin, ZonedDateTime startDateMax, ZonedDateTime endDate) {
        this.startYear = startYear;
        this.endYear = endYear;
        this.startDateMin = startDateMin;
        this.startDateMax = startDateMax;
        this.endDate = endDate;
    }

    public AcademicYear() {
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

    public AcademicYear(AcademicYear year) {
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
        AcademicYear that = (AcademicYear) o;
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
}
