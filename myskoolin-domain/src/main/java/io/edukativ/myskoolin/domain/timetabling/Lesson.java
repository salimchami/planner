package io.edukativ.myskoolin.domain.timetabling;

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

    @PlanningVariable(valueRangeProviderRefs = SchoolClassTimeTable.SCHOOL_ROOM_RANGE)
    private SchoolRoom schoolRoom;

    @PlanningVariable(valueRangeProviderRefs = SchoolClassTimeTable.TIME_SLOT_RANGE)
    private TimeSlot timeSlot;

    private Subject subject;
    private Teacher teacher;

    public Lesson() {
    }

    public Lesson(Long id, SchoolRoom schoolRoom, Subject subject, Teacher teacher, TimeSlot timeSlot) {
        this.id = id;
        this.schoolRoom = schoolRoom;
        this.subject = subject;
        this.teacher = teacher;
        this.timeSlot = timeSlot;
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
                '}';
    }

    public Integer overlappingGap(Lesson lesson2) {
        return timeSlot.overlappingGap(lesson2.getTimeSlot()).intValue();
    }

    public boolean isSameOverlappingAndSameSchoolRoom(Lesson lesson) {
        return isOverlapping(lesson) && this.schoolRoom.equals(lesson.getSchoolRoom());
    }

    public boolean isSameSchoolRoomIfConsecutiveLessons(Lesson lesson2) {
        final boolean sameSubjects = this.subject.equals(lesson2.subject);
        final boolean consecutive = this.timeSlot.isConsecutive(lesson2.timeSlot);
        final boolean sameTimeslots = this.schoolRoom.equals(lesson2.schoolRoom);
        return sameSubjects
                && consecutive
                && sameTimeslots;
    }

    public static int sameSchoolRoomsConsecutiveLessonsGap(Lesson lesson1, Lesson lesson2) {
        //TODO: add distance between school rooms
        return 1;
    }

    public boolean hasRightSchoolRoomType() {
        return subject.getSchoolRoomsTypes().contains(schoolRoom.getType());
    }

    public static int schoolRoomTypeConflictPenalty(Lesson lesson) {
        if (!lesson.hasRightSchoolRoomType()) {
            return lesson.timeSlot.durationInMinutes().intValue();
        }
        return 0;
    }

    public int wrongSchoolRoomTypePenalty() {
        return timeSlot.durationInMinutes().intValue();
    }
}
