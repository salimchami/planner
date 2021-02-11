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
    public <F, P> Constraint<TimeTable, F, P>[] defineConstraints(TimeTable solution, ConstraintFactory<TimeTable, F, P> constraintFactory) {
        return new Constraint[]{
                // Hard constraints
                subjectDurationByDayConflict(solution, constraintFactory)
        };
    }

    private Constraint<TimeTable, Subject, Timeslot> subjectDurationByDayConflict(TimeTable solution,
                                                                                  ConstraintFactory<TimeTable, Subject, Timeslot> constraintFactory) {
        return constraintFactory
                .name("Subject Duration By Day")
                .withFact(Subject.class)
                .withPlanningVariables(Timeslot.class)
//                .filter((Subject subject, List<Timeslot> timeslots) ->
//                        System.out.println("toto"))
                .filter((Subject subject, List<Timeslot> timeslots) -> {
                    return null;
                })
                .apply(   ScoreLevel.HARD,
                        Subject::maxMinutesPerDayPenalty,
                        Timeslot::totalDurationInMinutes);
    }

}
