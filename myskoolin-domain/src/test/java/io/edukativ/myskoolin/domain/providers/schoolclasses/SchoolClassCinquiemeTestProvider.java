package io.edukativ.myskoolin.domain.providers.schoolclasses;

import io.edukativ.myskoolin.domain.grades.Grade;
import io.edukativ.myskoolin.domain.schoolclasses.SchoolClass;
import io.edukativ.myskoolin.domain.providers.GlobalTestProvider;
import io.edukativ.myskoolin.domain.subjects.Subject;
import io.edukativ.myskoolin.domain.teachers.Teacher;
import io.edukativ.myskoolin.domain.teachers.TeachersBySubject;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class SchoolClassCinquiemeTestProvider {

    public static SchoolClass generate(String schoolClassId, Grade schoolClassGrade, List<Teacher> allTeachers, String schoolClassName, List<Subject> subjects) {
        List<TeachersBySubject> teachersBySubjects = Arrays.asList(
                new TeachersBySubject(subjects.stream().filter(subject -> subject.getId().equals(GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_TECHNOLOGIE_ID)).findFirst().orElseThrow(),
                        allTeachers.stream().filter(teacher ->
                                teacher.getId().equals(GlobalTestProvider.TEACHER_TECHNOLOGIE_1_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_TECHNOLOGIE_2_ID)
                        ).collect(Collectors.toList())),
                new TeachersBySubject(subjects.stream().filter(subject -> subject.getId().equals(GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_FRANCAIS_ID)).findFirst().orElseThrow(),
                        allTeachers.stream().filter(teacher ->
                                teacher.getId().equals(GlobalTestProvider.TEACHER_FRANCAIS_1_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_FRANCAIS_2_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_FRANCAIS_3_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_FRANCAIS_4_ID)
                        ).collect(Collectors.toList())),
                new TeachersBySubject(subjects.stream().filter(subject -> subject.getId().equals(GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_SPORT_ID)).findFirst().orElseThrow(),
                        allTeachers.stream().filter(teacher ->
                                teacher.getId().equals(GlobalTestProvider.TEACHER_SPORT_1_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_SPORT_2_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_SPORT_3_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_SPORT_4_ID)
                        ).collect(Collectors.toList())),
                new TeachersBySubject(subjects.stream().filter(subject -> subject.getId().equals(GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_ANGLAIS_ID)).findFirst().orElseThrow(),
                        allTeachers.stream().filter(teacher ->
                                teacher.getId().equals(GlobalTestProvider.TEACHER_ANGLAIS_1_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_ANGLAIS_2_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_ANGLAIS_3_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_ANGLAIS_4_ID)
                        ).collect(Collectors.toList())),
                new TeachersBySubject(subjects.stream().filter(subject -> subject.getId().equals(GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_ALLEMAND_ID)).findFirst().orElseThrow(),
                        allTeachers.stream().filter(teacher ->
                                teacher.getId().equals(GlobalTestProvider.TEACHER_ALLEMAND_1_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_ALLEMAND_2_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_ALLEMAND_3_ID)
                        ).collect(Collectors.toList())),
                new TeachersBySubject(subjects.stream().filter(subject -> subject.getId().equals(GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_DESSIN_ID)).findFirst().orElseThrow(),
                        allTeachers.stream().filter(teacher ->
                                teacher.getId().equals(GlobalTestProvider.TEACHER_DESSIN_1_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_DESSIN_2_ID)
                        ).collect(Collectors.toList())),
                new TeachersBySubject(subjects.stream().filter(subject -> subject.getId().equals(GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_HISTGEO_GEO_ID)).findFirst().orElseThrow(),
                        allTeachers.stream().filter(teacher ->
                                teacher.getId().equals(GlobalTestProvider.TEACHER_HISTGEO_1_GEO_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_HISTGEO_2_GEO_ID)
                        ).collect(Collectors.toList())),
                new TeachersBySubject(subjects.stream().filter(subject -> subject.getId().equals(GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_MATHS_ID)).findFirst().orElseThrow(),
                        allTeachers.stream().filter(teacher ->
                                teacher.getId().equals(GlobalTestProvider.TEACHER_MATHS_1_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_MATHS_2_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_MATHS_3_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_MATHS_4_ID)
                        ).collect(Collectors.toList())),
                new TeachersBySubject(subjects.stream().filter(subject -> subject.getId().equals(GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_EDUCCIV_ID)).findFirst().orElseThrow(),
                        allTeachers.stream().filter(teacher ->
                                teacher.getId().equals(GlobalTestProvider.TEACHER_EDUCCIV_1_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_EDUCCIV_2_ID)
                        ).collect(Collectors.toList())),
                new TeachersBySubject(subjects.stream().filter(subject -> subject.getId().equals(GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_SVT_ID)).findFirst().orElseThrow(),
                        allTeachers.stream().filter(teacher ->
                                teacher.getId().equals(GlobalTestProvider.TEACHER_SVT_1_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_SVT_2_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_SVT_3_ID)
                        ).collect(Collectors.toList())),
                new TeachersBySubject(subjects.stream().filter(subject -> subject.getId().equals(GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_PHYSIQUE_ID)).findFirst().orElseThrow(),
                        allTeachers.stream().filter(teacher ->
                                teacher.getId().equals(GlobalTestProvider.TEACHER_PHYSIQUE_1_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_PHYSIQUE_2_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_PHYSIQUE_3_ID)
                        ).collect(Collectors.toList())),
                new TeachersBySubject(subjects.stream().filter(subject -> subject.getId().equals(GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_MUSIQUE_ID)).findFirst().orElseThrow(),
                        allTeachers.stream().filter(teacher ->
                                teacher.getId().equals(GlobalTestProvider.TEACHER_MUSIQUE_1_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_MUSIQUE_2_ID)
                        ).collect(Collectors.toList())),
                new TeachersBySubject(subjects.stream().filter(subject -> subject.getId().equals(GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_MATHS_EPI_ID)).findFirst().orElseThrow(),
                        allTeachers.stream().filter(teacher ->
                                teacher.getId().equals(GlobalTestProvider.TEACHER_MATHS_1_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_MATHS_2_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_MATHS_3_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_MATHS_4_ID)
                        ).collect(Collectors.toList())),
                new TeachersBySubject(subjects.stream().filter(subject -> subject.getId().equals(GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_FRANCAIS_EPI_ID)).findFirst().orElseThrow(),
                        allTeachers.stream().filter(teacher ->
                                teacher.getId().equals(GlobalTestProvider.TEACHER_FRANCAIS_1_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_FRANCAIS_2_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_FRANCAIS_3_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_FRANCAIS_4_ID)
                        ).collect(Collectors.toList())),
                new TeachersBySubject(subjects.stream().filter(subject -> subject.getId().equals(GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_LATIN_ID)).findFirst().orElseThrow(),
                        allTeachers.stream().filter(teacher ->
                                teacher.getId().equals(GlobalTestProvider.TEACHER_LATIN_1_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_LATIN_2_ID)
                        ).collect(Collectors.toList())),
                new TeachersBySubject(subjects.stream().filter(subject -> subject.getId().equals(GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_SCIENCES_EPI_ID)).findFirst().orElseThrow(),
                        allTeachers.stream().filter(teacher ->
                                teacher.getId().equals(GlobalTestProvider.TEACHER_PHYSIQUE_1_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_PHYSIQUE_2_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_PHYSIQUE_3_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_SVT_1_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_TECHNOLOGIE_1_ID)
                                        && teacher.getId().equals(GlobalTestProvider.TEACHER_TECHNOLOGIE_2_ID)
                        ).collect(Collectors.toList()))
                );
//        return SchoolClassTestProvider.schoolClass(schoolClassId, schoolClassName, schoolClassGrade, null, teachersBySubjects);
        return null;
    }
}
