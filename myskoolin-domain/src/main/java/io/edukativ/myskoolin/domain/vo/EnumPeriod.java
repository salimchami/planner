package io.edukativ.myskoolin.domain.vo;

/**
 * Period enumeration (YEAR, MONTH...).
 */
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
