package io.edukativ.myskoolin.domain.providers.subjects;

import io.edukativ.myskoolin.domain.entity.Subject;
import io.edukativ.myskoolin.domain.providers.GlobalTestProvider;
import io.edukativ.myskoolin.domain.vo.EnumDays;
import io.edukativ.myskoolin.domain.vo.EnumPartsOfDay;
import io.edukativ.myskoolin.domain.vo.EnumSchoolRoomsTypes;
import io.edukativ.myskoolin.domain.vo.PreferredPartsOfDays;
import io.edukativ.myskoolin.domain.providers.GradeTestProvider;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class SubjectQuatriemeTestProvider {

    public static List<Subject> allSubjects() {
        return Arrays.asList(
                francais(), francaisEpi(), maths(), mathsEpi(), sciencesEpi(), histoireGeo(), educationCivique(),
                anglais(), allemand(), svt(), physique(), techno(), sport(), dessin(), musique(), latin()
        );
    }

    private static Subject francais() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_FRANCAIS_ID, GradeTestProvider.quatriemeGrade, "FRANCAIS", false,
                60, 60 * 2, (int) (60 * 3.5), 3,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    /**
     * enseignements pratiques interdisciplinaires - EPI - Francais
     */
    private static Subject francaisEpi() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_FRANCAIS_EPI_ID, GradeTestProvider.quatriemeGrade, "Français - EPI", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject maths() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_MATHS_ID, GradeTestProvider.quatriemeGrade, "MATHEMATIQUES", false,
                60, 60 * 2, (int) (60 * 2.5), 3,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    /**
     * enseignements pratiques interdisciplinaires - EPI - Maths
     */
    private static Subject mathsEpi() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_MATHS_EPI_ID, GradeTestProvider.quatriemeGrade, "MATHS - EPI", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }


    private static Subject histoireGeo() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_HISTGEO_GEO_ID, GradeTestProvider.quatriemeGrade, "HISTOIRE GEOGRAPHIE", false,
                60, 60, 60 * 2, 2,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject educationCivique() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_EDUCCIV_ID, GradeTestProvider.quatriemeGrade, "ENSEIGNEMENT MORAL ET CIVIQUE", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");

    }

    private static Subject anglais() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_ANGLAIS_ID, GradeTestProvider.quatriemeGrade, "ANGLAIS", true,
                60, 60 * 2, 60 * 3, 3,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject allemand() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_ALLEMAND_ID, GradeTestProvider.quatriemeGrade, "ALLEMAND", true,
                60, 60, 60 * 2, 2,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject svt() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_SVT_ID, GradeTestProvider.quatriemeGrade, "SCIENCES DE LA VIE ET DE LA TERRE", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.SCIENCES), 1, "");
    }

    private static Subject physique() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_PHYSIQUE_ID, GradeTestProvider.quatriemeGrade, "PHYSIQUE-CHIMIE", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.SCIENCES), 1, "");
    }

    private static Subject techno() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_TECHNOLOGIE_ID, GradeTestProvider.quatriemeGrade, "TECHNOLOGIE", false,
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
                GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_SCIENCES_EPI_ID, GradeTestProvider.quatriemeGrade, "Sciences - EPI", false,
                (int) (60 * 1.5), (int) (60 * 1.5), (int) (60 * 1.5), 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject sport() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_SPORT_ID, GradeTestProvider.quatriemeGrade, "EDUCATION PHYSIQUE", false,
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
                GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_DESSIN_ID, GradeTestProvider.quatriemeGrade, "ARTS PLASTIQUES", false,
                60, 60, 60, 1,
                Collections.singletonList(new PreferredPartsOfDays(EnumDays.FRIDAY)),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }

    private static Subject musique() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_MUSIQUE_ID, GradeTestProvider.quatriemeGrade, "EDUCATION MUSICALE", false,
                60, 60, 60, 1,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.MUSIC), 0, "");
    }

    private static Subject latin() {
        return SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Quatrieme.SUBJECT_QUATRIEME_LATIN_ID, GradeTestProvider.quatriemeGrade, "Latin", false,
                60, 60, 60 * 2, 2,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
    }
}
