package io.edukativ.schooling.myskoolin.domain.vo;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Student Canteen registration.
 */
public class CanteenRegistration {

    private Boolean recorded;
    private Boolean breakfast;
    private Boolean lunch;
    private Boolean dinner;
    private List<EnumDays> weekDays;
    private ZonedDateTime subscriptionPeriodStart;
    private ZonedDateTime subscriptionPeriodEnd;

    public CanteenRegistration() {
    }

    public CanteenRegistration(boolean recorded) {
        this.recorded = recorded;
    }

    public CanteenRegistration(Boolean recorded, Boolean breakfast, Boolean lunch, Boolean dinner, List<EnumDays> weekDays, ZonedDateTime subscriptionPeriodStart, ZonedDateTime subscriptionPeriodEnd) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CanteenRegistration that = (CanteenRegistration) o;
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
