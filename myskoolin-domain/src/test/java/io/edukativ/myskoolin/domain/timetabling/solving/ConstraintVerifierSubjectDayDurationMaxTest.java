package io.edukativ.myskoolin.domain.timetabling.solving;

import io.edukativ.myskoolin.domain.commons.vo.EnumDays;
import io.edukativ.myskoolin.domain.commons.vo.EnumPartsOfDay;
import io.edukativ.myskoolin.domain.providers.GlobalTestProvider;
import io.edukativ.myskoolin.domain.timetabling.Lesson;
import io.edukativ.myskoolin.domain.timetabling.SchoolClassTimeTable;
import io.edukativ.myskoolin.domain.timetabling.Time;
import io.edukativ.myskoolin.domain.timetabling.TimeSlot;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class ConstraintVerifierSubjectDayDurationMaxTest extends ScoreConstraintVerifierTest {

    private SchoolClassTimeTable timetable;

    @ParameterizedTest(name = "{0}")
    @MethodSource("conflictParams")
    void subjectsDayDurationPenalty(String description, int expectedSingleConflictPenalty, TimeSlot firstTimeSlot, TimeSlot secondTimeSlot, TimeSlot thirdTimeSlot) {
        initVariables(firstTimeSlot, secondTimeSlot, thirdTimeSlot);
//        scoreVerifier.assertHardWeight(TimeTableConstraintConfiguration.CONSTRAINT_SUBJECT_DURATION_MAX_BY_DAY, expectedSingleConflictPenalty, timetable);
    }

    private void initVariables(TimeSlot timeSlot1, TimeSlot timeSlot2, TimeSlot timeSlot3) {
        Lesson lesson1 = new Lesson(1L, schoolRoom1, sixiemeFrancaisSubject, francaisTeacher, timeSlot1);
        Lesson lesson2 = new Lesson(2L, schoolRoom1, sixiemeFrancaisSubject, mathsTeacher, timeSlot2);
        Lesson lesson3 = new Lesson(3L, schoolRoom2, sixiemeMathsSubject, mathsTeacher, timeSlot3);

        timetable = new SchoolClassTimeTable(config, GlobalTestProvider.CLIENT_ID, schoolClass1, List.of(schoolClass1, schoolClass2),
                List.of(schoolRoom1, schoolRoom2), List.of(sixiemeFrancaisSubject, sixiemeMathsSubject), List.of(francaisTeacher, mathsTeacher),
                List.of(lesson1, lesson2, lesson3));
    }

    private static Stream<Arguments> conflictParams() {
        prepareParams();
        return Stream.of(
                Arguments.of("francais -> 120 mn (max : 120) / maths -> 60 mn (max 120)", 600,
                        timeSlot1,
                        timeSlot2,
                        new TimeSlot(3L, EnumDays.MONDAY,
                                new Time(10, 0, 0, EnumPartsOfDay.AM),
                                new Time(11, 0, 0, EnumPartsOfDay.AM))),
                Arguments.of("francais -> 180 mn (max : 120) / maths -> 60 mn (max 120)", -1200,
                        timeSlot1,
                        new TimeSlot(3L, EnumDays.MONDAY,
                                new Time(10, 0, 0, EnumPartsOfDay.AM),
                                new Time(12, 0, 0, EnumPartsOfDay.AM)),
                        timeSlot2),
                Arguments.of("francais -> 180 mn (max : 120) / maths -> 240 mn (max 120)", -4200,
                        timeSlot1,
                        new TimeSlot(3L, EnumDays.MONDAY,
                                new Time(10, 0, 0, EnumPartsOfDay.AM),
                                new Time(12, 0, 0, EnumPartsOfDay.AM)),
                        new TimeSlot(4L, EnumDays.MONDAY,
                                new Time(10, 0, 0, EnumPartsOfDay.AM),
                                new Time(14, 0, 0, EnumPartsOfDay.PM)))
        );
    }

}
