package io.edukativ.myskoolin.infrastructure.students;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.edukativ.myskoolin.infrastructure.absences.AbsenceDbVO;
import io.edukativ.myskoolin.infrastructure.sanctions.SanctionDbVO;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

public class SchoolingInfosDbVO implements Serializable {

    public static final String MONGO_FIELD_INDIVIDUALIZED_RECEPTION_PLAN = "individualized_reception_plan";
    public static final String MONGO_FIELD_INDIVIDUALIZED_RECEPTION_PLAN_CONTENT = "individualized_reception_plan_content";
    public static final String MONGO_FIELD_PERSONALIZED_SCHOOLING_PLAN = "personalized_schooling_plan";
    public static final String MONGO_FIELD_PERSONALIZED_SCHOOLING_PLAN_CONTENT = "personalized_schooling_plan_content";
    public static final String MONGO_FIELD_ENTRY_DATE = "entry_date";
    public static final String MONGO_FIELD_EXIT_DATE = "exit_date";
    public static final String MONGO_FIELD_EXIT_REASON = "exit_reason";
    public static final String MONGO_FIELD_NATIONAL_NUMBER = "national_number";
    public static final String MONGO_FIELD_ORIGIN_ESTABLISHMENT = "origin_establishment";
    public static final String MONGO_FIELD_REDOUBLING = "redoubling";
    public static final String MONGO_FIELD_SANCTIONS = "sanctions";
    public static final String MONGO_FIELD_ABSENCES = "absences";

    @Field(MONGO_FIELD_INDIVIDUALIZED_RECEPTION_PLAN)
    private Boolean individualizedReceptionPlan = false;

    @Field(MONGO_FIELD_INDIVIDUALIZED_RECEPTION_PLAN_CONTENT)
    private String individualizedReceptionPlanContent;

    @Field(MONGO_FIELD_PERSONALIZED_SCHOOLING_PLAN)
    private Boolean personalizedSchoolingPlan = false;

    @Field(MONGO_FIELD_PERSONALIZED_SCHOOLING_PLAN_CONTENT)
    private String personalizedSchoolingPlanContent;

    @Field(MONGO_FIELD_ENTRY_DATE)
    @NotNull
    private transient ZonedDateTime entryDate;

    @Field(MONGO_FIELD_EXIT_DATE)
    private transient ZonedDateTime exitDate;

    @Field(MONGO_FIELD_EXIT_REASON)
    private String exitReason;

    @Field(MONGO_FIELD_NATIONAL_NUMBER)
    private String nationalNumber;

    @Field(MONGO_FIELD_ORIGIN_ESTABLISHMENT)
    private String originEstablishment;

    @Field(MONGO_FIELD_REDOUBLING)
    private boolean redoubling = false;

    @Field(MONGO_FIELD_SANCTIONS)
    private List<SanctionDbVO> sanctions;

    @Field(MONGO_FIELD_ABSENCES)
    private List<AbsenceDbVO> absences;

    public SchoolingInfosDbVO() {
    }

    public SchoolingInfosDbVO(Boolean individualizedReceptionPlan, String individualizedReceptionPlanContent, Boolean personalizedSchoolingPlan,
                              String personalizedSchoolingPlanContent, ZonedDateTime entryDate, ZonedDateTime exitDate, String exitReason,
                              String nationalNumber, String originEstablishment, boolean redoubling, List<SanctionDbVO> sanctions, List<AbsenceDbVO> absences) {
        this.individualizedReceptionPlan = individualizedReceptionPlan;
        this.individualizedReceptionPlanContent = individualizedReceptionPlanContent;
        this.personalizedSchoolingPlan = personalizedSchoolingPlan;
        this.personalizedSchoolingPlanContent = personalizedSchoolingPlanContent;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
        this.exitReason = exitReason;
        this.nationalNumber = nationalNumber;
        this.originEstablishment = originEstablishment;
        this.redoubling = redoubling;
        this.sanctions = sanctions;
        this.absences = absences;
    }

    public SchoolingInfosDbVO(ZonedDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public Boolean getIndividualizedReceptionPlan() {
        return individualizedReceptionPlan;
    }

    public void setIndividualizedReceptionPlan(Boolean individualizedReceptionPlan) {
        this.individualizedReceptionPlan = individualizedReceptionPlan;
    }

    public String getIndividualizedReceptionPlanContent() {
        return individualizedReceptionPlanContent;
    }

    public void setIndividualizedReceptionPlanContent(String individualizedReceptionPlanContent) {
        this.individualizedReceptionPlanContent = individualizedReceptionPlanContent;
    }

    public Boolean getPersonalizedSchoolingPlan() {
        return personalizedSchoolingPlan;
    }

    public void setPersonalizedSchoolingPlan(Boolean personalizedSchoolingPlan) {
        this.personalizedSchoolingPlan = personalizedSchoolingPlan;
    }

    public String getPersonalizedSchoolingPlanContent() {
        return personalizedSchoolingPlanContent;
    }

    public void setPersonalizedSchoolingPlanContent(String personalizedSchoolingPlanContent) {
        this.personalizedSchoolingPlanContent = personalizedSchoolingPlanContent;
    }

    public ZonedDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(ZonedDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public ZonedDateTime getExitDate() {
        return exitDate;
    }

    public void setExitDate(ZonedDateTime exitDate) {
        this.exitDate = exitDate;
    }

    public String getExitReason() {
        return exitReason;
    }

    public void setExitReason(String exitReason) {
        this.exitReason = exitReason;
    }

    public String getNationalNumber() {
        return nationalNumber;
    }

    public void setNationalNumber(String nationalNumber) {
        this.nationalNumber = nationalNumber;
    }

    public String getOriginEstablishment() {
        return originEstablishment;
    }

    public void setOriginEstablishment(String originEstablishment) {
        this.originEstablishment = originEstablishment;
    }

    public boolean isRedoubling() {
        return redoubling;
    }

    public void setRedoubling(boolean redoubling) {
        this.redoubling = redoubling;
    }

    public List<SanctionDbVO> getSanctions() {
        return sanctions;
    }

    public void setSanctions(List<SanctionDbVO> sanctions) {
        this.sanctions = sanctions;
    }

    public List<AbsenceDbVO> getAbsences() {
        return absences;
    }

    public void setAbsences(List<AbsenceDbVO> absences) {
        this.absences = absences;
    }

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "SchoolingInfos{" +
                    "individualizedReceptionPlan=" + individualizedReceptionPlan +
                    ", individualizedReceptionPlanContent='" + individualizedReceptionPlanContent + '\'' +
                    ", personalizedSchoolingPlan=" + personalizedSchoolingPlan +
                    ", personalizedSchoolingPlanContent='" + personalizedSchoolingPlanContent + '\'' +
                    ", entryDate=" + entryDate +
                    ", exitDate=" + exitDate +
                    ", exitReason='" + exitReason + '\'' +
                    ", nationalNumber='" + nationalNumber + '\'' +
                    ", originEstablishment='" + originEstablishment + '\'' +
                    ", redoubling=" + redoubling +
                    ", sanctions=" + sanctions +
                    ", absences=" + absences +
                    '}';
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolingInfosDbVO that = (SchoolingInfosDbVO) o;
        return individualizedReceptionPlan.equals(that.individualizedReceptionPlan) &&
                personalizedSchoolingPlan.equals(that.personalizedSchoolingPlan) &&
                entryDate.equals(that.entryDate) &&
                nationalNumber.equals(that.nationalNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(individualizedReceptionPlan, personalizedSchoolingPlan, entryDate, nationalNumber);
    }
}
