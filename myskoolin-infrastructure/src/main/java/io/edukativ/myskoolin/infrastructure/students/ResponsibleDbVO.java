package io.edukativ.myskoolin.infrastructure.students;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.edukativ.myskoolin.infrastructure.common.vo.AddressDbVO;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Student responsible.
 */
public class ResponsibleDbVO implements Serializable {

    public static final String MONGO_FIELD_FIRST_NAME = "first_name";
    public static final String MONGO_FIELD_LAST_NAME = "last_name";
    public static final String MONGO_FIELD_GENDER = "gender";
    public static final String MONGO_FIELD_EMAIL = "email";
    public static final String MONGO_FIELD_BIRTH_DATE = "birth_date";
    public static final String MONGO_FIELD_SECONDARY_EMAIL = "secondary_email";
    public static final String MONGO_FIELD_PRO_EMAIL = "pro_email";
    public static final String MONGO_FIELD_ADDRESS = "address";
    public static final String MONGO_FIELD_PHONE = "phone";
    public static final String MONGO_FIELD_CELL_PHONE = "cell_phone";
    public static final String MONGO_FIELD_WORK_PHONE = "work_phone";
    public static final String MONGO_FIELD_WORK_CELL_PHONE = "work_cell_phone";
    public static final String MONGO_FIELD_STUDENT_RELATIONSHIP = "student_relationship";
    public static final String MONGO_FIELD_STUDENT_RELATIONSHIP_OTHER = "student_relationship_other";
    public static final String MONGO_FIELD_RESP_RELATIONSHIP = "resp_relationship";
    public static final String MONGO_FIELD_RESP_RELATIONSHIP_OTHER = "resp_relationship_other";
    public static final String MONGO_FIELD_PROFESSION = "profession";
    public static final String MONGO_FIELD_FAMILY_SITUATION = "family_situation";
    public static final String MONGO_FIELD_DEPENDENT_CHILDREN = "dependent_children";
    public static final String MONGO_FIELD_COMMENT = "comment";
    public static final String MONGO_FIELD_PARENT_ASSOCIATION = "parent_association";
    public static final String MONGO_FIELD_PARENT_ASSOCIATION_POSITION = "parent_association_position";
    public static final String MONGO_FIELD_RESP_ACCOUNT = "resp_account";
    public static final String MONGO_FIELD_NATIONALITY = "nationality";

    @Size(max = 50)
    @Field(MONGO_FIELD_FIRST_NAME)
    private String firstName;

    @Size(max = 50)
    @Field(MONGO_FIELD_LAST_NAME)
    private String lastName;

    @Field(MONGO_FIELD_GENDER)
    private String gender;

    @Field(MONGO_FIELD_NATIONALITY)
    private String nationality;

    @Field(MONGO_FIELD_RESP_ACCOUNT)
    private boolean respAccount;

    @Email
    @Field(MONGO_FIELD_EMAIL)
    private String email;

    @Field(MONGO_FIELD_BIRTH_DATE)
    private ZonedDateTime birthdate;

    @Email
    @Field(MONGO_FIELD_SECONDARY_EMAIL)
    private String secondaryEmail;

    @Email
    @Field(MONGO_FIELD_PRO_EMAIL)
    private String proEmail;

    @Field(MONGO_FIELD_ADDRESS)
    private AddressDbVO address;

    @Field(MONGO_FIELD_PHONE)
    private String phone;
    @Field(MONGO_FIELD_CELL_PHONE)
    private String cellPhone;
    @Field(MONGO_FIELD_WORK_PHONE)
    private String workPhone;
    @Field(MONGO_FIELD_WORK_CELL_PHONE)
    private String workCellPhone;

    @Field(MONGO_FIELD_STUDENT_RELATIONSHIP)
    private String studentRelationship;
    @Field(MONGO_FIELD_PROFESSION)
    private String profession;
    @Field(MONGO_FIELD_FAMILY_SITUATION)
    private String familySituation;
    @Field(MONGO_FIELD_DEPENDENT_CHILDREN)
    private Integer dependentChildren;

    @Field(MONGO_FIELD_COMMENT)
    private String comment;
    @Field(MONGO_FIELD_PARENT_ASSOCIATION)
    private Boolean parentsAssociation = Boolean.FALSE;
    @Field(MONGO_FIELD_PARENT_ASSOCIATION_POSITION)
    private String parentsAssociationPosition;

    public ResponsibleDbVO() {
    }

    public ResponsibleDbVO(String firstName, String lastName, String gender, String nationality, boolean respAccount, String email, ZonedDateTime birthdate, String secondaryEmail, String proEmail, AddressDbVO address, String phone, String cellPhone, String workPhone, String workCellPhone, String studentRelationship, String profession, String familySituation, Integer dependentChildren, String comment, Boolean parentsAssociation, String parentsAssociationPosition) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.nationality = nationality;
        this.respAccount = respAccount;
        this.email = email;
        this.birthdate = birthdate;
        this.secondaryEmail = secondaryEmail;
        this.proEmail = proEmail;
        this.address = address;
        this.phone = phone;
        this.cellPhone = cellPhone;
        this.workPhone = workPhone;
        this.workCellPhone = workCellPhone;
        this.studentRelationship = studentRelationship;
        this.profession = profession;
        this.familySituation = familySituation;
        this.dependentChildren = dependentChildren;
        this.comment = comment;
        this.parentsAssociation = parentsAssociation;
        this.parentsAssociationPosition = parentsAssociationPosition;
    }

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

    public AddressDbVO getAddress() {
        return address;
    }

    public void setAddress(AddressDbVO address) {
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
        ResponsibleDbVO that = (ResponsibleDbVO) o;
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
