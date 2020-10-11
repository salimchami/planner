package io.edukativ.myskoolin.infrastructure.timetabling;

import java.time.DayOfWeek;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

/**
 * A TimeTableOptions.
 */
public class TimeTableOptionsVO implements Serializable {

    private Boolean schoolRoomsDistances = true;
    private DayOfWeek firstWeekDay;
    private BigDecimal surfaceMinPerStudent;
    private LocalTime coursesStartTime;
    private LocalTime coursesEndTime;
    private List<TimeSlotVO> coursesTimeSlots;
    private List<TimeSlotVO> extraActivities;
    private Integer calendarTimelineDuration;

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

    public List<TimeSlotVO> getCoursesTimeSlots() {
        return coursesTimeSlots;
    }

    public void setCoursesTimeSlots(List<TimeSlotVO> coursesTimeSlots) {
        this.coursesTimeSlots = coursesTimeSlots;
    }

    public List<TimeSlotVO> getExtraActivities() {
        return extraActivities;
    }

    public void setExtraActivities(List<TimeSlotVO> extraActivities) {
        this.extraActivities = extraActivities;
    }

    public Integer getCalendarTimelineDuration() {
        return calendarTimelineDuration;
    }

    public void setCalendarTimelineDuration(Integer calendarTimelineDuration) {
        this.calendarTimelineDuration = calendarTimelineDuration;
    }
}
