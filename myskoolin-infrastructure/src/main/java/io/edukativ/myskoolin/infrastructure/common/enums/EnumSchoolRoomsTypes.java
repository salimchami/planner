package io.edukativ.myskoolin.infrastructure.common.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.edukativ.myskoolin.infrastructure.common.enums.serializers.EnumSchoolRoomsTypesDeserializer;
import io.edukativ.myskoolin.infrastructure.common.enums.serializers.EnumSchoolRoomsTypesSerializer;

import java.util.Arrays;

/**
 * Currency enumeration.
 */
@JsonSerialize(using = EnumSchoolRoomsTypesSerializer.class)
@JsonDeserialize(using = EnumSchoolRoomsTypesDeserializer.class)
public enum EnumSchoolRoomsTypes {

    NORMAL("schoolRoom.enums.types.normal"),
    SCIENCES("schoolRoom.enums.types.sciences"),
    MUSIC("schoolRoom.enums.types.music"),
    SPORT("schoolRoom.enums.types.sport"),
    IT("schoolRoom.enums.types.it"),
    MULTIPURPOSE("schoolRoom.enums.types.multipurpose"),
    STEPPED("schoolRoom.enums.types.stepped"),
    AMPHITHEATER("schoolRoom.enums.types.amphitheater");

    private final String code;

    EnumSchoolRoomsTypes(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static EnumSchoolRoomsTypes fromCode(String code) {
        return Arrays.stream(EnumSchoolRoomsTypes.values())
            .filter(type -> code.equals(type.getCode()))
            .findFirst()
            .orElse(null);
    }
}
