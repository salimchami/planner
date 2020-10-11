package io.edukativ.myskoolin.infrastructure.timetabling;

import io.edukativ.myskoolin.infrastructure.common.enums.EnumPartsOfDay;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.List;

public class PreferredPartsOfDaysDbVO implements Serializable {

    private DayOfWeek day;
    private List<EnumPartsOfDay> partsOfDay;

    public PreferredPartsOfDaysDbVO() {
    }

    public PreferredPartsOfDaysDbVO(DayOfWeek day, List<EnumPartsOfDay> partsOfDay) {
        this.day = day;
        this.partsOfDay = partsOfDay;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public List<EnumPartsOfDay> getPartsOfDay() {
        return partsOfDay;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public void setPartsOfDay(List<EnumPartsOfDay> partsOfDay) {
        this.partsOfDay = partsOfDay;
    }
}
