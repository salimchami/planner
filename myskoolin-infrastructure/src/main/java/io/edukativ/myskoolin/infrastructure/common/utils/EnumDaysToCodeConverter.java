package io.edukativ.myskoolin.infrastructure.common.utils;

import io.edukativ.myskoolin.infrastructure.common.enums.EnumDays;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class EnumDaysToCodeConverter implements Converter<EnumDays, String> {

    public static final EnumDaysToCodeConverter INSTANCE = new EnumDaysToCodeConverter();

    private EnumDaysToCodeConverter() {
    }

    @Override
    public String convert(EnumDays source) {
        return source == null ? "" : source.getCode();
    }

}
