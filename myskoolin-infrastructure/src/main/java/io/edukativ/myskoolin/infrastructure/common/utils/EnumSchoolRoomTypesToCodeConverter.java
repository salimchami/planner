package io.edukativ.myskoolin.infrastructure.common.utils;

import io.edukativ.myskoolin.infrastructure.common.enums.EnumSchoolRoomsTypesDb;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class EnumSchoolRoomTypesToCodeConverter implements Converter<EnumSchoolRoomsTypesDb, String> {

    public static final EnumSchoolRoomTypesToCodeConverter INSTANCE = new EnumSchoolRoomTypesToCodeConverter();

    private EnumSchoolRoomTypesToCodeConverter() {
    }

    @Override
    public String convert(EnumSchoolRoomsTypesDb source) {
        return source == null ? "" : source.getCode();
    }

}
