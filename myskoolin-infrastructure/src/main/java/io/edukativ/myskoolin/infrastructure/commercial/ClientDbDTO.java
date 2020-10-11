package io.edukativ.myskoolin.infrastructure.commercial;

import io.edukativ.myskoolin.infrastructure.common.enums.EnumContactsBy;
import io.edukativ.myskoolin.infrastructure.common.vo.AddressDbVO;
import io.edukativ.myskoolin.infrastructure.common.vo.EmailDbVO;
import io.edukativ.myskoolin.infrastructure.common.vo.PhoneDbVO;
import io.edukativ.myskoolin.infrastructure.common.vo.WebsiteDbVO;
import io.edukativ.myskoolin.infrastructure.timetabling.TimeSlotDbVO;
import io.edukativ.myskoolin.infrastructure.timetabling.TimeTableOptionsDbVO;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Client entity.
 */
@Document(collection = ClientDbDTO.MONGO_COLLECTION_NAME)
public class ClientDbDTO implements Serializable {

    public static final String MONGO_COLLECTION_NAME = "clients";

    private static final String MONGO_FIELD_ADDRESS = "address";
    private static final String MONGO_FIELD_DUNS_NUMBER = "duns_number";
    public static final String MONGO_FIELD_CONTACTS_BY = "contacts_by";
    public static final String MONGO_FIELD_TIMETABLE_OPTIONS = "timetable_options";
    public static final String MONGO_FIELD_EMAILS = "emails";
    public static final String MONGO_FIELD_MAJORITY_AGE = "majority_age";
    public static final String MONGO_FIELD_NAME = "name";
    public static final String MONGO_FIELD_NB_MAX_OF_STUDENTS_PER_SCHOOL_CLASS = "nb_max_of_students_per_school_class";
    public static final String MONGO_FIELD_PHONES = "phones";
    public static final String MONGO_FIELD_SCHOLAR_YEAR_START = "scholar_year_start";
    public static final String MONGO_FIELD_SCHOLAR_YEAR_END = "scholar_year_end";
    public static final String MONGO_FIELD_CONTRACT = "contract";
    public static final String MONGO_FIELD_IBAN = "iban";
    public static final String MONGO_FIELD_BIC = "bic";
    public static final String MONGO_FIELD_WEBSITES = "websites";
    public static final String MONGO_FIELD_PRINCIPAL_WEBSITE = "principal_website";
    public static final String MONGO_FIELD_DELETED = "deleted";
    public static final String MONGO_FIELD_SURFACES_MEASUREMENT_UNIT = "surfaces_measurement_unit";
    public static final String MONGO_FIELD_DISTANCES_MEASUREMENT_UNIT = "distances_measurement_unit";
    public static final String MONGO_FIELD_INTERLOCUTOR = "interlocutor";

    @Id
    private ObjectId id;

    @Field(MONGO_FIELD_ADDRESS)
    private AddressDbVO address; //OK

    @Field(MONGO_FIELD_DUNS_NUMBER)
    private String dunsNumber;//OK

    @Field(MONGO_FIELD_CONTACTS_BY)
    private List<EnumContactsBy> contactsBy;

    @Field(MONGO_FIELD_TIMETABLE_OPTIONS)
    private TimeTableOptionsDbVO timeTableOptions;

    @Field(MONGO_FIELD_EMAILS)
    private List<EmailDbVO> emails;

    @Field(MONGO_FIELD_MAJORITY_AGE)
    private Integer majorityAge;

    @Field(MONGO_FIELD_NAME)
    private String name;

    @Field(MONGO_FIELD_NB_MAX_OF_STUDENTS_PER_SCHOOL_CLASS)
    private Integer nbMaxOfStudentsPerSchoolClass;

    @Field(MONGO_FIELD_PHONES)
    private List<PhoneDbVO> phones;

    @Field(MONGO_FIELD_SCHOLAR_YEAR_START)
    private ZonedDateTime scholarYearStart;

    @Field(MONGO_FIELD_SCHOLAR_YEAR_END)
    private ZonedDateTime scholarYearEnd;

    @Field(MONGO_FIELD_CONTRACT)
    private ContractDbDTO contract;

    @Field(MONGO_FIELD_IBAN)
    private String iban;

    @Field(MONGO_FIELD_BIC)
    private String bic;

    @Field(MONGO_FIELD_WEBSITES)
    private List<WebsiteDbVO> websites;

    @Field(MONGO_FIELD_PRINCIPAL_WEBSITE)
    private String principalWebsite;

    @Field(MONGO_FIELD_DELETED)
    private Boolean deleted;

    @Field(MONGO_FIELD_SURFACES_MEASUREMENT_UNIT)
    private String surfacesMeasurementUnit;
    @Field(MONGO_FIELD_DISTANCES_MEASUREMENT_UNIT)
    private String distancesMeasurementUnit;

    @Field(MONGO_FIELD_INTERLOCUTOR)
    private InterlocutorDbDTO interlocutor;

    public ClientDbDTO() {
        this.contactsBy = Arrays.asList(EnumContactsBy.values());
        this.emails = new ArrayList<>();
        this.phones = new ArrayList<>();
        this.websites = new ArrayList<>();
        this.contract = new ContractDbDTO();
        this.deleted = false;
    }

    public ClientDbDTO(ObjectId id, AddressDbVO address, String dunsNumber, List<EnumContactsBy> contactsBy,
                       TimeTableOptionsDbVO timeTableOptions, List<EmailDbVO> emails, Integer majorityAge, String name,
                       Integer nbMaxOfStudentsPerSchoolClass, List<PhoneDbVO> phones, ZonedDateTime scholarYearStart,
                       ZonedDateTime scholarYearEnd, ContractDbDTO contract, String iban, String bic,
                       List<WebsiteDbVO> websites, String principalWebsite, Boolean deleted, String surfacesMeasurementUnit,
                       String distancesMeasurementUnit, InterlocutorDbDTO interlocutor) {
        this.id = id;
        this.address = address;
        this.dunsNumber = dunsNumber;
        this.contactsBy = contactsBy;
        this.timeTableOptions = timeTableOptions;
        this.emails = emails;
        this.majorityAge = majorityAge;
        this.name = name;
        this.nbMaxOfStudentsPerSchoolClass = nbMaxOfStudentsPerSchoolClass;
        this.phones = phones;
        this.scholarYearStart = scholarYearStart;
        this.scholarYearEnd = scholarYearEnd;
        this.contract = contract;
        this.iban = iban;
        this.bic = bic;
        this.websites = websites;
        this.principalWebsite = principalWebsite;
        this.deleted = deleted;
        this.surfacesMeasurementUnit = surfacesMeasurementUnit;
        this.distancesMeasurementUnit = distancesMeasurementUnit;
        this.interlocutor = interlocutor;
    }

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
        if (this.contactsBy == null) {
            this.contactsBy = Arrays.asList(EnumContactsBy.values());
        }
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
        if (this.emails == null) {
            this.emails = new ArrayList<>();
        }
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
        if (this.phones == null) {
            this.phones = new ArrayList<>();
        }
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
        if (this.contract == null) {
            this.contract = new ContractDbDTO();
        }
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
        if (this.websites == null) {
            this.websites = new ArrayList<>();
        }
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

    public InterlocutorDbDTO getInterlocutor() {
        return interlocutor;
    }

    public void setInterlocutor(InterlocutorDbDTO interlocutor) {
        this.interlocutor = interlocutor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDbDTO client = (ClientDbDTO) o;
        return Objects.equals(id, client.id) &&
                Objects.equals(dunsNumber, client.dunsNumber) &&
                Objects.equals(name, client.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dunsNumber, name);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static List<TimeSlotDbVO> defaultCoursesTimeSlots() {
        List<TimeSlotDbVO> courses = new ArrayList<>();
        String courseTitle = "timetable.common.course-period";
        String bgColor = "#31708f";
        String fontColorCssClass = "#FFFFFF";
        Arrays.stream(DayOfWeek.values()).forEach(enumDays -> {
            if (!enumDays.equals(DayOfWeek.SATURDAY) && !enumDays.equals(DayOfWeek.SUNDAY) && !enumDays.equals(DayOfWeek.WEDNESDAY)) {
                addAmCourses(courses, courseTitle, bgColor, fontColorCssClass, enumDays);
                addPmCourses(courses, courseTitle, bgColor, fontColorCssClass, enumDays);
            } else if (enumDays.equals(DayOfWeek.SATURDAY) || enumDays.equals(DayOfWeek.WEDNESDAY)) {
                addAmCourses(courses, courseTitle, bgColor, fontColorCssClass, enumDays);
            }
        });
        return courses;
    }

    public static List<TimeSlotDbVO> defaultExtraActivities() {
        List<TimeSlotDbVO> extraActivities = new ArrayList<>();
        String courseTitle = "timetable.common.extra-activity-period";
        String bgColor = "#cfe0cbff";
        String fontColorCssClass = "#333366";
        Arrays.stream(DayOfWeek.values()).forEach(enumDays -> {
            if (enumDays.equals(DayOfWeek.SATURDAY) || enumDays.equals(DayOfWeek.WEDNESDAY)) {
                addPmExtraActivities(extraActivities, courseTitle, bgColor, fontColorCssClass, enumDays);
            }
        });
        return extraActivities;
    }

    private static void addPmExtraActivities(List<TimeSlotDbVO> courses, String courseTitle, String bgColor, String fontColorCssClass, DayOfWeek enumDays) {
        courses.add(new TimeSlotDbVO(courseTitle, enumDays,
                LocalTime.of(13, 0, 0),
                LocalTime.of(18, 30, 0), bgColor, fontColorCssClass));
    }

    private static void addPmCourses(List<TimeSlotDbVO> courses, String courseTitle, String bgColor, String fontColorCssClass,
                                     DayOfWeek enumDays) {
        courses.add(new TimeSlotDbVO(courseTitle, enumDays,
                LocalTime.of(14, 0, 0),
                LocalTime.of(15, 0, 0), bgColor, fontColorCssClass));
        courses.add(new TimeSlotDbVO(courseTitle, enumDays,
                LocalTime.of(15, 0, 0),
                LocalTime.of(16, 0, 0), bgColor, fontColorCssClass));
        courses.add(new TimeSlotDbVO(courseTitle, enumDays,
                LocalTime.of(16, 0, 0),
                LocalTime.of(17, 0, 0), bgColor, fontColorCssClass));
        courses.add(new TimeSlotDbVO(courseTitle, enumDays,
                LocalTime.of(17, 0, 0),
                LocalTime.of(18, 0, 0), bgColor, fontColorCssClass));
    }

    private static void addAmCourses(List<TimeSlotDbVO> courses, String courseTitle, String bgColor, String fontColorCssClass, DayOfWeek enumDays) {
        courses.add(new TimeSlotDbVO(courseTitle, enumDays,
                LocalTime.of(8, 0, 0),
                LocalTime.of(9, 0, 0), bgColor, fontColorCssClass));
        courses.add(new TimeSlotDbVO(courseTitle, enumDays,
                LocalTime.of(9, 0, 0),
                LocalTime.of(10, 0, 0), bgColor, fontColorCssClass));
        courses.add(new TimeSlotDbVO(courseTitle, enumDays,
                LocalTime.of(10, 0, 0),
                LocalTime.of(11, 0, 0), bgColor, fontColorCssClass));
        courses.add(new TimeSlotDbVO(courseTitle, enumDays,
                LocalTime.of(11, 0, 0),
                LocalTime.of(12, 0, 0), bgColor, fontColorCssClass));
    }

    public static TimeTableOptionsDbVO defaultTimeTableOptions() {
        return new TimeTableOptionsDbVO(true, DayOfWeek.MONDAY, BigDecimal.valueOf(2L),
                LocalTime.of(8, 0, 0),
                LocalTime.of(18, 0, 0),
                defaultCoursesTimeSlots(), defaultExtraActivities(), 30);
    }
}
