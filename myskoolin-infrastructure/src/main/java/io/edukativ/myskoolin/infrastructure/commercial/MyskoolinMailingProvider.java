package io.edukativ.myskoolin.infrastructure.commercial;

import io.edukativ.myskoolin.domain.commons.mailing.MyskoolinMailingSPI;
import io.edukativ.myskoolin.domain.teachers.Teacher;

public class MyskoolinMailingProvider implements MyskoolinMailingSPI {

    @Override
    public void notifyCreatedTeacher(Teacher createdTeacher) {

    }
}
