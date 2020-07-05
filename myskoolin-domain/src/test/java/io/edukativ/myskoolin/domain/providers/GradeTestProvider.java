package io.edukativ.myskoolin.domain.providers;

import io.edukativ.myskoolin.domain.commercial.Client;
import io.edukativ.myskoolin.domain.grades.Grade;
import io.edukativ.myskoolin.domain.commons.vo.EnumDays;
import io.edukativ.myskoolin.domain.schoolclasses.EnumSchoolClassNotation;
import io.edukativ.myskoolin.domain.timetabling.TimeTableOptions;

import java.util.*;
import java.util.stream.Collectors;

public final class GradeTestProvider {
    //FIXME : put the instantiation in a constructor because of timeslots ids
    public static Grade sixiemeGrade = GradeTestProvider.grade(Client.defaultTimeTableOptions(),
            GlobalTestProvider.Grades.GRADE_SIXIEME_ID, "SIXIEME", 7 * 60, 30 * 60);

    public static Grade cinquiemeGrade = GradeTestProvider.grade(Client.defaultTimeTableOptions(),
            GlobalTestProvider.Grades.GRADE_CINQUIEME_ID, "CINQUIEME", 7 * 60, 30 * 60);

    public static Grade quatriemeGrade = GradeTestProvider.grade(Client.defaultTimeTableOptions(),
            GlobalTestProvider.Grades.GRADE_QUATRIEME_ID, "QUATRIEME", 7 * 60, 30 * 60);

    public static Grade troisiemeGrade = GradeTestProvider.grade(Client.defaultTimeTableOptions(),
            GlobalTestProvider.Grades.GRADE_TROISIEME_ID, "TROISIEME", 7 * 60, 30 * 60);

    public static Grade secondeGrade = GradeTestProvider.grade(Client.defaultTimeTableOptions(),
            GlobalTestProvider.Grades.GRADE_SECONDE_ID, "SECONDE", 8 * 60, 35 * 60);

    public static Grade premiereGrade = GradeTestProvider.grade(Client.defaultTimeTableOptions(),
            GlobalTestProvider.Grades.GRADE_PREMIERE_ID, "PREMIERE", 8 * 60, 35 * 60);

    public static Grade terminaleGrade = GradeTestProvider.grade(Client.defaultTimeTableOptions(),
            GlobalTestProvider.Grades.GRADE_TERMINALE_ID, "TERMINALE", 8 * 60, 35 * 60);

    public static Grade grade(TimeTableOptions timeTableOptions, String id, String name, Integer maxMinutesPerDay, Integer maxMinutesPerWeek) {
        return new Grade(id, GlobalTestProvider.CLIENT_ID, name, 0, EnumSchoolClassNotation.QUARTER,
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
