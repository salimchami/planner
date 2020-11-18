package io.edukativ.myskoolin.planner.entities;

import io.edukativ.myskoolin.planner.declarations.Fact;
import io.edukativ.myskoolin.planner.declarations.FactId;
import io.edukativ.myskoolin.planner.declarations.FactItem;

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
}
