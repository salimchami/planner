package io.edukativ.myskoolin.domain.providers;

import io.edukativ.myskoolin.domain.commons.exceptions.NotFoundException;
import io.edukativ.myskoolin.domain.entity.Grade;
import io.edukativ.myskoolin.domain.entity.User;
import io.edukativ.myskoolin.domain.teachers.Teacher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserTestProvider {

    private static List<String> collegeSciencesEpiTaughtSubjects = Arrays.asList(
            GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_SCIENCES_EPI_ID,
            GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_SCIENCES_EPI_ID,
            GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_SCIENCES_EPI_ID,
            GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_SCIENCES_EPI_ID
    );

    private static List<String> collegeMathsEpiTaughtSubjects = Arrays.asList(
            GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_MATHS_EPI_ID,
            GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_MATHS_EPI_ID,
            GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_MATHS_EPI_ID,
            GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_MATHS_EPI_ID
    );

    private static List<String> collegeFrancaisEpiTaughtSubjects = Arrays.asList(
            GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_FRANCAIS_EPI_ID,
            GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_FRANCAIS_EPI_ID,
            GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_FRANCAIS_EPI_ID,
            GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_FRANCAIS_EPI_ID
    );

    public static Teacher teacherUser(String id, List<Grade> grades, List<String> taughtSubjects) {
//        User user = defaultUser(id);
//        Teacher teacher = new Teacher(null, false,
//                null, null, taughtSubjects, new ArrayList<>(),
//                null, grades);
//        user.setTeacher(teacher);
//        return user;
        return null;
    }

    public static User defaultUser(String id) {
        return null;
    }

    private static List<Teacher> musiqueTeachers(Grade... grades) {
        List<String> subjects = Arrays.asList(
                GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_MUSIQUE_ID,
                GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_MUSIQUE_ID,
                GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_MUSIQUE_ID,
                GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_MUSIQUE_ID
        );
        return Arrays.asList(
                teacherUser(GlobalTestProvider.TEACHER_MUSIQUE_1_ID, new ArrayList<>(Arrays.asList(grades)), subjects),
                teacherUser(GlobalTestProvider.TEACHER_MUSIQUE_2_ID, new ArrayList<>(Arrays.asList(grades)), subjects)
        );
    }

    private static List<Teacher> physiqueTeachers(Grade... grades) {
        List<String> subjects = Arrays.asList(
                GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_PHYSIQUE_ID,
                GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_PHYSIQUE_ID,
                GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_PHYSIQUE_ID,
                GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_PHYSIQUE_ID
        );
        final List<String> allSubjects = Stream.of(subjects, collegeSciencesEpiTaughtSubjects).flatMap(Collection::stream).collect(Collectors.toList());
        return Arrays.asList(
                teacherUser(GlobalTestProvider.TEACHER_PHYSIQUE_1_ID, new ArrayList<>(Arrays.asList(grades)), allSubjects),
                teacherUser(GlobalTestProvider.TEACHER_PHYSIQUE_2_ID, new ArrayList<>(Arrays.asList(grades)), allSubjects),
                teacherUser(GlobalTestProvider.TEACHER_PHYSIQUE_3_ID, new ArrayList<>(Arrays.asList(grades)), allSubjects)
        );
    }

    private static List<Teacher> svtTeachers(Grade... grades) {
        List<String> subjects = Arrays.asList(
                GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_SVT_ID,
                GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_SVT_ID,
                GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_SVT_ID,
                GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_SVT_ID
        );
        final List<String> allSubjects = Stream.of(subjects, collegeSciencesEpiTaughtSubjects).flatMap(Collection::stream).collect(Collectors.toList());
        return Arrays.asList(
                teacherUser(GlobalTestProvider.TEACHER_SVT_1_ID, new ArrayList<>(Arrays.asList(grades)), allSubjects),
                teacherUser(GlobalTestProvider.TEACHER_SVT_2_ID, new ArrayList<>(Arrays.asList(grades)), allSubjects),
                teacherUser(GlobalTestProvider.TEACHER_SVT_3_ID, new ArrayList<>(Arrays.asList(grades)), allSubjects)
        );
    }

    private static List<Teacher> educCivTeachers(Grade... grades) {
        List<String> subjects = Arrays.asList(
                GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_EDUCCIV_ID,
                GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_EDUCCIV_ID,
                GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_EDUCCIV_ID,
                GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_EDUCCIV_ID
        );
        return Arrays.asList(
                teacherUser(GlobalTestProvider.TEACHER_EDUCCIV_1_ID, new ArrayList<>(Arrays.asList(grades)), subjects),
                teacherUser(GlobalTestProvider.TEACHER_EDUCCIV_2_ID, new ArrayList<>(Arrays.asList(grades)), subjects)
        );
    }

    private static List<Teacher> mathsTeachers(Grade... grades) {
        List<String> subjects = Arrays.asList(
                GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_MATHS_ID,
                GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_MATHS_ID,
                GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_MATHS_ID,
                GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_MATHS_ID
        );
        final List<String> allSubjects = Stream.of(subjects, collegeMathsEpiTaughtSubjects).flatMap(Collection::stream).collect(Collectors.toList());
        return Arrays.asList(
                teacherUser(GlobalTestProvider.TEACHER_MATHS_1_ID, new ArrayList<>(Arrays.asList(grades)), allSubjects),
                teacherUser(GlobalTestProvider.TEACHER_MATHS_2_ID, new ArrayList<>(Arrays.asList(grades)), allSubjects),
                teacherUser(GlobalTestProvider.TEACHER_MATHS_3_ID, new ArrayList<>(Arrays.asList(grades)), allSubjects),
                teacherUser(GlobalTestProvider.TEACHER_MATHS_4_ID, new ArrayList<>(Arrays.asList(grades)), allSubjects)
        );
    }

    private static List<Teacher> histGeoTeachers(Grade... grades) {
        List<String> subjects = Arrays.asList(
                GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_HISTGEO_GEO_ID,
                GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_HISTGEO_GEO_ID,
                GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_HISTGEO_GEO_ID,
                GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_HISTGEO_GEO_ID
        );
        return Arrays.asList(
                teacherUser(GlobalTestProvider.TEACHER_HISTGEO_1_GEO_ID, new ArrayList<>(Arrays.asList(grades)), subjects),
                teacherUser(GlobalTestProvider.TEACHER_HISTGEO_2_GEO_ID, new ArrayList<>(Arrays.asList(grades)), subjects)
        );
    }

    private static List<Teacher> dessinTeachers(Grade... grades) {
        List<String> subjects = Arrays.asList(
                GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_DESSIN_ID,
                GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_DESSIN_ID,
                GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_DESSIN_ID,
                GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_DESSIN_ID
        );
        return Arrays.asList(
                teacherUser(GlobalTestProvider.TEACHER_DESSIN_1_ID, new ArrayList<>(Arrays.asList(grades)), subjects),
                teacherUser(GlobalTestProvider.TEACHER_DESSIN_2_ID, new ArrayList<>(Arrays.asList(grades)), subjects)
        );
    }

    private static List<Teacher> anglaisTeachers(Grade... grades) {
        List<String> subjects = Arrays.asList(
                GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_ANGLAIS_ID,
                GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_ANGLAIS_ID,
                GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_ANGLAIS_ID,
                GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_ANGLAIS_ID
        );
        return Arrays.asList(
                teacherUser(GlobalTestProvider.TEACHER_ANGLAIS_1_ID, new ArrayList<>(Arrays.asList(grades)), subjects),
                teacherUser(GlobalTestProvider.TEACHER_ANGLAIS_2_ID, new ArrayList<>(Arrays.asList(grades)), subjects),
                teacherUser(GlobalTestProvider.TEACHER_ANGLAIS_3_ID, new ArrayList<>(Arrays.asList(grades)), subjects),
                teacherUser(GlobalTestProvider.TEACHER_ANGLAIS_4_ID, new ArrayList<>(Arrays.asList(grades)), subjects)
        );
    }

    private static List<Teacher> latinTeachers(Grade... grades) {
        List<String> subjects = Arrays.asList(
                GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_LATIN_ID,
                GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_LATIN_ID,
                GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_LATIN_ID
        );
        return Arrays.asList(
                teacherUser(GlobalTestProvider.TEACHER_LATIN_1_ID, new ArrayList<>(Arrays.asList(grades)), subjects),
                teacherUser(GlobalTestProvider.TEACHER_LATIN_2_ID, new ArrayList<>(Arrays.asList(grades)), subjects)
        );
    }

    private static List<Teacher> allemandTeachers(Grade... grades) {
        List<String> subjects = Arrays.asList(
                GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_ALLEMAND_ID,
                GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_ALLEMAND_ID,
                GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_ALLEMAND_ID,
                GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_ALLEMAND_ID
        );
        return Arrays.asList(
                teacherUser(GlobalTestProvider.TEACHER_ALLEMAND_1_ID, new ArrayList<>(Arrays.asList(grades)), subjects),
                teacherUser(GlobalTestProvider.TEACHER_ALLEMAND_2_ID, new ArrayList<>(Arrays.asList(grades)), subjects),
                teacherUser(GlobalTestProvider.TEACHER_ALLEMAND_3_ID, new ArrayList<>(Arrays.asList(grades)), subjects)
        );
    }

    private static List<Teacher> sportTeachers(Grade... grades) {
        List<String> subjects = Arrays.asList(
                GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_SPORT_ID,
                GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_SPORT_ID,
                GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_SPORT_ID,
                GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_SPORT_ID
        );
        return Arrays.asList(
                teacherUser(GlobalTestProvider.TEACHER_SPORT_1_ID, new ArrayList<>(Arrays.asList(grades)), subjects),
                teacherUser(GlobalTestProvider.TEACHER_SPORT_2_ID, new ArrayList<>(Arrays.asList(grades)), subjects),
                teacherUser(GlobalTestProvider.TEACHER_SPORT_3_ID, new ArrayList<>(Arrays.asList(grades)), subjects),
                teacherUser(GlobalTestProvider.TEACHER_SPORT_4_ID, new ArrayList<>(Arrays.asList(grades)), subjects)
        );
    }

    private static List<Teacher> francaisTeachers(Grade... grades) {
        List<String> subjects = Arrays.asList(
                GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_FRANCAIS_ID,
                GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_FRANCAIS_ID,
                GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_FRANCAIS_ID,
                GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_FRANCAIS_ID
        );
        final List<String> allSubjects = Stream.of(subjects, collegeFrancaisEpiTaughtSubjects).flatMap(Collection::stream).collect(Collectors.toList());
        return Arrays.asList(
                teacherUser(GlobalTestProvider.TEACHER_FRANCAIS_1_ID, new ArrayList<>(Arrays.asList(grades)), allSubjects),
                teacherUser(GlobalTestProvider.TEACHER_FRANCAIS_2_ID, new ArrayList<>(Arrays.asList(grades)), allSubjects),
                teacherUser(GlobalTestProvider.TEACHER_FRANCAIS_3_ID, new ArrayList<>(Arrays.asList(grades)), allSubjects),
                teacherUser(GlobalTestProvider.TEACHER_FRANCAIS_4_ID, new ArrayList<>(Arrays.asList(grades)), allSubjects)
        );
    }

    private static List<Teacher> technoTeachers(Grade... grades) {
        List<String> subjects = Arrays.asList(
                GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_TECHNOLOGIE_ID,
                GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_TECHNOLOGIE_ID,
                GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_TECHNOLOGIE_ID,
                GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_TECHNOLOGIE_ID
        );
        return Arrays.asList(
                teacherUser(GlobalTestProvider.TEACHER_TECHNOLOGIE_1_ID, new ArrayList<>(Arrays.asList(grades)), subjects),
                teacherUser(GlobalTestProvider.TEACHER_TECHNOLOGIE_2_ID, new ArrayList<>(Arrays.asList(grades)), subjects)
        );
    }

    // ###################################################################################################################
    public static User userById(List<User> allTeachers, String id) {
        return allTeachers.stream().filter(user -> user.getId().equals(id)).findFirst().orElseThrow(() -> new NotFoundException("teacher not found, id : " + id));
    }

    public static List<Teacher> teacherUsers(List<Grade> grades) {
        Grade[] gradesArr = grades.toArray(new Grade[0]);
        return Stream.of(
                musiqueTeachers(gradesArr), physiqueTeachers(gradesArr), svtTeachers(gradesArr), educCivTeachers(gradesArr),
                mathsTeachers(gradesArr), histGeoTeachers(gradesArr), dessinTeachers(gradesArr), anglaisTeachers(gradesArr),
                sportTeachers(gradesArr), francaisTeachers(gradesArr), technoTeachers(gradesArr), allemandTeachers(gradesArr),
                latinTeachers(gradesArr)
        ).flatMap(Collection::stream).collect(Collectors.toList());
    }
}
