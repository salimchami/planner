package io.edukativ.myskoolin.domain.vo;

import io.edukativ.myskoolin.domain.entity.Grade;
import io.edukativ.myskoolin.domain.entity.Subject;
import io.edukativ.myskoolin.domain.entity.User;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A Teacher.
 */
public class Teacher extends User {

    private ZonedDateTime employedDate;
    private Boolean substitute = false;
    private List<Teacher> substitutedTeachers;
    private List<Absence> absences;
    private List<Subject> taughtSubjects;
    private List<TimeSlot> timetable;
    private ZonedDateTime exitDate;
    private List<Grade> grades;

    public Teacher() {
    }

    public Teacher(ZonedDateTime employedDate,
                   Boolean substitute, List<Teacher> substitutedTeachers, List<Absence> absences,
                   List<Subject> taughtSubjects, List<TimeSlot> timetable,
                   ZonedDateTime exitDate,
                   List<Grade> grades) {
        this.employedDate = employedDate;
        this.substitute = substitute;
        this.substitutedTeachers = substitutedTeachers;
        this.absences = absences;
        this.taughtSubjects = taughtSubjects;
        this.timetable = timetable;
        this.exitDate = exitDate;
        this.grades = grades;
    }

    public static List<Teacher> teachersByGrade(List<Teacher> teachers, Grade grade) {
        return teachers.stream().filter(teacher -> teacher.getGrades().contains(grade)).collect(Collectors.toList());
    }

    public ZonedDateTime getEmployedDate() {
        return employedDate;
    }

    public void setEmployedDate(ZonedDateTime employedDate) {
        this.employedDate = employedDate;
    }

    public Boolean getSubstitute() {
        return substitute;
    }

    public void setSubstitute(Boolean substitute) {
        this.substitute = substitute;
    }

    public List<Teacher> getSubstitutedTeachers() {
        if (this.substitutedTeachers == null) {
            this.substitutedTeachers = new ArrayList<>();
        }
        return substitutedTeachers;
    }

    public void setSubstitutedTeachers(List<Teacher> substitutedTeachers) {
        this.substitutedTeachers = substitutedTeachers;
    }

    public List<Absence> getAbsences() {
        if (this.absences == null) {
            this.absences = new ArrayList<>();
        }
        return absences;
    }

    public void setAbsences(List<Absence> absences) {
        this.absences = absences;
    }

    public List<Subject> getTaughtSubjects() {
        if (this.taughtSubjects == null) {
            this.taughtSubjects = new ArrayList<>();
        }
        return taughtSubjects;
    }

    public void setTaughtSubjects(List<Subject> taughtSubjects) {
        this.taughtSubjects = taughtSubjects;
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

    public ZonedDateTime getExitDate() {
        return exitDate;
    }

    public void setExitDate(ZonedDateTime exitDate) {
        this.exitDate = exitDate;
    }

    public List<Grade> getGrades() {
        if (this.grades == null) {
            this.grades = new ArrayList<>();
        }
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return employedDate.equals(teacher.employedDate) &&
                substitute.equals(teacher.substitute) &&
                Objects.equals(timetable, teacher.timetable) &&
                grades.equals(teacher.grades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employedDate, substitute, timetable, grades);
    }
}
