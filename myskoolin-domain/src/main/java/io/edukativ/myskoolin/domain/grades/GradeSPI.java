package io.edukativ.myskoolin.domain.grades;

import io.edukativ.myskoolin.domain.entity.Grade;

import java.util.List;

public interface GradeSPI {

    List<Grade> findNotDeletedByClientId(String clientId);

    List<Grade> findAll();

    List<Grade> findAllNotDeletedByClientId(String clientId);
}
