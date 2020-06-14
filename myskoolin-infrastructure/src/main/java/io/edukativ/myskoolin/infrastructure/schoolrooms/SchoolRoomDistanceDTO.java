package io.edukativ.myskoolin.infrastructure.schoolrooms;

import io.edukativ.myskoolin.infrastructure.common.dto.DistanceDTO;

import java.io.Serializable;

public class SchoolRoomDistanceDTO implements Serializable {

    private String schoolRoomName;
    private DistanceDTO distance;

    public String getSchoolRoomName() {
        return schoolRoomName;
    }

    public void setSchoolRoomName(String schoolRoomName) {
        this.schoolRoomName = schoolRoomName;
    }

    public DistanceDTO getDistance() {
        return distance;
    }

    public void setDistance(DistanceDTO distance) {
        this.distance = distance;
    }
}
