package io.edukativ.myskoolin.domain.timetabling.constraints;

import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;

import java.util.stream.Stream;

public class TimeTableConstraintProvider implements ConstraintProvider {

    private final TimeSlotConstraintProvider timeSlotConstraintProvider;
    private final SchoolClassConstraintProvider schoolClassConstraintProvider;
    private final SchoolRoomConstraintProvider schoolRoomConstraintProvider;
    private final SubjectConstraintProvider subjectConstraintProvider;
    private final TeacherConstraintProvider teacherConstraintProvider;

    public TimeTableConstraintProvider(TimeSlotConstraintProvider timeSlotConstraintProvider,
                                       SchoolClassConstraintProvider schoolClassConstraintProvider,
                                       SchoolRoomConstraintProvider schoolRoomConstraintProvider,
                                       SubjectConstraintProvider subjectConstraintProvider,
                                       TeacherConstraintProvider teacherConstraintProvider) {
        this.timeSlotConstraintProvider = timeSlotConstraintProvider;
        this.schoolClassConstraintProvider = schoolClassConstraintProvider;
        this.schoolRoomConstraintProvider = schoolRoomConstraintProvider;
        this.subjectConstraintProvider = subjectConstraintProvider;
        this.teacherConstraintProvider = teacherConstraintProvider;
    }

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return Stream.of(hardConstraints(constraintFactory), softConstraints(constraintFactory)).flatMap(Stream::of)
                .toArray(Constraint[]::new);
    }

    private Constraint[] hardConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
            timeSlotConstraintProvider.timeSlotConflict(constraintFactory)
        };
    }

    private Constraint[] softConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {

        };
    }
}
