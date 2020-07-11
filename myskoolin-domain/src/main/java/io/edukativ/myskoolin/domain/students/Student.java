package io.edukativ.myskoolin.domain.students;

import io.edukativ.myskoolin.domain.absences.Absence;
import io.edukativ.myskoolin.domain.canteen.CanteenRegistration;
import io.edukativ.myskoolin.domain.commons.entity.User;
import io.edukativ.myskoolin.domain.continuousassessment.ContinuousAssessmentItem;
import io.edukativ.myskoolin.domain.dailybook.DailyBookTimeSlot;
import io.edukativ.myskoolin.domain.delays.Delay;
import io.edukativ.myskoolin.domain.medical.InfirmaryStatistics;
import io.edukativ.myskoolin.domain.medical.MedicalInfos;
import io.edukativ.myskoolin.domain.orientation.Orientation;
import io.edukativ.myskoolin.domain.reports.Report;
import io.edukativ.myskoolin.domain.residential.ResidentialSchool;
import io.edukativ.myskoolin.domain.sanctions.Sanction;

import java.util.List;
import java.util.Objects;

/**
 * A Student.
 */
public class Student extends User {

    private InfirmaryStatistics infirmaryStatistics;
    private List<Responsible> responsibles;
    private User responsibleAccount;
    private String comment;
    private CanteenRegistration canteenRegistration;
    private MedicalInfos medicalInfos;
    private SchoolingInfos schoolingInfos;
    private ResidentialSchool residentialSchool;
    private List<DailyBookTimeSlot> dailyBook;
    private List<ContinuousAssessmentItem> continuousAssessment;
    private String schoolClassId;
    private List<Sanction> sanctions;
    private List<Delay> delays;
    private List<Report> reports;
    private Orientation orientation;
    private List<Absence> absences;

    public InfirmaryStatistics getInfirmaryStatistics() {
        return infirmaryStatistics;
    }

    public void setInfirmaryStatistics(InfirmaryStatistics infirmaryStatistics) {
        this.infirmaryStatistics = infirmaryStatistics;
    }

    public List<Responsible> getResponsibles() {
        return responsibles;
    }

    public void setResponsibles(List<Responsible> responsibles) {
        this.responsibles = responsibles;
    }

    public User getResponsibleAccount() {
        return responsibleAccount;
    }

    public void setResponsibleAccount(User responsibleAccount) {
        this.responsibleAccount = responsibleAccount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CanteenRegistration getCanteenRegistration() {
        return canteenRegistration;
    }

    public void setCanteenRegistration(CanteenRegistration canteenRegistration) {
        this.canteenRegistration = canteenRegistration;
    }

    public MedicalInfos getMedicalInfos() {
        return medicalInfos;
    }

    public void setMedicalInfos(MedicalInfos medicalInfos) {
        this.medicalInfos = medicalInfos;
    }

    public SchoolingInfos getSchoolingInfos() {
        return schoolingInfos;
    }

    public void setSchoolingInfos(SchoolingInfos schoolingInfos) {
        this.schoolingInfos = schoolingInfos;
    }

    public ResidentialSchool getResidentialSchool() {
        return residentialSchool;
    }

    public void setResidentialSchool(ResidentialSchool residentialSchool) {
        this.residentialSchool = residentialSchool;
    }

    public List<DailyBookTimeSlot> getDailyBook() {
        return dailyBook;
    }

    public void setDailyBook(List<DailyBookTimeSlot> dailyBook) {
        this.dailyBook = dailyBook;
    }

    public List<ContinuousAssessmentItem> getContinuousAssessment() {
        return continuousAssessment;
    }

    public void setContinuousAssessment(List<ContinuousAssessmentItem> continuousAssessment) {
        this.continuousAssessment = continuousAssessment;
    }

    public String getSchoolClassId() {
        return schoolClassId;
    }

    public void setSchoolClassId(String schoolClassId) {
        this.schoolClassId = schoolClassId;
    }

    public List<Sanction> getSanctions() {
        return sanctions;
    }

    public void setSanctions(List<Sanction> sanctions) {
        this.sanctions = sanctions;
    }

    public List<Delay> getDelays() {
        return delays;
    }

    public void setDelays(List<Delay> delays) {
        this.delays = delays;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public List<Absence> getAbsences() {
        return absences;
    }

    public void setAbsences(List<Absence> absences) {
        this.absences = absences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return canteenRegistration.equals(student.canteenRegistration) &&
                schoolingInfos.equals(student.schoolingInfos) &&
                residentialSchool.equals(student.residentialSchool) &&
                schoolClassId.equals(student.schoolClassId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(canteenRegistration, schoolingInfos, residentialSchool, schoolClassId);
    }
}
