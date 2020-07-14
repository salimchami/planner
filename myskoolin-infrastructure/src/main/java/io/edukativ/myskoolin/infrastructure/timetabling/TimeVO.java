package io.edukativ.myskoolin.infrastructure.timetabling;

import java.io.Serializable;

public class TimeVO implements Serializable {

    private Integer hour;
    private Integer minutes;
    private Integer seconds;
    private String partOfDay;

    public TimeVO(Integer hour, Integer minutes, Integer seconds, String partOfDay) {
        this.hour = hour;
        this.minutes = minutes;
        this.seconds = seconds;
        this.partOfDay = partOfDay;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Integer getSeconds() {
        return seconds;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

    public String getPartOfDay() {
        return partOfDay;
    }

    public void setPartOfDay(String partOfDay) {
        this.partOfDay = partOfDay;
    }
}
