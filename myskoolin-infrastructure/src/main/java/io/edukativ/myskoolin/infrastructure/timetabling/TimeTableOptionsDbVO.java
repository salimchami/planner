package io.edukativ.myskoolin.infrastructure.timetabling;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumDays;
import io.edukativ.myskoolin.infrastructure.common.vo.TimeDbVO;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A TimeTableOptions.
 */
public class TimeTableOptionsDbVO implements Serializable {

    public static final String MONGO_FIELD_SCHOOL_ROOMS_DISTANCES = "school_rooms_distances";
    public static final String MONGO_FIELD_FIRST_WEEK_DAY = "first_week_day";
    public static final String MONGO_FIELD_SURFACE_MIN_PER_STUDENT = "surface_min_per_student";
    public static final String MONGO_FIELD_COURSES_START_TIME = "courses_start_time";
    public static final String MONGO_FIELD_COURSES_END_TIME = "courses_end_time";
    public static final String MONGO_FIELD_DAY_TIME_BREAKS = "day_time_breaks";
    public static final String MONGO_FIELD_ONE_HOUR_DURATION = "one_hour_duration";
    public static final String MONGO_FIELD_COURSES_TIME_SLOTS = "courses_time_slots";
    public static final String MONGO_FIELD_EXTRA_ACTIVITIES_TIME_SLOTS = "extra_activities_time_slots";
    public static final String MONGO_FIELD_CALENDAR_TIMELINE_DURATION = "calendar_timeline_duration";

    @Field(MONGO_FIELD_SCHOOL_ROOMS_DISTANCES)
    private Boolean schoolRoomsDistances = true;

    @Field(MONGO_FIELD_FIRST_WEEK_DAY)
    private EnumDays firstWeekDay;

    @Field(MONGO_FIELD_SURFACE_MIN_PER_STUDENT)
    private BigDecimal surfaceMinPerStudent;

    @Field(MONGO_FIELD_COURSES_START_TIME)
    private TimeDbVO coursesStartTime;

    @Field(MONGO_FIELD_COURSES_END_TIME)
    private TimeDbVO coursesEndTime;

    @Field(MONGO_FIELD_COURSES_TIME_SLOTS)
    private List<TimeSlotDbVO> coursesTimeSlots;

    @Field(MONGO_FIELD_EXTRA_ACTIVITIES_TIME_SLOTS)
    private List<TimeSlotDbVO> extraActivities;

    @Field(MONGO_FIELD_CALENDAR_TIMELINE_DURATION)
    private Integer calendarTimelineDuration;

    public TimeTableOptionsDbVO() {
    }

    public TimeTableOptionsDbVO(Boolean schoolRoomsDistances, EnumDays firstWeekDay, BigDecimal surfaceMinPerStudent,
                                TimeDbVO coursesStartTime, TimeDbVO coursesEndTime,
                                List<TimeSlotDbVO> coursesTimeSlots, List<TimeSlotDbVO> extraActivities,
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

    public void setCoursesTimeSlots(List<TimeSlotDbVO> coursesTimeSlots) {
        this.coursesTimeSlots = coursesTimeSlots;
    }

    public Integer getCalendarTimelineDuration() {
        return calendarTimelineDuration;
    }

    public void setCalendarTimelineDuration(Integer calendarTimelineDuration) {
        this.calendarTimelineDuration = calendarTimelineDuration;
    }

    public List<TimeSlotDbVO> getCoursesTimeSlots() {
        if (coursesTimeSlots == null) {
            coursesTimeSlots = new ArrayList<>();
        }
        return coursesTimeSlots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeTableOptionsDbVO that = (TimeTableOptionsDbVO) o;
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

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "TimeTableOptions{" +
                    "schoolRoomsDistances=" + schoolRoomsDistances +
                    ", firstWeekDay=" + firstWeekDay +
                    ", surfaceMinPerStudent=" + surfaceMinPerStudent +
                    ", coursesStartTime=" + coursesStartTime +
                    ", coursesEndTime=" + coursesEndTime +
                    ", coursesTimeSlots=" + coursesTimeSlots +
                    ", calendarTimelineDuration=" + calendarTimelineDuration +
                    '}';
        }
    }

    public List<EnumDays> clientCoursesDaysList() {
        return coursesTimeSlots.stream()
                .map(TimeSlotDbVO::getDay)
                .distinct()
                .collect(Collectors.toList());
    }

    public Map<EnumDays, List<TimeSlotDbVO>> coursesTimeSlotsByDay() {
        Map<EnumDays, List<TimeSlotDbVO>> clientCoursesTimeSlotsPerDay = new HashMap<>();
        Arrays.stream(EnumDays.values()).forEach(enumDays -> {
            final List<TimeSlotDbVO> timeSlots = coursesTimeSlots
                    .stream()
                    .filter(timeSlot -> enumDays.equals(timeSlot.getDay()))
                    .collect(Collectors.toList());
            if (!timeSlots.isEmpty()) {
                clientCoursesTimeSlotsPerDay.put(enumDays, timeSlots);
            }
        });
        return clientCoursesTimeSlotsPerDay;
    }

    public static List<TimeSlotDbVO> timeSlotsByDay(EnumDays day, List<TimeSlotDbVO> timeSlots) {
        return timeSlots
                .stream()
                .filter(timeSlot -> timeSlot.getDay().equals(day))
                .collect(Collectors.toList());
    }

    public static Map<EnumDays, List<TimeSlotDbVO>> timeSlotsMapByDay(List<TimeSlotDbVO> timeSlots) {
        Map<EnumDays, List<TimeSlotDbVO>> timeSlotsPerDay = new HashMap<>();
        Arrays.stream(EnumDays.values()).forEach(enumDays -> {
            final List<TimeSlotDbVO> filteredTimeSlots = timeSlots
                    .stream()
                    .filter(timeSlot -> enumDays.equals(timeSlot.getDay()))
                    .collect(Collectors.toList());
            if (!filteredTimeSlots.isEmpty()) {
                timeSlotsPerDay.put(enumDays, filteredTimeSlots);
            }
        });
        return timeSlotsPerDay;
    }

    public List<TimeSlotDbVO> getExtraActivities() {
        return extraActivities;
    }

    public void setExtraActivities(List<TimeSlotDbVO> extraActivities) {
        this.extraActivities = extraActivities;
    }
}
