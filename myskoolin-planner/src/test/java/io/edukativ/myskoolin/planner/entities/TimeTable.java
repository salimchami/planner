package io.edukativ.myskoolin.planner.entities;

import io.edukativ.myskoolin.planner.Score;
import io.edukativ.myskoolin.planner.declarations.BaseVariables;
import io.edukativ.myskoolin.planner.declarations.Facts;
import io.edukativ.myskoolin.planner.declarations.ModifiableVariables;
import io.edukativ.myskoolin.planner.declarations.PlanningSolution;

import java.time.Instant;
import java.util.List;

@PlanningSolution
public class TimeTable {

    @BaseVariables
    private final List<Timeslot> baseTimeslots;

    @ModifiableVariables
    private List<Timeslot> timeslots;

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
        return null;
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
}
