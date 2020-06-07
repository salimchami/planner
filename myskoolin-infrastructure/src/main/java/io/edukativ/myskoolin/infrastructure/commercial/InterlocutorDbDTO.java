package io.edukativ.myskoolin.infrastructure.commercial;

import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * A Interlocutor.
 */
public class InterlocutorDbDTO implements Serializable {

    public static final String MONGO_FIELD_LAST_NAME = "last_name";
    public static final String MONGO_FIELD_FIRST_NAME = "first_name";
    public static final String MONGO_FIELD_GENDER = "gender";
    public static final String MONGO_FIELD_SEX = "sex";
    public static final String MONGO_FIELD_POSITION = "position";
    public static final String MONGO_FIELD_PHONE = "phone";
    public static final String MONGO_FIELD_MOBILE = "mobile";
    public static final String MONGO_FIELD_EMAIL = "email";

    @Field(MONGO_FIELD_LAST_NAME)
    private String lastname;

    @Field(MONGO_FIELD_FIRST_NAME)
    private String firstname;

    @Field(MONGO_FIELD_GENDER)
    private String gender;

    @Field(MONGO_FIELD_SEX)
    private String sex;

    @Field(MONGO_FIELD_POSITION)
    private String position;

    @Field(MONGO_FIELD_PHONE)
    private String phone;

    @Field(MONGO_FIELD_MOBILE)
    private String mobile;

    @Field(MONGO_FIELD_EMAIL)
    private String email;

    public InterlocutorDbDTO() {
    }

    public InterlocutorDbDTO(String lastname, String firstname, String gender, String sex, String position, String phone, String mobile, String email) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.gender = gender;
        this.position = position;
        this.phone = phone;
        this.mobile = mobile;
        this.email = email;
        this.sex = sex;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
