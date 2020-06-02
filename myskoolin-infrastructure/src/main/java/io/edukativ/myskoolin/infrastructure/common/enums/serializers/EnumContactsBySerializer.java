package io.edukativ.myskoolin.infrastructure.common.enums.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumContactsBy;
import io.edukativ.myskoolin.infrastructure.common.utils.EnumSerializeUtil;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;


@JsonComponent
public class EnumContactsBySerializer extends StdSerializer<EnumContactsBy> {

    protected EnumContactsBySerializer() {
        super(EnumContactsBy.class);
    }

    protected EnumContactsBySerializer(Class<EnumContactsBy> t) {
        super(t);
    }

    @Override
    public void serialize(EnumContactsBy enumContactsBy, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        EnumSerializeUtil.serialize(jsonGenerator, enumContactsBy.name(), enumContactsBy.getCode());
    }
}

