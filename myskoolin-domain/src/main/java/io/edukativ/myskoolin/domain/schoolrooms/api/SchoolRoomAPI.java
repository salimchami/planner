package io.edukativ.myskoolin.domain.schoolrooms.api;

import io.edukativ.myskoolin.domain.entity.SchoolRoom;
import io.edukativ.myskoolin.domain.entity.User;

import java.util.Optional;

public interface SchoolRoomAPI {

    Optional<SchoolRoom> createOrUpdateSchoolRoom(SchoolRoom schoolRoom, User currentUser);

    void deleteSchoolRoom(String id);
}
