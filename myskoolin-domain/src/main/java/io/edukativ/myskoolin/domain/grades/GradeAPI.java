package io.edukativ.myskoolin.domain.grades;

import io.edukativ.myskoolin.domain.commons.entity.User;

import java.util.List;

public interface GradeAPI {

    List<Grade> findGrades(User currentUser);

    List<GradeSerie> findAllGradesSeries(User currentUser);
}
