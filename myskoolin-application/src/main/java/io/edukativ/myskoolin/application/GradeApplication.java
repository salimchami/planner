package io.edukativ.myskoolin.application;

import io.edukativ.myskoolin.application.security.UserService;
import io.edukativ.myskoolin.domain.entity.Grade;
import io.edukativ.myskoolin.domain.entity.User;
import io.edukativ.myskoolin.domain.grades.GradeAPI;
import io.edukativ.myskoolin.domain.vo.GradeSerie;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import io.edukativ.myskoolin.infrastructure.app.mapper.UserMapper;
import io.edukativ.myskoolin.infrastructure.grades.*;
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
    private final GradeSerieMapper gradeSerieMapper;

    public GradeApplication(GradeMapper gradeMapper, UserMapper userMapper,
                            UserService userService, GradeAPI gradeAPI, GradeRepository gradeRepository, SubjectRepository subjectRepository, SubjectMapper subjectMapper, GradeSerieMapper gradeSerieMapper) {
        this.gradeMapper = gradeMapper;
        this.userMapper = userMapper;
        this.userService = userService;
        this.gradeAPI = gradeAPI;
        this.gradeRepository = gradeRepository;
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
        this.gradeSerieMapper = gradeSerieMapper;
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
        }).orElse(Optional.empty());
    }

    public List<GradeDTO> updateGrades(List<GradeDTO> grades) {
        List<GradeDbDTO> dbGrades = gradeMapper.dtosToDbDtos(grades);
        final List<GradeDbDTO> savedDbGrades = gradeRepository.saveAll(dbGrades);
        return gradeMapper.dbDtosToDtos(savedDbGrades);
    }

    public List<GradeDTO> createGrades(List<GradeDTO> grades) {
        final List<GradeDbDTO> gradesDbDTO = gradeMapper.dtosToDbDtos(grades);
        final List<GradeDbDTO> savedGradesDbDTO = gradeRepository.saveAll(gradesDbDTO);
        return gradeMapper.dbDtosToDtos(savedGradesDbDTO);
    }

    public void deleteGrade(String id) {
        final Optional<GradeDbDTO> grade = gradeRepository.findById(id);
        grade.ifPresent(gradeDbDTO -> {
            gradeDbDTO.setDeleted(true);
            gradeRepository.save(gradeDbDTO);
        });
    }

    public Boolean isGradeAvailableByName(String name) {
        Optional<UserDbDTO> optCurrentUser = userService.getCurrentUserWithAuthorities();
        return optCurrentUser
                .map(user -> gradeRepository.findOneByName(name, false, user.getClientId()).isEmpty())
                .orElse(false);
    }

    public Boolean isGradeDiminutiveAvailable(String diminutive) {
        Optional<UserDbDTO> optCurrentUser = userService.getCurrentUserWithAuthorities();
        return optCurrentUser
                .map(user -> gradeRepository.findOneByDiminutive(diminutive, false, user.getClientId()).isEmpty())
                .orElse(false);
    }

    public List<GradeSerieVO> findAllSeries() {
        Optional<UserDbDTO> optCurrentUser = userService.getCurrentUserWithAuthorities();
        return optCurrentUser
                .map(user -> {
                    final User currentUser = userMapper.dbDtoToDomain(user);
                    final List<GradeSerie> allGradesSeries = gradeAPI.findAllGradesSeries(currentUser);
                    return gradeSerieMapper.modelToDtos(allGradesSeries);
                }).orElse(Collections.emptyList());
    }
}
