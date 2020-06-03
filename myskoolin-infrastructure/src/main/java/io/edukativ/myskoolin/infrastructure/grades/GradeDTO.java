package io.edukativ.myskoolin.infrastructure.grades;

import io.edukativ.myskoolin.infrastructure.common.enums.EnumSchoolClassNotation;
import io.edukativ.myskoolin.infrastructure.schooling.vo.TimeTableOptionsDbVO;

import java.util.List;

public class GradeDTO {

    private String id;
    private String clientId;
    private String name;
    private Integer order;
    private EnumSchoolClassNotation notation;
    private String diminutive;
    private Integer maxMinutesPerDay;
    private Boolean deleted;
    private List<GradeSerieDbVO> series;
    private TimeTableOptionsDbVO timeTableOptions;
    private int nbSubjects;

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

    public List<GradeSerieDbVO> getSeries() {
        return series;
    }

    public void setSeries(List<GradeSerieDbVO> series) {
        this.series = series;
    }

    public TimeTableOptionsDbVO getTimeTableOptions() {
        return timeTableOptions;
    }

    public void setTimeTableOptions(TimeTableOptionsDbVO timeTableOptions) {
        this.timeTableOptions = timeTableOptions;
    }

    public int getNbSubjects() {
        return nbSubjects;
    }

    public void setNbSubjects(int nbSubjects) {
        this.nbSubjects = nbSubjects;
    }
}
