package io.edukativ.myskoolin.infrastructure.teachers;

import io.edukativ.myskoolin.domain.commons.MyskoolinLoggerSPI;
import io.edukativ.myskoolin.domain.teachers.TeacherSPI;
import io.edukativ.myskoolin.domain.vo.Teacher;
import io.edukativ.myskoolin.infrastructure.app.providers.MyskoolinLogger;
import org.springframework.stereotype.Component;

@Component
public class TeacherProvider implements TeacherSPI {

    private final TeacherMapper teacherMapper;
    private final TeacherRepository teacherRepository;
    private final TeacherSearchRepository teacherSearchRepository;
    private final MyskoolinLoggerSPI logger;

    public TeacherProvider(TeacherMapper teacherMapper, TeacherRepository teacherRepository,
                           TeacherSearchRepository teacherSearchRepository, MyskoolinLogger logger) {
        this.teacherMapper = teacherMapper;
        this.teacherRepository = teacherRepository;
        this.teacherSearchRepository = teacherSearchRepository;
        this.logger = logger;
    }

    @Override
    public Teacher create(Teacher teacher) {
        TeacherDbDTO teacherDbDTO = teacherMapper.domainToDbDto(teacher);
        TeacherDbDTO createdTeacher = teacherRepository.save(teacherDbDTO);
        teacherSearchRepository.save(teacherDbDTO);
        logger.debug(TeacherProvider.class, String.format("Created Information for Teacher: %s", teacherDbDTO.toString()));
        return teacherMapper.dbDtoToDomain(createdTeacher);
    }
}
