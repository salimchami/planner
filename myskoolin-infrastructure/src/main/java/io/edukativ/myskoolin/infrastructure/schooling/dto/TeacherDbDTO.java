package io.edukativ.myskoolin.infrastructure.schooling.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.edukativ.myskoolin.infrastructure.grades.GradeDbDTO;
import io.edukativ.myskoolin.infrastructure.schooling.vo.AbsenceDbVO;
import io.edukativ.myskoolin.infrastructure.schooling.vo.InfirmaryStatisticsDbVO;
import io.edukativ.myskoolin.infrastructure.schooling.vo.MedicalInfosDbVO;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import io.edukativ.myskoolin.infrastructure.schooling.vo.SchoolClassTimeSlotDbVO;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A Teacher.
 */
@Document(collection = "teachers")
public class TeacherDbDTO extends UserDbDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String MONGO_FIELD_COMMENT = "comment";
    public static final String MONGO_FIELD_EMPLOYED_DATE = "employed_date";
    public static final String MONGO_FIELD_FAMILY_SITUATION = "family_situation";
    public static final String MONGO_FIELD_HEAD_TEACHER = "head_teacher";
    public static final String MONGO_FIELD_SUBSTITUTE = "substitute";
    public static final String MONGO_FIELD_SUBSTITUTED_TEACHERS = "substituted_teachers";
    public static final String MONGO_FIELD_ABSENCES = "absences";
    public static final String MONGO_FIELD_TAUGHT_SUBJECTS = "taught_subjects";
    public static final String MONGO_FIELD_TIMETABLE = "timetable";
    public static final String MONGO_FIELD_PRO_CELL_PHONE = "pro_cell_phone";
    public static final String MONGO_FIELD_PRO_PHONE = "pro_phone";
    public static final String MONGO_FIELD_PRO_EMAIL = "pro_email";
    private static final String MONGO_FIELD_INFIRMARY_STATISTICS = "infirmary_statistics";
    private static final String MONGO_FIELD_MEDICAL_INFOS = "medical_infos";
    private static final String MONGO_FIELD_EXIT_DATE = "exit_date";
    private static final String MONGO_FIELD_EXIT_REASON = "exit_reason";
    private static final String MONGO_FIELD_GRADES = "grades";

    @Field(MONGO_FIELD_COMMENT)
    private String comment;

    @Field(MONGO_FIELD_EMPLOYED_DATE)
    private ZonedDateTime employedDate;

    @Field(MONGO_FIELD_FAMILY_SITUATION)
    private String familySituation;

    @Field(MONGO_FIELD_SUBSTITUTE)
    private Boolean substitute = false;

    @DBRef
    @Field(MONGO_FIELD_SUBSTITUTED_TEACHERS)
    private List<UserDbDTO> substitutedTeachers;

    //private Set<Option> taughtOptions;
    @Field(MONGO_FIELD_ABSENCES)
    private List<AbsenceDbVO> absences;

    @DBRef
    @Field(MONGO_FIELD_TAUGHT_SUBJECTS)
    private List<SubjectDbDTO> taughtSubjects;

    @Field(MONGO_FIELD_TIMETABLE)
    private List<SchoolClassTimeSlotDbVO> timetable;

    @Field(MONGO_FIELD_PRO_CELL_PHONE)
    private String proCellPhone;

    @Field(MONGO_FIELD_PRO_PHONE)
    private String proPhone;

    @Field(MONGO_FIELD_PRO_EMAIL)
    private String proEmail;

    @Field(MONGO_FIELD_INFIRMARY_STATISTICS)
    private InfirmaryStatisticsDbVO infirmaryStatistics;

    @Field(MONGO_FIELD_MEDICAL_INFOS)
    private MedicalInfosDbVO medicalInfos;

    @Field(MONGO_FIELD_EXIT_DATE)
    private ZonedDateTime exitDate;

    @Field(MONGO_FIELD_EXIT_REASON)
    private String exitReason;

    @DBRef
    @Field(MONGO_FIELD_GRADES)
    private List<GradeDbDTO> grades;

    public TeacherDbDTO() {
    }

    public TeacherDbDTO(String comment, ZonedDateTime employedDate, String familySituation,
                        Boolean substitute, List<UserDbDTO> substitutedTeachers, List<AbsenceDbVO> absences,
                        List<SubjectDbDTO> taughtSubjects, List<SchoolClassTimeSlotDbVO> timetable, String proCellPhone,
                        String proPhone, String proEmail, InfirmaryStatisticsDbVO infirmaryStatistics, MedicalInfosDbVO medicalInfos, ZonedDateTime exitDate,
                        String exitReason, List<GradeDbDTO> grades) {
        this.comment = comment;
        this.employedDate = employedDate;
        this.familySituation = familySituation;
        this.substitute = substitute;
        this.substitutedTeachers = substitutedTeachers;
        this.absences = absences;
        this.taughtSubjects = taughtSubjects;
        this.timetable = timetable;
        this.proCellPhone = proCellPhone;
        this.proPhone = proPhone;
        this.proEmail = proEmail;
        this.infirmaryStatistics = infirmaryStatistics;
        this.medicalInfos = medicalInfos;
        this.exitDate = exitDate;
        this.exitReason = exitReason;
        this.grades = grades;
    }

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

    public List<UserDbDTO> getSubstitutedTeachers() {
        return substitutedTeachers;
    }

    public void setSubstitutedTeachers(List<UserDbDTO> substitutedTeachers) {
        this.substitutedTeachers = substitutedTeachers;
    }

    public List<AbsenceDbVO> getAbsences() {
        return absences;
    }

    public void setAbsences(List<AbsenceDbVO> absences) {
        this.absences = absences;
    }

    public List<SubjectDbDTO> getTaughtSubjects() {
        if(taughtSubjects == null) {
            taughtSubjects = new ArrayList<>();
        }
        return taughtSubjects;
    }

    public void setTaughtSubjects(List<SubjectDbDTO> taughtSubjects) {
        this.taughtSubjects = taughtSubjects;
    }

    public List<SchoolClassTimeSlotDbVO> getTimetable() {
        if(timetable == null) {
            timetable = new ArrayList<>();
        }
        return timetable;
    }

    public void setTimetable(List<SchoolClassTimeSlotDbVO> timetable) {
        this.timetable = timetable;
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

    public InfirmaryStatisticsDbVO getInfirmaryStatistics() {
        return infirmaryStatistics;
    }

    public void setInfirmaryStatistics(InfirmaryStatisticsDbVO infirmaryStatistics) {
        this.infirmaryStatistics = infirmaryStatistics;
    }

    public MedicalInfosDbVO getMedicalInfos() {
        return medicalInfos;
    }

    public void setMedicalInfos(MedicalInfosDbVO medicalInfos) {
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

    public List<GradeDbDTO> getGrades() {
        return grades;
    }

    public void setGrades(List<GradeDbDTO> grades) {
        this.grades = grades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherDbDTO teacher = (TeacherDbDTO) o;
        return employedDate.equals(teacher.employedDate) &&
                familySituation.equals(teacher.familySituation) &&
                substitute.equals(teacher.substitute) &&
                Objects.equals(timetable, teacher.timetable) &&
                proCellPhone.equals(teacher.proCellPhone) &&
                proEmail.equals(teacher.proEmail) &&
                grades.equals(teacher.grades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employedDate, familySituation, substitute, timetable, proCellPhone, proEmail, grades);
    }

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {

            return "Teacher{" +
                    "comment='" + comment + '\'' +
                    ", employedDate=" + employedDate +
                    ", familySituation='" + familySituation + '\'' +
                    ", substitute=" + substitute +
                    ", substitutedTeachers=" + substitutedTeachers +
                    ", absences=" + absences +
                    ", taughtSubjects=" + taughtSubjects +
                    ", timetable=" + timetable +
                    ", proCellPhone='" + proCellPhone + '\'' +
                    ", proPhone='" + proPhone + '\'' +
                    ", proEmail='" + proEmail + '\'' +
                    ", infirmaryStatistics=" + infirmaryStatistics +
                    ", medicalInfos=" + medicalInfos +
                    ", exitDate=" + exitDate +
                    ", exitReason='" + exitReason + '\'' +
                    ", grades=" + grades +
                    '}';
        }
    }
}
