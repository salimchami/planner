package io.edukativ.myskoolin.infrastructure.common.utils;

import io.edukativ.myskoolin.infrastructure.common.enums.EnumPartsOfDay;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class EnumPartsOfDayToEnumConverter implements Converter<String, EnumPartsOfDay> {

    public static final EnumPartsOfDayToEnumConverter INSTANCE = new EnumPartsOfDayToEnumConverter();

    private EnumPartsOfDayToEnumConverter() {
    }

    @Override
    public EnumPartsOfDay convert(String source) {
        try {
            return EnumPartsOfDay.valueOf(source);
        } catch (Exception e) {
            return EnumPartsOfDay.partOfDayByCode(source);
        }
    }

}
