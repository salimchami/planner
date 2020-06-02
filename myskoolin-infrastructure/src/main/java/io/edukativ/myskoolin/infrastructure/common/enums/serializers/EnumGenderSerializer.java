package io.edukativ.myskoolin.infrastructure.common.enums.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumGender;
import io.edukativ.myskoolin.infrastructure.common.utils.EnumSerializeUtil;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class EnumGenderSerializer extends StdSerializer<EnumGender> {

    protected EnumGenderSerializer() {
        super(EnumGender.class);
    }

    protected EnumGenderSerializer(Class<EnumGender> t) {
        super(t);
    }

    @Override
    public void serialize(EnumGender enumGender, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        EnumSerializeUtil.serialize(jsonGenerator, enumGender.name(), enumGender.getCode());
    }
}
