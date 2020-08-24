package io.edukativ.myskoolin.domain.timetabling;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface TimeTableSPI {

    Optional<SchoolClassTimeTable> findById(String timeTableId);

    SchoolClassTimeTable saveTimeTable(SchoolClassTimeTable schoolClassTimeTable);

    List<SchoolClassTimeTable> findAllByClientId(String clientId);

    Optional<SchoolClassTimeTable> findBySchoolCLassId(String schoolCLassId);

    long countTimeTables();

    Instant lastGenerationDate();

}
