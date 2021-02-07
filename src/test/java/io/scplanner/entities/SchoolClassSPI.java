package io.scplanner.entities;

import java.util.Optional;

public interface SchoolClassSPI {
    Optional<SchoolClass> findById(String schoolClassId);
}
