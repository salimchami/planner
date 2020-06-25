package io.edukativ.myskoolin.application;

import io.edukativ.myskoolin.application.security.UserService;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import io.edukativ.myskoolin.infrastructure.teachers.TeacherDTO;
import io.edukativ.myskoolin.infrastructure.teachers.TeacherDbDTO;
import io.edukativ.myskoolin.infrastructure.teachers.TeacherMapper;
import io.edukativ.myskoolin.infrastructure.teachers.TeacherRepository;
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

    public TeacherApplication(UserService userService, TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
        this.userService = userService;
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }

    public Optional<TeacherDTO> create(TeacherDTO teacher, String baseUrl) {
        //        if (userRepository.findOneByLoginIgnoreCase(teacher.getLogin()).isPresent()) {
//            return ResponseEntity.badRequest()
//                .headers(HeaderUtil.createFailureAlert("userManagement", "userexists", "Login already in use"))
//                .body(null);
//        } else if (userRepository.findOneByEmail(teacher.getEmail()).isPresent()) {
//            return ResponseEntity.badRequest()
//                .headers(HeaderUtil.createFailureAlert("userManagement", "emailexists", "Email already in use"))
//                .body(null);
//        }

        return null;
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
        return teacherRepository.findById(id).map(teacherMapper::dbDtoToDto);
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
