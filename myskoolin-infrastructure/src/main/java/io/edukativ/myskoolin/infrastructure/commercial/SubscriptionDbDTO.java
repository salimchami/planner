package io.edukativ.myskoolin.infrastructure.commercial;

import io.edukativ.myskoolin.infrastructure.common.vo.AddressDbVO;
import io.edukativ.myskoolin.infrastructure.schooling.vo.AcademicYearDbVO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Subscription.
 */

@Document(collection = "subscription")
public class SubscriptionDbDTO implements Serializable {

    @Id
    private String id;

    @Field("school_name")
    private String schoolName;

    @Field("duns_number")
    private String dunsNumber;

    @Field("address")
    private AddressDbVO address;

    @Field("website")
    private String website;

    @Field("phone")
    private String phone;

    @Field("email")
    private String email;

    @Field("contractualised")
    private Boolean contractualised = false;

    @Field("lang_key")
    private String langKey;

    @Field("interlocutor")
    private InterlocutorDbDTO interlocutor;

    @Field("date")
    private ZonedDateTime date;

    @Field("academicYear")
    private AcademicYearDbVO academicYear;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressDbVO getAddress() {
        return address;
    }

    public void setAddress(AddressDbVO address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SubscriptionDbDTO subscription = (SubscriptionDbDTO) o;
        return !(subscription.id == null || id == null) && Objects.equals(id, subscription.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Subscription{" +
            "id=" + id +
            ", schoolName='" + schoolName + "'" +
            ", dunsNumber='" + dunsNumber + "'" +
            ", address='" + address + "'" +
            ", address='" + address + "'" +
            ", website='" + website + "'" +
            ", phone='" + phone + "'" +
            ", email='" + email + "'" +
            '}';
    }

    public InterlocutorDbDTO getInterlocutor() {
        return interlocutor;
    }

    public void setInterlocutor(InterlocutorDbDTO interlocutor) {
        this.interlocutor = interlocutor;
    }

    public Boolean getContractualised() {
        return contractualised;
    }

    public void setContractualised(Boolean contractualised) {
        this.contractualised = contractualised;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public String getDunsNumber() {
        return dunsNumber;
    }

    public void setDunsNumber(String dunsNumber) {
        this.dunsNumber = dunsNumber;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public AcademicYearDbVO getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(AcademicYearDbVO academicYear) {
        this.academicYear = academicYear;
    }
}
