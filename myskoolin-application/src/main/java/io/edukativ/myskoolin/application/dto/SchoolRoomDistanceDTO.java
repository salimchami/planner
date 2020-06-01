package io.edukativ.myskoolin.application.dto;

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
