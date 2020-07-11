package io.edukativ.myskoolin.infrastructure.students;

import io.edukativ.myskoolin.infrastructure.absences.AbsenceVO;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDTO;
import io.edukativ.myskoolin.infrastructure.canteen.CanteenRegistrationVO;
import io.edukativ.myskoolin.infrastructure.continuousassessment.ContinuousAssessmentItemVO;
import io.edukativ.myskoolin.infrastructure.dailybook.DailyBookTimeSlotVO;
import io.edukativ.myskoolin.infrastructure.delays.DelayVO;
import io.edukativ.myskoolin.infrastructure.medical.InfirmaryStatisticsVO;
import io.edukativ.myskoolin.infrastructure.medical.MedicalInfosVO;
import io.edukativ.myskoolin.infrastructure.orientation.OrientationVO;
import io.edukativ.myskoolin.infrastructure.reports.ReportVO;
import io.edukativ.myskoolin.infrastructure.residential.ResidentialSchoolVO;
import io.edukativ.myskoolin.infrastructure.sanctions.SanctionVO;

import java.util.List;
import java.util.Objects;

/**
 * A Teacher.
 */
public class StudentDTO extends UserDTO {

    private InfirmaryStatisticsVO infirmaryStatistics;
    private List<ResponsibleVO> responsibles;
    private UserDTO responsibleAccount;
    private String comment;
    private CanteenRegistrationVO canteenRegistration;
    private MedicalInfosVO medicalInfos;
    private SchoolingInfosVO schoolingInfos;
    private ResidentialSchoolVO residentialSchool;
    private List<DailyBookTimeSlotVO> dailyBook;
    private List<ContinuousAssessmentItemVO> continuousAssessment;
    private String schoolClassId;
    private List<SanctionVO> sanctions;
    private List<DelayVO> delays;
    private List<ReportVO> reports;
    private List<AbsenceVO> absences;
    private OrientationVO orientation;

    public InfirmaryStatisticsVO getInfirmaryStatistics() {
        return infirmaryStatistics;
    }

    public void setInfirmaryStatistics(InfirmaryStatisticsVO infirmaryStatistics) {
        this.infirmaryStatistics = infirmaryStatistics;
    }

    public List<ResponsibleVO> getResponsibles() {
        return responsibles;
    }

    public void setResponsibles(List<ResponsibleVO> responsibles) {
        this.responsibles = responsibles;
    }

    public UserDTO getResponsibleAccount() {
        return responsibleAccount;
    }

    public void setResponsibleAccount(UserDTO responsibleAccount) {
        this.responsibleAccount = responsibleAccount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CanteenRegistrationVO getCanteenRegistration() {
        return canteenRegistration;
    }

    public void setCanteenRegistration(CanteenRegistrationVO canteenRegistration) {
        this.canteenRegistration = canteenRegistration;
    }

    public MedicalInfosVO getMedicalInfos() {
        return medicalInfos;
    }

    public void setMedicalInfos(MedicalInfosVO medicalInfos) {
        this.medicalInfos = medicalInfos;
    }

    public SchoolingInfosVO getSchoolingInfos() {
        return schoolingInfos;
    }

    public void setSchoolingInfos(SchoolingInfosVO schoolingInfos) {
        this.schoolingInfos = schoolingInfos;
    }

    public ResidentialSchoolVO getResidentialSchool() {
        return residentialSchool;
    }

    public void setResidentialSchool(ResidentialSchoolVO residentialSchool) {
        this.residentialSchool = residentialSchool;
    }

    public List<DailyBookTimeSlotVO> getDailyBook() {
        return dailyBook;
    }

    public void setDailyBook(List<DailyBookTimeSlotVO> dailyBook) {
        this.dailyBook = dailyBook;
    }

    public List<ContinuousAssessmentItemVO> getContinuousAssessment() {
        return continuousAssessment;
    }

    public void setContinuousAssessment(List<ContinuousAssessmentItemVO> continuousAssessment) {
        this.continuousAssessment = continuousAssessment;
    }

    public String getSchoolClassId() {
        return schoolClassId;
    }

    public void setSchoolClassId(String schoolClassId) {
        this.schoolClassId = schoolClassId;
    }

    public List<SanctionVO> getSanctions() {
        return sanctions;
    }

    public void setSanctions(List<SanctionVO> sanctions) {
        this.sanctions = sanctions;
    }

    public List<DelayVO> getDelays() {
        return delays;
    }

    public void setDelays(List<DelayVO> delays) {
        this.delays = delays;
    }

    public List<ReportVO> getReports() {
        return reports;
    }

    public void setReports(List<ReportVO> reports) {
        this.reports = reports;
    }

    public List<AbsenceVO> getAbsences() {
        return absences;
    }

    public void setAbsences(List<AbsenceVO> absences) {
        this.absences = absences;
    }

    public OrientationVO getOrientation() {
        return orientation;
    }

    public void setOrientation(OrientationVO orientation) {
        this.orientation = orientation;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }
}
