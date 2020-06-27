package io.edukativ.myskoolin.domain.timetabling.constraints;

import io.edukativ.myskoolin.domain.timetabling.Lesson;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;

public class TimeSlotConstraintProvider {

    /**
     * A student can attend at most one lesson at the same time.
     * If the timeslot overlaps an other timeslot, it cannot be added.
     */
    public Constraint timeSlotConflict(ConstraintFactory constraintFactory) {
        return constraintFactory
                .from(Lesson.class)
                .join(Lesson.class)
                .filter((lesson1, lesson2) -> lesson1.getTimeSlot().isOverlapping(lesson2.getTimeSlot()))
                .penalize("Student school class conflict", HardSoftScore.ONE_HARD);
    }

}
