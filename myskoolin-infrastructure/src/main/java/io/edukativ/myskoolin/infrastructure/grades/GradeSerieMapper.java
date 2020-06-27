package io.edukativ.myskoolin.infrastructure.grades;

import io.edukativ.myskoolin.domain.grades.GradeSerie;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface GradeSerieMapper {

    List<GradeSerieVO> dbDtosToDtos(List<GradeSerieDbVO> gradeSeries);

    GradeSerieVO map(GradeSerieDbVO gradeSerie);

    List<GradeSerieVO> modelToDtos(List<GradeSerie> gradesSeries);
}
