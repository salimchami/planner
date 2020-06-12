package io.edukativ.myskoolin.infrastructure.subjects;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumSchoolRoomsTypesDb;
import io.edukativ.myskoolin.infrastructure.grades.GradeDbDTO;
import io.edukativ.myskoolin.infrastructure.grades.GradeSerieDbVO;
import io.edukativ.myskoolin.infrastructure.schooling.vo.PreferredPartsOfDaysDbVO;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * A Subject.
 */
@Document(collection = SubjectDbDTO.MONGO_COLLECTION_NAME)
public class SubjectDbDTO implements Comparable<SubjectDbDTO>, Serializable {

    private static final long serialVersionUID = 1L;

    public static final String MONGO_COLLECTION_NAME = "subjects";

    public static final String MONGO_FIELD_CLIENT_ID = "clientId";
    public static final String MONGO_FIELD_NAME = "name";
    public static final String MONGO_FIELD_CUSTOM_NAME = "custom_name";
    public static final String MONGO_FIELD_GRADE_SERIE = "grade_serie";
    public static final String MONGO_FIELD_COEFFICIENT = "coefficient";
    public static final String MONGO_FIELD_COLOR = "color";
    public static final String MONGO_FIELD_BG_COLOR = "bg-color";
    public static final String MONGO_FIELD_FOREIGN_LANGUAGE = "foreign_language";
    public static final String MONGO_FIELD_MAX_MINUTES_PER_DAY = "max_minutes_per_day";
    public static final String MONGO_FIELD_MIN_MINUTES_PER_DAY = "min_minutes_per_day";
    public static final String MONGO_FIELD_MAX_MINUTES_PER_WEEK = "max_minutes_per_week";
    public static final String MONGO_FIELD_COURSES_FREQUENCY_PER_WEEK = "courses_frequency_per_week";
    public static final String MONGO_FIELD_DELETED = "deleted";
    public static final String MONGO_FIELD_GRADE = "grade";
    public static final String MONGO_FIELD_COMMENT = "comment";
    public static final String MONGO_FIELD_PARTS_OF_DAYS_IN_TIMETABLES = "parts_of_days_in_timetables";
    public static final String MONGO_FIELD_SCHOOL_ROOMS_TYPE = "school_room_type";
    public static final String MONGO_FIELD_DAY_BETWEEN_TIMESLOTS = "days_between_timeslots";
    public static final String MONGO_FIELD_GROUP_SUBJECT = "group_subject";

    @Id
    private ObjectId id;

    @Field(MONGO_FIELD_CLIENT_ID)
    @Indexed
    private ObjectId clientId;

    @TextIndexed
    @Field(MONGO_FIELD_NAME)
    private String name;

    @TextIndexed
    @Field(MONGO_FIELD_CUSTOM_NAME)
    private String customName;

    @DBRef
    @Field(MONGO_FIELD_GRADE)
    private GradeDbDTO grade;

    @Field(MONGO_FIELD_GRADE_SERIE)
    private GradeSerieDbVO gradeSerie;

    @Field(MONGO_FIELD_COEFFICIENT)
    private BigDecimal coefficient;

    @Field(MONGO_FIELD_COLOR)
    private String color = "#CFD8DC";

    @Field(MONGO_FIELD_BG_COLOR)
    private String bgColor = "#6A6A6A";

    @Field(MONGO_FIELD_FOREIGN_LANGUAGE)
    private Boolean foreignLanguage = false;

    @Field(MONGO_FIELD_MAX_MINUTES_PER_DAY)
    private Integer maxMinutesPerDay;

    @Field(MONGO_FIELD_MIN_MINUTES_PER_DAY)
    private Integer minMinutesPerDay;

    @Field(MONGO_FIELD_MAX_MINUTES_PER_WEEK)
    private Integer minutesPerWeek;

    @Field(MONGO_FIELD_COURSES_FREQUENCY_PER_WEEK)
    private Integer coursesFrequencyPerWeek;

    @Field(MONGO_FIELD_DELETED)
    private Boolean deleted;

    @Field(MONGO_FIELD_COMMENT)
    private String comment;

    @Field(MONGO_FIELD_GROUP_SUBJECT)
    private boolean groupSubject;

    @Field(MONGO_FIELD_PARTS_OF_DAYS_IN_TIMETABLES)
    private List<PreferredPartsOfDaysDbVO> preferredPartsOfDaysInTimetables;

    @Field(MONGO_FIELD_SCHOOL_ROOMS_TYPE)
    private List<EnumSchoolRoomsTypesDb> schoolRoomsTypes;

    @Field(MONGO_FIELD_DAY_BETWEEN_TIMESLOTS)
    private Integer daysBetweenTimeSlots;

    public SubjectDbDTO() {
        this.deleted = false;
    }

    public SubjectDbDTO(ObjectId clientId, String name, String customName, GradeDbDTO grade, GradeSerieDbVO gradeSerie,
                        BigDecimal coefficient, String color, String bgColor, Boolean foreignLanguage,
                        Integer maxMinutesPerDay, Integer minMinutesPerDay, Integer minutesPerWeek,
                        Integer coursesFrequencyPerWeek, Boolean deleted, String comment, List<EnumSchoolRoomsTypesDb> schoolRoomsTypes, boolean groupSubject) {
        this.clientId = clientId;
        this.name = name;
        this.customName = customName;
        this.grade = grade;
        this.gradeSerie = gradeSerie;
        this.coefficient = coefficient;
        this.color = color;
        this.bgColor = bgColor;
        this.foreignLanguage = foreignLanguage;
        this.maxMinutesPerDay = maxMinutesPerDay;
        this.minMinutesPerDay = minMinutesPerDay;
        this.minutesPerWeek = minutesPerWeek;
        this.coursesFrequencyPerWeek = coursesFrequencyPerWeek;
        this.deleted = deleted;
        this.comment = comment;
        this.schoolRoomsTypes = schoolRoomsTypes;
        this.groupSubject = groupSubject;
    }

    public void update(SubjectDbDTO subject) {
        this.name = subject.getName();
        this.customName = subject.getCustomName();
        this.grade = subject.getGrade();
        this.gradeSerie = subject.getGradeSerie();
        this.coefficient = subject.getCoefficient();
        this.color = subject.getColor();
        this.bgColor = subject.getBgColor();
        this.foreignLanguage = subject.isForeignLanguage();
        this.maxMinutesPerDay = subject.getMaxMinutesPerDay();
        this.minMinutesPerDay = subject.getMinMinutesPerDay();
        this.minutesPerWeek = subject.getMinutesPerWeek();
        this.coursesFrequencyPerWeek = subject.getCoursesFrequencyPerWeek();
        this.comment = subject.getComment();
        this.groupSubject = subject.isGroupSubject();
        this.preferredPartsOfDaysInTimetables = subject.getPreferredPartsOfDaysInTimetables();
        this.schoolRoomsTypes = subject.getSchoolRoomsTypes();
        this.daysBetweenTimeSlots = subject.getDaysBetweenTimeSlots();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public BigDecimal getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public Boolean isForeignLanguage() {
        return foreignLanguage;
    }

    public void setForeignLanguage(Boolean foreignLanguage) {
        this.foreignLanguage = foreignLanguage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GradeDbDTO getGrade() {
        return grade;
    }

    public void setGrade(GradeDbDTO grade) {
        this.grade = grade;
    }

    public ObjectId getClientId() {
        return clientId;
    }

    public void setClientId(ObjectId clientId) {
        this.clientId = clientId;
    }

    public Integer getMaxMinutesPerDay() {
        return maxMinutesPerDay;
    }

    public void setMaxMinutesPerDay(Integer maxMinutesPerDay) {
        this.maxMinutesPerDay = maxMinutesPerDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SubjectDbDTO subject = (SubjectDbDTO) o;
        return !(subject.id == null || id == null) && Objects.equals(id, subject.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "Subject{" +
                    "id=" + id +
                    ", coefficient='" + coefficient + "'" +
                    ", color='" + color + "'" +
                    ", customName='" + customName + "'" +
                    ", foreignLanguage='" + foreignLanguage + "'" +
                    ", name='" + name + "'" +
                    ", gradeName='" + grade.getName() + "'" +
                    '}';
        }
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public GradeSerieDbVO getGradeSerie() {
        return gradeSerie;
    }

    public void setGradeSerie(GradeSerieDbVO gradeSerie) {
        this.gradeSerie = gradeSerie;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public Integer getMinutesPerWeek() {
        return minutesPerWeek;
    }

    public void setMinutesPerWeek(Integer minutesPerWeek) {
        this.minutesPerWeek = minutesPerWeek;
    }

    public Integer getCoursesFrequencyPerWeek() {
        return coursesFrequencyPerWeek;
    }

    public void setCoursesFrequencyPerWeek(Integer coursesFrequencyPerWeek) {
        this.coursesFrequencyPerWeek = coursesFrequencyPerWeek;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String timetableName() {
        if (this.customName != null && !this.customName.isEmpty()) {
            return this.customName;
        } else {
            return this.name;
        }
    }

    public List<PreferredPartsOfDaysDbVO> getPreferredPartsOfDaysInTimetables() {
        return preferredPartsOfDaysInTimetables;
    }

    public void setPreferredPartsOfDaysInTimetables(List<PreferredPartsOfDaysDbVO> preferredPartsOfDaysInTimetables) {
        this.preferredPartsOfDaysInTimetables = preferredPartsOfDaysInTimetables;
    }

    public Integer getMinMinutesPerDay() {
        return minMinutesPerDay;
    }

    public void setMinMinutesPerDay(Integer minMinutesPerDay) {
        this.minMinutesPerDay = minMinutesPerDay;
    }

    public int compareTo(SubjectDbDTO o) {
        if (o == null) {
            return -1;
        }
        return Comparator.comparing(SubjectDbDTO::getMaxMinutesPerDay)
                .thenComparing(SubjectDbDTO::getMinutesPerWeek)
                .thenComparing(SubjectDbDTO::getCoursesFrequencyPerWeek)
                .compare(this, o);
    }

    public List<EnumSchoolRoomsTypesDb> getSchoolRoomsTypes() {
        return schoolRoomsTypes;
    }

    public void setSchoolRoomsTypes(List<EnumSchoolRoomsTypesDb> schoolRoomsTypes) {
        this.schoolRoomsTypes = schoolRoomsTypes;
    }

    public Integer getDaysBetweenTimeSlots() {
        return daysBetweenTimeSlots;
    }

    public void setDaysBetweenTimeSlots(Integer daysBetweenTimeSlots) {
        this.daysBetweenTimeSlots = daysBetweenTimeSlots;
    }

    public boolean isGroupSubject() {
        return groupSubject;
    }

    public void setGroupSubject(boolean groupSubject) {
        this.groupSubject = groupSubject;
    }
}
