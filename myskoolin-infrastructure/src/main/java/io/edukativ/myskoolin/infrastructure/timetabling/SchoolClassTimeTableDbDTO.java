package io.edukativ.myskoolin.infrastructure.timetabling;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.edukativ.myskoolin.domain.schoolclasses.SchoolClass;
import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoom;
import io.edukativ.myskoolin.domain.subjects.Subject;
import io.edukativ.myskoolin.domain.teachers.Teacher;
import io.edukativ.myskoolin.domain.timetabling.TimeSlot;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SchoolClassTimeTableDbDTO implements Serializable {

    public static final String MONGO_FIELD_STATIC_TIMETABLE = "static_timetable";
    public static final String MONGO_FIELD_EVENTS = "events";
    public static final String MONGO_FIELD_LAST_GENERATION_DATE = "last_generation_date";

    @Id
    private ObjectId id;

    @Field(value = MONGO_FIELD_STATIC_TIMETABLE)
    private List<LessonDbVO> staticTimeTable;

    @Field(value = MONGO_FIELD_EVENTS)
    private List<LessonDbVO> events;

    @Field(value = MONGO_FIELD_TIMESLOTS)
    private List<TimeSlot> timeSlots;

    @DBRef
    @Field(value = MONGO_FIELD_SCHOOL_ROOMS)
    private List<SchoolRoom> schoolRooms;

    @DBRef
    @Field(value = MONGO_FIELD_SUBJECTS)
    private List<Subject> subjects;

    @DBRef
    @Field(value = MONGO_FIELD_TEACHERS)
    private List<Teacher> teachers;

    @DBRef
    @Field(value = MONGO_FIELD_SCHOOL_CLASSES)
    private List<SchoolClass> schoolClasses;

    @Field(value = MONGO_FIELD_LAST_GENERATION_DATE)
    private Instant lastGenerationDate;

    public SchoolClassTimeTableDbDTO(List<SchoolClassTimeSlotDbVO> staticTimeTable, Instant lastGenerationDate) {
        this.staticTimeTable = staticTimeTable;
        this.lastGenerationDate = lastGenerationDate;
    }

    public SchoolClassTimeTableDbDTO() {
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
        SchoolClassTimeTableDbDTO that = (SchoolClassTimeTableDbDTO) o;
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

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
