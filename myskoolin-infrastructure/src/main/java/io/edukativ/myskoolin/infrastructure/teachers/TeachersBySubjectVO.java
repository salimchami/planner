package io.edukativ.myskoolin.infrastructure.teachers;

import io.edukativ.myskoolin.infrastructure.subjects.SubjectDTO;

import java.io.Serializable;
import java.util.List;

public class TeachersBySubjectVO implements Serializable {

    private SubjectDTO subject;
    private List<TeacherDTO> teachers;

    public TeachersBySubjectVO(SubjectDTO subject, List<TeacherDTO> teachers) {
        this.subject = subject;
        this.teachers = teachers;
    }

    public SubjectDTO getSubject() {
        return subject;
    }

    public void setSubject(SubjectDTO subject) {
        this.subject = subject;
    }

    public List<TeacherDTO> getTeachers() {
        return teachers;
    }

    public void setTeacherUsers(List<TeacherDTO> teachers) {
        this.teachers = teachers;
    }
}
