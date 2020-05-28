package io.edukativ.schooling.myskoolin.domain.providers.subjects;

import io.edukativ.schooling.myskoolin.domain.entity.Subject;
import io.edukativ.schooling.myskoolin.domain.providers.GlobalProvider;
import io.edukativ.schooling.myskoolin.domain.providers.GradeProvider;
import io.edukativ.schooling.myskoolin.domain.vo.EnumDays;
import io.edukativ.schooling.myskoolin.domain.vo.EnumPartsOfDay;
import io.edukativ.schooling.myskoolin.domain.vo.EnumSchoolRoomsTypes;
import io.edukativ.schooling.myskoolin.domain.vo.PreferredPartsOfDays;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class SubjectTroisiemeProvider {

    public static List<Subject> allSubjects() {
        return Arrays.asList(
                francais(), francaisEpi(), maths(), mathsEpi(), sciencesEpi(), histoireGeo(), educationCivique(),
                anglais(), allemand(), svt(), physique(), techno(), sport(), dessin(), musique(), latin()
        );
    }

    private static Subject francais() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Troisieme.SUBJECT_TROISIEME_FRANCAIS_ID, GradeProvider.troisiemeGrade, "FRANCAIS", false,
                60, 60 * 2, 60 * 3, 3,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0);
    }

    /**
     * enseignements pratiques interdisciplinaires - EPI - Francais
     */
    private static Subject francaisEpi() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Troisieme.SUBJECT_TROISIEME_FRANCAIS_EPI_ID, GradeProvider.troisiemeGrade, "Français - EPI", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0);
    }

    private static Subject maths() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Troisieme.SUBJECT_TROISIEME_MATHS_ID, GradeProvider.troisiemeGrade, "MATHEMATIQUES", false,
                60, 60 * 2, (int) (60 * 2.5), 3,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0);
    }

    /**
     * enseignements pratiques interdisciplinaires - EPI - Maths
     */
    private static Subject mathsEpi() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Troisieme.SUBJECT_TROISIEME_MATHS_EPI_ID, GradeProvider.troisiemeGrade, "MATHS - EPI", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0);
    }


    private static Subject histoireGeo() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Troisieme.SUBJECT_TROISIEME_HISTGEO_GEO_ID, GradeProvider.troisiemeGrade, "HISTOIRE GEOGRAPHIE", false,
                60, 60, 60 * 2, 2,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0);
    }

    private static Subject educationCivique() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Troisieme.SUBJECT_TROISIEME_EDUCCIV_ID, GradeProvider.troisiemeGrade, "ENSEIGNEMENT MORAL ET CIVIQUE", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0);

    }

    private static Subject anglais() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Troisieme.SUBJECT_TROISIEME_ANGLAIS_ID, GradeProvider.troisiemeGrade, "ANGLAIS", true,
                60, 60 * 2, 60 * 3, 3,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0);
    }

    private static Subject allemand() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Troisieme.SUBJECT_TROISIEME_ALLEMAND_ID, GradeProvider.troisiemeGrade, "ALLEMAND", true,
                60, 60, 60 * 2, 2,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0);
    }

    private static Subject svt() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Troisieme.SUBJECT_TROISIEME_SVT_ID, GradeProvider.troisiemeGrade, "SCIENCES DE LA VIE ET DE LA TERRE", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.SCIENCES), 1);
    }

    private static Subject physique() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Troisieme.SUBJECT_TROISIEME_PHYSIQUE_ID, GradeProvider.troisiemeGrade, "PHYSIQUE-CHIMIE", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.SCIENCES), 1);
    }

    private static Subject techno() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Troisieme.SUBJECT_TROISIEME_TECHNOLOGIE_ID, GradeProvider.troisiemeGrade, "TECHNOLOGIE", false,
                60, 60, 60, 1,
                Arrays.asList(
                        new PreferredPartsOfDays(EnumDays.MONDAY),
                        new PreferredPartsOfDays(EnumDays.TUESDAY, EnumPartsOfDay.PM),
                        new PreferredPartsOfDays(EnumDays.FRIDAY, EnumPartsOfDay.PM)
                ),
                Collections.singletonList(EnumSchoolRoomsTypes.IT), 1);
    }

    /**
     * enseignements pratiques interdisciplinaires - EPI - Sciences
     */
    private static Subject sciencesEpi() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Troisieme.SUBJECT_TROISIEME_SCIENCES_EPI_ID, GradeProvider.troisiemeGrade, "Sciences - EPI", false,
                (int) (60 * 1.5), (int) (60 * 1.5), (int) (60 * 1.5), 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0);
    }

    private static Subject sport() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Troisieme.SUBJECT_TROISIEME_SPORT_ID, GradeProvider.troisiemeGrade, "EDUCATION PHYSIQUE", false,
                (int) (60 * 1.5), (int) (60 * 1.5), 60 * 3, 2,
                Arrays.asList(
                        new PreferredPartsOfDays(EnumDays.FRIDAY, EnumPartsOfDay.PM),
                        new PreferredPartsOfDays(EnumDays.THURSDAY, EnumPartsOfDay.PM),
                        new PreferredPartsOfDays(EnumDays.FRIDAY, EnumPartsOfDay.PM)
                ),
                Collections.singletonList(EnumSchoolRoomsTypes.SPORT), 1);
    }

    private static Subject dessin() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Troisieme.SUBJECT_TROISIEME_DESSIN_ID, GradeProvider.troisiemeGrade, "ARTS PLASTIQUES", false,
                60, 60, 60, 1,
                Collections.singletonList(new PreferredPartsOfDays(EnumDays.FRIDAY)),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0);
    }

    private static Subject musique() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Troisieme.SUBJECT_TROISIEME_MUSIQUE_ID, GradeProvider.troisiemeGrade, "EDUCATION MUSICALE", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.MUSIC), 0);
    }

    private static Subject latin() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Troisieme.SUBJECT_TROISIEME_LATIN_ID, GradeProvider.troisiemeGrade, "Latin", false,
                60, 60, 60 * 2, 2,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0);
    }
}
