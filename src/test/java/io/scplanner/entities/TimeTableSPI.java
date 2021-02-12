package io.scplanner.entities;

public interface TimeTableSPI {
    void saveTimeTable(TimeTable schoolClassTimeTable);

    void save(TimeTable timeTable);
}
