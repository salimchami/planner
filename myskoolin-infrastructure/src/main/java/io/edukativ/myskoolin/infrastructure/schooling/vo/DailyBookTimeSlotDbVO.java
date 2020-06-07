package io.edukativ.myskoolin.infrastructure.schooling.vo;

import io.edukativ.myskoolin.infrastructure.subjects.SubjectDbDTO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/**
 * Daily book (cahier de texte) timeslot
 */
public class DailyBookTimeSlotDbVO extends TimeSlotDbVO implements Serializable {

    public static final String MONGO_FIELD_SUBJECT = "subject";
    public static final String MONGO_FIELD_TEACHERS = "teachers";
    public static final String MONGO_FIELD_TARGET_DATE = "target_date";

    @DBRef
    @Field(value = MONGO_FIELD_SUBJECT)
    private SubjectDbDTO subject;

    @Field(value = MONGO_FIELD_TEACHERS)
    private List<ObjectId> teachers;

    @Field(value = MONGO_FIELD_TARGET_DATE)
    private Instant targetDate;

    public SubjectDbDTO getSubject() {
        return subject;
    }

    public void setSubject(SubjectDbDTO subject) {
        this.subject = subject;
    }

    public List<ObjectId> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<ObjectId> teachers) {
        this.teachers = teachers;
    }

    public Instant getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Instant targetDate) {
        this.targetDate = targetDate;
    }

    //fixme: add option entity
    //private Subject option;

}
