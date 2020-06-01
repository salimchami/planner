package io.edukativ.myskoolin.application.dto;

import io.edukativ.myskoolin.infrastructure.common.enums.EnumDays;
import io.edukativ.myskoolin.infrastructure.common.vo.TimeDbVO;
import io.edukativ.myskoolin.infrastructure.schooling.vo.TimeSlotDbVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * A TimeTableOptions.
 */
public class TimeTableOptionsDTO implements Serializable {

    private Boolean schoolRoomsDistances = true;
    private EnumDays firstWeekDay;
    private BigDecimal surfaceMinPerStudent;
    private TimeDbVO coursesStartTime;
    private TimeDbVO coursesEndTime;
    private List<TimeSlotDbVO> coursesTimeSlots;
    private List<TimeSlotDbVO> extraActivities;
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

    public TimeDbVO getCoursesStartTime() {
        return coursesStartTime;
    }

    public void setCoursesStartTime(TimeDbVO coursesStartTime) {
        this.coursesStartTime = coursesStartTime;
    }

    public TimeDbVO getCoursesEndTime() {
        return coursesEndTime;
    }

    public void setCoursesEndTime(TimeDbVO coursesEndTime) {
        this.coursesEndTime = coursesEndTime;
    }

    public List<TimeSlotDbVO> getCoursesTimeSlots() {
        return coursesTimeSlots;
    }

    public void setCoursesTimeSlots(List<TimeSlotDbVO> coursesTimeSlots) {
        this.coursesTimeSlots = coursesTimeSlots;
    }

    public List<TimeSlotDbVO> getExtraActivities() {
        return extraActivities;
    }

    public void setExtraActivities(List<TimeSlotDbVO> extraActivities) {
        this.extraActivities = extraActivities;
    }

    public Integer getCalendarTimelineDuration() {
        return calendarTimelineDuration;
    }

    public void setCalendarTimelineDuration(Integer calendarTimelineDuration) {
        this.calendarTimelineDuration = calendarTimelineDuration;
    }
}
