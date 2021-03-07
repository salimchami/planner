package io.scplanner.solver;

import io.scplanner.entities.Subject;
import io.scplanner.entities.TimeTable;
import io.scplanner.entities.Timeslot;
import io.scplanner.exceptions.SolutionConfigurationException;
import io.scplanner.providers.CorrectTimeSlotsTestProvider;
import io.scplanner.providers.EmptyTimeSlotsTestProvider;
import io.scplanner.providers.SubjectsTestProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class
SolverJobTest {

    @ParameterizedTest
    @MethodSource("should_add_correct_duration_of_subject_params")
    void should_add_correct_duration_of_subject(Subject subject, Set<Timeslot> baseTimeslots) throws SolutionConfigurationException {
        final TimeTable timeTable = new TimeTable(baseTimeslots, singletonList(subject));
        SolverJob<TimeTable, String, Timeslot> sut = new SolverJob<>("io.scplanner", timeTable);
        sut.solve();
        assertThat(subject.durationOfSubject(sut.getFinalBestSolution().getTimeslots()))
                .isLessThanOrEqualTo(subject.getMaxMinutesPerDay())
                .isGreaterThanOrEqualTo(subject.getMinMinutesPerDay());
    }

    private static Stream<Arguments> should_add_correct_duration_of_subject_params() {
        return Stream.of(
                Arguments.of(SubjectsTestProvider.english, CorrectTimeSlotsTestProvider.timeSlots()),
                Arguments.of(SubjectsTestProvider.english, EmptyTimeSlotsTestProvider.timeSlots())
        );
    }
}
