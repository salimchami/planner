package io.edukativ.myskoolin.infrastructure.teachers;

import io.edukativ.myskoolin.domain.teachers.TeacherMailingSPI;
import io.edukativ.myskoolin.domain.vo.Teacher;
import org.springframework.stereotype.Component;

@Component
public class TeacherMailingProvider implements TeacherMailingSPI {

    @Override
    public void sendToCreatedTeacher(Teacher createdTeacher, String baseUrl) {

    }
}
