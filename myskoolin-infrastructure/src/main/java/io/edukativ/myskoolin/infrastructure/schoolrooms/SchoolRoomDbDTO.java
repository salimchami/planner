package io.edukativ.myskoolin.infrastructure.schoolrooms;

import io.edukativ.myskoolin.infrastructure.common.enums.EnumSchoolRoomsTypes;
import io.edukativ.myskoolin.infrastructure.schooling.vo.SchoolClassTimeSlotDbVO;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A SchoolRoom.
 */
@Document(collection = SchoolRoomDbDTO.MONGO_COLLECTION_NAME)
public class SchoolRoomDbDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String MONGO_COLLECTION_NAME = "school_rooms";
    public static final String MONGO_FIELD_CLIENT_ID = "clientId";
    public static final String MONGO_FIELD_DISTANCES = "distances";
    public static final String MONGO_FIELD_TIMETABLE = "timetable";
    public static final String MONGO_FIELD_NAME = "name";
    public static final String MONGO_FIELD_CLOSED = "closed";
    public static final String MONGO_FIELD_SEATS = "seats";
    public static final String MONGO_FIELD_TYPE = "type";
    public static final String MONGO_FIELD_SURFACE = "surface";
    public static final String MONGO_FIELD_COMMENT = "comment";
    public static final String MONGO_FIELD_LONGITUDE = "longitude";
    public static final String MONGO_FIELD_LATITUDE = "latitude";
    public static final String MONGO_FIELD_DELETED = "deleted";

    @Id
    private ObjectId id;

    @Field(MONGO_FIELD_CLIENT_ID)
    private ObjectId clientId;

    @Field(MONGO_FIELD_DISTANCES)
    private List<SchoolRoomDistanceDbVO> distances;

    @Field(MONGO_FIELD_TIMETABLE)
    private List<SchoolClassTimeSlotDbVO> timetable;

    //TODO: add refTimeslots
    /*
    ###########################################
    ############# BASIC INFOS #################
    ###########################################
     */
    @Field(MONGO_FIELD_NAME)
    private String name;

    @Field(MONGO_FIELD_CLOSED)
    private boolean closed;

    @Field(MONGO_FIELD_SEATS)
    private Integer seats;

    @Field(MONGO_FIELD_TYPE)
    private EnumSchoolRoomsTypes type;

    @Field(MONGO_FIELD_SURFACE)
    private BigDecimal surface;

    @Field(MONGO_FIELD_COMMENT)
    private String comment;

    @Field(MONGO_FIELD_LONGITUDE)
    private String longitude;

    @Field(MONGO_FIELD_LATITUDE)
    private String latitude;

    @Field(MONGO_FIELD_DELETED)
    private boolean deleted;
    /*
    ###########################################
    ############# BASIC INFOS - END ###########
    ###########################################
     */

    public SchoolRoomDbDTO() {
    }

    public SchoolRoomDbDTO(ObjectId id, ObjectId clientId, List<SchoolRoomDistanceDbVO> distances, List<SchoolClassTimeSlotDbVO> timetable,
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

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<SchoolRoomDistanceDbVO> getDistances() {
        if (this.distances == null) {
            this.distances = new ArrayList<>();
        }
        return distances;
    }

    public void setDistances(List<SchoolRoomDistanceDbVO> distances) {
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

    public List<SchoolClassTimeSlotDbVO> getTimetable() {
        if (this.timetable == null) {
            this.timetable = new ArrayList<>();
        }
        return timetable;
    }

    public void setTimetable(List<SchoolClassTimeSlotDbVO> timetable) {
        this.timetable = timetable;
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

    public EnumSchoolRoomsTypes getType() {
        return type;
    }

    public void setType(EnumSchoolRoomsTypes type) {
        this.type = type;
    }

    public ObjectId getClientId() {
        return clientId;
    }

    public void setClientId(ObjectId clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SchoolRoomDbDTO schoolRoom = (SchoolRoomDbDTO) o;
        return !(schoolRoom.id == null || id == null) && Objects.equals(id, schoolRoom.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "SchoolRoom{" +
//                "id=" + id +
//                ", clientId='" + clientId + "'" +
//                ", closed='" + closed + "'" +
//                ", comment='" + comment + "'" +
//                ", distances='" + distances + "'" +
                ", name='" + name + "'" +
//                ", seats='" + seats + "'" +
//                ", surface='" + surface + "'" +
                ", timetable='" + timetable + "'" +
                '}';
    }

    public void update(SchoolRoomDbDTO schoolRoom) {
        this.distances = schoolRoom.getDistances();
        this.timetable = schoolRoom.getTimetable();
        this.name = schoolRoom.getName();
        this.closed = schoolRoom.isClosed();
        this.seats = schoolRoom.getSeats();
        this.type = schoolRoom.getType();
        this.surface = schoolRoom.getSurface();
        this.comment = schoolRoom.getComment();
        this.longitude = schoolRoom.getLongitude();
        this.latitude = schoolRoom.getLatitude();
        this.deleted = schoolRoom.getDeleted();
    }

    public void addTimeSlotToTimetable(SchoolClassTimeSlotDbVO finalTimeSlot) {
        this.timetable.add(finalTimeSlot);
    }
}
