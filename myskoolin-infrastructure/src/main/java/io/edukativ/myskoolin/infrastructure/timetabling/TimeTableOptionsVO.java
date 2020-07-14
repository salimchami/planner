package io.edukativ.myskoolin.infrastructure.timetabling;

import io.edukativ.myskoolin.infrastructure.common.enums.EnumDays;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * A TimeTableOptions.
 */
public class TimeTableOptionsVO implements Serializable {

    private Boolean schoolRoomsDistances = true;
    private EnumDays firstWeekDay;
    private BigDecimal surfaceMinPerStudent;
    private TimeVO coursesStartTime;
    private TimeVO coursesEndTime;
    private List<TimeSlotVO> coursesTimeSlots;
    private List<TimeSlotVO> extraActivities;
    private Integer calendarTimelineDuration;

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

    public TimeVO getCoursesStartTime() {
        return coursesStartTime;
    }

    public void setCoursesStartTime(TimeVO coursesStartTime) {
        this.coursesStartTime = coursesStartTime;
    }

    public TimeVO getCoursesEndTime() {
        return coursesEndTime;
    }

    public void setCoursesEndTime(TimeVO coursesEndTime) {
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
