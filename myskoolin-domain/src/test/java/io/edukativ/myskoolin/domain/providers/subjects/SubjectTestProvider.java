package io.edukativ.myskoolin.domain.providers.subjects;

import io.edukativ.myskoolin.domain.entity.Grade;
import io.edukativ.myskoolin.domain.entity.Subject;
import io.edukativ.myskoolin.domain.providers.GlobalTestProvider;
import io.edukativ.myskoolin.domain.schoolrooms.EnumSchoolRoomsTypes;
import io.edukativ.myskoolin.domain.timetabling.PreferredPartsOfDays;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SubjectTestProvider {

    public static Subject subject(String id, Grade grade, String name, Boolean foreignLanguage, Integer minMinutesPerDay,
                                  Integer maxMinutesPerDay, Integer minutesPerWeek, Integer coursesFrequencyPerWeek,
                                  List<PreferredPartsOfDays> preferredDays, List<EnumSchoolRoomsTypes> schoolRoomsTypes,
                                  Integer daysBetweenTimeSlots, String comment) {
        return new Subject(id, GlobalTestProvider.CLIENT_ID, name, "", grade, null,
                BigDecimal.ONE, "color", "bgColor", foreignLanguage, maxMinutesPerDay, minMinutesPerDay,
                minutesPerWeek, coursesFrequencyPerWeek, false, comment, preferredDays, schoolRoomsTypes, daysBetweenTimeSlots);
    }

    public static List<Subject> allSubjects() {
        return Stream.of(
                SubjectSixiemeTestProvider.allSubjects(),
                SubjectCinquiemeTestProvider.allSubjects(),
                SubjectQuatriemeTestProvider.allSubjects(),
                SubjectTroisiemeTestProvider.allSubjects()
//                SubjectSecondeProvider.allSubjects(),
//                SubjectPremiereProvider.allSubjects(),
//                SubjectTerminaleProvider.allSubjects()
        ).flatMap(Collection::stream).collect(Collectors.toList());
    }
}

