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

    public int correctDurationPenalty(Set<Timeslot> timeslots) {
        if(!correctTotalDuration(timeslots, minutesPerWeek, minutesPerWeek)) {

        } else if(!correctDurationByDay(timeslots)) {

        }



        final Map<DayOfWeek, Set<Timeslot>> subjectTimeslots = timeslots.stream()
                .filter(timeslot -> this.equals(timeslot.getSubject()))
                .collect(groupingBy(Timeslot::getDay, mapping(Function.identity(), toSet())));

        final Optional<Long> maxTotalDurationByDay = subjectTimeslots.values().stream()
                .map(timeslotSet -> {
                    final long sum = timeslotSet.stream().mapToLong(Timeslot::durationInMinutes).sum();
                    return sum;
                })
                .max(Long::compare);

        final Integer integer = maxTotalDurationByDay
                .map(totalDuration -> {
                    final Integer totalDurationPenalty = penaltyFromTotalDuration(totalDuration);
                    return totalDurationPenalty;
                })
                .orElse(minutesPerWeek);
        return integer;
    }

    private Integer penaltyFromTotalDuration(Long totalDuration) {
        if (totalDuration.intValue() < minMinutesPerDay) {
            return maxMinutesPerDay;
        } else if (totalDuration.intValue() > maxMinutesPerDay) {
            return totalDuration.intValue();
        }
        return 0;
    }

    public Boolean correctDuration(Set<Timeslot> timeslots) {
        final boolean correctTotalDuration = correctTotalDuration(timeslots, minutesPerWeek, minutesPerWeek);
        final boolean correctDurationByDay = correctDurationByDay(timeslots);
        return correctTotalDuration && correctDurationByDay;
    }

    private boolean correctTotalDuration(Set<Timeslot> timeslots, Integer maxMinutes, Integer minMinutes) {
        final Integer totalDuration = Timeslot.totalDurationInMinutes(timeslots, this);
        final boolean correctMax = totalDuration <= maxMinutes;
        final boolean correctMin = totalDuration >= minMinutes;
        return correctMin && correctMax;
    }

    private boolean correctDurationByDay(Set<Timeslot> timeslots) {
        final Map<DayOfWeek, Set<Timeslot>> timeslotsByDay = timeslots.stream()
                .filter(timeslot -> this.equals(timeslot.getSubject()))
                .collect(groupingBy(Timeslot::getDay, mapping(Function.identity(), toSet())));

        return timeslotsByDay.entrySet().stream()
                .allMatch(entry -> correctTotalDuration(entry.getValue(), maxMinutesPerDay, minMinutesPerDay));
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
