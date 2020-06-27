package io.edukativ.myskoolin.domain.grades;

import java.util.List;

public interface GradeSPI {

    List<Grade> findNotDeletedByClientId(String clientId);

    List<Grade> findAll();
}
