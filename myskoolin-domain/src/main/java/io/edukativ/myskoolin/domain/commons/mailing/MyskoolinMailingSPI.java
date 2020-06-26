package io.edukativ.myskoolin.domain.commons.mailing;

import io.edukativ.myskoolin.domain.vo.Teacher;

public interface MyskoolinMailingSPI {

    void notifyCreatedTeacher(Teacher createdTeacher);
}
