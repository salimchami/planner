package io.edukativ.myskoolin.infrastructure.commercial;

import io.edukativ.myskoolin.infrastructure.common.enums.EnumContactsBy;
import io.edukativ.myskoolin.infrastructure.common.vo.AddressDbVO;
import io.edukativ.myskoolin.infrastructure.common.vo.EmailDbVO;
import io.edukativ.myskoolin.infrastructure.common.vo.PhoneDbVO;
import io.edukativ.myskoolin.infrastructure.common.vo.WebsiteDbVO;
import io.edukativ.myskoolin.infrastructure.schooling.vo.TimeTableOptionsDbVO;
import org.bson.types.ObjectId;

import java.time.ZonedDateTime;
import java.util.List;

public class ClientFullDTO {

    private ObjectId id;
    private AddressDbVO address;
    private String dunsNumber;
    private List<EnumContactsBy> contactsBy;
    private TimeTableOptionsDbVO timeTableOptions;
    private List<EmailDbVO> emails;
    private Integer majorityAge;
    private String name;
    private Integer nbMaxOfStudentsPerSchoolClass;
    private List<PhoneDbVO> phones;
    private ZonedDateTime scholarYearStart;
    private ZonedDateTime scholarYearEnd;
    private ContractDbDTO contract;
    private String iban;
    private String bic;
    private List<WebsiteDbVO> websites;
    private String principalWebsite;
    private Boolean deleted;
    private String surfacesMeasurementUnit;
    private String distancesMeasurementUnit;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public AddressDbVO getAddress() {
        return address;
    }

    public void setAddress(AddressDbVO address) {
        this.address = address;
    }

    public String getDunsNumber() {
        return dunsNumber;
    }

    public void setDunsNumber(String dunsNumber) {
        this.dunsNumber = dunsNumber;
    }

    public List<EnumContactsBy> getContactsBy() {
        return contactsBy;
    }

    public void setContactsBy(List<EnumContactsBy> contactsBy) {
        this.contactsBy = contactsBy;
    }

    public TimeTableOptionsDbVO getTimeTableOptions() {
        return timeTableOptions;
    }

    public void setTimeTableOptions(TimeTableOptionsDbVO timeTableOptions) {
        this.timeTableOptions = timeTableOptions;
    }

    public List<EmailDbVO> getEmails() {
        return emails;
    }

    public void setEmails(List<EmailDbVO> emails) {
        this.emails = emails;
    }

    public Integer getMajorityAge() {
        return majorityAge;
    }

    public void setMajorityAge(Integer majorityAge) {
        this.majorityAge = majorityAge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNbMaxOfStudentsPerSchoolClass() {
        return nbMaxOfStudentsPerSchoolClass;
    }

    public void setNbMaxOfStudentsPerSchoolClass(Integer nbMaxOfStudentsPerSchoolClass) {
        this.nbMaxOfStudentsPerSchoolClass = nbMaxOfStudentsPerSchoolClass;
    }

    public List<PhoneDbVO> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDbVO> phones) {
        this.phones = phones;
    }

    public ZonedDateTime getScholarYearStart() {
        return scholarYearStart;
    }

    public void setScholarYearStart(ZonedDateTime scholarYearStart) {
        this.scholarYearStart = scholarYearStart;
    }

    public ZonedDateTime getScholarYearEnd() {
        return scholarYearEnd;
    }

    public void setScholarYearEnd(ZonedDateTime scholarYearEnd) {
        this.scholarYearEnd = scholarYearEnd;
    }

    public ContractDbDTO getContract() {
        return contract;
    }

    public void setContract(ContractDbDTO contract) {
        this.contract = contract;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public List<WebsiteDbVO> getWebsites() {
        return websites;
    }

    public void setWebsites(List<WebsiteDbVO> websites) {
        this.websites = websites;
    }

    public String getPrincipalWebsite() {
        return principalWebsite;
    }

    public void setPrincipalWebsite(String principalWebsite) {
        this.principalWebsite = principalWebsite;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getSurfacesMeasurementUnit() {
        return surfacesMeasurementUnit;
    }

    public void setSurfacesMeasurementUnit(String surfacesMeasurementUnit) {
        this.surfacesMeasurementUnit = surfacesMeasurementUnit;
    }

    public String getDistancesMeasurementUnit() {
        return distancesMeasurementUnit;
    }

    public void setDistancesMeasurementUnit(String distancesMeasurementUnit) {
        this.distancesMeasurementUnit = distancesMeasurementUnit;
    }
}
