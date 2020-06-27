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

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }
}
