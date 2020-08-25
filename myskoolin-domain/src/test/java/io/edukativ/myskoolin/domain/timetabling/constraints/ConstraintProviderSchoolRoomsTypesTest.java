package io.edukativ.myskoolin.domain.timetabling.constraints;

import io.edukativ.myskoolin.domain.providers.GlobalTestProvider;
import io.edukativ.myskoolin.domain.schoolrooms.EnumSchoolRoomsTypes;
import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoom;
import io.edukativ.myskoolin.domain.subjects.Subject;
import io.edukativ.myskoolin.domain.timetabling.Lesson;
import io.edukativ.myskoolin.domain.timetabling.SchoolClassTimeTable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

class ConstraintProviderSchoolRoomsTypesTest extends ScoreConstraintProviderTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("conflictParams")
    void roomConflict(String description, int expectedPenalty, EnumSchoolRoomsTypes schoolRoomType,
                      List<EnumSchoolRoomsTypes> subjectSchoolRoomTypes) {

        SchoolRoom schoolRoom = new SchoolRoom();
        schoolRoom.setType(schoolRoomType);
        Lesson lesson = new Lesson(1L, schoolRoom, null, null, timeSlot1, null);
        Subject subject = new Subject();
        subject.setSchoolRoomsTypes(subjectSchoolRoomTypes);

        SchoolClassTimeTable timetable = new SchoolClassTimeTable(config, GlobalTestProvider.CLIENT_ID, schoolClass1, Arrays.asList(schoolClass1, schoolClass2),
                Arrays.asList(schoolRoom1, schoolRoom2), Arrays.asList(subject1, subject2), Arrays.asList(teacher1, teacher2), Collections.singletonList(lesson),
                Collections.singletonList(lesson.getTimeSlot()));

        scoreVerifier.assertHardWeight(TimeTableConstraintConfiguration.CONSTRAINT_SCHOOL_ROOM_TYPE, expectedPenalty, timetable);
    }

    private static Stream<Arguments> conflictParams() {
        prepareParams();
        return Stream.of(
                Arguments.of("normal - [normal, music]", 0, EnumSchoolRoomsTypes.NORMAL,
                        Arrays.asList(EnumSchoolRoomsTypes.NORMAL, EnumSchoolRoomsTypes.MUSIC)),
                Arguments.of("normal - [normal, music]", 0, EnumSchoolRoomsTypes.NORMAL,
                        Collections.singletonList(EnumSchoolRoomsTypes.NORMAL)),
                Arguments.of("normal - [normal, music]", -200, EnumSchoolRoomsTypes.IT,
                        Arrays.asList(EnumSchoolRoomsTypes.NORMAL, EnumSchoolRoomsTypes.MUSIC))
        );
    }

}
