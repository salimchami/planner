package io.scplanner.providers;

import io.scplanner.entities.Timeslot;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class TimeSlotsProvider {

    public static Set<Timeslot> emptyTimeSlots() {
        return Arrays.stream(DayOfWeek.values())
                .map(dayOfWeek -> new HashSet<>(Arrays.asList(
                        new Timeslot(1L, dayOfWeek, LocalTime.of(8, 0), LocalTime.of(8, 30), null),
                        new Timeslot(2L, dayOfWeek, LocalTime.of(8, 30), LocalTime.of(9, 0), null),
                        new Timeslot(3L, dayOfWeek, LocalTime.of(9, 0), LocalTime.of(9, 30), null),
                        new Timeslot(4L, dayOfWeek, LocalTime.of(9, 30), LocalTime.of(10, 0), null),
                        new Timeslot(5L, dayOfWeek, LocalTime.of(10, 0), LocalTime.of(10, 30), null),
                        new Timeslot(6L, dayOfWeek, LocalTime.of(10, 30), LocalTime.of(11, 0), null),
                        new Timeslot(7L, dayOfWeek, LocalTime.of(11, 0), LocalTime.of(11, 30), null),
                        new Timeslot(8L, dayOfWeek, LocalTime.of(11, 30), LocalTime.of(12, 0), null),
                        new Timeslot(8L, dayOfWeek, LocalTime.of(14, 0), LocalTime.of(14, 30), null),
                        new Timeslot(8L, dayOfWeek, LocalTime.of(14, 30), LocalTime.of(15, 0), null),
                        new Timeslot(8L, dayOfWeek, LocalTime.of(15, 0), LocalTime.of(15, 30), null),
                        new Timeslot(8L, dayOfWeek, LocalTime.of(15, 30), LocalTime.of(16, 0), null),
                        new Timeslot(8L, dayOfWeek, LocalTime.of(16, 0), LocalTime.of(16, 30), null),
                        new Timeslot(8L, dayOfWeek, LocalTime.of(16, 30), LocalTime.of(17, 0), null),
                        new Timeslot(8L, dayOfWeek, LocalTime.of(17, 0), LocalTime.of(17, 30), null),
                        new Timeslot(8L, dayOfWeek, LocalTime.of(17, 30), LocalTime.of(18, 0), null)
                )))
                .flatMap(Collection::stream)
                .collect(toSet());
    }
}
