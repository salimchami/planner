package io.edukativ.myskoolin.domain.timetabling;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;

import static org.optaplanner.core.api.score.stream.Joiners.equal;

public class TimeTableConstraintsProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
                timeSlotConflictPenalty(constraintFactory),
                roomConflict(constraintFactory),
                conflictBetweenTimeTables(constraintFactory),
//                roomConflict(constraintFactory),
//                subjectsDurationByWeek(constraintFactory),
//                teacherConflict(constraintFactory),
//                schoolClassConflict(constraintFactory),
//                teacherRoomStability(constraintFactory),
//                teacherTimeEfficiency(constraintFactory),
//                schoolClassSubjectVariety(constraintFactory)
        };
    }

    /**
     * A student can attend at most one lesson at the same time.
     * If the timeslot overlaps an other timeslot, it cannot be added.
     */
    public Constraint timeSlotConflictPenalty(ConstraintFactory constraintFactory) {
        return constraintFactory
                .from(Lesson.class)
                .join(Lesson.class)
                .filter(Lesson::isOverlapping)
                .reward("time slot conflict penalty", HardSoftScore.ofHard(2))
                ;
    }

    public Constraint roomConflict(ConstraintFactory constraintFactory) {
        // A room can accommodate at most one lesson at the same time.
        return constraintFactory
                // Select each pair of 2 different lessons ...
                .fromUniquePair(Lesson.class,
                        // ... in the same timeslot ...
                        equal(Lesson::getTimeSlot),
                        // ... in the same room ...
                        equal(Lesson::getSchoolRoom))
                // ... and penalize each pair with a hard weight.
                .penalize("Room conflict", HardSoftScore.ofHard(2));
    }

    public Constraint conflictBetweenTimeTables(ConstraintFactory constraintFactory) {
        return constraintFactory
                .from(Lesson.class)
                .join(Lesson.class)
                .filter((lesson1, lesson2) -> {
                    boolean subjectsEquality = lesson1.getSubject().equals(lesson2.getSubject());
                    boolean schoolRoomsEquality = lesson1.getSchoolRoom().equals(lesson2.getSchoolRoom());
                    boolean daysEquality = lesson1.getSchoolRoom().equals(lesson2.getSchoolRoom());
                    boolean startAndEndTimeEquality = lesson1.getSchoolRoom().equals(lesson2.getSchoolRoom());
                    if (lesson1.getSchoolClass().equals(lesson2.getSchoolClass())) {
                        return schoolRoomsEquality && daysEquality && startAndEndTimeEquality && !subjectsEquality;
                    } else {
                        return schoolRoomsEquality && daysEquality && startAndEndTimeEquality;
                    }
                })
                .penalize("conflict between school classes penalized", HardSoftScore.ofHard(2));
    }
//
//    /**
//     * A student can attend at most one lesson at the same time.
//     * If the timeslot overlaps an other timeslot, it cannot be added.
//     */
//    public Constraint timeSlotConflictReward(ConstraintFactory constraintFactory) {
//        return constraintFactory
//                .from(Lesson.class)
//                .join(Lesson.class)
//                .filter((lesson1, lesson2) -> !lesson1.isOverlapping(lesson2))
//                .reward("time slot conflict reward", HardSoftScore.ONE_HARD);
//    }
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
