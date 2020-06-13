package io.edukativ.myskoolin.application;

import io.edukativ.myskoolin.application.security.UserService;
import io.edukativ.myskoolin.domain.entity.Subject;
import io.edukativ.myskoolin.domain.entity.User;
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
    private final SubjectMapper subjectMapper;
    private final SubjectRepository subjectRepository;

    public SubjectApplication(UserService userService, SubjectAPI subjectAPI, UserMapper userMapper,
                              SubjectMapper subjectMapper, SubjectRepository subjectRepository) {
        this.userService = userService;
        this.subjectAPI = subjectAPI;
        this.subjectMapper = subjectMapper;
        this.subjectRepository = subjectRepository;
    }

    public SubjectDTO createSubject(SubjectDTO subject) {
        User userDbDTO = userService.currentUser();
        Subject savedSubject = subjectAPI.createSubject(
                subjectMapper.dtoToDomain(subject), userDbDTO);
        return subjectMapper.domainToDto(savedSubject);
    }

    public SubjectDTO updateSubject(SubjectDTO subject) {
        User userDbDTO = userService.currentUser();
        Subject savedSubject = subjectAPI.updateSubject(subjectMapper.dtoToDomain(subject), userDbDTO);
        return subjectMapper.domainToDto(savedSubject);
    }

    public List<SubjectDTO> findAll() {
        UserDbDTO userDbDTO = userService.currentUserWithAuthorities();
        final List<SubjectDbDTO> subjects = subjectRepository.findAllNotDeleted(userDbDTO.getClientId());
        return subjectMapper.dbDtosToDtos(subjects);
    }

    public List<SubjectDTO> searchByName(String name) {
        final User user = userService.currentUser();
        List<Subject> subjects = subjectAPI.searchByName(name, user);
        return subjectMapper.domainsToDtos(subjects);
    }

    public List<SubjectDTO> subjectsByGradesId(List<String> gradesId) {
        final User user = userService.currentUser();
        List<Subject> subjects = subjectAPI.subjectsByGradesId(gradesId, user);
        return subjectMapper.domainsToDtos(subjects);
    }

    public Optional<SubjectDTO> findById(String id) {
        final Optional<SubjectDbDTO> optSubject = subjectRepository.findById(id);
        return optSubject.map(subjectMapper::dbDtoToDto);
    }

    public void delete(String id) {
        if (userService.currentUserExists()) {
            subjectAPI.deleteById(id);
        }
    }
}
