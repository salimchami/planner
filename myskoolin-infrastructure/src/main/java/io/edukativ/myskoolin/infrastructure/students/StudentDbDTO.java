package io.edukativ.myskoolin.infrastructure.students;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.edukativ.myskoolin.infrastructure.absences.AbsenceDbVO;
import io.edukativ.myskoolin.infrastructure.app.dto.AbstractUserDbDTO;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import io.edukativ.myskoolin.infrastructure.canteen.CanteenRegistrationDbVO;
import io.edukativ.myskoolin.infrastructure.continuousassessment.ContinuousAssessmentItemDbVO;
import io.edukativ.myskoolin.infrastructure.dailybook.DailyBookTimeSlotDbVO;
import io.edukativ.myskoolin.infrastructure.delays.DelayDbVO;
import io.edukativ.myskoolin.infrastructure.medical.InfirmaryStatisticsDbVO;
import io.edukativ.myskoolin.infrastructure.medical.MedicalInfosDbVO;
import io.edukativ.myskoolin.infrastructure.orientation.OrientationDbVO;
import io.edukativ.myskoolin.infrastructure.reports.ReportDbVO;
import io.edukativ.myskoolin.infrastructure.residential.ResidentialSchoolDbVO;
import io.edukativ.myskoolin.infrastructure.sanctions.SanctionDbVO;
import io.edukativ.myskoolin.infrastructure.timetabling.SchoolClassTimeTableDbDTO;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A Student.
 */
@Document(collection = "students")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "student")
public class StudentDbDTO extends AbstractUserDbDTO {

    private static final long serialVersionUID = 1L;
    public static final String MONGO_COLLECTION_NAME = "students";
    public static final String MONGO_FIELD_INFIRMARY_STATISTICS = "infirmary_statistics";
    public static final String MONGO_FIELD_RESPONSIBLES = "responsibles";
    public static final String MONGO_FIELD_RESPONSIBLE_ACCOUNT = "responsible_account";
    public static final String MONGO_FIELD_COMMENT = "comment";
    public static final String MONGO_FIELD_CANTEEN_REGISTRATION = "canteen_registration";
    public static final String MONGO_FIELD_MEDICAL_INFOS = "medical_infos";
    public static final String MONGO_FIELD_SCHOOLING_INFOS = "schooling_infos";
    public static final String MONGO_FIELD_RESIDENTIAL_SCHOOL = "residential_school";
    public static final String MONGO_FIELD_TIMETABLE = "timetable";
    public static final String MONGO_FIELD_DAILY_BOOK = "daily_book";
    public static final String MONGO_FIELD_CONTINUOUS_ASSESSMENT = "continuous_assessment";
    public static final String MONGO_FIELD_SCHOOL_CLASS_ID = "school_class_id";
    public static final String MONGO_FIELD_SANCTIONS = "sanctions";
    public static final String MONGO_FIELD_DELAYS = "delays";
    public static final String MONGO_FIELD_REPORTS = "reports";
    public static final String MONGO_FIELD_ORIENTATION = "orientation";
    public static final String MONGO_FIELD_ABSENCES = "absences";

    @Field(MONGO_FIELD_INFIRMARY_STATISTICS)
    private InfirmaryStatisticsDbVO infirmaryStatistics;

    @Size(max = 5)
    @Field(MONGO_FIELD_RESPONSIBLES)
    private List<ResponsibleDbVO> responsibles;

    @Indexed
    @DBRef
    @Field(MONGO_FIELD_RESPONSIBLE_ACCOUNT)
    private UserDbDTO responsibleAccount;

    @Field(MONGO_FIELD_COMMENT)
    private String comment;

    @Field(MONGO_FIELD_CANTEEN_REGISTRATION)
    private CanteenRegistrationDbVO canteenRegistration;

    @Field(MONGO_FIELD_MEDICAL_INFOS)
    private MedicalInfosDbVO medicalInfos;

    @Field(MONGO_FIELD_SCHOOLING_INFOS)
    private SchoolingInfosDbVO schoolingInfos;

    @Field(MONGO_FIELD_RESIDENTIAL_SCHOOL)
    private ResidentialSchoolDbVO residentialSchool;

    @Field(MONGO_FIELD_DAILY_BOOK)
    private List<DailyBookTimeSlotDbVO> dailyBook;

    @Field(MONGO_FIELD_CONTINUOUS_ASSESSMENT)
    private List<ContinuousAssessmentItemDbVO> continuousAssessment;

    @Field(MONGO_FIELD_SCHOOL_CLASS_ID)
    private String schoolClassId;

    @Field(MONGO_FIELD_SANCTIONS)
    private List<SanctionDbVO> sanctions;

    @Field(MONGO_FIELD_DELAYS)
    private List<DelayDbVO> delays;

    @Field(MONGO_FIELD_REPORTS)
    private List<ReportDbVO> reports;

    @Field(MONGO_FIELD_ORIENTATION)
    private OrientationDbVO orientation;

    @Field(MONGO_FIELD_ABSENCES)
    private List<AbsenceDbVO> absences;

    //?? originSchoolClass: String,

    /*
    @DBRef
    @Field("clubs")
    List<Club> clubs;
    */

    /*
    @DBRef
    @Field("groups")
    List<Group> groups;
    */

    /*
     * saas
     * */

    /*
     * schoolClass
     * */

    /*
     * additional time slots (timetable)
     *
     */

    public StudentDbDTO() {
    }

    private StudentDbDTO(StudentDbDTOBuilder builder) {
        super(builder);
        this.infirmaryStatistics = builder.infirmaryStatistics;
        this.responsibles = builder.responsibles;
        this.responsibleAccount = builder.responsibleAccount;
        this.comment = builder.comment;
        this.canteenRegistration = builder.canteenRegistration;
        this.medicalInfos = builder.medicalInfos;
        this.schoolingInfos = builder.schoolingInfos;
        this.residentialSchool = builder.residentialSchool;
        this.dailyBook = builder.dailyBook;
        this.continuousAssessment = builder.continuousAssessment;
        this.schoolClassId = builder.schoolClassId;
        this.sanctions = builder.sanctions;
        this.delays = builder.delays;
        this.reports = builder.reports;
        this.orientation = builder.orientation;
        this.absences = builder.absences;
    }

    public void changeSchoolClassId(String schoolClassId) {
        this.schoolClassId = schoolClassId;
    }

    public static class StudentDbDTOBuilder extends AbstractUserDbDTOBuilder<StudentDbDTO.StudentDbDTOBuilder, StudentDbDTO> {

        private InfirmaryStatisticsDbVO infirmaryStatistics;
        private List<ResponsibleDbVO> responsibles;
        private UserDbDTO responsibleAccount;
        private String comment;
        private CanteenRegistrationDbVO canteenRegistration;
        private MedicalInfosDbVO medicalInfos;
        private SchoolingInfosDbVO schoolingInfos;
        private ResidentialSchoolDbVO residentialSchool;
        private SchoolClassTimeTableDbDTO timetable;
        private List<DailyBookTimeSlotDbVO> dailyBook;
        private List<ContinuousAssessmentItemDbVO> continuousAssessment;
        private String schoolClassId;
        private List<SanctionDbVO> sanctions;
        private List<DelayDbVO> delays;
        private List<ReportDbVO> reports;
        private List<AbsenceDbVO> absences;
        private OrientationDbVO orientation;

        public StudentDbDTOBuilder infirmaryStatistics(InfirmaryStatisticsDbVO infirmaryStatistics) {
            this.infirmaryStatistics = infirmaryStatistics;
            return this;
        }

        public StudentDbDTOBuilder responsibles(List<ResponsibleDbVO> responsibles) {
            this.responsibles = responsibles;
            return this;
        }

        public StudentDbDTOBuilder responsibleAccount(UserDbDTO responsibleAccount) {
            this.responsibleAccount = responsibleAccount;
            return this;
        }

        public StudentDbDTOBuilder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public StudentDbDTOBuilder canteenRegistration(CanteenRegistrationDbVO canteenRegistration) {
            this.canteenRegistration = canteenRegistration;
            return this;
        }

        public StudentDbDTOBuilder medicalInfos(MedicalInfosDbVO medicalInfos) {
            this.medicalInfos = medicalInfos;
            return this;
        }

        public StudentDbDTOBuilder schoolingInfos(SchoolingInfosDbVO schoolingInfos) {
            this.schoolingInfos = schoolingInfos;
            return this;
        }

        public StudentDbDTOBuilder residentialSchool(ResidentialSchoolDbVO residentialSchool) {
            this.residentialSchool = residentialSchool;
            return this;
        }

        public StudentDbDTOBuilder timetable(SchoolClassTimeTableDbDTO timetable) {
            this.timetable = timetable;
            return this;
        }

        public StudentDbDTOBuilder dailyBook(List<DailyBookTimeSlotDbVO> dailyBook) {
            this.dailyBook = dailyBook;
            return this;
        }

        public StudentDbDTOBuilder continuousAssessment(List<ContinuousAssessmentItemDbVO> continuousAssessment) {
            this.continuousAssessment = continuousAssessment;
            return this;
        }

        public StudentDbDTOBuilder schoolClassId(String schoolClassId) {
            this.schoolClassId = schoolClassId;
            return this;
        }

        public StudentDbDTOBuilder sanctions(List<SanctionDbVO> sanctions) {
            this.sanctions = sanctions;
            return this;
        }

        public StudentDbDTOBuilder delays(List<DelayDbVO> delays) {
            this.delays = delays;
            return this;
        }

        public StudentDbDTOBuilder reports(List<ReportDbVO> reports) {
            this.reports = reports;
            return this;
        }

        public StudentDbDTOBuilder orientation(OrientationDbVO orientation) {
            this.orientation = orientation;
            return this;
        }

        public StudentDbDTOBuilder absences(List<AbsenceDbVO> absences) {
            this.absences = absences;
            return this;
        }

        @Override
        public StudentDbDTO build() {
            return new StudentDbDTO(this);
        }
    }

    public InfirmaryStatisticsDbVO getInfirmaryStatistics() {
        return infirmaryStatistics;
    }

    public List<ResponsibleDbVO> getResponsibles() {
        if(this.responsibles == null) {
            return new ArrayList<>();
        }
        return responsibles;
    }

    public UserDbDTO getResponsibleAccount() {
        return responsibleAccount;
    }

    public String getComment() {
        return comment;
    }

    public CanteenRegistrationDbVO getCanteenRegistration() {
        return canteenRegistration;
    }

    public MedicalInfosDbVO getMedicalInfos() {
        return medicalInfos;
    }

    public SchoolingInfosDbVO getSchoolingInfos() {
        return schoolingInfos;
    }

    public ResidentialSchoolDbVO getResidentialSchool() {
        return residentialSchool;
    }

    public List<DailyBookTimeSlotDbVO> getDailyBook() {
        if(this.dailyBook == null) {
            return new ArrayList<>();
        }
        return dailyBook;
    }

    public List<ContinuousAssessmentItemDbVO> getContinuousAssessment() {
        return Objects.requireNonNullElseGet(this.continuousAssessment, ArrayList::new);
    }

    public String getSchoolClassId() {
        return schoolClassId;
    }

    public List<SanctionDbVO> getSanctions() {
        if(this.sanctions == null) {
            return new ArrayList<>();
        }
        return sanctions;
    }

    public List<DelayDbVO> getDelays() {
        if(this.delays == null) {
            return new ArrayList<>();
        }
        return delays;
    }

    public List<ReportDbVO> getReports() {
        if(this.reports == null) {
            return new ArrayList<>();
        }
        return reports;
    }

    public OrientationDbVO getOrientation() {
        return orientation;
    }

    public List<AbsenceDbVO> getAbsences() {
        if(this.absences == null) {
            return new ArrayList<>();
        }
        return absences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDbDTO student = (StudentDbDTO) o;
        return responsibles.equals(student.responsibles) &&
                responsibleAccount.equals(student.responsibleAccount) &&
                canteenRegistration.equals(student.canteenRegistration) &&
                schoolingInfos.equals(student.schoolingInfos) &&
                residentialSchool.equals(student.residentialSchool) &&
                schoolClassId.equals(student.schoolClassId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responsibles, responsibleAccount, canteenRegistration, schoolingInfos, residentialSchool, schoolClassId);
    }

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {

            return "Student{" +
                    "infirmaryStatistics=" + infirmaryStatistics +
                    ", responsibles=" + responsibles +
                    ", responsibleAccount=" + responsibleAccount +
                    ", comment='" + comment + '\'' +
                    ", canteenRegistration=" + canteenRegistration +
                    ", medicalInfos=" + medicalInfos +
                    ", schoolingInfos=" + schoolingInfos +
                    ", residentialSchool=" + residentialSchool +
                    ", dailyBook=" + dailyBook +
                    ", continuousAssessment=" + continuousAssessment +
                    ", schoolClassId" + schoolClassId + '\'' +
                    ", sanctions=" + sanctions +
                    ", delays=" + delays +
                    ", reports=" + reports +
                    ", orientation=" + orientation +
                    '}';
        }
    }
}
