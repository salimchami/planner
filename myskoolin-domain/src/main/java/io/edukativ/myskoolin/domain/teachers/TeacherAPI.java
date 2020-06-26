package io.edukativ.myskoolin.domain.teachers;

import io.edukativ.myskoolin.domain.entity.User;
import io.edukativ.myskoolin.domain.vo.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherAPI {

    Teacher create(Teacher teacher, String encryptedRandomPassword, User currentUser, String baseUrl);

    List<Teacher> searchByName(String name, User currentUser);

    Optional<Teacher> update(Teacher teacher, User currentUser, String baseUrl);
}
