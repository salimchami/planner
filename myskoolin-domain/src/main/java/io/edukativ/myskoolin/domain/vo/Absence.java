package io.edukativ.myskoolin.domain.vo;

import java.time.ZonedDateTime;
import java.util.Objects;

public class Absence {

    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private String cause;
    private String comment;
    private Boolean causeFile;

    public Absence() {
    }

    public Absence(ZonedDateTime startDate, ZonedDateTime endDate, String cause, String comment, Boolean causeFile) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.cause = cause;
        this.comment = comment;
        this.causeFile = causeFile;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getCauseFile() {
        return causeFile;
    }

    public void setCauseFile(Boolean causeFile) {
        this.causeFile = causeFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Absence absence = (Absence) o;
        return startDate.equals(absence.startDate) &&
                endDate.equals(absence.endDate) &&
                cause.equals(absence.cause) &&
                Objects.equals(comment, absence.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, cause, comment);
    }


}
