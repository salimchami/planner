package io.edukativ.myskoolin.front.web.rest;

import io.edukativ.myskoolin.application.TeacherApplication;
import io.edukativ.myskoolin.domain.commons.AuthoritiesConstants;
import io.edukativ.myskoolin.domain.commons.MyskoolinLoggerSPI;
import io.edukativ.myskoolin.front.web.util.WebUtil;
import io.edukativ.myskoolin.infrastructure.app.repository.UserRepository;
import io.edukativ.myskoolin.infrastructure.config.Constants;
import io.edukativ.myskoolin.infrastructure.teachers.TaughtSubjectsSearch;
import io.edukativ.myskoolin.infrastructure.teachers.TeacherDTO;
import io.github.jhipster.web.util.HeaderUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teachers")
public class TeacherResource {

    private static final String TEACHER_ENTITY = "teacher";

    private final TeacherApplication teacherApplication;
    private final UserRepository userRepository;
    private final MyskoolinLoggerSPI logger;

    public TeacherResource(TeacherApplication teacherApplication, UserRepository userRepository, MyskoolinLoggerSPI logger) {
        this.teacherApplication = teacherApplication;
        this.userRepository = userRepository;
        this.logger = logger;
    }

    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE,
    })
    @ResponseBody
    @PostMapping
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody TeacherDTO teacher, HttpServletRequest request) throws URISyntaxException {
        if (userRepository.findOneByLoginIgnoreCase(teacher.getLogin()).isPresent()) {
            return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert(Constants.APPLICATION_NAME, false,
                    TEACHER_ENTITY, "loginAlreadyInUse", "Login already in use"))
                .body(null);
        } else if (userRepository.findOneByEmail(teacher.getEmail()).isPresent()) {
            return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert(Constants.APPLICATION_NAME, false,
                    TEACHER_ENTITY, "emailAlreadyInUse", "Email already in use"))
                .body(null);
        }

        logger.debug(String.format("REST request to save Teacher : %s", teacher));
        Optional<TeacherDTO> optCreatedTeacher = teacherApplication.create(teacher, WebUtil.baseUrl(request));
        if (optCreatedTeacher.isPresent()) {
            final TeacherDTO createdTeacher = optCreatedTeacher.get();
            return ResponseEntity.created(new URI("/api/teachers/" + createdTeacher.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(Constants.APPLICATION_NAME, false,
                    TEACHER_ENTITY, createdTeacher.getId()))
                .body(optCreatedTeacher.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE,
    })
    @PutMapping
    public ResponseEntity<TeacherDTO> updateTeacher(@RequestBody TeacherDTO teacherDTO, HttpServletRequest request) {
        logger.debug(String.format("REST request to update Teacher : %s", teacherDTO));
        Optional<TeacherDTO> optTeacher = teacherApplication.update(teacherDTO, WebUtil.baseUrl(request));
        return optTeacher
            .map(teacher -> ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(Constants.APPLICATION_NAME, false, TEACHER_ENTITY, teacherDTO.getLogin()))
                .body(teacher))
            .orElse(ResponseEntity.unprocessableEntity()
                .headers(HeaderUtil.createEntityUpdateAlert(Constants.APPLICATION_NAME, false, TEACHER_ENTITY, teacherDTO.getLogin()))
                .build()
            );
    }

    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE,
        AuthoritiesConstants.INFIRMARY,
        AuthoritiesConstants.TEACHERS,
    })
    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        logger.debug("REST request to get a page of teachers");
        List<TeacherDTO> teachers = teacherApplication.findAllByCurrentUserClient();
        if (teachers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(teachers);
    }

    @ResponseBody
    @GetMapping(value = "/photo")
    public ResponseEntity<String> downloadFile(@RequestParam(name = "userLogin") String userLogin) {
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityUpdateAlert(Constants.APPLICATION_NAME, false,
                TEACHER_ENTITY, "/photo is not yet implemented."))
            .build();
    }

    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE,
        AuthoritiesConstants.INFIRMARY,
        AuthoritiesConstants.TEACHERS,
    })
    @GetMapping(value = "/search")
    public ResponseEntity<List<TeacherDTO>> searchTeacher(@RequestParam("name")
                                                          @NotNull
                                                          @Size(min = Constants.TEACHER_NAME_FOR_SEARCH_MIN_LENGTH)
                                                              String name) {
        logger.debug(String.format("REST request to get teachers by name : %s", name));
        List<TeacherDTO> result = teacherApplication.searchByName(name);
        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);

    }

    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE,
        AuthoritiesConstants.INFIRMARY,
        AuthoritiesConstants.TEACHERS,
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<TeacherDTO> findTeacher(@PathVariable(name = "id") String id) {
        logger.debug(String.format("REST request to get Teacher : %s", id));
        Optional<TeacherDTO> optTeacher = teacherApplication.findOneById(id);
        return optTeacher
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.noContent().build());
    }

    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE,
        AuthoritiesConstants.INFIRMARY,
        AuthoritiesConstants.TEACHERS,
    })
    @GetMapping(value = "/byGrade/{gradeId}")
    public ResponseEntity<List<TeacherDTO>> findByGrade(@PathVariable(name = "gradeId") String gradeId) {
        logger.debug(String.format("REST request to get Teacher by grade id : %s", gradeId));
        List<TeacherDTO> teachers = teacherApplication.findOneByGrade(gradeId);
        if (!teachers.isEmpty()) {
            return ResponseEntity.ok(teachers);
        }
        return ResponseEntity.noContent().build();
    }

    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE,
        AuthoritiesConstants.INFIRMARY,
        AuthoritiesConstants.TEACHERS,
    })
    @PostMapping(value = "/teachers/by-grades-and-subjects")
    public @ResponseBody
    ResponseEntity<List<TeacherDTO>> findByGradesIdsAndSubjectsIds(@RequestBody TaughtSubjectsSearch taughtSubjectsSearch) {
        logger.debug(String.format("REST request to get Teachers ByGradesIdsAndSubjectsIds : %s %s", taughtSubjectsSearch.getGradesIds(), taughtSubjectsSearch.getSubjectsIds()));
        List<TeacherDTO> teachers = teacherApplication.findByGradesIdsAndSubjectsIds(taughtSubjectsSearch.getGradesIds(), taughtSubjectsSearch.getSubjectsIds());
        if (!teachers.isEmpty()) {
            return ResponseEntity.ok(teachers);
        }
        return ResponseEntity.noContent().build();
    }

}
