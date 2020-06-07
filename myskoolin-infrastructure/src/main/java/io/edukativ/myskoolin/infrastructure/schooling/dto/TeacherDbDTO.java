package io.edukativ.myskoolin.infrastructure.schooling.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.edukativ.myskoolin.infrastructure.app.dto.AbstractUserDbDTO;
import io.edukativ.myskoolin.infrastructure.grades.GradeDbDTO;
import io.edukativ.myskoolin.infrastructure.schooling.vo.AbsenceDbVO;
import io.edukativ.myskoolin.infrastructure.schooling.vo.InfirmaryStatisticsDbVO;
import io.edukativ.myskoolin.infrastructure.schooling.vo.MedicalInfosDbVO;
import io.edukativ.myskoolin.infrastructure.schooling.vo.SchoolClassTimeSlotDbVO;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

/**
 * A Teacher.
 */
@Document(collection = "teachers")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "teacher")
public class TeacherDbDTO extends AbstractUserDbDTO {

    public static final String MONGO_COLLECTION_NAME = "teachers";
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
    private Boolean substitute;

    @DBRef
    @Field(MONGO_FIELD_SUBSTITUTED_TEACHERS)
    private List<TeacherDbDTO> substitutedTeachers;

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

    protected TeacherDbDTO(TeacherDbDTOBuilder builder) {
        super(builder);
        this.comment = builder.comment;
        this.employedDate = builder.employedDate;
        this.familySituation = builder.familySituation;
        this.substitute = builder.substitute;
        this.substitutedTeachers = builder.substitutedTeachers;
        this.absences = builder.absences;
        this.taughtSubjects = builder.taughtSubjects;
        this.timetable = builder.timetable;
        this.proCellPhone = builder.proCellPhone;
        this.proPhone = builder.proPhone;
        this.proEmail = builder.proEmail;
        this.infirmaryStatistics = builder.infirmaryStatistics;
        this.medicalInfos = builder.medicalInfos;
        this.exitDate = builder.exitDate;
        this.exitReason = builder.exitReason;
        this.grades = builder.grades;
    }

    public static class TeacherDbDTOBuilder extends AbstractUserDbDTOBuilder<TeacherDbDTO.TeacherDbDTOBuilder, TeacherDbDTO> {

        private String comment;
        private ZonedDateTime employedDate;
        private String familySituation;
        private Boolean substitute;
        private List<TeacherDbDTO> substitutedTeachers;
        private List<AbsenceDbVO> absences;
        private List<SubjectDbDTO> taughtSubjects;
        private List<SchoolClassTimeSlotDbVO> timetable;
        private String proCellPhone;
        private String proPhone;
        private String proEmail;
        private InfirmaryStatisticsDbVO infirmaryStatistics;
        private MedicalInfosDbVO medicalInfos;
        private ZonedDateTime exitDate;
        private String exitReason;
        private List<GradeDbDTO> grades;

        public TeacherDbDTOBuilder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public TeacherDbDTOBuilder employedDate(ZonedDateTime employedDate) {
            this.employedDate = employedDate;
            return this;
        }

        public TeacherDbDTOBuilder familySituation(String familySituation) {
            this.familySituation = familySituation;
            return this;
        }

        public TeacherDbDTOBuilder substitute(boolean substitute) {
            this.substitute = substitute;
            return this;
        }

        public TeacherDbDTOBuilder substitutedTeachers(List<TeacherDbDTO> substitutedTeachers) {
            this.substitutedTeachers = substitutedTeachers;
            return this;
        }

        public TeacherDbDTOBuilder absences(List<AbsenceDbVO> absences) {
            this.absences = absences;
            return this;
        }

        public TeacherDbDTOBuilder taughtSubjects(List<SubjectDbDTO> taughtSubjects) {
            this.taughtSubjects = taughtSubjects;
            return this;
        }

        public TeacherDbDTOBuilder timetable(List<SchoolClassTimeSlotDbVO> timetable) {
            this.timetable = timetable;
            return this;
        }

        public TeacherDbDTOBuilder proCellPhone(String proCellPhone) {
            this.proCellPhone = proCellPhone;
            return this;
        }

        public TeacherDbDTOBuilder proPhone(String proPhone) {
            this.proPhone = proPhone;
            return this;
        }

        public TeacherDbDTOBuilder proEmail(String proEmail) {
            this.proEmail = proEmail;
            return this;
        }

        public TeacherDbDTOBuilder infirmaryStatistics(InfirmaryStatisticsDbVO infirmaryStatistics) {
            this.infirmaryStatistics = infirmaryStatistics;
            return this;
        }

        public TeacherDbDTOBuilder medicalInfos(MedicalInfosDbVO medicalInfos) {
            this.medicalInfos = medicalInfos;
            return this;
        }

        public TeacherDbDTOBuilder exitDate(ZonedDateTime exitDate) {
            this.exitDate = exitDate;
            return this;
        }

        public TeacherDbDTOBuilder exitReason(String exitReason) {
            this.exitReason = exitReason;
            return this;
        }

        public TeacherDbDTOBuilder grades(List<GradeDbDTO> grades) {
            this.grades = grades;
            return this;
        }

        @Override
        public TeacherDbDTO build() {
            return new TeacherDbDTO(this);
        }
    }

    public String getComment() {
        return comment;
    }

    public ZonedDateTime getEmployedDate() {
        return employedDate;
    }

    public String getFamilySituation() {
        return familySituation;
    }

    public Boolean getSubstitute() {
        return substitute;
    }

    public List<TeacherDbDTO> getSubstitutedTeachers() {
        return substitutedTeachers;
    }

    public List<AbsenceDbVO> getAbsences() {
        return absences;
    }

    public List<SubjectDbDTO> getTaughtSubjects() {
        return taughtSubjects;
    }

    public List<SchoolClassTimeSlotDbVO> getTimetable() {
        return timetable;
    }

    public String getProCellPhone() {
        return proCellPhone;
    }

    public String getProPhone() {
        return proPhone;
    }

    public String getProEmail() {
        return proEmail;
    }

    public InfirmaryStatisticsDbVO getInfirmaryStatistics() {
        return infirmaryStatistics;
    }

    public MedicalInfosDbVO getMedicalInfos() {
        return medicalInfos;
    }

    public ZonedDateTime getExitDate() {
        return exitDate;
    }

    public String getExitReason() {
        return exitReason;
    }

    public List<GradeDbDTO> getGrades() {
        return grades;
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
