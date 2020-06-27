package io.edukativ.myskoolin.domain.medical;

import java.util.Objects;

public class MedicalInfos {

    private Boolean healthInsurance;
    private String healthInsuranceName;
    private String healthInsuranceReference;
    private String healthInsuranceContact;
    private Boolean complementaryHealthInsurance;
    private String complementaryHealthInsuranceName;
    private String complementaryHealthInsuranceReference;
    private String complementaryHealthInsuranceContact;
    private String healthComment;
    private String allergies;
    private String knownDiseases;

    public MedicalInfos(Boolean healthInsurance, String healthInsuranceName, String healthInsuranceReference, String healthInsuranceContact, Boolean complementaryHealthInsurance, String complementaryHealthInsuranceName, String complementaryHealthInsuranceReference, String complementaryHealthInsuranceContact, String healthComment, String allergies, String knownDiseases) {
        this.healthInsurance = healthInsurance;
        this.healthInsuranceName = healthInsuranceName;
        this.healthInsuranceReference = healthInsuranceReference;
        this.healthInsuranceContact = healthInsuranceContact;
        this.complementaryHealthInsurance = complementaryHealthInsurance;
        this.complementaryHealthInsuranceName = complementaryHealthInsuranceName;
        this.complementaryHealthInsuranceReference = complementaryHealthInsuranceReference;
        this.complementaryHealthInsuranceContact = complementaryHealthInsuranceContact;
        this.healthComment = healthComment;
        this.allergies = allergies;
        this.knownDiseases = knownDiseases;
    }

    public MedicalInfos() {
        this.complementaryHealthInsurance = false;
        this.healthInsurance = false;
    }

    public Boolean getHealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance(Boolean healthInsurance) {
        this.healthInsurance = healthInsurance;
    }

    public String getHealthInsuranceName() {
        return healthInsuranceName;
    }

    public void setHealthInsuranceName(String healthInsuranceName) {
        this.healthInsuranceName = healthInsuranceName;
    }

    public String getHealthInsuranceReference() {
        return healthInsuranceReference;
    }

    public void setHealthInsuranceReference(String healthInsuranceReference) {
        this.healthInsuranceReference = healthInsuranceReference;
    }

    public String getHealthInsuranceContact() {
        return healthInsuranceContact;
    }

    public void setHealthInsuranceContact(String healthInsuranceContact) {
        this.healthInsuranceContact = healthInsuranceContact;
    }

    public Boolean getComplementaryHealthInsurance() {
        return complementaryHealthInsurance;
    }

    public void setComplementaryHealthInsurance(Boolean complementaryHealthInsurance) {
        this.complementaryHealthInsurance = complementaryHealthInsurance;
    }

    public String getComplementaryHealthInsuranceName() {
        return complementaryHealthInsuranceName;
    }

    public void setComplementaryHealthInsuranceName(String complementaryHealthInsuranceName) {
        this.complementaryHealthInsuranceName = complementaryHealthInsuranceName;
    }

    public String getComplementaryHealthInsuranceReference() {
        return complementaryHealthInsuranceReference;
    }

    public void setComplementaryHealthInsuranceReference(String complementaryHealthInsuranceReference) {
        this.complementaryHealthInsuranceReference = complementaryHealthInsuranceReference;
    }

    public String getComplementaryHealthInsuranceContact() {
        return complementaryHealthInsuranceContact;
    }

    public void setComplementaryHealthInsuranceContact(String complementaryHealthInsuranceContact) {
        this.complementaryHealthInsuranceContact = complementaryHealthInsuranceContact;
    }

    public String getHealthComment() {
        return healthComment;
    }

    public void setHealthComment(String healthComment) {
        this.healthComment = healthComment;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getKnownDiseases() {
        return knownDiseases;
    }

    public void setKnownDiseases(String knownDiseases) {
        this.knownDiseases = knownDiseases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalInfos that = (MedicalInfos) o;
        return healthInsurance.equals(that.healthInsurance) &&
                Objects.equals(healthInsuranceName, that.healthInsuranceName) &&
                Objects.equals(healthInsuranceReference, that.healthInsuranceReference) &&
                Objects.equals(healthInsuranceContact, that.healthInsuranceContact) &&
                complementaryHealthInsurance.equals(that.complementaryHealthInsurance) &&
                Objects.equals(complementaryHealthInsuranceName, that.complementaryHealthInsuranceName) &&
                Objects.equals(complementaryHealthInsuranceReference, that.complementaryHealthInsuranceReference) &&
                Objects.equals(complementaryHealthInsuranceContact, that.complementaryHealthInsuranceContact) &&
                Objects.equals(healthComment, that.healthComment) &&
                Objects.equals(allergies, that.allergies) &&
                Objects.equals(knownDiseases, that.knownDiseases);
    }

    @Override
    public int hashCode() {
        return Objects.hash(healthInsurance, healthInsuranceName, healthInsuranceReference, healthInsuranceContact,
                complementaryHealthInsurance, complementaryHealthInsuranceName, complementaryHealthInsuranceReference,
                complementaryHealthInsuranceContact, healthComment, allergies, knownDiseases);
    }
}
