package io.edukativ.myskoolin.domain.timetabling.constraints;

import org.optaplanner.core.api.domain.constraintweight.ConstraintConfiguration;
import org.optaplanner.core.api.domain.constraintweight.ConstraintWeight;
import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;

@ConstraintConfiguration(constraintPackage = "io.edukativ.myskoolin.domain.timetabling.constraints")
public class TimeTableConstraintConfiguration {

    public static final String CONSTRAINT_TIMESLOTS_OVERLAPS = "Time slot overlapping another time slot";
    public static final String CONSTRAINT_SCHOOL_ROOM_TYPE = "School room right type";
    public static final String CONSTRAINT_SUBJECT_DURATION_MAX_BY_DAY_MULTIPLE_LESSONS = "Subject duration max by day - multiple lessons";
    public static final String CONSTRAINT_SUBJECT_DURATION_MAX_BY_DAY_SINGLE_LESSON = "Subject duration max by day - single lesson";
    public static final String CONSTRAINT_SAME_SCHOOLROOM_IF_CONSECUTIVE_LESSONS = "Same schoolroom if consecutive lessons";

    @ConstraintWeight(CONSTRAINT_TIMESLOTS_OVERLAPS)
    private HardMediumSoftScore timeslotsOverlappingScore = HardMediumSoftScore.ofHard(10);

    @ConstraintWeight(CONSTRAINT_SCHOOL_ROOM_TYPE)
    private HardMediumSoftScore schoolRoomRightTypeScore = HardMediumSoftScore.ofHard(10);

    @ConstraintWeight(CONSTRAINT_SUBJECT_DURATION_MAX_BY_DAY_MULTIPLE_LESSONS)
    private HardMediumSoftScore subjectDurationByDayScoreForMultipleLessons = HardMediumSoftScore.ofHard(10);

    @ConstraintWeight(CONSTRAINT_SUBJECT_DURATION_MAX_BY_DAY_SINGLE_LESSON)
    private HardMediumSoftScore subjectDurationByDayScoreForSingleLesson = HardMediumSoftScore.ofHard(10);
//
//    @ConstraintWeight(CONSTRAINT_SAME_SCHOOLROOM_IF_CONSECUTIVE_LESSONS)
//    private final HardMediumSoftScore sameSchoolRoomsConsecutiveLessonsScore = HardMediumSoftScore.ONE_HARD;

    public HardMediumSoftScore getTimeslotsOverlappingScore() {
        return timeslotsOverlappingScore;
    }

    public void setTimeslotsOverlappingScore(HardMediumSoftScore timeslotsOverlappingScore) {
        this.timeslotsOverlappingScore = timeslotsOverlappingScore;
    }

    public HardMediumSoftScore getSchoolRoomRightTypeScore() {
        return schoolRoomRightTypeScore;
    }

    public void setSchoolRoomRightTypeScore(HardMediumSoftScore schoolRoomRightTypeScore) {
        this.schoolRoomRightTypeScore = schoolRoomRightTypeScore;
    }

    public HardMediumSoftScore getSubjectDurationByDayScoreForMultipleLessons() {
        return subjectDurationByDayScoreForMultipleLessons;
    }

    public void setSubjectDurationByDayScoreForMultipleLessons(HardMediumSoftScore subjectDurationByDayScoreForMultipleLessons) {
        this.subjectDurationByDayScoreForMultipleLessons = subjectDurationByDayScoreForMultipleLessons;
    }

    public HardMediumSoftScore getSubjectDurationByDayScoreForSingleLesson() {
        return subjectDurationByDayScoreForSingleLesson;
    }

    public void setSubjectDurationByDayScoreForSingleLesson(HardMediumSoftScore subjectDurationByDayScoreForSingleLesson) {
        this.subjectDurationByDayScoreForSingleLesson = subjectDurationByDayScoreForSingleLesson;
    }
}
