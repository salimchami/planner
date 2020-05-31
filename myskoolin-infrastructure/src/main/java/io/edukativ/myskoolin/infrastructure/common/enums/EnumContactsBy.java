package io.edukativ.myskoolin.infrastructure.common.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.edukativ.myskoolin.infrastructure.common.enums.serializers.EnumContactsByDeserializer;
import io.edukativ.myskoolin.infrastructure.common.enums.serializers.EnumContactsBySerializer;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Contacts (Communication means) by enumeration.
 */
@JsonSerialize(using = EnumContactsBySerializer.class)
@JsonDeserialize(using = EnumContactsByDeserializer.class)
public enum EnumContactsBy implements Serializable {

    @JsonProperty("PRIMARY_EMAIL")
    PRIMARY_EMAIL("global.enums.contacts-by.primary-email"),
    @JsonProperty("SECONDARY_EMAIL")
    SECONDARY_EMAIL("global.enums.contacts-by.secondary-email"),
    @JsonProperty("PROFESSIONAL_EMAIL")
    PROFESSIONAL_EMAIL("global.enums.contacts-by.professional-email"),
    @JsonProperty("MOBILE_PHONE")
    MOBILE_PHONE("global.enums.contacts-by.mobile-phone"),
    @JsonProperty("PROFESSIONAL_MOBILE_PHONE")
    PROFESSIONAL_MOBILE_PHONE("global.enums.contacts-by.professional-mobile-phone"),
    @JsonProperty("PROFESSIONAL_PHONE")
    PROFESSIONAL_PHONE("global.enums.contacts-by.professional-phone"),
    @JsonProperty("PHONE")
    PHONE("global.enums.contacts-by.phone"),
    @JsonProperty("SMS")
    SMS("global.enums.contacts-by.sms"),
    @JsonProperty("PROFESSIONAL_SMS")
    PROFESSIONAL_SMS("global.enums.contacts-by.professional-sms"),
    @JsonProperty("SCHOOLME_EMAIL")
    SCHOOLME_EMAIL("global.enums.contacts-by.schoolme-email"),
    @JsonProperty("POSTAL_MAIL")
    POSTAL_MAIL("global.enums.contacts-by.postal-mail");

    @JsonProperty("code")
    private String code;

    EnumContactsBy(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static EnumContactsBy contactByByCode(String code) {
        return Arrays.stream(EnumContactsBy.values()).filter(day -> day.code.equals(code))
            .findAny()
            .orElseThrow(() -> new IllegalStateException(String.format("Unsupported code %s", code)));
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }

    public static List<String> names(List<EnumContactsBy> contactsBy) {
        return contactsBy.stream()
            .map(EnumContactsBy::name)
            .collect(Collectors.toList());
    }

    public static List<String> names() {
        return Stream.of(EnumContactsBy.values())
            .map(EnumContactsBy::name)
            .collect(Collectors.toList());
    }
}
