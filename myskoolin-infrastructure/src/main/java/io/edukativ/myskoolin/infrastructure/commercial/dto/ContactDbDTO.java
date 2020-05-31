package io.edukativ.myskoolin.infrastructure.commercial.dto;

import io.edukativ.myskoolin.infrastructure.common.enums.EnumGender;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumSex;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Contact.
 */

@Document(collection = "contact")
public class ContactDbDTO implements Serializable {

    @Id
    private String id;

    @Field("type")
    private String type;

    @Field("sex")
    private String sex;

    @Field("gender")
    private String gender;

    private String translatedGender;

    @Field("name")
    private String name;

    @Field("email")
    private String email;

    @Field("message")
    private String message;

    @Field("lang_key")
    private String langKey;

    @Field("phone")
    private String phone;

    @Field("date")
    private ZonedDateTime date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
        if (this.sex == null) {
            EnumGender currentGender = EnumGender.fromCode(gender);
            switch (currentGender) {
                case MISS:
                case MRS:
                    setSex(EnumSex.FEMALE.getCode());
                    break;
                case MR:
                default:
                    setSex(EnumSex.MALE.getCode());
                    break;
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ContactDbDTO contact = (ContactDbDTO) o;
        return Objects.equals(id, contact.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", type='" + type + "'" +
                ", gender='" + gender + "'" +
                ", sex='" + sex + "'" +
                ", name='" + name + "'" +
                ", email='" + email + "'" +
                ", message='" + message + "'" +
                '}';
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTranslatedGender() {
        return translatedGender;
    }

    public void setTranslatedGender(String translatedGender) {
        this.translatedGender = translatedGender;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
        if (this.gender == null) {
            EnumSex currentSex = EnumSex.fromCode(sex);
            switch (currentSex) {
                case MALE:
                    setGender(EnumGender.MR.getCode());
                    break;
                case FEMALE:
                    setGender(EnumGender.MRS.getCode());
                    break;
            }
        }

    }
}
