package io.edukativ.myskoolin.infrastructure.schooling;

import io.edukativ.myskoolin.infrastructure.grades.GradeDbDTO;
import io.edukativ.myskoolin.infrastructure.grades.GradeSerieDbVO;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * An option.
 */
@Document(collection = OptionDbDTO.MONGO_COLLECTION_NAME)
public class OptionDbDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String MONGO_COLLECTION_NAME = "options";
    public static final String MONGO_FIELD_CLIENT_ID = "clientId";
    public static final String MONGO_FIELD_NAME = "name";
    public static final String MONGO_FIELD_CUSTOM_NAME = "custom_name";
    public static final String MONGO_FIELD_GRADE_SERIE = "grade_serie";
    public static final String MONGO_FIELD_COEFFICIENT = "coefficient";
    public static final String MONGO_FIELD_COLOR = "color";
    public static final String MONGO_FIELD_BG_COLOR = "bg-color";
    public static final String MONGO_FIELD_FOREIGN_LANGUAGE = "foreign_language";
    public static final String MONGO_FIELD_MAX_MINUTES_PER_DAY = "max_minutes_per_day";
    public static final String MONGO_FIELD_MINUTES_PER_WEEK = "minutes_per_week";
    public static final String MONGO_FIELD_COURSES_FREQUENCY_PER_WEEK = "courses_frequency_per_week";
    public static final String MONGO_FIELD_DELETED = "deleted";
    private static final String MONGO_FIELD_GRADE = "grade";
    private static final String MONGO_FIELD_COMMENT = "comment";

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

    @Field(MONGO_FIELD_MINUTES_PER_WEEK)
    private Integer minutesPerWeek;

    @Field(MONGO_FIELD_COURSES_FREQUENCY_PER_WEEK)
    private Integer coursesFrequencyPerWeek;

    @Field(MONGO_FIELD_DELETED)
    private Boolean deleted = false;

    @Field(MONGO_FIELD_COMMENT)
    private String comment;

    public OptionDbDTO() {
    }

    public OptionDbDTO(ObjectId clientId, String name, String customName, GradeDbDTO grade, GradeSerieDbVO gradeSerie,
                       BigDecimal coefficient, String color, String bgColor, Boolean foreignLanguage,
                       Integer maxMinutesPerDay, Integer minutesPerWeek, Integer coursesFrequencyPerWeek,
                       Boolean deleted, String comment) {
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
        this.minutesPerWeek = minutesPerWeek;
        this.coursesFrequencyPerWeek = coursesFrequencyPerWeek;
        this.deleted = deleted;
        this.comment = comment;
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
        OptionDbDTO subject = (OptionDbDTO) o;
        return !(subject.id == null || id == null) && Objects.equals(id, subject.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
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
}
