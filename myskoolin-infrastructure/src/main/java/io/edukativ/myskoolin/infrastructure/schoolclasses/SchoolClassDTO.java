package io.edukativ.myskoolin.infrastructure.schoolclasses;

import io.edukativ.myskoolin.infrastructure.common.enums.EnumSchoolClassNotation;
import io.edukativ.myskoolin.infrastructure.dailybook.DailyBookTimeSlotVO;
import io.edukativ.myskoolin.infrastructure.grades.GradeDTO;
import io.edukativ.myskoolin.infrastructure.grades.GradeSerieVO;
import io.edukativ.myskoolin.infrastructure.teachers.TeachersBySubjectVO;
import io.edukativ.myskoolin.infrastructure.timetabling.SchoolClassTimeTableVO;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * A School Class.
 */
public class SchoolClassDTO implements Serializable {

    private String id;
    private String clientId;
    private ZonedDateTime coursesStartDate;
    private ZonedDateTime coursesEndDate;
    private List<ZonedDateTime> councilsDates;
    private String customName;
    private String name;
    private Boolean deleted = false;
    private List<String> headTeachers;
    private GradeDTO grade;
    private GradeSerieVO gradeSerie;
    private SchoolClassTimeTableVO timetable;
    private List<DailyBookTimeSlotVO> dailyBook;
    private EnumSchoolClassNotation notation = EnumSchoolClassNotation.QUARTER;
    private List<TeachersBySubjectVO> teachersBySubjects;

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

    public ZonedDateTime getCoursesStartDate() {
        return coursesStartDate;
    }

    public void setCoursesStartDate(ZonedDateTime coursesStartDate) {
        this.coursesStartDate = coursesStartDate;
    }

    public ZonedDateTime getCoursesEndDate() {
        return coursesEndDate;
    }

    public void setCoursesEndDate(ZonedDateTime coursesEndDate) {
        this.coursesEndDate = coursesEndDate;
    }

    public List<ZonedDateTime> getCouncilsDates() {
        return councilsDates;
    }

    public void setCouncilsDates(List<ZonedDateTime> councilsDates) {
        this.councilsDates = councilsDates;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public List<String> getHeadTeachers() {
        return headTeachers;
    }

    public void setHeadTeachers(List<String> headTeachers) {
        this.headTeachers = headTeachers;
    }

    public GradeDTO getGrade() {
        return grade;
    }

    public void setGrade(GradeDTO grade) {
        this.grade = grade;
    }

    public GradeSerieVO getGradeSerie() {
        return gradeSerie;
    }

    public void setGradeSerie(GradeSerieVO gradeSerie) {
        this.gradeSerie = gradeSerie;
    }

    public SchoolClassTimeTableVO getTimetable() {
        return timetable;
    }

    public void setTimetable(SchoolClassTimeTableVO timetable) {
        this.timetable = timetable;
    }

    public List<DailyBookTimeSlotVO> getDailyBook() {
        return dailyBook;
    }

    public void setDailyBook(List<DailyBookTimeSlotVO> dailyBook) {
        this.dailyBook = dailyBook;
    }

    public EnumSchoolClassNotation getNotation() {
        return notation;
    }

    public void setNotation(EnumSchoolClassNotation notation) {
        this.notation = notation;
    }

    public List<TeachersBySubjectVO> getTeachersBySubjects() {
        return teachersBySubjects;
    }

    public void setTeachersBySubjects(List<TeachersBySubjectVO> teachersBySubjects) {
        this.teachersBySubjects = teachersBySubjects;
    }
}
