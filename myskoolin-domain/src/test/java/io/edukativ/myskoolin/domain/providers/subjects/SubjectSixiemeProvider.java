package io.edukativ.myskoolin.domain.providers.subjects;

import io.edukativ.myskoolin.domain.entity.Subject;
import io.edukativ.myskoolin.domain.providers.GlobalProvider;
import io.edukativ.myskoolin.domain.vo.EnumDays;
import io.edukativ.myskoolin.domain.vo.EnumPartsOfDay;
import io.edukativ.myskoolin.domain.vo.EnumSchoolRoomsTypes;
import io.edukativ.myskoolin.domain.vo.PreferredPartsOfDays;
import io.edukativ.myskoolin.domain.providers.GradeProvider;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class SubjectSixiemeProvider {

    public static List<Subject> allSubjects() {
        return Arrays.asList(
                francais(), francaisEpi(), maths(), mathsEpi(), sciencesEpi(), histoireGeo(), educationCivique(),
                anglais(), allemand(), svt(), physique(), techno(), sport(), dessin(), musique()
        );
    }

    private static Subject francais() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Sixieme.SUBJECT_SIXIEME_FRANCAIS_ID, GradeProvider.sixiemeGrade, "FRANCAIS", false,
                60, 60 * 2, (int) (60 * 3.5), 3,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    /**
     * enseignements pratiques interdisciplinaires - EPI - Francais
     */
    private static Subject francaisEpi() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Sixieme.SUBJECT_SIXIEME_FRANCAIS_EPI_ID, GradeProvider.sixiemeGrade, "Français - EPI", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject maths() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Sixieme.SUBJECT_SIXIEME_MATHS_ID, GradeProvider.sixiemeGrade, "MATHEMATIQUES", false,
                60, 60 * 2, (int) (60 * 3.5), 3,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    /**
     * enseignements pratiques interdisciplinaires - EPI - Maths
     */
    private static Subject mathsEpi() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Sixieme.SUBJECT_SIXIEME_MATHS_EPI_ID, GradeProvider.sixiemeGrade, "MATHS - EPI", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }


    private static Subject histoireGeo() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Sixieme.SUBJECT_SIXIEME_HISTGEO_GEO_ID, GradeProvider.sixiemeGrade, "HISTOIRE GEOGRAPHIE", false,
                60, 60, 60 * 2, 2,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject educationCivique() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Sixieme.SUBJECT_SIXIEME_EDUCCIV_ID, GradeProvider.sixiemeGrade, "ENSEIGNEMENT MORAL ET CIVIQUE", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");

    }

    private static Subject anglais() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Sixieme.SUBJECT_SIXIEME_ANGLAIS_ID, GradeProvider.sixiemeGrade, "ANGLAIS", true,
                60, 60 * 2, 60 * 4, 4,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject allemand() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Sixieme.SUBJECT_SIXIEME_ALLEMAND_ID, GradeProvider.sixiemeGrade, "ALLEMAND", true,
                60, 60, 60 * 2, 2,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject svt() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Sixieme.SUBJECT_SIXIEME_SVT_ID, GradeProvider.sixiemeGrade, "SCIENCES DE LA VIE ET DE LA TERRE", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.SCIENCES), 1, "");
    }

    private static Subject physique() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Sixieme.SUBJECT_SIXIEME_PHYSIQUE_ID, GradeProvider.sixiemeGrade, "PHYSIQUE-CHIMIE", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.SCIENCES), 1, "");
    }

    private static Subject techno() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Sixieme.SUBJECT_SIXIEME_TECHNOLOGIE_ID, GradeProvider.sixiemeGrade, "TECHNOLOGIE", false,
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
                GlobalProvider.Subjects.Sixieme.SUBJECT_SIXIEME_SCIENCES_EPI_ID, GradeProvider.sixiemeGrade, "Sciences - EPI", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject sport() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Sixieme.SUBJECT_SIXIEME_SPORT_ID, GradeProvider.sixiemeGrade, "EDUCATION PHYSIQUE", false,
                120, 60 * 2, 60 * 4, 2,
                Arrays.asList(
                        new PreferredPartsOfDays(EnumDays.FRIDAY, EnumPartsOfDay.PM),
                        new PreferredPartsOfDays(EnumDays.THURSDAY, EnumPartsOfDay.PM),
                        new PreferredPartsOfDays(EnumDays.FRIDAY, EnumPartsOfDay.PM)
                ),
                Collections.singletonList(EnumSchoolRoomsTypes.SPORT), 1, "");
    }

    private static Subject dessin() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Sixieme.SUBJECT_SIXIEME_DESSIN_ID, GradeProvider.sixiemeGrade, "ARTS PLASTIQUES", false,
                60, 60, 60, 1,
                Collections.singletonList(new PreferredPartsOfDays(EnumDays.FRIDAY)),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject musique() {
        return SubjectProvider.subject(
                GlobalProvider.Subjects.Sixieme.SUBJECT_SIXIEME_MUSIQUE_ID, GradeProvider.sixiemeGrade, "EDUCATION MUSICALE", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.MUSIC), 0, "");
    }
}
