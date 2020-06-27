package io.edukativ.myskoolin.infrastructure.students;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.edukativ.myskoolin.infrastructure.common.vo.AddressVO;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Student responsible.
 */
public class ResponsibleVO implements Serializable {

    private String firstName;
    private String lastName;
    private String gender;
    private String nationality;
    private boolean respAccount;
    private String email;
    private ZonedDateTime birthdate;
    private String secondaryEmail;
    private String proEmail;
    private AddressVO address;
    private String phone;
    private String cellPhone;
    private String workPhone;
    private String workCellPhone;
    private String studentRelationship;
    private String profession;
    private String familySituation;
    private Integer dependentChildren;
    private String comment;
    private Boolean parentsAssociation = Boolean.FALSE;
    private String parentsAssociationPosition;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ZonedDateTime getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(ZonedDateTime birthDate) {
        this.birthdate = birthDate;
    }

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    public String getProEmail() {
        return proEmail;
    }

    public void setProEmail(String proEmail) {
        this.proEmail = proEmail;
    }

    public AddressVO getAddress() {
        return address;
    }

    public void setAddress(AddressVO address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getWorkCellPhone() {
        return workCellPhone;
    }

    public void setWorkCellPhone(String workCellPhone) {
        this.workCellPhone = workCellPhone;
    }

    public String getStudentRelationship() {
        return studentRelationship;
    }

    public void setStudentRelationship(String studentRelationship) {
        this.studentRelationship = studentRelationship;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getFamilySituation() {
        return familySituation;
    }

    public void setFamilySituation(String familySituation) {
        this.familySituation = familySituation;
    }

    public Integer getDependentChildren() {
        return dependentChildren;
    }

    public void setDependentChildren(Integer dependentChildren) {
        this.dependentChildren = dependentChildren;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getParentsAssociation() {
        return parentsAssociation;
    }

    public void setParentsAssociation(Boolean parentsAssociation) {
        this.parentsAssociation = parentsAssociation;
    }

    public String getParentsAssociationPosition() {
        return parentsAssociationPosition;
    }

    public void setParentsAssociationPosition(String parentsAssociationPosition) {
        this.parentsAssociationPosition = parentsAssociationPosition;
    }

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "Responsible{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", gender='" + gender + '\'' +
                    ", email='" + email + '\'' +
                    ", nationality='" + nationality + '\'' +
                    ", birthDate=" + birthdate +
                    ", secondaryEmail='" + secondaryEmail + '\'' +
                    ", proEmail='" + proEmail + '\'' +
                    ", address=" + address +
                    ", phone='" + phone + '\'' +
                    ", cellPhone='" + cellPhone + '\'' +
                    ", workPhone='" + workPhone + '\'' +
                    ", workCellPhone='" + workCellPhone + '\'' +
                    ", studentRelationship='" + studentRelationship + '\'' +
                    ", profession='" + profession + '\'' +
                    ", familySituation='" + familySituation + '\'' +
                    ", dependentChildren=" + dependentChildren +
                    ", comment='" + comment + '\'' +
                    ", parentsAssociation=" + parentsAssociation +
                    ", parentsAssociationPosition='" + parentsAssociationPosition + '\'' +
                    '}';
        }
    }

    public boolean isRespAccount() {
        return respAccount;
    }

    public void setRespAccount(boolean respAccount) {
        this.respAccount = respAccount;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponsibleVO that = (ResponsibleVO) o;
        return respAccount == that.respAccount &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(nationality, that.nationality) &&
                Objects.equals(email, that.email) &&
                Objects.equals(birthdate, that.birthdate) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, gender, nationality, respAccount, email, birthdate, address);
    }
}
