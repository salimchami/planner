package io.edukativ.myskoolin.infrastructure.commercial;

import io.edukativ.myskoolin.domain.commons.mailing.MyskoolinMailingSPI;
import io.edukativ.myskoolin.domain.teachers.Teacher;
import org.springframework.stereotype.Component;

@Component
public class MyskoolinMailingProvider implements MyskoolinMailingSPI {

    @Override
    public void notifyCreatedTeacher(Teacher createdTeacher) {

    }
}
