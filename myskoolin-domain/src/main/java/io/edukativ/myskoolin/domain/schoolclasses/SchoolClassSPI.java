package io.edukativ.myskoolin.domain.schoolclasses;

import io.edukativ.myskoolin.domain.timetabling.SchoolClassTimeTable;

import java.util.List;

public interface SchoolClassSPI {

    List<SchoolClass> findAllByClientId(String clientId);

    SchoolClass findById(String schoolClassId);

    void saveTimeTable(SchoolClassTimeTable schoolClassTimeTable);

}
