package io.edukativ.myskoolin.infrastructure.teachers;

import io.edukativ.myskoolin.infrastructure.subjects.SubjectDbDTO;

import java.io.Serializable;
import java.util.List;

public class TeachersBySubjectVO implements Serializable {

    private SubjectDbDTO subject;
    private List<TeacherDbDTO> teacherUsers;

    public SubjectDbDTO getSubject() {
        return subject;
    }

    public void setSubject(SubjectDbDTO subject) {
        this.subject = subject;
    }

    public List<TeacherDbDTO> getTeacherUsers() {
        return teacherUsers;
    }

    public void setTeacherUsers(List<TeacherDbDTO> teacherUsers) {
        this.teacherUsers = teacherUsers;
    }
}
