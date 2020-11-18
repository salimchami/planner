package io.edukativ.myskoolin.planner.constraints;

import io.edukativ.myskoolin.planner.*;
import io.edukativ.myskoolin.planner.declarations.ConstraintsProvider;
import io.edukativ.myskoolin.planner.entities.Subject;
import io.edukativ.myskoolin.planner.entities.Timeslot;

import java.util.List;

@ConstraintsProvider
public class TimeTableConstraintProvider implements ConstraintProvider {

    @Override
    public ConstraintContract[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
                // Hard constraints
                subjectDurationByDayConflict(constraintFactory),
                subjectDurationByDayConflict(constraintFactory),
                subjectDurationByDayConflict(constraintFactory),
                subjectDurationByDayConflict(constraintFactory),
                subjectDurationByDayConflict(constraintFactory),
                subjectDurationByDayConflict(constraintFactory)
        };
    }

    private Constraint subjectDurationByDayConflict(ConstraintFactory constraintFactory) {
        return constraintFactory
                .fromMultiple(Timeslot.class)
                .withFact(Subject.class)
                .filter(Subject::getMaxMinutesPerDay)
                .filter(Subject::getMinMinutesPerDay)
                .apply("Subject Duration By Day", ScoreLevel.HARD, (Subject subject, List<Timeslot> timeSlots) ->
                        subject.maxMinutesPerDayPenalty(Timeslot.totalDuration(timeSlots)));

    }
}
