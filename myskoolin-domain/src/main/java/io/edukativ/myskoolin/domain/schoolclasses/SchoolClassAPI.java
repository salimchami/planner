package io.edukativ.myskoolin.domain.schoolclasses;

import io.edukativ.myskoolin.domain.commons.entity.User;

import java.util.List;

public interface SchoolClassAPI {

    List<SchoolClass> findAll(User currentUser);
}
