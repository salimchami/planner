package io.edukativ.myskoolin.infrastructure.common.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.time.DayOfWeek;

@WritingConverter
public class EnumDaysToCodeConverter implements Converter<DayOfWeek, String> {

    public static final EnumDaysToCodeConverter INSTANCE = new EnumDaysToCodeConverter();

    private EnumDaysToCodeConverter() {
    }

    @Override
    public String convert(DayOfWeek source) {
        return source == null ? "" : source.name();
    }

}
