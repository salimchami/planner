package io.edukativ.myskoolin.infrastructure.common.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.edukativ.myskoolin.infrastructure.common.enums.serializers.EnumCurrencySerializer;
import io.edukativ.myskoolin.infrastructure.common.enums.serializers.EnumCurrencyDeserializer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Currency enumeration.
 */
@JsonSerialize(using = EnumCurrencySerializer.class)
@JsonDeserialize(using = EnumCurrencyDeserializer.class)
public enum EnumCurrency {

    EURO("global.enums.currency.singular.euro", "global.enums.currency.plural.euros", "€"),
    DOLLAR("global.enums.currency.singular.dollar", "global.enums.currency.plural.dollars", "$"),
    DIRHAM("global.enums.currency.singular.dirham", "global.enums.currency.plural.dirhams", "MDH");

    private String code;
    private String pluralCode;
    private String symbol;

    EnumCurrency(String code, String pluralCode, String symbol) {
        this.code = code;
        this.pluralCode = pluralCode;
        this.symbol = symbol;
    }

    public String getCode() {
        return code;
    }

    public String getPluralCode() {
        return pluralCode;
    }

    public String getSymbol() {
        return symbol;
    }

    public static List<String> names(List<EnumCurrency> currencies) {
        return currencies.stream()
            .map(EnumCurrency::name)
            .collect(Collectors.toList());
    }

    public static List<String> names() {
        return Stream.of(EnumCurrency.values())
            .map(EnumCurrency::name)
            .collect(Collectors.toList());
    }


}
