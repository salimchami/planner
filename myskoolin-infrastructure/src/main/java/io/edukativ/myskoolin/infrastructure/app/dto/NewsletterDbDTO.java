package io.edukativ.myskoolin.infrastructure.app.dto;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Newsletter.
 */

@Document(collection = "newsletter")
public class NewsletterDbDTO implements Serializable {

    @Id
    private String id;

    @Field("email")
    private String email;

    @Field("confirm_token")
    private String confirmToken;

    @Field("confirm_token_expires")
    private ZonedDateTime confirmTokenExpires;

    @Field("confirmed")
    private Boolean confirmed;

    private ObjectId userId;

    @Field("lang_key")
    private String langKey;


    /**
     * Default constructor.
     */
    public NewsletterDbDTO() {
        this.confirmed = false;
    }

//    /**
//     * constructor with newsletter dto parameter.
//     *
//     * @param newsletterDto newsletter dto
//     */
//    public Newsletter(NewsletterDTO newsletterDto) {
//        this.confirmToken = newsletterDto.getConfirmToken();
//        this.email = newsletterDto.getEmail();
//        this.confirmToken = newsletterDto.getConfirmToken();
//        this.confirmed = newsletterDto.getConfirmed();
//        this.langKey = newsletterDto.getLangKey();
//    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmToken() {
        return confirmToken;
    }

    public void setConfirmToken(String confirmToken) {
        this.confirmToken = confirmToken;
    }

    public ZonedDateTime getConfirmTokenExpires() {
        return confirmTokenExpires;
    }

    public void setConfirmTokenExpires(ZonedDateTime confirmTokenExpires) {
        this.confirmTokenExpires = confirmTokenExpires;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NewsletterDbDTO newsletter = (NewsletterDbDTO) o;
        return !(newsletter.id == null || id == null) && Objects.equals(id, newsletter.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Newsletter{" +
            "id=" + id +
            ", email='" + email + "'" +
            ", confirmToken='" + confirmToken + "'" +
            ", confirmTokenExpires='" + confirmTokenExpires + "'" +
            ", confirmed='" + confirmed + "'" +
            ", userId='" + userId + "'" +
            '}';
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public void update(String email, String confirmToken, String langKey) {
        this.email = email;
        this.confirmToken = confirmToken;
        this.langKey = langKey;
    }
}
