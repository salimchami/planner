package io.edukativ.myskoolin.infrastructure.common.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.DayOfWeek;

@ReadingConverter
public class EnumDaysToEnumConverter implements Converter<String, DayOfWeek> {

    public static final EnumDaysToEnumConverter INSTANCE = new EnumDaysToEnumConverter();

    private EnumDaysToEnumConverter() {
    }

    @Override
    public DayOfWeek convert(String source) {
        try {
            return DayOfWeek.valueOf(source);
        } catch (Exception e) {
            return DayOfWeek.valueOf(source);
        }
    }

}
