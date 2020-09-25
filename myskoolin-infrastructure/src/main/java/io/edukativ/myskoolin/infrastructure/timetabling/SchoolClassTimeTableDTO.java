package io.edukativ.myskoolin.infrastructure.timetabling;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.optaplanner.core.api.solver.SolverStatus;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class SchoolClassTimeTableDTO implements Serializable {

    private String id;
    private String clientId;
    private List<LessonVO> staticTimeTable;
    private List<LessonVO> events;
    private Instant lastGenerationDate;
    private SolverStatus solverStatus;
    private HardSoftScoreVO score;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SolverStatus getSolverStatus() {
        return solverStatus;
    }

    public void setSolverStatus(SolverStatus solverStatus) {
        this.solverStatus = solverStatus;
    }

    public HardSoftScoreVO getScore() {
        return score;
    }

    public void setScore(HardSoftScoreVO score) {
        this.score = score;
    }

    public List<LessonVO> getStaticTimeTable() {
        return staticTimeTable;
    }

    public void setStaticTimeTable(List<LessonVO> staticTimeTable) {
        this.staticTimeTable = staticTimeTable;
    }

    public List<LessonVO> getEvents() {
        return events;
    }

    public void setEvents(List<LessonVO> events) {
        this.events = events;
    }

    public Instant getLastGenerationDate() {
        return lastGenerationDate;
    }

    public void setLastGenerationDate(Instant lastGenerationDate) {
        this.lastGenerationDate = lastGenerationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SchoolClassTimeTableDTO that = (SchoolClassTimeTableDTO) o;
        return staticTimeTable.equals(that.staticTimeTable) &&
                Objects.equals(events, that.events) &&
                Objects.equals(lastGenerationDate, that.lastGenerationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(staticTimeTable, events, lastGenerationDate);
    }

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {

            return "SchoolClassTimeTable{" +
                    ", staticTimeTable=" + staticTimeTable +
                    ", events=" + events +
                    ", lastGenerationDate=" + lastGenerationDate +
                    '}';
        }
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
