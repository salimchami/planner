package io.edukativ.myskoolin.domain.schoolclasses;

import java.util.List;

public interface SchoolClassSPI {

    List<SchoolClass> findAllByClientId(String clientId);
}
