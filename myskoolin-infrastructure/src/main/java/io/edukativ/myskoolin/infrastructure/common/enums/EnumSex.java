package io.edukativ.myskoolin.infrastructure.common.enums;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Currency enumeration.
 */
public enum EnumSex implements Serializable {

    MALE("global.constants.genders.male"),
    FEMALE("global.constants.genders.female");

    private final String code;

    EnumSex(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static EnumSex fromCode(String code) {
        return Arrays.stream(EnumSex.values()).filter(gender -> gender.code.equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalStateException(String.format("Unsupported code %s", code)));

    }
}
