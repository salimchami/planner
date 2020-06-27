package io.edukativ.myskoolin.infrastructure.medical;

import java.io.Serializable;

public class MedicalInfosVO implements Serializable {

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
}
