package io.edukativ.myskoolin.domain.schoolrooms.core;

import io.edukativ.myskoolin.domain.entity.SchoolRoom;
import io.edukativ.myskoolin.domain.entity.User;
import io.edukativ.myskoolin.domain.schoolrooms.api.SchoolRoomAPI;
import io.edukativ.myskoolin.domain.schoolrooms.spi.SchoolRoomSPI;
import io.edukativ.myskoolin.domain.vo.SchoolRoomDistance;

import java.util.Optional;

public class SchoolRoomService implements SchoolRoomAPI {

    private final SchoolRoomSPI schoolRoomSPI;

    public SchoolRoomService(SchoolRoomSPI schoolRoomSPI) {
        this.schoolRoomSPI = schoolRoomSPI;
    }

    @Override
    public Optional<SchoolRoom> createOrUpdateSchoolRoom(SchoolRoom schoolRoom, User currentUser) {
        if (schoolRoom.getId() != null) {
            return updateSchoolRoom(schoolRoom, currentUser);
        } else {

            schoolRoom.setDeleted(false);
            return Optional.of(schoolRoomSPI.persist(schoolRoom));
        }
    }

    private Optional<SchoolRoom> updateSchoolRoom(SchoolRoom schoolRoom, User currentUser) {
        final Optional<SchoolRoom> optSchoolRoom = schoolRoomSPI.findById(schoolRoom.getId());
        return optSchoolRoom.map(schoolRoomToUpdate -> {
            schoolRoomToUpdate.update(schoolRoom);
            SchoolRoom savedSchoolRoom = schoolRoomSPI.persist(schoolRoomToUpdate);
            updateSchoolRoomsDistances(savedSchoolRoom, currentUser);
            return savedSchoolRoom;
        });
    }

    private void updateSchoolRoomsDistances(SchoolRoom refRoom, User currentUser) {
        for (SchoolRoomDistance refRoomDistance : refRoom.getDistances()) {
            Optional<SchoolRoom> optSubSchoolRoom = schoolRoomSPI.findByName(refRoomDistance.getSchoolRoomName(), false, currentUser.getClientId());
            if (optSubSchoolRoom.isPresent()) {
                SchoolRoom subSchoolRoom = optSubSchoolRoom.get();
                boolean subDistancesExists = subSchoolRoom.getDistances().stream()
                        .map(SchoolRoomDistance::getSchoolRoomName)
                        .anyMatch(refRoom.getName()::equals);
                createOrUpdateSubDistances(refRoom, refRoomDistance, subSchoolRoom, subDistancesExists);
                schoolRoomSPI.persist(subSchoolRoom);
            }
        }
    }

    private void createOrUpdateSubDistances(SchoolRoom refRoom, SchoolRoomDistance refRoomDistance, SchoolRoom subSchoolRoom, boolean subDistancesExists) {
        if (subDistancesExists) {
            //update sub distances
            subSchoolRoom.getDistances().stream()
                    .filter(schoolRoomToUpdateDistance -> schoolRoomToUpdateDistance.getSchoolRoomName().equals(refRoom.getName()))
                    .forEach(schoolRoomToUpdateDistance -> schoolRoomToUpdateDistance.getDistance().setValue(refRoomDistance.getDistance().getValue()));
        } else {
            //create sub distances
            SchoolRoomDistance subDistance = new SchoolRoomDistance();
            subDistance.setSchoolRoomName(refRoom.getName());
            subDistance.setDistance(refRoomDistance.getDistance());
            subSchoolRoom.getDistances().add(subDistance);
        }
    }

    @Override
    public void deleteSchoolRoom(String id) {
        Optional<SchoolRoom> optSchoolRoom = schoolRoomSPI.findById(id);
        optSchoolRoom.ifPresent(schoolRoom -> {
            schoolRoom.setDeleted(true);
            schoolRoomSPI.persist(schoolRoom);
        });
    }
}
