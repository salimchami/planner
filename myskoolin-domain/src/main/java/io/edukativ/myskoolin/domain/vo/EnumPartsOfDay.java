package io.edukativ.myskoolin.domain.vo;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * Currency enumeration.
 */
public enum EnumPartsOfDay {

    AM("global.enums.parts-of-day.am"),
    PM("global.enums.parts-of-day.pm");

    static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("hh:mm a");
    private String code;

    EnumPartsOfDay(String code) {
        this.code = code;
    }

    public static EnumPartsOfDay fromLocalTime(LocalTime localTime) {
        return localTime.format(fmt).contains(AM.name()) ? AM : PM;
    }

    public String getCode() {
        return code;
    }

    public static EnumPartsOfDay partOfDayByCode(String code) {
        return Arrays.stream(EnumPartsOfDay.values()).filter(day -> day.code.equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalStateException(String.format("Unsupported code %s", code)));
    }
}
