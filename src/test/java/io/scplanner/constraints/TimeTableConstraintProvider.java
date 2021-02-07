package io.scplanner.constraints;

import io.scplanner.Constraint;
import io.scplanner.ConstraintFactory;
import io.scplanner.ConstraintProvider;
import io.scplanner.ScoreLevel;
import io.scplanner.annotations.ConstraintsProvider;
import io.scplanner.entities.Subject;
import io.scplanner.entities.Timeslot;

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
                .apply("Subject Duration By Day",
                        ScoreLevel.HARD,
                        timeslot -> timeslot,
                        Subject::maxMinutesPerDayPenalty,
                        Timeslot::totalDurationInMinutes);

    }
}
