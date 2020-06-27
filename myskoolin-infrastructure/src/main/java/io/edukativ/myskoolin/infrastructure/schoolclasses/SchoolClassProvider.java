package io.edukativ.myskoolin.infrastructure.schoolclasses;

import io.edukativ.myskoolin.domain.schoolclasses.SchoolClass;
import io.edukativ.myskoolin.domain.schoolclasses.SchoolClassSPI;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SchoolClassProvider implements SchoolClassSPI {


    private final SchoolClassRepository schoolClassRepository;
    private final SchoolClassMapper schoolClassMapper;

    public SchoolClassProvider(SchoolClassRepository schoolClassRepository, SchoolClassMapper schoolClassMapper) {
        this.schoolClassRepository = schoolClassRepository;
        this.schoolClassMapper = schoolClassMapper;
    }

    @Override
    public List<SchoolClass> findAllByClientId(String clientId) {
        final List<SchoolClassDbDTO> schoolClasses = schoolClassRepository.findAllNotDeletedSchoolClasses(new ObjectId(clientId));
        return schoolClassMapper.dbDtosToDomains(schoolClasses);
    }
}
