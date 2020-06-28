package io.edukativ.myskoolin.infrastructure.timetabling;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumDays;
import io.edukativ.myskoolin.infrastructure.common.vo.TimeDbVO;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Duration;
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
    private EnumDays day;

    @NotNull
    @Field(MONGO_FIELD_START_TIME)
    private TimeDbVO startTime;

    @NotNull
    @Field(MONGO_FIELD_END_TIME)
    private TimeDbVO endTime;

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

    public TimeSlotDbVO(EnumDays day, TimeDbVO startTime, TimeDbVO endTime) {
        this.canceled = false;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        autoAlterable = false;
    }

    public TimeSlotDbVO(EnumDays day, TimeDbVO startTime, TimeDbVO endTime, boolean half) {
        this.half = half;
        this.canceled = false;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        autoAlterable = false;
    }

    public TimeSlotDbVO(String title, EnumDays day, TimeDbVO startTime, TimeDbVO endTime, String bgColor, String fontColorCssClass, boolean autoAlterable) {
        this(day, startTime, endTime);
        this.title = title;
        this.bgColor = bgColor;
        this.fontColorCssClass = fontColorCssClass;
        this.autoAlterable = autoAlterable;
    }

    public TimeSlotDbVO(String title, EnumDays day, TimeDbVO startTime, TimeDbVO endTime, String bgColor, String fontColorCssClass) {
        this(title, day, startTime, endTime, bgColor, fontColorCssClass, false);
    }

    public TimeSlotDbVO(String title, String secondTitle, String comment, Boolean canceled, EnumDays day, TimeDbVO startTime,
                        TimeDbVO endTime, ZonedDateTime date, String bgColor, String fontColorCssClass, Boolean autoAlterable, boolean half) {
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

    public EnumDays getDay() {
        return day;
    }

    public void setDay(EnumDays day) {
        this.day = day;
    }

    public TimeDbVO getStartTime() {
        return startTime;
    }

    public void setStartTime(TimeDbVO startTime) {
        this.startTime = startTime;
    }

    public TimeDbVO getEndTime() {
        return endTime;
    }

    public void setEndTime(TimeDbVO endTime) {
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
        return Duration.between(startTime.toLocalTime(), endTime.toLocalTime()).toMinutes();
    }

    public boolean isOverlapping(EnumDays day, List<TimeSlotDbVO> staticTimeSlotsForDate) {
        if(this.day != day) {
            return false;
        }
        for (TimeSlotDbVO timeSlot : staticTimeSlotsForDate) {
            if (this.hasSameTimes(timeSlot) || this.hasSamePartsOfTime(timeSlot) || this.isInside(timeSlot) || this.isIncluding(timeSlot) ||
                    (this.startTime.toLocalTime().isBefore(timeSlot.getEndTime().toLocalTime())
                            && this.endTime.toLocalTime().isAfter(timeSlot.getEndTime().toLocalTime()))) {
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
        return this.startTime.toLocalTime().isBefore(timeSlot.getEndTime().toLocalTime())
                && this.endTime.toLocalTime().isAfter(timeSlot.getStartTime().toLocalTime());
    }

    private boolean isIncluding(TimeSlotDbVO timeSlot) {
        return this.getStartTime().toLocalTime().isBefore(timeSlot.getStartTime().toLocalTime())
                && this.getEndTime().toLocalTime().isAfter(timeSlot.getEndTime().toLocalTime());
    }

    private boolean isInside(TimeSlotDbVO timeSlot) {
        return this.getStartTime().toLocalTime().isAfter(timeSlot.getStartTime().toLocalTime())
                && this.getEndTime().toLocalTime().isBefore(timeSlot.getEndTime().toLocalTime());
    }

    public boolean hasSamePartsOfTime(TimeSlotDbVO timeSlot) {
        return (this.getStartTime().getHour().equals(timeSlot.getStartTime().getHour())
                && this.getStartTime().getMinutes().equals(timeSlot.getStartTime().getMinutes()))
                ||
                (this.getEndTime().getHour().equals(timeSlot.getEndTime().getHour())
                        && this.getEndTime().getMinutes().equals(timeSlot.getEndTime().getMinutes()));
    }

    public boolean isOverlappingRefTimeBreaks(EnumDays day, List<TimeSlotDbVO> refCourses, TimeDbVO refCoursesStartTime, TimeDbVO refCoursesEndTime) {
        final List<TimeSlotDbVO> timeBreaks = searchForTimeBreaks(day, refCourses, refCoursesStartTime, refCoursesEndTime);
        for (TimeSlotDbVO timeBreak : timeBreaks) {
            if (this.isOverlapping(timeBreak)) {
                return true;
            }
        }
        return false;
    }

    public static List<TimeSlotDbVO> searchForTimeBreaks(EnumDays day, List<TimeSlotDbVO> refCourses, TimeDbVO refCoursesStartTime, TimeDbVO refCoursesEndTime) {
        refCourses.sort(comparing(timeSlot -> timeSlot.getStartTime().toLocalTime()));
        List<TimeSlotDbVO> timeBreaks = new ArrayList<>();
        if (!refCourses.isEmpty() && !refCoursesStartTime.toLocalTime().equals(refCourses.get(0).getStartTime().toLocalTime())) {
            TimeSlotDbVO firstRefCourse = refCourses.get(0);
            timeBreaks.add(new TimeSlotDbVO(day, refCoursesStartTime, firstRefCourse.getEndTime()));
        }
        if (!refCourses.isEmpty() && !refCoursesEndTime.toLocalTime().equals(refCourses.get(refCourses.size() - 1).getEndTime().toLocalTime())) {
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

    public boolean isOverlappingSchoolClassTimeSlots(EnumDays day, List<TimeSlotDbVO> schoolClassTimeSlots) {
        return isOverlapping(day, schoolClassTimeSlots.stream().map(TimeSlotDbVO::new).collect(Collectors.toList()));
    }
}
