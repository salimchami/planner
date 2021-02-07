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
    public <S, U, T> Constraint<U, T>[] defineConstraints(S solution, ConstraintFactory constraintFactory) {
        return new Constraint[]{
                // Hard constraints
                subjectDurationByDayConflict(solution, constraintFactory)
        };
    }

    private <S> Constraint<Subject, Timeslot> subjectDurationByDayConflict(S solution, ConstraintFactory constraintFactory) {
        return constraintFactory
                .name("Subject Duration By Day")
                .fromMultiple(Timeslot.class)
                .withFact(Subject.class)
                .filter(Subject::getMaxMinutesPerDay)
                .apply(
                        ScoreLevel.HARD,
                        Subject::maxMinutesPerDayPenalty,
                        Timeslot::totalDurationInMinutes);

    }
}
