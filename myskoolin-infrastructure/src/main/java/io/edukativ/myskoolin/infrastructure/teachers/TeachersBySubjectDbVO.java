package io.edukativ.myskoolin.infrastructure.teachers;

import io.edukativ.myskoolin.infrastructure.subjects.SubjectDbDTO;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

public class TeachersBySubjectDbVO implements Serializable {

    public static final String MONGO_FIELD_TEACHER_USERS = "teachers_users";
    private static final String MONGO_FIELD_SUBJECTS = "subjects";

    @DBRef
    @Field(MONGO_FIELD_SUBJECTS)
    private SubjectDbDTO subject;

    @DBRef
    @Field(MONGO_FIELD_TEACHER_USERS)
    private List<TeacherDbDTO> teachers;

    public TeachersBySubjectDbVO() {
    }

    public TeachersBySubjectDbVO(SubjectDbDTO subject, List<TeacherDbDTO> teachers) {
        this.subject = subject;
        this.teachers = teachers;
    }

    public SubjectDbDTO getSubject() {
        return subject;
    }

    public void setSubject(SubjectDbDTO subject) {
        this.subject = subject;
    }

    public List<TeacherDbDTO> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<TeacherDbDTO> teachers) {
        this.teachers = teachers;
    }
}
