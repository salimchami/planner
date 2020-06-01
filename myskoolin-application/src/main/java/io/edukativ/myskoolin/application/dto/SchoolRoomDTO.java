package io.edukativ.myskoolin.application.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.edukativ.myskoolin.domain.vo.EnumSchoolRoomsTypes;
import io.edukativ.myskoolin.domain.vo.SchoolRoomDistance;
import io.edukativ.myskoolin.domain.vo.TimeSlot;
import io.edukativ.myskoolin.infrastructure.schooling.dto.SchoolRoomDbDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A SchoolRoom.
 */
public class SchoolRoomDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String clientId;

    private List<SchoolRoomDistance> distances;

    private List<TimeSlot> timetable;

    private String name;

    private Boolean closed;

    private Integer seats;

    private EnumSchoolRoomsTypes type;

    private BigDecimal surface;

    private String comment;

    private String longitude;

    private String latitude;

    private Boolean deleted;

    /*
    ###########################################
    ############# BASIC INFOS - END ###########
    ###########################################
     */

    public SchoolRoomDTO() {
    }

    public SchoolRoomDTO(SchoolRoomDbDTO schoolRoom) {
        this.id = schoolRoom.getId().toString();
        this.clientId = schoolRoom.getClientId().toHexString();
        this.name = schoolRoom.getName();
        this.closed = schoolRoom.isClosed();
        this.seats = schoolRoom.getSeats();
        this.type = EnumSchoolRoomsTypes.valueOf(schoolRoom.getType().name());
        this.surface = schoolRoom.getSurface();
        this.comment = schoolRoom.getComment();
        this.longitude = schoolRoom.getLongitude();
        this.latitude = schoolRoom.getLatitude();
        this.deleted = schoolRoom.getDeleted();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean isClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public BigDecimal getSurface() {
        return surface;
    }

    public void setSurface(BigDecimal surface) {
        this.surface = surface;
    }

    public List<TimeSlot> getTimetable() {
        if (this.timetable == null) {
            this.timetable = new ArrayList<>();
        }

        return timetable;
    }

    public void setTimetable(List<TimeSlot> timetable) {
        this.timetable = timetable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SchoolRoomDTO schoolRoom = (SchoolRoomDTO) o;
        return !(schoolRoom.id == null || id == null) && Objects.equals(id, schoolRoom.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "SchoolRoom{" +
                "id=" + id +
                ", clientId='" + clientId + "'" +
                ", closed='" + closed + "'" +
                ", comment='" + comment + "'" +
                ", distances='" + distances + "'" +
                ", name='" + name + "'" +
                ", seats='" + seats + "'" +
                ", surface='" + surface + "'" +
                ", timetable='" + timetable + "'" +
                '}';
        }
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public EnumSchoolRoomsTypes getType() {
        return type;
    }

    public void setType(EnumSchoolRoomsTypes type) {
        this.type = type;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
