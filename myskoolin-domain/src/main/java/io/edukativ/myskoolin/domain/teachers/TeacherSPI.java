package io.edukativ.myskoolin.domain.teachers;

import java.util.List;
import java.util.Optional;

public interface TeacherSPI {

    Teacher create(Teacher teacher);

    List<Teacher> searchTeachers(String name);

    List<Teacher> searchTeachers(String clientId, String name);

    Optional<Teacher> findById(String id);

    List<Teacher> findAllByClientId(String clientId);
}
