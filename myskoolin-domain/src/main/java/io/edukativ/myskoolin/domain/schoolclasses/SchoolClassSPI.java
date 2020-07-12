package io.edukativ.myskoolin.domain.schoolclasses;

import java.util.Optional;

public interface SchoolClassSPI {

    Optional<SchoolClass> findById(String schoolClassId);

}
