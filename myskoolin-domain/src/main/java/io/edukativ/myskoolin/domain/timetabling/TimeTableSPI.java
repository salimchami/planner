package io.edukativ.myskoolin.domain.timetabling;

import java.util.Optional;

public interface TimeTableSPI {

    Optional<SchoolClassTimeTable> findById(String timeTableId);
}
