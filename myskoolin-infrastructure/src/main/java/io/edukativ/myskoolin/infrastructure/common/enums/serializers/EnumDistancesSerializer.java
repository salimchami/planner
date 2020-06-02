package io.edukativ.myskoolin.infrastructure.common.enums.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumDistances;
import io.edukativ.myskoolin.infrastructure.common.utils.EnumSerializeUtil;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class EnumDistancesSerializer extends StdSerializer<EnumDistances> {

    protected EnumDistancesSerializer() {
        super(EnumDistances.class);
    }

    protected EnumDistancesSerializer(Class<EnumDistances> t) {
        super(t);
    }

    @Override
    public void serialize(EnumDistances value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        EnumSerializeUtil.serialize(gen, value.name(), value.getCode());
    }

}
