package io.edukativ.myskoolin.domain.teachers;

import io.edukativ.myskoolin.domain.subjects.Subject;

import java.util.List;

public class TeachersBySubject {

    private Subject subject;
    private List<Teacher> teachers;

    public TeachersBySubject() {
    }

    public TeachersBySubject(Subject subject, List<Teacher> teachers) {
        this.subject = subject;
        this.teachers = teachers;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
