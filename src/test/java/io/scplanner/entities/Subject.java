package io.scplanner.entities;

import io.scplanner.annotations.Fact;
import io.scplanner.annotations.FactId;
import io.scplanner.annotations.FactItem;

import java.util.Objects;

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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public Integer getCoursesFrequencyPerWeek() {
        return coursesFrequencyPerWeek;
    }

    public int maxMinutesPerDayPenalty(Integer totalDuration) {
        if (totalDuration > maxMinutesPerDay) {
            return totalDuration;
        }
        return 0;
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
}
