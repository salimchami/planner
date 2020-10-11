package io.edukativ.myskoolin.infrastructure.timetabling;

import io.edukativ.myskoolin.infrastructure.common.enums.EnumPartsOfDay;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class PreferredPartsOfDaysVO implements Serializable {

    private DayOfWeek day;
    private List<EnumPartsOfDay> partsOfDay;

    public PreferredPartsOfDaysVO() {
    }

    public PreferredPartsOfDaysVO(DayOfWeek day, List<EnumPartsOfDay> partsOfDay) {
        this.day = day;
        this.partsOfDay = partsOfDay;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public List<EnumPartsOfDay> getPartsOfDay() {
        if(this.partsOfDay == null) {
            this.partsOfDay = new ArrayList<>();
        }
        return partsOfDay;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public void setPartsOfDay(List<EnumPartsOfDay> partsOfDay) {
        this.partsOfDay = partsOfDay;
    }
}
