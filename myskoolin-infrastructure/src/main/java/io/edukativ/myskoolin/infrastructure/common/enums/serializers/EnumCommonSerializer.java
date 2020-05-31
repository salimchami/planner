package io.edukativ.myskoolin.infrastructure.common.enums.serializers;

import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class EnumCommonSerializer {

    public static final String FIELD_NAME_NAME = "name";
    public static final  String FIELD_NAME_CODE = "code";
    public static final String FIELD_NAME_PLURAL_CODE = "pluralCode";
    public static final String FIELD_NAME_SYMBOL = "symbol";
    public static final String FIELD_NAME_POSITION = "position";
}
