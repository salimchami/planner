package io.edukativ.myskoolin.infrastructure.schoolrooms;

import io.edukativ.myskoolin.infrastructure.common.enums.EnumSchoolRoomsTypesDb;
import io.edukativ.myskoolin.infrastructure.timetabling.LessonVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * A SchoolRoom.
 */
public class SchoolRoomDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String ENTITY_NAME = "schoolRoom";

    private String id;

    private String clientId;

    private List<SchoolRoomDistanceDTO> distances;

    private List<LessonVO> timetable;

    private String name;

    private boolean closed;

    private Integer seats;

    private EnumSchoolRoomsTypesDb type;

    private BigDecimal surface;

    private String comment;

    private String longitude;

    private String latitude;

    private boolean deleted;

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public List<SchoolRoomDistanceDTO> getDistances() {
        return distances;
    }

    public void setDistances(List<SchoolRoomDistanceDTO> distances) {
        this.distances = distances;
    }

    public List<LessonVO> getTimetable() {
        return timetable;
    }

    public void setTimetable(List<LessonVO> timetable) {
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

    public EnumSchoolRoomsTypesDb getType() {
        return type;
    }

    public void setType(EnumSchoolRoomsTypesDb type) {
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
}
