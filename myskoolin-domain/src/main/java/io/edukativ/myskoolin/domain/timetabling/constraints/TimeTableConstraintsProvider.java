package io.edukativ.myskoolin.domain.timetabling.constraints;

import io.edukativ.myskoolin.domain.timetabling.Lesson;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;

public class TimeTableConstraintsProvider implements ConstraintProvider {

    /**
     * A student can attend at most one lesson at the same time.
     * If the timeslot overlaps an other timeslot, it cannot be added.
     */
    private Constraint timeSlotConflict(ConstraintFactory constraintFactory) {
        return constraintFactory
                .from(Lesson.class)
                .join(Lesson.class)
                .filter((lesson1, lesson2) -> lesson1.getTimeSlot().isOverlapping(lesson2.getTimeSlot()))
                .penalize("Student school class conflict", HardSoftScore.ONE_HARD);
    }

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
                timeSlotConflict(constraintFactory)
        };
    }
}
