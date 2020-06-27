package io.edukativ.myskoolin.domain.grades;

import io.edukativ.myskoolin.domain.commercial.Client;
import io.edukativ.myskoolin.domain.schoolclasses.EnumSchoolClassNotation;
import io.edukativ.myskoolin.domain.timetabling.TimeTableOptions;

import java.util.*;

/**
 * A Grade.
 */
public class Grade implements Comparable<Grade> {

    private String id;
    private String clientId;
    private String name;
    private Integer order;
    private EnumSchoolClassNotation notation;
    private String diminutive;
    private Integer maxMinutesPerDay;
    private Integer maxMinutesPerWeek;
    private Boolean deleted = false;
    private List<GradeSerie> series;
    private TimeTableOptions timeTableOptions;
    private int nbSubjects;

    public Grade() {
    }

    public Grade(String id, String clientId, String name, Integer order, EnumSchoolClassNotation notation, String diminutive, Integer maxMinutesPerDay, Integer maxMinutesPerWeek, Boolean deleted, List<GradeSerie> series, TimeTableOptions timeTableOptions) {
        this.id = id;
        this.clientId = clientId;
        this.name = name;
        this.order = order;
        this.notation = notation;
        this.diminutive = diminutive;
        this.maxMinutesPerDay = maxMinutesPerDay;
        this.maxMinutesPerWeek = maxMinutesPerWeek;
        this.deleted = deleted;
        this.series = series;
        this.timeTableOptions = timeTableOptions;
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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public EnumSchoolClassNotation getNotation() {
        return notation;
    }

    public void setNotation(EnumSchoolClassNotation notation) {
        this.notation = notation;
    }

    public String getDiminutive() {
        return diminutive;
    }

    public void setDiminutive(String diminutive) {
        this.diminutive = diminutive;
    }

    public Integer getMaxMinutesPerDay() {
        return maxMinutesPerDay;
    }

    public void setMaxMinutesPerDay(Integer maxMinutesPerDay) {
        this.maxMinutesPerDay = maxMinutesPerDay;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public List<GradeSerie> getSeries() {
        if (this.series == null) {
            this.series = new ArrayList<>();
        }
        return series;
    }

    public void setSeries(List<GradeSerie> series) {
        this.series = series;
    }

    public void setTimeTableOptions(TimeTableOptions timeTableOptions) {
        this.timeTableOptions = timeTableOptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Grade grade = (Grade) o;
        return !(grade.id == null || id == null) && Objects.equals(id, grade.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public int compareTo(Grade o) {
        return Comparator
                .comparing(Grade::getOrder)
                .thenComparing(Grade::getName)
                .compare(this, o);
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", order='" + order + "'" +
                ", diminutive='" + diminutive + "'" +
                ", maxMinutesPerDay='" + maxMinutesPerDay + "'" +
                '}';
    }

    public TimeTableOptions getTimeTableOptions() {
        if (this.timeTableOptions == null) {
            this.timeTableOptions = Client.defaultTimeTableOptions();
        }
        return timeTableOptions;
    }

    public Integer getMaxMinutesPerWeek() {
        return maxMinutesPerWeek;
    }

    public void setMaxMinutesPerWeek(Integer maxMinutesPerWeek) {
        this.maxMinutesPerWeek = maxMinutesPerWeek;
    }

    public int getNbSubjects() {
        return nbSubjects;
    }

    public void setNbSubjects(int nbSubjects) {
        this.nbSubjects = nbSubjects;
    }
}
