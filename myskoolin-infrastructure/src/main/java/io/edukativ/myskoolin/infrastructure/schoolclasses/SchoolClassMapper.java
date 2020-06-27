package io.edukativ.myskoolin.infrastructure.schoolclasses;

import io.edukativ.myskoolin.domain.schoolclasses.SchoolClass;
import io.edukativ.myskoolin.infrastructure.common.mapper.ObjectIdMapper;
import io.edukativ.myskoolin.infrastructure.grades.GradeMapper;
import io.edukativ.myskoolin.infrastructure.grades.GradeSerieMapper;
import io.edukativ.myskoolin.infrastructure.teachers.TeachersBySubjectMapper;
import io.edukativ.myskoolin.infrastructure.timetabling.SchoolClassTimeTableMapper;
import io.edukativ.myskoolin.infrastructure.timetabling.TimeSlotMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        ObjectIdMapper.class,
        GradeMapper.class,
        GradeSerieMapper.class,
        TimeSlotMapper.class,
        TeachersBySubjectMapper.class,
        SchoolClassTimeTableMapper.class,
})
public interface SchoolClassMapper {

    SchoolClassDTO dbDtoToDto(SchoolClassDbDTO param);

    SchoolClass dbDtoToDomain(SchoolClassDbDTO param);

    SchoolClassDbDTO dtoToDbDto(SchoolClassDTO param);

    SchoolClass dtoToDomain(SchoolClassDTO param);

    SchoolClassDbDTO domainToDbDto(SchoolClass param);

    SchoolClassDTO domainToDto(SchoolClass param);

    List<SchoolClassDTO> domainsToDtos(List<SchoolClass> schoolClasses);

    List<SchoolClass> dbDtosToDomains(List<SchoolClassDbDTO> schoolClasses);

    List<SchoolClassDTO> dbDtosToDtos(List<SchoolClassDbDTO> schoolClasses);
}
