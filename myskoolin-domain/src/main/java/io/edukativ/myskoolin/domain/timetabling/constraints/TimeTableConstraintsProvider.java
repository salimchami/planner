package io.edukativ.myskoolin.domain.timetabling.constraints;

import io.edukativ.myskoolin.domain.timetabling.Lesson;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;

import static io.edukativ.myskoolin.domain.timetabling.constraints.TimeTableConstraintConfiguration.CONSTRAINT_SAME_SCHOOLROOM_IF_CONSECUTIVE_LESSONS;
import static io.edukativ.myskoolin.domain.timetabling.constraints.TimeTableConstraintConfiguration.CONSTRAINT_SCHOOL_ROOM_TYPE;
import static org.optaplanner.core.api.score.stream.Joiners.filtering;

public class TimeTableConstraintsProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory factory) {
        return new Constraint[]{
                schoolRoomTypesConflicsPenalty(factory)
//                sameSchoolRoomIfConsecutiveLessonsPenalty(constraintFactory)
//                subjectsDayDurationPenalty(constraintFactory),
        };
    }

    public Constraint schoolRoomTypesConflicsPenalty(ConstraintFactory factory) {
        System.out.println("constraint provider !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return factory.from(Lesson.class).filter(lesson -> !lesson.hasRightSchoolRoomType())
                .penalizeConfigurable(CONSTRAINT_SCHOOL_ROOM_TYPE, Lesson::schoolRoomTypeConflictPenalty);
    }

    public Constraint sameSchoolRoomIfConsecutiveLessonsPenalty(ConstraintFactory factory) {
        return factory.from(Lesson.class)
                .join(Lesson.class,
                        filtering((lesson1, lesson2) -> !lesson1.isSameSchoolRoomIfConsecutiveLessons(lesson2)))
                .penalizeConfigurable(CONSTRAINT_SAME_SCHOOLROOM_IF_CONSECUTIVE_LESSONS, Lesson::sameSchoolRoomsConsecutiveLessonsGap);
    }

//    public Constraint subjectsDayDurationPenalty(ConstraintFactory factory) {
//        return factory.from(SchoolClassTimeTable.class)
//                .join(Lesson.class)
//                .filter(SchoolClassTimeTable::subjectDurationByDayExceedsMax)
//                .penalizeConfigurable(CONSTRAINT_SUBJECT_DURATION_MAX_BY_DAY, SchoolClassTimeTable::subjectDurationByDayGap);
//    }

//    public Constraint roomConflict(ConstraintFactory factory) {
//        return factory.fromUniquePair(Lesson.class,
//                filtering(Lesson::isSameOverlappingAndSameSchoolRoom))
//                .penalize("Room conflict", HardSoftScore.ofHard(2));
//    }


//    public Constraint globalConflictsForSameSchoolClass(ConstraintFactory factory) {
//        return factory
//                .from(Lesson.class)
//                .join(Lesson.class)
//                .filter((lesson1, lesson2) -> {
//                    boolean daysEquality = lesson1.getSchoolRoom().equals(lesson2.getSchoolRoom());
//                    boolean startAndEndTimeEquality = lesson1.getSchoolRoom().equals(lesson2.getSchoolRoom());
//                    boolean sameTimeSlot = daysEquality && startAndEndTimeEquality;
//                    boolean sameTeacher = lesson1.getTeacher().equals(lesson2.getTeacher());
//                    boolean sameSubjects = lesson1.getSubject().equals(lesson2.getSubject());
//                    boolean sameSchoolRooms = lesson1.getSchoolRoom().equals(lesson2.getSchoolRoom());
//
//                    return sameTimeSlot || (sameSchoolRooms && sameTeacher && sameSubjects)
//                            || (sameSchoolRooms && sameTeacher && !sameSubjects)
//                            || (sameSchoolRooms && !sameTeacher && sameSubjects)
//                            || (sameSchoolRooms && !sameTeacher && !sameSubjects)
//                            || (!sameSchoolRooms && sameTeacher && sameSubjects)
//                            || (!sameSchoolRooms && sameTeacher && !sameSubjects)
//                            || (!sameSchoolRooms && !sameTeacher && sameSubjects)
//                            || (!sameSchoolRooms && !sameTeacher && !sameSubjects)
//                            ;
//                })
//                .penalize("conflict between school classes penalized", HardSoftScore.ONE_HARD);
//    }


    /*
    *   private Constraint roomUnavailableTimeslot(ConstraintFactory factory) {
        return factory.from(Talk.class)
                .filter(Talk::hasUnavailableRoom)
                .penalizeConfigurable(ROOM_UNAVAILABLE_TIMESLOT,
                        Talk::getDurationInMinutes);
    }
    * */

//
//    public Constraint teacherConflict(ConstraintFactory factory) {
//        // A teacher can teach at most one lesson at the same time.
//        return factory
//                .fromUniquePair(Lesson.class,
//                        equal(Lesson::getTimeSlot),
//                        equal(Lesson::getTeacher))
//                .penalize("Teacher conflict", HardSoftScore.ONE_HARD);
//    }
//
//    public Constraint schoolClassConflict(ConstraintFactory factory) {
//        // A student can attend at most one lesson at the same time.
//        return factory
//                .fromUniquePair(Lesson.class,
//                        equal(Lesson::getTimeSlot),
//                        equal(Lesson::getSchoolClass))
//                .penalize("School classes conflict", HardSoftScore.ONE_HARD);
//    }
//
//    public Constraint teacherRoomStability(ConstraintFactory factory) {
//        // A teacher prefers to teach in a single room.
//        return factory
//                .fromUniquePair(Lesson.class,
//                        equal(Lesson::getTeacher))
//                .filter((lesson1, lesson2) -> lesson1.getSchoolRoom() != lesson2.getSchoolRoom())
//                .penalize("Teacher room stability", HardSoftScore.ONE_SOFT);
//    }
//
//    public Constraint teacherTimeEfficiency(ConstraintFactory factory) {
//        // A teacher prefers to teach sequential lessons and dislikes gaps between lessons.
//        return factory
//                .from(Lesson.class)
//                .join(Lesson.class, equal(Lesson::getTeacher),
//                        equal(lesson -> lesson.getTimeSlot().getDay().getDayOfWeek()))
//                .filter((lesson1, lesson2) -> {
//                    Duration between = Duration.between(lesson1.getTimeSlot().getEndTime().toLocalTime(),
//                            lesson2.getTimeSlot().getStartTime().toLocalTime());
//                    return !between.isNegative() && between.compareTo(Duration.ofMinutes(30)) <= 0;
//                })
//                .reward("Teacher time efficiency", HardSoftScore.ONE_SOFT);
//    }
//
//    public Constraint schoolClassSubjectVariety(ConstraintFactory factory) {
//        // A student group dislikes sequential lessons on the same subject.
//        return factory
//                .from(Lesson.class)
//                .join(Lesson.class,
//                        equal(Lesson::getSubject),
//                        equal(Lesson::getSchoolClass),
//                        equal(lesson -> lesson.getTimeSlot().getDay().getDayOfWeek()))
//                .filter((lesson1, lesson2) -> {
//                    Duration between = Duration.between(lesson1.getTimeSlot().getEndTime().toLocalTime(),
//                            lesson2.getTimeSlot().getStartTime().toLocalTime());
//                    return !between.isNegative() && between.compareTo(Duration.ofMinutes(30)) <= 0;
//                })
//                .penalize("school class subject variety", HardSoftScore.ONE_SOFT);
//    }
//
}
