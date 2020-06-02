package io.edukativ.myskoolin.infrastructure.config.dbmigrations.dev;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumSchoolRoomsTypes;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.dev.util.DevDbMigrationsConstants;
import io.edukativ.myskoolin.infrastructure.schoolrooms.SchoolRoomDbDTO;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;

@ChangeLog(order = "08")
public class ChangeSet08Schoolrooms {
    private static final String SCHOOL_ROOMS_COLLECTION = "school_rooms";

    @ChangeSet(order = "01", author = "sch", id = "01-schoolrooms-NORMAL")
    public void addSchoolRoomsNORMAL(MongoTemplate mongoTemplate) {
        for (int i = 100; i < 110; i++) {
            SchoolRoomDbDTO room = new SchoolRoomDbDTO(null, DevDbMigrationsConstants.CLIENT_01_ID,
                new ArrayList<>(), new ArrayList<>(), Integer.toString(i), false, 22, EnumSchoolRoomsTypes.NORMAL,
                new BigDecimal("30"),
                "", "2556878", "654654657", false);
            switch (i) {
                case 100:
                    room.setId(DevDbMigrationsConstants.SCHOOL_ROOM_01_ID);
                    break;
                case 101:
                    room.setId(DevDbMigrationsConstants.SCHOOL_ROOM_02_ID);
                    break;
                case 102:
                    room.setId(DevDbMigrationsConstants.SCHOOL_ROOM_03_ID);
                    break;
                case 103:
                    room.setId(DevDbMigrationsConstants.SCHOOL_ROOM_04_ID);
                    break;
                case 104:
                    room.setId(DevDbMigrationsConstants.SCHOOL_ROOM_05_ID);
                    break;
                case 105:
                    room.setId(DevDbMigrationsConstants.SCHOOL_ROOM_06_ID);
                    break;
                case 106:
                    room.setId(DevDbMigrationsConstants.SCHOOL_ROOM_07_ID);
                    break;
                case 107:
                    room.setId(DevDbMigrationsConstants.SCHOOL_ROOM_08_ID);
                    break;
                case 108:
                    room.setId(DevDbMigrationsConstants.SCHOOL_ROOM_09_ID);
                    break;
                case 109:
                    room.setId(DevDbMigrationsConstants.SCHOOL_ROOM_10_ID);
                    break;
                default:
                    break;
            }
            mongoTemplate.insert(room);
        }
    }

    @ChangeSet(order = "02", author = "sch", id = "02-schoolrooms-Sciences")
    public void addSchoolRoomsSciences(MongoTemplate mongoTemplate) {
        for (int i = 1; i <= 4; i++) {
            SchoolRoomDbDTO room = new SchoolRoomDbDTO(null, DevDbMigrationsConstants.CLIENT_01_ID,
                new ArrayList<>(), new ArrayList<>(), "SCIENCES-".concat(Integer.toString(i)), false, 35, EnumSchoolRoomsTypes.SCIENCES,
                new BigDecimal("40"),
                "", "25546878", "6546454657", false);
            switch (i) {
                case 1:
                    room.setId(DevDbMigrationsConstants.SCHOOL_ROOM_SCIENCES_01_ID);
                    break;
                case 2:
                    room.setId(DevDbMigrationsConstants.SCHOOL_ROOM_SCIENCES_02_ID);
                    break;
                case 3:
                    room.setId(DevDbMigrationsConstants.SCHOOL_ROOM_SCIENCES_03_ID);
                    break;
                case 4:
                    room.setId(DevDbMigrationsConstants.SCHOOL_ROOM_SCIENCES_04_ID);
                    break;
                default:
                    break;
            }
            mongoTemplate.insert(room);
        }
    }

    @ChangeSet(order = "03", author = "sch", id = "03-schoolrooms-Music")
    public void addSchoolRoomsMusic(MongoTemplate mongoTemplate) {
        for (int i = 1; i <= 2; i++) {
            SchoolRoomDbDTO room = new SchoolRoomDbDTO(null, DevDbMigrationsConstants.CLIENT_01_ID,
                new ArrayList<>(), new ArrayList<>(), "MUSIC-".concat(Integer.toString(i)), false, 35, EnumSchoolRoomsTypes.MUSIC,
                new BigDecimal("40"),
                "", "25546878", "6546454657", false);
            switch (i) {
                case 1:
                    room.setId(DevDbMigrationsConstants.SCHOOL_ROOM_MUSIC_01_ID);
                    break;
                case 2:
                    room.setId(DevDbMigrationsConstants.SCHOOL_ROOM_MUSIC_02_ID);
                    break;
                default:
                    break;
            }
            mongoTemplate.insert(room);
        }
    }

    @ChangeSet(order = "04", author = "sch", id = "04-schoolrooms-Sport")
    public void addSchoolRoomsSport(MongoTemplate mongoTemplate) {
        for (int i = 1; i <= 4; i++) {
            SchoolRoomDbDTO room = new SchoolRoomDbDTO(null, DevDbMigrationsConstants.CLIENT_01_ID,
                new ArrayList<>(), new ArrayList<>(), "SPORT-".concat(Integer.toString(i)), false, 0, EnumSchoolRoomsTypes.SPORT,
                new BigDecimal("100"),
                "", "25546878", "6546454657", false);
            switch (i) {
                case 1:
                    room.setId(DevDbMigrationsConstants.SCHOOL_ROOM_SPORT_01_ID);
                    break;
                case 2:
                    room.setId(DevDbMigrationsConstants.SCHOOL_ROOM_SPORT_02_ID);
                    break;
                case 3:
                    room.setId(DevDbMigrationsConstants.SCHOOL_ROOM_SPORT_03_ID);
                    break;
                case 4:
                    room.setId(DevDbMigrationsConstants.SCHOOL_ROOM_SPORT_04_ID);
                    break;
                default:
                    break;
            }
            mongoTemplate.insert(room);
        }
    }

    @ChangeSet(order = "05", author = "sch", id = "05-schoolrooms-IT")
    public void addSchoolRoomsIT(MongoTemplate mongoTemplate) {
        for (int i = 1; i <= 3; i++) {
            SchoolRoomDbDTO room = new SchoolRoomDbDTO(null, DevDbMigrationsConstants.CLIENT_01_ID,
                new ArrayList<>(), new ArrayList<>(), "IT-".concat(Integer.toString(i)), false, 22, EnumSchoolRoomsTypes.IT,
                new BigDecimal("30"),
                "", "25546878", "6546454657", false);
            switch (i) {
                case 1:
                    room.setId(DevDbMigrationsConstants.SCHOOL_ROOM_IT_01_ID);
                    break;
                case 2:
                    room.setId(DevDbMigrationsConstants.SCHOOL_ROOM_IT_02_ID);
                    break;
                case 3:
                    room.setId(DevDbMigrationsConstants.SCHOOL_ROOM_IT_03_ID);
                    break;
                default:
                    break;
            }
            mongoTemplate.insert(room);
        }
    }

    @ChangeSet(order = "06", author = "sch", id = "06-schoolrooms-MULTIPURPOSE")
    public void addSchoolRoomsMulti(MongoTemplate mongoTemplate) {
        SchoolRoomDbDTO room = new SchoolRoomDbDTO(null, DevDbMigrationsConstants.CLIENT_01_ID,
            new ArrayList<>(), new ArrayList<>(), "Salle polyvalente", false, 80, EnumSchoolRoomsTypes.MULTIPURPOSE,
            new BigDecimal("200"),
            "", "25546878", "6546454657", false);
        mongoTemplate.insert(room);
    }

    @ChangeSet(order = "07", author = "sch", id = "07-schoolrooms-STEPPED")
    public void addSchoolRoomsSTEPPED(MongoTemplate mongoTemplate) {
        SchoolRoomDbDTO room = new SchoolRoomDbDTO(null, DevDbMigrationsConstants.CLIENT_01_ID,
            new ArrayList<>(), new ArrayList<>(), "René Descartes", false, 80, EnumSchoolRoomsTypes.STEPPED,
            new BigDecimal("200"),
            "", "25546878", "6546454657", false);
        mongoTemplate.insert(room);
    }

    @ChangeSet(order = "08", author = "sch", id = "08-schoolrooms-AMPHITHEATER")
    public void addSchoolRoomsAMPHITHEATER(MongoTemplate mongoTemplate) {
        SchoolRoomDbDTO room = new SchoolRoomDbDTO(null, DevDbMigrationsConstants.CLIENT_01_ID,
            new ArrayList<>(), new ArrayList<>(), "Amphitheater", false, 80, EnumSchoolRoomsTypes.AMPHITHEATER,
            new BigDecimal("200"),
            "", "25546878", "6546454657", false);
        mongoTemplate.insert(room);
    }
}
