package io.edukativ.myskoolin.infrastructure.grades;

import io.edukativ.myskoolin.domain.grades.Grade;
import io.edukativ.myskoolin.infrastructure.common.mapper.ObjectIdMapper;
import io.edukativ.myskoolin.infrastructure.timetabling.TimeTableOptionsMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        ObjectIdMapper.class,
        GradeSerieMapper.class,
        TimeTableOptionsMapper.class
})
public interface GradeMapper {

    List<GradeDTO> dbDtosToDtos(List<GradeDbDTO> grades);

    @Mapping(target = "nbSubjects", ignore = true)
    @Mapping(target = "subjects", ignore = true)
    GradeDTO dbDtoToDto(GradeDbDTO grade);

    List<GradeDTO> domainsToDtos(List<Grade> grades);

    GradeDbDTO domainToDbDto(Grade grade);

    List<Grade> dbDtosToDomains(List<GradeDbDTO> allNotDeleted);

    List<GradeDbDTO> dtosToDbDtos(List<GradeDTO> grades);

    GradeDTO domainToDto(Grade grade);

    List<GradeDbDTO> domainsToDbDtos(List<Grade> grades);

    List<Grade> dtosToDomains(List<GradeDTO> grades);
}
