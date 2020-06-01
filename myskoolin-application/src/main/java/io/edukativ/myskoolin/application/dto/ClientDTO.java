package io.edukativ.myskoolin.application.dto;

import io.edukativ.myskoolin.infrastructure.common.enums.EnumContactsBy;

import java.util.List;

public class ClientDTO {

    private String id;
    private AddressDTO address;
    private List<EnumContactsBy> contactsBy;
    private List<EmailDTO> emails;
    private String name;
    private List<PhoneDTO> phones;
    private List<WebsiteDTO> websites;
    private String principalWebsite;
    private String surfacesMeasurementUnit;
    private String distancesMeasurementUnit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public List<EnumContactsBy> getContactsBy() {
        return contactsBy;
    }

    public void setContactsBy(List<EnumContactsBy> contactsBy) {
        this.contactsBy = contactsBy;
    }

    public List<EmailDTO> getEmails() {
        return emails;
    }

    public void setEmails(List<EmailDTO> emails) {
        this.emails = emails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PhoneDTO> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDTO> phones) {
        this.phones = phones;
    }

    public List<WebsiteDTO> getWebsites() {
        return websites;
    }

    public void setWebsites(List<WebsiteDTO> websites) {
        this.websites = websites;
    }

    public String getPrincipalWebsite() {
        return principalWebsite;
    }

    public void setPrincipalWebsite(String principalWebsite) {
        this.principalWebsite = principalWebsite;
    }

    public String getSurfacesMeasurementUnit() {
        return surfacesMeasurementUnit;
    }

    public void setSurfacesMeasurementUnit(String surfacesMeasurementUnit) {
        this.surfacesMeasurementUnit = surfacesMeasurementUnit;
    }
}
