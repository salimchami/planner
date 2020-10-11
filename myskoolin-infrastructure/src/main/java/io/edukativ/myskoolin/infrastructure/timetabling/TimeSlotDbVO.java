package io.edukativ.myskoolin.infrastructure.timetabling;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class TimeSlotDbVO implements Serializable {

    public static final String MONGO_FIELD_TITLE = "title";
    public static final String MONGO_FIELD_SECOND_TITLE = "second_title";
    public static final String MONGO_FIELD_COMMENT = "comment";
    public static final String MONGO_FIELD_CANCELED = "canceled";
    public static final String MONGO_FIELD_DAY = "day";
    public static final String MONGO_FIELD_DATE = "date";
    public static final String MONGO_FIELD_POSITION = "position";
    public static final String MONGO_FIELD_TIME_UNIT = "time_unit";
    public static final String MONGO_FIELD_END_TIME = "end_time";
    public static final String MONGO_FIELD_START_TIME = "start_time";
    public static final String MONGO_FIELD_BG_COLOR = "bg_color";
    public static final String MONGO_FIELD_FONT_COLOR = "font_color_css_class";
    public static final String MONGO_FIELD_AUTO_ALTERABLE = "auto_alterable";
    public static final String MONGO_FIELD_HALF = "half";
    private static final String MONGO_FIELD_ID = "id";

    @Field(MONGO_FIELD_ID)
    private String id;

    @NotNull
    @Field(MONGO_FIELD_TITLE)
    private String title;

    @Field(MONGO_FIELD_SECOND_TITLE)
    private String secondTitle;

    @Field(MONGO_FIELD_COMMENT)
    private String comment;

    @NotNull
    @Field(MONGO_FIELD_CANCELED)
    private Boolean canceled;

    //TODO: add intervenant (by type)

    @NotNull
    @Field(MONGO_FIELD_DAY)
    private DayOfWeek day;

    @NotNull
    @Field(MONGO_FIELD_START_TIME)
    private LocalTime startTime;

    @NotNull
    @Field(MONGO_FIELD_END_TIME)
    private LocalTime endTime;

    @Field(MONGO_FIELD_DATE)
    private ZonedDateTime date;

    @NotNull
    @Field(MONGO_FIELD_BG_COLOR)
    private String bgColor;

    @NotNull
    @Field(MONGO_FIELD_FONT_COLOR)
    private String fontColorCssClass;

    @Field(MONGO_FIELD_AUTO_ALTERABLE)
    private Boolean autoAlterable;

    @Field(MONGO_FIELD_HALF)
    private boolean half;

    public TimeSlotDbVO() {
        this.canceled = false;
        autoAlterable = false;
    }

    public TimeSlotDbVO(DayOfWeek day, LocalTime startTime, LocalTime endTime) {
        this.canceled = false;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        autoAlterable = false;
    }

    public TimeSlotDbVO(DayOfWeek day, LocalTime startTime, LocalTime endTime, boolean half) {
        this.half = half;
        this.canceled = false;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        autoAlterable = false;
    }

    public TimeSlotDbVO(String title, DayOfWeek day, LocalTime startTime, LocalTime endTime, String bgColor, String fontColorCssClass, boolean autoAlterable) {
        this(day, startTime, endTime);
        this.title = title;
        this.bgColor = bgColor;
        this.fontColorCssClass = fontColorCssClass;
        this.autoAlterable = autoAlterable;
    }

    public TimeSlotDbVO(String title, DayOfWeek day, LocalTime startTime, LocalTime endTime, String bgColor, String fontColorCssClass) {
        this(title, day, startTime, endTime, bgColor, fontColorCssClass, false);
    }

    public TimeSlotDbVO(String title, String secondTitle, String comment, Boolean canceled, DayOfWeek day, LocalTime startTime,
                        LocalTime endTime, ZonedDateTime date, String bgColor, String fontColorCssClass, Boolean autoAlterable, boolean half) {
        this(title, day, startTime, endTime, bgColor, fontColorCssClass, autoAlterable);
        this.secondTitle = secondTitle;
        this.comment = comment;
        this.canceled = canceled;
        this.date = date;
        this.half = half;
    }

    public TimeSlotDbVO(TimeSlotDbVO timeSlot) {
        this(timeSlot.getTitle(), timeSlot.getDay(), timeSlot.getStartTime(), timeSlot.getEndTime(), timeSlot.getBgColor(), timeSlot.getFontColorCssClass(), timeSlot.getAutoAlterable());
        this.secondTitle = timeSlot.getSecondTitle();
        this.comment = timeSlot.getComment();
        this.canceled = timeSlot.getCanceled();
        this.date = timeSlot.getDate();
        this.half = timeSlot.getHalf();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isHalf() {
        return half;
    }

    public void setHalf(boolean half) {
        this.half = half;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getFontColorCssClass() {
        return fontColorCssClass;
    }

    public void setFontColorCssClass(String fontColorCssClass) {
        this.fontColorCssClass = fontColorCssClass;
    }

    public Boolean getAutoAlterable() {
        return autoAlterable;
    }

    public void setAutoAlterable(Boolean autoAlterable) {
        this.autoAlterable = autoAlterable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeSlotDbVO timeSlot = (TimeSlotDbVO) o;
        return day.equals(timeSlot.day) &&
                startTime.equals(timeSlot.startTime) &&
                endTime.equals(timeSlot.endTime) &&
                date.equals(timeSlot.date);
    }

    public boolean hasSameTimes(TimeSlotDbVO timeSlot) {
        return this.startTime.equals(timeSlot.getStartTime()) || this.endTime.equals(timeSlot.getEndTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, startTime, endTime, date);
    }

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "TimeSlot{" +
                    ", title='" + title + '\'' +
                    ", comment='" + comment + '\'' +
                    ", canceled=" + canceled +
                    ", day=" + day +
                    ", startTime=" + startTime +
                    ", endTime=" + endTime +
                    ", date=" + date +
                    ", bgColor='" + bgColor + '\'' +
                    ", fontColor='" + fontColorCssClass + '\'' +
                    ", autoAlterable=" + autoAlterable +
                    '}';
        }
    }

    public String getSecondTitle() {
        return secondTitle;
    }

    public void setSecondTitle(String secondTitle) {
        this.secondTitle = secondTitle;
    }


    public Long durationInMinutes() {
        return Duration.between(startTime, endTime).toMinutes();
    }

    public boolean isOverlapping(DayOfWeek day, List<TimeSlotDbVO> staticTimeSlotsForDate) {
        if(this.day != day) {
            return false;
        }
        for (TimeSlotDbVO timeSlot : staticTimeSlotsForDate) {
            if (this.hasSameTimes(timeSlot) || this.hasSamePartsOfTime(timeSlot) || this.isInside(timeSlot) || this.isIncluding(timeSlot) ||
                    (this.startTime.isBefore(timeSlot.getEndTime())
                            && this.endTime.isAfter(timeSlot.getEndTime()))) {
                return true;
            }
        }
        return false;
    }

    public boolean isOverlapping(TimeSlotDbVO timeSlot) {
        if(this.day != timeSlot.getDay()) {
            return false;
        }
        final boolean sameTimes = hasSameTimes(timeSlot);
        final boolean inside = isInside(timeSlot);
        final boolean including = isIncluding(timeSlot);
        final boolean standardOverlapping = standardOverlapping(timeSlot);
        return sameTimes || inside || including || standardOverlapping;
    }

    private boolean standardOverlapping(TimeSlotDbVO timeSlot) {
        return this.startTime.isBefore(timeSlot.getEndTime())
                && this.endTime.isAfter(timeSlot.getStartTime());
    }

    private boolean isIncluding(TimeSlotDbVO timeSlot) {
        return this.getStartTime().isBefore(timeSlot.getStartTime())
                && this.getEndTime().isAfter(timeSlot.getEndTime());
    }

    private boolean isInside(TimeSlotDbVO timeSlot) {
        return this.getStartTime().isAfter(timeSlot.getStartTime())
                && this.getEndTime().isBefore(timeSlot.getEndTime());
    }

    public boolean hasSamePartsOfTime(TimeSlotDbVO timeSlot) {
        return (this.getStartTime().getHour() == timeSlot.getStartTime().getHour()
                && this.getStartTime().getMinute() == timeSlot.getStartTime().getMinute())
                ||
                (this.getEndTime().getHour() == timeSlot.getEndTime().getHour()
                        && this.getEndTime().getMinute() == timeSlot.getEndTime().getMinute());
    }

    public boolean isOverlappingRefTimeBreaks(DayOfWeek day, List<TimeSlotDbVO> refCourses, LocalTime refCoursesStartTime, LocalTime refCoursesEndTime) {
        final List<TimeSlotDbVO> timeBreaks = searchForTimeBreaks(day, refCourses, refCoursesStartTime, refCoursesEndTime);
        for (TimeSlotDbVO timeBreak : timeBreaks) {
            if (this.isOverlapping(timeBreak)) {
                return true;
            }
        }
        return false;
    }

    public static List<TimeSlotDbVO> searchForTimeBreaks(DayOfWeek day, List<TimeSlotDbVO> refCourses, LocalTime refCoursesStartTime, LocalTime refCoursesEndTime) {
        refCourses.sort(comparing(timeSlot -> timeSlot.getStartTime()));
        List<TimeSlotDbVO> timeBreaks = new ArrayList<>();
        if (!refCourses.isEmpty() && !refCoursesStartTime.equals(refCourses.get(0).getStartTime())) {
            TimeSlotDbVO firstRefCourse = refCourses.get(0);
            timeBreaks.add(new TimeSlotDbVO(day, refCoursesStartTime, firstRefCourse.getEndTime()));
        }
        if (!refCourses.isEmpty() && !refCoursesEndTime.equals(refCourses.get(refCourses.size() - 1).getEndTime())) {
            TimeSlotDbVO lastRefCourse = refCourses.get(refCourses.size() - 1);
            timeBreaks.add(new TimeSlotDbVO(day, lastRefCourse.getEndTime(), refCoursesEndTime));
        }
        for (int i = 0; i < refCourses.size(); i++) {
            TimeSlotDbVO firstTimeSlot = refCourses.get(i);
            if (refCourses.size() >= i + 2) {
                TimeSlotDbVO nextTimeSlot = refCourses.get(i + 1);
                if (!nextTimeSlot.getStartTime().equals(firstTimeSlot.getEndTime())) {
                    timeBreaks.add(new TimeSlotDbVO(day, firstTimeSlot.getEndTime(), nextTimeSlot.getStartTime()));
                }
            }
        }
        return timeBreaks;
    }

    public Boolean getHalf() {
        return half;
    }

    public void setHalf(Boolean half) {
        this.half = half;
    }

    public boolean isOverlappingSchoolClassTimeSlots(DayOfWeek day, List<TimeSlotDbVO> schoolClassTimeSlots) {
        return isOverlapping(day, schoolClassTimeSlots.stream().map(TimeSlotDbVO::new).collect(Collectors.toList()));
    }
}
