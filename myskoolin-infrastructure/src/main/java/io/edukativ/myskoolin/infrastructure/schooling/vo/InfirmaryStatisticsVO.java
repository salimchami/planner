package io.edukativ.myskoolin.infrastructure.schooling.vo;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Student infirmary statistics.
 */
public class InfirmaryStatisticsVO implements Serializable {

    private List<ZonedDateTime> transitDates;
    private List<DrugVO> drugs;
    private Integer transitsNumber = 0;
    private String comment;

    public List<ZonedDateTime> getTransitDates() {
        return transitDates;
    }

    public void setTransitDates(List<ZonedDateTime> transitDates) {
        this.transitDates = transitDates;
    }

    public List<DrugVO> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<DrugVO> drugs) {
        this.drugs = drugs;
    }

    public Integer getTransitsNumber() {
        return transitsNumber;
    }

    public void setTransitsNumber(Integer transitsNumber) {
        this.transitsNumber = transitsNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
