package io.edukativ.myskoolin.infrastructure.common.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.edukativ.myskoolin.infrastructure.common.enums.serializers.EnumPeriodDeserializer;
import io.edukativ.myskoolin.infrastructure.common.enums.serializers.EnumPeriodSerializer;

/**
 * Period enumeration (YEAR, MONTH...).
 */
@JsonSerialize(using = EnumPeriodSerializer.class)
@JsonDeserialize(using = EnumPeriodDeserializer.class)
public enum EnumPeriod {

    YEAR("global.enums.periods.year"),
    SEMESTER("global.enums.periods.semester"),
    QUARTER("global.enums.periods.quarter"),
    MONTH("global.enums.periods.month"),
    WEEK("global.enums.periods.week"),
    DAY("global.enums.periods.day"),
    HOUR("global.enums.periods.hour"),
    MINUTE("global.enums.periods.minute"),
    SECOND("global.enums.periods.second");

    private String code;

    EnumPeriod(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
