package io.edukativ.myskoolin.infrastructure.common.enums.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumPeriod;
import io.edukativ.myskoolin.infrastructure.utils.EnumSerializeUtil;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class EnumPeriodSerializer extends StdSerializer<EnumPeriod> {

    protected EnumPeriodSerializer() {
        super(EnumPeriod.class);
    }

    protected EnumPeriodSerializer(Class<EnumPeriod> t) {
        super(t);
    }

    @Override
    public void serialize(EnumPeriod enumDays, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        EnumSerializeUtil.serialize(jsonGenerator, enumDays.name(), enumDays.getCode());
    }
}
