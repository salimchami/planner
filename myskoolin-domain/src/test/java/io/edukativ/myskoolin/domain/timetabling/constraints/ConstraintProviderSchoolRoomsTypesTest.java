package io.edukativ.myskoolin.domain.timetabling.constraints;

import io.edukativ.myskoolin.domain.schoolrooms.EnumSchoolRoomsTypes;
import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoom;
import io.edukativ.myskoolin.domain.subjects.Subject;
import io.edukativ.myskoolin.domain.timetabling.Lesson;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

class ConstraintProviderSchoolRoomsTypesTest extends ConstraintProviderTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("conflictParams")
    void roomConflict(String description, int expectedReward, EnumSchoolRoomsTypes schoolRoomType,
                      List<EnumSchoolRoomsTypes> subjectSchoolRoomTypes) {

        SchoolRoom schoolRoom = new SchoolRoom();
        schoolRoom.setType(schoolRoomType);
        Lesson lesson = new Lesson(1L, schoolRoom, null, null, timeSlot1, null);
        Subject subject = new Subject();
        subject.setSchoolRoomsTypes(subjectSchoolRoomTypes);

        constraintVerifier.verifyThat(TimeTableConstraintsProvider::schoolRoomTypesConflicsPenalty)
                .given(lesson, subject)
                .rewardsWith(expectedReward);
    }

    private static Stream<Arguments> conflictParams() {
        prepareParams();
        return Stream.of(
                Arguments.of("normal - [normal, music]", 1, EnumSchoolRoomsTypes.NORMAL,
                        Arrays.asList(EnumSchoolRoomsTypes.NORMAL, EnumSchoolRoomsTypes.MUSIC)),
                Arguments.of("normal - [normal, music]", 1, EnumSchoolRoomsTypes.NORMAL,
                        Collections.singletonList(EnumSchoolRoomsTypes.NORMAL)),
                Arguments.of("normal - [normal, music]", 0, EnumSchoolRoomsTypes.IT,
                        Arrays.asList(EnumSchoolRoomsTypes.NORMAL, EnumSchoolRoomsTypes.MUSIC))
        );
    }

}
