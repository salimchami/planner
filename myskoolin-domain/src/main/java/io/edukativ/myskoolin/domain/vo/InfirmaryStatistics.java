package io.edukativ.myskoolin.domain.vo;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Student infirmary statistics.
 */
public class InfirmaryStatistics implements Serializable {

    private List<ZonedDateTime> transitDates;
    private List<Drug> drugs;
    private Integer transitsNumber = 0;
    private String comment;

    public List<ZonedDateTime> getTransitDates() {
        return transitDates;
    }

    public void setTransitDates(List<ZonedDateTime> transitDates) {
        this.transitDates = transitDates;
    }

    public List<Drug> getDrugs() {
        if(drugs == null) {
            return new ArrayList<>();
        }
        return drugs;
    }

    public void setDrugs(List<Drug> drugs) {
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
