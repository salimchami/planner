package io.edukativ.myskoolin.infrastructure.common.utils;

import io.edukativ.myskoolin.infrastructure.common.enums.EnumSchoolRoomsTypes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class EnumSchoolRoomTypesToEnumConverter implements Converter<String, EnumSchoolRoomsTypes> {

    public static final EnumSchoolRoomTypesToEnumConverter INSTANCE = new EnumSchoolRoomTypesToEnumConverter();

    private EnumSchoolRoomTypesToEnumConverter() {
    }

    @Override
    public EnumSchoolRoomsTypes convert(String source) {
        return EnumSchoolRoomsTypes.fromCode(source);
    }

}
