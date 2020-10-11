package io.edukativ.myskoolin.infrastructure.common.enums.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.edukativ.myskoolin.infrastructure.common.utils.EnumSerializeUtil;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.DayOfWeek;

@JsonComponent
public class EnumDaysSerializer extends StdSerializer<DayOfWeek> {

    protected EnumDaysSerializer() {
        super(DayOfWeek.class);
    }

    protected EnumDaysSerializer(Class<DayOfWeek> t) {
        super(t);
    }

    @Override
    public void serialize(DayOfWeek enumDays, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        EnumSerializeUtil.serialize(jsonGenerator, enumDays.name());
    }
}
