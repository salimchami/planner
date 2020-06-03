package io.edukativ.myskoolin.application;

import io.edukativ.myskoolin.application.security.UserService;
import io.edukativ.myskoolin.domain.entity.Grade;
import io.edukativ.myskoolin.domain.grades.GradeAPI;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import io.edukativ.myskoolin.infrastructure.grades.GradeDTO;
import io.edukativ.myskoolin.infrastructure.grades.GradeMapper;
import io.edukativ.myskoolin.infrastructure.temp.UserMapper;
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

    public GradeApplication(GradeMapper gradeMapper, UserMapper userMapper,
                            UserService userService, GradeAPI gradeAPI) {
        this.gradeMapper = gradeMapper;
        this.userMapper = userMapper;
        this.userService = userService;
        this.gradeAPI = gradeAPI;
    }

    public List<GradeDTO> findGrades() {
        Optional<UserDbDTO> optCurrentUser = userService.getCurrentUserWithAuthorities();
        return optCurrentUser.map(user -> {
            final List<Grade> grades = gradeAPI.findGrades(userMapper.dbDtoToDomain(user));
            return gradeMapper.modelsToDtos(grades);
        }).orElse(Collections.emptyList());
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
