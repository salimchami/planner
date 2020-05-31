package io.edukativ.myskoolin.infrastructure.schooling.vo;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Student infirmary statistics.
 */
public class InfirmaryStatisticsDbVO implements Serializable {

    public static final String MONGO_FIELD_TRANSIT_DATE = "transit_dates";
    public static final String MONGO_FIELD_DRUGS = "drugs";
    public static final String MONGO_FIELD_TRANSITS_NUMBER = "transits_number";
    public static final String MONGO_FIELD_COMMENT = "comment";

    @Field(MONGO_FIELD_TRANSIT_DATE)
    private List<ZonedDateTime> transitDates;

    @Field(MONGO_FIELD_DRUGS)
    private List<DrugDbVO> drugs;

    @Field(MONGO_FIELD_TRANSITS_NUMBER)
    private Integer transitsNumber = 0;

    @Field(MONGO_FIELD_COMMENT)
    private String comment;

    public InfirmaryStatisticsDbVO() {
    }

    public InfirmaryStatisticsDbVO(List<ZonedDateTime> transitDates, List<DrugDbVO> drugs, Integer transitsNumber, String comment) {
        this.transitDates = transitDates;
        this.drugs = drugs;
        this.transitsNumber = transitsNumber;
        this.comment = comment;
    }

    public List<ZonedDateTime> getTransitDates() {
        return transitDates;
    }

    public void setTransitDates(List<ZonedDateTime> transitDates) {
        this.transitDates = transitDates;
    }

    public List<DrugDbVO> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<DrugDbVO> drugs) {
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

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "InfirmaryStatistics{" +
                    "transitDate=" + transitDates +
                    ", drugs=" + drugs +
                    ", transitsNumber=" + transitsNumber +
                    ", comment='" + comment + '\'' +
                    '}';
        }
    }
}
