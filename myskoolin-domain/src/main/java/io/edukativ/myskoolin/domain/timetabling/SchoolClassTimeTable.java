package io.edukativ.myskoolin.domain.timetabling;

import io.edukativ.myskoolin.domain.schoolclasses.SchoolClass;
import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoom;
import io.edukativ.myskoolin.domain.subjects.Subject;
import io.edukativ.myskoolin.domain.teachers.Teacher;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.SolverStatus;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@PlanningSolution
public class SchoolClassTimeTable {

    private String id;
    private String clientId;
    @PlanningEntityCollectionProperty
    private List<Lesson> lessons;

    private List<Lesson> events;

    @ValueRangeProvider(id = "timeSlotRange")
    private List<TimeSlot> timeSlots;

    @ProblemFactCollectionProperty
    private List<SchoolRoom> schoolRooms;

    @ProblemFactCollectionProperty
    private List<Subject> subjects;

    @ProblemFactCollectionProperty
    private List<Teacher> teachers;

    @ProblemFactCollectionProperty
    private List<SchoolClass> schoolClasses;

    @PlanningScore
    private HardSoftScore score;

    private SolverStatus solverStatus;

    private Instant lastGenerationDate;

    private SchoolClass schoolClass;

    public SchoolClassTimeTable() {
        this.timeSlots = new ArrayList<>();
        this.lessons = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    public SchoolClassTimeTable(String clientId, SchoolClass schoolClass, List<SchoolClass> schoolClasses, List<SchoolRoom> schoolRooms,
                                List<Subject> subjects, List<Teacher> teachers, List<Lesson> lessons, List<TimeSlot> timeSlots) {
        this.id = schoolClass.getId();
        this.schoolClass = schoolClass;
        this.schoolClasses = schoolClasses;
        this.schoolRooms = schoolRooms;
        this.subjects = subjects;
        this.teachers = teachers;
        this.timeSlots = timeSlots;
        this.lessons = lessons;
        this.events = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SchoolClassTimeTable that = (SchoolClassTimeTable) o;
        return timeSlots.equals(that.timeSlots) &&
                Objects.equals(events, that.events) &&
                Objects.equals(lastGenerationDate, that.lastGenerationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeSlots, events, lastGenerationDate);
    }

    @Override
    public String toString() {
        return "SchoolClassTimeTable{" +
                ", staticTimeTable=" + timeSlots +
                ", events=" + events +
                ", lastGenerationDate=" + lastGenerationDate +
                '}';
    }

    public List<Lesson> getLessons() {
        if (this.lessons == null) {
            return new ArrayList<>();
        }
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Lesson> getEvents() {
        return events;
    }

    public void setEvents(List<Lesson> events) {
        this.events = events;
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public List<SchoolRoom> getSchoolRooms() {
        return schoolRooms;
    }

    public void setSchoolRooms(List<SchoolRoom> schoolRooms) {
        this.schoolRooms = schoolRooms;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<SchoolClass> getSchoolClasses() {
        return schoolClasses;
    }

    public void setSchoolClasses(List<SchoolClass> schoolClasses) {
        this.schoolClasses = schoolClasses;
    }

    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

    public SolverStatus getSolverStatus() {
        return solverStatus;
    }

    public void setSolverStatus(SolverStatus solverStatus) {
        this.solverStatus = solverStatus;
    }

    public Instant getLastGenerationDate() {
        return lastGenerationDate;
    }

    public void setLastGenerationDate(Instant lastGenerationDate) {
        this.lastGenerationDate = lastGenerationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
