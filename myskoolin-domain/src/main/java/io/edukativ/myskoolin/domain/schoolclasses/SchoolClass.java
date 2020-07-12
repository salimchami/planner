package io.edukativ.myskoolin.domain.schoolclasses;

import io.edukativ.myskoolin.domain.grades.Grade;
import io.edukativ.myskoolin.domain.grades.GradeSerie;
import io.edukativ.myskoolin.domain.teachers.Teacher;
import io.edukativ.myskoolin.domain.teachers.TeachersBySubject;
import io.edukativ.myskoolin.domain.timetabling.TimeSlot;
import org.optaplanner.core.api.domain.lookup.PlanningId;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A School Class.
 */
public class SchoolClass {

    @PlanningId
    private String id;
    private String clientId;
    private ZonedDateTime coursesStartDate;
    private ZonedDateTime coursesEndDate;
    private List<ZonedDateTime> councilsDates;
    private String customName;
    private String name;
    private Boolean deleted = false;
    private List<String> headTeachers;
    private Grade grade;
    private GradeSerie gradeSerie;
    private List<TimeSlot> dailyBook;
    private EnumSchoolClassNotation notation;
    private List<TeachersBySubject> teachersBySubjects;

    public SchoolClass() {
    }

    public SchoolClass(String id, String clientId, ZonedDateTime coursesStartDate, ZonedDateTime coursesEndDate,
                       List<ZonedDateTime> councilsDates, String customName, String name, Boolean deleted,
                       List<String> headTeachers, Grade grade, GradeSerie gradeSerie, List<TimeSlot> dailyBook,
                       EnumSchoolClassNotation notation, List<TeachersBySubject> teachersBySubjects) {
        this.id = id;
        this.clientId = clientId;
        this.coursesStartDate = coursesStartDate;
        this.coursesEndDate = coursesEndDate;
        this.councilsDates = councilsDates;
        this.customName = customName;
        this.name = name;
        this.deleted = deleted;
        this.headTeachers = headTeachers;
        this.grade = grade;
        this.gradeSerie = gradeSerie;
        this.dailyBook = dailyBook;
        this.notation = notation;
        this.teachersBySubjects = teachersBySubjects;
    }

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
        if (this.councilsDates == null) {
            this.councilsDates = new ArrayList<>();
        }
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
        if (this.headTeachers == null) {
            this.headTeachers = new ArrayList<>();
        }
        return headTeachers;
    }

    public void setHeadTeachers(List<String> headTeachers) {
        this.headTeachers = headTeachers;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public GradeSerie getGradeSerie() {
        return gradeSerie;
    }

    public void setGradeSerie(GradeSerie gradeSerie) {
        this.gradeSerie = gradeSerie;
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

    public EnumSchoolClassNotation getNotation() {
        return notation;
    }

    public void setNotation(EnumSchoolClassNotation notation) {
        this.notation = notation;
    }

    public List<TeachersBySubject> getTeachersBySubjects() {
        if (this.teachersBySubjects == null) {
            this.teachersBySubjects = new ArrayList<>();
        }
        return teachersBySubjects;
    }

    public void setTeachersBySubjects(List<TeachersBySubject> teachersBySubjects) {
        this.teachersBySubjects = teachersBySubjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SchoolClass schoolClass = (SchoolClass) o;
        return !(schoolClass.id == null || id == null) && Objects.equals(id, schoolClass.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "SchoolClass{" +
                "id=" + id +
                ", coursesStartDate='" + coursesStartDate + "'" +
                ", coursesEndDate='" + coursesEndDate + "'" +
                ", customName='" + customName + "'" +
                ", name='" + name + "'" +
                ", deleted='" + deleted + "'" +
                '}';
    }

    public List<Teacher> getTeachersBySubject(String subjectId) {
        return this.teachersBySubjects.stream()
                .filter(teachersBySubject -> teachersBySubject.getSubject().getId().equals(subjectId))
                .map(TeachersBySubject::getTeachers)
                .flatMap(List::stream).collect(Collectors.toList());
    }
}
