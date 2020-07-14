package io.edukativ.myskoolin.infrastructure.timetabling;

import io.edukativ.myskoolin.domain.commons.vo.EnumDays;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class TimeSlotVO implements Serializable {

    private String id;
    private String title;
    private String secondTitle;
    private String comment;
    private Boolean canceled;
    private EnumDays day;
    private TimeVO startTime;
    private TimeVO endTime;
    private ZonedDateTime date;
    private String bgColor;
    private String fontColorCssClass;
    private Boolean autoAlterable;
    private boolean half;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSecondTitle() {
        return secondTitle;
    }

    public void setSecondTitle(String secondTitle) {
        this.secondTitle = secondTitle;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }

    public EnumDays getDay() {
        return day;
    }

    public void setDay(EnumDays day) {
        this.day = day;
    }

    public TimeVO getStartTime() {
        return startTime;
    }

    public void setStartTime(TimeVO startTime) {
        this.startTime = startTime;
    }

    public TimeVO getEndTime() {
        return endTime;
    }

    public void setEndTime(TimeVO endTime) {
        this.endTime = endTime;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getFontColorCssClass() {
        return fontColorCssClass;
    }

    public void setFontColorCssClass(String fontColorCssClass) {
        this.fontColorCssClass = fontColorCssClass;
    }

    public Boolean getAutoAlterable() {
        return autoAlterable;
    }

    public void setAutoAlterable(Boolean autoAlterable) {
        this.autoAlterable = autoAlterable;
    }

    public boolean isHalf() {
        return half;
    }

    public void setHalf(boolean half) {
        this.half = half;
    }
}
