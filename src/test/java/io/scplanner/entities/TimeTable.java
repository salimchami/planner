package io.scplanner.entities;

import io.scplanner.annotations.*;
import io.scplanner.score.Score;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@PlanningSolution
public class TimeTable {

    @SolutionId
    private String schoolClassName;

    @BasePlanningVariables
    private final Set<Timeslot> baseTimeslots;

    @ModifiablePlanningVariables
    private Set<Timeslot> timeslots = new HashSet<>();

    @Facts
    private final List<Subject> subjects;

    private Instant lastGenerationDate;
    
    private Score score;

    public TimeTable(Set<Timeslot> baseTimeslots, List<Subject> subjects) {
        this.baseTimeslots = baseTimeslots;
        this.subjects = subjects;
    }

    public Set<Timeslot> getTimeslots() {
        return timeslots;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public Set<Timeslot> getBaseTimeslots() {
        return baseTimeslots;
    }

    public Instant getLastGenerationDate() {
        return lastGenerationDate;
    }

    public void setTimeslots(Set<Timeslot> timeslots) {
        this.timeslots = timeslots;
    }

    public void setLastGenerationDate(Instant lastGenerationDate) {
        this.lastGenerationDate = lastGenerationDate;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "TimeTable{" +
                "timeslots=" + timeslots +
                ", lastGenerationDate=" + lastGenerationDate +
                ", score=" + score +
                '}';
    }

    public String getSchoolClassName() {
        return schoolClassName;
    }
}
