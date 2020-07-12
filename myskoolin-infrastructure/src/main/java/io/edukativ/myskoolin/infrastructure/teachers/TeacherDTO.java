package io.edukativ.myskoolin.infrastructure.teachers;

import io.edukativ.myskoolin.infrastructure.absences.AbsenceVO;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDTO;
import io.edukativ.myskoolin.infrastructure.grades.GradeDTO;
import io.edukativ.myskoolin.infrastructure.medical.InfirmaryStatisticsVO;
import io.edukativ.myskoolin.infrastructure.medical.MedicalInfosVO;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectDTO;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A Teacher.
 */
public class TeacherDTO extends UserDTO implements Serializable {

    private String comment;
    private ZonedDateTime employedDate;
    private String familySituation;
    private Boolean substitute;
    private List<TeacherDTO> substitutedTeachers;
    //private Set<Option> taughtOptions;
    private List<AbsenceVO> absences;
    private List<SubjectDTO> taughtSubjects;
    private String proCellPhone;
    private String proPhone;
    private String proEmail;
    private InfirmaryStatisticsVO infirmaryStatistics;
    private MedicalInfosVO medicalInfos;
    private ZonedDateTime exitDate;
    private String exitReason;
    private List<GradeDTO> grades;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ZonedDateTime getEmployedDate() {
        return employedDate;
    }

    public void setEmployedDate(ZonedDateTime employedDate) {
        this.employedDate = employedDate;
    }

    public String getFamilySituation() {
        return familySituation;
    }

    public void setFamilySituation(String familySituation) {
        this.familySituation = familySituation;
    }

    public Boolean getSubstitute() {
        return substitute;
    }

    public void setSubstitute(Boolean substitute) {
        this.substitute = substitute;
    }

    public List<TeacherDTO> getSubstitutedTeachers() {
        if(substitutedTeachers == null) {
            substitutedTeachers = new ArrayList<>();
        }

        return substitutedTeachers;
    }

    public void setSubstitutedTeachers(List<TeacherDTO> substitutedTeachers) {
        this.substitutedTeachers = substitutedTeachers;
    }

    public List<AbsenceVO> getAbsences() {
        if(absences == null) {
            absences = new ArrayList<>();
        }
        return absences;
    }

    public void setAbsences(List<AbsenceVO> absences) {
        this.absences = absences;
    }

    public List<SubjectDTO> getTaughtSubjects() {
        if(taughtSubjects == null) {
            taughtSubjects = new ArrayList<>();
        }
        return taughtSubjects;
    }

    public void setTaughtSubjects(List<SubjectDTO> taughtSubjects) {
        this.taughtSubjects = taughtSubjects;
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

    public InfirmaryStatisticsVO getInfirmaryStatistics() {
        return infirmaryStatistics;
    }

    public void setInfirmaryStatistics(InfirmaryStatisticsVO infirmaryStatistics) {
        this.infirmaryStatistics = infirmaryStatistics;
    }

    public MedicalInfosVO getMedicalInfos() {
        return medicalInfos;
    }

    public void setMedicalInfos(MedicalInfosVO medicalInfos) {
        this.medicalInfos = medicalInfos;
    }

    public ZonedDateTime getExitDate() {
        return exitDate;
    }

    public void setExitDate(ZonedDateTime exitDate) {
        this.exitDate = exitDate;
    }

    public String getExitReason() {
        return exitReason;
    }

    public void setExitReason(String exitReason) {
        this.exitReason = exitReason;
    }

    public List<GradeDTO> getGrades() {
        if(grades == null) {
            grades = new ArrayList<>();
        }
        return grades;
    }

    public void setGrades(List<GradeDTO> grades) {
        this.grades = grades;
    }
}
