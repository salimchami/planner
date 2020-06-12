package io.edukativ.myskoolin.infrastructure.common.utils;

import io.edukativ.myskoolin.infrastructure.common.enums.EnumSchoolRoomsTypesDb;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class EnumSchoolRoomTypesToEnumConverter implements Converter<String, EnumSchoolRoomsTypesDb> {

    public static final EnumSchoolRoomTypesToEnumConverter INSTANCE = new EnumSchoolRoomTypesToEnumConverter();

    private EnumSchoolRoomTypesToEnumConverter() {
    }

    @Override
    public EnumSchoolRoomsTypesDb convert(String source) {
        return EnumSchoolRoomsTypesDb.fromCode(source);
    }

}
