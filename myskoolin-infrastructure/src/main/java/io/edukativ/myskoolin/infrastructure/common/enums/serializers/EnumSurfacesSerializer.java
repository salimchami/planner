package io.edukativ.myskoolin.infrastructure.common.enums.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumSurfaces;
import io.edukativ.myskoolin.infrastructure.common.utils.EnumSerializeUtil;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class EnumSurfacesSerializer extends StdSerializer<EnumSurfaces> {

    protected EnumSurfacesSerializer() {
        super(EnumSurfaces.class);
    }

    protected EnumSurfacesSerializer(Class<EnumSurfaces> t) {
        super(t);
    }

    @Override
    public void serialize(EnumSurfaces value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        EnumSerializeUtil.serialize(jsonGenerator, value.name(), value.getCode());
    }
}
