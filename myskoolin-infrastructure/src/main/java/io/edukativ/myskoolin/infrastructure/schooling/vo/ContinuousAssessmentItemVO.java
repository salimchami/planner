package io.edukativ.myskoolin.infrastructure.schooling.vo;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class ContinuousAssessmentItemVO implements Serializable {

    private Instant date;
    private String subject;
    private String option;
    private BigDecimal score;
    private Integer denominator;

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Integer getDenominator() {
        return denominator;
    }

    public void setDenominator(Integer denominator) {
        this.denominator = denominator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContinuousAssessmentItemVO that = (ContinuousAssessmentItemVO) o;
        return date.equals(that.date) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(option, that.option) &&
                score.equals(that.score) &&
                denominator.equals(that.denominator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, subject, option, score, denominator);
    }

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {

            return "ContinuousAssessmentItem{" +
                    "date=" + date +
                    ", subject='" + subject + '\'' +
                    ", option='" + option + '\'' +
                    ", score=" + score +
                    ", denominator=" + denominator +
                    '}';
        }
    }
}
