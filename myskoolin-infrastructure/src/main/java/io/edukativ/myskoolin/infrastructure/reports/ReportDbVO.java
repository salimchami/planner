package io.edukativ.myskoolin.infrastructure.reports;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumPeriod;
import io.edukativ.myskoolin.infrastructure.common.vo.AcademicYearDbVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class ReportDbVO implements Serializable {

    private String title;
    private String subTitle;
    private String comment;
    private String appreciation;
    private String globalAppreciation;
    private EnumPeriod period;
    private Integer periodNumber;
    private AcademicYearDbVO academicYear;
    private String subjectId;
    private BigDecimal averageScore;
    private BigDecimal schoolClassAverageScore;
    private BigDecimal schoolClassAverageHighScore;
    private BigDecimal schoolClassAverageDownScore;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    public String getGlobalAppreciation() {
        return globalAppreciation;
    }

    public void setGlobalAppreciation(String globalAppreciation) {
        this.globalAppreciation = globalAppreciation;
    }

    public EnumPeriod getPeriod() {
        return period;
    }

    public void setPeriod(EnumPeriod period) {
        this.period = period;
    }

    public Integer getPeriodNumber() {
        return periodNumber;
    }

    public void setPeriodNumber(Integer periodNumber) {
        this.periodNumber = periodNumber;
    }

    public AcademicYearDbVO getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(AcademicYearDbVO academicYear) {
        this.academicYear = academicYear;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public BigDecimal getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(BigDecimal averageScore) {
        this.averageScore = averageScore;
    }

    public BigDecimal getSchoolClassAverageScore() {
        return schoolClassAverageScore;
    }

    public void setSchoolClassAverageScore(BigDecimal schoolClassAverageScore) {
        this.schoolClassAverageScore = schoolClassAverageScore;
    }

    public BigDecimal getSchoolClassAverageHighScore() {
        return schoolClassAverageHighScore;
    }

    public void setSchoolClassAverageHighScore(BigDecimal schoolClassAverageHighScore) {
        this.schoolClassAverageHighScore = schoolClassAverageHighScore;
    }

    public BigDecimal getSchoolClassAverageDownScore() {
        return schoolClassAverageDownScore;
    }

    public void setSchoolClassAverageDownScore(BigDecimal schoolClassAverageDownScore) {
        this.schoolClassAverageDownScore = schoolClassAverageDownScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportDbVO report = (ReportDbVO) o;
        return title.equals(report.title) &&
                period == report.period &&
                periodNumber.equals(report.periodNumber) &&
                academicYear.equals(report.academicYear) &&
                subjectId.equals(report.subjectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, period, periodNumber, academicYear, subjectId);
    }

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {

            return "Report{" +
                    "title='" + title + '\'' +
                    ", subTitle='" + subTitle + '\'' +
                    ", comment='" + comment + '\'' +
                    ", appreciation='" + appreciation + '\'' +
                    ", globalAppreciation='" + globalAppreciation + '\'' +
                    ", period=" + period +
                    ", periodNumber=" + periodNumber +
                    ", academicYear=" + academicYear +
                    ", subjectId='" + subjectId + '\'' +
                    ", averageScore=" + averageScore +
                    ", schoolClassAverageScore=" + schoolClassAverageScore +
                    ", schoolClassAverageHighScore=" + schoolClassAverageHighScore +
                    ", schoolClassAverageDownScore=" + schoolClassAverageDownScore +
                    '}';
        }
    }
}
