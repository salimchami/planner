package io.edukativ.myskoolin.domain.providers;

import io.edukativ.myskoolin.domain.commons.vo.EnumDays;
import io.edukativ.myskoolin.domain.commons.vo.EnumPartsOfDay;
import io.edukativ.myskoolin.domain.timetabling.Time;
import io.edukativ.myskoolin.domain.timetabling.TimeSlot;

import java.util.*;

public class TimeSlotTestProvider {


    public static TimeSlot timeSlot(EnumDays day, int startHour, EnumPartsOfDay startPartsOfDay, int endHour, EnumPartsOfDay endPartsOfDay) {
        return new TimeSlot(
            day,
            new Time(startHour, 0, 0, startPartsOfDay),
            new Time(endHour, 0, 0, endPartsOfDay)
        );
    }

    public static TimeSlot timeSlot(EnumDays day, int startHour, EnumPartsOfDay startPartsOfDay, int endHour, EnumPartsOfDay endPartsOfDay, boolean half) {
        final TimeSlot timeSlot = timeSlot(day, startHour, startPartsOfDay, endHour, endPartsOfDay);
        timeSlot.setHalf(half);
        return timeSlot;
    }

    public static List<TimeSlot> defaultDayRefTimeSlots(EnumDays day) {
        final EnumPartsOfDay pm = EnumPartsOfDay.PM;
        final EnumPartsOfDay am = EnumPartsOfDay.AM;
        return Arrays.asList(
            timeSlot(day, 8, am, 9, am),
            timeSlot(day, 9, am, 10, am),
            timeSlot(day, 10, am, 11, am),
            timeSlot(day, 11, am, 12, pm),
            timeSlot(day, 14, pm, 15, pm),
            timeSlot(day, 15, pm, 16, pm),
            timeSlot(day, 16, pm, 17, pm),
            timeSlot(day, 17, pm, 18, pm)
        );
    }

    public static Map<EnumDays, List<TimeSlot>> defaultRefTimeSlots() {
        return defaultRefTimeSlots(new ArrayList<>(Arrays.asList(EnumDays.values())));
    }

    public static Map<EnumDays, List<TimeSlot>> defaultRefTimeSlots(List<EnumDays> days) {
        Map<EnumDays, List<TimeSlot>> map = new HashMap<>();
        for (EnumDays day : days) {
            List<TimeSlot> timeslots = defaultDayRefTimeSlots(day);
            map.put(day, timeslots);
        }
        return map;
    }
}
