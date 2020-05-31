package io.edukativ.myskoolin.infrastructure.common.enums.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumFilesTypes;
import io.edukativ.myskoolin.infrastructure.utils.EnumSerializeUtil;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class EnumFilesTypesSerializer extends StdSerializer<EnumFilesTypes> {

    protected EnumFilesTypesSerializer() {
        super(EnumFilesTypes.class);
    }

    protected EnumFilesTypesSerializer(Class<EnumFilesTypes> t) {
        super(t);
    }

    @Override
    public void serialize(EnumFilesTypes value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        EnumSerializeUtil.serialize(gen, value.name(), value.getCode());
    }

}
