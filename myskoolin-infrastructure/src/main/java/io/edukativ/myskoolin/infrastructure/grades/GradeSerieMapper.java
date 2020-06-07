package io.edukativ.myskoolin.infrastructure.grades;

import io.edukativ.myskoolin.domain.vo.GradeSerie;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GradeSerieMapper {

    List<GradeSerieVO> dbDtosToDtos(List<GradeSerieDbVO> gradeSeries);

    GradeSerieVO dbDtoToDto(GradeSerieDbVO gradeSerie);

    List<GradeSerieVO> modelToDtos(List<GradeSerie> gradesSeries);
}
