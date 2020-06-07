package io.edukativ.myskoolin.application;

import io.edukativ.myskoolin.application.security.UserService;
import io.edukativ.myskoolin.domain.entity.Grade;
import io.edukativ.myskoolin.domain.grades.GradeAPI;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import io.edukativ.myskoolin.infrastructure.app.mapper.UserMapper;
import io.edukativ.myskoolin.infrastructure.grades.GradeDTO;
import io.edukativ.myskoolin.infrastructure.grades.GradeDbDTO;
import io.edukativ.myskoolin.infrastructure.grades.GradeMapper;
import io.edukativ.myskoolin.infrastructure.grades.GradeRepository;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectDbDTO;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectMapper;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class GradeApplication {

    private final GradeMapper gradeMapper;
    private final UserMapper userMapper;
    private final UserService userService;
    private final GradeAPI gradeAPI;
    private final GradeRepository gradeRepository;
    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    public GradeApplication(GradeMapper gradeMapper, UserMapper userMapper,
                            UserService userService, GradeAPI gradeAPI, GradeRepository gradeRepository, SubjectRepository subjectRepository, SubjectMapper subjectMapper) {
        this.gradeMapper = gradeMapper;
        this.userMapper = userMapper;
        this.userService = userService;
        this.gradeAPI = gradeAPI;
        this.gradeRepository = gradeRepository;
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }

    public List<GradeDTO> findGrades() {
        Optional<UserDbDTO> optCurrentUser = userService.getCurrentUserWithAuthorities();
        return optCurrentUser.map(user -> {
            final List<Grade> grades = gradeAPI.findGrades(userMapper.dbDtoToDomain(user));
            return gradeMapper.modelsToDtos(grades);
        }).orElse(Collections.emptyList());
    }

    public Optional<GradeDTO> findGradeByName(String name) {
        Optional<UserDbDTO> optCurrentUser = userService.getCurrentUserWithAuthorities();
        return optCurrentUser.map(user -> {
            Optional<GradeDbDTO> optDbGrade = gradeRepository.findOneByName(name, false, user.getClientId());
            return optDbGrade.map(dbGrade -> {
                final List<SubjectDbDTO> subjects = subjectRepository.findByGrade(dbGrade.getId(), false);
                final GradeDTO grade = gradeMapper.dbDtoToDto(dbGrade);
                grade.setSubjects(subjectMapper.dbDtosToDtos(subjects));
                return grade;
            });
        }).orElse(null);
    }

//    public Optional<GradeDTO> findOneByName(String name) {
//        final Optional<UserDbDTO> optUserWithAuthorities = userService.getCurrentUserWithAuthorities();
//        return optUserWithAuthorities.flatMap(user -> {
//            final Optional<GradeDbDTO> optGradeDTO = gradeRepository.findOneByName(name, false, user.getClientId());
//            return optGradeDTO.map(gradeMapper::dbDTOToDTO);
//        });
//    }
//
//    public GradeDTO createOrUpdateGrade(GradeDTO gradeDTO) {
//        final Optional<UserDbDTO> optUserWithAuthorities = userService.getCurrentUserWithAuthorities();
//        return optUserWithAuthorities.flatMap(userDbDTO -> {
//            final Grade grade = gradeMapper.dtoToDomain(gradeDTO);
//            Optional<Grade> optSavedGrade = gradeAPI.createOrUpdateGrade(grade, userMapper.dbDtoToDomain(userDbDTO));
//            return optSavedGrade.map(gradeMapper::domainToDto);
//        }).orElseThrow();
//    }
//
//    public void deleteGrade(String id) {
//        gradeAPI.deleteGrade(id);
//    }
}
