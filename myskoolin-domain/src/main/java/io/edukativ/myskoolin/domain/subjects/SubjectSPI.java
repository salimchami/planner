package io.edukativ.myskoolin.domain.subjects;

import io.edukativ.myskoolin.domain.entity.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectSPI {
    Integer countByGrade(String gradeId);

    Subject createSubject(Subject subject);

    Subject updateSubject(Subject subject);

    List<Subject> searchSubjects(String clientId, String name);

    List<Subject> searchSubjects(String name);

    List<Subject> findByGradeContaining(List<String> gradesId);

    List<Subject> findByGradeInAndClientId(List<String> gradesId, String clientId);

    Optional<Subject> findById(String id);

    void save(Subject subject);
}
