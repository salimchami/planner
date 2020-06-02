package io.edukativ.myskoolin.infrastructure.schoolrooms;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.edukativ.myskoolin.infrastructure.common.vo.DistanceDbVO;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

public class SchoolRoomDistanceDbVO implements Serializable {

    public static final String MONGO_FIELD_SCHOOL_ROOM_NAME = "school_room_name";
    public static final String MONGO_FIELD_DISTANCE = "distance";
    @Field(MONGO_FIELD_SCHOOL_ROOM_NAME)
    private String schoolRoomName;

    @Field(MONGO_FIELD_DISTANCE)
    private DistanceDbVO distance;

    public String getSchoolRoomName() {
        return schoolRoomName;
    }

    public void setSchoolRoomName(String schoolRoomName) {
        this.schoolRoomName = schoolRoomName;
    }

    public DistanceDbVO getDistance() {
        return distance;
    }

    public void setDistance(DistanceDbVO distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolRoomDistanceDbVO that = (SchoolRoomDistanceDbVO) o;
        return schoolRoomName.equals(that.schoolRoomName) &&
                distance.equals(that.distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schoolRoomName, distance);
    }

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "SchoolRoomDistance{" +
                    "schoolRoomName='" + schoolRoomName + '\'' +
                    ", distance=" + distance +
                    '}';
        }
    }
}
