package io.scplanner.entities;

import io.scplanner.annotations.Fact;
import io.scplanner.annotations.FactId;
import io.scplanner.annotations.FactItem;

import java.time.DayOfWeek;
import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

@Fact
public class Subject {

    @FactId
    private final Long id;
    private final String name;
    @FactItem
    private final Integer maxMinutesPerDay;

    @FactItem
    private final Integer minMinutesPerDay;

    @FactItem
    private final Integer minutesPerWeek;
    @FactItem
    private final Integer coursesFrequencyPerWeek;

    public Subject(Long id, String name, Integer maxMinutesPerDay, Integer minMinutesPerDay, Integer minutesPerWeek, Integer coursesFrequencyPerWeek) {
        this.id = id;
        this.name = name;
        this.maxMinutesPerDay = maxMinutesPerDay;
        this.minMinutesPerDay = minMinutesPerDay;
        this.minutesPerWeek = minutesPerWeek;
        this.coursesFrequencyPerWeek = coursesFrequencyPerWeek;
    }

    public Integer getMaxMinutesPerDay() {
        return maxMinutesPerDay;
    }

    public Integer getMinMinutesPerDay() {
        return minMinutesPerDay;
    }

    public Integer getMinutesPerWeek() {
        return minutesPerWeek;
    }

    public int correctDurationPenalty(Set<Timeslot> timeslots) {
        if (!correctTotalDuration(timeslots, minutesPerWeek, minutesPerWeek)) {
            return incorrectTotalDurationPenalty(timeslots);
        } else if (!correctDurationByDay(timeslots)) {
            return incorrectDurationByDayPenalty(timeslots);
        }
        return 0;
    }

    private boolean correctTotalDuration(Set<Timeslot> timeslots, Integer maxMinutes, Integer minMinutes) {
        final Integer totalDuration = Timeslot.totalDurationInMinutes(timeslots, this);
        final boolean correctMax = totalDuration <= maxMinutes;
        final boolean correctMin = totalDuration >= minMinutes;
        return correctMin && correctMax;
    }

    private int incorrectTotalDurationPenalty(Set<Timeslot> timeslots) {
        final int sum = timeslots.stream()
                .filter(timeslot -> this.equals(timeslot.getSubject()))
                .map(Timeslot::durationInMinutes)
                .mapToInt(Long::intValue)
                .sum();
        return sum == 0 || sum < minutesPerWeek ? minutesPerWeek : sum;
    }

    private boolean correctDurationByDay(Set<Timeslot> timeslots) {
        final Map<DayOfWeek, Set<Timeslot>> timeslotsByDay = timeslots.stream()
                .filter(timeslot -> this.equals(timeslot.getSubject()))
                .collect(groupingBy(Timeslot::getDay, mapping(Function.identity(), toSet())));

        return timeslotsByDay.entrySet().stream()
                .allMatch(entry -> correctTotalDuration(entry.getValue(), maxMinutesPerDay, minMinutesPerDay));
    }

    private int incorrectDurationByDayPenalty(Set<Timeslot> timeslots) {
        final Map<DayOfWeek, Set<Timeslot>> timeslotsByDay = timeslots.stream()
                .filter(timeslot -> this.equals(timeslot.getSubject()))
                .collect(groupingBy(Timeslot::getDay, mapping(Function.identity(), toSet())));
        final Set<Timeslot> overflowTimeslots = timeslotsByDay.values().stream()
                .filter(dayTimeslots -> Timeslot.totalDurationInMinutes(dayTimeslots) > maxMinutesPerDay
                        || Timeslot.totalDurationInMinutes(dayTimeslots) < minMinutesPerDay)
                .flatMap(Collection::stream)
                .collect(toSet());
        return Timeslot.totalDurationInMinutes(overflowTimeslots);
    }

    public Boolean correctDuration(Set<Timeslot> timeslots) {
        final boolean correctTotalDuration = correctTotalDuration(timeslots, minutesPerWeek, minutesPerWeek);
        final boolean correctDurationByDay = correctDurationByDay(timeslots);
        return correctTotalDuration && correctDurationByDay;
    }

    public Long durationOfSubject(Set<Timeslot> timeslots) {
        return timeslots.stream()
                .filter(timeslot -> timeslot.getSubject() != null)
                .filter(timeslot -> equals(timeslot.getSubject()))
                .mapToLong(Timeslot::durationInMinutes).sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(id, subject.id) &&
                Objects.equals(name, subject.name) &&
                Objects.equals(maxMinutesPerDay, subject.maxMinutesPerDay) &&
                Objects.equals(minMinutesPerDay, subject.minMinutesPerDay) &&
                Objects.equals(minutesPerWeek, subject.minutesPerWeek) &&
                Objects.equals(coursesFrequencyPerWeek, subject.coursesFrequencyPerWeek);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, maxMinutesPerDay, minMinutesPerDay, minutesPerWeek, coursesFrequencyPerWeek);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
