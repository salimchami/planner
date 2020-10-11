package io.edukativ.myskoolin.domain.timetabling;


import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A TimeTableOptions.
 */
public class TimeTableOptions {

    private Boolean schoolRoomsDistances = true;
    private DayOfWeek firstWeekDay;
    private BigDecimal surfaceMinPerStudent;
    private LocalTime coursesStartTime;
    private LocalTime coursesEndTime;
    private List<TimeSlot> coursesTimeSlots;
    private List<TimeSlot> extraActivities;
    private Integer calendarTimelineDuration;

    public TimeTableOptions() {
    }

    public TimeTableOptions(Boolean schoolRoomsDistances, DayOfWeek firstWeekDay, BigDecimal surfaceMinPerStudent,
                            LocalTime coursesStartTime, LocalTime coursesEndTime,
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
        this.coursesStartTime = options.getCoursesStartTime();
        this.coursesEndTime = options.getCoursesEndTime();
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

    public DayOfWeek getFirstWeekDay() {
        return firstWeekDay;
    }

    public void setFirstWeekDay(DayOfWeek firstWeekDay) {
        this.firstWeekDay = firstWeekDay;
    }

    public BigDecimal getSurfaceMinPerStudent() {
        return surfaceMinPerStudent;
    }

    public void setSurfaceMinPerStudent(BigDecimal surfaceMinPerStudent) {
        this.surfaceMinPerStudent = surfaceMinPerStudent;
    }

    public LocalTime getCoursesStartTime() {
        return coursesStartTime;
    }

    public void setCoursesStartTime(LocalTime coursesStartTime) {
        this.coursesStartTime = coursesStartTime;
    }

    public LocalTime getCoursesEndTime() {
        return coursesEndTime;
    }

    public void setCoursesEndTime(LocalTime coursesEndTime) {
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

    public Map<DayOfWeek, List<TimeSlot>> refCoursesByDay(List<DayOfWeek> withDays) {
        Map<DayOfWeek, List<TimeSlot>> clientCoursesTimeSlotsPerDay = new EnumMap<>(DayOfWeek.class);
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

    public List<DayOfWeek> refDays() {
        return coursesTimeSlots.stream().map(TimeSlot::getDay).distinct().collect(Collectors.toList());
    }

    public Map<DayOfWeek, List<TimeSlot>> coursesTimeSlotsByDay() {
        Map<DayOfWeek, List<TimeSlot>> clientCoursesTimeSlotsPerDay = new EnumMap<>(DayOfWeek.class);
        Arrays.stream(DayOfWeek.values()).forEach(enumDays -> {
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
