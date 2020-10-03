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
        return Collections.singletonList(steppedSchoolRoom());
    }

    public static SchoolRoom steppedSchoolRoom() {
        return schoolRoom(GlobalTestProvider.SCHOOL_ROOM_STEPPED_01_ID, new ArrayList<>(), "Victor HUGO", 100, EnumSchoolRoomsTypes.STEPPED);
    }

    public static List<SchoolRoom> multipurposeSchoolRooms() {
        return Collections.singletonList(multipurposeSchoolRoom());
    }

    public static SchoolRoom multipurposeSchoolRoom() {
        return schoolRoom(GlobalTestProvider.SCHOOL_ROOM_MULTIPURPOSE_01_ID, new ArrayList<>(), "POLY", 100, EnumSchoolRoomsTypes.MULTIPURPOSE);
    }

    public static List<SchoolRoom> amphitheaterSchoolRooms() {
        return Collections.singletonList(amphitheaterSchoolRoom());
    }

    public static SchoolRoom amphitheaterSchoolRoom() {
        return schoolRoom(GlobalTestProvider.SCHOOL_ROOM_AMPHITHEATER_01_ID, new ArrayList<>(), "René Descartes", 100, EnumSchoolRoomsTypes.AMPHITHEATER);
    }

    public static List<SchoolRoom> normalSchoolRooms() {
        return Arrays.asList(
                normalSchoolRoom100(),
                normalSchoolRoom101(),
                normalSchoolRoom102(),
                normalSchoolRoom103(),
                normalSchoolRoom104(),
                normalSchoolRoom105(),
                normalSchoolRoom106(),
                normalSchoolRoom107(),
                normalSchoolRoom108(),
                normalSchoolRoom109(),
                normalSchoolRoom110()
        );
    }

    public static SchoolRoom normalSchoolRoom110() {
        return schoolRoom(GlobalTestProvider.SCHOOL_ROOM_110_ID, new ArrayList<>(), "110", 30, EnumSchoolRoomsTypes.NORMAL);
    }

    public static SchoolRoom normalSchoolRoom109() {
        return schoolRoom(GlobalTestProvider.SCHOOL_ROOM_109_ID, new ArrayList<>(), "109", 30, EnumSchoolRoomsTypes.NORMAL);
    }

    public static SchoolRoom normalSchoolRoom108() {
        return schoolRoom(GlobalTestProvider.SCHOOL_ROOM_108_ID, new ArrayList<>(), "108", 30, EnumSchoolRoomsTypes.NORMAL);
    }

    public static SchoolRoom normalSchoolRoom107() {
        return schoolRoom(GlobalTestProvider.SCHOOL_ROOM_107_ID, new ArrayList<>(), "107", 30, EnumSchoolRoomsTypes.NORMAL);
    }

    public static SchoolRoom normalSchoolRoom106() {
        return schoolRoom(GlobalTestProvider.SCHOOL_ROOM_106_ID, new ArrayList<>(), "106", 30, EnumSchoolRoomsTypes.NORMAL);
    }

    public static SchoolRoom normalSchoolRoom105() {
        return schoolRoom(GlobalTestProvider.SCHOOL_ROOM_105_ID, new ArrayList<>(), "105", 30, EnumSchoolRoomsTypes.NORMAL);
    }

    public static SchoolRoom normalSchoolRoom104() {
        return schoolRoom(GlobalTestProvider.SCHOOL_ROOM_104_ID, new ArrayList<>(), "104", 30, EnumSchoolRoomsTypes.NORMAL);
    }

    public static SchoolRoom normalSchoolRoom103() {
        return schoolRoom(GlobalTestProvider.SCHOOL_ROOM_103_ID, new ArrayList<>(), "103", 30, EnumSchoolRoomsTypes.NORMAL);
    }

    public static SchoolRoom normalSchoolRoom102() {
        return schoolRoom(GlobalTestProvider.SCHOOL_ROOM_102_ID, new ArrayList<>(), "102", 30, EnumSchoolRoomsTypes.NORMAL);
    }

    public static SchoolRoom normalSchoolRoom101() {
        return schoolRoom(GlobalTestProvider.SCHOOL_ROOM_101_ID, new ArrayList<>(), "101", 30, EnumSchoolRoomsTypes.NORMAL);
    }

    public static SchoolRoom normalSchoolRoom100() {
        return schoolRoom(GlobalTestProvider.SCHOOL_ROOM_100_ID, new ArrayList<>(), "100", 30, EnumSchoolRoomsTypes.NORMAL);
    }

    public static List<SchoolRoom> sciencesSchoolRooms() {
        return Arrays.asList(
                sciencesSchoolRoomSC1(),
                sciencesSchoolRoomSC2(),
                sciencesSchoolRoomSC3(),
                sciencesSchoolRoomSC4()
        );
    }

    public static SchoolRoom sciencesSchoolRoomSC4() {
        return schoolRoom(GlobalTestProvider.SCHOOL_ROOM_SCIENCES_04_ID, new ArrayList<>(), "SC-4", 30, EnumSchoolRoomsTypes.SCIENCES);
    }

    public static SchoolRoom sciencesSchoolRoomSC3() {
        return schoolRoom(GlobalTestProvider.SCHOOL_ROOM_SCIENCES_03_ID, new ArrayList<>(), "SC-3", 30, EnumSchoolRoomsTypes.SCIENCES);
    }

    public static SchoolRoom sciencesSchoolRoomSC2() {
        return schoolRoom(GlobalTestProvider.SCHOOL_ROOM_SCIENCES_02_ID, new ArrayList<>(), "SC-2", 30, EnumSchoolRoomsTypes.SCIENCES);
    }

    public static SchoolRoom sciencesSchoolRoomSC1() {
        return schoolRoom(GlobalTestProvider.SCHOOL_ROOM_SCIENCES_01_ID, new ArrayList<>(), "SC-1", 30, EnumSchoolRoomsTypes.SCIENCES);
    }

    public static List<SchoolRoom> musicSchoolRooms() {
        return Arrays.asList(musicSchoolRoom100(), musicSchoolRoom101());
    }

    public static SchoolRoom musicSchoolRoom101() {
        return schoolRoom(GlobalTestProvider.SCHOOL_ROOM_MUSIQUE_02_ID, new ArrayList<>(), "M-101", 30, EnumSchoolRoomsTypes.MUSIC);
    }

    public static SchoolRoom musicSchoolRoom100() {
        return schoolRoom(GlobalTestProvider.SCHOOL_ROOM_MUSIQUE_01_ID, new ArrayList<>(), "M-100", 30, EnumSchoolRoomsTypes.MUSIC);
    }

    public static List<SchoolRoom> sportSchoolRooms() {
        return Arrays.asList(
                sportSchoolRoom0(),
                sportSchoolRoom1(),
                sportSchoolRoom2(),
                sportSchoolRoom3()
        );
    }

    public static SchoolRoom sportSchoolRoom3() {
        return schoolRoom(GlobalTestProvider.SCHOOL_ROOM_SPORT_04_ID, new ArrayList<>(), "SP-3", 30, EnumSchoolRoomsTypes.SPORT);
    }

    public static SchoolRoom sportSchoolRoom2() {
        return schoolRoom(GlobalTestProvider.SCHOOL_ROOM_SPORT_03_ID, new ArrayList<>(), "SP-2", 30, EnumSchoolRoomsTypes.SPORT);
    }

    public static SchoolRoom sportSchoolRoom1() {
        return schoolRoom(GlobalTestProvider.SCHOOL_ROOM_SPORT_02_ID, new ArrayList<>(), "SP-1", 30, EnumSchoolRoomsTypes.SPORT);
    }

    public static SchoolRoom sportSchoolRoom0() {
        return schoolRoom(GlobalTestProvider.SCHOOL_ROOM_SPORT_01_ID, new ArrayList<>(), "SP-0", 30, EnumSchoolRoomsTypes.SPORT);
    }

    public static List<SchoolRoom> itSchoolRooms() {
        return Arrays.asList(
                itSchoolRoom0(),
                itSchoolRoom10(),
                itSchoolRoom100()
        );
    }

    public static SchoolRoom itSchoolRoom100() {
        return schoolRoom(GlobalTestProvider.SCHOOL_ROOM_IT_03_ID, new ArrayList<>(), "IT-100", 30, EnumSchoolRoomsTypes.IT);
    }

    public static SchoolRoom itSchoolRoom10() {
        return schoolRoom(GlobalTestProvider.SCHOOL_ROOM_IT_02_ID, new ArrayList<>(), "IT-10", 30, EnumSchoolRoomsTypes.IT);
    }

    public static SchoolRoom itSchoolRoom0() {
        return schoolRoom(GlobalTestProvider.SCHOOL_ROOM_IT_01_ID, new ArrayList<>(), "IT-0", 30, EnumSchoolRoomsTypes.IT);
    }
}
