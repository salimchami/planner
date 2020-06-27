package io.edukativ.myskoolin.infrastructure.timetabling;

import io.edukativ.myskoolin.infrastructure.common.enums.EnumDays;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumPartsOfDay;

import java.io.Serializable;
import java.util.List;

public class PreferredPartsOfDaysDbVO implements Serializable {

    private EnumDays day;
    private List<EnumPartsOfDay> partsOfDay;

    public PreferredPartsOfDaysDbVO() {
    }

    public PreferredPartsOfDaysDbVO(EnumDays day, List<EnumPartsOfDay> partsOfDay) {
        this.day = day;
        this.partsOfDay = partsOfDay;
    }

    public EnumDays getDay() {
        return day;
    }

    public List<EnumPartsOfDay> getPartsOfDay() {
        return partsOfDay;
    }

    public void setDay(EnumDays day) {
        this.day = day;
    }

    public void setPartsOfDay(List<EnumPartsOfDay> partsOfDay) {
        this.partsOfDay = partsOfDay;
    }
}
