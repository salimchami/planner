package io.edukativ.myskoolin.infrastructure.utils;

import io.edukativ.myskoolin.infrastructure.common.enums.EnumDays;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class EnumDaysToEnumConverter implements Converter<String, EnumDays> {

    public static final EnumDaysToEnumConverter INSTANCE = new EnumDaysToEnumConverter();

    private EnumDaysToEnumConverter() {
    }

    @Override
    public EnumDays convert(String source) {
        try {
            return EnumDays.valueOf(source);
        } catch (Exception e) {
            return EnumDays.dayByCode(source);
        }
    }

}
