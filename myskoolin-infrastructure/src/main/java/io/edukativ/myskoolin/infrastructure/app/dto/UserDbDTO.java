package io.edukativ.myskoolin.infrastructure.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.edukativ.myskoolin.infrastructure.common.vo.AddressDbVO;
import io.edukativ.myskoolin.infrastructure.config.Constants;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A user.
 */
@org.springframework.data.mongodb.core.mapping.Document(collection = "users")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "user")
public class UserDbDTO extends AbstractAuditingEntity implements Serializable {

    public static final String MONGO_COLLECTION_NAME = "users";
    private static final long serialVersionUID = 1L;
    public static final String MONGO_FIELD_LOGIN = "login";
    public static final String MONGO_FIELD_CLIENT_ID = "clientId";
    public static final String MONGO_FIELD_PASSWORD = "password";
    public static final String MONGO_FIELD_GENDER = "sex";
    public static final String MONGO_FIELD_FIRST_NAME = "first_name";
    public static final String MONGO_FIELD_LAST_NAME = "last_name";
    public static final String MONGO_FIELD_CELL_PHONE = "cell_phone";
    public static final String MONGO_FIELD_HOME_PHONE = "home_phone";
    public static final String MONGO_FIELD_NATIONALITY = "nationality";
    public static final String MONGO_FIELD_EMAIL = "email";
    public static final String MONGO_FIELD_ACTIVATED = "activated";
    public static final String MONGO_FIELD_LANG_KEY = "lang_key";
    public static final String MONGO_FIELD_IMAGE_URL = "image_url";
    public static final String MONGO_FIELD_ACTIVATION_KEY = "activation_key";
    public static final String MONGO_FIELD_RESET_KEY = "reset_key";
    public static final String MONGO_FIELD_RESET_DATE = "reset_date";
    public static final String MONGO_FIELD_ADDRESS = "address";
    public static final String MONGO_FIELD_STUDENT = "student";
    public static final String MONGO_FIELD_BIRTH_DATE = "birth_date";
    public static final String MONGO_FIELD_TEACHER = "teacher";
    public static final String MONGO_FIELD_DELETED = "deleted";
    public static final String MONGO_FIELD_ARCHIVED = "archived";

    @Id
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private String id;

    @Indexed
    @Field(MONGO_FIELD_CLIENT_ID)
    private ObjectId clientId;

    @NotNull
    @TextIndexed
    @Size(min = 1, max = 50)
    @Field(MONGO_FIELD_LOGIN)
    @Pattern(regexp = Constants.LOGIN_REGEX)
    private String login;

    @NotNull
    @JsonIgnore
    @Size(min = 60, max = 60)
    @Field(MONGO_FIELD_PASSWORD)
    private String password;

    @Field(MONGO_FIELD_GENDER)
    private String gender;

    @TextIndexed
    @Size(max = 50)
    @Field(MONGO_FIELD_FIRST_NAME)
    private String firstName;

    @TextIndexed
    @Size(max = 50)
    @Field(MONGO_FIELD_LAST_NAME)
    private String lastName;

    @Field(MONGO_FIELD_CELL_PHONE)
    private String cellPhone;

    @Field(MONGO_FIELD_HOME_PHONE)
    private String homePhone;

    @Field(MONGO_FIELD_NATIONALITY)
    private String nationality;

    @Email
    @Size(min = 5, max = 254)
    @Indexed
    @Field(MONGO_FIELD_EMAIL)
    private String email;

    @Field(MONGO_FIELD_ACTIVATED)
    private boolean activated = false;

    @Size(min = 2, max = 10)
    @Field(MONGO_FIELD_LANG_KEY)
    private String langKey;

    @Size(max = 256)
    @Field(MONGO_FIELD_IMAGE_URL)
    private String imageUrl;

    @JsonIgnore
    @Size(max = 20)
    @Field(MONGO_FIELD_ACTIVATION_KEY)
    private String activationKey;

    @JsonIgnore
    @Size(max = 20)
    @Field(MONGO_FIELD_RESET_KEY)
    private String resetKey;

    @Field(MONGO_FIELD_RESET_DATE)
    private Instant resetDate = null;

    @JsonIgnore
    private Set<AuthorityDbDTO> authorities = new HashSet<>();

    @Field(MONGO_FIELD_ADDRESS)
    private AddressDbVO address;

    @Field(MONGO_FIELD_BIRTH_DATE)
    private ZonedDateTime birthDate;

    @Field(MONGO_FIELD_DELETED)
    private boolean deleted;

    @Field(MONGO_FIELD_ARCHIVED)
    private boolean archived;

    public UserDbDTO(String id, @NotNull @Size(min = 1, max = 50) @Pattern(regexp = Constants.LOGIN_REGEX) String login,
                     ObjectId clientId, @NotNull @Size(min = 60, max = 60) String password, String gender,
                     @Size(max = 50) String firstName, @Size(max = 50) String lastName, String cellPhone,
                     String homePhone, String nationality, @Email @Size(min = 5, max = 254) String email,
                     @Size(min = 2, max = 10) String langKey, @Size(max = 256) String imageUrl,
                     @Size(max = 20) String activationKey, @Size(max = 20) String resetKey, Instant resetDate,
                     Set<AuthorityDbDTO> authorities, AddressDbVO address, ZonedDateTime birthDate) {
        this.id = id;
        this.login = login;
        this.clientId = clientId;
        this.password = password;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cellPhone = cellPhone;
        this.homePhone = homePhone;
        this.nationality = nationality;
        this.email = email;
        this.langKey = langKey;
        this.imageUrl = imageUrl;
        this.activationKey = activationKey;
        this.resetKey = resetKey;
        this.resetDate = resetDate;
        this.authorities = authorities;
        this.address = address;
        this.birthDate = birthDate;
    }

    public UserDbDTO() {
    }

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public ObjectId getClientId() {
        return clientId;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getNationality() {
        return nationality;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActivated() {
        return activated;
    }

    public String getLangKey() {
        return langKey;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public String getResetKey() {
        return resetKey;
    }

    public Instant getResetDate() {
        return resetDate;
    }

    public Set<AuthorityDbDTO> getAuthorities() {
        return authorities;
    }

    public AddressDbVO getAddress() {
        return address;
    }

    public ZonedDateTime getBirthDate() {
        return birthDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setClientId(ObjectId clientId) {
        this.clientId = clientId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    public void setResetDate(Instant resetDate) {
        this.resetDate = resetDate;
    }

    public void setAuthorities(Set<AuthorityDbDTO> authorities) {
        this.authorities = authorities;
    }

    public void setAddress(AddressDbVO address) {
        this.address = address;
    }

    public void setBirthDate(ZonedDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserDbDTO)) {
            return false;
        }
        return id != null && id.equals(((UserDbDTO) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "User{" +
            "login='" + login + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", imageUrl='" + imageUrl + '\'' +
            ", activated='" + activated + '\'' +
            ", langKey='" + langKey + '\'' +
            ", activationKey='" + activationKey + '\'' +
            "}";
    }
}
