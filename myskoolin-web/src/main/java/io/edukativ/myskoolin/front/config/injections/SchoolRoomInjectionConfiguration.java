package io.edukativ.myskoolin.front.config.injections;

import io.edukativ.myskoolin.domain.grades.GradeAPI;
import io.edukativ.myskoolin.domain.grades.GradeSPI;
import io.edukativ.myskoolin.domain.grades.GradeService;
import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoomAPI;
import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoomSPI;
import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoomService;
import io.edukativ.myskoolin.domain.subjects.SubjectSPI;
import io.edukativ.myskoolin.infrastructure.grades.GradeMapper;
import io.edukativ.myskoolin.infrastructure.grades.GradeProvider;
import io.edukativ.myskoolin.infrastructure.grades.GradeRepository;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectRepository;
import io.edukativ.myskoolin.infrastructure.schoolrooms.SchoolRoomMapper;
import io.edukativ.myskoolin.infrastructure.schoolrooms.SchoolRoomProvider;
import io.edukativ.myskoolin.infrastructure.schoolrooms.SchoolRoomRepository;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchoolRoomInjectionConfiguration {

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
    public SubjectSPI subjectSPI(SubjectRepository subjectRepository) {
        return new SubjectProvider(subjectRepository);
    }
}
