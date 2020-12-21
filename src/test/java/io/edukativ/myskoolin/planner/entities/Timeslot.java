package io.edukativ.myskoolin.planner.entities;

import io.edukativ.myskoolin.planner.declarations.PlanningVariable;
import io.edukativ.myskoolin.planner.declarations.PlanningVariableId;
import io.edukativ.myskoolin.planner.declarations.PlanningVariableItem;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

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
    @PlanningVariableItem
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

    public Timeslot(Long id, DayOfWeek day, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public static Integer totalDurationInMinutes(List<Timeslot> timeSlots) {
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
