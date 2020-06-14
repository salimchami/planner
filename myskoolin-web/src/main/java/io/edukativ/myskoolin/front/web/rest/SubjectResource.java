package io.edukativ.myskoolin.front.web.rest;

import io.edukativ.myskoolin.application.SubjectApplication;
import io.edukativ.myskoolin.domain.commons.AuthoritiesConstants;
import io.edukativ.myskoolin.infrastructure.config.Constants;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectDTO;
import io.github.jhipster.web.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Subject.
 */
@RestController
@RequestMapping("/api/subjects")
class SubjectResource {

    private final Logger log = LoggerFactory.getLogger(SubjectResource.class);
    private final SubjectApplication subjectApplication;
    private final String entityName;

    SubjectResource(SubjectApplication subjectApplication) {
        this.subjectApplication = subjectApplication;
        this.entityName = "subject";
    }

    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE
    })
    @PostMapping
    public ResponseEntity<SubjectDTO> createSubject(@RequestBody SubjectDTO subject) {
        log.debug("REST request to save Subject : {}", subject);
        if (subject.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(Constants.APPLICATION_NAME,
                false, entityName, "", "A new subject cannot already have an ID")).body(null);
        }
        SubjectDTO createdSubject = subjectApplication.createSubject(subject);
        return ResponseEntity.status(HttpStatus.CREATED)
            .headers(HeaderUtil.createEntityCreationAlert(Constants.APPLICATION_NAME, false, entityName, createdSubject.getId()))
            .body(createdSubject);
    }

    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE
    })
    @PutMapping
    public ResponseEntity<SubjectDTO> updateSubject(@RequestBody SubjectDTO subject) {
        log.debug("REST request to update Subject : {}", subject);
        if (subject.getId() == null) {
            return createSubject(subject);
        }
        SubjectDTO updatedSubject = subjectApplication.updateSubject(subject);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(Constants.APPLICATION_NAME, false, entityName, subject.getId()))
            .body(updatedSubject);
    }

    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE,
        AuthoritiesConstants.STUDENTS,
        AuthoritiesConstants.INFIRMARY,
        AuthoritiesConstants.TEACHERS,
        AuthoritiesConstants.STUDENT_RESPONSIBLE
    })
    @GetMapping
    public ResponseEntity<List<SubjectDTO>> getAllSubjects() {
        log.debug("REST request to get all Subjects");
        List<SubjectDTO> subjects = subjectApplication.findAll();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE,
        AuthoritiesConstants.INFIRMARY,
        AuthoritiesConstants.TEACHERS,
    })
    @GetMapping(value = "/search/{query}")
    public ResponseEntity<List<SubjectDTO>> getAllSearchedSubjects(@PathVariable("query") String query) {
        log.debug("REST request to get all searched Subjects");
        List<SubjectDTO> subjects = subjectApplication.searchByName(query);
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE,
        AuthoritiesConstants.INFIRMARY,
        AuthoritiesConstants.TEACHERS
    })
    @PostMapping(value = "/by-grades")
    public ResponseEntity<List<SubjectDTO>> getSubjectsByGradesIds(@RequestBody List<String> gradesIds) {
        log.debug("REST request to get all searched Subjects");
        List<SubjectDTO> subjects = subjectApplication.subjectsByGradesId(gradesIds);
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE,
        AuthoritiesConstants.STUDENTS,
        AuthoritiesConstants.INFIRMARY,
        AuthoritiesConstants.TEACHERS,
        AuthoritiesConstants.STUDENT_RESPONSIBLE
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<SubjectDTO> getSubject(@PathVariable String id) {
        log.debug("REST request to get Subject : {}", id);
        Optional<SubjectDTO> optSubject = subjectApplication.findById(id);
        return optSubject
            .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE,
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable String id) {
        log.debug("REST request to delete Subject : {}", id);
        subjectApplication.delete(id);
        return ResponseEntity
            .accepted()
            .headers(HeaderUtil.createEntityDeletionAlert(Constants.APPLICATION_NAME, false, entityName, id))
            .build();
    }

}
