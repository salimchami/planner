package io.edukativ.myskoolin.infrastructure.schooling.vo;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

public class SchoolingInfosVO implements Serializable {

    private Boolean individualizedReceptionPlan = false;
    private String individualizedReceptionPlanContent;
    private Boolean personalizedSchoolingPlan = false;
    private String personalizedSchoolingPlanContent;
    private transient ZonedDateTime entryDate;
    private transient ZonedDateTime exitDate;
    private String exitReason;
    private String nationalNumber;
    private String originEstablishment;
    private boolean redoubling = false;
    private List<SanctionVO> sanctions;
    private List<AbsenceVO> absences;

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

    public List<SanctionVO> getSanctions() {
        return sanctions;
    }

    public void setSanctions(List<SanctionVO> sanctions) {
        this.sanctions = sanctions;
    }

    public List<AbsenceVO> getAbsences() {
        return absences;
    }

    public void setAbsences(List<AbsenceVO> absences) {
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
        SchoolingInfosVO that = (SchoolingInfosVO) o;
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
