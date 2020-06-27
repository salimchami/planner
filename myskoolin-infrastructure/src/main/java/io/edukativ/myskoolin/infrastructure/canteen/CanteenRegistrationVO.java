package io.edukativ.myskoolin.infrastructure.canteen;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumDays;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Student Canteen registration.
 */
public class CanteenRegistrationVO implements Serializable {

    private Boolean recorded;
    private Boolean breakfast;
    private Boolean lunch;
    private Boolean dinner;
    private List<EnumDays> weekDays;
    private ZonedDateTime subscriptionPeriodStart;
    private ZonedDateTime subscriptionPeriodEnd;

    public Boolean getRecorded() {
        return recorded;
    }

    public void setRecorded(Boolean recorded) {
        this.recorded = recorded;
    }

    public Boolean getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Boolean breakfast) {
        this.breakfast = breakfast;
    }

    public Boolean getLunch() {
        return lunch;
    }

    public void setLunch(Boolean lunch) {
        this.lunch = lunch;
    }

    public Boolean getDinner() {
        return dinner;
    }

    public void setDinner(Boolean dinner) {
        this.dinner = dinner;
    }

    public List<EnumDays> getWeekDays() {
        if (this.weekDays == null) {
            this.weekDays = new ArrayList<>();
        }
        return weekDays;
    }

    public void setWeekDays(List<EnumDays> weekDays) {
        this.weekDays = weekDays;
    }

    public ZonedDateTime getSubscriptionPeriodStart() {
        return subscriptionPeriodStart;
    }

    public void setSubscriptionPeriodStart(ZonedDateTime subscriptionPeriodStart) {
        this.subscriptionPeriodStart = subscriptionPeriodStart;
    }

    public ZonedDateTime getSubscriptionPeriodEnd() {
        return subscriptionPeriodEnd;
    }

    public void setSubscriptionPeriodEnd(ZonedDateTime subscriptionPeriodEnd) {
        this.subscriptionPeriodEnd = subscriptionPeriodEnd;
    }

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "CanteenRegistration{" +
                    "recorded=" + recorded +
                    ", breakfast=" + breakfast +
                    ", lunch=" + lunch +
                    ", dinner=" + dinner +
                    ", weekDays=" + weekDays +
                    ", subscriptionPeriodStart=" + subscriptionPeriodStart +
                    ", subscriptionPeriodEnd=" + subscriptionPeriodEnd +
                    '}';
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CanteenRegistrationVO that = (CanteenRegistrationVO) o;
        return recorded.equals(that.recorded) &&
                Objects.equals(breakfast, that.breakfast) &&
                Objects.equals(lunch, that.lunch) &&
                Objects.equals(dinner, that.dinner) &&
                Objects.equals(weekDays, that.weekDays) &&
                Objects.equals(subscriptionPeriodStart, that.subscriptionPeriodStart) &&
                Objects.equals(subscriptionPeriodEnd, that.subscriptionPeriodEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recorded, breakfast, lunch, dinner, weekDays, subscriptionPeriodStart, subscriptionPeriodEnd);
    }


}
