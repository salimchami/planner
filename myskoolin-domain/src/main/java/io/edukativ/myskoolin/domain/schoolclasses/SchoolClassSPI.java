package io.edukativ.myskoolin.domain.schoolclasses;

import io.edukativ.myskoolin.domain.timetabling.SchoolClassTimeTable;

import java.util.Optional;

public interface SchoolClassSPI {

    Optional<SchoolClass> findById(String schoolClassId);

    Optional<SchoolClass> saveTimeTable(String schoolClassId, SchoolClassTimeTable schoolClassTimeTable);

}
