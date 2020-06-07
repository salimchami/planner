package io.edukativ.myskoolin.domain.providers.schoolclasses;

import io.edukativ.myskoolin.domain.commons.exceptions.NotFoundException;
import io.edukativ.myskoolin.domain.entity.Grade;
import io.edukativ.myskoolin.domain.entity.SchoolClass;
import io.edukativ.myskoolin.domain.entity.Subject;
import io.edukativ.myskoolin.domain.providers.GlobalProvider;
import io.edukativ.myskoolin.domain.providers.GradeProvider;
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

public final class SchoolClassProvider {

    private static Random randomGenerator = new Random();

    public static SchoolClass schoolClass(String id, int coursesStartDateYear, int coursesStartDateMonth, int coursesStartDateDay,
                                          int coursesEndDateYear, int coursesEndDateMonth, int coursesEndDateDay,
                                          String name, Grade grade, GradeSerie gradeSerie,
                                          List<TeachersBySubject> teachersBySubjects) {
        return new SchoolClass(id, GlobalProvider.CLIENT_ID,
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
            schoolClasses.add(SchoolClassTroisiemeProvider.generate("Troisieme" + i, grade, allTeachers, "Troisième " + i));
        }
        return schoolClasses;
    }

    public static List<SchoolClass> generateQuatrieme(List<Teacher> allTeachers, Grade grade, Integer nb) {
        List<SchoolClass> schoolClasses = new ArrayList<>();
        for (int i = 1; i <= nb; i++) {
            schoolClasses.add(SchoolClassQuatriemeProvider.generate("Quatrieme" + i, grade, allTeachers, "Quatrième " + i));
        }
        return schoolClasses;
    }

    public static List<SchoolClass> generateCinquieme(List<Teacher> allTeachers, Grade grade, Integer nb) {
        List<SchoolClass> schoolClasses = new ArrayList<>();
        for (int i = 1; i <= nb; i++) {
            schoolClasses.add(SchoolClassCinquiemeProvider.generate("Cinquieme" + i, grade, allTeachers, "Cinquième " + i));
        }
        return schoolClasses;
    }

    public static List<SchoolClass> generateSixieme(List<Teacher> allTeachers, Grade grade, Integer nb) {
        List<SchoolClass> schoolClasses = new ArrayList<>();
        for (int i = 1; i <= nb; i++) {
            schoolClasses.add(SchoolClassSixiemeProvider.generate("Sixieme" + i, grade, allTeachers, "Sixième " + i));
        }
        return schoolClasses;
    }

    public static List<SchoolClass> schoolClasses(List<Teacher> teachers, int sixiemes, int cinquiemes, int quatriemes, int troisiemes) {
        return Stream.of(
                SchoolClassProvider.generateSixieme(teachers, GradeProvider.sixiemeGrade, sixiemes),
                SchoolClassProvider.generateCinquieme(teachers, GradeProvider.cinquiemeGrade, cinquiemes),
                SchoolClassProvider.generateQuatrieme(teachers, GradeProvider.quatriemeGrade, quatriemes),
                SchoolClassProvider.generateTroisieme(teachers, GradeProvider.troisiemeGrade, troisiemes)
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
                case GlobalProvider.Grades.GRADE_SIXIEME_ID:
                    schoolClassesMap.put(schoolClass, SubjectSixiemeProvider.allSubjects());
                    break;
                case GlobalProvider.Grades.GRADE_CINQUIEME_ID:
                    schoolClassesMap.put(schoolClass, SubjectCinquiemeProvider.allSubjects());
                    break;
                case GlobalProvider.Grades.GRADE_QUATRIEME_ID:
                    schoolClassesMap.put(schoolClass, SubjectQuatriemeProvider.allSubjects());
                    break;
                case GlobalProvider.Grades.GRADE_TROISIEME_ID:
                    schoolClassesMap.put(schoolClass, SubjectTroisiemeProvider.allSubjects());
                    break;
                case GlobalProvider.Grades.GRADE_SECONDE_ID:
                    schoolClassesMap.put(schoolClass, SubjectSecondeProvider.allSubjects());
                    break;
                case GlobalProvider.Grades.GRADE_PREMIERE_ID:
                    schoolClassesMap.put(schoolClass, SubjectPremiereProvider.allSubjects());
                    break;
                case GlobalProvider.Grades.GRADE_TERMINALE_ID:
                    schoolClassesMap.put(schoolClass, SubjectTerminaleProvider.allSubjects());
                    break;
            }
        });
        return schoolClassesMap;
    }

}
