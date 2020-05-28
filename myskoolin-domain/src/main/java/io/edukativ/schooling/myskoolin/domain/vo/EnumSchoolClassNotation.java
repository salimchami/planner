package io.edukativ.schooling.myskoolin.domain.vo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum EnumSchoolClassNotation {
    MONTHLY(1, "global.enums.school-class-notation.monthly", 9),
    QUARTER(2, "global.enums.school-class-notation.quarter", 3),
    SEMESTER(3, "global.enums.school-class-notation.semester", 2),
    ANNUAL(4, "global.enums.school-class-notation.annual", 1);

    private String code;
    private Integer position;
    private Integer defaultPeriodsNbPerYear;

    EnumSchoolClassNotation(Integer position, String code, Integer periodsNbPerYear) {
        this.position = position;
        this.code = code;
        this.defaultPeriodsNbPerYear = periodsNbPerYear;
    }

    public String getCode() {
        return code;
    }

    public Integer getPosition() {
        return position;
    }

    public Integer getDefaultPeriodsNbPerYear() {
        return defaultPeriodsNbPerYear;
    }

    public static EnumSchoolClassNotation fromCode(String code) {
        return Arrays.stream(EnumSchoolClassNotation.values())
            .filter(type -> code.equals(type.getCode()))
            .findFirst()
            .orElse(null);
    }

    public static List<String> names(List<EnumSchoolClassNotation> schoolClassNotations) {
        return schoolClassNotations.stream()
            .map(EnumSchoolClassNotation::name)
            .collect(Collectors.toList());
    }

    public static List<String> names() {
        return Stream.of(EnumSchoolClassNotation.values())
            .map(EnumSchoolClassNotation::name)
            .collect(Collectors.toList());
    }
}
