package io.edukativ.myskoolin.domain.teachers;

import io.edukativ.myskoolin.domain.entity.User;
import io.edukativ.myskoolin.domain.vo.Teacher;

import java.util.List;

public interface TeacherAPI {

    Teacher create(Teacher teacher, String encryptedRandomPassword, User currentUser, String baseUrl);

    List<Teacher> searchByName(String name, User currentUser);
}
