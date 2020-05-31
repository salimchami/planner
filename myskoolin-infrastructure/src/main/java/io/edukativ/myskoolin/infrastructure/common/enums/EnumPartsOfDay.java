package io.edukativ.myskoolin.infrastructure.common.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.edukativ.myskoolin.infrastructure.common.enums.serializers.EnumPartOfDayDeserializer;
import io.edukativ.myskoolin.infrastructure.common.enums.serializers.EnumPartOfDaySerializer;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * Currency enumeration.
 */
@JsonSerialize(using = EnumPartOfDaySerializer.class)
@JsonDeserialize(using = EnumPartOfDayDeserializer.class)
public enum EnumPartsOfDay {

    AM("global.enums.parts-of-day.am"),
    PM("global.enums.parts-of-day.pm");

    private String code;

    EnumPartsOfDay(String code){
        this.code = code;
    }

    public static EnumPartsOfDay fromLocalTime(LocalTime localTime) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("hh:mm a");
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
