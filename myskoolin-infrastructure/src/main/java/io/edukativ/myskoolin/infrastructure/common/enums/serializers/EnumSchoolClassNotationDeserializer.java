package io.edukativ.myskoolin.infrastructure.common.enums.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumSchoolClassNotation;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class EnumSchoolClassNotationDeserializer extends JsonDeserializer<EnumSchoolClassNotation> {

    @Override
    public EnumSchoolClassNotation deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        String name = node.get("name").asText();
        return EnumSchoolClassNotation.valueOf(name);
    }
}
