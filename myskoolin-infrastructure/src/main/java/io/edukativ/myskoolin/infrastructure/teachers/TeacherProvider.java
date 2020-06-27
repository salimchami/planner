package io.edukativ.myskoolin.infrastructure.teachers;

import io.edukativ.myskoolin.domain.commons.MyskoolinLoggerSPI;
import io.edukativ.myskoolin.domain.teachers.TeacherSPI;
import io.edukativ.myskoolin.domain.vo.Teacher;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TeacherProvider implements TeacherSPI {

    private final TeacherMapper teacherMapper;
    private final TeacherMapperImplemented teacherMapperImplemented;
    private final TeacherRepository teacherRepository;
    private final MyskoolinLoggerSPI logger;

    public TeacherProvider(TeacherMapper teacherMapper, TeacherMapperImplemented teacherMapperImplemented, TeacherRepository teacherRepository,
                           MyskoolinLoggerSPI logger) {
        this.teacherMapper = teacherMapper;
        this.teacherMapperImplemented = teacherMapperImplemented;
        this.teacherRepository = teacherRepository;
        this.logger = logger;
    }

    @Override
    public Teacher create(Teacher teacher) {
        TeacherDbDTO teacherDbDTO = teacherMapperImplemented.domainToDbDto(teacher);
        TeacherDbDTO createdTeacher = teacherRepository.save(teacherDbDTO);
        logger.debug(String.format("Created Information for Teacher: %s", teacherDbDTO.toString()));
        return teacherMapper.dbDtoToDomain(createdTeacher);
    }

    @Override
    public List<Teacher> searchTeachers(String name) {
        final List<TeacherDbDTO> teachers = teacherRepository.searchTeachers(name);
        return teacherMapper.dbDtosToDomains(teachers);
    }

    @Override
    public List<Teacher> searchTeachers(String clientId, String name) {
        final List<TeacherDbDTO> teachers = teacherRepository.searchTeachers(new ObjectId(clientId), name);
        return teacherMapper.dbDtosToDomains(teachers);
    }

    @Override
    public Optional<Teacher> findById(String id) {
        final Optional<TeacherDbDTO> optTeacher = teacherRepository.findById(id);
        return optTeacher.map(teacherMapper::dbDtoToDomain);
    }
}
