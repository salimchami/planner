package io.scplanner.providers;

import io.scplanner.entities.Timeslot;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class OverflowTimeSlotsForEnglishAndFrenchTestProvider {

    public static Set<Timeslot> timeSlots() {
        Set<Timeslot> mondayTimeSlots = mondayTimeslots();
        Set<Timeslot> tuesdayTimeSlots = tuesdayTimeslots();
        Set<Timeslot> wednesdayTimeSlots = wednesdayTimeslots();
        Set<Timeslot> thursdayTimeSlots = thursdayTimeslots();
        Set<Timeslot> fridayTimeSlots = fridayTimeslots();
        return timeSlots(mondayTimeSlots, tuesdayTimeSlots, wednesdayTimeSlots, thursdayTimeSlots, fridayTimeSlots);
    }

    private static Set<Timeslot> timeSlots(Set<Timeslot> mondayTimeSlots,
                                           Set<Timeslot> tuesdayTimeSlots,
                                           Set<Timeslot> wednesdayTimeSlots,
                                           Set<Timeslot> thursdayTimeSlots,
                                           Set<Timeslot> fridayTimeSlots) {
        Set<Timeslot> correctTimeslots = new HashSet<>();
        correctTimeslots.addAll(mondayTimeSlots);
        correctTimeslots.addAll(tuesdayTimeSlots);
        correctTimeslots.addAll(wednesdayTimeSlots);
        correctTimeslots.addAll(thursdayTimeSlots);
        correctTimeslots.addAll(fridayTimeSlots);
        return correctTimeslots;
    }

    private static Set<Timeslot> mondayTimeslots() {
        return new HashSet<>(Arrays.asList(
                new Timeslot(1L, DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(8, 30), SubjectsTestProvider.english),
                new Timeslot(2L, DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 0), SubjectsTestProvider.english),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(9, 30), SubjectsTestProvider.english),
                new Timeslot(4L, DayOfWeek.MONDAY, LocalTime.of(9, 30), LocalTime.of(10, 0), SubjectsTestProvider.english),
                new Timeslot(5L, DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(10, 30), SubjectsTestProvider.english),
                new Timeslot(6L, DayOfWeek.MONDAY, LocalTime.of(10, 30), LocalTime.of(11, 0), SubjectsTestProvider.english),
                new Timeslot(7L, DayOfWeek.MONDAY, LocalTime.of(11, 0), LocalTime.of(11, 30), SubjectsTestProvider.english),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(11, 30), LocalTime.of(12, 0), SubjectsTestProvider.english),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(14, 0), LocalTime.of(14, 30), SubjectsTestProvider.english),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(14, 30), LocalTime.of(15, 0), SubjectsTestProvider.english),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(15, 0), LocalTime.of(15, 30), SubjectsTestProvider.french),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(15, 30), LocalTime.of(16, 0), SubjectsTestProvider.french),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(16, 0), LocalTime.of(16, 30), SubjectsTestProvider.lifeEarthSciences),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(16, 30), LocalTime.of(17, 0), SubjectsTestProvider.lifeEarthSciences),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(17, 0), LocalTime.of(17, 30), SubjectsTestProvider.arts),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(17, 30), LocalTime.of(18, 0), SubjectsTestProvider.arts)
        ));
    }

    private static Set<Timeslot> tuesdayTimeslots() {
        return new HashSet<>(Arrays.asList(
                new Timeslot(1L, DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(8, 30), SubjectsTestProvider.english),
                new Timeslot(2L, DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 0), SubjectsTestProvider.english),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(9, 30), SubjectsTestProvider.english),
                new Timeslot(4L, DayOfWeek.MONDAY, LocalTime.of(9, 30), LocalTime.of(10, 0), SubjectsTestProvider.english),
                new Timeslot(5L, DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(10, 30), SubjectsTestProvider.english),
                new Timeslot(6L, DayOfWeek.MONDAY, LocalTime.of(10, 30), LocalTime.of(11, 0), SubjectsTestProvider.english),
                new Timeslot(7L, DayOfWeek.MONDAY, LocalTime.of(11, 0), LocalTime.of(11, 30), SubjectsTestProvider.english),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(11, 30), LocalTime.of(12, 0), SubjectsTestProvider.english),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(14, 0), LocalTime.of(14, 30), SubjectsTestProvider.french),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(14, 30), LocalTime.of(15, 0), SubjectsTestProvider.french),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(15, 0), LocalTime.of(15, 30), SubjectsTestProvider.french),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(15, 30), LocalTime.of(16, 0), SubjectsTestProvider.french),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(16, 0), LocalTime.of(16, 30), SubjectsTestProvider.french),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(16, 30), LocalTime.of(17, 0), SubjectsTestProvider.french),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(17, 0), LocalTime.of(17, 30), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(17, 30), LocalTime.of(18, 0), null)
        ));
    }

    private static Set<Timeslot> wednesdayTimeslots() {
        return new HashSet<>(Arrays.asList(
                new Timeslot(1L, DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(8, 30), SubjectsTestProvider.english),
                new Timeslot(2L, DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 0), SubjectsTestProvider.english),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(9, 30), SubjectsTestProvider.english),
                new Timeslot(4L, DayOfWeek.MONDAY, LocalTime.of(9, 30), LocalTime.of(10, 0),SubjectsTestProvider.english),
                new Timeslot(5L, DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(10, 30), SubjectsTestProvider.french),
                new Timeslot(6L, DayOfWeek.MONDAY, LocalTime.of(10, 30), LocalTime.of(11, 0), SubjectsTestProvider.french),
                new Timeslot(7L, DayOfWeek.MONDAY, LocalTime.of(11, 0), LocalTime.of(11, 30), SubjectsTestProvider.french),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(11, 30), LocalTime.of(12, 0), SubjectsTestProvider.french),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(14, 0), LocalTime.of(14, 30), SubjectsTestProvider.french),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(14, 30), LocalTime.of(15, 0), SubjectsTestProvider.french),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(15, 0), LocalTime.of(15, 30), SubjectsTestProvider.french),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(15, 30), LocalTime.of(16, 0), SubjectsTestProvider.french),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(16, 0), LocalTime.of(16, 30), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(16, 30), LocalTime.of(17, 0), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(17, 0), LocalTime.of(17, 30), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(17, 30), LocalTime.of(18, 0), null)
        ));
    }

    private static Set<Timeslot> thursdayTimeslots() {
        return new HashSet<>(Arrays.asList(
                new Timeslot(1L, DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(8, 30), SubjectsTestProvider.french),
                new Timeslot(2L, DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 0), SubjectsTestProvider.french),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(9, 30), null),
                new Timeslot(4L, DayOfWeek.MONDAY, LocalTime.of(9, 30), LocalTime.of(10, 0), null),
                new Timeslot(5L, DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(10, 30), SubjectsTestProvider.french),
                new Timeslot(6L, DayOfWeek.MONDAY, LocalTime.of(10, 30), LocalTime.of(11, 0), SubjectsTestProvider.french),
                new Timeslot(7L, DayOfWeek.MONDAY, LocalTime.of(11, 0), LocalTime.of(11, 30), SubjectsTestProvider.french),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(11, 30), LocalTime.of(12, 0), SubjectsTestProvider.french),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(14, 0), LocalTime.of(14, 30), SubjectsTestProvider.english),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(14, 30), LocalTime.of(15, 0), SubjectsTestProvider.english),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(15, 0), LocalTime.of(15, 30), SubjectsTestProvider.english),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(15, 30), LocalTime.of(16, 0), SubjectsTestProvider.english),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(16, 0), LocalTime.of(16, 30), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(16, 30), LocalTime.of(17, 0), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(17, 0), LocalTime.of(17, 30), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(17, 30), LocalTime.of(18, 0), null)
        ));
    }

    private static Set<Timeslot> fridayTimeslots() {
        return new HashSet<>(Arrays.asList(
                new Timeslot(1L, DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(8, 30), null),
                new Timeslot(2L, DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 0), null),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(9, 30), null),
                new Timeslot(4L, DayOfWeek.MONDAY, LocalTime.of(9, 30), LocalTime.of(10, 0), null),
                new Timeslot(5L, DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(10, 30), null),
                new Timeslot(6L, DayOfWeek.MONDAY, LocalTime.of(10, 30), LocalTime.of(11, 0), null),
                new Timeslot(7L, DayOfWeek.MONDAY, LocalTime.of(11, 0), LocalTime.of(11, 30), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(11, 30), LocalTime.of(12, 0), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(14, 0), LocalTime.of(14, 30), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(14, 30), LocalTime.of(15, 0), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(15, 0), LocalTime.of(15, 30), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(15, 30), LocalTime.of(16, 0), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(16, 0), LocalTime.of(16, 30), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(16, 30), LocalTime.of(17, 0), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(17, 0), LocalTime.of(17, 30), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(17, 30), LocalTime.of(18, 0), null)
        ));
    }
}
