package io.edukativ.myskoolin.planner.entities;

public interface TimeTableSPI {
    void saveTimeTable(TimeTable schoolClassTimeTable);

    void save(TimeTable timeTable);
}
