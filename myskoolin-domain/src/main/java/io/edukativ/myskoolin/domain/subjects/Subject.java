package io.edukativ.myskoolin.domain.subjects;

import io.edukativ.myskoolin.domain.commons.exceptions.GradeNotFoundException;
import io.edukativ.myskoolin.domain.grades.Grade;
import io.edukativ.myskoolin.domain.grades.GradeSerie;
import io.edukativ.myskoolin.domain.schoolrooms.EnumSchoolRoomsTypes;
import io.edukativ.myskoolin.domain.timetabling.PreferredPartsOfDays;
import org.optaplanner.core.api.domain.lookup.PlanningId;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A Subject.
 */
public class Subject implements Comparable<Subject> {

    @PlanningId
    private String id;
    private String clientId;
    private String name;
    private String customName;
    private Grade grade;
    private boolean groupSubject;
    private GradeSerie gradeSerie;
    private BigDecimal coefficient;
    private String color = "#CFD8DC";
    private String bgColor = "#6A6A6A";
    private Boolean foreignLanguage = false;
    private Integer maxMinutesPerDay;
    private Integer minMinutesPerDay;
    private Integer minutesPerWeek;
    private Integer coursesFrequencyPerWeek;
    private Boolean deleted;
    private String comment;
    private List<PreferredPartsOfDays> preferredPartsOfDaysInTimetables;
    private List<EnumSchoolRoomsTypes> schoolRoomsTypes;
    private Integer daysBetweenTimeSlots;

    public Subject() {
        this.deleted = false;
    }

    public Subject(String id, String clientId, String name, String customName, Grade grade, GradeSerie gradeSerie,
                   BigDecimal coefficient, String color, String bgColor,
                   Boolean foreignLanguage, Integer maxMinutesPerDay, Integer minMinutesPerDay,
                   Integer minutesPerWeek, Integer coursesFrequencyPerWeek, Boolean deleted,
                   String comment, List<PreferredPartsOfDays> preferredPartsOfDaysInTimetables,
                   List<EnumSchoolRoomsTypes> schoolRoomsTypes, Integer daysBetweenTimeSlots) {
        this.id = id;
        this.clientId = clientId;
        this.name = name;
        this.customName = customName;
        this.grade = grade;
        this.gradeSerie = gradeSerie;
        this.coefficient = coefficient;
        this.color = color;
        this.bgColor = bgColor;
        this.foreignLanguage = foreignLanguage;
        this.maxMinutesPerDay = maxMinutesPerDay;
        this.minMinutesPerDay = minMinutesPerDay;
        this.minutesPerWeek = minutesPerWeek;
        this.coursesFrequencyPerWeek = coursesFrequencyPerWeek;
        this.deleted = deleted;
        this.comment = comment;
        this.preferredPartsOfDaysInTimetables = preferredPartsOfDaysInTimetables;
        this.schoolRoomsTypes = schoolRoomsTypes;
        this.daysBetweenTimeSlots = daysBetweenTimeSlots;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
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

    public BigDecimal getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public Boolean getForeignLanguage() {
        return foreignLanguage;
    }

    public void setForeignLanguage(Boolean foreignLanguage) {
        this.foreignLanguage = foreignLanguage;
    }

    public Integer getMaxMinutesPerDay() {
        return maxMinutesPerDay;
    }

    public void setMaxMinutesPerDay(Integer maxMinutesPerDay) {
        this.maxMinutesPerDay = maxMinutesPerDay;
    }

    public Integer getMinMinutesPerDay() {
        return minMinutesPerDay;
    }

    public void setMinMinutesPerDay(Integer minMinutesPerDay) {
        this.minMinutesPerDay = minMinutesPerDay;
    }

    public Integer getMinutesPerWeek() {
        return minutesPerWeek;
    }

    public void setMinutesPerWeek(Integer minutesPerWeek) {
        this.minutesPerWeek = minutesPerWeek;
    }

    public Integer getCoursesFrequencyPerWeek() {
        return coursesFrequencyPerWeek;
    }

    public void setCoursesFrequencyPerWeek(Integer coursesFrequencyPerWeek) {
        this.coursesFrequencyPerWeek = coursesFrequencyPerWeek;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public List<PreferredPartsOfDays> getPreferredPartsOfDaysInTimetables() {
        if (this.preferredPartsOfDaysInTimetables == null) {
            this.preferredPartsOfDaysInTimetables = new ArrayList<>();
        }
        return preferredPartsOfDaysInTimetables;
    }

    public void setPreferredPartsOfDaysInTimetables(List<PreferredPartsOfDays> preferredPartsOfDaysInTimetables) {
        this.preferredPartsOfDaysInTimetables = preferredPartsOfDaysInTimetables;
    }

    public List<EnumSchoolRoomsTypes> getSchoolRoomsTypes() {
        if (this.schoolRoomsTypes == null) {
            this.schoolRoomsTypes = new ArrayList<>();
        }
        return schoolRoomsTypes;
    }

    public void setSchoolRoomsTypes(List<EnumSchoolRoomsTypes> schoolRoomsTypes) {
        this.schoolRoomsTypes = schoolRoomsTypes;
    }

    public Integer getDaysBetweenTimeSlots() {
        return daysBetweenTimeSlots;
    }

    public void setDaysBetweenTimeSlots(Integer daysBetweenTimeSlots) {
        this.daysBetweenTimeSlots = daysBetweenTimeSlots;
    }

    public boolean isGroupSubject() {
        return groupSubject;
    }

    public void setGroupSubject(boolean groupSubject) {
        this.groupSubject = groupSubject;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Subject subject = (Subject) o;
        return !(subject.id == null || id == null) && Objects.equals(id, subject.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public int compareTo(Subject o) {
        return Comparator.comparing(Subject::getMaxMinutesPerDay)
                .thenComparing(Subject::getMinutesPerWeek)
                .thenComparing(Subject::getCoursesFrequencyPerWeek)
                .compare(this, o);
    }

    //#############################################################################################
    //#############################################################################################
    //#############################################################################################
    //#############################################################################################

    public String timetableName() {
        if (this.customName != null && !this.customName.isEmpty()) {
            return this.customName;
        } else {
            return this.name;
        }
    }

    public static List<Subject> subjectByGrade(List<Subject> subjects, Grade grade) {
        return subjects.stream().filter(subject -> subject.getGrade().getId().equals(grade.getId())).collect(Collectors.toList());
    }

    public static Integer totalDurationPerWeek(List<Subject> subjects) {
        return subjects.stream().map(Subject::getMinutesPerWeek).mapToInt(Integer::intValue).sum();
    }

    public void findAndSetGradeIfPresent(List<Grade> grades) {
        final Optional<Grade> optGrade = grades
                .stream()
                .filter(g -> g.getName().equals(grade.getName()))
                .findFirst();
        if(optGrade.isPresent()) {
            this.setGrade(optGrade.get());
        } else {
            throw new GradeNotFoundException(String.format("No grade for subject %s", name));
        }
    }

    public void findAndSetGradeSerieFromGrade() {
        if (gradeSerie != null) {
            Optional<GradeSerie> optGradeSerie = grade.getSeries()
                    .stream()
                    .filter(gs -> gs.getName().equals(gradeSerie.getName()))
                    .findFirst();
            optGradeSerie.ifPresent(this::setGradeSerie);
        }
    }
}
