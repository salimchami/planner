package io.edukativ.myskoolin.infrastructure.schooling.vo;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SchoolClassTimeTableDbVO implements Serializable {

    public static final String MONGO_FIELD_STATIC_TIMETABLE = "static_timetable";
    public static final String MONGO_FIELD_EVENTS = "events";
    public static final String MONGO_FIELD_LAST_GENERATION_DATE = "last_generation_date";

    @Field(value = MONGO_FIELD_STATIC_TIMETABLE)
    private List<SchoolClassTimeSlotDbVO> staticTimeTable;

    @Field(value = MONGO_FIELD_EVENTS)
    private List<SchoolClassTimeSlotDbVO> events;

    @Field(value = MONGO_FIELD_LAST_GENERATION_DATE)
    private Instant lastGenerationDate;

    public SchoolClassTimeTableDbVO(List<SchoolClassTimeSlotDbVO> staticTimeTable, Instant lastGenerationDate) {
        this.staticTimeTable = staticTimeTable;
        this.lastGenerationDate = lastGenerationDate;
    }

    public SchoolClassTimeTableDbVO() {
        this.staticTimeTable = new ArrayList<>();
    }

    public List<SchoolClassTimeSlotDbVO> getStaticTimeTable() {
        return staticTimeTable;
    }

    public void setStaticTimeTable(List<SchoolClassTimeSlotDbVO> staticTimeTable) {
        this.staticTimeTable = staticTimeTable;
    }

    public List<SchoolClassTimeSlotDbVO> getEvents() {
        return events;
    }

    public void setEvents(List<SchoolClassTimeSlotDbVO> events) {
        this.events = events;
    }

    public Instant getLastGenerationDate() {
        return lastGenerationDate;
    }

    public void setLastGenerationDate(Instant lastGenerationDate) {
        this.lastGenerationDate = lastGenerationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SchoolClassTimeTableDbVO that = (SchoolClassTimeTableDbVO) o;
        return staticTimeTable.equals(that.staticTimeTable) &&
                Objects.equals(events, that.events) &&
                Objects.equals(lastGenerationDate, that.lastGenerationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(staticTimeTable, events, lastGenerationDate);
    }

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {

            return "SchoolClassTimeTable{" +
                    ", staticTimeTable=" + staticTimeTable +
                    ", events=" + events +
                    ", lastGenerationDate=" + lastGenerationDate +
                    '}';
        }
    }
}
