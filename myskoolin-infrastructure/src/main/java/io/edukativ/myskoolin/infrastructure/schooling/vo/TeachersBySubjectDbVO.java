package io.edukativ.myskoolin.infrastructure.schooling.vo;

import io.edukativ.myskoolin.infrastructure.subjects.SubjectDbDTO;
import io.edukativ.myskoolin.infrastructure.teachers.TeacherDbDTO;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class TeachersBySubjectDbVO {

    public static final String MONGO_FIELD_TEACHER_USERS = "teachers_users";

    private SubjectDbDTO subject;
    @Field(MONGO_FIELD_TEACHER_USERS)
    private List<TeacherDbDTO> teacherUsers;

    public TeachersBySubjectDbVO() {
    }

    public TeachersBySubjectDbVO(SubjectDbDTO subject, List<TeacherDbDTO> teacherUsers) {
        this.subject = subject;
        this.teacherUsers = teacherUsers;
    }

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
