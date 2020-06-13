package io.edukativ.myskoolin.infrastructure.common.utils;

import io.edukativ.myskoolin.infrastructure.common.enums.EnumPartsOfDay;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class EnumPartsOfDayToCodeConverter implements Converter<EnumPartsOfDay, String> {

    public static final EnumPartsOfDayToCodeConverter INSTANCE = new EnumPartsOfDayToCodeConverter();

    private EnumPartsOfDayToCodeConverter() {
    }

    @Override
    public String convert(EnumPartsOfDay source) {
        return source == null ? "" : source.getCode();
    }

}
