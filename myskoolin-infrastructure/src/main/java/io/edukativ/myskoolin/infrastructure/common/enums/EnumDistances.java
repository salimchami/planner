package io.edukativ.myskoolin.infrastructure.common.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.edukativ.myskoolin.infrastructure.common.enums.serializers.EnumDistancesDeserializer;
import io.edukativ.myskoolin.infrastructure.common.enums.serializers.EnumDistancesSerializer;

/**
 * Distances Types enumeration.
 */
@JsonSerialize(using = EnumDistancesSerializer.class)
@JsonDeserialize(using = EnumDistancesDeserializer.class)
public enum EnumDistances {

    METER("global.enums.distances.meter"),
    KILOMETER("global.enums.distances.kilometer"),
    INCH("global.enums.distances.inch"),
    FOOT("global.enums.distances.foot"),
    YARD("global.enums.distances.yard"),
    MILE("global.enums.distances.mile");

    private String code;

    EnumDistances(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
