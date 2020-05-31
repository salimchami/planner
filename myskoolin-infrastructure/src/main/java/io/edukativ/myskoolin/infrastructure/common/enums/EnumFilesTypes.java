package io.edukativ.myskoolin.infrastructure.common.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.edukativ.myskoolin.infrastructure.common.enums.serializers.EnumFilesTypesDeserializer;
import io.edukativ.myskoolin.infrastructure.common.enums.serializers.EnumFilesTypesSerializer;

@JsonSerialize(using = EnumFilesTypesSerializer.class)
@JsonDeserialize(using = EnumFilesTypesDeserializer.class)
public enum EnumFilesTypes {

    CLIENT_LOGO("global.enums.files.client-logo");

    private String code;

    EnumFilesTypes(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
