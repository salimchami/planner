package io.edukativ.schooling.myskoolin.domain.vo;

public class TeachersBySubject {

    private String subjectId;
    private String teacherUserId;

    public TeachersBySubject() {
    }

    public TeachersBySubject(String subjectId, String teacherUserId) {
        this.subjectId = subjectId;
        this.teacherUserId = teacherUserId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getTeacherUserId() {
        return teacherUserId;
    }

    public void setTeacherUserId(String teacherUserId) {
        this.teacherUserId = teacherUserId;
    }
}
