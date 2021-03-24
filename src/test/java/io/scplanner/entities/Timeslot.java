package io.scplanner.entities;

import io.scplanner.annotations.PlanningVariable;
import io.scplanner.annotations.PlanningVariableFact;
import io.scplanner.annotations.PlanningVariableId;
import io.scplanner.annotations.PlanningVariableItem;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@PlanningVariable
public class Timeslot {

    @PlanningVariableId
    private Long id;
    @PlanningVariableItem
    private DayOfWeek day;
    @PlanningVariableItem
    private LocalTime startTime;
    @PlanningVariableItem
    private LocalTime endTime;
    @PlanningVariableFact
    private Subject subject;

    public Timeslot() {
    }

    public Timeslot(Long id, DayOfWeek day, LocalTime startTime, LocalTime endTime, Subject subject) {
        this.id = id;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.subject = subject;
    }

    public Timeslot newInstance() {
        return new Timeslot(id, day, startTime, endTime, subject);
    }

    public Timeslot(Long id, DayOfWeek day, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    /**
     * @param timeSlots Time slot must contains subject.
     */
    public static Integer totalDurationInMinutes(Set<Timeslot> timeSlots) {
        return timeSlots.stream().filter(timeslot -> timeslot.getSubject() != null).map(Timeslot::durationInMinutes).mapToInt(Long::intValue).sum();
    }

    public static Integer totalDurationInMinutes(Set<Timeslot> timeSlots, Subject subject) {
        return totalDurationInMinutes(timeSlots.stream()
                .filter(timeslot -> subject.equals(timeslot.getSubject())).collect(toSet()));
    }

    public static Integer favorableScore(List<Timeslot> timeslots) {
        return timeslots.stream().map(Timeslot::favorableScore).mapToInt(Integer::intValue).sum();
    }

    public Integer favorableScore() {
        if (subject == null) {
            return -durationInMinutes().intValue();
        } else {
            return durationInMinutes().intValue();
        }
    }

    public Long durationInMinutes() {
        return Duration.between(startTime, endTime).toMinutes();
    }

    public Subject getSubject() {
        return subject;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Timeslot timeslot = (Timeslot) o;
        return id.equals(timeslot.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "\nTimeslot{" +
                "id=" + id +
                ", day=" + day +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", subject=" + subject +
                '}';
    }
}
