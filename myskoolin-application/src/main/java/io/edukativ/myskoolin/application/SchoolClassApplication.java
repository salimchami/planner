package io.edukativ.myskoolin.application;

import io.edukativ.myskoolin.application.security.UserService;
import io.edukativ.myskoolin.domain.commons.entity.User;
import io.edukativ.myskoolin.domain.schoolclasses.SchoolClass;
import io.edukativ.myskoolin.domain.schoolclasses.SchoolClassAPI;
import io.edukativ.myskoolin.infrastructure.schoolclasses.SchoolClassDTO;
import io.edukativ.myskoolin.infrastructure.schoolclasses.SchoolClassMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SchoolClassApplication {

    private final SchoolClassAPI schoolClassAPI;
    private final UserService userService;
    private final SchoolClassMapper schoolClassMapper;

    public SchoolClassApplication(SchoolClassAPI schoolClassAPI, UserService userService, SchoolClassMapper schoolClassMapper) {
        this.schoolClassAPI = schoolClassAPI;
        this.userService = userService;
        this.schoolClassMapper = schoolClassMapper;
    }

    public List<SchoolClassDTO> findAll() {
        final User currentUser = userService.currentUser();
        final List<SchoolClass> schoolClasses = schoolClassAPI.findAll(currentUser);
        return schoolClassMapper.domainsToDtos(schoolClasses);
    }
}
