package io.edukativ.myskoolin.planner.constraints;

import io.edukativ.myskoolin.planner.*;
import io.edukativ.myskoolin.planner.declarations.ConstraintsProvider;
import io.edukativ.myskoolin.planner.entities.Subject;
import io.edukativ.myskoolin.planner.entities.Timeslot;

import java.util.List;

@ConstraintsProvider
public class TimeTableConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
                // Hard constraints
                subjectDurationByDayConflict(constraintFactory)
        };
    }

    private Constraint<Subject, Timeslot> subjectDurationByDayConflict(ConstraintFactory constraintFactory) {
        return constraintFactory
                .fromMultiple(Timeslot.class)
                .withFact(Subject.class)
                .filter(Subject::getMaxMinutesPerDay)
                .apply("Subject Duration By Day", ScoreLevel.HARD,  (Subject subject, List<Timeslot> timeSlots) -> {
                    return subject.maxMinutesPerDayPenalty(Timeslot.totalDurationInMinutes(timeSlots));
                }, Timeslot::totalDurationInMinutes);

    }
}
