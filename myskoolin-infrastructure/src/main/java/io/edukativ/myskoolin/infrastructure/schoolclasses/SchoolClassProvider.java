package io.edukativ.myskoolin.infrastructure.schoolclasses;

import io.edukativ.myskoolin.domain.schoolclasses.SchoolClass;
import io.edukativ.myskoolin.domain.schoolclasses.SchoolClassSPI;
import io.edukativ.myskoolin.domain.timetabling.SchoolClassTimeTable;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SchoolClassProvider implements SchoolClassSPI {


    private final SchoolClassRepository schoolClassRepository;
    private final SchoolClassMapper schoolClassMapper;

    public SchoolClassProvider(SchoolClassRepository schoolClassRepository, SchoolClassMapper schoolClassMapper) {
        this.schoolClassRepository = schoolClassRepository;
        this.schoolClassMapper = schoolClassMapper;
    }

    @Override
    public Optional<SchoolClass> findById(String schoolClassId) {
        final Optional<SchoolClassDbDTO> optSchoolClass = schoolClassRepository.findById(new ObjectId(schoolClassId));
        return optSchoolClass.map(schoolClassMapper::dbDtoToDomain);
    }

    @Override
    public void saveTimeTable(SchoolClassTimeTable schoolClassTimeTable) {

    }
}
