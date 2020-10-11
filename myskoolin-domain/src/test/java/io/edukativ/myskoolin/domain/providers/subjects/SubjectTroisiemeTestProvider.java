package io.edukativ.myskoolin.domain.providers.subjects;

import io.edukativ.myskoolin.domain.commons.vo.EnumPartsOfDay;
import io.edukativ.myskoolin.domain.providers.GlobalTestProvider;
import io.edukativ.myskoolin.domain.providers.GradeTestProvider;
import io.edukativ.myskoolin.domain.schoolrooms.EnumSchoolRoomsTypes;
import io.edukativ.myskoolin.domain.subjects.Subject;
import io.edukativ.myskoolin.domain.timetabling.PreferredPartsOfDays;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class SubjectTroisiemeTestProvider {

    public static List<Subject> allSubjects() {
        return Arrays.asList(
                francais(), francaisEpi(), maths(), mathsEpi(), sciencesEpi(), histoireGeo(), educationCivique(),
                anglais(), allemand(), svt(), physique(), techno(), sport(), dessin(), musique(), latin()
        );
    }

    private static Subject francais() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_FRANCAIS_ID, GradeTestProvider.troisiemeGrade, "FRANCAIS", false,
                60, 60 * 2, 60 * 3, 3,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    /**
     * enseignements pratiques interdisciplinaires - EPI - Francais
     */
    private static Subject francaisEpi() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_FRANCAIS_EPI_ID, GradeTestProvider.troisiemeGrade, "Français - EPI", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject maths() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_MATHS_ID, GradeTestProvider.troisiemeGrade, "MATHEMATIQUES", false,
                60, 60 * 2, (int) (60 * 2.5), 3,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    /**
     * enseignements pratiques interdisciplinaires - EPI - Maths
     */
    private static Subject mathsEpi() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_MATHS_EPI_ID, GradeTestProvider.troisiemeGrade, "MATHS - EPI", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }


    private static Subject histoireGeo() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_HISTGEO_GEO_ID, GradeTestProvider.troisiemeGrade, "HISTOIRE GEOGRAPHIE", false,
                60, 60, 60 * 2, 2,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject educationCivique() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_EDUCCIV_ID, GradeTestProvider.troisiemeGrade, "ENSEIGNEMENT MORAL ET CIVIQUE", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");

    }

    private static Subject anglais() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_ANGLAIS_ID, GradeTestProvider.troisiemeGrade, "ANGLAIS", true,
                60, 60 * 2, 60 * 3, 3,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject allemand() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_ALLEMAND_ID, GradeTestProvider.troisiemeGrade, "ALLEMAND", true,
                60, 60, 60 * 2, 2,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject svt() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_SVT_ID, GradeTestProvider.troisiemeGrade, "SCIENCES DE LA VIE ET DE LA TERRE", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.SCIENCES), 1, "");
    }

    private static Subject physique() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_PHYSIQUE_ID, GradeTestProvider.troisiemeGrade, "PHYSIQUE-CHIMIE", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.SCIENCES), 1, "");
    }

    private static Subject techno() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_TECHNOLOGIE_ID, GradeTestProvider.troisiemeGrade, "TECHNOLOGIE", false,
                60, 60, 60, 1,
                Arrays.asList(
                        new PreferredPartsOfDays(DayOfWeek.MONDAY),
                        new PreferredPartsOfDays(DayOfWeek.TUESDAY, EnumPartsOfDay.PM),
                        new PreferredPartsOfDays(DayOfWeek.FRIDAY, EnumPartsOfDay.PM)
                ),
                Collections.singletonList(EnumSchoolRoomsTypes.IT), 1, "");
    }

    /**
     * enseignements pratiques interdisciplinaires - EPI - Sciences
     */
    private static Subject sciencesEpi() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_SCIENCES_EPI_ID, GradeTestProvider.troisiemeGrade, "Sciences - EPI", false,
                (int) (60 * 1.5), (int) (60 * 1.5), (int) (60 * 1.5), 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject sport() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_SPORT_ID, GradeTestProvider.troisiemeGrade, "EDUCATION PHYSIQUE", false,
                (int) (60 * 1.5), (int) (60 * 1.5), 60 * 3, 2,
                Arrays.asList(
                        new PreferredPartsOfDays(DayOfWeek.FRIDAY, EnumPartsOfDay.PM),
                        new PreferredPartsOfDays(DayOfWeek.THURSDAY, EnumPartsOfDay.PM),
                        new PreferredPartsOfDays(DayOfWeek.FRIDAY, EnumPartsOfDay.PM)
                ),
                Collections.singletonList(EnumSchoolRoomsTypes.SPORT), 1, "");
    }

    private static Subject dessin() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_DESSIN_ID, GradeTestProvider.troisiemeGrade, "ARTS PLASTIQUES", false,
                60, 60, 60, 1,
                Collections.singletonList(new PreferredPartsOfDays(DayOfWeek.FRIDAY)),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject musique() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_MUSIQUE_ID, GradeTestProvider.troisiemeGrade, "EDUCATION MUSICALE", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.MUSIC), 0, "");
    }

    private static Subject latin() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Troisieme.SUBJECT_TROISIEME_LATIN_ID, GradeTestProvider.troisiemeGrade, "Latin", false,
                60, 60, 60 * 2, 2,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }
}
