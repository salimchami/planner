package io.edukativ.myskoolin.domain.timetabling.constraints;

import org.optaplanner.core.api.domain.constraintweight.ConstraintConfiguration;
import org.optaplanner.core.api.domain.constraintweight.ConstraintWeight;
import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;

@ConstraintConfiguration
public class TimeTableConstraintConfiguration {

    public static final String CONSTRAINT_TIMESLOTS_OVERLAPS = "Time slot overlapping another time slot";
    public static final String CONSTRAINT_SCHOOL_TYPE_REWARD= "School type reward";
    public static final String CONSTRAINT_SUBJECT_DURATION_BY_DAY = "Subject duration by day";
    public static final String CONSTRAINT_SAME_SCHOOLROOM_IF_CONSECUTIVE_LESSONS = "Same schoolroom if consecutive lessons";

    @ConstraintWeight(CONSTRAINT_TIMESLOTS_OVERLAPS)
    private final HardMediumSoftScore timeslotsOverlappingScore = HardMediumSoftScore.ONE_HARD;

    @ConstraintWeight(CONSTRAINT_SUBJECT_DURATION_BY_DAY)
    private final HardMediumSoftScore subjectDurationByDayScore = HardMediumSoftScore.ONE_HARD;

    @ConstraintWeight(CONSTRAINT_SAME_SCHOOLROOM_IF_CONSECUTIVE_LESSONS)
    private final HardMediumSoftScore sameSchoolRoomsConsecutiveLessonsScore = HardMediumSoftScore.ONE_HARD;
}
