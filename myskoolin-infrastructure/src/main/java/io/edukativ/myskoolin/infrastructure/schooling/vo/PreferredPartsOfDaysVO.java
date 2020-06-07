package io.edukativ.myskoolin.infrastructure.schooling.vo;

import io.edukativ.myskoolin.infrastructure.common.enums.EnumDays;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumPartsOfDay;

import java.util.List;

public class PreferredPartsOfDaysVO {

    private EnumDays day;
    private List<EnumPartsOfDay> partsOfDay;

    public PreferredPartsOfDaysVO() {
    }

    public PreferredPartsOfDaysVO(EnumDays day, List<EnumPartsOfDay> partsOfDay) {
        this.day = day;
        this.partsOfDay = partsOfDay;
    }

    public EnumDays getDay() {
        return day;
    }

    public List<EnumPartsOfDay> getPartsOfDay() {
        return partsOfDay;
    }
}
