package io.edukativ.myskoolin.domain.schoolrooms;

import io.edukativ.myskoolin.domain.commons.entity.User;

import java.util.Optional;

public interface SchoolRoomAPI {

    Optional<SchoolRoom> createOrUpdateSchoolRoom(SchoolRoom schoolRoom, User currentUser);

    void deleteSchoolRoom(String id);
}
