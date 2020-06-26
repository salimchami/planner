package io.edukativ.myskoolin.application;

import io.edukativ.myskoolin.application.security.UserService;
import io.edukativ.myskoolin.domain.entity.User;
import io.edukativ.myskoolin.domain.teachers.TeacherAPI;
import io.edukativ.myskoolin.domain.vo.Teacher;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import io.edukativ.myskoolin.infrastructure.teachers.TeacherDTO;
import io.edukativ.myskoolin.infrastructure.teachers.TeacherDbDTO;
import io.edukativ.myskoolin.infrastructure.teachers.TeacherMapper;
import io.edukativ.myskoolin.infrastructure.teachers.TeacherRepository;
import io.github.jhipster.security.RandomUtil;
import org.bson.types.ObjectId;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class TeacherApplication {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final TeacherAPI teacherAPI;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public TeacherApplication(TeacherRepository teacherRepository, TeacherMapper teacherMapper,
                              TeacherAPI teacherAPI, PasswordEncoder passwordEncoder, UserService userService) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
        this.teacherAPI = teacherAPI;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    public Optional<TeacherDTO> create(TeacherDTO teacherDTO, String baseUrl) {
        Teacher teacher = teacherMapper.dtoToDomain(teacherDTO);
        String encryptedRandomPassword = passwordEncoder.encode(RandomUtil.generatePassword());
        User currentUser = userService.currentUser();
        Teacher createdTeacher = teacherAPI.create(teacher, encryptedRandomPassword, currentUser, baseUrl);
        if (createdTeacher != null) {
            return Optional.of(teacherMapper.domainToDto(createdTeacher));
        } else {
            return Optional.empty();
        }
    }

    public List<TeacherDTO> findAllByCurrentUserClient() {
        UserDbDTO currentUserWithAuthorities = userService.currentUserWithAuthorities();
        final List<TeacherDbDTO> teachers = teacherRepository.findAllNotDeletedTeachers(currentUserWithAuthorities.getClientId());
        return teacherMapper.dbDtosToDtos(teachers);
    }

    public List<TeacherDTO> searchByName(String name) {
        User currentUser = userService.currentUser();
        List<Teacher> teachers = teacherAPI.searchByName(name, currentUser);
        return teacherMapper.domainsToDtos(teachers);
    }

    public Optional<TeacherDTO> findOneById(String id) {
        final Optional<TeacherDbDTO> teachers = teacherRepository.findById(id);
        return teachers.map(teacherMapper::dbDtoToDto);
    }

    public List<TeacherDTO> findOneByGrade(String gradeId) {
        User currentUser = userService.currentUser();
        final List<TeacherDbDTO> teachers =
                teacherRepository.findNotDeletedTeachersByGradeId(new ObjectId(currentUser.getClientId()), new ObjectId(gradeId));
        return teacherMapper.dbDtosToDtos(teachers);
    }

    public List<TeacherDTO> findByGradesIdsAndSubjectsIds(List<String> gradesIds, List<String> subjectsIds) {
        UserDbDTO currentUser = userService.currentUserWithAuthorities();
        final List<TeacherDbDTO> teachers = teacherRepository.findNotDeletedTeachersByGradesIdsAndSubjectsId(currentUser.getClientId(), gradesIds, subjectsIds);
        return teacherMapper.dbDtosToDtos(teachers);
    }

    public Optional<TeacherDTO> update(TeacherDTO teacherDTO, String baseUrl) {
        return null;
    }
}
