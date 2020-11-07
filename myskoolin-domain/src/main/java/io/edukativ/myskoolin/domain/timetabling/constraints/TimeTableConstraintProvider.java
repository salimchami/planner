package io.edukativ.myskoolin.domain.timetabling.constraints;

import io.edukativ.myskoolin.domain.timetabling.Lesson;
import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;

import java.time.Duration;

public class TimeTableConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
                // Hard constraints
                roomConflict(constraintFactory),
                //teacherConflict(constraintFactory),
                //studentGroupConflict(constraintFactory),
                rightSchoolRoomType(constraintFactory),
                teacherRoomStability(constraintFactory),
                studentGroupSubjectVariety(constraintFactory)
                // Soft constraints
                //teacherTimeEfficiency(constraintFactory),
        };
    }

    private Constraint rightSchoolRoomType(ConstraintFactory constraintFactory) {
        return constraintFactory
                .fromUnfiltered(Lesson.class)
                .filter(lesson -> !lesson.hasRightSchoolRoomType())
                .penalize("Room Type", HardMediumSoftScore.ONE_HARD);
    }

    private Constraint roomConflict(ConstraintFactory constraintFactory) {
        // A room can accommodate at most one lesson at the same time.
        return constraintFactory
                // Select each pair of 2 different lessons ...
                .fromUniquePair(Lesson.class,
                        // ... in the same timeslot ...
                        Joiners.equal(Lesson::getTimeSlot),
                        // ... in the same room ...
                        Joiners.equal(Lesson::getSchoolRoom))
                // ... and penalize each pair with a hard weight.
                .penalize("Room conflict", HardMediumSoftScore.ONE_HARD);
    }

    private Constraint teacherConflict(ConstraintFactory constraintFactory) {
        // A teacher can teach at most one lesson at the same time.
        return constraintFactory
                .fromUniquePair(Lesson.class,
                        Joiners.equal(Lesson::getTimeSlot),
                        Joiners.equal(Lesson::getTeacher))
                .penalize("Teacher conflict", HardMediumSoftScore.ONE_HARD);
    }

    private Constraint studentGroupConflict(ConstraintFactory constraintFactory) {
        // A student can attend at most one lesson at the same time.
        return constraintFactory
                .fromUniquePair(Lesson.class,
                        Joiners.equal(Lesson::getTimeSlot),
                        Joiners.equal(Lesson::getSchoolClass))
                .penalize("Student group conflict", HardMediumSoftScore.ONE_HARD);
    }

    private Constraint teacherRoomStability(ConstraintFactory constraintFactory) {
        // A teacher prefers to teach in a single room.
        return constraintFactory
                .fromUniquePair(Lesson.class,
                        Joiners.equal(Lesson::getTeacher))
                .filter((lesson1, lesson2) -> lesson1.getSchoolRoom() != lesson2.getSchoolRoom())
                .penalize("Teacher room stability", HardMediumSoftScore.ONE_HARD);
    }

    private Constraint teacherTimeEfficiency(ConstraintFactory constraintFactory) {
        // A teacher prefers to teach sequential lessons and dislikes gaps between lessons.
        return constraintFactory
                .from(Lesson.class)
                .join(Lesson.class, Joiners.equal(Lesson::getTeacher),
                        Joiners.equal(lesson -> lesson.getTimeSlot().getDay()))
                .filter((lesson1, lesson2) -> {
                    Duration between = Duration.between(lesson1.getTimeSlot().getEndTime(),
                            lesson2.getTimeSlot().getStartTime());
                    return !between.isNegative() && between.compareTo(Duration.ofMinutes(30)) <= 0;
                })
                .reward("Teacher time efficiency", HardMediumSoftScore.ONE_SOFT);
    }

    private Constraint studentGroupSubjectVariety(ConstraintFactory constraintFactory) {
        // A student group dislikes sequential lessons on the same subject.
        return constraintFactory
                .from(Lesson.class)
                .join(Lesson.class,
                        Joiners.equal(Lesson::getSubject),
                        Joiners.equal(Lesson::getSchoolClass),
                        Joiners.equal(lesson -> lesson.getTimeSlot().getDay()))
                .filter((lesson1, lesson2) -> {
                    Duration between = Duration.between(lesson1.getTimeSlot().getEndTime(),
                            lesson2.getTimeSlot().getStartTime());
                    return !between.isNegative() && between.compareTo(Duration.ofMinutes(120)) <= 0;
                })
                .penalize("Student group subject variety", HardMediumSoftScore.ONE_HARD);
    }

}
