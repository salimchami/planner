package io.edukativ.myskoolin.application;

import io.edukativ.myskoolin.application.security.UserService;
import io.edukativ.myskoolin.domain.teachers.TeacherAPI;
import io.edukativ.myskoolin.domain.vo.Teacher;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import io.edukativ.myskoolin.infrastructure.app.mapper.UserMapper;
import io.edukativ.myskoolin.infrastructure.teachers.TeacherDTO;
import io.edukativ.myskoolin.infrastructure.teachers.TeacherDbDTO;
import io.edukativ.myskoolin.infrastructure.teachers.TeacherMapper;
import io.edukativ.myskoolin.infrastructure.teachers.TeacherRepository;
import io.github.jhipster.security.RandomUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class TeacherApplication {

    private final UserService userService;
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final UserMapper userMapper;
    private final TeacherAPI teacherAPI;
    private final PasswordEncoder passwordEncoder;

    public TeacherApplication(UserService userService, TeacherRepository teacherRepository, TeacherMapper teacherMapper,
                              UserMapper userMapper, TeacherAPI teacherAPI, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
        this.userMapper = userMapper;
        this.teacherAPI = teacherAPI;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<TeacherDTO> create(TeacherDTO teacherDTO, String baseUrl) {
        Teacher teacher = teacherMapper.dtoToDomain(teacherDTO);
        String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
        Teacher createdTeacher = teacherAPI.create(teacher, encryptedPassword, baseUrl);
        if (createdTeacher != null) {
            return Optional.of(teacherMapper.domainToDto(createdTeacher));
        } else {
            return Optional.empty();
        }
    }

    public List<TeacherDTO> findAllByCurrentUserClient() {
        final UserDbDTO user = userService.currentUserWithAuthorities();
        final List<TeacherDbDTO> teachers = teacherRepository.findAllNotDeletedTeachers(user.getClientId());
        return teacherMapper.dbDtosToDtos(teachers);
    }

    public List<TeacherDTO> searchByName(String name) {
        return null;
    }

    public Optional<TeacherDTO> findOneById(String id) {
        final Optional<TeacherDbDTO> teachers = teacherRepository.findById(id);
        return teachers.map(teacherMapper::dbDtoToDto);
    }

    public List<TeacherDTO> findOneByGrade(String gradeId) {
        return null;
    }

    public List<TeacherDTO> findByGradesIdsAndSubjectsIds(List<String> gradesIds, List<String> subjectsIds) {
        return null;
    }

    public Optional<TeacherDTO> update(TeacherDTO teacherDTO, String baseUrl) {
        return null;
    }
}
