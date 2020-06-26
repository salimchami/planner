package io.edukativ.myskoolin.infrastructure.grades;

import io.edukativ.myskoolin.domain.entity.Grade;
import io.edukativ.myskoolin.infrastructure.common.mapper.ObjectIdMapper;
import io.edukativ.myskoolin.infrastructure.schooling.TimeTableOptionsMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        ObjectIdMapper.class,
        GradeSerieMapper.class,
        TimeTableOptionsMapper.class
})
public interface GradeMapper {

    List<GradeDTO> dbDtosToDtos(List<GradeDbDTO> grades);

    GradeDTO map(GradeDbDTO grade);

    List<GradeDTO> modelsToDtos(List<Grade> grades);

    GradeDbDTO modelToDbDto(Grade grade);

    List<Grade> dbDtosToModels(List<GradeDbDTO> allNotDeleted);

    List<GradeDbDTO> dtosToDbDtos(List<GradeDTO> grades);

    GradeDTO map(Grade grade);

    List<GradeDbDTO> domainsToDbDtos(List<Grade> grades);
}
