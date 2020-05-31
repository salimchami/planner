package io.edukativ.myskoolin.infrastructure.schooling.vo;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

public class MedicalInfosDbVO implements Serializable {

    public static final String MONGO_FIELD_HEALTH_INSURANCE = "health_insurance";
    public static final String MONGO_FIELD_HEALTH_INSURANCE_NAME = "health_insurance_name";
    public static final String MONGO_FIELD_HEALTH_INSURANCE_REFERENCE = "health_insurance_reference";
    public static final String MONGO_FIELD_HEALTH_INSURANCE_CONTACT = "health_insurance_contact";
    public static final String MONGO_FIELD_COMPLEMENTARY_HEALTH_INSURANCE = "complementary_health_insurance";
    public static final String MONGO_FIELD_COMPLEMENTARY_HEALTH_INSURANCE_NAME = "complementary_health_insurance_name";
    public static final String MONGO_FIELD_COMPLEMENTARY_HEALTH_INSURANCE_REFERENCE = "complementary_health_insurance_reference";
    public static final String MONGO_FIELD_COMPLEMENTARY_HEALTH_INSURANCE_CONTACT = "complementary_health_insurance_contact";
    public static final String MONGO_FIELD_ALLERGIES = "allergies";
    public static final String MONGO_FIELD_HEALTH_COMMENT = "health_comment";
    public static final String MONGO_FIELD_KNOWN_DISEASES = "known_diseases";

    @Field(MONGO_FIELD_HEALTH_INSURANCE)
    private Boolean healthInsurance;

    @Field(MONGO_FIELD_HEALTH_INSURANCE_NAME)
    private String healthInsuranceName;

    @Field(MONGO_FIELD_HEALTH_INSURANCE_REFERENCE)
    private String healthInsuranceReference;

    @Field(MONGO_FIELD_HEALTH_INSURANCE_CONTACT)
    private String healthInsuranceContact;

    @Field(MONGO_FIELD_COMPLEMENTARY_HEALTH_INSURANCE)
    private Boolean complementaryHealthInsurance;

    @Field(MONGO_FIELD_COMPLEMENTARY_HEALTH_INSURANCE_NAME)
    private String complementaryHealthInsuranceName;

    @Field(MONGO_FIELD_COMPLEMENTARY_HEALTH_INSURANCE_REFERENCE)
    private String complementaryHealthInsuranceReference;

    @Field(MONGO_FIELD_COMPLEMENTARY_HEALTH_INSURANCE_CONTACT)
    private String complementaryHealthInsuranceContact;

    @Field(MONGO_FIELD_HEALTH_COMMENT)
    private String healthComment;

    @Field(MONGO_FIELD_ALLERGIES)
    private String allergies;

    @Field(MONGO_FIELD_KNOWN_DISEASES)
    private String knownDiseases;

    public MedicalInfosDbVO(Boolean healthInsurance, String healthInsuranceName, String healthInsuranceReference, String healthInsuranceContact, Boolean complementaryHealthInsurance, String complementaryHealthInsuranceName, String complementaryHealthInsuranceReference, String complementaryHealthInsuranceContact, String healthComment, String allergies, String knownDiseases) {
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

    public MedicalInfosDbVO() {
        this.complementaryHealthInsurance = false;
        this.healthInsurance = false;
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

    public String getKnownDiseases() {
        return knownDiseases;
    }

    public void setKnownDiseases(String knownDiseases) {
        this.knownDiseases = knownDiseases;
    }

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "MedicalInfos{" +
                    "healthInsurance=" + healthInsurance +
                    ", healthInsuranceName='" + healthInsuranceName + '\'' +
                    ", healthInsuranceReference='" + healthInsuranceReference + '\'' +
                    ", healthInsuranceContact='" + healthInsuranceContact + '\'' +
                    ", complementaryHealthInsurance=" + complementaryHealthInsurance +
                    ", complementaryHealthInsuranceName='" + complementaryHealthInsuranceName + '\'' +
                    ", complementaryHealthInsuranceReference='" + complementaryHealthInsuranceReference + '\'' +
                    ", complementaryHealthInsuranceContact='" + complementaryHealthInsuranceContact + '\'' +
                    ", healthComment='" + healthComment + '\'' +
                    ", allergies='" + allergies + '\'' +
                    ", knownDiseases='" + knownDiseases + '\'' +
                    '}';
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalInfosDbVO that = (MedicalInfosDbVO) o;
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
        return Objects.hash(healthInsurance, healthInsuranceName, healthInsuranceReference, healthInsuranceContact, complementaryHealthInsurance, complementaryHealthInsuranceName, complementaryHealthInsuranceReference, complementaryHealthInsuranceContact, healthComment, allergies, knownDiseases);
    }


}
