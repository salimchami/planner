package io.edukativ.schooling.myskoolin.domain.vo;

import java.util.Objects;

public class SchoolRoomDistance {

    private String schoolRoomName;
    private Distance distance;

    public String getSchoolRoomName() {
        return schoolRoomName;
    }

    public void setSchoolRoomName(String schoolRoomName) {
        this.schoolRoomName = schoolRoomName;
    }

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolRoomDistance that = (SchoolRoomDistance) o;
        return schoolRoomName.equals(that.schoolRoomName) &&
                distance.equals(that.distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schoolRoomName, distance);
    }
}
