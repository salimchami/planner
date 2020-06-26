package io.edukativ.myskoolin.domain.providers.schoolclasses;

import io.edukativ.myskoolin.domain.commons.exceptions.NotFoundException;
import io.edukativ.myskoolin.domain.entity.Grade;
import io.edukativ.myskoolin.domain.entity.SchoolClass;
import io.edukativ.myskoolin.domain.entity.Subject;
import io.edukativ.myskoolin.domain.providers.GlobalTestProvider;
import io.edukativ.myskoolin.domain.providers.GradeTestProvider;
import io.edukativ.myskoolin.domain.providers.subjects.*;
import io.edukativ.myskoolin.domain.vo.EnumSchoolClassNotation;
import io.edukativ.myskoolin.domain.vo.GradeSerie;
import io.edukativ.myskoolin.domain.vo.Teacher;
import io.edukativ.myskoolin.domain.vo.TeachersBySubject;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SchoolClassTestProvider {

    private static Random randomGenerator = new Random();

    public static SchoolClass schoolClass(String id, int coursesStartDateYear, int coursesStartDateMonth, int coursesStartDateDay,
                                          int coursesEndDateYear, int coursesEndDateMonth, int coursesEndDateDay,
                                          String name, Grade grade, GradeSerie gradeSerie,
                                          List<TeachersBySubject> teachersBySubjects) {
        return new SchoolClass(id, GlobalTestProvider.CLIENT_ID,
                ZonedDateTime.of(coursesStartDateYear, coursesStartDateMonth, coursesStartDateDay, 8, 0, 0, 0, ZoneId.systemDefault()),
                ZonedDateTime.of(coursesEndDateYear, coursesEndDateMonth, coursesEndDateDay, 8, 0, 0, 0, ZoneId.systemDefault()),
                null, "", name,
                false, null, grade, gradeSerie, new ArrayList<>(), null, EnumSchoolClassNotation.QUARTER, teachersBySubjects);
    }

    public static SchoolClass schoolClass(String id, String name, Grade grade, GradeSerie gradeSerie,
                                          List<TeachersBySubject> teachersBySubjects) {
        return schoolClass(id, 2020, 9, 1, 2021, 7, 8,
                name, grade, gradeSerie, teachersBySubjects);
    }

    public static Teacher teacherById(List<Teacher> teachers, String id) {
        return teachers.stream().filter(user -> user.getId().equals(id)).findFirst()
                .orElseThrow(() -> new NotFoundException("teacher not found"));
    }

    public static String randomTeacherIdByIds(List<Teacher> teachers, List<String> ids) {
        int index = randomGenerator.nextInt(ids.size());
        return teacherById(teachers, ids.get(index)).getId();
    }

    public static List<SchoolClass> generateTroisieme(List<Teacher> allTeachers, Grade grade, Integer nb) {
        List<SchoolClass> schoolClasses = new ArrayList<>();
        for (int i = 1; i <= nb; i++) {
            schoolClasses.add(SchoolClassTroisiemeTestProvider.generate("Troisieme" + i, grade, allTeachers, "Troisième " + i));
        }
        return schoolClasses;
    }

    public static List<SchoolClass> generateQuatrieme(List<Teacher> allTeachers, Grade grade, Integer nb) {
        List<SchoolClass> schoolClasses = new ArrayList<>();
        for (int i = 1; i <= nb; i++) {
            schoolClasses.add(SchoolClassQuatriemeTestProvider.generate("Quatrieme" + i, grade, allTeachers, "Quatrième " + i));
        }
        return schoolClasses;
    }

    public static List<SchoolClass> generateCinquieme(List<Teacher> allTeachers, Grade grade, Integer nb) {
        List<SchoolClass> schoolClasses = new ArrayList<>();
        for (int i = 1; i <= nb; i++) {
            schoolClasses.add(SchoolClassCinquiemeTestProvider.generate("Cinquieme" + i, grade, allTeachers, "Cinquième " + i));
        }
        return schoolClasses;
    }

    public static List<SchoolClass> generateSixieme(List<Teacher> allTeachers, Grade grade, Integer nb) {
        List<SchoolClass> schoolClasses = new ArrayList<>();
        for (int i = 1; i <= nb; i++) {
            schoolClasses.add(SchoolClassSixiemeTestProvider.generate("Sixieme" + i, grade, allTeachers, "Sixième " + i));
        }
        return schoolClasses;
    }

    public static List<SchoolClass> schoolClasses(List<Teacher> teachers, int sixiemes, int cinquiemes, int quatriemes, int troisiemes) {
        return Stream.of(
                SchoolClassTestProvider.generateSixieme(teachers, GradeTestProvider.sixiemeGrade, sixiemes),
                SchoolClassTestProvider.generateCinquieme(teachers, GradeTestProvider.cinquiemeGrade, cinquiemes),
                SchoolClassTestProvider.generateQuatrieme(teachers, GradeTestProvider.quatriemeGrade, quatriemes),
                SchoolClassTestProvider.generateTroisieme(teachers, GradeTestProvider.troisiemeGrade, troisiemes)
        ).flatMap(Collection::stream).collect(Collectors.toList());
    }

    public static List<SchoolClass> schoolClasses(int sixiemes, int cinquiemes, int quatriemes, int troisiemes) {
        return schoolClasses(Collections.emptyList(), sixiemes, cinquiemes, quatriemes, troisiemes);
    }

    public static Map<SchoolClass, List<Subject>> schoolClassesWithSubjects(int sixiemes, int cinquiemes, int quatriemes, int troisiemes) {
        final List<SchoolClass> schoolClasses = schoolClasses(Collections.emptyList(), sixiemes, cinquiemes, quatriemes, troisiemes);
        Map<SchoolClass, List<Subject>> schoolClassesMap = new HashMap<>();
        schoolClasses.forEach(schoolClass -> {
            switch (schoolClass.getGrade().getId()) {
                case GlobalTestProvider.Grades.GRADE_SIXIEME_ID:
                    schoolClassesMap.put(schoolClass, SubjectSixiemeTestProvider.allSubjects());
                    break;
                case GlobalTestProvider.Grades.GRADE_CINQUIEME_ID:
                    schoolClassesMap.put(schoolClass, SubjectCinquiemeTestProvider.allSubjects());
                    break;
                case GlobalTestProvider.Grades.GRADE_QUATRIEME_ID:
                    schoolClassesMap.put(schoolClass, SubjectQuatriemeTestProvider.allSubjects());
                    break;
                case GlobalTestProvider.Grades.GRADE_TROISIEME_ID:
                    schoolClassesMap.put(schoolClass, SubjectTroisiemeTestProvider.allSubjects());
                    break;
                case GlobalTestProvider.Grades.GRADE_SECONDE_ID:
                    schoolClassesMap.put(schoolClass, SubjectSecondeTestProvider.allSubjects());
                    break;
                case GlobalTestProvider.Grades.GRADE_PREMIERE_ID:
                    schoolClassesMap.put(schoolClass, SubjectPremiereTestProvider.allSubjects());
                    break;
                case GlobalTestProvider.Grades.GRADE_TERMINALE_ID:
                    schoolClassesMap.put(schoolClass, SubjectTerminaleTestProvider.allSubjects());
                    break;
            }
        });
        return schoolClassesMap;
    }

}
