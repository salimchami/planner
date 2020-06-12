package io.edukativ.myskoolin.domain.providers.subjects;

import io.edukativ.myskoolin.domain.entity.Grade;
import io.edukativ.myskoolin.domain.entity.Subject;
import io.edukativ.myskoolin.domain.providers.GlobalProvider;
import io.edukativ.myskoolin.domain.vo.EnumSchoolRoomsTypes;
import io.edukativ.myskoolin.domain.vo.PreferredPartsOfDays;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SubjectProvider {

    public static Subject subject(String id, Grade grade, String name, Boolean foreignLanguage, Integer minMinutesPerDay,
                                  Integer maxMinutesPerDay, Integer minutesPerWeek, Integer coursesFrequencyPerWeek,
                                  List<PreferredPartsOfDays> preferredDays, List<EnumSchoolRoomsTypes> schoolRoomsTypes,
                                  Integer daysBetweenTimeSlots, String comment) {
        return new Subject(id, GlobalProvider.CLIENT_ID, name, "", grade, null,
                BigDecimal.ONE, "color", "bgColor", foreignLanguage, maxMinutesPerDay, minMinutesPerDay,
                minutesPerWeek, coursesFrequencyPerWeek, false, comment, preferredDays, schoolRoomsTypes, daysBetweenTimeSlots);
    }

    public static List<Subject> allSubjects() {
        return Stream.of(
                SubjectSixiemeProvider.allSubjects(),
                SubjectCinquiemeProvider.allSubjects(),
                SubjectQuatriemeProvider.allSubjects(),
                SubjectTroisiemeProvider.allSubjects()
//                SubjectSecondeProvider.allSubjects(),
//                SubjectPremiereProvider.allSubjects(),
//                SubjectTerminaleProvider.allSubjects()
        ).flatMap(Collection::stream).collect(Collectors.toList());
    }
}

