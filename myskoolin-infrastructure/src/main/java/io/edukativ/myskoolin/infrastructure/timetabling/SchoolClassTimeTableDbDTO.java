package io.edukativ.myskoolin.infrastructure.timetabling;

import io.edukativ.myskoolin.infrastructure.schoolclasses.SchoolClassDbDTO;
import org.optaplanner.core.api.solver.SolverStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "schoolclasses_timetables")
public class SchoolClassTimeTableDbDTO implements Serializable {

    public static final String MONGO_FIELD_EVENTS = "events";
    public static final String MONGO_FIELD_LAST_GENERATION_DATE = "last_generation_date";
    public static final String MONGO_FIELD_SCORES = "scores";
    public static final String MONGO_FIELD_LESSONS = "lessons";
    public static final String MONGO_FIELD_SOLVER_STATUS = "solver_status";
    public static final String MONGO_FIELD_SCHOOL_CLASS = "school_class";

    @Id
    private String id;

    @Field(MONGO_FIELD_LESSONS)
    private List<LessonDbVO> lessons;

    @Field(MONGO_FIELD_EVENTS)
    private List<LessonDbVO> events;

    @Field(MONGO_FIELD_SCORES)
    private HardSoftScoreDbVO score;

    @Field(MONGO_FIELD_SOLVER_STATUS)
    private SolverStatus solverStatus;

    @Field(MONGO_FIELD_LAST_GENERATION_DATE)
    private Instant lastGenerationDate;

    @DBRef
    @Field(MONGO_FIELD_SCHOOL_CLASS)
    private SchoolClassDbDTO schoolClass;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<LessonDbVO> getLessons() {
        if(lessons == null) {
            return lessons = new ArrayList<>();
        }
        return lessons;
    }

    public void setLessons(List<LessonDbVO> lessons) {
        this.lessons = lessons;
    }

    public List<LessonDbVO> getEvents() {
        if (events == null) {
            return new ArrayList<>();
        }
        return events;
    }

    public void setEvents(List<LessonDbVO> events) {
        this.events = events;
    }

    public HardSoftScoreDbVO getScore() {
        return score;
    }

    public void setScore(HardSoftScoreDbVO score) {
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

    public SchoolClassDbDTO getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClassDbDTO schoolClass) {
        this.schoolClass = schoolClass;
    }
}
