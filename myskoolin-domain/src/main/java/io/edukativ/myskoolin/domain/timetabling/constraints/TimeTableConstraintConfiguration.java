package io.edukativ.myskoolin.domain.timetabling.constraints;

import org.optaplanner.core.api.domain.constraintweight.ConstraintConfiguration;
import org.optaplanner.core.api.domain.constraintweight.ConstraintWeight;
import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;

@ConstraintConfiguration
public class TimeTableConstraintConfiguration {

    public static final String CONSTRAINT_TIMESLOTS_OVERLAPS = "Time slot overlapping another time slot";

    @ConstraintWeight(CONSTRAINT_TIMESLOTS_OVERLAPS)
    private final HardMediumSoftScore timeslotsOverlappingScore = HardMediumSoftScore.ONE_HARD;
}
