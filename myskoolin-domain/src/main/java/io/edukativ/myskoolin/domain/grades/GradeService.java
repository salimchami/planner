package io.edukativ.myskoolin.domain.grades;

import io.edukativ.myskoolin.domain.entity.Grade;
import io.edukativ.myskoolin.domain.entity.User;
import io.edukativ.myskoolin.domain.subjects.SubjectSPI;

import java.util.List;

public class GradeService implements GradeAPI {

    private final GradeSPI gradeSPI;
    private final SubjectSPI subjectSPI;

    public GradeService(GradeSPI gradeSPI, SubjectSPI subjectSPI) {
        this.gradeSPI = gradeSPI;
        this.subjectSPI = subjectSPI;
    }

    @Override
    public List<Grade> findGrades(User user) {
        final List<Grade> grades = gradeSPI.findNotDeletedByClientId(user.getClientId());
        grades.forEach(grade -> {
            Integer nbSubjects = subjectSPI.countByGrade(grade.getId());
            grade.setNbSubjects(nbSubjects);
        });
        return grades;
    }
}
