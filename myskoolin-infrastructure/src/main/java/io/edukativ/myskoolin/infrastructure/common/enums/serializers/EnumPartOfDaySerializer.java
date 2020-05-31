package io.edukativ.myskoolin.infrastructure.common.enums.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumPartsOfDay;
import io.edukativ.myskoolin.infrastructure.utils.EnumSerializeUtil;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class EnumPartOfDaySerializer extends StdSerializer<EnumPartsOfDay> {

    protected EnumPartOfDaySerializer() {
        super(EnumPartsOfDay.class);
    }

    protected EnumPartOfDaySerializer(Class<EnumPartsOfDay> t) {
        super(t);
    }

    @Override
    public void serialize(EnumPartsOfDay enumPartOfDays, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        EnumSerializeUtil.serialize(jsonGenerator, enumPartOfDays.name(), enumPartOfDays.getCode());
    }
}
