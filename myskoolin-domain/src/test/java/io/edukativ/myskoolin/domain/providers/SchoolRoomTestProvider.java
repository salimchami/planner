package io.edukativ.myskoolin.domain.providers;

import io.edukativ.myskoolin.domain.schoolrooms.EnumSchoolRoomsTypes;
import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoom;
import io.edukativ.myskoolin.domain.timetabling.Lesson;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SchoolRoomTestProvider {

    public static SchoolRoom schoolRoom(String id, List<Lesson> timetable, String name, int seats,
                                        EnumSchoolRoomsTypes schoolRoomsType) {
        return new SchoolRoom(id, GlobalTestProvider.CLIENT_ID, Collections.emptyList(), timetable,
                name, false, seats, schoolRoomsType, BigDecimal.valueOf(65L), "RAS",
                null, null, false);
    }

    public static List<SchoolRoom> allSchoolRooms() {
        return Stream.of(
                steppedSchoolRooms(),
                multipurposeSchoolRooms(),
                amphitheaterSchoolRooms(),
                normalSchoolRooms(),
                sciencesSchoolRooms(),
                musicSchoolRooms(),
                sportSchoolRooms(),
                itSchoolRooms()
        )
        .flatMap(Collection::stream).collect(Collectors.toList());
    }

    public static List<SchoolRoom> steppedSchoolRooms() {
        return Collections.singletonList(
                schoolRoom(GlobalTestProvider.SCHOOL_ROOM_STEPPED_01_ID, new ArrayList<>(), "Victor HUGO", 100, EnumSchoolRoomsTypes.STEPPED)
        );
    }

    public static List<SchoolRoom> multipurposeSchoolRooms() {
        return Collections.singletonList(
                schoolRoom(GlobalTestProvider.SCHOOL_ROOM_MULTIPURPOSE_01_ID, new ArrayList<>(), "POLY", 100, EnumSchoolRoomsTypes.MULTIPURPOSE)
        );
    }

    public static List<SchoolRoom> amphitheaterSchoolRooms() {
        return Collections.singletonList(
                schoolRoom(GlobalTestProvider.SCHOOL_ROOM_AMPHITHEATER_01_ID, new ArrayList<>(), "René Descartes", 100, EnumSchoolRoomsTypes.AMPHITHEATER)
        );
    }

    private static List<SchoolRoom> normalSchoolRooms() {
        return Arrays.asList(
                schoolRoom(GlobalTestProvider.SCHOOL_ROOM_100_ID, new ArrayList<>(), "100", 30, EnumSchoolRoomsTypes.NORMAL),
                schoolRoom(GlobalTestProvider.SCHOOL_ROOM_101_ID, new ArrayList<>(), "101", 30, EnumSchoolRoomsTypes.NORMAL),
                schoolRoom(GlobalTestProvider.SCHOOL_ROOM_102_ID, new ArrayList<>(), "102", 30, EnumSchoolRoomsTypes.NORMAL),
                schoolRoom(GlobalTestProvider.SCHOOL_ROOM_103_ID, new ArrayList<>(), "103", 30, EnumSchoolRoomsTypes.NORMAL),
                schoolRoom(GlobalTestProvider.SCHOOL_ROOM_104_ID, new ArrayList<>(), "104", 30, EnumSchoolRoomsTypes.NORMAL),
                schoolRoom(GlobalTestProvider.SCHOOL_ROOM_105_ID, new ArrayList<>(), "105", 30, EnumSchoolRoomsTypes.NORMAL),
                schoolRoom(GlobalTestProvider.SCHOOL_ROOM_106_ID, new ArrayList<>(), "106", 30, EnumSchoolRoomsTypes.NORMAL),
                schoolRoom(GlobalTestProvider.SCHOOL_ROOM_107_ID, new ArrayList<>(), "107", 30, EnumSchoolRoomsTypes.NORMAL),
                schoolRoom(GlobalTestProvider.SCHOOL_ROOM_108_ID, new ArrayList<>(), "108", 30, EnumSchoolRoomsTypes.NORMAL),
                schoolRoom(GlobalTestProvider.SCHOOL_ROOM_109_ID, new ArrayList<>(), "109", 30, EnumSchoolRoomsTypes.NORMAL),
                schoolRoom(GlobalTestProvider.SCHOOL_ROOM_110_ID, new ArrayList<>(), "110", 30, EnumSchoolRoomsTypes.NORMAL)
        );
    }

    private static List<SchoolRoom> sciencesSchoolRooms() {
        return Arrays.asList(
                schoolRoom(GlobalTestProvider.SCHOOL_ROOM_SCIENCES_01_ID, new ArrayList<>(), "SC-1", 30, EnumSchoolRoomsTypes.SCIENCES),
                schoolRoom(GlobalTestProvider.SCHOOL_ROOM_SCIENCES_02_ID, new ArrayList<>(), "SC-2", 30, EnumSchoolRoomsTypes.SCIENCES),
                    schoolRoom(GlobalTestProvider.SCHOOL_ROOM_SCIENCES_03_ID, new ArrayList<>(), "SC-3", 30, EnumSchoolRoomsTypes.SCIENCES),
                schoolRoom(GlobalTestProvider.SCHOOL_ROOM_SCIENCES_04_ID, new ArrayList<>(), "SC-4", 30, EnumSchoolRoomsTypes.SCIENCES)
        );
    }

    private static List<SchoolRoom> musicSchoolRooms() {
        return Arrays.asList(
                schoolRoom(GlobalTestProvider.SCHOOL_ROOM_MUSIQUE_01_ID, new ArrayList<>(), "M-100", 30, EnumSchoolRoomsTypes.MUSIC),
                schoolRoom(GlobalTestProvider.SCHOOL_ROOM_MUSIQUE_02_ID, new ArrayList<>(), "M-101", 30, EnumSchoolRoomsTypes.MUSIC)
        );
    }

    private static List<SchoolRoom> sportSchoolRooms() {
        return Arrays.asList(
                schoolRoom(GlobalTestProvider.SCHOOL_ROOM_SPORT_01_ID, new ArrayList<>(), "SP-0", 30, EnumSchoolRoomsTypes.SPORT),
                schoolRoom(GlobalTestProvider.SCHOOL_ROOM_SPORT_02_ID, new ArrayList<>(), "SP-1", 30, EnumSchoolRoomsTypes.SPORT),
                schoolRoom(GlobalTestProvider.SCHOOL_ROOM_SPORT_03_ID, new ArrayList<>(), "SP-2", 30, EnumSchoolRoomsTypes.SPORT),
                schoolRoom(GlobalTestProvider.SCHOOL_ROOM_SPORT_04_ID, new ArrayList<>(), "SP-3", 30, EnumSchoolRoomsTypes.SPORT)
        );
    }

    private static List<SchoolRoom> itSchoolRooms() {
        return Arrays.asList(
                schoolRoom(GlobalTestProvider.SCHOOL_ROOM_IT_01_ID, new ArrayList<>(), "IT-0", 30, EnumSchoolRoomsTypes.IT),
                schoolRoom(GlobalTestProvider.SCHOOL_ROOM_IT_02_ID, new ArrayList<>(), "IT-10", 30, EnumSchoolRoomsTypes.IT),
                schoolRoom(GlobalTestProvider.SCHOOL_ROOM_IT_03_ID, new ArrayList<>(), "IT-100", 30, EnumSchoolRoomsTypes.IT)
        );
    }
}
