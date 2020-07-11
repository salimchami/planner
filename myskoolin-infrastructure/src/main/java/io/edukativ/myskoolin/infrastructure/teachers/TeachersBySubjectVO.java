package io.edukativ.myskoolin.infrastructure.teachers;

import io.edukativ.myskoolin.infrastructure.subjects.SubjectDbDTO;

import java.io.Serializable;
import java.util.List;

public class TeachersBySubjectVO implements Serializable {

    private SubjectDbDTO subject;
    private List<TeacherDbDTO> teachers;

    public SubjectDbDTO getSubject() {
        return subject;
    }

    public void setSubject(SubjectDbDTO subject) {
        this.subject = subject;
    }

    public List<TeacherDbDTO> getTeachers() {
        return teachers;
    }

    public void setTeacherUsers(List<TeacherDbDTO> teachers) {
        this.teachers = teachers;
    }
}
