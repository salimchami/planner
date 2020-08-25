package io.edukativ.myskoolin.domain.timetabling.constraints;

import io.edukativ.myskoolin.domain.commons.vo.EnumDays;
import io.edukativ.myskoolin.domain.commons.vo.EnumPartsOfDay;
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
import io.edukativ.myskoolin.domain.timetabling.Time;
import io.edukativ.myskoolin.domain.timetabling.TimeSlot;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.test.impl.score.buildin.hardmediumsoft.HardMediumSoftScoreVerifier;

import java.util.List;

public abstract class ScoreConstraintProviderTest {

    public static final String SOLVER_CONFIG = "timetabling/solver/schoolClassTimetablesSolverConfig.xml";

    protected HardMediumSoftScoreVerifier<SchoolClassTimeTable> scoreVerifier = new HardMediumSoftScoreVerifier<>(
            SolverFactory.createFromXmlResource(SOLVER_CONFIG));

    protected static TimeTableConstraintConfiguration config;

    protected static SchoolClass schoolClass1;
    protected static SchoolClass schoolClass2;
    protected static TimeSlot timeSlot1;
    protected static TimeSlot timeSlot2;
    protected static SchoolRoom schoolRoom1;
    protected static SchoolRoom schoolRoom2;
    protected static Subject subject1;
    protected static Subject subject2;
    protected static Teacher teacher1;
    protected static Teacher teacher2;

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
        subject1 = subjects.stream().filter(s -> s.getId().equals(GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_FRANCAIS_ID)).findFirst().get();
        subject2 = subjects.stream().filter(s -> s.getId().equals(GlobalTestProvider.Subjects.Sixieme.SUBJECT_SIXIEME_MATHS_ID)).findFirst().get();
        teacher1 = teachers.stream().filter(t -> t.getId().equals(GlobalTestProvider.TEACHER_FRANCAIS_1_ID)).findFirst().get();
        teacher2 = teachers.stream().filter(t -> t.getId().equals(GlobalTestProvider.TEACHER_MATHS_1_ID)).findFirst().get();
        timeSlot1 = new TimeSlot(1L, EnumDays.MONDAY,
                new Time(8, 0, 0, EnumPartsOfDay.AM),
                new Time(9, 0, 0, EnumPartsOfDay.AM)
        );
        timeSlot2 = new TimeSlot(2L, EnumDays.MONDAY,
                new Time(9, 0, 0, EnumPartsOfDay.AM),
                new Time(10, 0, 0, EnumPartsOfDay.AM)
        );
    }

}
