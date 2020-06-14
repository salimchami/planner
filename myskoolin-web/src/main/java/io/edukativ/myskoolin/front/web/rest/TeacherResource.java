package io.edukativ.myskoolin.front.web.rest;

import io.edukativ.myskoolin.application.TeacherApplication;
import io.edukativ.myskoolin.domain.commons.AuthoritiesConstants;
import io.edukativ.myskoolin.front.web.util.WebUtil;
import io.edukativ.myskoolin.infrastructure.config.Constants;
import io.edukativ.myskoolin.infrastructure.teachers.TaughtSubjectsSearch;
import io.edukativ.myskoolin.infrastructure.teachers.TeacherDTO;
import io.github.jhipster.web.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherResource.class);
    private static final String TEACHER_ENTITY = "teacher";

    private final TeacherApplication teacherApplication;

    public TeacherResource(TeacherApplication teacherApplication) {
        this.teacherApplication = teacherApplication;
    }

    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE,
    })
    @ResponseBody
    @PostMapping
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody TeacherDTO teacher, HttpServletRequest request) throws URISyntaxException {
        LOGGER.debug("REST request to save Teacher : {}", teacher);
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
        LOGGER.debug("REST request to update Teacher : {}", teacherDTO);
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
        LOGGER.debug("REST request to get a page of teachers");
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
            .headers(HeaderUtil.createEntityUpdateAlert(Constants.APPLICATION_NAME, false, TEACHER_ENTITY, "/photo is not yet implemented."))
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
        LOGGER.debug("REST request to get teachers by name : {}", name);
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
        LOGGER.debug("REST request to get Teacher : {}", id);
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
        LOGGER.debug("REST request to get Teacher by grade id : {}", gradeId);
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
        LOGGER.debug("REST request to get Teachers ByGradesIdsAndSubjectsIds : {} {}", taughtSubjectsSearch.getGradesIds(), taughtSubjectsSearch.getSubjectsIds());
        List<TeacherDTO> teachers = teacherApplication.findByGradesIdsAndSubjectsIds(taughtSubjectsSearch.getGradesIds(), taughtSubjectsSearch.getSubjectsIds());
        if (!teachers.isEmpty()) {
            return ResponseEntity.ok(teachers);
        }
        return ResponseEntity.noContent().build();
    }

}
