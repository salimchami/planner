package io.edukativ.myskoolin.domain.providers;

import io.edukativ.myskoolin.domain.commons.exceptions.NotFoundException;
import io.edukativ.myskoolin.domain.grades.Grade;
import io.edukativ.myskoolin.domain.commons.entity.User;
import io.edukativ.myskoolin.domain.subjects.Subject;
import io.edukativ.myskoolin.domain.teachers.Teacher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserTestProvider {

    public static Teacher teacherUser(String id, String clientId, List<Grade> grades, List<Subject> taughtSubjects) {
        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setTaughtSubjects(taughtSubjects);
        teacher.setGrades(grades);
        teacher.setClientId(clientId);
        return teacher;
    }

    public static User defaultUser(String id) {
        return null;
    }

    private static List<Teacher> musiqueTeachers(String clientId, List<Subject> allSubjects, Grade... grades) {
        List<Subject> taughtSubjects = allSubjects.stream().filter(subject ->
                subject.getId().equals(GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_MUSIQUE_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_MUSIQUE_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_MUSIQUE_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_MUSIQUE_ID))
                .collect(Collectors.toList());
        return Arrays.asList(
                teacherUser(GlobalTestProvider.TEACHER_MUSIQUE_1_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects),
                teacherUser(GlobalTestProvider.TEACHER_MUSIQUE_2_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects)
        );
    }

    private static List<Teacher> physiqueTeachers(String clientId, List<Subject> allSubjects, Grade... grades) {
        List<Subject> taughtSubjects = allSubjects.stream().filter(subject ->
                subject.getId().equals(GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_PHYSIQUE_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_PHYSIQUE_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_PHYSIQUE_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_PHYSIQUE_ID))
                .collect(Collectors.toList());
        return Arrays.asList(
                teacherUser(GlobalTestProvider.TEACHER_PHYSIQUE_1_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects),
                teacherUser(GlobalTestProvider.TEACHER_PHYSIQUE_2_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects),
                teacherUser(GlobalTestProvider.TEACHER_PHYSIQUE_3_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects)
        );
    }

    private static List<Teacher> svtTeachers(String clientId, List<Subject> allSubjects, Grade... grades) {
        List<Subject> taughtSubjects = allSubjects.stream().filter(subject ->
                subject.getId().equals(GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_SVT_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_SVT_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_SVT_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_SVT_ID))
                .collect(Collectors.toList());
        return Arrays.asList(
                teacherUser(GlobalTestProvider.TEACHER_SVT_1_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects),
                teacherUser(GlobalTestProvider.TEACHER_SVT_2_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects),
                teacherUser(GlobalTestProvider.TEACHER_SVT_3_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects)
        );
    }

    private static List<Teacher> educCivTeachers(String clientId, List<Subject> allSubjects, Grade... grades) {
        List<Subject> taughtSubjects = allSubjects.stream().filter(subject ->
                subject.getId().equals(GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_EDUCCIV_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_EDUCCIV_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_EDUCCIV_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_EDUCCIV_ID))
                .collect(Collectors.toList());
        return Arrays.asList(
                teacherUser(GlobalTestProvider.TEACHER_EDUCCIV_1_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects),
                teacherUser(GlobalTestProvider.TEACHER_EDUCCIV_2_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects)
        );
    }

    private static List<Teacher> mathsTeachers(String clientId, List<Subject> allSubjects, Grade... grades) {
        List<Subject> taughtSubjects = allSubjects.stream().filter(subject ->
                subject.getId().equals(GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_MATHS_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_MATHS_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_MATHS_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_MATHS_ID))
                .collect(Collectors.toList());
        return Arrays.asList(
                teacherUser(GlobalTestProvider.TEACHER_MATHS_1_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects),
                teacherUser(GlobalTestProvider.TEACHER_MATHS_2_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects),
                teacherUser(GlobalTestProvider.TEACHER_MATHS_3_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects),
                teacherUser(GlobalTestProvider.TEACHER_MATHS_4_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects)
        );
    }

    private static List<Teacher> histGeoTeachers(String clientId, List<Subject> allSubjects, Grade... grades) {
        List<Subject> taughtSubjects = allSubjects.stream().filter(subject ->
                subject.getId().equals(GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_HISTGEO_GEO_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_HISTGEO_GEO_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_HISTGEO_GEO_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_HISTGEO_GEO_ID))
                .collect(Collectors.toList());
        return Arrays.asList(
                teacherUser(GlobalTestProvider.TEACHER_HISTGEO_1_GEO_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects),
                teacherUser(GlobalTestProvider.TEACHER_HISTGEO_2_GEO_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects)
        );
    }

    private static List<Teacher> dessinTeachers(String clientId, List<Subject> allSubjects, Grade... grades) {
        List<Subject> taughtSubjects = allSubjects.stream().filter(subject ->
                subject.getId().equals(GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_DESSIN_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_DESSIN_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_DESSIN_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_DESSIN_ID))
                .collect(Collectors.toList());
        return Arrays.asList(
                teacherUser(GlobalTestProvider.TEACHER_DESSIN_1_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects),
                teacherUser(GlobalTestProvider.TEACHER_DESSIN_2_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects)
        );
    }

    private static List<Teacher> anglaisTeachers(String clientId, List<Subject> allSubjects, Grade... grades) {
        List<Subject> taughtSubjects = allSubjects.stream().filter(subject ->
                subject.getId().equals(GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_ANGLAIS_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_ANGLAIS_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_ANGLAIS_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_ANGLAIS_ID))
                .collect(Collectors.toList());
        return Arrays.asList(
                teacherUser(GlobalTestProvider.TEACHER_ANGLAIS_1_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects),
                teacherUser(GlobalTestProvider.TEACHER_ANGLAIS_2_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects),
                teacherUser(GlobalTestProvider.TEACHER_ANGLAIS_3_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects),
                teacherUser(GlobalTestProvider.TEACHER_ANGLAIS_4_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects)
        );
    }

    private static List<Teacher> latinTeachers(String clientId, List<Subject> allSubjects, Grade... grades) {
        List<Subject> taughtSubjects = allSubjects.stream().filter(subject ->
                subject.getId().equals(GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_LATIN_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_LATIN_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_LATIN_ID))
                .collect(Collectors.toList());
        return Arrays.asList(
                teacherUser(GlobalTestProvider.TEACHER_LATIN_1_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects),
                teacherUser(GlobalTestProvider.TEACHER_LATIN_2_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects)
        );
    }

    private static List<Teacher> allemandTeachers(String clientId, List<Subject> allSubjects, Grade... grades) {
        List<Subject> taughtSubjects = allSubjects.stream().filter(subject ->
                subject.getId().equals(GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_ALLEMAND_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_ALLEMAND_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_ALLEMAND_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_ALLEMAND_ID))
                .collect(Collectors.toList());
        return Arrays.asList(
                teacherUser(GlobalTestProvider.TEACHER_ALLEMAND_1_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects),
                teacherUser(GlobalTestProvider.TEACHER_ALLEMAND_2_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects),
                teacherUser(GlobalTestProvider.TEACHER_ALLEMAND_3_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects)
        );
    }

    private static List<Teacher> sportTeachers(String clientId, List<Subject> allSubjects, Grade... grades) {
        List<Subject> taughtSubjects = allSubjects.stream().filter(subject ->
                subject.getId().equals(GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_SPORT_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_SPORT_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_SPORT_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_SPORT_ID))
                .collect(Collectors.toList());
        return Arrays.asList(
                teacherUser(GlobalTestProvider.TEACHER_SPORT_1_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects),
                teacherUser(GlobalTestProvider.TEACHER_SPORT_2_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects),
                teacherUser(GlobalTestProvider.TEACHER_SPORT_3_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects),
                teacherUser(GlobalTestProvider.TEACHER_SPORT_4_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects)
        );
    }

    private static List<Teacher> francaisTeachers(String clientId, List<Subject> allSubjects, Grade... grades) {
        List<Subject> taughtSubjects = allSubjects.stream().filter(subject ->
                subject.getId().equals(GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_FRANCAIS_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_FRANCAIS_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_FRANCAIS_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_FRANCAIS_ID))
                .collect(Collectors.toList());
        return Arrays.asList(
                teacherUser(GlobalTestProvider.TEACHER_FRANCAIS_1_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects),
                teacherUser(GlobalTestProvider.TEACHER_FRANCAIS_2_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects),
                teacherUser(GlobalTestProvider.TEACHER_FRANCAIS_3_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects),
                teacherUser(GlobalTestProvider.TEACHER_FRANCAIS_4_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects)
        );
    }

    private static List<Teacher> technoTeachers(String clientId, List<Subject> allSubjects, Grade... grades) {
        List<Subject> taughtSubjects = allSubjects.stream().filter(subject ->
                subject.getId().equals(GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_TECHNOLOGIE_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_TECHNOLOGIE_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_TECHNOLOGIE_ID) ||
                        subject.getId().equals(GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_TECHNOLOGIE_ID))
                .collect(Collectors.toList());
        return Arrays.asList(
                teacherUser(GlobalTestProvider.TEACHER_TECHNOLOGIE_1_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects),
                teacherUser(GlobalTestProvider.TEACHER_TECHNOLOGIE_2_ID, clientId, new ArrayList<>(Arrays.asList(grades)), taughtSubjects)
        );
    }

    // ###################################################################################################################
    public static User userById(List<User> allTeachers, String id) {
        return allTeachers.stream().filter(user -> user.getId().equals(id)).findFirst().orElseThrow(() -> new NotFoundException("teacher not found, id : " + id));
    }

    public static List<Teacher> teacherUsers(String clientId, List<Subject> allSubjects, List<Grade> grades) {
        Grade[] gradesArr = grades.toArray(new Grade[0]);
        return Stream.of(
                musiqueTeachers(clientId, allSubjects, gradesArr), physiqueTeachers(clientId, allSubjects, gradesArr),
                svtTeachers(clientId, allSubjects, gradesArr), educCivTeachers(clientId, allSubjects, gradesArr),
                mathsTeachers(clientId, allSubjects, gradesArr), histGeoTeachers(clientId, allSubjects, gradesArr),
                dessinTeachers(clientId, allSubjects, gradesArr), anglaisTeachers(clientId, allSubjects, gradesArr),
                sportTeachers(clientId, allSubjects, gradesArr), francaisTeachers(clientId, allSubjects, gradesArr),
                technoTeachers(clientId, allSubjects, gradesArr), allemandTeachers(clientId, allSubjects, gradesArr),
                latinTeachers(clientId, allSubjects, gradesArr)
        ).flatMap(Collection::stream).collect(Collectors.toList());
    }
}
