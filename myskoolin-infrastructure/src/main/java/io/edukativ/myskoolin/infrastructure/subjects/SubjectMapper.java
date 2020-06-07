package io.edukativ.myskoolin.infrastructure.subjects;

import io.edukativ.myskoolin.infrastructure.common.mapper.ObjectIdMapper;
import io.edukativ.myskoolin.infrastructure.grades.GradeMapper;
import io.edukativ.myskoolin.infrastructure.grades.GradeSerieMapper;
import io.edukativ.myskoolin.infrastructure.schooling.PreferredPartsOfDaysMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        ObjectIdMapper.class,
        GradeMapper.class,
        GradeSerieMapper.class,
        PreferredPartsOfDaysMapper.class
})
public interface SubjectMapper {


    List<SubjectDTO> dbDtosToDtos(List<SubjectDbDTO> subjects);
}
