package io.edukativ.myskoolin.infrastructure.schooling.vo;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

public class AbsenceDbVO implements Serializable {

    public static final String MONGO_FIELD_START_DATE = "start_date";
    public static final String MONGO_FIELD_END_DATE = "end_date";
    public static final String MONGO_FIELD_CAUSE = "cause";
    public static final String MONGO_FIELD_COMMENT = "comment";
    public static final String MONGO_FIELD_CAUSE_FILE = "cause_file";

    @Field(MONGO_FIELD_START_DATE)
    private ZonedDateTime startDate;

    @Field(MONGO_FIELD_END_DATE)
    private ZonedDateTime endDate;

    @Field(MONGO_FIELD_CAUSE)
    private String cause;

    @Field(MONGO_FIELD_COMMENT)
    private String comment;

    @Field(MONGO_FIELD_CAUSE_FILE)
    private Boolean causeFile;

    public AbsenceDbVO() {
    }

    public AbsenceDbVO(ZonedDateTime startDate, ZonedDateTime endDate, String cause, String comment, Boolean causeFile) {
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
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "Absence{" +
                    "startDate=" + startDate +
                    ", endDate=" + endDate +
                    ", cause='" + cause + '\'' +
                    ", comment='" + comment + '\'' +
                    ", causeFile=" + causeFile +
                    '}';
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbsenceDbVO absence = (AbsenceDbVO) o;
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
