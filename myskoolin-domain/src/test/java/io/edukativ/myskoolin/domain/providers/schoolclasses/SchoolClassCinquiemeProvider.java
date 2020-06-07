package io.edukativ.myskoolin.domain.providers.schoolclasses;

import io.edukativ.myskoolin.domain.entity.Grade;
import io.edukativ.myskoolin.domain.entity.SchoolClass;
import io.edukativ.myskoolin.domain.providers.GlobalProvider;
import io.edukativ.myskoolin.domain.vo.Teacher;
import io.edukativ.myskoolin.domain.vo.TeachersBySubject;

import java.util.Arrays;
import java.util.List;

public final class SchoolClassCinquiemeProvider {

    public static SchoolClass generate(String schoolClassId, Grade schoolClassGrade, List<Teacher> allTeachers, String schoolClassName) {
        List<TeachersBySubject> teachersBySubjects = Arrays.asList(
                new TeachersBySubject(GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_TECHNOLOGIE_ID,
                        Arrays.asList(
                                GlobalProvider.TEACHER_TECHNOLOGIE_1_ID,
                                GlobalProvider.TEACHER_TECHNOLOGIE_2_ID
                        )),
                new TeachersBySubject(GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_FRANCAIS_ID,
                        Arrays.asList(
                                GlobalProvider.TEACHER_FRANCAIS_1_ID,
                                GlobalProvider.TEACHER_FRANCAIS_2_ID,
                                GlobalProvider.TEACHER_FRANCAIS_3_ID,
                                GlobalProvider.TEACHER_FRANCAIS_4_ID
                        )),
                new TeachersBySubject(GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_SPORT_ID,
                        Arrays.asList(
                                GlobalProvider.TEACHER_SPORT_1_ID,
                                GlobalProvider.TEACHER_SPORT_2_ID,
                                GlobalProvider.TEACHER_SPORT_3_ID,
                                GlobalProvider.TEACHER_SPORT_4_ID
                        )),
                new TeachersBySubject(GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_ANGLAIS_ID,
                        Arrays.asList(
                                GlobalProvider.TEACHER_ANGLAIS_1_ID,
                                GlobalProvider.TEACHER_ANGLAIS_2_ID,
                                GlobalProvider.TEACHER_ANGLAIS_3_ID,
                                GlobalProvider.TEACHER_ANGLAIS_4_ID
                        )),
                new TeachersBySubject(GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_ALLEMAND_ID,
                        Arrays.asList(
                                GlobalProvider.TEACHER_ALLEMAND_1_ID,
                                GlobalProvider.TEACHER_ALLEMAND_2_ID,
                                GlobalProvider.TEACHER_ALLEMAND_3_ID
                        )),
                new TeachersBySubject(GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_DESSIN_ID,
                        Arrays.asList(
                                GlobalProvider.TEACHER_DESSIN_1_ID,
                                GlobalProvider.TEACHER_DESSIN_2_ID
                        )),
                new TeachersBySubject(GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_HISTGEO_GEO_ID,
                        Arrays.asList(
                                GlobalProvider.TEACHER_HISTGEO_1_GEO_ID,
                                GlobalProvider.TEACHER_HISTGEO_2_GEO_ID
                        )),
                new TeachersBySubject(GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_MATHS_ID,
                        Arrays.asList(
                                GlobalProvider.TEACHER_MATHS_1_ID,
                                GlobalProvider.TEACHER_MATHS_2_ID,
                                GlobalProvider.TEACHER_MATHS_3_ID,
                                GlobalProvider.TEACHER_MATHS_4_ID
                        )),
                new TeachersBySubject(GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_EDUCCIV_ID,
                        Arrays.asList(
                                GlobalProvider.TEACHER_EDUCCIV_1_ID,
                                GlobalProvider.TEACHER_EDUCCIV_2_ID
                        )),
                new TeachersBySubject(GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_SVT_ID,
                        Arrays.asList(
                                GlobalProvider.TEACHER_SVT_1_ID,
                                GlobalProvider.TEACHER_SVT_2_ID,
                                GlobalProvider.TEACHER_SVT_3_ID
                        )),
                new TeachersBySubject(GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_PHYSIQUE_ID,
                        Arrays.asList(
                                GlobalProvider.TEACHER_PHYSIQUE_1_ID,
                                GlobalProvider.TEACHER_PHYSIQUE_2_ID,
                                GlobalProvider.TEACHER_PHYSIQUE_3_ID
                        )),
                new TeachersBySubject(GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_MUSIQUE_ID,
                        Arrays.asList(
                                GlobalProvider.TEACHER_MUSIQUE_1_ID,
                                GlobalProvider.TEACHER_MUSIQUE_2_ID
                        )),
                new TeachersBySubject(GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_MATHS_EPI_ID,
                        Arrays.asList(
                                GlobalProvider.TEACHER_MATHS_1_ID,
                                GlobalProvider.TEACHER_MATHS_2_ID,
                                GlobalProvider.TEACHER_MATHS_3_ID,
                                GlobalProvider.TEACHER_MATHS_4_ID
                        )),
                new TeachersBySubject(GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_FRANCAIS_EPI_ID,
                        Arrays.asList(
                                GlobalProvider.TEACHER_FRANCAIS_1_ID,
                                GlobalProvider.TEACHER_FRANCAIS_2_ID,
                                GlobalProvider.TEACHER_FRANCAIS_3_ID,
                                GlobalProvider.TEACHER_FRANCAIS_4_ID
                        )),
                new TeachersBySubject(GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_LATIN_ID,
                        Arrays.asList(
                                GlobalProvider.TEACHER_LATIN_1_ID,
                                GlobalProvider.TEACHER_LATIN_2_ID
                        )),
                new TeachersBySubject(GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_SCIENCES_EPI_ID,
                        Arrays.asList(
                                GlobalProvider.TEACHER_PHYSIQUE_1_ID,
                                GlobalProvider.TEACHER_PHYSIQUE_2_ID,
                                GlobalProvider.TEACHER_PHYSIQUE_3_ID,
                                GlobalProvider.TEACHER_SVT_1_ID,
                                GlobalProvider.TEACHER_SVT_2_ID,
                                GlobalProvider.TEACHER_SVT_3_ID,
                                GlobalProvider.TEACHER_TECHNOLOGIE_1_ID,
                                GlobalProvider.TEACHER_TECHNOLOGIE_2_ID
                        ))
        );
        return SchoolClassProvider.schoolClass(schoolClassId, schoolClassName, schoolClassGrade, null, teachersBySubjects);
    }
}
