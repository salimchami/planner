package io.edukativ.myskoolin.domain.providers;

import io.edukativ.myskoolin.domain.timetabling.TimeSlot;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

public class TimeSlotTestProvider {


    public static TimeSlot timeSlot(Long id, DayOfWeek day, int startHour, int endHour) {
        return new TimeSlot(id,
                day,
                LocalTime.of(startHour, 0, 0),
                LocalTime.of(endHour, 0, 0)
        );
    }

    public static TimeSlot timeSlot(Long id, DayOfWeek day, int startHour, int startMinutes, int endHour, int endMinutes) {
        return new TimeSlot(id,
                day,
                LocalTime.of(startHour, startMinutes),
                LocalTime.of(endHour, endMinutes)
        );
    }

    public static TimeSlot timeSlot(DayOfWeek day, int startHour, int endHour) {
        return new TimeSlot(
                day,
                LocalTime.of(startHour, 0, 0),
                LocalTime.of(endHour, 0, 0)
        );
    }

    public static List<TimeSlot> defaultDayRefTimeSlots(DayOfWeek day) {
        return Arrays.asList(
                timeSlot(day, 8, 9),
                timeSlot(day, 9, 10),
                timeSlot(day, 10, 11),
                timeSlot(day, 11, 12),
                timeSlot(day, 14, 15),
                timeSlot(day, 15, 16),
                timeSlot(day, 16, 17),
                timeSlot(day, 17, 18)
        );
    }

    public static Map<DayOfWeek, List<TimeSlot>> defaultRefTimeSlots() {
        return defaultRefTimeSlots(new ArrayList<>(Arrays.asList(DayOfWeek.values())));
    }

    public static Map<DayOfWeek, List<TimeSlot>> defaultRefTimeSlots(List<DayOfWeek> days) {
        Map<DayOfWeek, List<TimeSlot>> map = new HashMap<>();
        for (DayOfWeek day : days) {
            List<TimeSlot> timeslots = defaultDayRefTimeSlots(day);
            map.put(day, timeslots);
        }
        return map;
    }
}
