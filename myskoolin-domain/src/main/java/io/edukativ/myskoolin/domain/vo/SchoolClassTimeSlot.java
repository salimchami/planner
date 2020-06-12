package io.edukativ.myskoolin.domain.vo;

import io.edukativ.myskoolin.domain.entity.Subject;

import java.util.Objects;

public class SchoolClassTimeSlot extends TimeSlot {

    private String schoolRoomId;
    private String schoolClassId;
    private Subject subject;
    private String teacherId;

    public SchoolClassTimeSlot() {

    }

    public SchoolClassTimeSlot(TimeSlot timeSlot) {
        this(timeSlot, null, null, null, null);
    }

    public SchoolClassTimeSlot(TimeSlot timeSlot, String schoolClassId, String schoolRoomId, Subject subject, String teacherId) {
        super(timeSlot.getTitle(), timeSlot.getSecondTitle(), timeSlot.getComment(), timeSlot.getCanceled(),
                timeSlot.getDay(), timeSlot.getStartTime(), timeSlot.getEndTime(), timeSlot.getDate(), timeSlot.getBgColor(),
                timeSlot.getFontColorCssClass(), timeSlot.getAutoAlterable(), timeSlot.isHalf());
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
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
        TimeSlot timeSlot = (TimeSlot) o;
        return getDay().equals(timeSlot.getDay()) &&
                getStartTime().equals(timeSlot.getStartTime()) &&
                getEndTime().equals(timeSlot.getEndTime()) &&
                getDate().equals(timeSlot.getDate());
    }

    public boolean hasSameTimes(TimeSlot timeSlot) {
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
