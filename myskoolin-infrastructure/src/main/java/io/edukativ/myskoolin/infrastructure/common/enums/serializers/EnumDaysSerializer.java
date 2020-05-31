package io.edukativ.myskoolin.infrastructure.common.enums.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumDays;
import io.edukativ.myskoolin.infrastructure.utils.EnumSerializeUtil;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class EnumDaysSerializer extends StdSerializer<EnumDays> {

    protected EnumDaysSerializer() {
        super(EnumDays.class);
    }

    protected EnumDaysSerializer(Class<EnumDays> t) {
        super(t);
    }

    @Override
    public void serialize(EnumDays enumDays, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        EnumSerializeUtil.serialize(jsonGenerator, enumDays.name(), enumDays.getCode(), enumDays.getPosition());
    }
}
