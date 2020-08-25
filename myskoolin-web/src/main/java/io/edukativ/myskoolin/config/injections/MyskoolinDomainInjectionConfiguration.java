package io.edukativ.myskoolin.config.injections;

import io.edukativ.myskoolin.domain.commons.AuthoritySPI;
import io.edukativ.myskoolin.domain.commons.MyskoolinLoggerSPI;
import io.edukativ.myskoolin.domain.commons.mailing.MyskoolinMailingSPI;
import io.edukativ.myskoolin.domain.grades.GradeAPI;
import io.edukativ.myskoolin.domain.grades.GradeSPI;
import io.edukativ.myskoolin.domain.grades.GradeService;
import io.edukativ.myskoolin.domain.schoolclasses.SchoolClassAPI;
import io.edukativ.myskoolin.domain.schoolclasses.SchoolClassSPI;
import io.edukativ.myskoolin.domain.schoolclasses.SchoolClassService;
import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoomAPI;
import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoomSPI;
import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoomService;
import io.edukativ.myskoolin.domain.subjects.SubjectAPI;
import io.edukativ.myskoolin.domain.subjects.SubjectSPI;
import io.edukativ.myskoolin.domain.subjects.SubjectService;
import io.edukativ.myskoolin.domain.teachers.TeacherAPI;
import io.edukativ.myskoolin.domain.teachers.TeacherMailingSPI;
import io.edukativ.myskoolin.domain.teachers.TeacherSPI;
import io.edukativ.myskoolin.domain.teachers.TeacherService;
import io.edukativ.myskoolin.domain.timetabling.SchoolClassTimeTable;
import io.edukativ.myskoolin.domain.timetabling.TimeTableSPI;
import io.edukativ.myskoolin.domain.timetabling.TimeTablesSolver;
import io.edukativ.myskoolin.domain.timetabling.TimetablesSolverAPI;
import io.edukativ.myskoolin.infrastructure.app.providers.MyskoolinLogger;
import org.optaplanner.core.api.score.ScoreManager;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyskoolinDomainInjectionConfiguration {

    @Bean
    public SubjectAPI subjectAPI(SubjectSPI subjectSPI, GradeSPI gradeSPI) {
        return new SubjectService(subjectSPI, gradeSPI);
    }

    @Bean
    public SchoolRoomAPI schoolRoomAPI(SchoolRoomSPI schoolRoomSPI) {
        return new SchoolRoomService(schoolRoomSPI);
    }

    @Bean
    public GradeAPI gradeAPI(GradeSPI gradeSPI, SubjectSPI subjectSPI) {
        return new GradeService(gradeSPI, subjectSPI);
    }

    @Bean
    public TeacherAPI teacherAPI(TeacherSPI teacherSPI, AuthoritySPI authoritySPI,
                                 TeacherMailingSPI teacherMailingSPI, MyskoolinMailingSPI myskoolinMailingSPI,
                                 MyskoolinLoggerSPI logger) {
        return new TeacherService(teacherSPI, authoritySPI, teacherMailingSPI, myskoolinMailingSPI, logger);
    }

    @Bean
    public SchoolClassAPI schoolClassAPI(SchoolClassSPI schoolClassSPI) {
        return new SchoolClassService(schoolClassSPI);
    }

    @Bean
    public TimetablesSolverAPI timeTableGenerationAPI(ScoreManager<SchoolClassTimeTable> scoreManager,
                                                      SchoolClassSPI schoolClassSPI,
                                                      TimeTableSPI timeTableSPI, MyskoolinLogger logger) {
        ClassLoader classloader = SchoolClassTimeTable.class.getClassLoader();
        SolverFactory<SchoolClassTimeTable> solverFactory = SolverFactory.createFromXmlResource("timetabling/solver/schoolClassTimetablesSolverConfig.xml", classloader);
        Solver<SchoolClassTimeTable> solver = solverFactory.buildSolver();
        return new TimeTablesSolver(solver, scoreManager, schoolClassSPI, timeTableSPI, logger);
    }
}
