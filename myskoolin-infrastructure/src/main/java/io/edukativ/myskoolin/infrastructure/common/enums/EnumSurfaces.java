package io.edukativ.myskoolin.infrastructure.common.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.edukativ.myskoolin.infrastructure.common.enums.serializers.EnumSurfacesDeserializer;
import io.edukativ.myskoolin.infrastructure.common.enums.serializers.EnumSurfacesSerializer;

import java.util.Arrays;

/**
 * Currency enumeration.
 */
@JsonSerialize(using = EnumSurfacesSerializer.class)
@JsonDeserialize(using = EnumSurfacesDeserializer.class)
public enum EnumSurfaces {

    SQUARE_METER("global.enums.surfaces.squareMeter"),
    SQUARE_KILOMETER("global.enums.surfaces.squareKilometer"),
    SQUARE_INCH("global.enums.surfaces.squareInch"),
    SQUARE_FOOT("global.enums.surfaces.squareFoot"),
    SQUARE_MILE("global.enums.surfaces.squareMile");

    private String code;

    EnumSurfaces(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static EnumSurfaces fromCode(String code) {
        return Arrays.asList(EnumSurfaces.values())
            .stream()
            .filter(surface -> code.equals(surface.getCode()))
            .findFirst()
            .orElse(null);
    }
}
