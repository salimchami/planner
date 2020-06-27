package io.edukativ.myskoolin.domain.timetabling;

import io.edukativ.myskoolin.domain.schoolclasses.SchoolClass;
import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoom;
import io.edukativ.myskoolin.domain.subjects.Subject;
import io.edukativ.myskoolin.domain.commons.entity.User;
import io.edukativ.myskoolin.domain.schoolrooms.EnumSchoolRoomsTypes;
import io.edukativ.myskoolin.domain.teachers.Teacher;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class SchoolResourcesNeeds {

    private long totalRefWeekDuration;
    private Map<SchoolClass, List<Subject>> schoolClasses;
    private List<EnumSchoolRoomsTypes> insufficientSchoolRoomTypes;
    private List<String> insufficientTeachersForSubject;

    public SchoolResourcesNeeds(Map<SchoolClass, List<Subject>> schoolClasses, long totalRefWeekDuration) {
        this.schoolClasses = schoolClasses;
        this.totalRefWeekDuration = totalRefWeekDuration;
        this.insufficientSchoolRoomTypes = new ArrayList<>();
        this.insufficientTeachersForSubject = new ArrayList<>();
    }

    public boolean isResourcesSufficient(List<User> teachers, List<SchoolRoom> schoolRooms) {
//        final SchoolingResources minimumResources = new SchoolingResources(minimumSchoolRoomsByType(), minimumTeachersBySubject());
//        return isTeachersSufficient(teachers, minimumResources.getTeachersBySubject())
//                && isSchoolRoomsSufficient(schoolRooms, minimumResources.getSchoolRoomsByType());
        return true;
    }

    private boolean isSchoolRoomsSufficient(List<SchoolRoom> schoolRooms, Map<EnumSchoolRoomsTypes, Integer> schoolRoomsByType) {
        for (Map.Entry<EnumSchoolRoomsTypes, Integer> entry : schoolRoomsByType.entrySet()) {
            final long count = schoolRooms.stream().filter(schoolRoom -> schoolRoom.getType().equals(entry.getKey())).count();
            if (count != entry.getValue()) {
                insufficientSchoolRoomTypes.add(entry.getKey());
                return false;
            }
        }
        return true;
    }

    private boolean isTeachersSufficient(List<Teacher> teachers, Map<Subject, Integer> teachersBySubject) {
        for (Map.Entry<Subject, Integer> entry : teachersBySubject.entrySet()) {
            final long count = teachers.stream().filter(schoolRoom -> schoolRoom.getTaughtSubjects().contains(entry.getKey().getId())).count();
            if (count != entry.getValue()) {
                insufficientTeachersForSubject.add(entry.getKey().getId());
                return false;
            }
        }
        return true;
    }

    private Map<Subject, Integer> minimumTeachersBySubject() {
        Map<Subject, Integer> minTeachersBySubject = new HashMap<>();
        Map<Subject, Integer> totalDurationsForSubjects = new HashMap<>();
        for (Map.Entry<SchoolClass, List<Subject>> entry : schoolClasses.entrySet()) {
            for (Subject subject : entry.getValue()) {
                totalDurationsForSubjects.put(subject, addOrCreateDurationBySubject(totalDurationsForSubjects, subject));
            }
        }
        for (Map.Entry<Subject, Integer> totalDurationEntry : totalDurationsForSubjects.entrySet()) {
            minTeachersBySubject.put(totalDurationEntry.getKey(),
                    BigDecimal.valueOf(totalDurationsForSubjects.get(totalDurationEntry.getKey()))
                            .divide(BigDecimal.valueOf(totalRefWeekDuration), RoundingMode.HALF_UP).intValue());
        }
        return minTeachersBySubject;
    }

    private Integer addOrCreateDurationBySubject(Map<Subject, Integer> teachersBySubject, Subject subject) {
        if (teachersBySubject.get(subject) != null) {
            return teachersBySubject.get(subject) + subject.getMinutesPerWeek();
        }
        return subject.getMinutesPerWeek();
    }

//    private Integer addOrCreateDurationBySubjectForSchoolRoom(Map<EnumSchoolRoomsTypes, Integer> nbSchoolRoomsByType, EnumSchoolRoomsTypes enumSchoolRoomsTypes) {
//        if (nbSchoolRoomsByType.containsKey(enumSchoolRoomsTypes)) {
//            return nbSchoolRoomsByType.get(enumSchoolRoomsTypes) + enumSchoolRoomsTypes;
//        }
//        return subject.getMinutesPerWeek();
//    }


//    private Map<EnumSchoolRoomsTypes, Integer> minimumSchoolRoomsByType() {
//        Map<EnumSchoolRoomsTypes, Integer> minSchoolRoomsByType = new EnumMap<>(EnumSchoolRoomsTypes.class);
//        Map<EnumSchoolRoomsTypes, Integer> totalDurationsForSchoolRooms = new EnumMap<>(EnumSchoolRoomsTypes.class);
//        for (Map.Entry<SchoolClass, List<Subject>> entry : schoolClasses.entrySet()) {
//            for (Subject subject : entry.getValue()) {
//                for (EnumSchoolRoomsTypes schoolRoomsType : subject.getSchoolRoomsTypes()) {
//                    totalDurationsForSchoolRooms.put(schoolRoomsType, addOrCreateDurationBySubjectForSchoolRoom(totalDurationsForSchoolRooms, schoolRoomsType));
//                }
//            }
//        }
//        for (Map.Entry<EnumSchoolRoomsTypes, Integer> totalDurationEntry : totalDurationsForSchoolRooms.entrySet()) {
//            minSchoolRoomsByType.put(totalDurationEntry.getKey(),
//                    BigDecimal.valueOf(totalDurationsForSchoolRooms.get(totalDurationEntry.getKey()))
//                            .divide(BigDecimal.valueOf(totalRefWeekDuration), RoundingMode.HALF_UP).intValue());
//        }
//        return minSchoolRoomsByType;
//    }

    public List<EnumSchoolRoomsTypes> getInsufficientSchoolRoomTypes() {
        return Collections.unmodifiableList(insufficientSchoolRoomTypes);
    }

    public List<String> getInsufficientTeachersForSubject() {
        return Collections.unmodifiableList(insufficientTeachersForSubject);
    }

}
