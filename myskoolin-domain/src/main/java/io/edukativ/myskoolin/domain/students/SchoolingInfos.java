package io.edukativ.myskoolin.domain.students;

import java.util.Objects;

public class SchoolingInfos {

    private Boolean individualizedReceptionPlan = false;
    private String individualizedReceptionPlanContent;
    private Boolean personalizedSchoolingPlan = false;
    private String personalizedSchoolingPlanContent;
    private boolean redoubling = false;

    public SchoolingInfos() {
    }

    public SchoolingInfos(Boolean individualizedReceptionPlan, String individualizedReceptionPlanContent, Boolean personalizedSchoolingPlan,
                          String personalizedSchoolingPlanContent,
                          boolean redoubling) {
        this.individualizedReceptionPlan = individualizedReceptionPlan;
        this.individualizedReceptionPlanContent = individualizedReceptionPlanContent;
        this.personalizedSchoolingPlan = personalizedSchoolingPlan;
        this.personalizedSchoolingPlanContent = personalizedSchoolingPlanContent;
        this.redoubling = redoubling;
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

    public boolean isRedoubling() {
        return redoubling;
    }

    public void setRedoubling(boolean redoubling) {
        this.redoubling = redoubling;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolingInfos that = (SchoolingInfos) o;
        return individualizedReceptionPlan.equals(that.individualizedReceptionPlan) &&
                personalizedSchoolingPlan.equals(that.personalizedSchoolingPlan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(individualizedReceptionPlan, personalizedSchoolingPlan);
    }
}
