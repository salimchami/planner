package io.edukativ.myskoolin.planner.entities;

import io.edukativ.myskoolin.planner.entities.SchoolClass;

import java.util.Optional;

public interface SchoolClassSPI {
    Optional<SchoolClass> findById(String schoolClassId);
}
