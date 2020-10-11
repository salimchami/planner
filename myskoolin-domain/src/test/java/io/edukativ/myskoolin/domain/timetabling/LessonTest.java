package io.edukativ.myskoolin.domain.timetabling;

import io.edukativ.myskoolin.domain.providers.GlobalTestProvider;
import io.edukativ.myskoolin.domain.providers.GradeTestProvider;
import io.edukativ.myskoolin.domain.providers.SchoolRoomTestProvider;
import io.edukativ.myskoolin.domain.providers.TimeSlotTestProvider;
import io.edukativ.myskoolin.domain.providers.subjects.SubjectTestProvider;
import io.edukativ.myskoolin.domain.schoolrooms.EnumSchoolRoomsTypes;
import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoom;
import io.edukativ.myskoolin.domain.subjects.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class LessonTest {

    protected static TimeSlot timeSlot1;
    protected static TimeSlot timeSlot2;
    protected static SchoolRoom schoolRoom1;
    protected static SchoolRoom schoolRoom2;
    protected static Subject subject1;
    protected static Subject subject2;

    @BeforeEach
    void setUp() {
        schoolRoom1 = SchoolRoomTestProvider.schoolRoom("1", Collections.emptyList(), "100", 25, EnumSchoolRoomsTypes.NORMAL);
        schoolRoom2 = SchoolRoomTestProvider.schoolRoom("2", Collections.emptyList(), "101", 25, EnumSchoolRoomsTypes.NORMAL);
        subject1 = SubjectTestProvider.subject(
                GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_FRANCAIS_ID, GradeTestProvider.sixiemeGrade, "FRANCAIS", false,
                60, 60 * 2, (int) (60 * 3.5), 3,
                Collections.emptyList(),
                Collections.singletonList(EnumSchoolRoomsTypes.NORMAL), 0, "");
        timeSlot1 = TimeSlotTestProvider.timeSlot(DayOfWeek.MONDAY, 8, 9);
        timeSlot2 = TimeSlotTestProvider.timeSlot(DayOfWeek.MONDAY, 9, 10);

    }

    @Test
    void shouldReturnFalseBecauseNotSameSchoolRoom() {
        Lesson lesson1 = new Lesson(1L, schoolRoom1, subject1, null, timeSlot1);
        Lesson lesson2 = new Lesson(2L, schoolRoom2, subject1, null, timeSlot2);
        final boolean sameSchoolRoom = lesson1.isSameSchoolRoomIfConsecutiveLessons(lesson2);
        assertFalse(sameSchoolRoom);

    }

    @Test
    void shouldReturnTrueBecauseSameSchoolRoom() {
        Lesson lesson1 = new Lesson(1L, schoolRoom1, subject1, null, timeSlot1);
        Lesson lesson2 = new Lesson(2L, schoolRoom1, subject1, null, timeSlot2);
        final boolean sameSchoolRoom = lesson1.isSameSchoolRoomIfConsecutiveLessons(lesson2);
        assertTrue(sameSchoolRoom);

    }
}
