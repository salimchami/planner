package io.edukativ.myskoolin.domain.timetabling;

import io.edukativ.myskoolin.domain.commons.vo.EnumDays;
import org.optaplanner.core.api.domain.lookup.PlanningId;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

import static java.util.Comparator.comparing;

public class TimeSlot implements Comparable<TimeSlot> {

    @PlanningId
    private Long id;
    private String title;
    private String secondTitle;
    private String comment;
    private Boolean canceled;
    private EnumDays day;
    private Time startTime;
    private Time endTime;
    private ZonedDateTime date;
    private String bgColor;
    private String fontColorCssClass;
    private Boolean autoAlterable;
    private boolean half;

    public TimeSlot() {
    }

    public TimeSlot(Long id, String courseTitle, EnumDays day, Time startTime, Time endTime, String bgColor, String fontColorCssClass) {
        this.id = id;
        this.title = courseTitle;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bgColor = bgColor;
        this.fontColorCssClass = fontColorCssClass;
    }

    public TimeSlot(EnumDays day, Time startTime, Time endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public TimeSlot(Long id, EnumDays day, Time startTime, Time endTime) {
        this.id = id;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSecondTitle() {
        return secondTitle;
    }

    public void setSecondTitle(String secondTitle) {
        this.secondTitle = secondTitle;
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

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
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

    public boolean isHalf() {
        return half;
    }

    public void setHalf(boolean half) {
        this.half = half;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeSlot timeSlot = (TimeSlot) o;
        return Objects.equals(id, timeSlot.id) &&
                day.equals(timeSlot.day) &&
                startTime.equals(timeSlot.startTime) &&
                endTime.equals(timeSlot.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, day, startTime, endTime, date);
    }

    //####################################################################################################
    //####################################################################################################
    //####################################################################################################
    //####################################################################################################

    public boolean hasSameTimes(TimeSlot timeSlot) {
        return this.startTime.equals(timeSlot.getStartTime()) || this.endTime.equals(timeSlot.getEndTime());
    }

    public Long durationInMinutes() {
        return Duration.between(startTime.toLocalTime(), endTime.toLocalTime()).toMinutes();
    }

    //
//    public boolean isOverlapping(EnumDays day, List<TimeSlot> staticTimeSlotsForDate) {
//        if(this.day != day) {
//            return false;
//        }
//        for (TimeSlot timeSlot : staticTimeSlotsForDate) {
//            if (this.hasSameTimes(timeSlot) || this.hasSamePartsOfTime(timeSlot) || this.isInside(timeSlot) || this.isIncluding(timeSlot) ||
//                    (this.startTime.toLocalTime().isBefore(timeSlot.getEndTime().toLocalTime())
//                            && this.endTime.toLocalTime().isAfter(timeSlot.getEndTime().toLocalTime()))) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public boolean isOverlapping(List<TimeSlot> timeSlots) {
//        for (TimeSlot timeSlot : timeSlots) {
//            if(this.isOverlapping(timeSlot)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
    public boolean isOverlapping(TimeSlot timeSlot) {
        if (this.day != timeSlot.getDay()) {
            return false;
        }
        final boolean sameTimes = hasSameTimes(timeSlot);
        final boolean inside = isInside(timeSlot);
        final boolean including = isIncluding(timeSlot);
        final boolean standardOverlapping = standardOverlapping(timeSlot);
        return sameTimes || inside || including || standardOverlapping;
    }

    public Long overlappingGap(TimeSlot timeSlot) {
        if (this.day == timeSlot.getDay()) {
            if (isIncluding(timeSlot)) {
                return Duration.between(this.startTime.toLocalTime(), timeSlot.startTime.toLocalTime()).toMinutes()
                        + Duration.between(this.endTime.toLocalTime(), timeSlot.endTime.toLocalTime()).toMinutes();
            } else if (timeSlot.isIncluding(this)) {
                return Duration.between(timeSlot.startTime.toLocalTime(), this.startTime.toLocalTime()).toMinutes()
                        + Duration.between(timeSlot.endTime.toLocalTime(), this.endTime.toLocalTime()).toMinutes();
            } else if (isBefore(timeSlot)) {
                return Duration.between(this.startTime.toLocalTime(), timeSlot.startTime.toLocalTime()).toMinutes();
            } else if (timeSlot.isBefore(this)) {
                return Duration.between(timeSlot.startTime.toLocalTime(), this.startTime.toLocalTime()).toMinutes();
            } else if(this.hasSameTimes(timeSlot)) {
                return this.durationInMinutes();
            }
        }
        return 0L;
    }

    private boolean standardOverlapping(TimeSlot timeSlot) {
        return this.startTime.toLocalTime().isBefore(timeSlot.getEndTime().toLocalTime())
                && this.endTime.toLocalTime().isAfter(timeSlot.getStartTime().toLocalTime());
    }

    private boolean isIncluding(TimeSlot timeSlot) {
        return this.getStartTime().toLocalTime().isBefore(timeSlot.getStartTime().toLocalTime())
                && this.getEndTime().toLocalTime().isAfter(timeSlot.getEndTime().toLocalTime());
    }

    //
    private boolean isInside(TimeSlot timeSlot) {
        return this.getStartTime().toLocalTime().isAfter(timeSlot.getStartTime().toLocalTime())
                && this.getEndTime().toLocalTime().isBefore(timeSlot.getEndTime().toLocalTime());
    }

    //    public boolean hasSamePartsOfTime(TimeSlot timeSlot) {
//        return (this.getStartTime().getHour().equals(timeSlot.getStartTime().getHour())
//                && this.getStartTime().getMinutes().equals(timeSlot.getStartTime().getMinutes()))
//                ||
//                (this.getEndTime().getHour().equals(timeSlot.getEndTime().getHour())
//                        && this.getEndTime().getMinutes().equals(timeSlot.getEndTime().getMinutes()));
//    }
//
//    public boolean isOverlappingRefTimeBreaks(EnumDays day, List<TimeSlot> refCourses, Time refCoursesStartTime, Time refCoursesEndTime) {
//        final List<TimeSlot> timeBreaks = searchForTimeBreaks(day, refCourses, refCoursesStartTime, refCoursesEndTime);
//        for (TimeSlot timeBreak : timeBreaks) {
//            if (this.isOverlapping(timeBreak)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public static List<TimeSlot> searchForTimeBreaks(EnumDays day, List<TimeSlot> refCourses, Time refCoursesStartTime, Time refCoursesEndTime) {
//        refCourses.sort(comparing(timeSlot -> timeSlot.getStartTime().toLocalTime()));
//        List<TimeSlot> timeBreaks = new ArrayList<>();
//        if (!refCourses.isEmpty() && !refCoursesStartTime.toLocalTime().equals(refCourses.get(0).getStartTime().toLocalTime())) {
//            TimeSlot firstRefCourse = refCourses.get(0);
//            timeBreaks.add(new TimeSlot(day, refCoursesStartTime, firstRefCourse.getEndTime()));
//        }
//        if (!refCourses.isEmpty() && !refCoursesEndTime.toLocalTime().equals(refCourses.get(refCourses.size() - 1).getEndTime().toLocalTime())) {
//            TimeSlot lastRefCourse = refCourses.get(refCourses.size() - 1);
//            timeBreaks.add(new TimeSlot(day, lastRefCourse.getEndTime(), refCoursesEndTime));
//        }
//        for (int i = 0; i < refCourses.size(); i++) {
//            TimeSlot firstTimeSlot = refCourses.get(i);
//            if (refCourses.size() >= i + 2) {
//                TimeSlot nextTimeSlot = refCourses.get(i + 1);
//                if (!nextTimeSlot.getStartTime().equals(firstTimeSlot.getEndTime())) {
//                    timeBreaks.add(new TimeSlot(day, firstTimeSlot.getEndTime(), nextTimeSlot.getStartTime()));
//                }
//            }
//        }
//        return timeBreaks;
//    }
//
    @Override
    public int compareTo(TimeSlot o) {
        return comparing(TimeSlot::getDay).thenComparing(TimeSlot::getStartTime).compare(this, o);
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "hashcode=" + hashCode() +
                "day=" + day +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isOverlappingTimeSlots(EnumDays day, List<TimeSlot> timeSlots) {
        return timeSlots.stream()
                .filter(timeSlot -> timeSlot.day.equals(day))
                .anyMatch(this::isOverlapping);
    }

    TimeSlot next(List<TimeSlot> timeSlots) {
        return timeSlots.stream()
                .sorted()
                .filter(this::isBefore)
                .findFirst().orElseThrow();
    }

    boolean isBefore(TimeSlot timeSlot) {
        final ZonedDateTime now = ZonedDateTime.now();
        final Instant instant1 = this.getStartTime().toInstant(DayOfWeek.valueOf(this.getDay().name()), now);
        final Instant instant2 = timeSlot.getStartTime().toInstant(DayOfWeek.valueOf(timeSlot.getDay().name()), now);
        return instant1.isBefore(instant2);
    }

    public boolean isConsecutive(TimeSlot timeSlot) {
        return this.startTime.equals(timeSlot.endTime) || this.endTime.equals(timeSlot.startTime);
    }
}
