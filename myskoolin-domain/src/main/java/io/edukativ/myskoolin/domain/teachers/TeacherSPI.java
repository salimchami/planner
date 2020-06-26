package io.edukativ.myskoolin.domain.teachers;

import io.edukativ.myskoolin.domain.vo.Teacher;

import java.util.List;

public interface TeacherSPI {

    Teacher create(Teacher teacher);

    List<Teacher> searchTeachers(String name);

    List<Teacher> searchTeachers(String clientId, String name);
}
