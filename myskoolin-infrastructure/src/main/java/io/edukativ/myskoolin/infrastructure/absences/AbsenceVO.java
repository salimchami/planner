package io.edukativ.myskoolin.infrastructure.absences;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class AbsenceVO implements Serializable {

    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private String cause;
    private String comment;
    private Boolean causeFile;

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
}
