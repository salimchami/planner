package io.edukativ.myskoolin.infrastructure.common.enums;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Currency enumeration.
 */
public enum EnumSex implements Serializable {

    MALE("global.constants.sexes.male"),
    FEMALE("global.constants.sexes.female");

    private String code;

    EnumSex(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static EnumSex fromCode(String code) {
        return Arrays.stream(EnumSex.values()).filter(sex -> sex.code.equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalStateException(String.format("Unsupported code %s", code)));

    }
}
