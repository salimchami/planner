package io.edukativ.myskoolin.infrastructure.schooling.vo;

import io.edukativ.myskoolin.infrastructure.subjects.SubjectDTO;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

/**
 * Daily book (cahier de texte) timeslot
 */
public class DailyBookTimeSlotVO extends TimeSlotDbVO implements Serializable {

    private SubjectDTO subject;
    private List<ObjectId> teachers;
    private Instant targetDate;

    public SubjectDTO getSubject() {
        return subject;
    }

    public void setSubject(SubjectDTO subject) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DailyBookTimeSlotVO that = (DailyBookTimeSlotVO) o;
        return Objects.equals(subject, that.subject) &&
                Objects.equals(teachers, that.teachers) &&
                Objects.equals(targetDate, that.targetDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subject, teachers, targetDate);
    }
}
