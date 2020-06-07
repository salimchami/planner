package io.edukativ.myskoolin.domain.vo;

import java.util.List;

public class TeachersBySubject {

    private String subjectId;
    private List<String> teacherUserId;

    public TeachersBySubject() {
    }

    public TeachersBySubject(String subjectId, List<String> teacherUserId) {
        this.subjectId = subjectId;
        this.teacherUserId = teacherUserId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public List<String> getTeacherUserId() {
        return teacherUserId;
    }

    public void setTeacherUserId(List<String> teacherUserId) {
        this.teacherUserId = teacherUserId;
    }
}
