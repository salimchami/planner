package io.edukativ.myskoolin.domain.vo;

import java.util.Arrays;

/**
 * Currency enumeration.
 */
public enum EnumDays {

    SUNDAY("global.enums.days.sunday", 0),
    MONDAY("global.enums.days.monday", 1),
    TUESDAY("global.enums.days.tuesday", 2),
    WEDNESDAY("global.enums.days.wednesday", 3),
    THURSDAY("global.enums.days.thursday", 4),
    FRIDAY("global.enums.days.friday", 5),
    SATURDAY("global.enums.days.saturday", 6);

    private String code;
    private Integer position;

    EnumDays(String code, int position) {
        this.code = code;
        this.position = position;
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
}
