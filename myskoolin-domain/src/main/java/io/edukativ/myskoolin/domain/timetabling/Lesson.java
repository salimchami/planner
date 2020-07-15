package io.edukativ.myskoolin.domain.timetabling;

import io.edukativ.myskoolin.domain.schoolclasses.SchoolClass;
import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoom;
import io.edukativ.myskoolin.domain.subjects.Subject;
import io.edukativ.myskoolin.domain.teachers.Teacher;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Lesson {

    @PlanningId
    private Long id;
    private SchoolRoom schoolRoom;
    private Subject subject;
    private Teacher teacher;
    @PlanningVariable(valueRangeProviderRefs = "timeSlotRange")
    private TimeSlot timeSlot;
    //FIXME: add option entity
    //private Subject option;
    private SchoolClass schoolClass;

    public Lesson() {
    }

    public Lesson(Long id, SchoolRoom schoolRoom, Subject subject, Teacher teacher, TimeSlot timeSlot, SchoolClass schoolClass) {
        this.id = id;
        this.schoolRoom = schoolRoom;
        this.subject = subject;
        this.teacher = teacher;
        this.timeSlot = timeSlot;
        this.schoolClass = schoolClass;
    }

    public Long getId() {
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

    public void setId(Long id) {
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

    public boolean isOverlapping(TimeSlot timeSlot) {
        return this.timeSlot.isOverlapping(timeSlot);
    }

    public boolean isOverlapping(Lesson lesson2) {
        return this.timeSlot.isOverlapping(lesson2.getTimeSlot());
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "schoolRoom=" + schoolRoom +
                ", subject=" + subject +
                ", teacher=" + teacher +
                ", timeSlot=" + timeSlot +
                ", schoolClass=" + schoolClass +
                '}';
    }
}
