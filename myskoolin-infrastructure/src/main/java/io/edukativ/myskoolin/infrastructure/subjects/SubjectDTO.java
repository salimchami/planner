package io.edukativ.myskoolin.infrastructure.subjects;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumSchoolRoomsTypesDb;
import io.edukativ.myskoolin.infrastructure.grades.GradeDTO;
import io.edukativ.myskoolin.infrastructure.grades.GradeSerieVO;
import io.edukativ.myskoolin.infrastructure.timetabling.PreferredPartsOfDaysVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * A Subject.
 */
public class SubjectDTO implements Comparable<SubjectDTO>, Serializable {

    private String id;
    private String clientId;
    private String name;
    private String customName;
    private GradeDTO grade;
    private GradeSerieVO gradeSerie;
    private BigDecimal coefficient;
    private String color;
    private String bgColor;
    private Boolean foreignLanguage;
    private Integer maxMinutesPerDay;
    private Integer minMinutesPerDay;
    private Integer minutesPerWeek;
    private Integer coursesFrequencyPerWeek;
    private boolean groupSubject;
    private Boolean deleted;
    private String comment;
    private List<PreferredPartsOfDaysVO> preferredPartsOfDaysInTimetables;
    private List<EnumSchoolRoomsTypesDb> schoolRoomsTypes;
    private Integer daysBetweenTimeSlots;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<PreferredPartsOfDaysVO> getPreferredPartsOfDaysInTimetables() {
        return preferredPartsOfDaysInTimetables;
    }

    public void setPreferredPartsOfDaysInTimetables(List<PreferredPartsOfDaysVO> preferredPartsOfDaysInTimetables) {
        this.preferredPartsOfDaysInTimetables = preferredPartsOfDaysInTimetables;
    }

    public List<EnumSchoolRoomsTypesDb> getSchoolRoomsTypes() {
        return schoolRoomsTypes;
    }

    public void setSchoolRoomsTypes(List<EnumSchoolRoomsTypesDb> schoolRoomsTypes) {
        this.schoolRoomsTypes = schoolRoomsTypes;
    }

    public Integer getDaysBetweenTimeSlots() {
        return daysBetweenTimeSlots;
    }

    public void setDaysBetweenTimeSlots(Integer daysBetweenTimeSlots) {
        this.daysBetweenTimeSlots = daysBetweenTimeSlots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SubjectDTO subject = (SubjectDTO) o;
        return !(subject.id == null || id == null) && Objects.equals(id, subject.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "Subject{" +
                    "id=" + id +
                    ", coefficient='" + coefficient + "'" +
                    ", color='" + color + "'" +
                    ", customName='" + customName + "'" +
                    ", foreignLanguage='" + foreignLanguage + "'" +
                    ", name='" + name + "'" +
                    ", gradeName='" + grade.getName() + "'" +
                    '}';
        }
    }

    public int compareTo(SubjectDTO o) {
        if (o == null) {
            return -1;
        }
        return Comparator.comparing(SubjectDTO::getMaxMinutesPerDay)
                .thenComparing(SubjectDTO::getMinutesPerWeek)
                .thenComparing(SubjectDTO::getCoursesFrequencyPerWeek)
                .compare(this, o);
    }

    public boolean isGroupSubject() {
        return groupSubject;
    }

    public void setGroupSubject(boolean groupSubject) {
        this.groupSubject = groupSubject;
    }
}
