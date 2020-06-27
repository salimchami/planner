package io.edukativ.myskoolin.domain.timetabling.constraints;

import io.edukativ.myskoolin.domain.timetabling.constraints.hard.SchoolClassHardConstraintProvider;
import io.edukativ.myskoolin.domain.timetabling.constraints.hard.SchoolRoomHardConstraintProvider;
import io.edukativ.myskoolin.domain.timetabling.constraints.hard.TeacherHardConstraintProvider;
import io.edukativ.myskoolin.domain.timetabling.constraints.soft.SchoolClassSoftConstraintProvider;
import io.edukativ.myskoolin.domain.timetabling.constraints.soft.SchoolRoomSoftConstraintProvider;
import io.edukativ.myskoolin.domain.timetabling.constraints.soft.TeacherSoftConstraintProvider;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;

public class TimeTableConstraintProvider implements ConstraintProvider {

    private final SchoolClassHardConstraintProvider schoolClassHardConstraintProvider;
    private final SchoolClassSoftConstraintProvider schoolClassSoftConstraintProvider;
    private final SchoolRoomHardConstraintProvider schoolRoomHardConstraintProvider;
    private final SchoolRoomSoftConstraintProvider schoolRoomSoftConstraintProvider;
    private final TeacherHardConstraintProvider teacherHardConstraintProvider;
    private final TeacherSoftConstraintProvider teacherSoftConstraintProvider;

    public TimeTableConstraintProvider(SchoolClassHardConstraintProvider schoolClassHardConstraintProvider,
                                       SchoolClassSoftConstraintProvider schoolClassSoftConstraintProvider,
                                       SchoolRoomHardConstraintProvider schoolRoomHardConstraintProvider,
                                       SchoolRoomSoftConstraintProvider schoolRoomSoftConstraintProvider,
                                       TeacherHardConstraintProvider teacherHardConstraintProvider,
                                       TeacherSoftConstraintProvider teacherSoftConstraintProvider) {
        this.schoolClassHardConstraintProvider = schoolClassHardConstraintProvider;
        this.schoolClassSoftConstraintProvider = schoolClassSoftConstraintProvider;
        this.schoolRoomHardConstraintProvider = schoolRoomHardConstraintProvider;
        this.schoolRoomSoftConstraintProvider = schoolRoomSoftConstraintProvider;
        this.teacherHardConstraintProvider = teacherHardConstraintProvider;
        this.teacherSoftConstraintProvider = teacherSoftConstraintProvider;
    }

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
                // Hard constraints
                schoolRoomHardConstraintProvider.roomConflict(constraintFactory),
                teacherHardConstraintProvider.teacherConflict(constraintFactory),
                schoolClassHardConstraintProvider.studentGroupConflict(constraintFactory),
                // Soft constraints
                schoolRoomSoftConstraintProvider.teacherRoomStability(constraintFactory),
                teacherSoftConstraintProvider.teacherTimeEfficiency(constraintFactory),
                schoolClassSoftConstraintProvider.studentGroupSubjectVariety(constraintFactory)
        };
    }
}
