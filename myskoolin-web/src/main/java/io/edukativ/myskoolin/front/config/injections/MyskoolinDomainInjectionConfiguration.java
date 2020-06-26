package io.edukativ.myskoolin.front.config.injections;

import io.edukativ.myskoolin.domain.commons.AuthoritySPI;
import io.edukativ.myskoolin.domain.commons.MyskoolinLoggerSPI;
import io.edukativ.myskoolin.domain.commons.mailing.MyskoolinMailingSPI;
import io.edukativ.myskoolin.domain.grades.GradeAPI;
import io.edukativ.myskoolin.domain.grades.GradeSPI;
import io.edukativ.myskoolin.domain.grades.GradeService;
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
import io.edukativ.myskoolin.infrastructure.app.mapper.AuthorityMapper;
import io.edukativ.myskoolin.infrastructure.app.providers.AuthorityProvider;
import io.edukativ.myskoolin.infrastructure.app.providers.MyskoolinLogger;
import io.edukativ.myskoolin.infrastructure.app.repository.AuthorityRepository;
import io.edukativ.myskoolin.infrastructure.commercial.MyskoolinMailingProvider;
import io.edukativ.myskoolin.infrastructure.grades.GradeMapper;
import io.edukativ.myskoolin.infrastructure.grades.GradeProvider;
import io.edukativ.myskoolin.infrastructure.grades.GradeRepository;
import io.edukativ.myskoolin.infrastructure.schoolrooms.SchoolRoomMapper;
import io.edukativ.myskoolin.infrastructure.schoolrooms.SchoolRoomProvider;
import io.edukativ.myskoolin.infrastructure.schoolrooms.SchoolRoomRepository;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectMapper;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectProvider;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectRepository;
import io.edukativ.myskoolin.infrastructure.teachers.TeacherMapper;
import io.edukativ.myskoolin.infrastructure.teachers.TeacherMapperImplemented;
import io.edukativ.myskoolin.infrastructure.teachers.TeacherProvider;
import io.edukativ.myskoolin.infrastructure.teachers.TeacherRepository;
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
    public SchoolRoomSPI schoolRoomSPI(SchoolRoomRepository schoolRoomRepository, SchoolRoomMapper schoolRoomMapper) {
        return new SchoolRoomProvider(schoolRoomRepository, schoolRoomMapper);
    }

    @Bean
    public GradeAPI gradeApi(GradeSPI gradeSPI, SubjectSPI subjectSPI) {
        return new GradeService(gradeSPI, subjectSPI);
    }

    @Bean
    public GradeSPI gradeSPI(GradeRepository gradeRepository, GradeMapper gradeMapper) {
        return new GradeProvider(gradeRepository, gradeMapper);
    }

    @Bean
    public SubjectSPI subjectSPI(SubjectRepository subjectRepository, SubjectMapper subjectMapper) {
        return new SubjectProvider(subjectRepository, subjectMapper);
    }

    @Bean
    public TeacherAPI teacherAPI(TeacherSPI teacherSPI, AuthoritySPI authoritySPI,
                                 TeacherMailingSPI teacherMailingSPI, MyskoolinMailingSPI myskoolinMailingSPI,
                                 MyskoolinLoggerSPI logger) {
        return new TeacherService(teacherSPI, authoritySPI, teacherMailingSPI, myskoolinMailingSPI, logger);
    }

    @Bean
    public MyskoolinLoggerSPI myskoolinLoggerSPI() {
        return new MyskoolinLogger();
    }

    @Bean
    public MyskoolinMailingSPI myskoolinMailingSPI() {
        return new MyskoolinMailingProvider();
    }

    @Bean
    public AuthoritySPI authoritySPI(AuthorityRepository authorityRepository, AuthorityMapper authorityMapper) {
        return new AuthorityProvider(authorityRepository, authorityMapper);
    }

    @Bean
    public TeacherSPI teacherSPI(TeacherMapper teacherMapper, TeacherRepository teacherRepository,
                                 MyskoolinLoggerSPI myskoolinLogger, TeacherMapperImplemented teacherMapperImplemented) {
        return new TeacherProvider(teacherMapper, teacherMapperImplemented, teacherRepository, myskoolinLogger);
    }

}
