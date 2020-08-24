package io.edukativ.myskoolin.domain.teachers;

import io.edukativ.myskoolin.domain.absences.Absence;
import io.edukativ.myskoolin.domain.commons.AuthoritiesConstants;
import io.edukativ.myskoolin.domain.commons.entity.Authority;
import io.edukativ.myskoolin.domain.commons.entity.User;
import io.edukativ.myskoolin.domain.grades.Grade;
import io.edukativ.myskoolin.domain.medical.InfirmaryStatistics;
import io.edukativ.myskoolin.domain.medical.MedicalInfos;
import io.edukativ.myskoolin.domain.subjects.Subject;
import org.optaplanner.core.api.domain.entity.PlanningPin;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A Teacher.
 */
public class Teacher extends User {

    private String comment;
    private ZonedDateTime employedDate;
    private String familySituation;
    private Boolean substitute;
    private List<Teacher> substitutedTeachers;
    private List<Absence> absences;
    private List<Subject> taughtSubjects;
    private String proCellPhone;
    private String proPhone;
    private String proEmail;
    private InfirmaryStatistics infirmaryStatistics;
    private MedicalInfos medicalInfos;
    private ZonedDateTime exitDate;
    private String exitReason;
    private List<Grade> grades;
    @PlanningPin
    private boolean pinned = true;

    public Teacher() {
    }

    public static List<Teacher> teachersByGrade(List<Teacher> teachers, Grade grade) {
        return teachers.stream().filter(teacher -> teacher.getGrades().contains(grade)).collect(Collectors.toList());
    }

    public ZonedDateTime getEmployedDate() {
        return employedDate;
    }

    public void setEmployedDate(ZonedDateTime employedDate) {
        this.employedDate = employedDate;
    }

    public Boolean getSubstitute() {
        return substitute;
    }

    public void setSubstitute(Boolean substitute) {
        this.substitute = substitute;
    }

    public List<Teacher> getSubstitutedTeachers() {
        if (this.substitutedTeachers == null) {
            this.substitutedTeachers = new ArrayList<>();
        }
        return substitutedTeachers;
    }

    public void setSubstitutedTeachers(List<Teacher> substitutedTeachers) {
        this.substitutedTeachers = substitutedTeachers;
    }

    public List<Absence> getAbsences() {
        if (this.absences == null) {
            this.absences = new ArrayList<>();
        }
        return absences;
    }

    public void setAbsences(List<Absence> absences) {
        this.absences = absences;
    }

    public List<Subject> getTaughtSubjects() {
        if (this.taughtSubjects == null) {
            this.taughtSubjects = new ArrayList<>();
        }
        return taughtSubjects;
    }

    public void setTaughtSubjects(List<Subject> taughtSubjects) {
        this.taughtSubjects = taughtSubjects;
    }

    public ZonedDateTime getExitDate() {
        return exitDate;
    }

    public void setExitDate(ZonedDateTime exitDate) {
        this.exitDate = exitDate;
    }

    public List<Grade> getGrades() {
        if (this.grades == null) {
            this.grades = new ArrayList<>();
        }
        return grades;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFamilySituation() {
        return familySituation;
    }

    public void setFamilySituation(String familySituation) {
        this.familySituation = familySituation;
    }

    public String getProCellPhone() {
        return proCellPhone;
    }

    public void setProCellPhone(String proCellPhone) {
        this.proCellPhone = proCellPhone;
    }

    public String getProPhone() {
        return proPhone;
    }

    public void setProPhone(String proPhone) {
        this.proPhone = proPhone;
    }

    public String getProEmail() {
        return proEmail;
    }

    public void setProEmail(String proEmail) {
        this.proEmail = proEmail;
    }

    public InfirmaryStatistics getInfirmaryStatistics() {
        return infirmaryStatistics;
    }

    public void setInfirmaryStatistics(InfirmaryStatistics infirmaryStatistics) {
        this.infirmaryStatistics = infirmaryStatistics;
    }

    public MedicalInfos getMedicalInfos() {
        return medicalInfos;
    }

    public void setMedicalInfos(MedicalInfos medicalInfos) {
        this.medicalInfos = medicalInfos;
    }

    public String getExitReason() {
        return exitReason;
    }

    public void setExitReason(String exitReason) {
        this.exitReason = exitReason;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;

        return Objects.equals(getId(), teacher.getId()) &&
                Objects.equals(employedDate, teacher.employedDate) &&
                Objects.equals(substitute, teacher.substitute) &&
                Objects.equals(grades, teacher.grades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employedDate, substitute, grades);
    }

    public void checkTeacherAuthority(Authority teacherAuthority) {
        if (getAuthorities().stream().noneMatch(authority -> AuthoritiesConstants.TEACHERS.equals(authority.getName()))) {
            addAuthority(teacherAuthority);
        }

    }

    public boolean isPinned() {
        return pinned;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }
}
