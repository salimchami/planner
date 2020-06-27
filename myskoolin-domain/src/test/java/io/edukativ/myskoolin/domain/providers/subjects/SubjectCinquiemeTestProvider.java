package io.edukativ.myskoolin.domain.providers.subjects;

import io.edukativ.myskoolin.domain.entity.Subject;
import io.edukativ.myskoolin.domain.providers.GlobalTestProvider;
import io.edukativ.myskoolin.domain.providers.GradeTestProvider;
import io.edukativ.myskoolin.domain.commons.vo.EnumDays;
import io.edukativ.myskoolin.domain.commons.vo.EnumPartsOfDay;
import io.edukativ.myskoolin.domain.schoolrooms.EnumSchoolRoomsTypes;
import io.edukativ.myskoolin.domain.timetabling.PreferredPartsOfDays;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class SubjectCinquiemeTestProvider {

    public static List<Subject> allSubjects() {
        return Arrays.asList(
                francais(), francaisEpi(), maths(), mathsEpi(), sciencesEpi(), histoireGeo(), educationCivique(),
                anglais(), allemand(), svt(), physique(), techno(), sport(), dessin(), musique(), latin()
        );
    }

    private static Subject francais() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_FRANCAIS_ID, GradeTestProvider.cinquiemeGrade, "FRANCAIS", false,
                60, 60 * 2, (int) (60 * 3.5), 3,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    /**
     * enseignements pratiques interdisciplinaires - EPI - Francais
     */
    private static Subject francaisEpi() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_FRANCAIS_EPI_ID, GradeTestProvider.cinquiemeGrade, "Français - EPI", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject maths() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_MATHS_ID, GradeTestProvider.cinquiemeGrade, "MATHEMATIQUES", false,
                60, 60 * 2, (int) (60 * 2.5), 3,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    /**
     * enseignements pratiques interdisciplinaires - EPI - Maths
     */
    private static Subject mathsEpi() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_MATHS_EPI_ID, GradeTestProvider.cinquiemeGrade, "MATHS - EPI", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }


    private static Subject histoireGeo() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_HISTGEO_GEO_ID, GradeTestProvider.cinquiemeGrade, "HISTOIRE GEOGRAPHIE", false,
                60, 60, 60 * 2, 2,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject educationCivique() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_EDUCCIV_ID, GradeTestProvider.cinquiemeGrade, "ENSEIGNEMENT MORAL ET CIVIQUE", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");

    }

    private static Subject anglais() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_ANGLAIS_ID, GradeTestProvider.cinquiemeGrade, "ANGLAIS", true,
                60, 60 * 2, 60 * 3, 3,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject allemand() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_ALLEMAND_ID, GradeTestProvider.cinquiemeGrade, "ALLEMAND", true,
                60, 60, 60 * 2, 2,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject svt() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_SVT_ID, GradeTestProvider.cinquiemeGrade, "SCIENCES DE LA VIE ET DE LA TERRE", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.SCIENCES), 1, "");
    }

    private static Subject physique() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_PHYSIQUE_ID, GradeTestProvider.cinquiemeGrade, "PHYSIQUE-CHIMIE", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.SCIENCES), 1, "");
    }

    private static Subject techno() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_TECHNOLOGIE_ID, GradeTestProvider.cinquiemeGrade, "TECHNOLOGIE", false,
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
                GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_SCIENCES_EPI_ID, GradeTestProvider.cinquiemeGrade, "Sciences - EPI", false,
                (int) (60 * 1.5), (int) (60 * 1.5), (int) (60 * 1.5), 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject sport() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_SPORT_ID, GradeTestProvider.cinquiemeGrade, "EDUCATION PHYSIQUE", false,
                (int) (60 * 1.5), (int) (60 * 1.5), 60 * 3, 2,
                Arrays.asList(
                        new PreferredPartsOfDays(EnumDays.FRIDAY, EnumPartsOfDay.PM),
                        new PreferredPartsOfDays(EnumDays.THURSDAY, EnumPartsOfDay.PM),
                        new PreferredPartsOfDays(EnumDays.FRIDAY, EnumPartsOfDay.PM)
                ),
                Collections.singletonList(EnumSchoolRoomsTypes.SPORT), 1, "");
    }

    private static Subject dessin() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_DESSIN_ID, GradeTestProvider.cinquiemeGrade, "ARTS PLASTIQUES", false,
                60, 60, 60, 1,
                Collections.singletonList(new PreferredPartsOfDays(EnumDays.FRIDAY)),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject musique() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_MUSIQUE_ID, GradeTestProvider.cinquiemeGrade, "EDUCATION MUSICALE", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.MUSIC), 0, "");
    }

    private static Subject latin() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_LATIN_ID, GradeTestProvider.cinquiemeGrade, "Latin", false,
                60, 60, 60 * 2, 2,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

}
