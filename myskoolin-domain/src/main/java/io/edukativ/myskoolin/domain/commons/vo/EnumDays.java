package io.edukativ.myskoolin.domain.commons.vo;

import java.time.DayOfWeek;
import java.util.Arrays;

/**
 * Currency enumeration.
 */
public enum EnumDays {

    SUNDAY("global.enums.days.sunday", 0, DayOfWeek.SUNDAY),
    MONDAY("global.enums.days.monday", 1, DayOfWeek.MONDAY),
    TUESDAY("global.enums.days.tuesday", 2, DayOfWeek.TUESDAY),
    WEDNESDAY("global.enums.days.wednesday", 3, DayOfWeek.WEDNESDAY),
    THURSDAY("global.enums.days.thursday", 4, DayOfWeek.THURSDAY),
    FRIDAY("global.enums.days.friday", 5, DayOfWeek.FRIDAY),
    SATURDAY("global.enums.days.saturday", 6, DayOfWeek.SATURDAY);

    private final String code;
    private final Integer position;
    private final DayOfWeek dayOfWeek;

    EnumDays(String code, int position, DayOfWeek dayOfWeek) {
        this.code = code;
        this.position = position;
        this.dayOfWeek = dayOfWeek;
    }


    public String getCode() {
        return code;
    }

    public int getPosition() {
        return position;
    }

    public static EnumDays dayByCode(String code) {
        return Arrays.stream(EnumDays.values()).filter(day -> day.code.equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalStateException(String.format("Unsupported code %s", code)));
    }

    public static EnumDays dayByPosition(int position) {
        return Arrays.stream(EnumDays.values()).filter(day -> day.position == position)
                .findAny()
                .orElseThrow(() -> new IllegalStateException(String.format("Unsupported code %s", position)));
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
}
