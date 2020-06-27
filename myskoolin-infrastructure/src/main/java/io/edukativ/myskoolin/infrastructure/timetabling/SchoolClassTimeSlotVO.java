package io.edukativ.myskoolin.infrastructure.timetabling;

import io.edukativ.myskoolin.infrastructure.subjects.SubjectDTO;

import java.io.Serializable;

public class SchoolClassTimeSlotVO extends TimeSlotVO implements Serializable {

    private String schoolRoomId;
    private String schoolClassId;
    private SubjectDTO subject;
    private String teacherId;

    public String getSchoolRoomId() {
        return schoolRoomId;
    }

    public void setSchoolRoomId(String schoolRoomId) {
        this.schoolRoomId = schoolRoomId;
    }

    public String getSchoolClassId() {
        return schoolClassId;
    }

    public void setSchoolClassId(String schoolClassId) {
        this.schoolClassId = schoolClassId;
    }

    public SubjectDTO getSubject() {
        return subject;
    }

    public void setSubject(SubjectDTO subject) {
        this.subject = subject;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
}
