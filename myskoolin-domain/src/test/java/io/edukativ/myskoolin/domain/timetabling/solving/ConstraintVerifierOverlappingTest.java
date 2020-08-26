package io.edukativ.myskoolin.domain.timetabling.solving;

import io.edukativ.myskoolin.domain.commons.vo.EnumDays;
import io.edukativ.myskoolin.domain.commons.vo.EnumPartsOfDay;
import io.edukativ.myskoolin.domain.providers.GlobalTestProvider;
import io.edukativ.myskoolin.domain.timetabling.Lesson;
import io.edukativ.myskoolin.domain.timetabling.SchoolClassTimeTable;
import io.edukativ.myskoolin.domain.timetabling.Time;
import io.edukativ.myskoolin.domain.timetabling.TimeSlot;
import io.edukativ.myskoolin.domain.timetabling.constraints.TimeTableConstraintConfiguration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

class ConstraintVerifierOverlappingTest extends ScoreConstraintVerifierTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("conflictParams")
    void roomConflict(String description, int expectedConflictPenalty, TimeSlot firstTimeSlot, TimeSlot secondTimeSlot) {

        Lesson lesson1 = new Lesson(1L, schoolRoom1, subject1, teacher1, firstTimeSlot, schoolClass1);
        Lesson lesson2 = new Lesson(2L, schoolRoom2, subject2, teacher2, secondTimeSlot, schoolClass1);
        SchoolClassTimeTable timetable = new SchoolClassTimeTable(config, GlobalTestProvider.CLIENT_ID, schoolClass1, Arrays.asList(schoolClass1, schoolClass2),
                Arrays.asList(schoolRoom1, schoolRoom2), Arrays.asList(subject1, subject2), Arrays.asList(teacher1, teacher2), Arrays.asList(lesson1, lesson2),
                Arrays.asList(lesson1.getTimeSlot(), lesson2.getTimeSlot()));

        scoreVerifier.assertHardWeight(TimeTableConstraintConfiguration.CONSTRAINT_TIMESLOTS_OVERLAPS, expectedConflictPenalty,
                timetable);
    }

    private static Stream<Arguments> conflictParams() {
        prepareParams();
        return Stream.of(
                Arguments.of("== timeSlot", 40, timeSlot1, new TimeSlot(3L, timeSlot1.getDay(),
                        timeSlot1.getStartTime(), timeSlot1.getEndTime())),
                Arguments.of("<> timeSlot", 20, timeSlot1, timeSlot2),
                Arguments.of("<> timeSlot", -580, timeSlot1, new TimeSlot(3L, EnumDays.MONDAY,
                        new Time(8, 30, 0, EnumPartsOfDay.AM),
                        new Time(10, 0, 0, EnumPartsOfDay.AM)))
        );
    }

}
