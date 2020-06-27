package io.edukativ.myskoolin.infrastructure.timetabling;

import io.edukativ.myskoolin.infrastructure.subjects.SubjectDbDTO;
import io.edukativ.myskoolin.infrastructure.timetabling.TimeSlotDbVO;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

public class SchoolClassTimeSlotDbVO extends TimeSlotDbVO implements Serializable {

    public static final String MONGO_FIELD_SCHOOL_ROOM = "school_room_id";
    public static final String MONGO_FIELD_SUBJECT = "subject_id";
    public static final String MONGO_FIELD_SCHOOL_CLASS = "school_class_id";
    public static final String MONGO_FIELD_TEACHER = "teacher_id";

    @Field(value = MONGO_FIELD_SCHOOL_ROOM)
    private String schoolRoomId;

    @Field(value = MONGO_FIELD_SCHOOL_CLASS)
    private String schoolClassId;

    @DBRef
    @Field(value = MONGO_FIELD_SUBJECT)
    private SubjectDbDTO subject;

    @Field(value = MONGO_FIELD_TEACHER)
    private String teacherId;

    public SchoolClassTimeSlotDbVO() {

    }

    public SchoolClassTimeSlotDbVO(TimeSlotDbVO timeSlot) {
        this(timeSlot, null, null, null, null);
    }

    public SchoolClassTimeSlotDbVO(TimeSlotDbVO timeSlot, String schoolClassId, String schoolRoomId, SubjectDbDTO subject, String teacherId) {
        super(timeSlot.getTitle(), timeSlot.getSecondTitle(), timeSlot.getComment(), timeSlot.getCanceled(),
                timeSlot.getDay(), timeSlot.getStartTime(), timeSlot.getEndTime(), timeSlot.getDate(), timeSlot.getBgColor(),
                timeSlot.getFontColorCssClass(), timeSlot.getAutoAlterable(), timeSlot.getHalf());
        this.schoolClassId = schoolClassId;
        this.schoolRoomId = schoolRoomId;
        this.subject = subject;
        this.teacherId = teacherId;
    }

    public String getSchoolRoomId() {
        return schoolRoomId;
    }

    public void setSchoolRoomId(String schoolRoomId) {
        this.schoolRoomId = schoolRoomId;
    }

    public SubjectDbDTO getSubject() {
        return subject;
    }

    public void setSubject(SubjectDbDTO subject) {
        this.subject = subject;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeachers(String teachersId) {
        this.teacherId = teachersId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeSlotDbVO timeSlot = (TimeSlotDbVO) o;
        return getDay().equals(timeSlot.getDay()) &&
                getStartTime().equals(timeSlot.getStartTime()) &&
                getEndTime().equals(timeSlot.getEndTime()) &&
                getDate().equals(timeSlot.getDate());
    }

    public boolean hasSameTimes(TimeSlotDbVO timeSlot) {
        return this.getStartTime().equals(timeSlot.getStartTime()) || this.getEndTime().equals(timeSlot.getEndTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDay(), getStartTime(), getEndTime(), getDate());
    }

    public String getSchoolClassId() {
        return schoolClassId;
    }

    public void setSchoolClassId(String schoolClassId) {
        this.schoolClassId = schoolClassId;
    }

    //fixme: add option entity
    //private Subject option;
}
