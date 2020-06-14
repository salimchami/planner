package io.edukativ.myskoolin.domain.entity;

/**
 * Postal address.
 */
public class Address {

    private String name;
    private String postalCode;
    private String city;
    private String country;

    public Address() {
    }

    public Address(String name, String postalCode, String city, String country) {
        this.name = name;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
