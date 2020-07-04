package io.edukativ.myskoolin.infrastructure.schoolclasses;

import io.edukativ.myskoolin.domain.schoolclasses.SchoolClass;
import io.edukativ.myskoolin.domain.schoolclasses.SchoolClassSPI;
import io.edukativ.myskoolin.domain.timetabling.SchoolClassTimeTable;
import io.edukativ.myskoolin.infrastructure.timetabling.SchoolClassTimeTableMapper;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SchoolClassProvider implements SchoolClassSPI {


    private final SchoolClassRepository schoolClassRepository;
    private final SchoolClassMapper schoolClassMapper;
    private final SchoolClassTimeTableMapper schoolClassTimeTableMapper;

    public SchoolClassProvider(SchoolClassRepository schoolClassRepository, SchoolClassMapper schoolClassMapper, SchoolClassTimeTableMapper schoolClassTimeTableMapper) {
        this.schoolClassRepository = schoolClassRepository;
        this.schoolClassMapper = schoolClassMapper;
        this.schoolClassTimeTableMapper = schoolClassTimeTableMapper;
    }

    @Override
    public Optional<SchoolClass> findById(String schoolClassId) {
        final Optional<SchoolClassDbDTO> optSchoolClass = schoolClassRepository.findById(new ObjectId(schoolClassId));
        return optSchoolClass.map(schoolClassMapper::dbDtoToDomain);
    }

    @Override
    public Optional<SchoolClass> saveTimeTable(String schoolClassId, SchoolClassTimeTable schoolClassTimeTable) {
        final Optional<SchoolClassDbDTO> optSchoolClass = schoolClassRepository.findById(new ObjectId(schoolClassId));
        return optSchoolClass.map(schoolClassDbDTO -> {
            schoolClassDbDTO.setTimetable(schoolClassTimeTableMapper.domainToDbVo(schoolClassTimeTable));
            schoolClassRepository.save(schoolClassDbDTO);
            return schoolClassMapper.dbDtoToDomain(schoolClassDbDTO);
        });
    }
}
