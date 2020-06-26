package io.edukativ.myskoolin.domain.teachers;

import io.edukativ.myskoolin.domain.vo.Teacher;

public interface TeacherMailingSPI {

    void sendToCreatedTeacher(Teacher createdTeacher, String baseUrl);
}
