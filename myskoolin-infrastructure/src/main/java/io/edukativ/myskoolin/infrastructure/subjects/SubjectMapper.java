package io.edukativ.myskoolin.infrastructure.subjects;

import io.edukativ.myskoolin.domain.subjects.Subject;
import io.edukativ.myskoolin.domain.schoolrooms.EnumSchoolRoomsTypes;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumSchoolRoomsTypesDb;
import io.edukativ.myskoolin.infrastructure.common.mapper.ObjectIdMapper;
import io.edukativ.myskoolin.infrastructure.grades.GradeMapper;
import io.edukativ.myskoolin.infrastructure.grades.GradeSerieMapper;
import io.edukativ.myskoolin.infrastructure.timetabling.PreferredPartsOfDaysMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        ObjectIdMapper.class,
        GradeMapper.class,
        GradeSerieMapper.class,
        PreferredPartsOfDaysMapper.class,

})
public interface SubjectMapper {

    List<SubjectDTO> dbDtosToDtos(List<SubjectDbDTO> subjects);

    Subject dtoToDomain(SubjectDTO subject);

    SubjectDTO domainToDto(Subject savedSubject);

    SubjectDbDTO domainToDbDto(Subject subject);

    Subject dbDtoToDomain(SubjectDbDTO savedSubject);

    default EnumSchoolRoomsTypes dbToVo(EnumSchoolRoomsTypesDb type) {
        return EnumSchoolRoomsTypes.valueOf(type.name());
    }

    List<SubjectDTO> domainsToDtos(List<Subject> subjects);

    List<Subject> dbDtosToDomains(List<SubjectDbDTO> subjectDbDTOS);

    List<Subject> dtosToDomains(List<SubjectDTO> subjectDbDTOS);

    SubjectDTO dbDtoToDto(SubjectDbDTO subject);

    List<SubjectDbDTO> domainsToDbDtos(List<Subject> subjects);
}
