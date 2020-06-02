package io.edukativ.myskoolin.infrastructure.common.enums.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumSchoolClassNotation;
import io.edukativ.myskoolin.infrastructure.common.utils.EnumSerializeUtil;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class EnumSchoolClassNotationSerializer extends StdSerializer<EnumSchoolClassNotation> {

    protected EnumSchoolClassNotationSerializer() {
        super(EnumSchoolClassNotation.class);
    }

    protected EnumSchoolClassNotationSerializer(Class<EnumSchoolClassNotation> t) {
        super(t);
    }

    @Override
    public void serialize(EnumSchoolClassNotation value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        EnumSerializeUtil.serialize(jsonGenerator, value.name(), value.getCode(), value.getPosition(), value.getDefaultPeriodsNbPerYear());
    }
}
