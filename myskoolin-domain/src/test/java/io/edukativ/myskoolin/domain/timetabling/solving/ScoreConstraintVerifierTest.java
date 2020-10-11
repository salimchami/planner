package io.edukativ.myskoolin.domain.timetabling.solving;

import io.edukativ.myskoolin.domain.grades.Grade;
import io.edukativ.myskoolin.domain.providers.GlobalTestProvider;
import io.edukativ.myskoolin.domain.providers.GradeTestProvider;
import io.edukativ.myskoolin.domain.providers.SchoolRoomTestProvider;
import io.edukativ.myskoolin.domain.providers.UserTestProvider;
import io.edukativ.myskoolin.domain.providers.subjects.SubjectTestProvider;
import io.edukativ.myskoolin.domain.schoolclasses.SchoolClass;
import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoom;
import io.edukativ.myskoolin.domain.subjects.Subject;
import io.edukativ.myskoolin.domain.teachers.Teacher;
import io.edukativ.myskoolin.domain.timetabling.SchoolClassTimeTable;
import io.edukativ.myskoolin.domain.timetabling.TimeSlot;
import io.edukativ.myskoolin.domain.timetabling.constraints.TimeTableConstraintConfiguration;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.test.impl.score.buildin.hardmediumsoft.HardMediumSoftScoreVerifier;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public abstract class ScoreConstraintVerifierTest {

    public static final String SOLVER_CONFIG = "solverConfig.xml";

    protected HardMediumSoftScoreVerifier<SchoolClassTimeTable> scoreVerifier = new HardMediumSoftScoreVerifier<>(
            SolverFactory.createFromXmlResource(SOLVER_CONFIG));

    protected static TimeTableConstraintConfiguration config;

    protected static SchoolClass schoolClass1;
    protected static SchoolClass schoolClass2;
    protected static TimeSlot timeSlot1;
    protected static TimeSlot timeSlot2;
    protected static SchoolRoom schoolRoom1;
    protected static SchoolRoom schoolRoom2;
    protected static SchoolRoom schoolRoom3;
    protected static Subject sixiemeFrancaisSubject;
    protected static Subject sixiemeMathsSubject;
    protected static Teacher francaisTeacher;
    protected static Teacher mathsTeacher;

    protected static void prepareParams() {
        config = new TimeTableConstraintConfiguration();
        List<Grade> grades = GradeTestProvider.allGrades();
        List<SchoolRoom> allSchoolRooms = SchoolRoomTestProvider.allSchoolRooms();
        List<Subject> subjects = SubjectTestProvider.allSubjects();
        List<Teacher> teachers = UserTestProvider.teacherUsers(GlobalTestProvider.CLIENT_ID, subjects, grades);
        schoolClass1 = new SchoolClass();
        schoolClass1.setClientId(GlobalTestProvider.CLIENT_ID);
        schoolClass1.setId("1");
        schoolClass2 = new SchoolClass();
        schoolClass2.setClientId(GlobalTestProvider.CLIENT_ID);
        schoolClass2.setId("2");
        schoolRoom1 = allSchoolRooms.stream().filter(sr -> sr.getName().equals("100")).findFirst().get();
        schoolRoom2 = allSchoolRooms.stream().filter(sr -> sr.getName().equals("101")).findFirst().get();
        sixiemeFrancaisSubject = subjects.stream().filter(s -> s.getId().equals(GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_FRANCAIS_ID)).findFirst().get();
        sixiemeMathsSubject = subjects.stream().filter(s -> s.getId().equals(GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_MATHS_ID)).findFirst().get();
        francaisTeacher = teachers.stream().filter(t -> t.getId().equals(GlobalTestProvider.TEACHER_FRANCAIS_1_ID)).findFirst().get();
        mathsTeacher = teachers.stream().filter(t -> t.getId().equals(GlobalTestProvider.TEACHER_MATHS_1_ID)).findFirst().get();
        timeSlot1 = new TimeSlot(1L, DayOfWeek.MONDAY,
                LocalTime.of(8, 0, 0),
                LocalTime.of(9, 0, 0)
        );
        timeSlot2 = new TimeSlot(2L, DayOfWeek.MONDAY,
                LocalTime.of(9, 0, 0),
                LocalTime.of(10, 0, 0)
        );
    }

}
