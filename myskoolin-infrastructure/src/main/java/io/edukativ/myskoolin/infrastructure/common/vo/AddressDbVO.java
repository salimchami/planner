package io.edukativ.myskoolin.infrastructure.common.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Postal address.
 */
public class AddressDbVO implements Serializable {

    public static final String FIELD_NAME_NAME = "name";
    public static final String FIELD_NAME_CITY = "city";
    public static final String FIELD_NAME_COUNTRY = "country";
    public static final String FIELD_NAME_POSTAL_CODE = "postal_code";

    @NotNull
    @Field(FIELD_NAME_NAME)
    private String name;

    @Field(FIELD_NAME_POSTAL_CODE)
    private String postalCode;
    @Field(FIELD_NAME_CITY)
    private String city;
    @Field(FIELD_NAME_COUNTRY)
    private String country;

    public AddressDbVO() {
        this.name = "";
    }

    @JsonCreator
    public AddressDbVO(@JsonProperty(FIELD_NAME_NAME) String name, @JsonProperty(FIELD_NAME_POSTAL_CODE) String postalCode,
                       @JsonProperty(FIELD_NAME_CITY) String city, @JsonProperty(FIELD_NAME_COUNTRY) String country) {
        this.name = name;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "Address{" +
                    "name='" + name + '\'' +
                    ", postalCode='" + postalCode + '\'' +
                    ", city='" + city + '\'' +
                    ", country='" + country + '\'' +
                    '}';
        }

    }
}
