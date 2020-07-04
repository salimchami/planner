package io.edukativ.myskoolin.infrastructure.timetabling;

import io.edukativ.myskoolin.domain.timetabling.SchoolClassTimeTable;
import io.edukativ.myskoolin.domain.timetabling.TimeTableSPI;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SchoolClassTimeTableProvider implements TimeTableSPI {

    private final SchoolClassTimeTableRepository schoolClassTimeTableRepository;
    private final SchoolClassTimeTableMapper schoolClassTimeTableMapper;

    public SchoolClassTimeTableProvider(SchoolClassTimeTableRepository schoolClassTimeTableRepository, SchoolClassTimeTableMapper schoolClassTimeTableMapper) {
        this.schoolClassTimeTableRepository = schoolClassTimeTableRepository;
        this.schoolClassTimeTableMapper = schoolClassTimeTableMapper;
    }

    @Override
    public Optional<SchoolClassTimeTable> findById(String timeTableId) {
        final Optional<SchoolClassTimeTableDbDTO> optTimetable = schoolClassTimeTableRepository.findById(timeTableId);
        return optTimetable.map(schoolClassTimeTableMapper::dbVoToDomain);
    }

    @Override
    public SchoolClassTimeTable saveTimeTable(SchoolClassTimeTable schoolClassTimeTable) {
        final SchoolClassTimeTableDbDTO schoolClassTimeTableDbDTO = schoolClassTimeTableMapper.domainToDbVo(schoolClassTimeTable);
        final SchoolClassTimeTableDbDTO savedTimeTable = schoolClassTimeTableRepository.save(schoolClassTimeTableDbDTO);
        return schoolClassTimeTableMapper.dbVoToDomain(savedTimeTable);
    }
}
