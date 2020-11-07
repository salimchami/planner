package io.edukativ.myskoolin.planner.entities;

import io.edukativ.myskoolin.planner.entities.TimeTable;

public interface TimeTableSPI {
    void saveTimeTable(TimeTable schoolClassTimeTable);
}
