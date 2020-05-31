package io.edukativ.myskoolin.infrastructure.utils;

import io.edukativ.myskoolin.infrastructure.common.enums.EnumSchoolRoomsTypes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class EnumSchoolRoomTypesToCodeConverter implements Converter<EnumSchoolRoomsTypes, String> {

    public static final EnumSchoolRoomTypesToCodeConverter INSTANCE = new EnumSchoolRoomTypesToCodeConverter();

    private EnumSchoolRoomTypesToCodeConverter() {
    }

    @Override
    public String convert(EnumSchoolRoomsTypes source) {
        return source == null ? "" : source.getCode();
    }

}
