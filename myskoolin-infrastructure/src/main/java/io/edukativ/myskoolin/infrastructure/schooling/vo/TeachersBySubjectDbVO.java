package io.edukativ.myskoolin.infrastructure.schooling.vo;

import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import io.edukativ.myskoolin.infrastructure.schooling.dto.SubjectDbDTO;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class TeachersBySubjectDbVO {

    public static final String MONGO_FIELD_TEACHER_USERS = "teachers_users";

    private SubjectDbDTO subject;
    @Field(MONGO_FIELD_TEACHER_USERS)
    private List<UserDbDTO> teacherUsers;

    public TeachersBySubjectDbVO() {
    }

    public TeachersBySubjectDbVO(SubjectDbDTO subject, List<UserDbDTO> teacherUsers) {
        this.subject = subject;
        this.teacherUsers = teacherUsers;
    }

    public SubjectDbDTO getSubject() {
        return subject;
    }

    public void setSubject(SubjectDbDTO subject) {
        this.subject = subject;
    }

    public List<UserDbDTO> getTeacherUsers() {
        return teacherUsers;
    }

    public void setTeacherUsers(List<UserDbDTO> teacherUsers) {
        this.teacherUsers = teacherUsers;
    }
}
