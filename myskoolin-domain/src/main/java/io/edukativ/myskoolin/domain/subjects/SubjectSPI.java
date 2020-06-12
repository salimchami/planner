package io.edukativ.myskoolin.domain.subjects;

import io.edukativ.myskoolin.domain.entity.Subject;

public interface SubjectSPI {
    Integer countByGrade(String gradeId);

    Subject createSubject(Subject subject);

    Subject updateSubject(Subject subject);
}
