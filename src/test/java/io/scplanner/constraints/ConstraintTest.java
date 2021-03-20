package io.scplanner.constraints;

import io.scplanner.entities.Subject;
import io.scplanner.entities.TimeTable;
import io.scplanner.entities.Timeslot;
import io.scplanner.exceptions.SolutionConfigurationException;
import io.scplanner.providers.OverflowTimeSlotsForEnglishAndFrenchTestProvider;
import io.scplanner.providers.SubjectsTestProvider;
import io.scplanner.score.ScoreLevel;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ConstraintTest {

    private static Stream<Arguments> should_calculate_constraint_score_with_subject_filter_Params() {
        return Stream.of(
                Arguments.of("total : 3h30", -780, new TimeTable(OverflowTimeSlotsForEnglishAndFrenchTestProvider.timeSlots(),
                        SubjectsTestProvider.subjects()))
//                Arguments.of("total : 1h30", 1560, new TimeTable(CorrectTimeSlotsTestProvider.timeSlots(),
//                        SubjectsTestProvider.subjects())),
//                Arguments.of("total : 0, subjects null", -1560, new TimeTable(EmptyTimeSlotsTestProvider.timeSlots(),
//                        SubjectsTestProvider.subjects()))
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("should_calculate_constraint_score_with_subject_filter_Params")
    void should_calculate_constraint_score_with_subject_max_filter(String title,
                                           int expectedScore,
                                           TimeTable timeTable) throws SolutionConfigurationException {
        Constraint<TimeTable, Subject, Timeslot> constraint =
                new Constraint<>("Max Subject Duration By Day",
                        ScoreLevel.HARD,
                        timeTable,
                        Subject.class,
                        Subject::correctDuration,
                        Subject::correctDurationPenalty,
                        Timeslot::totalDurationInMinutes);
        final int score = constraint.calculateScore(timeTable.getBaseTimeslots());
        assertThat(score).isEqualTo(expectedScore);
    }
}
