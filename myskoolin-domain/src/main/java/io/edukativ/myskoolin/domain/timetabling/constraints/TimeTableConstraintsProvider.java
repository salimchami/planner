package io.edukativ.myskoolin.domain.timetabling.constraints;

import io.edukativ.myskoolin.domain.subjects.Subject;
import io.edukativ.myskoolin.domain.timetabling.Lesson;
import io.edukativ.myskoolin.domain.timetabling.SchoolClassTimeTable;
import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;

import static io.edukativ.myskoolin.domain.timetabling.constraints.TimeTableConstraintConfiguration.*;
import static org.optaplanner.core.api.score.stream.Joiners.filtering;

public class TimeTableConstraintsProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
                timeSlotsOverlappingConflictPenalty(constraintFactory),
                schoolRoomTypeReward(constraintFactory),
                subjectsDayDurationPenalty(constraintFactory),
        };
    }

    private Constraint subjectsDayDurationPenalty(ConstraintFactory constraintFactory) {
        return constraintFactory.from(SchoolClassTimeTable.class)
                .join(Lesson.class)
                .filter(SchoolClassTimeTable::subjectDurationByDayExceedsMax)
                .penalizeConfigurable(CONSTRAINT_SUBJECT_DURATION_BY_DAY, SchoolClassTimeTable::subjectDurationByDayGap);
    }

    public Constraint timeSlotsOverlappingConflictPenalty(ConstraintFactory constraintFactory) {
        return constraintFactory.fromUniquePair(Lesson.class,
                filtering(Lesson::isOverlapping))
                .penalizeConfigurable(CONSTRAINT_TIMESLOTS_OVERLAPS, Lesson::overlappingGap);
    }

    public Constraint schoolRoomTypeReward(ConstraintFactory constraintFactory) {
        return constraintFactory
                .from(Lesson.class)
                .join(Subject.class)
                .filter((lesson, subject) -> subject.getSchoolRoomsTypes().contains(lesson.getSchoolRoom().getType()))
                .reward(CONSTRAINT_SCHOOL_TYPE_REWARD, HardMediumSoftScore.ofMedium(1));
    }

//    public Constraint roomConflict(ConstraintFactory constraintFactory) {
//        return constraintFactory.fromUniquePair(Lesson.class,
//                filtering(Lesson::isSameOverlappingAndSameSchoolRoom))
//                .penalize("Room conflict", HardSoftScore.ofHard(2));
//    }


//    public Constraint globalConflictsForSameSchoolClass(ConstraintFactory constraintFactory) {
//        return constraintFactory
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
//    public Constraint teacherConflict(ConstraintFactory constraintFactory) {
//        // A teacher can teach at most one lesson at the same time.
//        return constraintFactory
//                .fromUniquePair(Lesson.class,
//                        equal(Lesson::getTimeSlot),
//                        equal(Lesson::getTeacher))
//                .penalize("Teacher conflict", HardSoftScore.ONE_HARD);
//    }
//
//    public Constraint schoolClassConflict(ConstraintFactory constraintFactory) {
//        // A student can attend at most one lesson at the same time.
//        return constraintFactory
//                .fromUniquePair(Lesson.class,
//                        equal(Lesson::getTimeSlot),
//                        equal(Lesson::getSchoolClass))
//                .penalize("School classes conflict", HardSoftScore.ONE_HARD);
//    }
//
//    public Constraint teacherRoomStability(ConstraintFactory constraintFactory) {
//        // A teacher prefers to teach in a single room.
//        return constraintFactory
//                .fromUniquePair(Lesson.class,
//                        equal(Lesson::getTeacher))
//                .filter((lesson1, lesson2) -> lesson1.getSchoolRoom() != lesson2.getSchoolRoom())
//                .penalize("Teacher room stability", HardSoftScore.ONE_SOFT);
//    }
//
//    public Constraint teacherTimeEfficiency(ConstraintFactory constraintFactory) {
//        // A teacher prefers to teach sequential lessons and dislikes gaps between lessons.
//        return constraintFactory
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
//    public Constraint schoolClassSubjectVariety(ConstraintFactory constraintFactory) {
//        // A student group dislikes sequential lessons on the same subject.
//        return constraintFactory
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
