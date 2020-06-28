package io.edukativ.myskoolin.domain.schoolrooms;

import io.edukativ.myskoolin.domain.subjects.Subject;
import io.edukativ.myskoolin.domain.timetabling.Lesson;
import org.optaplanner.core.api.domain.lookup.PlanningId;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A SchoolRoom.
 */
public class SchoolRoom {

    @PlanningId
    private String id;
    private String clientId;
    private List<SchoolRoomDistance> distances;
    private List<Lesson> timetable;
    private String name;
    private boolean closed;
    private Integer seats;
    private EnumSchoolRoomsTypes type;
    private BigDecimal surface;
    private String comment;
    private String longitude;
    private String latitude;
    private boolean deleted;

    public SchoolRoom() {
    }

    public SchoolRoom(String id, String clientId, List<SchoolRoomDistance> distances, List<Lesson> timetable,
                      String name, Boolean closed, Integer seats, EnumSchoolRoomsTypes type, BigDecimal surface,
                      String comment, String longitude, String latitude, Boolean deleted) {
        this.id = id;
        this.clientId = clientId;
        this.distances = distances;
        this.timetable = timetable;
        this.name = name;
        this.closed = closed;
        this.seats = seats;
        this.type = type;
        this.surface = surface;
        this.comment = comment;
        this.longitude = longitude;
        this.latitude = latitude;
        this.deleted = deleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public List<SchoolRoomDistance> getDistances() {
        if (this.distances == null) {
            this.distances = new ArrayList<>();
        }
        return distances;
    }

    public void setDistances(List<SchoolRoomDistance> distances) {
        this.distances = distances;
    }

    public List<Lesson> getTimetable() {
        if (this.timetable == null) {
            this.timetable = new ArrayList<>();
        }
        return timetable;
    }

    public void setTimetable(List<Lesson> timetable) {
        this.timetable = timetable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public EnumSchoolRoomsTypes getType() {
        return type;
    }

    public void setType(EnumSchoolRoomsTypes type) {
        this.type = type;
    }

    public BigDecimal getSurface() {
        return surface;
    }

    public void setSurface(BigDecimal surface) {
        this.surface = surface;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SchoolRoom schoolRoom = (SchoolRoom) o;
        return !(schoolRoom.id == null || id == null) && Objects.equals(id, schoolRoom.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    // ################################################################################################
    // ################################################################################################
    // ################################################################################################
    // ################################################################################################

//    public static Optional<SchoolRoom> searchForSchoolRoom(List<SchoolRoom> possibleSchoolRoomsForSubject, TimeSlot timeSlot) {
//        Optional<SchoolRoom> optSchoolRoom = Optional.empty();
//        for (SchoolRoom schoolRoom : possibleSchoolRoomsForSubject) {
//            if (schoolRoom.getTimetable().isEmpty() || !timeSlot.isOverlapping(timeSlot.getDay(), schoolRoom.getTimetable())) {
//                optSchoolRoom = Optional.of(schoolRoom);
//                break;
//            }
//        }
//        return optSchoolRoom;
//    }

    public static List<SchoolRoom> schoolRoomsByType(List<SchoolRoom> schoolRooms, Subject subject) {
        return schoolRooms.stream().filter(schoolRoom -> subject.getSchoolRoomsTypes().contains(schoolRoom.getType())).collect(Collectors.toList());
    }


    public void update(SchoolRoom schoolRoom) {
        this.distances = schoolRoom.getDistances();
        this.timetable = schoolRoom.getTimetable();
        this.name = schoolRoom.getName();
        this.closed = schoolRoom.getClosed();
        this.seats = schoolRoom.getSeats();
        this.type = schoolRoom.getType();
        this.surface = schoolRoom.getSurface();
        this.comment = schoolRoom.getComment();
        this.longitude = schoolRoom.getLongitude();
        this.latitude = schoolRoom.getLatitude();
    }
}
