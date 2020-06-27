package io.edukativ.myskoolin.domain.timetabling;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class SchoolClassTimeTable implements Serializable {

    private List<SchoolClassTimeSlot> staticTimeTable;
    private List<SchoolClassTimeSlot> events;
    private Instant lastGenerationDate;

    public List<SchoolClassTimeSlot> getStaticTimeTable() {
        return staticTimeTable;
    }

    public void setStaticTimeTable(List<SchoolClassTimeSlot> staticTimeTable) {
        this.staticTimeTable = staticTimeTable;
    }

    public List<SchoolClassTimeSlot> getEvents() {
        return events;
    }

    public void setEvents(List<SchoolClassTimeSlot> events) {
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
        SchoolClassTimeTable that = (SchoolClassTimeTable) o;
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
        return "SchoolClassTimeTable{" +
                ", staticTimeTable=" + staticTimeTable +
                ", events=" + events +
                ", lastGenerationDate=" + lastGenerationDate +
                '}';
    }
}
