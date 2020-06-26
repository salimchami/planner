package io.edukativ.myskoolin.domain.entity;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A user.
 */
public class User {

    private String id;
    private String login;
    private String clientId;
    private String password;
    private String gender;
    private String firstName;
    private String lastName;
    private String cellPhone;
    private String homePhone;
    private String nationality;
    private String email;
    private boolean activated = false;
    private String langKey;
    private String imageUrl;
    private String activationKey;
    private String resetKey;
    private Instant resetDate = null;
    private Set<Authority> authorities = new HashSet<>();
    private Address address;
    private ZonedDateTime birthDate;
    private boolean deleted;
    private boolean archived;

    /**
     * Default constructor.
     * Password, login and email must not be null. After construction, this fields must set to something.
     */
    public User() {
        this.deleted = false;
        this.activated = false;
        this.archived = false;
    }

    public User(String id, String login, String clientId, String password, String gender, String firstName, String lastName, String cellPhone, String homePhone, String nationality, String email, boolean activated, String langKey, String imageUrl, String activationKey, String resetKey, Instant resetDate, Set<Authority> authorities, Address address, ZonedDateTime birthDate, boolean deleted, boolean archived) {
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
        this.activated = activated;
        this.langKey = langKey;
        this.imageUrl = imageUrl;
        this.activationKey = activationKey;
        this.resetKey = resetKey;
        this.resetDate = resetDate;
        this.authorities = authorities;
        this.address = address;
        this.birthDate = birthDate;
        this.deleted = deleted;
        this.archived = archived;
    }

    /**
     * Parameterized constructor.
     *
     * @param authorities Authorities
     * @param clientId    clientId
     */
    public User(Set<Authority> authorities, String clientId) {
        this();
        this.authorities = authorities;
        this.clientId = clientId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public String getResetKey() {
        return resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    public Instant getResetDate() {
        return resetDate;
    }

    public void setResetDate(Instant resetDate) {
        this.resetDate = resetDate;
    }

    public Set<Authority> getAuthorities() {
        if (this.authorities == null) {
            return new HashSet<>();
        }
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ZonedDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(ZonedDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /* ############### BUSINESS LOGIC */

    public boolean hasAuthority(String authority) {
        return this.getAuthorities().stream().anyMatch(auth -> auth.getName().equals(authority));
    }

    public boolean containsAuthority(Authority authority) {
        return this.authorities.contains(authority);
    }

    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }
}
