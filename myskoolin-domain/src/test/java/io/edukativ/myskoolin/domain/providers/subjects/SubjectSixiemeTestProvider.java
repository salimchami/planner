package io.edukativ.myskoolin.domain.providers.subjects;

import io.edukativ.myskoolin.domain.entity.Subject;
import io.edukativ.myskoolin.domain.providers.GlobalTestProvider;
import io.edukativ.myskoolin.domain.commons.vo.EnumDays;
import io.edukativ.myskoolin.domain.commons.vo.EnumPartsOfDay;
import io.edukativ.myskoolin.domain.schoolrooms.EnumSchoolRoomsTypes;
import io.edukativ.myskoolin.domain.timetabling.PreferredPartsOfDays;
import io.edukativ.myskoolin.domain.providers.GradeTestProvider;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class SubjectSixiemeTestProvider {

    public static List<Subject> allSubjects() {
        return Arrays.asList(
                francais(), francaisEpi(), maths(), mathsEpi(), sciencesEpi(), histoireGeo(), educationCivique(),
                anglais(), allemand(), svt(), physique(), techno(), sport(), dessin(), musique()
        );
    }

    private static Subject francais() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_FRANCAIS_ID, GradeTestProvider.sixiemeGrade, "FRANCAIS", false,
                60, 60 * 2, (int) (60 * 3.5), 3,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    /**
     * enseignements pratiques interdisciplinaires - EPI - Francais
     */
    private static Subject francaisEpi() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_FRANCAIS_EPI_ID, GradeTestProvider.sixiemeGrade, "Français - EPI", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject maths() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_MATHS_ID, GradeTestProvider.sixiemeGrade, "MATHEMATIQUES", false,
                60, 60 * 2, (int) (60 * 3.5), 3,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    /**
     * enseignements pratiques interdisciplinaires - EPI - Maths
     */
    private static Subject mathsEpi() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_MATHS_EPI_ID, GradeTestProvider.sixiemeGrade, "MATHS - EPI", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }


    private static Subject histoireGeo() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_HISTGEO_GEO_ID, GradeTestProvider.sixiemeGrade, "HISTOIRE GEOGRAPHIE", false,
                60, 60, 60 * 2, 2,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject educationCivique() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_EDUCCIV_ID, GradeTestProvider.sixiemeGrade, "ENSEIGNEMENT MORAL ET CIVIQUE", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");

    }

    private static Subject anglais() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_ANGLAIS_ID, GradeTestProvider.sixiemeGrade, "ANGLAIS", true,
                60, 60 * 2, 60 * 4, 4,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject allemand() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_ALLEMAND_ID, GradeTestProvider.sixiemeGrade, "ALLEMAND", true,
                60, 60, 60 * 2, 2,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject svt() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_SVT_ID, GradeTestProvider.sixiemeGrade, "SCIENCES DE LA VIE ET DE LA TERRE", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.SCIENCES), 1, "");
    }

    private static Subject physique() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_PHYSIQUE_ID, GradeTestProvider.sixiemeGrade, "PHYSIQUE-CHIMIE", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.SCIENCES), 1, "");
    }

    private static Subject techno() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_TECHNOLOGIE_ID, GradeTestProvider.sixiemeGrade, "TECHNOLOGIE", false,
                60, 60, 60, 1,
                Arrays.asList(
                        new PreferredPartsOfDays(EnumDays.MONDAY),
                        new PreferredPartsOfDays(EnumDays.TUESDAY, EnumPartsOfDay.PM),
                        new PreferredPartsOfDays(EnumDays.FRIDAY, EnumPartsOfDay.PM)
                ),
                Collections.singletonList(EnumSchoolRoomsTypes.IT), 1, "");
    }

    /**
     * enseignements pratiques interdisciplinaires - EPI - Sciences
     */
    private static Subject sciencesEpi() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_SCIENCES_EPI_ID, GradeTestProvider.sixiemeGrade, "Sciences - EPI", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject sport() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_SPORT_ID, GradeTestProvider.sixiemeGrade, "EDUCATION PHYSIQUE", false,
                120, 60 * 2, 60 * 4, 2,
                Arrays.asList(
                        new PreferredPartsOfDays(EnumDays.FRIDAY, EnumPartsOfDay.PM),
                        new PreferredPartsOfDays(EnumDays.THURSDAY, EnumPartsOfDay.PM),
                        new PreferredPartsOfDays(EnumDays.FRIDAY, EnumPartsOfDay.PM)
                ),
                Collections.singletonList(EnumSchoolRoomsTypes.SPORT), 1, "");
    }

    private static Subject dessin() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_DESSIN_ID, GradeTestProvider.sixiemeGrade, "ARTS PLASTIQUES", false,
                60, 60, 60, 1,
                Collections.singletonList(new PreferredPartsOfDays(EnumDays.FRIDAY)),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject musique() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_MUSIQUE_ID, GradeTestProvider.sixiemeGrade, "EDUCATION MUSICALE", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.MUSIC), 0, "");
    }
}
