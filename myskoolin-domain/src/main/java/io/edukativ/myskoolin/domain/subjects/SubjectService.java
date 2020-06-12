package io.edukativ.myskoolin.domain.subjects;

import io.edukativ.myskoolin.domain.entity.Grade;
import io.edukativ.myskoolin.domain.entity.Subject;
import io.edukativ.myskoolin.domain.entity.User;
import io.edukativ.myskoolin.domain.grades.GradeSPI;

import java.util.List;

public class SubjectService implements SubjectAPI {

    private final SubjectSPI subjectSPI;
    private final GradeSPI gradeSPI;

    public SubjectService(SubjectSPI subjectSPI, GradeSPI gradeSPI) {
        this.subjectSPI = subjectSPI;
        this.gradeSPI = gradeSPI;
    }

    @Override
    public Subject createSubject(Subject subject, User user) {
        if (subject.getClientId() == null) {
            subject.setClientId(user.getClientId());
        }
        final List<Grade> grades = gradeSPI.findAllNotDeletedByClientId(subject.getClientId());
        subject.findAndSetGradeIfPresent(grades);
        subject.findAndSetGradeSerieFromGrade();
        return subjectSPI.createSubject(subject);
    }

    @Override
    public Subject updateSubject(Subject subject, User user) {
        final List<Grade> grades = gradeSPI.findAllNotDeletedByClientId(subject.getClientId());
        subject.findAndSetGradeIfPresent(grades);
        subject.findAndSetGradeSerieFromGrade();
        return subjectSPI.updateSubject(subject);
    }
}
