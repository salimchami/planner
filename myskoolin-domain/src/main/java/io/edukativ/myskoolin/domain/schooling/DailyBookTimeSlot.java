package io.edukativ.myskoolin.domain.schooling;

import io.edukativ.myskoolin.domain.entity.Subject;
import io.edukativ.myskoolin.domain.timetabling.TimeSlot;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

/**
 * Daily book (cahier de texte) timeslot
 */
public class DailyBookTimeSlot extends TimeSlot implements Serializable {

    private Subject subject;
    private List<String> teachers;
    private Instant targetDate;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<String> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<String> teachers) {
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
        DailyBookTimeSlot that = (DailyBookTimeSlot) o;
        return Objects.equals(subject, that.subject) &&
                Objects.equals(teachers, that.teachers) &&
                Objects.equals(targetDate, that.targetDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subject, teachers, targetDate);
    }
}
