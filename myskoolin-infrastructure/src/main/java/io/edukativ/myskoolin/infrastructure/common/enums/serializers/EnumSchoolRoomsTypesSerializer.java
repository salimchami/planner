package io.edukativ.myskoolin.infrastructure.common.enums.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumSchoolRoomsTypesDb;
import io.edukativ.myskoolin.infrastructure.common.utils.EnumSerializeUtil;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class EnumSchoolRoomsTypesSerializer extends StdSerializer<EnumSchoolRoomsTypesDb> {

    protected EnumSchoolRoomsTypesSerializer() {
        super(EnumSchoolRoomsTypesDb.class);
    }

    protected EnumSchoolRoomsTypesSerializer(Class<EnumSchoolRoomsTypesDb> t) {
        super(t);
    }

    @Override
    public void serialize(EnumSchoolRoomsTypesDb value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        EnumSerializeUtil.serialize(gen, value.name(), value.getCode());
    }
}
