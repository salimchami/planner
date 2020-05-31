package io.edukativ.myskoolin.domain.providers;

import io.edukativ.myskoolin.domain.entity.Client;
import io.edukativ.myskoolin.domain.entity.Grade;
import io.edukativ.myskoolin.domain.vo.EnumDays;
import io.edukativ.myskoolin.domain.vo.EnumSchoolClassNotation;
import io.edukativ.myskoolin.domain.vo.TimeTableOptions;

import java.util.*;
import java.util.stream.Collectors;

public final class GradeProvider {

    public static Grade sixiemeGrade = GradeProvider.grade(Client.defaultTimeTableOptions(),
            GlobalProvider.Grades.GRADE_SIXIEME_ID, "SIXIEME", 7 * 60, 30 * 60);

    public static Grade cinquiemeGrade = GradeProvider.grade(Client.defaultTimeTableOptions(),
            GlobalProvider.Grades.GRADE_CINQUIEME_ID, "CINQUIEME", 7 * 60, 30 * 60);

    public static Grade quatriemeGrade = GradeProvider.grade(Client.defaultTimeTableOptions(),
            GlobalProvider.Grades.GRADE_QUATRIEME_ID, "QUATRIEME", 7 * 60, 30 * 60);

    public static Grade troisiemeGrade = GradeProvider.grade(Client.defaultTimeTableOptions(),
            GlobalProvider.Grades.GRADE_TROISIEME_ID, "TROISIEME", 7 * 60, 30 * 60);

    public static Grade secondeGrade = GradeProvider.grade(Client.defaultTimeTableOptions(),
            GlobalProvider.Grades.GRADE_SECONDE_ID, "SECONDE", 8 * 60, 35 * 60);

    public static Grade premiereGrade = GradeProvider.grade(Client.defaultTimeTableOptions(),
            GlobalProvider.Grades.GRADE_PREMIERE_ID, "PREMIERE", 8 * 60, 35 * 60);

    public static Grade terminaleGrade = GradeProvider.grade(Client.defaultTimeTableOptions(),
            GlobalProvider.Grades.GRADE_TERMINALE_ID, "TERMINALE", 8 * 60, 35 * 60);

    public static Grade grade(TimeTableOptions timeTableOptions, String id, String name, Integer maxMinutesPerDay, Integer maxMinutesPerWeek) {
        return new Grade(id, GlobalProvider.CLIENT_ID, name, 0, EnumSchoolClassNotation.QUARTER,
                "", maxMinutesPerDay, maxMinutesPerWeek, false, Collections.emptyList(), timeTableOptions);
    }

    public static Map<Grade, TimeTableOptions> timeTableOptionsByGrade(TimeTableOptions clientTimeTableOptions) {
        Map<Grade, TimeTableOptions> timeTableOptionsByGrade = new HashMap<>();
        timeTableOptionsByGrade.put(sixiemeGrade, collegeTimeTableOptions(clientTimeTableOptions));
        timeTableOptionsByGrade.put(cinquiemeGrade, collegeTimeTableOptions(clientTimeTableOptions));
        timeTableOptionsByGrade.put(quatriemeGrade, collegeTimeTableOptions(clientTimeTableOptions));
        timeTableOptionsByGrade.put(troisiemeGrade, collegeTimeTableOptions(clientTimeTableOptions));
        return timeTableOptionsByGrade;
    }

    private static TimeTableOptions collegeTimeTableOptions(TimeTableOptions clientTimeTableOptions) {
        TimeTableOptions options = new TimeTableOptions(clientTimeTableOptions);
        options.setCoursesTimeSlots(new ArrayList<>(clientTimeTableOptions.getCoursesTimeSlots()
                .stream()
                .filter(timeSlot -> !timeSlot.getDay().equals(EnumDays.SATURDAY)).collect(Collectors.toList())));
        return options;
    }

    public static List<Grade> allGrades() {
        return Arrays.asList(sixiemeGrade, cinquiemeGrade, quatriemeGrade, troisiemeGrade);
    }
}
