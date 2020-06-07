package io.edukativ.myskoolin.domain.grades;

import io.edukativ.myskoolin.domain.entity.Grade;
import io.edukativ.myskoolin.domain.entity.User;
import io.edukativ.myskoolin.domain.vo.GradeSerie;

import java.util.List;

public interface GradeAPI {

    List<Grade> findGrades(User currentUser);

    List<GradeSerie> findAllGradesSeries(User currentUser);
}
