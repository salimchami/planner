package io.edukativ.myskoolin.front.web.rest;

import io.edukativ.myskoolin.application.SubjectApplication;
import io.edukativ.myskoolin.infrastructure.config.Constants;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectDTO;
import io.github.jhipster.web.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    /**
     * POST  /subjects : Create a new subject.
     *
     * @param subject the subject to create
     * @return the ResponseEntity with status 201 (Created) and with body the new subject, or with status 400 (Bad Request) if the subject has already an ID
     */
    @PostMapping
    public ResponseEntity<SubjectDTO> createSubject(@RequestBody SubjectDTO subject) {
        log.debug("REST request to save Subject : {}", subject);
        if (subject.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(Constants.APPLICATION_NAME,
                false, entityName, "", "A new subject cannot already have an ID")).body(null);
        }
        SubjectDTO createdSubject = subjectApplication.createSubject(subject);
        return ResponseEntity.status(HttpStatus.CREATED)
            .headers(HeaderUtil.createEntityCreationAlert(Constants.APPLICATION_NAME, false, entityName, createdSubject.getId().toString()))
            .body(createdSubject);
    }

    /**
     * PUT  /subjects : Updates an existing subject.
     *
     * @param subject the subject to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated subject,
     * or with status 400 (Bad Request) if the subject is not valid,
     * or with status 500 (Internal Server Error) if the subject couldnt be updated
     */
    @PutMapping
    public ResponseEntity<SubjectDTO> updateSubject(@RequestBody SubjectDTO subject) {
        log.debug("REST request to update Subject : {}", subject);
        if (subject.getId() == null) {
            return createSubject(subject);
        }
        SubjectDTO updatedSubject = subjectApplication.updateSubject(subject);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(Constants.APPLICATION_NAME, false, entityName, subject.getId().toString()))
            .body(updatedSubject);
    }

    /**
     * GET  /subjects : get all the subjects.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of subjects in body
     */
    @GetMapping
    public ResponseEntity<List<SubjectDTO>> getAllSubjects() {
        log.debug("REST request to get all Subjects");
        List<SubjectDTO> subjects = subjectApplication.findAll();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    /**
     * GET  /subjects/search : get all searched subjects.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of subjects in body
     */
    @GetMapping(value = "/search/{query}")
    public ResponseEntity<List<SubjectDTO>> getAllSearchedSubjects(@PathVariable("query") String query) {
        log.debug("REST request to get all searched Subjects");
        List<SubjectDTO> subjects = subjectApplication.searchByName(query);
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    /**
     * GET  /subjects/search : get all searched subjects.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of subjects in body
     */
    @PostMapping(value = "/by-grades")
    public ResponseEntity<List<SubjectDTO>> getSubjectsByGradesIds(@RequestBody List<String> gradesIds) {
        log.debug("REST request to get all searched Subjects");
        List<SubjectDTO> subjects = subjectApplication.subjectsByGradesId(gradesIds);
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    /**
     * GET  /subjects/:id : get the "id" subject.
     *
     * @param id the id of the subject to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the subject, or with status 404 (Not Found)
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<SubjectDTO> getSubject(@PathVariable String id) {
        log.debug("REST request to get Subject : {}", id);
        Optional<SubjectDTO> optSubject = subjectApplication.findById(id);
        return optSubject
            .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    /**
     * DELETE  /subjects/:id : delete the "id" subject.
     *
     * @param id the id of the subject to delete
     * @return the ResponseEntity with status 200 (OK)
     */
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
