package io.edukativ.myskoolin.infrastructure.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.edukativ.myskoolin.infrastructure.common.vo.AddressDbVO;
import io.edukativ.myskoolin.infrastructure.config.Constants;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.*;
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
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * A user.
 */
public abstract class AbstractUserDbDTO extends AbstractAuditingEntity implements Serializable {

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
    public static final String MONGO_FIELD_BIRTH_DATE = "birth_date";
    public static final String MONGO_FIELD_DELETED = "deleted";
    public static final String MONGO_FIELD_ARCHIVED = "archived";

    @Id
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    protected String id;

    @Indexed
    @Field(MONGO_FIELD_CLIENT_ID)
    protected ObjectId clientId;

    @NotNull
    @TextIndexed
    @Size(min = 1, max = 50)
    @Field(MONGO_FIELD_LOGIN)
    @Pattern(regexp = Constants.LOGIN_REGEX)
    protected String login;

    @NotNull
    @JsonIgnore
    @Size(min = 60, max = 60)
    @Field(MONGO_FIELD_PASSWORD)
    protected String password;

    @Field(MONGO_FIELD_GENDER)
    protected String gender;

    @TextIndexed
    @Size(max = 50)
    @Field(MONGO_FIELD_FIRST_NAME)
    protected String firstName;

    @TextIndexed
    @Size(max = 50)
    @Field(MONGO_FIELD_LAST_NAME)
    protected String lastName;

    @Field(MONGO_FIELD_CELL_PHONE)
    protected String cellPhone;

    @Field(MONGO_FIELD_HOME_PHONE)
    protected String homePhone;

    @Field(MONGO_FIELD_NATIONALITY)
    protected String nationality;

    @Email
    @Size(min = 5, max = 254)
    @Indexed
    @Field(MONGO_FIELD_EMAIL)
    protected String email;

    @Field(MONGO_FIELD_ACTIVATED)
    private boolean activated;

    @Size(min = 2, max = 10)
    @Field(MONGO_FIELD_LANG_KEY)
    protected String langKey;

    @Size(max = 256)
    @Field(MONGO_FIELD_IMAGE_URL)
    protected String imageUrl;

    @JsonIgnore
    @Size(max = 20)
    @Field(MONGO_FIELD_ACTIVATION_KEY)
    protected String activationKey;

    @JsonIgnore
    @Size(max = 20)
    @Field(MONGO_FIELD_RESET_KEY)
    protected String resetKey;

    @Field(MONGO_FIELD_RESET_DATE)
    protected Instant resetDate;

    @JsonIgnore
    protected Set<AuthorityDbDTO> authorities;

    @Field(MONGO_FIELD_ADDRESS)
    protected AddressDbVO address;

    @Field(MONGO_FIELD_BIRTH_DATE)
    protected ZonedDateTime birthDate;

    @Field(MONGO_FIELD_DELETED)
    private boolean deleted;

    @Field(MONGO_FIELD_ARCHIVED)
    private boolean archived;

    protected AbstractUserDbDTO() {
    }

    protected AbstractUserDbDTO(AbstractUserDbDTOBuilder<?, ?> builder) {
        this.id = builder.id;
        this.login = builder.login;
        this.clientId = builder.clientId;
        this.password = builder.password;
        this.gender = builder.gender;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.cellPhone = builder.cellPhone;
        this.homePhone = builder.homePhone;
        this.nationality = builder.nationality;
        this.email = builder.email;
        this.langKey = builder.langKey;
        this.imageUrl = builder.imageUrl;
        this.activationKey = builder.activationKey;
        this.activated = builder.activated;
        this.resetKey = builder.resetKey;
        this.resetDate = builder.resetDate;
        this.authorities = builder.authorities;
        this.address = builder.address;
        this.birthDate = builder.birthDate;
        this.setCreatedBy(builder.createdBy);
        this.setCreatedDate(builder.createdDate);
        this.setLastModifiedBy(builder.lastModifiedBy);
        this.setLastModifiedDate(builder.lastModifiedDate);
        this.deleted = builder.deleted;
        this.archived = builder.archived;

    }

    public abstract static class AbstractUserDbDTOBuilder<T extends AbstractUserDbDTOBuilder<T, U>, U extends AbstractUserDbDTO> {

        protected String id;
        protected ObjectId clientId;
        protected String login;
        protected String password;
        protected String gender;
        protected String firstName;
        protected String lastName;
        protected String cellPhone;
        protected String homePhone;
        protected String nationality;
        protected String email;
        protected boolean activated;
        protected String langKey;
        protected String imageUrl;
        protected String activationKey;
        protected String resetKey;
        protected Instant resetDate;
        protected Set<AuthorityDbDTO> authorities;
        protected AddressDbVO address;
        protected ZonedDateTime birthDate;
        protected boolean deleted;
        protected boolean archived;

        protected String createdBy;
        protected Instant createdDate = Instant.now();
        protected String lastModifiedBy;
        protected Instant lastModifiedDate = Instant.now();

        public AbstractUserDbDTOBuilder<T, U> lastModifiedDate(Instant lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
            return this;
        }

        public AbstractUserDbDTOBuilder<T, U> createdDate(Instant createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public AbstractUserDbDTOBuilder<T, U> createdBy(String createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public AbstractUserDbDTOBuilder<T, U> lastModifiedBy(String lastModifiedBy) {
            this.lastModifiedBy = lastModifiedBy;
            return this;
        }

        public AbstractUserDbDTOBuilder<T, U> id(String id) {
            this.id = id;
            return this;
        }

        public AbstractUserDbDTOBuilder<T, U> clientId(ObjectId clientId) {
            this.clientId = clientId;
            return this;
        }

        public AbstractUserDbDTOBuilder<T, U> login(@NotNull @Size(min = 1, max = 50) @Pattern(regexp = Constants.LOGIN_REGEX) String login) {
            this.login = login;
            return this;
        }

        public AbstractUserDbDTOBuilder<T, U> password(@NotNull @Size(min = 60, max = 60) String password) {
            this.password = password;
            return this;
        }

        public AbstractUserDbDTOBuilder<T, U> gender(String gender) {
            this.gender = gender;
            return this;
        }

        public AbstractUserDbDTOBuilder<T, U> firstName(@Size(max = 50) String firstName) {
            this.firstName = firstName;
            return this;
        }

        public AbstractUserDbDTOBuilder<T, U> lastName(@Size(max = 50) String lastName) {
            this.lastName = lastName;
            return this;
        }

        public AbstractUserDbDTOBuilder<T, U> cellPhone(String cellPhone) {
            this.cellPhone = cellPhone;
            return this;
        }

        public AbstractUserDbDTOBuilder<T, U> homePhone(String homePhone) {
            this.homePhone = homePhone;
            return this;
        }

        public AbstractUserDbDTOBuilder<T, U> nationality(String nationality) {
            this.nationality = nationality;
            return this;
        }

        public AbstractUserDbDTOBuilder<T, U> email(@Email @Size(min = 5, max = 254) String email) {
            if (email != null) {
                this.email = email.toLowerCase();
            }
            return this;
        }

        public AbstractUserDbDTOBuilder<T, U> activated(boolean activated) {
            this.activated = activated;
            return this;
        }

        public AbstractUserDbDTOBuilder<T, U> langKey(@Size(min = 2, max = 10) String langKey) {
            this.langKey = Objects.requireNonNullElse(langKey, Constants.DEFAULT_LANGUAGE);
            return this;
        }

        public AbstractUserDbDTOBuilder<T, U> imageUrl(@Size(max = 256) String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public AbstractUserDbDTOBuilder<T, U> activationKey(@Size(max = 20) String activationKey) {
            this.activationKey = activationKey;
            return this;
        }

        public AbstractUserDbDTOBuilder<T, U> resetKey(@Size(max = 20) String resetKey) {
            this.resetKey = resetKey;
            return this;
        }

        public AbstractUserDbDTOBuilder<T, U> resetDate(Instant resetDate) {
            this.resetDate = resetDate;
            return this;
        }

        public AbstractUserDbDTOBuilder<T, U> authorities(Set<AuthorityDbDTO> authorities) {
            this.authorities = Objects.requireNonNullElse(authorities, Collections.emptySet());
            return this;
        }

        public AbstractUserDbDTOBuilder<T, U> address(AddressDbVO address) {
            this.address = address;
            return this;
        }

        public AbstractUserDbDTOBuilder<T, U> birthDate(ZonedDateTime birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public AbstractUserDbDTOBuilder<T, U> deleted(boolean deleted) {
            this.deleted = deleted;
            return this;
        }

        public AbstractUserDbDTOBuilder<T, U> archived(boolean archived) {
            this.archived = archived;
            return this;
        }

        public abstract U build();
    }

    public String getId() {
        return id;
    }

    public ObjectId getClientId() {
        return clientId;
    }

    public String getLogin() {
        return login;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractUserDbDTO)) {
            return false;
        }
        return id != null && id.equals(((AbstractUserDbDTO) o).id);
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
