package io.edukativ.myskoolin.infrastructure.app.dto;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A user.
 */
@Document(collection = "users")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "user")
public class UserDbDTO extends AbstractUserDbDTO {

    public UserDbDTO() {
    }

    protected UserDbDTO(UserDbDTOBuilder builder) {
        super(builder);
    }

    public static class UserDbDTOBuilder extends AbstractUserDbDTOBuilder<UserDbDTOBuilder, UserDbDTO> {

        public UserDbDTOBuilder() {
        }

        public UserDbDTOBuilder(UserDbDTO user) {
            this.id = user.getId();
            this.login = user.getLogin();
            this.clientId = user.getClientId();
            this.password = user.getPassword();
            this.gender = user.getGender();
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
            this.cellPhone = user.getCellPhone();
            this.homePhone = user.getHomePhone();
            this.nationality = user.getNationality();
            this.email = user.getEmail();
            this.langKey = user.getLangKey();
            this.imageUrl = user.getImageUrl();
            this.activationKey = user.getActivationKey();
            this.resetKey = user.getResetKey();
            this.resetDate = user.getResetDate();
            this.authorities = user.getAuthorities();
            this.address = user.getAddress();
            this.birthDate = user.getBirthDate();
        }

        @Override
        public UserDbDTO build() {
            return new UserDbDTO(this);
        }
    }
}
