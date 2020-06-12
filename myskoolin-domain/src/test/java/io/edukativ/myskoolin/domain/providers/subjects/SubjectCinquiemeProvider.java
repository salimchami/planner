package io.edukativ.myskoolin.domain.providers.subjects;

import io.edukativ.myskoolin.domain.entity.Subject;
import io.edukativ.myskoolin.domain.providers.GlobalProvider;
import io.edukativ.myskoolin.domain.providers.GradeProvider;
import io.edukativ.myskoolin.domain.vo.EnumDays;
import io.edukativ.myskoolin.domain.vo.EnumPartsOfDay;
import io.edukativ.myskoolin.domain.vo.EnumSchoolRoomsTypes;
import io.edukativ.myskoolin.domain.vo.PreferredPartsOfDays;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class SubjectCinquiemeProvider {

    public static List<Subject> allSubjects() {
        return Arrays.asList(
                francais(), francaisEpi(), maths(), mathsEpi(), sciencesEpi(), histoireGeo(), educationCivique(),
                anglais(), allemand(), svt(), physique(), techno(), sport(), dessin(), musique(), latin()
        );
    }

    private static Subject francais() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_FRANCAIS_ID, GradeProvider.cinquiemeGrade, "FRANCAIS", false,
                60, 60 * 2, (int) (60 * 3.5), 3,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    /**
     * enseignements pratiques interdisciplinaires - EPI - Francais
     */
    private static Subject francaisEpi() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_FRANCAIS_EPI_ID, GradeProvider.cinquiemeGrade, "Français - EPI", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject maths() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_MATHS_ID, GradeProvider.cinquiemeGrade, "MATHEMATIQUES", false,
                60, 60 * 2, (int) (60 * 2.5), 3,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    /**
     * enseignements pratiques interdisciplinaires - EPI - Maths
     */
    private static Subject mathsEpi() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_MATHS_EPI_ID, GradeProvider.cinquiemeGrade, "MATHS - EPI", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }


    private static Subject histoireGeo() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_HISTGEO_GEO_ID, GradeProvider.cinquiemeGrade, "HISTOIRE GEOGRAPHIE", false,
                60, 60, 60 * 2, 2,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject educationCivique() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_EDUCCIV_ID, GradeProvider.cinquiemeGrade, "ENSEIGNEMENT MORAL ET CIVIQUE", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");

    }

    private static Subject anglais() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_ANGLAIS_ID, GradeProvider.cinquiemeGrade, "ANGLAIS", true,
                60, 60 * 2, 60 * 3, 3,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject allemand() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_ALLEMAND_ID, GradeProvider.cinquiemeGrade, "ALLEMAND", true,
                60, 60, 60 * 2, 2,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject svt() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_SVT_ID, GradeProvider.cinquiemeGrade, "SCIENCES DE LA VIE ET DE LA TERRE", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.SCIENCES), 1, "");
    }

    private static Subject physique() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_PHYSIQUE_ID, GradeProvider.cinquiemeGrade, "PHYSIQUE-CHIMIE", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.SCIENCES), 1, "");
    }

    private static Subject techno() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_TECHNOLOGIE_ID, GradeProvider.cinquiemeGrade, "TECHNOLOGIE", false,
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
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_SCIENCES_EPI_ID, GradeProvider.cinquiemeGrade, "Sciences - EPI", false,
                (int) (60 * 1.5), (int) (60 * 1.5), (int) (60 * 1.5), 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject sport() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_SPORT_ID, GradeProvider.cinquiemeGrade, "EDUCATION PHYSIQUE", false,
                (int) (60 * 1.5), (int) (60 * 1.5), 60 * 3, 2,
                Arrays.asList(
                        new PreferredPartsOfDays(EnumDays.FRIDAY, EnumPartsOfDay.PM),
                        new PreferredPartsOfDays(EnumDays.THURSDAY, EnumPartsOfDay.PM),
                        new PreferredPartsOfDays(EnumDays.FRIDAY, EnumPartsOfDay.PM)
                ),
                Collections.singletonList(EnumSchoolRoomsTypes.SPORT), 1, "");
    }

    private static Subject dessin() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_DESSIN_ID, GradeProvider.cinquiemeGrade, "ARTS PLASTIQUES", false,
                60, 60, 60, 1,
                Collections.singletonList(new PreferredPartsOfDays(EnumDays.FRIDAY)),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject musique() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_MUSIQUE_ID, GradeProvider.cinquiemeGrade, "EDUCATION MUSICALE", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.MUSIC), 0, "");
    }

    private static Subject latin() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Cinquieme.SUBJECT_CINQUIEME_LATIN_ID, GradeProvider.cinquiemeGrade, "Latin", false,
                60, 60, 60 * 2, 2,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

}
