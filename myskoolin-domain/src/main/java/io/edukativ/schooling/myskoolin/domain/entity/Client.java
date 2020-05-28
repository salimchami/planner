package io.edukativ.schooling.myskoolin.domain.entity;

import io.edukativ.schooling.myskoolin.domain.vo.*;

import java.math.BigDecimal;
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
        Arrays.stream(EnumDays.values()).forEach(enumDays -> {
            if (!enumDays.equals(EnumDays.SATURDAY) && !enumDays.equals(EnumDays.SUNDAY) && !enumDays.equals(EnumDays.WEDNESDAY)) {
                addAmCourses(courses, courseTitle, bgColor, fontColorCssClass, enumDays);
                addPmCourses(courses, courseTitle, bgColor, fontColorCssClass, enumDays);
            } else if (enumDays.equals(EnumDays.SATURDAY) || enumDays.equals(EnumDays.WEDNESDAY)) {
                addAmCourses(courses, courseTitle, bgColor, fontColorCssClass, enumDays);
            }
        });
        return courses;
    }

    public static List<TimeSlot> defaultExtraActivities() {
        List<TimeSlot> extraActivities = new ArrayList<>();
        String courseTitle = "timetable.common.extra-activity-period";
        String bgColor = "#cfe0cbff";
        String fontColorCssClass = "#333366";
        Arrays.stream(EnumDays.values()).forEach(enumDays -> {
            if (enumDays.equals(EnumDays.SATURDAY) || enumDays.equals(EnumDays.WEDNESDAY)) {
                addPmExtraActivities(extraActivities, courseTitle, bgColor, fontColorCssClass, enumDays);
            }
        });
        return extraActivities;
    }

    public static TimeTableOptions defaultTimeTableOptions() {
        return new TimeTableOptions(true, EnumDays.MONDAY, BigDecimal.valueOf(2L),
                new Time(8, 0, 0, EnumPartsOfDay.AM),
                new Time(18, 0, 0, EnumPartsOfDay.PM),
                defaultCoursesTimeSlots(), defaultExtraActivities(), 30);
    }

    private static void addPmExtraActivities(List<TimeSlot> courses, String courseTitle, String bgColor, String fontColorCssClass, EnumDays enumDays) {
        courses.add(new TimeSlot(courseTitle, enumDays,
                new Time(13, 0, 0, EnumPartsOfDay.PM),
                new Time(18, 30, 0, EnumPartsOfDay.PM), bgColor, fontColorCssClass));
    }

    private static void addPmCourses(List<TimeSlot> courses, String courseTitle, String bgColor, String fontColorCssClass, EnumDays enumDays) {
        courses.add(new TimeSlot(courseTitle, enumDays,
                new Time(14, 0, 0, EnumPartsOfDay.PM),
                new Time(15, 0, 0, EnumPartsOfDay.PM), bgColor, fontColorCssClass));
        courses.add(new TimeSlot(courseTitle, enumDays,
                new Time(15, 0, 0, EnumPartsOfDay.PM),
                new Time(16, 0, 0, EnumPartsOfDay.PM), bgColor, fontColorCssClass));
        courses.add(new TimeSlot(courseTitle, enumDays,
                new Time(16, 0, 0, EnumPartsOfDay.PM),
                new Time(17, 0, 0, EnumPartsOfDay.PM), bgColor, fontColorCssClass));
        courses.add(new TimeSlot(courseTitle, enumDays,
                new Time(17, 0, 0, EnumPartsOfDay.PM),
                new Time(18, 0, 0, EnumPartsOfDay.PM), bgColor, fontColorCssClass));
    }

    private static void addAmCourses(List<TimeSlot> courses, String courseTitle, String bgColor, String fontColorCssClass, EnumDays enumDays) {
        courses.add(new TimeSlot(courseTitle, enumDays,
                new Time(8, 0, 0, EnumPartsOfDay.AM),
                new Time(9, 0, 0, EnumPartsOfDay.AM), bgColor, fontColorCssClass));
        courses.add(new TimeSlot(courseTitle, enumDays,
                new Time(9, 0, 0, EnumPartsOfDay.AM),
                new Time(10, 0, 0, EnumPartsOfDay.AM), bgColor, fontColorCssClass));
        courses.add(new TimeSlot(courseTitle, enumDays,
                new Time(10, 0, 0, EnumPartsOfDay.AM),
                new Time(11, 0, 0, EnumPartsOfDay.AM), bgColor, fontColorCssClass));
        courses.add(new TimeSlot(courseTitle, enumDays,
                new Time(11, 0, 0, EnumPartsOfDay.AM),
                new Time(12, 0, 0, EnumPartsOfDay.PM), bgColor, fontColorCssClass));
    }
}
