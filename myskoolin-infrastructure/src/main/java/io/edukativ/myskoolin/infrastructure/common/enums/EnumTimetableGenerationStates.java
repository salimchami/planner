package io.edukativ.myskoolin.infrastructure.common.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.edukativ.myskoolin.infrastructure.common.enums.serializers.EnumTimetableGenerationStatesSerializer;
import io.edukativ.myskoolin.infrastructure.common.enums.serializers.EnumTimetableGenerationStatesDeserializer;

/**
 * Contacts (Communication means) by enumeration.
 */
@JsonSerialize(using = EnumTimetableGenerationStatesSerializer.class)
@JsonDeserialize(using = EnumTimetableGenerationStatesDeserializer.class)
public enum EnumTimetableGenerationStates {

    NO_TIMETABLE("global.enums.tt-generation-states.no-tt"),
    TT_PARTIALLY_GENERATED("global.enums.tt-generation-states.partially-generated"),
    GENERATION_COMPLETE("global.enums.tt-generation-states.generation-completed");

    private String code;

    EnumTimetableGenerationStates(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
