package io.edukativ.myskoolin.planner.entities;

import io.edukativ.myskoolin.planner.declarations.ModifiableVariable;
import io.edukativ.myskoolin.planner.declarations.ModifiableVariableId;
import io.edukativ.myskoolin.planner.declarations.ModifiableVariableItem;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

@ModifiableVariable
public class Timeslot {

    @ModifiableVariableId
    private final Long id;
    @ModifiableVariableItem
    private final DayOfWeek day;
    @ModifiableVariableItem
    private final LocalTime startTime;
    @ModifiableVariableItem
    private final LocalTime endTime;
    @ModifiableVariableItem
    private Subject subject;

    public Timeslot(Long id, DayOfWeek day, LocalTime startTime, LocalTime endTime, Subject subject) {
        this.id = id;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.subject = subject;
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

    public DayOfWeek getDay() {
        return day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public static Integer totalDuration(List<Timeslot> timeSlots) {
        return timeSlots.stream().map(Timeslot::durationInMinutes).mapToInt(Long::intValue).sum();
    }

    public Long durationInMinutes() {
        return Duration.between(startTime, endTime).toMinutes();
    }

    @Override
    public String toString() {
        return "Timeslot{" +
                "id=" + id +
                ", day=" + day +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    public Subject getSubject() {
        return subject;
    }
}
