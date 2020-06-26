package io.edukativ.myskoolin.infrastructure.teachers;

import io.edukativ.myskoolin.domain.commons.MyskoolinLoggerSPI;
import io.edukativ.myskoolin.domain.teachers.TeacherSPI;
import io.edukativ.myskoolin.domain.vo.Teacher;
import org.springframework.stereotype.Component;

@Component
public class TeacherProvider implements TeacherSPI {

    private final TeacherMapper teacherMapper;
    private final TeacherRepository teacherRepository;
    private final MyskoolinLoggerSPI logger;

    public TeacherProvider(TeacherMapper teacherMapper, TeacherRepository teacherRepository,
                           MyskoolinLoggerSPI logger) {
        this.teacherMapper = teacherMapper;
        this.teacherRepository = teacherRepository;
        this.logger = logger;
    }

    @Override
    public Teacher create(Teacher teacher) {
        TeacherDbDTO teacherDbDTO = teacherMapper.domainToDbDto(teacher);
        TeacherDbDTO createdTeacher = teacherRepository.save(teacherDbDTO);
        logger.debug(TeacherProvider.class, String.format("Created Information for Teacher: %s", teacherDbDTO.toString()));
        return teacherMapper.dbDtoToDomain(createdTeacher);
    }
}
