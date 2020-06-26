package io.edukativ.myskoolin.domain.teachers;

import io.edukativ.myskoolin.domain.vo.Teacher;

public interface TeacherAPI {

    Teacher create(Teacher teacher, String encryptedRandomPassword, String baseUrl);
}
