package io.edukativ.schooling.myskoolin.domain.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A Student.
 */
public class Student {

    private String comment;
    private CanteenRegistration canteenRegistration;
    private MedicalInfos medicalInfos;
    private SchoolingInfos schoolingInfos;
    private ResidentialSchool residentialSchool;
    private List<TimeSlot> timetable;
    private List<TimeSlot> dailyBook;
    private String schoolClassId;

    public Student() {
    }

    public Student(String comment, CanteenRegistration canteenRegistration,
                   MedicalInfos medicalInfos, SchoolingInfos schoolingInfos, ResidentialSchool residentialSchool,
                   List<TimeSlot> timetable, List<TimeSlot> dailyBook,
                   String schoolClassId) {
        this.comment = comment;
        this.canteenRegistration = canteenRegistration;
        this.medicalInfos = medicalInfos;
        this.schoolingInfos = schoolingInfos;
        this.residentialSchool = residentialSchool;
        this.timetable = timetable;
        this.dailyBook = dailyBook;
        this.schoolClassId = schoolClassId;
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

    public List<TimeSlot> getTimetable() {
        if (this.timetable == null) {
            this.timetable = new ArrayList<>();
        }
        return timetable;
    }

    public void setTimetable(List<TimeSlot> timetable) {
        this.timetable = timetable;
    }

    public List<TimeSlot> getDailyBook() {
        if (this.dailyBook == null) {
            this.dailyBook = new ArrayList<>();
        }
        return dailyBook;
    }

    public void setDailyBook(List<TimeSlot> dailyBook) {
        this.dailyBook = dailyBook;
    }

    public String getSchoolClassId() {
        return schoolClassId;
    }

    public void setSchoolClassId(String schoolClassId) {
        this.schoolClassId = schoolClassId;
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
