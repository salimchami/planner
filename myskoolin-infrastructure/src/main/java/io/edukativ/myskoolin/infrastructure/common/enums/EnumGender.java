package io.edukativ.myskoolin.infrastructure.common.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.edukativ.myskoolin.infrastructure.common.enums.serializers.EnumGenderDeserializer;
import io.edukativ.myskoolin.infrastructure.common.enums.serializers.EnumGenderSerializer;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Currency enumeration.
 */
@JsonSerialize(using = EnumGenderSerializer.class)
@JsonDeserialize(using = EnumGenderDeserializer.class)
public enum EnumGender implements Serializable {

    MR("global.constants.genders.mr"),
    MRS("global.constants.genders.mrs"),
    MISS("global.constants.genders.miss");

    private String code;

    EnumGender(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static EnumGender fromCode(String code) {
        return Arrays.stream(EnumGender.values()).filter(gender -> gender.code.equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalStateException(String.format("Unsupported code %s", code)));

    }

}
