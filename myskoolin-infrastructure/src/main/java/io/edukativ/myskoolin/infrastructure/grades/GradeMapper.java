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

    GradeDTO dbDtoToDto(GradeDbDTO grade);

    List<GradeDTO> modelsToDtos(List<Grade> grades);

    List<Grade> dbDtosToModels(List<GradeDbDTO> allNotDeleted);
}
