package io.edukativ.myskoolin.domain.timetabling;

import io.edukativ.myskoolin.domain.schoolclasses.SchoolClass;
import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoom;
import io.edukativ.myskoolin.domain.subjects.Subject;
import io.edukativ.myskoolin.domain.teachers.Teacher;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.SolverStatus;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@PlanningSolution
public class SchoolClassTimeTable {

    private String id;

    @PlanningEntityCollectionProperty
    private List<Lesson> lessons;

    private List<Lesson> events;

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "timeSlotRange")
    private List<TimeSlot> timeSlots;

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "schoolRoomRange")
    private List<SchoolRoom> schoolRooms;

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "subjectRange")
    private List<Subject> subjects;

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "teacherRange")
    private List<Teacher> teachers;

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "schoolClassRange")
    private List<SchoolClass> schoolClasses;

    @PlanningScore
    private HardSoftScore score;

    private SolverStatus solverStatus;

    private Instant lastGenerationDate;
    private SchoolClass schoolClass;

    public SchoolClassTimeTable() {
    }

    public SchoolClassTimeTable(List<SchoolClass> schoolClasses, List<SchoolRoom> schoolRooms, List<Subject> subjects, List<Teacher> teachers) {
        this.schoolClasses = schoolClasses;
        this.schoolRooms = schoolRooms;
        this.subjects = subjects;
        this.teachers = teachers;
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

    public SolverStatus getSolverStatus() {
        return solverStatus;
    }

    public void setSolverStatus(SolverStatus solverStatus) {
        this.solverStatus = solverStatus;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }
}
