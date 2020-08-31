package io.edukativ.myskoolin.infrastructure.timetabling;

import io.edukativ.myskoolin.infrastructure.schoolrooms.SchoolRoomDbDTO;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectDbDTO;
import io.edukativ.myskoolin.infrastructure.teachers.TeacherDbDTO;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

public class LessonDbVO implements Serializable {

    private static final String MONGO_FIELD_SCHOOL_ROOM = "school_room";
    private static final String MONGO_FIELD_SUBJECT = "subject";
    private static final String MONGO_FIELD_TEACHER = "teacher";
    private static final String MONGO_FIELD_SCHOOL_CLASS = "school_class";
    private static final String MONGO_FIELD_ID = "id";
    private static final String MONGO_FIELD_TIMESLOT = "timeslot";

    @Field(MONGO_FIELD_ID)
    private String id;

    @DBRef
    @Field(MONGO_FIELD_SCHOOL_ROOM)
    private SchoolRoomDbDTO schoolRoom;
    @DBRef
    @Field(MONGO_FIELD_SUBJECT)
    private SubjectDbDTO subject;
    @DBRef
    @Field(MONGO_FIELD_TEACHER)
    private TeacherDbDTO teacher;
    @Field(MONGO_FIELD_TIMESLOT)
    private TimeSlotDbVO timeSlot;
    //FIXME: add option entity
    //private Subject option;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SchoolRoomDbDTO getSchoolRoom() {
        return schoolRoom;
    }

    public void setSchoolRoom(SchoolRoomDbDTO schoolRoom) {
        this.schoolRoom = schoolRoom;
    }

    public SubjectDbDTO getSubject() {
        return subject;
    }

    public void setSubject(SubjectDbDTO subject) {
        this.subject = subject;
    }

    public TeacherDbDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDbDTO teacher) {
        this.teacher = teacher;
    }

    public TimeSlotDbVO getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlotDbVO timeSlot) {
        this.timeSlot = timeSlot;
    }
}
