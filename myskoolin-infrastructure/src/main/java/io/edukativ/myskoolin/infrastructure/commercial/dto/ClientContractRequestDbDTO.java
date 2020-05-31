package io.edukativ.myskoolin.infrastructure.commercial.dto;

import org.bson.types.ObjectId;

import java.time.ZonedDateTime;

public class ClientContractRequestDbDTO {

    private ObjectId id;
    public String vatNumber;
    public ZonedDateTime startDateMin;
    public ZonedDateTime startDateMax;
    public ZonedDateTime endDate;
    public String wantedPricingId;

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public ZonedDateTime getStartDateMin() {
        return startDateMin;
    }

    public void setStartDateMin(ZonedDateTime startDateMin) {
        this.startDateMin = startDateMin;
    }

    public ZonedDateTime getStartDateMax() {
        return startDateMax;
    }

    public void setStartDateMax(ZonedDateTime startDateMax) {
        this.startDateMax = startDateMax;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public String getWantedPricingId() {
        return wantedPricingId;
    }

    public void setWantedPricingId(String wantedPricingId) {
        this.wantedPricingId = wantedPricingId;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
