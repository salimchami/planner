package io.edukativ.myskoolin.infrastructure.canteen;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumDays;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Student Canteen registration.
 */
public class CanteenRegistrationDbVO implements Serializable {

    public static final String MONGO_FIELD_RECORDED = "recorded";
    public static final String MONGO_FIELD_BREAKFAST = "breakfast";
    public static final String MONGO_FIELD_LUNCH = "lunch";
    public static final String MONGO_FIELD_DINNER = "dinner";
    public static final String MONGO_FIELD_WEEK_DAYS = "week_days";
    public static final String MONGO_FIELD_SUBSCRIPTION_PERIOD_START = "subscription_period_start";
    public static final String MONGO_FIELD_SUBSCRIPTION_PERIOD_END = "subscription_period_end";

    @Field(MONGO_FIELD_RECORDED)
    private Boolean recorded;

    @Field(MONGO_FIELD_BREAKFAST)
    private Boolean breakfast;

    @Field(MONGO_FIELD_LUNCH)
    private Boolean lunch;

    @Field(MONGO_FIELD_DINNER)
    private Boolean dinner;

    @Field(MONGO_FIELD_WEEK_DAYS)
    private List<EnumDays> weekDays;

    @Field(MONGO_FIELD_SUBSCRIPTION_PERIOD_START)
    private ZonedDateTime subscriptionPeriodStart;

    @Field(MONGO_FIELD_SUBSCRIPTION_PERIOD_END)
    private ZonedDateTime subscriptionPeriodEnd;

    public CanteenRegistrationDbVO() {
    }

    public CanteenRegistrationDbVO(boolean recorded) {
        this.recorded = recorded;
    }

    public CanteenRegistrationDbVO(Boolean recorded, Boolean breakfast, Boolean lunch, Boolean dinner, List<EnumDays> weekDays, ZonedDateTime subscriptionPeriodStart, ZonedDateTime subscriptionPeriodEnd) {
        this.recorded = recorded;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.weekDays = weekDays;
        this.subscriptionPeriodStart = subscriptionPeriodStart;
        this.subscriptionPeriodEnd = subscriptionPeriodEnd;
    }

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
        CanteenRegistrationDbVO that = (CanteenRegistrationDbVO) o;
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
