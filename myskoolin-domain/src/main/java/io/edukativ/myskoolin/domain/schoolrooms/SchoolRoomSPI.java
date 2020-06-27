package io.edukativ.myskoolin.domain.schoolrooms;

import java.util.Optional;

public interface SchoolRoomSPI {

    Optional<SchoolRoom> findById(String id);

    SchoolRoom persist(SchoolRoom schoolRoom);

    Optional<SchoolRoom> findByName(String schoolRoomName, boolean deleted, String clientId);
}
