package io.edukativ.myskoolin.infrastructure.grades;

import io.edukativ.myskoolin.infrastructure.common.enums.EnumSchoolClassNotation;
import io.edukativ.myskoolin.infrastructure.timetabling.TimeTableOptionsVO;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectDTO;

import java.util.List;

public class GradeDTO {

    private String id;
    private String clientId;
    private String name;
    private Integer order;
    private EnumSchoolClassNotation notation;
    private String diminutive;
    private Integer maxMinutesPerDay;
    private boolean deleted;
    private List<GradeSerieVO> series;
    private TimeTableOptionsVO timeTableOptions;
    private List<SubjectDTO> subjects;
    private int nbSubjects;
    private Integer maxMinutesPerWeek;

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

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public List<GradeSerieVO> getSeries() {
        return series;
    }

    public void setSeries(List<GradeSerieVO> series) {
        this.series = series;
    }

    public TimeTableOptionsVO getTimeTableOptions() {
        return timeTableOptions;
    }

    public void setTimeTableOptions(TimeTableOptionsVO timeTableOptions) {
        this.timeTableOptions = timeTableOptions;
    }

    public int getNbSubjects() {
        return nbSubjects;
    }

    public void setNbSubjects(int nbSubjects) {
        this.nbSubjects = nbSubjects;
    }

    public List<SubjectDTO> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectDTO> subjects) {
        this.subjects = subjects;
    }

    public Integer getMaxMinutesPerWeek() {
        return maxMinutesPerWeek;
    }

    public void setMaxMinutesPerWeek(Integer maxMinutesPerWeek) {
        this.maxMinutesPerWeek = maxMinutesPerWeek;
    }
}
