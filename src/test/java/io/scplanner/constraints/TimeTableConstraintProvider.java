package io.scplanner.constraints;

import io.scplanner.Constraint;
import io.scplanner.ConstraintFactory;
import io.scplanner.ConstraintProvider;
import io.scplanner.ScoreLevel;
import io.scplanner.annotations.ConstraintsProvider;
import io.scplanner.entities.Subject;
import io.scplanner.entities.TimeTable;
import io.scplanner.entities.Timeslot;

import java.util.List;

@ConstraintsProvider
public class TimeTableConstraintProvider implements ConstraintProvider<TimeTable> {

    @Override
    public <F, P> Constraint<F, P>[] defineConstraints(TimeTable solution, ConstraintFactory<F, P> constraintFactory) {
        return new Constraint[]{
                // Hard constraints
                subjectDurationByDayConflict(solution, constraintFactory)
        };
    }

    private Constraint<Subject, Timeslot> subjectDurationByDayConflict(TimeTable solution,
                                                                       ConstraintFactory constraintFactory) {
//        return constraintFactory
//                .name("Subject Duration By Day")
//                .fromMultiple(Timeslot.class)
//                .withFact(Subject.class)
//                .listFilter((Subject subject, List<Timeslot> timeslots) ->
//                        Timeslot.totalDurationInMinutes(timeslots, subject) <= subject.getMaxMinutesPerDay())
//                .apply(
//                        ScoreLevel.HARD,
//                        Subject::maxMinutesPerDayPenalty,
//                        Timeslot::totalDurationInMinutes);
        return null;

    }

}
