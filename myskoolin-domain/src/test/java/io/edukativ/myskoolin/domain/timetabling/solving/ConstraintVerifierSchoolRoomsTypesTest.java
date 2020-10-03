package io.edukativ.myskoolin.domain.timetabling.solving;

import io.edukativ.myskoolin.domain.providers.GlobalTestProvider;
import io.edukativ.myskoolin.domain.schoolrooms.EnumSchoolRoomsTypes;
import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoom;
import io.edukativ.myskoolin.domain.timetabling.Lesson;
import io.edukativ.myskoolin.domain.timetabling.SchoolClassTimeTable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

class ConstraintVerifierSchoolRoomsTypesTest extends ScoreConstraintVerifierTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("conflictParams")
    void roomTypesConflict(String description, int expectedPenalty, EnumSchoolRoomsTypes schoolRoomType,
                      List<EnumSchoolRoomsTypes> subjectSchoolRoomTypes) {

        SchoolRoom schoolRoom = new SchoolRoom();
        schoolRoom.setType(schoolRoomType);
        schoolRoom.setId("1");
        sixiemeFrancaisSubject.setSchoolRoomsTypes(subjectSchoolRoomTypes);
        Lesson lesson = new Lesson(1L, schoolRoom, sixiemeFrancaisSubject, francaisTeacher, timeSlot1);
        SchoolClassTimeTable timetable = new SchoolClassTimeTable(config, GlobalTestProvider.CLIENT_ID, schoolClass1, Arrays.asList(schoolClass1, schoolClass2),
                Arrays.asList(schoolRoom1, schoolRoom2), Arrays.asList(sixiemeFrancaisSubject, sixiemeMathsSubject), Arrays.asList(francaisTeacher, mathsTeacher),
                Collections.singletonList(lesson));

//        scoreVerifier.assertHardWeight(TimeTableConstraintConfiguration.CONSTRAINT_SCHOOL_ROOM_TYPE, expectedPenalty, timetable);
    }

    private static Stream<Arguments> conflictParams() {
        prepareParams();
        return Stream.of(
                Arguments.of("NORMAL - [NORMAL, MUSIC]", 0, EnumSchoolRoomsTypes.NORMAL,
                        Arrays.asList(EnumSchoolRoomsTypes.NORMAL, EnumSchoolRoomsTypes.MUSIC)),
                Arguments.of("NORMAL - [NORMAL]", 0, EnumSchoolRoomsTypes.NORMAL,
                        Collections.singletonList(EnumSchoolRoomsTypes.NORMAL)),
                Arguments.of("IT - [NORMAL, MUSIC]", -600, EnumSchoolRoomsTypes.IT,
                        Arrays.asList(EnumSchoolRoomsTypes.NORMAL, EnumSchoolRoomsTypes.MUSIC)),
                Arguments.of("IT - [NORMAL, MUSIC, AMPHITHEATER]", -600, EnumSchoolRoomsTypes.IT,
                        Arrays.asList(EnumSchoolRoomsTypes.NORMAL, EnumSchoolRoomsTypes.MUSIC, EnumSchoolRoomsTypes.AMPHITHEATER ))
        );
    }

}
