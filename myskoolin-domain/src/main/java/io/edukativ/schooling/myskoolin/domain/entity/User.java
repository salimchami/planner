package io.edukativ.schooling.myskoolin.domain.entity;

import io.edukativ.schooling.myskoolin.domain.vo.Student;
import io.edukativ.schooling.myskoolin.domain.vo.Teacher;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A user.
 */
public class User {

    private String id;
    private String clientId;
    private String sex;
    private boolean activated;
    private Set<Authority> authorities = new HashSet<>();
    private Student student;
    private ZonedDateTime birthDate;
    private Teacher teacher;
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

    public User(String id, String clientId, String sex, boolean activated,
                Set<Authority> authorities, Student student, ZonedDateTime birthDate,
                Teacher teacher, boolean deleted, boolean archived) {
        this.id = id;
        this.clientId = clientId;
        this.sex = sex;
        this.activated = activated;
        this.authorities = authorities;
        this.student = student;
        this.birthDate = birthDate;
        this.teacher = teacher;
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

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ZonedDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(ZonedDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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
}
