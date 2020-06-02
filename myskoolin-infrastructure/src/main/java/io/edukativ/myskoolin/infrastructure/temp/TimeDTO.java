package io.edukativ.myskoolin.infrastructure.temp;

import io.edukativ.myskoolin.infrastructure.common.enums.EnumPartsOfDay;

import java.io.Serializable;

public class TimeDTO implements Serializable {

    private Integer hour;
    private Integer minutes;
    private Integer seconds;
    private EnumPartsOfDay partOfDay;

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

    public EnumPartsOfDay getPartOfDay() {
        return partOfDay;
    }

    public void setPartOfDay(EnumPartsOfDay partOfDay) {
        this.partOfDay = partOfDay;
    }
}
