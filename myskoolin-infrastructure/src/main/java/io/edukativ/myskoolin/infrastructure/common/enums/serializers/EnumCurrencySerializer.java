package io.edukativ.myskoolin.infrastructure.common.enums.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumCurrency;
import io.edukativ.myskoolin.infrastructure.utils.EnumSerializeUtil;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class EnumCurrencySerializer extends StdSerializer<EnumCurrency> {

    protected EnumCurrencySerializer() {
        super(EnumCurrency.class);
    }

    protected EnumCurrencySerializer(Class<EnumCurrency> t) {
        super(t);
    }

    @Override
    public void serialize(EnumCurrency enumCurrency, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        EnumSerializeUtil.serialize(jsonGenerator, enumCurrency.name(), enumCurrency.getCode(), enumCurrency.getPluralCode(), enumCurrency.getSymbol());
    }
}
