package io.edukativ.myskoolin.domain.teachers;

public interface TeacherMailingSPI {

    void sendToCreatedTeacher(Teacher createdTeacher, String baseUrl);
}
