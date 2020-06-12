package io.edukativ.myskoolin.application;

import io.edukativ.myskoolin.application.security.UserService;
import io.edukativ.myskoolin.domain.entity.Subject;
import io.edukativ.myskoolin.domain.subjects.SubjectAPI;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import io.edukativ.myskoolin.infrastructure.app.mapper.UserMapper;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectDTO;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectDbDTO;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectMapper;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class SubjectApplication {

    private final UserService userService;
    private final SubjectAPI subjectAPI;
    private final UserMapper userMapper;
    private final SubjectMapper subjectMapper;
    private final SubjectRepository subjectRepository;

    public SubjectApplication(UserService userService, SubjectAPI subjectAPI, UserMapper userMapper,
                              SubjectMapper subjectMapper, SubjectRepository subjectRepository) {
        this.userService = userService;
        this.subjectAPI = subjectAPI;
        this.userMapper = userMapper;
        this.subjectMapper = subjectMapper;
        this.subjectRepository = subjectRepository;
    }

    public SubjectDTO createSubject(SubjectDTO subject) {
        UserDbDTO userDbDTO = userService.getCurrentUserWithAuthorities();
        Subject savedSubject = subjectAPI.createSubject(
                subjectMapper.dtoToDomain(subject),
                userMapper.dbDtoToDomain(userDbDTO)
        );
        return subjectMapper.domainToDto(savedSubject);
    }

    public SubjectDTO updateSubject(SubjectDTO subject) {
        UserDbDTO userDbDTO = userService.getCurrentUserWithAuthorities();
        Subject savedSubject = subjectAPI.updateSubject(
                subjectMapper.dtoToDomain(subject),
                userMapper.dbDtoToDomain(userDbDTO)
        );
        return subjectMapper.domainToDto(savedSubject);
    }

    public List<SubjectDTO> findAll() {
        UserDbDTO userDbDTO = userService.getCurrentUserWithAuthorities();
        final List<SubjectDbDTO> subjects = subjectRepository.findAllNotDeleted(userDbDTO.getClientId());
        return subjectMapper.dbDtosToDtos(subjects);
    }

    public List<SubjectDTO> searchByName(String name) {
        return null;
    }

    public List<SubjectDTO> subjectsByGradesIds(List<String> gradesIds) {
        return null;
    }

    public Optional<SubjectDTO> findById(String id) {
        return null;
    }

    public void delete(String id) {

    }
}
