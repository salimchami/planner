package io.edukativ.myskoolin.domain.timetabling;

import io.edukativ.myskoolin.domain.schoolclasses.SchoolClass;
import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoom;
import io.edukativ.myskoolin.domain.subjects.Subject;
import io.edukativ.myskoolin.domain.teachers.Teacher;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Lesson {

    private String id;
    @PlanningVariable(valueRangeProviderRefs = "schoolRoomRange")
    private SchoolRoom schoolRoom;
    @PlanningVariable(valueRangeProviderRefs = "subjectRange")
    private Subject subject;
    @PlanningVariable(valueRangeProviderRefs = "teacherRange")
    private Teacher teacher;
    @PlanningVariable(valueRangeProviderRefs = "timeSlotRange")
    private TimeSlot timeSlot;
    //FIXME: add option entity
    //private Subject option;
    private SchoolClass schoolClass;

    public Lesson() {
    }

    public Lesson(String id, SchoolRoom schoolRoom, Subject subject, Teacher teacher, TimeSlot timeSlot, SchoolClass schoolClass) {
        this.id = id;
        this.schoolRoom = schoolRoom;
        this.subject = subject;
        this.teacher = teacher;
        this.timeSlot = timeSlot;
        this.schoolClass = schoolClass;
    }

    public String getId() {
        return id;
    }

    public SchoolRoom getSchoolRoom() {
        return schoolRoom;
    }

    public Subject getSubject() {
        return subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSchoolRoom(SchoolRoom schoolRoom) {
        this.schoolRoom = schoolRoom;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }
}
