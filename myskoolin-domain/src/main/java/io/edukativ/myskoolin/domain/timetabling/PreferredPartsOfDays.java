package io.edukativ.myskoolin.domain.timetabling;

import io.edukativ.myskoolin.domain.commons.vo.EnumDays;
import io.edukativ.myskoolin.domain.commons.vo.EnumPartsOfDay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PreferredPartsOfDays {

    private EnumDays day;
    private List<EnumPartsOfDay> partsOfDay;


    public PreferredPartsOfDays() {
    }

    public PreferredPartsOfDays(EnumDays day, EnumPartsOfDay... partsOfDay) {
        this.day = day;
        if (partsOfDay.length > 0) {
            this.partsOfDay = new ArrayList<>(Arrays.asList(partsOfDay));
        } else {
            this.partsOfDay = new ArrayList<>(Arrays.asList(EnumPartsOfDay.AM, EnumPartsOfDay.PM));
        }
    }

    public EnumDays getDay() {
        return day;
    }

    public List<EnumPartsOfDay> getPartsOfDay() {
        return partsOfDay;
    }

    public static List<PreferredPartsOfDays> allDays(List<EnumDays> days) {
        return days.stream().map(PreferredPartsOfDays::new).collect(Collectors.toList());
    }
}
