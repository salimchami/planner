package io.edukativ.myskoolin.domain.timetabling.constraints.soft;

import io.edukativ.myskoolin.domain.timetabling.Lesson;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.Joiners;

public class SchoolClassSoftConstraintProvider {

    public Constraint studentGroupSubjectVariety(ConstraintFactory constraintFactory) {
        // A student group dislikes sequential lessons on the same subject.
        return constraintFactory
                .from(Lesson.class)
                .join(Lesson.class,
                        Joiners.equal(Lesson::getSubject),
                        Joiners.equal(Lesson::getSchoolClass))
//                        Joiners.equal(lesson -> lesson.getTimeSlot().getDayOfWeek()))
                .filter((lesson1, lesson2) -> {
//                    Duration between = Duration.between(lesson1.getTimeSlot().getEndTime(),
//                            lesson2.getTimeSlot().getStartTime());
//                    return !between.isNegative() && between.compareTo(Duration.ofMinutes(30)) <= 0;
                    return true;
                })
                .penalize("Student group subject variety", HardSoftScore.ONE_SOFT);
    }

}
