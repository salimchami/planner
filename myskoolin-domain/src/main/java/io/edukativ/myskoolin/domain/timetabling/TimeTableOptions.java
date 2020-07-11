package io.edukativ.myskoolin.domain.timetabling;

import io.edukativ.myskoolin.domain.commons.vo.EnumDays;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A TimeTableOptions.
 */
public class TimeTableOptions {

    private Boolean schoolRoomsDistances = true;
    private EnumDays firstWeekDay;
    private BigDecimal surfaceMinPerStudent;
    private Time coursesStartTime;
    private Time coursesEndTime;
    private List<TimeSlot> coursesTimeSlots;
    private List<TimeSlot> extraActivities;
    private Integer calendarTimelineDuration;

    public TimeTableOptions() {
    }

    public TimeTableOptions(Boolean schoolRoomsDistances, EnumDays firstWeekDay, BigDecimal surfaceMinPerStudent,
                            Time coursesStartTime, Time coursesEndTime,
                            List<TimeSlot> coursesTimeSlots, List<TimeSlot> extraActivities,
                            Integer calendarTimelineDuration) {
        this.schoolRoomsDistances = schoolRoomsDistances;
        this.firstWeekDay = firstWeekDay;
        this.surfaceMinPerStudent = surfaceMinPerStudent;
        this.coursesStartTime = coursesStartTime;
        this.coursesEndTime = coursesEndTime;
        this.coursesTimeSlots = coursesTimeSlots;
        this.extraActivities = extraActivities;
        this.calendarTimelineDuration = calendarTimelineDuration;
    }

    public TimeTableOptions(TimeTableOptions options) {
        this.schoolRoomsDistances = options.getSchoolRoomsDistances();
        this.firstWeekDay = options.getFirstWeekDay();
        this.surfaceMinPerStudent = options.getSurfaceMinPerStudent();
        final Time optionsCoursesStartTime = options.getCoursesStartTime();
        this.coursesStartTime = new Time(optionsCoursesStartTime.getHour(), optionsCoursesStartTime.getMinutes(), optionsCoursesStartTime.getSeconds(), optionsCoursesStartTime.getPartOfDay());
        final Time optionsCoursesEndTime = options.getCoursesEndTime();
        this.coursesEndTime = new Time(optionsCoursesEndTime.getHour(), optionsCoursesEndTime.getMinutes(), optionsCoursesEndTime.getSeconds(), optionsCoursesEndTime.getPartOfDay());
        this.coursesTimeSlots = new ArrayList<>(options.getCoursesTimeSlots());
        this.extraActivities = new ArrayList<>(options.getExtraActivities());
        this.calendarTimelineDuration = options.getCalendarTimelineDuration();
    }

    public Boolean getSchoolRoomsDistances() {
        return schoolRoomsDistances;
    }

    public void setSchoolRoomsDistances(Boolean schoolRoomsDistances) {
        this.schoolRoomsDistances = schoolRoomsDistances;
    }

    public EnumDays getFirstWeekDay() {
        return firstWeekDay;
    }

    public void setFirstWeekDay(EnumDays firstWeekDay) {
        this.firstWeekDay = firstWeekDay;
    }

    public BigDecimal getSurfaceMinPerStudent() {
        return surfaceMinPerStudent;
    }

    public void setSurfaceMinPerStudent(BigDecimal surfaceMinPerStudent) {
        this.surfaceMinPerStudent = surfaceMinPerStudent;
    }

    public Time getCoursesStartTime() {
        return coursesStartTime;
    }

    public void setCoursesStartTime(Time coursesStartTime) {
        this.coursesStartTime = coursesStartTime;
    }

    public Time getCoursesEndTime() {
        return coursesEndTime;
    }

    public void setCoursesEndTime(Time coursesEndTime) {
        this.coursesEndTime = coursesEndTime;
    }

    public void setCoursesTimeSlots(List<TimeSlot> coursesTimeSlots) {
        this.coursesTimeSlots = coursesTimeSlots;
    }

    public Integer getCalendarTimelineDuration() {
        return calendarTimelineDuration;
    }

    public void setCalendarTimelineDuration(Integer calendarTimelineDuration) {
        this.calendarTimelineDuration = calendarTimelineDuration;
    }

    public List<TimeSlot> getCoursesTimeSlots() {
        if (coursesTimeSlots == null) {
            coursesTimeSlots = new ArrayList<>();
        }
        return coursesTimeSlots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeTableOptions that = (TimeTableOptions) o;
        return Objects.equals(schoolRoomsDistances, that.schoolRoomsDistances) &&
                firstWeekDay == that.firstWeekDay &&
                Objects.equals(surfaceMinPerStudent, that.surfaceMinPerStudent) &&
                Objects.equals(coursesStartTime, that.coursesStartTime) &&
                Objects.equals(coursesEndTime, that.coursesEndTime) &&
                Objects.equals(coursesTimeSlots, that.coursesTimeSlots) &&
                Objects.equals(calendarTimelineDuration, that.calendarTimelineDuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schoolRoomsDistances, firstWeekDay, surfaceMinPerStudent, coursesStartTime, coursesEndTime, coursesTimeSlots, calendarTimelineDuration);
    }

    public Map<EnumDays, List<TimeSlot>> refCoursesByDay(List<EnumDays> withDays) {
        Map<EnumDays, List<TimeSlot>> clientCoursesTimeSlotsPerDay = new EnumMap<>(EnumDays.class);
        withDays.forEach(day -> {
            final List<TimeSlot> timeSlots = coursesTimeSlots
                    .stream()
                    .filter(timeSlot -> day.equals(timeSlot.getDay()))
                    .collect(Collectors.toList());
            if (!timeSlots.isEmpty()) {
                clientCoursesTimeSlotsPerDay.put(day, timeSlots);
            }
        });
        return clientCoursesTimeSlotsPerDay;
    }

    public List<TimeSlot> getExtraActivities() {
        if (this.extraActivities == null) {
            this.extraActivities = new ArrayList<>();
        }
        return extraActivities;
    }

    public void setExtraActivities(List<TimeSlot> extraActivities) {
        this.extraActivities = extraActivities;
    }

    public List<EnumDays> refDays() {
        return coursesTimeSlots.stream().map(TimeSlot::getDay).distinct().collect(Collectors.toList());
    }

    public Map<EnumDays, List<TimeSlot>> coursesTimeSlotsByDay() {
        Map<EnumDays, List<TimeSlot>> clientCoursesTimeSlotsPerDay = new EnumMap<>(EnumDays.class);
        Arrays.stream(EnumDays.values()).forEach(enumDays -> {
            final List<TimeSlot> timeSlots = coursesTimeSlots
                    .stream()
                    .filter(timeSlot -> enumDays.equals(timeSlot.getDay()))
                    .collect(Collectors.toList());
            if (!timeSlots.isEmpty()) {
                clientCoursesTimeSlotsPerDay.put(enumDays, timeSlots);
            }
        });
        return clientCoursesTimeSlotsPerDay;
    }

    public TimeSlot firstWeekTimeSlot() {
        return coursesTimeSlots.stream()
                .filter(timeSlot -> timeSlot.getDay().equals(firstWeekDay))
                .sorted()
                .findFirst()
                .orElseThrow();
    }
}
