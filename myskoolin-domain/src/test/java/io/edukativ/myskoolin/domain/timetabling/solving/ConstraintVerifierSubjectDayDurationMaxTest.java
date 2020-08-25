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

import java.util.List;
import java.util.stream.Stream;

class ConstraintVerifierSubjectDayDurationMaxTest extends ScoreConstraintVerifierTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("conflictParams")
    void subjectsDayDurationPenalty(String description, int expectedConflictPenalty, TimeSlot firstTimeSlot, TimeSlot secondTimeSlot) {

        Lesson lesson1 = new Lesson(1L, schoolRoom1, subject1, teacher1, firstTimeSlot, schoolClass1);
        Lesson lesson2 = new Lesson(2L, schoolRoom1, subject1, teacher1, secondTimeSlot, schoolClass1);

        SchoolClassTimeTable timetable = new SchoolClassTimeTable(config, GlobalTestProvider.CLIENT_ID, schoolClass1, List.of(schoolClass1, schoolClass2),
                List.of(schoolRoom1, schoolRoom2), List.of(subject1, subject2), List.of(teacher1, teacher2), List.of(lesson1, lesson2),
                List.of(lesson1.getTimeSlot(), lesson2.getTimeSlot()));

        scoreVerifier.assertHardWeight(TimeTableConstraintConfiguration.CONSTRAINT_SUBJECT_DURATION_MAX_BY_DAY, expectedConflictPenalty, timetable);
    }

    private static Stream<Arguments> conflictParams() {
        prepareParams();
        return Stream.of(
                Arguments.of("francais 60 minutes timeSlot", 0, timeSlot1, timeSlot2),
                Arguments.of("<> timeSlot", 0, timeSlot1, new TimeSlot(3L, EnumDays.MONDAY,
                        new Time(10, 0, 0, EnumPartsOfDay.AM),
                        new Time(11, 0, 0, EnumPartsOfDay.AM))),
                Arguments.of("<> timeSlot", 600, timeSlot1, new TimeSlot(3L, EnumDays.MONDAY,
                        new Time(10, 0, 0, EnumPartsOfDay.AM),
                        new Time(12, 0, 0, EnumPartsOfDay.AM)))
        );
    }

}
