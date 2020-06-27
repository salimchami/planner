package io.edukativ.myskoolin.domain.grades;

import io.edukativ.myskoolin.domain.commons.AuthoritiesConstants;
import io.edukativ.myskoolin.domain.commons.entity.User;
import io.edukativ.myskoolin.domain.subjects.SubjectSPI;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class GradeService implements GradeAPI {

    private final GradeSPI gradeSPI;
    private final SubjectSPI subjectSPI;

    public GradeService(GradeSPI gradeSPI, SubjectSPI subjectSPI) {
        this.gradeSPI = gradeSPI;
        this.subjectSPI = subjectSPI;
    }

    @Override
    public List<Grade> findGrades(User currentUser) {
        final List<Grade> grades = gradeSPI.findNotDeletedByClientId(currentUser.getClientId());
        grades.forEach(grade -> {
            Integer nbSubjects = subjectSPI.countByGrade(grade.getId());
            grade.setNbSubjects(nbSubjects);
        });
        return grades;
    }

    @Override
    public List<GradeSerie> findAllGradesSeries(User currentUser) {
        List<Grade> grades;
        if (currentUser.hasAuthority(AuthoritiesConstants.SCHOOLME_ADMIN)) {
            grades = gradeSPI.findAll();
        } else {
            grades = gradeSPI.findNotDeletedByClientId(currentUser.getClientId());
        }
        return grades
                .stream()
                .map(Grade::getSeries)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
    }
}
