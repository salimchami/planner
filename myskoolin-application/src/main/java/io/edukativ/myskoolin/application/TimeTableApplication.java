package io.edukativ.myskoolin.application;

import io.edukativ.myskoolin.domain.schoolclasses.SchoolClass;
import io.edukativ.myskoolin.domain.timetabling.TimeTableGenerationAPI;
import io.edukativ.myskoolin.infrastructure.schoolclasses.SchoolClassDTO;
import io.edukativ.myskoolin.infrastructure.schoolclasses.SchoolClassMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class TimeTableApplication {

    private final TimeTableGenerationAPI timeTableGenerationAPI;
    private final SchoolClassMapper schoolClassMapper;

    public TimeTableApplication(TimeTableGenerationAPI timeTableGenerationAPI, SchoolClassMapper schoolClassMapper) {
        this.timeTableGenerationAPI = timeTableGenerationAPI;
        this.schoolClassMapper = schoolClassMapper;
    }

    @Transactional
    public List<SchoolClassDTO> generateNewTimeTablesForSchoolClasses() {
        List<SchoolClass> schoolClasses = timeTableGenerationAPI.solveForAllSchoolClasses();
        return schoolClassMapper.domainsToDtos(schoolClasses);
    }

    @Transactional
    public SchoolClassDTO generateNewTimeTablesForSchoolClass(String id) {
        SchoolClass schoolClass = timeTableGenerationAPI.solveForSchoolClass(id);
        return schoolClassMapper.domainToDto(schoolClass);
    }
}
