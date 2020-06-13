package io.edukativ.myskoolin.domain.subjects;

import io.edukativ.myskoolin.domain.entity.Subject;
import io.edukativ.myskoolin.domain.entity.User;

import java.util.List;

public interface SubjectAPI {

    Subject createSubject(Subject subject, User user);

    Subject updateSubject(Subject subject, User user);

    List<Subject> searchByName(String name, User user);

    List<Subject> subjectsByGradesId(List<String> gradesId, User user);
}
