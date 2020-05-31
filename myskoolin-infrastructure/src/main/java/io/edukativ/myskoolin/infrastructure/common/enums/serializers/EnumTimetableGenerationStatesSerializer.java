package io.edukativ.myskoolin.infrastructure.common.enums.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumTimetableGenerationStates;
import io.edukativ.myskoolin.infrastructure.utils.EnumSerializeUtil;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class EnumTimetableGenerationStatesSerializer extends StdSerializer<EnumTimetableGenerationStates> {

    protected EnumTimetableGenerationStatesSerializer() {
        super(EnumTimetableGenerationStates.class);
    }

    protected EnumTimetableGenerationStatesSerializer(Class<EnumTimetableGenerationStates> t) {
        super(t);
    }

    @Override
    public void serialize(EnumTimetableGenerationStates value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        EnumSerializeUtil.serialize(jsonGenerator, value.name(), value.getCode());
    }
}
