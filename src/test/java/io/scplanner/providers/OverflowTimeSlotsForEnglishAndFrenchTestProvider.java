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
                new Timeslot(9L, DayOfWeek.MONDAY, LocalTime.of(14, 0), LocalTime.of(14, 30), SubjectsTestProvider.english),
                new Timeslot(10L, DayOfWeek.MONDAY, LocalTime.of(14, 30), LocalTime.of(15, 0), SubjectsTestProvider.english),
                new Timeslot(11L, DayOfWeek.MONDAY, LocalTime.of(15, 0), LocalTime.of(15, 30), SubjectsTestProvider.french),
                new Timeslot(12L, DayOfWeek.MONDAY, LocalTime.of(15, 30), LocalTime.of(16, 0), SubjectsTestProvider.french),
                new Timeslot(13L, DayOfWeek.MONDAY, LocalTime.of(16, 0), LocalTime.of(16, 30), SubjectsTestProvider.lifeEarthSciences),
                new Timeslot(14L, DayOfWeek.MONDAY, LocalTime.of(16, 30), LocalTime.of(17, 0), SubjectsTestProvider.lifeEarthSciences),
                new Timeslot(15L, DayOfWeek.MONDAY, LocalTime.of(17, 0), LocalTime.of(17, 30), SubjectsTestProvider.arts),
                new Timeslot(16L, DayOfWeek.MONDAY, LocalTime.of(17, 30), LocalTime.of(18, 0), SubjectsTestProvider.arts)
        ));
    }

    private static Set<Timeslot> tuesdayTimeslots() {
        return new HashSet<>(Arrays.asList(
                new Timeslot(17L, DayOfWeek.TUESDAY, LocalTime.of(8, 0), LocalTime.of(8, 30), SubjectsTestProvider.english),
                new Timeslot(18L, DayOfWeek.TUESDAY, LocalTime.of(8, 30), LocalTime.of(9, 0), SubjectsTestProvider.english),
                new Timeslot(19L, DayOfWeek.TUESDAY, LocalTime.of(9, 0), LocalTime.of(9, 30), SubjectsTestProvider.english),
                new Timeslot(20L, DayOfWeek.TUESDAY, LocalTime.of(9, 30), LocalTime.of(10, 0), SubjectsTestProvider.english),
                new Timeslot(21L, DayOfWeek.TUESDAY, LocalTime.of(10, 0), LocalTime.of(10, 30), SubjectsTestProvider.english),
                new Timeslot(22L, DayOfWeek.TUESDAY, LocalTime.of(10, 30), LocalTime.of(11, 0), SubjectsTestProvider.english),
                new Timeslot(23L, DayOfWeek.TUESDAY, LocalTime.of(11, 0), LocalTime.of(11, 30), SubjectsTestProvider.english),
                new Timeslot(24L, DayOfWeek.TUESDAY, LocalTime.of(11, 30), LocalTime.of(12, 0), SubjectsTestProvider.english),
                new Timeslot(25L, DayOfWeek.TUESDAY, LocalTime.of(14, 0), LocalTime.of(14, 30), SubjectsTestProvider.french),
                new Timeslot(26L, DayOfWeek.TUESDAY, LocalTime.of(14, 30), LocalTime.of(15, 0), SubjectsTestProvider.french),
                new Timeslot(27L, DayOfWeek.TUESDAY, LocalTime.of(15, 0), LocalTime.of(15, 30), SubjectsTestProvider.french),
                new Timeslot(28L, DayOfWeek.TUESDAY, LocalTime.of(15, 30), LocalTime.of(16, 0), SubjectsTestProvider.french),
                new Timeslot(29L, DayOfWeek.TUESDAY, LocalTime.of(16, 0), LocalTime.of(16, 30), SubjectsTestProvider.french),
                new Timeslot(30L, DayOfWeek.TUESDAY, LocalTime.of(16, 30), LocalTime.of(17, 0), SubjectsTestProvider.french),
                new Timeslot(31L, DayOfWeek.TUESDAY, LocalTime.of(17, 0), LocalTime.of(17, 30), null),
                new Timeslot(32L, DayOfWeek.TUESDAY, LocalTime.of(17, 30), LocalTime.of(18, 0), null)
        ));
    }

    private static Set<Timeslot> wednesdayTimeslots() {
        return new HashSet<>(Arrays.asList(
                new Timeslot(33L, DayOfWeek.WEDNESDAY, LocalTime.of(8, 0), LocalTime.of(8, 30), SubjectsTestProvider.english),
                new Timeslot(34L, DayOfWeek.WEDNESDAY, LocalTime.of(8, 30), LocalTime.of(9, 0), SubjectsTestProvider.english),
                new Timeslot(35L, DayOfWeek.WEDNESDAY, LocalTime.of(9, 0), LocalTime.of(9, 30), SubjectsTestProvider.english),
                new Timeslot(36L, DayOfWeek.WEDNESDAY, LocalTime.of(9, 30), LocalTime.of(10, 0),SubjectsTestProvider.english),
                new Timeslot(37L, DayOfWeek.WEDNESDAY, LocalTime.of(10, 0), LocalTime.of(10, 30), SubjectsTestProvider.french),
                new Timeslot(38L, DayOfWeek.WEDNESDAY, LocalTime.of(10, 30), LocalTime.of(11, 0), SubjectsTestProvider.french),
                new Timeslot(39L, DayOfWeek.WEDNESDAY, LocalTime.of(11, 0), LocalTime.of(11, 30), SubjectsTestProvider.french),
                new Timeslot(40L, DayOfWeek.WEDNESDAY, LocalTime.of(11, 30), LocalTime.of(12, 0), SubjectsTestProvider.french),
                new Timeslot(41L, DayOfWeek.WEDNESDAY, LocalTime.of(14, 0), LocalTime.of(14, 30), SubjectsTestProvider.french),
                new Timeslot(42L, DayOfWeek.WEDNESDAY, LocalTime.of(14, 30), LocalTime.of(15, 0), SubjectsTestProvider.french),
                new Timeslot(43L, DayOfWeek.WEDNESDAY, LocalTime.of(15, 0), LocalTime.of(15, 30), SubjectsTestProvider.french),
                new Timeslot(44L, DayOfWeek.WEDNESDAY, LocalTime.of(15, 30), LocalTime.of(16, 0), SubjectsTestProvider.french),
                new Timeslot(45L, DayOfWeek.WEDNESDAY, LocalTime.of(16, 0), LocalTime.of(16, 30), null),
                new Timeslot(46L, DayOfWeek.WEDNESDAY, LocalTime.of(16, 30), LocalTime.of(17, 0), null),
                new Timeslot(47L, DayOfWeek.WEDNESDAY, LocalTime.of(17, 0), LocalTime.of(17, 30), null),
                new Timeslot(48L, DayOfWeek.WEDNESDAY, LocalTime.of(17, 30), LocalTime.of(18, 0), null)
        ));
    }

    private static Set<Timeslot> thursdayTimeslots() {
        return new HashSet<>(Arrays.asList(
                new Timeslot(49L, DayOfWeek.THURSDAY, LocalTime.of(8, 0), LocalTime.of(8, 30), SubjectsTestProvider.french),
                new Timeslot(50L, DayOfWeek.THURSDAY, LocalTime.of(8, 30), LocalTime.of(9, 0), SubjectsTestProvider.french),
                new Timeslot(51L, DayOfWeek.THURSDAY, LocalTime.of(9, 0), LocalTime.of(9, 30), null),
                new Timeslot(52L, DayOfWeek.THURSDAY, LocalTime.of(9, 30), LocalTime.of(10, 0), null),
                new Timeslot(53L, DayOfWeek.THURSDAY, LocalTime.of(10, 0), LocalTime.of(10, 30), SubjectsTestProvider.french),
                new Timeslot(54L, DayOfWeek.THURSDAY, LocalTime.of(10, 30), LocalTime.of(11, 0), SubjectsTestProvider.french),
                new Timeslot(55L, DayOfWeek.THURSDAY, LocalTime.of(11, 0), LocalTime.of(11, 30), SubjectsTestProvider.french),
                new Timeslot(56L, DayOfWeek.THURSDAY, LocalTime.of(11, 30), LocalTime.of(12, 0), SubjectsTestProvider.french),
                new Timeslot(57L, DayOfWeek.THURSDAY, LocalTime.of(14, 0), LocalTime.of(14, 30), SubjectsTestProvider.english),
                new Timeslot(58L, DayOfWeek.THURSDAY, LocalTime.of(14, 30), LocalTime.of(15, 0), SubjectsTestProvider.english),
                new Timeslot(59L, DayOfWeek.THURSDAY, LocalTime.of(15, 0), LocalTime.of(15, 30), SubjectsTestProvider.english),
                new Timeslot(60L, DayOfWeek.THURSDAY, LocalTime.of(15, 30), LocalTime.of(16, 0), SubjectsTestProvider.english),
                new Timeslot(61L, DayOfWeek.THURSDAY, LocalTime.of(16, 0), LocalTime.of(16, 30), null),
                new Timeslot(62L, DayOfWeek.THURSDAY, LocalTime.of(16, 30), LocalTime.of(17, 0), null),
                new Timeslot(63L, DayOfWeek.THURSDAY, LocalTime.of(17, 0), LocalTime.of(17, 30), null),
                new Timeslot(64L, DayOfWeek.THURSDAY, LocalTime.of(17, 30), LocalTime.of(18, 0), null)
        ));
    }

    private static Set<Timeslot> fridayTimeslots() {
        return new HashSet<>(Arrays.asList(
                new Timeslot(65L, DayOfWeek.FRIDAY, LocalTime.of(8, 0), LocalTime.of(8, 30), null),
                new Timeslot(66L, DayOfWeek.FRIDAY, LocalTime.of(8, 30), LocalTime.of(9, 0), null),
                new Timeslot(67L, DayOfWeek.FRIDAY, LocalTime.of(9, 0), LocalTime.of(9, 30), null),
                new Timeslot(68L, DayOfWeek.FRIDAY, LocalTime.of(9, 30), LocalTime.of(10, 0), null),
                new Timeslot(69L, DayOfWeek.FRIDAY, LocalTime.of(10, 0), LocalTime.of(10, 30), null),
                new Timeslot(70L, DayOfWeek.FRIDAY, LocalTime.of(10, 30), LocalTime.of(11, 0), null),
                new Timeslot(71L, DayOfWeek.FRIDAY, LocalTime.of(11, 0), LocalTime.of(11, 30), null),
                new Timeslot(72L, DayOfWeek.FRIDAY, LocalTime.of(11, 30), LocalTime.of(12, 0), null),
                new Timeslot(73L, DayOfWeek.FRIDAY, LocalTime.of(14, 0), LocalTime.of(14, 30), null),
                new Timeslot(74L, DayOfWeek.FRIDAY, LocalTime.of(14, 30), LocalTime.of(15, 0), null),
                new Timeslot(75L, DayOfWeek.FRIDAY, LocalTime.of(15, 0), LocalTime.of(15, 30), null),
                new Timeslot(76L, DayOfWeek.FRIDAY, LocalTime.of(15, 30), LocalTime.of(16, 0), null),
                new Timeslot(77L, DayOfWeek.FRIDAY, LocalTime.of(16, 0), LocalTime.of(16, 30), null),
                new Timeslot(78L, DayOfWeek.FRIDAY, LocalTime.of(16, 30), LocalTime.of(17, 0), null),
                new Timeslot(79L, DayOfWeek.FRIDAY, LocalTime.of(17, 0), LocalTime.of(17, 30), null),
                new Timeslot(80L, DayOfWeek.FRIDAY, LocalTime.of(17, 30), LocalTime.of(18, 0), null)
        ));
    }
}
