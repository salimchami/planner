package io.edukativ.myskoolin.domain.timetabling;

import io.edukativ.myskoolin.domain.providers.GradeTestProvider;
import io.edukativ.myskoolin.domain.providers.SchoolRoomTestProvider;
import io.edukativ.myskoolin.domain.providers.UserTestProvider;
import io.edukativ.myskoolin.domain.providers.schoolclasses.SchoolClassTestProvider;
import io.edukativ.myskoolin.domain.entity.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class SchoolResourcesNeedsTest {

    private static Stream<Arguments> needsTestMethodParam() {
        List<Grade> grades = GradeTestProvider.allGrades();

        return Stream.of(
                Arguments.of(1, 0, 0, 0,
                        UserTestProvider.teacherUsers(grades), SchoolRoomTestProvider.allSchoolRooms(),
                        true
//                        new HashMap<EnumSchoolRoomsTypes, Integer>() {{
//                            put(EnumSchoolRoomsTypes.NORMAL, 8);
//                            put(EnumSchoolRoomsTypes.SCIENCES, 2);
//                            put(EnumSchoolRoomsTypes.SPORT, 2);
//                            put(EnumSchoolRoomsTypes.IT, 1);
//                            put(EnumSchoolRoomsTypes.MUSIC, 1);
//                        }},
//                        new HashMap<String, Integer>() {{
//                            put(GlobalProvider.Subjects.Sixieme.SUBJECT_SIXIEME_ALLEMAND_ID, 1);
//                            put(GlobalProvider.Subjects.Sixieme.SUBJECT_SIXIEME_ANGLAIS_ID, 1);
//                            put(GlobalProvider.Subjects.Sixieme.SUBJECT_SIXIEME_DESSIN_ID, 1);
//                            put(GlobalProvider.Subjects.Sixieme.SUBJECT_SIXIEME_EDUCCIV_ID, 1);
//                            put(GlobalProvider.Subjects.Sixieme.SUBJECT_SIXIEME_FRANCAIS_EPI_ID, 1);
//                            put(GlobalProvider.Subjects.Sixieme.SUBJECT_SIXIEME_FRANCAIS_ID, 1);
//                            put(GlobalProvider.Subjects.Sixieme.SUBJECT_SIXIEME_HISTGEO_GEO_ID, 1);
//                            put(GlobalProvider.Subjects.Sixieme.SUBJECT_SIXIEME_MATHS_EPI_ID, 1);
//                            put(GlobalProvider.Subjects.Sixieme.SUBJECT_SIXIEME_MATHS_ID, 1);
//                            put(GlobalProvider.Subjects.Sixieme.SUBJECT_SIXIEME_MUSIQUE_ID, 1);
//                            put(GlobalProvider.Subjects.Sixieme.SUBJECT_SIXIEME_PHYSIQUE_ID, 1);
//                            put(GlobalProvider.Subjects.Sixieme.SUBJECT_SIXIEME_SCIENCES_EPI_ID, 1);
//                            put(GlobalProvider.Subjects.Sixieme.SUBJECT_SIXIEME_SPORT_ID, 1);
//                            put(GlobalProvider.Subjects.Sixieme.SUBJECT_SIXIEME_SVT_ID, 1);
//                            put(GlobalProvider.Subjects.Sixieme.SUBJECT_SIXIEME_TECHNOLOGIE_ID, 1);
//                        }}

                )
        );
    }

    @ParameterizedTest
    @MethodSource("needsTestMethodParam")
    void isResourcesSufficientTest(int sixiemeCount, int cinquiemeCount, int quatriemeCount, int troisiemeCount,
                                   List<User> teachersUsers, List<SchoolRoom> schoolRooms, boolean expectedSufficiency) {
        long totalRefWeekDuration = Client.defaultCoursesTimeSlots().stream().mapToLong(TimeSlot::durationInMinutes).sum();
        Map<SchoolClass, List<Subject>> schoolClasses = SchoolClassTestProvider.schoolClassesWithSubjects(sixiemeCount, cinquiemeCount, quatriemeCount, troisiemeCount);
        SchoolResourcesNeeds sut = new SchoolResourcesNeeds(schoolClasses, totalRefWeekDuration);
        boolean sufficient = sut.isResourcesSufficient(teachersUsers, schoolRooms);
        Assertions.assertThat(sufficient).isEqualTo(expectedSufficiency);
    }
}
