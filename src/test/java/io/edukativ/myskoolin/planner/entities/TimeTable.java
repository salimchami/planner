package io.edukativ.myskoolin.planner.entities;

import io.edukativ.myskoolin.planner.Score;
import io.edukativ.myskoolin.planner.declarations.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@PlanningSolution
public class TimeTable {

    @SolutionId
    private String schoolClassName;

    @BasePlanningVariables
    private final List<Timeslot> baseTimeslots;

    @ModifiablePlanningVariables
    private List<Timeslot> timeslots = new ArrayList<>();

    @Facts
    private final List<Subject> subjects;

    private Instant lastGenerationDate;
    
    private Score score;

    public TimeTable(List<Timeslot> baseTimeslots, List<Subject> subjects) {
        this.baseTimeslots = baseTimeslots;
        this.subjects = subjects;
    }

    public List<Timeslot> getTimeslots() {
        return timeslots;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public List<Timeslot> getBaseTimeslots() {
        return baseTimeslots;
    }

    public Instant getLastGenerationDate() {
        return lastGenerationDate;
    }

    public void setTimeslots(List<Timeslot> timeslots) {
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
