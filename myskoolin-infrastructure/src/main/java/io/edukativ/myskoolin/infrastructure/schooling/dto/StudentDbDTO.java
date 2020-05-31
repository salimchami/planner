package io.edukativ.myskoolin.infrastructure.schooling.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import io.edukativ.myskoolin.infrastructure.schooling.vo.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Max;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A Student.
 */
@Document(collection = "students")
public class StudentDbDTO extends UserDbDTO implements Serializable {

    private static final long serialVersionUID = 1L;
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

    @Field(MONGO_FIELD_INFIRMARY_STATISTICS)
    private InfirmaryStatisticsDbVO infirmaryStatistics;

    @Max(5)
    @Field(MONGO_FIELD_RESPONSIBLES)
    private List<ResponsibleDbVO> responsibles;

    @Indexed
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

    @Field(MONGO_FIELD_TIMETABLE)
    private SchoolClassTimeTableDbVO timetable;

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
     * addiional time slots (timetable)
     *
     */

    public StudentDbDTO() {
    }

    public StudentDbDTO(InfirmaryStatisticsDbVO infirmaryStatistics, List<ResponsibleDbVO> responsibles,
                        UserDbDTO responsibleAccount, String comment, CanteenRegistrationDbVO canteenRegistration,
                        MedicalInfosDbVO medicalInfos, SchoolingInfosDbVO schoolingInfos, ResidentialSchoolDbVO residentialSchool,
                        SchoolClassTimeTableDbVO timetable, List<DailyBookTimeSlotDbVO> dailyBook, List<ContinuousAssessmentItemDbVO> continuousAssessment,
                        String schoolClassId, List<SanctionDbVO> sanctions, List<DelayDbVO> delays, List<ReportDbVO> reports, OrientationDbVO orientation) {
        this.infirmaryStatistics = infirmaryStatistics;
        this.responsibles = responsibles;
        this.responsibleAccount = responsibleAccount;
        this.comment = comment;
        this.canteenRegistration = canteenRegistration;
        this.medicalInfos = medicalInfos;
        this.schoolingInfos = schoolingInfos;
        this.residentialSchool = residentialSchool;
        this.timetable = timetable;
        this.dailyBook = dailyBook;
        this.continuousAssessment = continuousAssessment;
        this.schoolClassId = schoolClassId;
        this.sanctions = sanctions;
        this.delays = delays;
        this.reports = reports;
        this.orientation = orientation;
    }

//    public static Student fromDbObject(BasicDBObject dbObject) {
//        Student student = new Student();
//        student.setInfirmaryStatistics(InfirmaryStatistics.fromDbObject((BasicDBObject) dbObject.get(MONGO_FIELD_INFIRMARY_STATISTICS)));
//        student.setResponsibles(Responsible.fromDbObjects((BasicDBList) dbObject.get(MONGO_FIELD_RESPONSIBLES)));
//        student.setResponsibleAccount(ResponsibleAccount.fromDbObject((BasicDBObject) dbObject.get(MONGO_FIELD_RESPONSIBLE_ACCOUNT)));
//        student.setComment(dbObject.getString(MONGO_FIELD_COMMENT));
//        student.setCanteenRegistration(CanteenRegistration.fromDbObject((BasicDBObject) dbObject.get(MONGO_FIELD_CANTEEN_REGISTRATION)));
//        student.setMedicalInfos(MedicalInfos.fromDbObject((BasicDBObject) dbObject.get(MONGO_FIELD_MEDICAL_INFOS)));
//        student.setSchoolingInfos(SchoolingInfos.fromDbObject((BasicDBObject) dbObject.get(MONGO_FIELD_SCHOOLING_INFOS)));
//        student.setResidentialSchool(ResidentialSchool.fromDbObject((BasicDBObject) dbObject.get(MONGO_FIELD_RESIDENTIAL_SCHOOL)));
//        student.setTimetable(SchoolClassTimeTable.fromDbObject((BasicDBObject) dbObject.get(MONGO_FIELD_TIMETABLE)));
//        student.setDailyBook(DailyBookTimeSlot.fromDbObjects((BasicDBList) dbObject.get(MONGO_FIELD_DAILY_BOOK)));
//        student.setContinuousAssessment(ContinuousAssessmentItem.fromDbObject((BasicDBObject) dbObject.get(MONGO_FIELD_CONTINUOUS_ASSESSMENT)));
//        student.setSchoolClassId(dbObject.getString(MONGO_FIELD_SCHOOL_CLASS_ID));
//        student.setSanctions(Sanction.fromDbObjects((BasicDBList) dbObject.get(MONGO_FIELD_SANCTIONS)));
//        student.setDelays(Delay.fromDbObjects((BasicDBList) dbObject.get(MONGO_FIELD_DELAYS)));
//        student.setReports(Report.fromDbObjects((BasicDBList) dbObject.get(MONGO_FIELD_REPORTS)));
//        student.setOrientation(Orientation.fromDbObject((BasicDBObject) dbObject.get(MONGO_FIELD_ORIENTATION)));
//        return student;
//    }

    public InfirmaryStatisticsDbVO getInfirmaryStatistics() {
        return infirmaryStatistics;
    }

    public void setInfirmaryStatistics(InfirmaryStatisticsDbVO infirmaryStatistics) {
        this.infirmaryStatistics = infirmaryStatistics;
    }

    public List<ResponsibleDbVO> getResponsibles() {
        if (this.responsibles == null) {
            this.responsibles = new ArrayList<>();
        }
        return responsibles;
    }

    public void setResponsibles(List<ResponsibleDbVO> responsibles) {
        this.responsibles = responsibles;
    }

    public UserDbDTO getResponsibleAccount() {
        return responsibleAccount;
    }

    public void setResponsibleAccount(UserDbDTO responsibleAccount) {
        this.responsibleAccount = responsibleAccount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CanteenRegistrationDbVO getCanteenRegistration() {
        return canteenRegistration;
    }

    public void setCanteenRegistration(CanteenRegistrationDbVO canteenRegistration) {
        this.canteenRegistration = canteenRegistration;
    }

    public MedicalInfosDbVO getMedicalInfos() {
        return medicalInfos;
    }

    public void setMedicalInfos(MedicalInfosDbVO medicalInfos) {
        this.medicalInfos = medicalInfos;
    }

    public SchoolingInfosDbVO getSchoolingInfos() {
        return schoolingInfos;
    }

    public void setSchoolingInfos(SchoolingInfosDbVO schoolingInfos) {
        this.schoolingInfos = schoolingInfos;
    }

    public ResidentialSchoolDbVO getResidentialSchool() {
        return residentialSchool;
    }

    public void setResidentialSchool(ResidentialSchoolDbVO residentialSchool) {
        this.residentialSchool = residentialSchool;
    }

    public SchoolClassTimeTableDbVO getTimetable() {
        return timetable;
    }

    public void setTimetable(SchoolClassTimeTableDbVO timetable) {
        this.timetable = timetable;
    }

    public List<DailyBookTimeSlotDbVO> getDailyBook() {
        return dailyBook;
    }

    public void setDailyBook(List<DailyBookTimeSlotDbVO> dailyBook) {
        this.dailyBook = dailyBook;
    }

    public List<ContinuousAssessmentItemDbVO> getContinuousAssessment() {
        if (this.continuousAssessment == null) {
            this.continuousAssessment = new ArrayList<>();
        }
        return continuousAssessment;
    }

    public void setContinuousAssessment(List<ContinuousAssessmentItemDbVO> continuousAssessment) {
        this.continuousAssessment = continuousAssessment;
    }

    public String getSchoolClassId() {
        return schoolClassId;
    }

    public void setSchoolClassId(String schoolClassId) {
        this.schoolClassId = schoolClassId;
    }

    public List<SanctionDbVO> getSanctions() {
        return sanctions;
    }

    public void setSanctions(List<SanctionDbVO> sanctions) {
        this.sanctions = sanctions;
    }

    public List<DelayDbVO> getDelays() {
        return delays;
    }

    public void setDelays(List<DelayDbVO> delays) {
        this.delays = delays;
    }

    public List<ReportDbVO> getReports() {
        return reports;
    }

    public void setReports(List<ReportDbVO> reports) {
        this.reports = reports;
    }

    public OrientationDbVO getOrientation() {
        return orientation;
    }

    public void setOrientation(OrientationDbVO orientation) {
        this.orientation = orientation;
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
                    ", timetable=" + timetable +
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
