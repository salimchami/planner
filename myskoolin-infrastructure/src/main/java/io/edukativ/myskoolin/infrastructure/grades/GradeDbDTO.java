package io.edukativ.myskoolin.infrastructure.grades;

import com.google.common.collect.ComparisonChain;
import io.edukativ.myskoolin.infrastructure.commercial.ClientDbDTO;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumSchoolClassNotation;
import io.edukativ.myskoolin.infrastructure.timetabling.TimeTableOptionsDbVO;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static io.edukativ.myskoolin.infrastructure.subjects.SubjectDbDTO.MONGO_FIELD_MAX_MINUTES_PER_WEEK;

/**
 * A Grade.
 */
@Document(collection = GradeDbDTO.MONGO_COLLECTION_NAME)
public class GradeDbDTO implements Serializable, Comparable<GradeDbDTO> {

    private static final long serialVersionUID = 1L;
    public static final String MONGO_COLLECTION_NAME = "grades";
    public static final String MONGO_FIELD_CLIENT_ID = "clientId";
    public static final String MONGO_FIELD_NAME = "name";
    public static final String MONGO_FIELD_ORDER = "order";
    public static final String MONGO_FIELD_NOTATION = "notation";
    public static final String MONGO_FIELD_DIMINUTIVE = "diminutive";
    public static final String MONGO_FIELD_MAX_MINUTES_PER_DAY = "max_minutes_per_day";
    public static final String MONGO_FIELD_DELETED = "deleted";
    public static final String MONGO_FIELD_SERIES = "series";
    public static final String MONGO_FIELD_TIMETABLE_OPTIONS = "timetable_options";

    @Id
    private ObjectId id;

    @Field(MONGO_FIELD_CLIENT_ID)
    private ObjectId clientId;

    @Field(MONGO_FIELD_NAME)
    private String name;

    @Field(MONGO_FIELD_ORDER)
    private Integer order;

    @Field(MONGO_FIELD_NOTATION)
    private EnumSchoolClassNotation notation = EnumSchoolClassNotation.QUARTER;

    @Field(MONGO_FIELD_DIMINUTIVE)
    private String diminutive;

    @Field(MONGO_FIELD_MAX_MINUTES_PER_DAY)
    private Integer maxMinutesPerDay;

    @Field(MONGO_FIELD_DELETED)
    private boolean deleted;

    @Field(MONGO_FIELD_SERIES)
    private List<GradeSerieDbVO> series;

    @Field(MONGO_FIELD_TIMETABLE_OPTIONS)
    private TimeTableOptionsDbVO timeTableOptions;

    @Field(MONGO_FIELD_MAX_MINUTES_PER_WEEK)
    private Integer maxMinutesPerWeek;

    public GradeDbDTO() {
    }

    public GradeDbDTO(String name) {
        this.name = name;
    }

    public GradeDbDTO(ObjectId clientId, String name, Integer order, String diminutive,
                      EnumSchoolClassNotation notation, Integer maxMinutesPerDay, List<GradeSerieDbVO> series,
                      TimeTableOptionsDbVO timeTableOptions, Integer maxMinutesPerWeek) {
        this.clientId = clientId;
        this.name = name;
        this.order = order;
        this.diminutive = diminutive;
        this.maxMinutesPerDay = maxMinutesPerDay;
        this.series = series;
        this.notation = notation;
        this.timeTableOptions = timeTableOptions;
        this.maxMinutesPerWeek = maxMinutesPerWeek;
    }

    public GradeDbDTO(ObjectId id, ObjectId clientId, String name, Integer order, String diminutive,
                      EnumSchoolClassNotation notation, Integer maxMinutesPerDay, List<GradeSerieDbVO> series,
                      TimeTableOptionsDbVO timeTableOptions, Integer maxMinutesPerWeek) {
        this(clientId, name, order, diminutive, notation, maxMinutesPerDay, series, timeTableOptions, maxMinutesPerWeek);
        this.id = id;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getDiminutive() {
        return diminutive;
    }

    public void setDiminutive(String diminutive) {
        this.diminutive = diminutive;
    }

    public Integer getMaxMinutesPerDay() {
        return maxMinutesPerDay;
    }

    public void setMaxMinutesPerDay(Integer maxMinutesPerDay) {
        this.maxMinutesPerDay = maxMinutesPerDay;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public ObjectId getClientId() {
        return clientId;
    }

    public void setClientId(ObjectId clientId) {
        this.clientId = clientId;
    }

    public List<GradeSerieDbVO> getSeries() {
        if (series == null) {
            series = new ArrayList<>();
        }
        return series;
    }

    public void setSeries(List<GradeSerieDbVO> series) {
        this.series = series;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GradeDbDTO grade = (GradeDbDTO) o;
        return !(grade.id == null || id == null) && Objects.equals(id, grade.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", order='" + order + "'" +
                ", diminutive='" + diminutive + "'" +
                ", maxMinutesPerDay='" + maxMinutesPerDay + "'" +
                '}';
    }

    @Override
    public int compareTo(GradeDbDTO o) {
        return ComparisonChain
                .start()
                .compare(this.order, o.getOrder())
                .compare(this.name, o.getName())
                .result();
    }

    public EnumSchoolClassNotation getNotation() {
        return notation;
    }

    public void setNotation(EnumSchoolClassNotation notation) {
        this.notation = notation;
    }

    public TimeTableOptionsDbVO getTimeTableOptions() {
        if (this.timeTableOptions == null) {
            this.timeTableOptions = ClientDbDTO.defaultTimeTableOptions();
        }
        return timeTableOptions;
    }

    public void setTimeTableOptions(TimeTableOptionsDbVO timeTableOptions) {
        this.timeTableOptions = timeTableOptions;
    }

    public static Optional<GradeSerieDbVO> findGradeSerieInGrades(List<GradeDbDTO> grades, String gradeSerieName) {
        Optional<GradeDbDTO> grade = grades.stream()
                .filter(gradeFromList -> {
                    Optional<GradeSerieDbVO> serie = gradeFromList.getSeries()
                            .stream()
                            .filter(gradeSerie -> gradeSerieName.equals(gradeSerie.getName())).findFirst();
                    return serie.isPresent();
                })
                .findFirst();
        return grade.flatMap(value -> value.getSeries().stream().filter(gradeSerie -> gradeSerieName.equals(gradeSerie.getName())).findFirst());
    }

    public Integer getMaxMinutesPerWeek() {
        return maxMinutesPerWeek;
    }

    public void setMaxMinutesPerWeek(Integer maxMinutesPerWeek) {
        this.maxMinutesPerWeek = maxMinutesPerWeek;
    }
}
