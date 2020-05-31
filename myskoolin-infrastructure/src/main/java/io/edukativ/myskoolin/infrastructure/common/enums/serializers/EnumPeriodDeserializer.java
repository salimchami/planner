package io.edukativ.myskoolin.infrastructure.common.enums.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumPeriod;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class EnumPeriodDeserializer extends JsonDeserializer<EnumPeriod> {

    @Override
    public EnumPeriod deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        String name = node.get("name").asText();
        return EnumPeriod.valueOf(name);
    }
}
