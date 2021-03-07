package io.scplanner.providers;

import io.scplanner.entities.Timeslot;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class EmptyTimeSlotsTestProvider {

    public static Set<Timeslot> timeSlots() {
        return Arrays.stream(DayOfWeek.values())
                .map(dayOfWeek -> new HashSet<>(Arrays.asList(
                        new Timeslot(dayOfWeek.ordinal() + 1L, dayOfWeek, LocalTime.of(8, 0), LocalTime.of(8, 30), null),
                        new Timeslot(dayOfWeek.ordinal() + 2L, dayOfWeek, LocalTime.of(8, 30), LocalTime.of(9, 0), null),
                        new Timeslot(dayOfWeek.ordinal() + 3L, dayOfWeek, LocalTime.of(9, 0), LocalTime.of(9, 30), null),
                        new Timeslot(dayOfWeek.ordinal() + 4L, dayOfWeek, LocalTime.of(9, 30), LocalTime.of(10, 0), null),
                        new Timeslot(dayOfWeek.ordinal() + 5L, dayOfWeek, LocalTime.of(10, 0), LocalTime.of(10, 30), null),
                        new Timeslot(dayOfWeek.ordinal() + 6L, dayOfWeek, LocalTime.of(10, 30), LocalTime.of(11, 0), null),
                        new Timeslot(dayOfWeek.ordinal() + 7L, dayOfWeek, LocalTime.of(11, 0), LocalTime.of(11, 30), null),
                        new Timeslot(dayOfWeek.ordinal() + 8L, dayOfWeek, LocalTime.of(11, 30), LocalTime.of(12, 0), null),
                        new Timeslot(dayOfWeek.ordinal() + 9L, dayOfWeek, LocalTime.of(14, 0), LocalTime.of(14, 30), null),
                        new Timeslot(dayOfWeek.ordinal() + 10L, dayOfWeek, LocalTime.of(14, 30), LocalTime.of(15, 0), null),
                        new Timeslot(dayOfWeek.ordinal() + 11L, dayOfWeek, LocalTime.of(15, 0), LocalTime.of(15, 30), null),
                        new Timeslot(dayOfWeek.ordinal() + 12L, dayOfWeek, LocalTime.of(15, 30), LocalTime.of(16, 0), null),
                        new Timeslot(dayOfWeek.ordinal() + 13L, dayOfWeek, LocalTime.of(16, 0), LocalTime.of(16, 30), null),
                        new Timeslot(dayOfWeek.ordinal() + 14L, dayOfWeek, LocalTime.of(16, 30), LocalTime.of(17, 0), null),
                        new Timeslot(dayOfWeek.ordinal() + 15L, dayOfWeek, LocalTime.of(17, 0), LocalTime.of(17, 30), null),
                        new Timeslot(dayOfWeek.ordinal() + 16L, dayOfWeek, LocalTime.of(17, 30), LocalTime.of(18, 0), null)
                )))
                .flatMap(Collection::stream)
                .collect(toSet());
    }
}
