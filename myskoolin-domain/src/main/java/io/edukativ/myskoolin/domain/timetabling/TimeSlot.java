package io.edukativ.myskoolin.domain.timetabling;

import org.optaplanner.core.api.domain.lookup.PlanningId;

import java.time.*;
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
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;
    private ZonedDateTime date;
    private String bgColor;
    private String fontColorCssClass;
    private Boolean autoAlterable;
    private boolean half;

    public TimeSlot() {
    }

    public TimeSlot(Long id, String courseTitle, DayOfWeek day, LocalTime startTime, LocalTime endTime, String bgColor, String fontColorCssClass) {
        this.id = id;
        this.title = courseTitle;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bgColor = bgColor;
        this.fontColorCssClass = fontColorCssClass;
    }

    public TimeSlot(DayOfWeek day, LocalTime startTime, LocalTime endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public TimeSlot(Long id, DayOfWeek day, LocalTime startTime, LocalTime endTime) {
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
        return this.startTime.equals(timeSlot.startTime) || this.endTime.equals(timeSlot.endTime);
    }

    public Long durationInMinutes() {
        return Duration.between(startTime, endTime).toMinutes();
    }

    //
//    public boolean isOverlapping(EnumDays day, List<TimeSlot> staticTimeSlotsForDate) {
//        if(this.day != day) {
//            return false;
//        }
//        for (TimeSlot timeSlot : staticTimeSlotsForDate) {
//            if (this.hasSameTimes(timeSlot) || this.hasSamePartsOfTime(timeSlot) || this.isInside(timeSlot) || this.isIncluding(timeSlot) ||
//                    (this.startTime.isBefore(timeSlot.endTime)
//                            && this.endTime.isAfter(timeSlot.endTime))) {
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
        if (this.day != timeSlot.day) {
            return false;
        }
        final boolean sameTimes = hasSameTimes(timeSlot);
        final boolean inside = isInside(timeSlot);
        final boolean including = isIncluding(timeSlot);
        final boolean standardOverlapping = standardOverlapping(timeSlot);
        return sameTimes || inside || including || standardOverlapping;
    }

    public int overlappingPenalty(TimeSlot timeSlot) {
        return overlappingGap(timeSlot).intValue() / 2;
    }

    public Long overlappingGap(TimeSlot timeSlot) {
        if (this.day == timeSlot.day) {
            if (isIncluding(timeSlot)) {
                return Duration.between(this.startTime, timeSlot.startTime).toMinutes()
                        + Duration.between(this.endTime, timeSlot.endTime).toMinutes();
            } else if (timeSlot.isIncluding(this)) {
                return Duration.between(timeSlot.startTime, this.startTime).toMinutes()
                        + Duration.between(timeSlot.endTime, this.endTime).toMinutes();
            } else if (isBefore(timeSlot)) {
                return Duration.between(this.startTime, timeSlot.startTime).toMinutes();
            } else if (timeSlot.isBefore(this)) {
                return Duration.between(timeSlot.startTime, this.startTime).toMinutes();
            } else if(!this.id.equals(timeSlot.id) && hasSameTimes(timeSlot)) {
                return durationInMinutes();
            }
            return 0L;
        }
        return 0L;
    }

    private boolean standardOverlapping(TimeSlot timeSlot) {
        return this.startTime.isBefore(timeSlot.endTime)
                && this.endTime.isAfter(timeSlot.startTime);
    }

    private boolean isIncluding(TimeSlot timeSlot) {
        return this.startTime.isBefore(timeSlot.startTime)
                && this.endTime.isAfter(timeSlot.endTime);
    }

    //
    private boolean isInside(TimeSlot timeSlot) {
        return this.startTime.isAfter(timeSlot.startTime)
                && this.endTime.isBefore(timeSlot.endTime);
    }

    //    public boolean hasSamePartsOfTime(TimeSlot timeSlot) {
//        return (this.startTime.getHour().equals(timeSlot.startTime.getHour())
//                && this.startTime.getMinutes().equals(timeSlot.startTime.getMinutes()))
//                ||
//                (this.endTime.getHour().equals(timeSlot.endTime.getHour())
//                        && this.endTime.getMinutes().equals(timeSlot.endTime.getMinutes()));
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
//        refCourses.sort(comparing(timeSlot -> timeSlot.startTime));
//        List<TimeSlot> timeBreaks = new ArrayList<>();
//        if (!refCourses.isEmpty() && !refCoursesStartTime.equals(refCourses.get(0).startTime)) {
//            TimeSlot firstRefCourse = refCourses.get(0);
//            timeBreaks.add(new TimeSlot(day, refCoursesStartTime, firstRefCourse.endTime));
//        }
//        if (!refCourses.isEmpty() && !refCoursesEndTime.equals(refCourses.get(refCourses.size() - 1).endTime)) {
//            TimeSlot lastRefCourse = refCourses.get(refCourses.size() - 1);
//            timeBreaks.add(new TimeSlot(day, lastRefCourse.endTime, refCoursesEndTime));
//        }
//        for (int i = 0; i < refCourses.size(); i++) {
//            TimeSlot firstTimeSlot = refCourses.get(i);
//            if (refCourses.size() >= i + 2) {
//                TimeSlot nextTimeSlot = refCourses.get(i + 1);
//                if (!nextTimeSlot.startTime.equals(firstTimeSlot.endTime)) {
//                    timeBreaks.add(new TimeSlot(day, firstTimeSlot.endTime, nextTimeSlot.startTime));
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

    public boolean isOverlappingTimeSlots(DayOfWeek day, List<TimeSlot> timeSlots) {
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
        final Instant instant1 = LocalDateTime.of(now.getYear(), now.getMonth(), day.getValue(), startTime.getHour(), startTime.getMinute()).toInstant(ZoneOffset.UTC);
        final Instant instant2 = LocalDateTime.of(now.getYear(), now.getMonth(), timeSlot.day.getValue(), timeSlot.startTime.getHour(), timeSlot.startTime.getMinute()).toInstant(ZoneOffset.UTC);
        return instant1.isBefore(instant2);
    }

    public boolean isConsecutive(TimeSlot timeSlot) {
        return this.startTime.equals(timeSlot.endTime) || this.endTime.equals(timeSlot.startTime);
    }
}
