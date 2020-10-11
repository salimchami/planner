package io.edukativ.myskoolin.infrastructure.common.enums.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.DayOfWeek;

@JsonComponent
public class EnumDaysDeserializer extends JsonDeserializer<DayOfWeek> {

    @Override
    public DayOfWeek deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        String name = node.get("name").asText();
        return DayOfWeek.valueOf(name);
    }
}
