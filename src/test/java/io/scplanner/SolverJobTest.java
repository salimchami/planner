package io.scplanner;

import io.scplanner.entities.Subject;
import io.scplanner.entities.TimeTable;
import io.scplanner.entities.Timeslot;
import io.scplanner.exceptions.SolutionConfigurationException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class
SolverJobTest {

    @Disabled
    @ParameterizedTest
    @MethodSource("should_add_correct_duration_of_subject_params")
    void should_add_correct_duration_of_subject(Subject subject, List<Timeslot> baseTimeslots) throws SolutionConfigurationException {
        final TimeTable timeTable = new TimeTable(baseTimeslots, singletonList(subject));
        SolverJob<TimeTable, String, Timeslot> sut = new SolverJob<>("io.scplanner", timeTable);
        sut.solve();
        assertThat(durationOfSubject(subject, sut.getFinalBestSolution()))
                .isLessThan(subject.getMaxMinutesPerDay())
                .isGreaterThan(subject.getMinMinutesPerDay());
    }

    private static Stream<Arguments> should_add_correct_duration_of_subject_params() {
        final Subject english = new Subject(1L, "English", 120, 60, 300, 3);
        return Stream.of(
/*                Arguments.of(english, Arrays.asList(
                        new Timeslot(1L, DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(8, 30), english),
                        new Timeslot(2L, DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 0), english),
                        new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(9, 30), english)))
 */               Arguments.of(english, Arrays.asList(
                        new Timeslot(1L, DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(8, 30), null),
                        new Timeslot(2L, DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 0), null),
                        new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(9, 30), null)))
        );
    }

    private Long durationOfSubject(Subject subject, TimeTable timeTable) {
        return timeTable.getTimeslots().stream()
                .filter(timeslot -> timeslot.getSubject() != null)
                .filter(timeslot -> timeslot.getSubject().equals(subject))
                .mapToLong(Timeslot::durationInMinutes).sum();
    }
}
