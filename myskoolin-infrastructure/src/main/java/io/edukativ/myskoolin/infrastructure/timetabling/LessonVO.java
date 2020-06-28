package io.edukativ.myskoolin.infrastructure.timetabling;

import io.edukativ.myskoolin.infrastructure.schoolclasses.SchoolClassDTO;
import io.edukativ.myskoolin.infrastructure.schoolrooms.SchoolRoomDTO;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectDTO;
import io.edukativ.myskoolin.infrastructure.teachers.TeacherDTO;

public class LessonVO {

    private String id;
    private SchoolRoomDTO schoolRoom;
    private SubjectDTO subject;
    private TeacherDTO teacher;
    private TimeSlotVO timeSlot;
    //FIXME: add option entity
    //private Subject option;
    private SchoolClassDTO schoolClass;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SchoolRoomDTO getSchoolRoom() {
        return schoolRoom;
    }

    public void setSchoolRoom(SchoolRoomDTO schoolRoom) {
        this.schoolRoom = schoolRoom;
    }

    public SubjectDTO getSubject() {
        return subject;
    }

    public void setSubject(SubjectDTO subject) {
        this.subject = subject;
    }

    public TeacherDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDTO teacher) {
        this.teacher = teacher;
    }

    public TimeSlotVO getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlotVO timeSlot) {
        this.timeSlot = timeSlot;
    }

    public SchoolClassDTO getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClassDTO schoolClass) {
        this.schoolClass = schoolClass;
    }
}
