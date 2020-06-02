package io.edukativ.myskoolin.infrastructure.common.utils;

import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;

public final class EnumSerializeUtil {

    private EnumSerializeUtil() {}

    public static void serialize(JsonGenerator gen, String name) throws IOException {
        gen.writeStartObject();
        gen.writeFieldName("name");
        gen.writeString(name);
        gen.writeEndObject();
    }

    public static void serialize(JsonGenerator gen, String name, String code) throws IOException {
        gen.writeStartObject();
        gen.writeFieldName("name");
        gen.writeString(name);
        gen.writeFieldName("code");
        gen.writeString(code);
        gen.writeEndObject();
    }

    public static void serialize(JsonGenerator gen, String name, String code, Integer position) throws IOException {
        gen.writeStartObject();
        gen.writeFieldName("name");
        gen.writeString(name);
        gen.writeFieldName("code");
        gen.writeString(code);
        gen.writeFieldName("position");
        gen.writeNumber(position);
        gen.writeEndObject();
    }
    public static void serialize(JsonGenerator gen, String name, String code, Integer position, Integer defaultPeriodsNbPerYear) throws IOException {
        gen.writeStartObject();
        gen.writeFieldName("name");
        gen.writeString(name);
        gen.writeFieldName("code");
        gen.writeString(code);
        gen.writeFieldName("position");
        gen.writeNumber(position);
        gen.writeFieldName("defaultPeriodsNbPerYear");
        gen.writeNumber(defaultPeriodsNbPerYear);
        gen.writeEndObject();
    }

    public static void serialize(JsonGenerator gen, String name, String code, String pluralCode, String symbol) throws IOException {
        gen.writeStartObject();
        gen.writeFieldName("name");
        gen.writeString(name);
        gen.writeFieldName("code");
        gen.writeString(code);
        gen.writeFieldName("pluralCode");
        gen.writeString(pluralCode);
        gen.writeFieldName("symbol");
        gen.writeString(symbol);
        gen.writeEndObject();
    }
}
