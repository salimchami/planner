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
    public <F, P> Constraint<TimeTable, F, P>[] defineConstraints(TimeTable timeTable, ConstraintFactory constraintFactory) {
        return new Constraint[]{
                // Hard constraints
                subjectDurationByDayConflict(timeTable, constraintFactory)
        };
    }

    private <F, P> Constraint<TimeTable, Subject, Timeslot> subjectDurationByDayConflict(TimeTable solution,
                                                                                         ConstraintFactory<TimeTable, Subject, Timeslot> constraintFactory) {
        return constraintFactory
                .name("Subject Duration By Day")
                .withFact(Subject.class)
                .withPlanningVariables(Timeslot.class)
                .filter((Subject subject, List<Timeslot> timeslots) ->
                        Timeslot.totalDurationInMinutes(timeslots, subject) <= subject.getMaxMinutesPerDay())
                .apply(ScoreLevel.HARD,
                        Subject::maxMinutesPerDayPenalty,
                        Timeslot::totalDurationInMinutes);
    }

}
