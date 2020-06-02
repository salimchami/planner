package io.edukativ.myskoolin.infrastructure.common.enums.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumSchoolRoomsTypes;
import io.edukativ.myskoolin.infrastructure.common.utils.EnumSerializeUtil;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class EnumSchoolRoomsTypesSerializer extends StdSerializer<EnumSchoolRoomsTypes> {

    protected EnumSchoolRoomsTypesSerializer() {
        super(EnumSchoolRoomsTypes.class);
    }

    protected EnumSchoolRoomsTypesSerializer(Class<EnumSchoolRoomsTypes> t) {
        super(t);
    }

    @Override
    public void serialize(EnumSchoolRoomsTypes value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        EnumSerializeUtil.serialize(gen, value.name(), value.getCode());
    }
}
