package io.edukativ.myskoolin.domain.subjects;

import io.edukativ.myskoolin.domain.entity.Subject;
import io.edukativ.myskoolin.domain.entity.User;

public interface SubjectAPI {

    Subject createSubject(Subject subject, User user);

    Subject updateSubject(Subject subject, User user);
}
