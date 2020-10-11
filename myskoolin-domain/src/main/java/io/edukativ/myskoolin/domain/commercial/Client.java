package io.edukativ.myskoolin.domain.commercial;

import io.edukativ.myskoolin.domain.timetabling.TimeSlot;
import io.edukativ.myskoolin.domain.timetabling.TimeTableOptions;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Client entity.
 */
public class Client {

    private String id;
    private TimeTableOptions timeTableOptions;
    private Integer majorityAge;
    private Integer nbMaxOfStudentsPerSchoolClass;
    private ZonedDateTime scholarYearStart;
    private ZonedDateTime scholarYearEnd;
    private Contract contract;

    public Client() {
        this.contract = new Contract();
    }

    public Contract getContract() {
        if (this.contract == null) {
            this.contract = new Contract();
        }
        return contract;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TimeTableOptions getTimeTableOptions() {
        return timeTableOptions;
    }

    public void setTimeTableOptions(TimeTableOptions timeTableOptions) {
        this.timeTableOptions = timeTableOptions;
    }

    public Integer getMajorityAge() {
        return majorityAge;
    }

    public void setMajorityAge(Integer majorityAge) {
        this.majorityAge = majorityAge;
    }

    public Integer getNbMaxOfStudentsPerSchoolClass() {
        return nbMaxOfStudentsPerSchoolClass;
    }

    public void setNbMaxOfStudentsPerSchoolClass(Integer nbMaxOfStudentsPerSchoolClass) {
        this.nbMaxOfStudentsPerSchoolClass = nbMaxOfStudentsPerSchoolClass;
    }

    public ZonedDateTime getScholarYearStart() {
        return scholarYearStart;
    }

    public void setScholarYearStart(ZonedDateTime scholarYearStart) {
        this.scholarYearStart = scholarYearStart;
    }

    public ZonedDateTime getScholarYearEnd() {
        return scholarYearEnd;
    }

    public void setScholarYearEnd(ZonedDateTime scholarYearEnd) {
        this.scholarYearEnd = scholarYearEnd;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                '}';
    }

    public static List<TimeSlot> defaultCoursesTimeSlots() {
        List<TimeSlot> courses = new ArrayList<>();
        String courseTitle = "timetable.common.course-period";
        String bgColor = "#31708f";
        String fontColorCssClass = "#FFFFFF";
        Arrays.stream(DayOfWeek.values()).forEach(dayOfWeek -> {
            if (!dayOfWeek.equals(DayOfWeek.SATURDAY) && !dayOfWeek.equals(DayOfWeek.SUNDAY) && !dayOfWeek.equals(DayOfWeek.WEDNESDAY)) {
                addAmCourses(courses, courseTitle, bgColor, fontColorCssClass, dayOfWeek);
                addPmCourses(courses, courseTitle, bgColor, fontColorCssClass, dayOfWeek);
            } else if (dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.WEDNESDAY)) {
                addAmCourses(courses, courseTitle, bgColor, fontColorCssClass, dayOfWeek);
            }
        });
        return courses;
    }

    public static List<TimeSlot> defaultExtraActivities() {
        List<TimeSlot> extraActivities = new ArrayList<>();
        String courseTitle = "timetable.common.extra-activity-period";
        String bgColor = "#cfe0cbff";
        String fontColorCssClass = "#333366";
        Arrays.stream(DayOfWeek.values()).forEach(enumDays -> {
            if (enumDays.equals(DayOfWeek.SATURDAY) || enumDays.equals(DayOfWeek.WEDNESDAY)) {
                addPmExtraActivities(extraActivities, courseTitle, bgColor, fontColorCssClass, enumDays);
            }
        });
        return extraActivities;
    }

    public static TimeTableOptions defaultTimeTableOptions() {
        return new TimeTableOptions(true, DayOfWeek.MONDAY, BigDecimal.valueOf(2L),
                LocalTime.of(8, 0,  0),
                LocalTime.of(18, 0,  0),
                defaultCoursesTimeSlots(), defaultExtraActivities(), 30);
    }

    private static void addPmExtraActivities(List<TimeSlot> courses, String lessonTitle, String bgColor, String fontColorCssClass, DayOfWeek dayOfWeek) {
        courses.add(new TimeSlot(nextTimeSlotId(courses), lessonTitle, dayOfWeek,
                LocalTime.of(13, 0,  0),
                LocalTime.of(18, 30,  0), bgColor, fontColorCssClass));
    }

    private static Long nextTimeSlotId(List<TimeSlot> timeSlots) {
        return timeSlots.stream().map(TimeSlot::getId).mapToLong(value -> value + 1).max().orElse(0);
    }

    private static void addPmCourses(List<TimeSlot> courses, String lessonTitle, String bgColor, String fontColorCssClass, DayOfWeek enumDays) {
        courses.add(new TimeSlot(nextTimeSlotId(courses), lessonTitle, enumDays,
                LocalTime.of(14, 0,  0),
        LocalTime .of(15, 0,  0), bgColor, fontColorCssClass));

        courses.add(new TimeSlot(nextTimeSlotId(courses), lessonTitle, enumDays,
                LocalTime.of(15, 0,  0),
                LocalTime.of(16, 0,  0), bgColor, fontColorCssClass));
        courses.add(new TimeSlot(nextTimeSlotId(courses), lessonTitle, enumDays,
                LocalTime.of(16, 0,  0),
                LocalTime.of(17, 0,  0), bgColor, fontColorCssClass));
        courses.add(new TimeSlot(nextTimeSlotId(courses), lessonTitle, enumDays,
                LocalTime.of(17, 0,  0),
                LocalTime.of(18, 0,  0), bgColor, fontColorCssClass));
    }

    private static void addAmCourses(List<TimeSlot> courses, String lessonTitle, String bgColor, String fontColorCssClass, DayOfWeek enumDays) {
        courses.add(new TimeSlot(nextTimeSlotId(courses), lessonTitle, enumDays,
                LocalTime.of(8, 0,  0),
                LocalTime.of(9, 0,  0), bgColor, fontColorCssClass));
        courses.add(new TimeSlot(nextTimeSlotId(courses), lessonTitle, enumDays,
                LocalTime.of(9, 0,  0),
                LocalTime.of(10, 0,  0), bgColor, fontColorCssClass));
        courses.add(new TimeSlot(nextTimeSlotId(courses), lessonTitle, enumDays,
                LocalTime.of(10, 0,  0),
                LocalTime.of(11, 0,  0), bgColor, fontColorCssClass));
        courses.add(new TimeSlot(nextTimeSlotId(courses), lessonTitle, enumDays,
                LocalTime.of(11, 0,  0),
                LocalTime.of(12, 0,  0), bgColor, fontColorCssClass));
    }
}
