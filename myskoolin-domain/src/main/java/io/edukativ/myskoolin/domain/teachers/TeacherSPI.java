package io.edukativ.myskoolin.domain.teachers;

import io.edukativ.myskoolin.domain.vo.Teacher;

public interface TeacherSPI {

    Teacher create(Teacher teacher);
}
