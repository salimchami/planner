package io.edukativ.myskoolin.front.web.rest;

import io.edukativ.myskoolin.application.GradeApplication;
import io.edukativ.myskoolin.domain.commons.AuthoritiesConstants;
import io.edukativ.myskoolin.infrastructure.config.Constants;
import io.edukativ.myskoolin.infrastructure.grades.GradeDTO;
import io.edukativ.myskoolin.infrastructure.grades.GradeSerieVO;
import io.github.jhipster.web.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Grade.
 */
@RestController
@RequestMapping("/api/grades")
class GradeResource {

    private final Logger log = LoggerFactory.getLogger(GradeResource.class);

    private final GradeApplication gradeApplication;

    GradeResource(GradeApplication gradeApplication) {
        this.gradeApplication = gradeApplication;
    }

    @PostMapping
    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE
    })
    public ResponseEntity<List<GradeDTO>> createGrades(@RequestBody List<GradeDTO> grades) {
        log.debug("REST request to update Grades : {}", grades);
        List<GradeDTO> gradesDTO = gradeApplication.createGrades(grades);
        return ResponseEntity.status(HttpStatus.CREATED).body(gradesDTO);
    }

    @PutMapping
    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE
    })
    public ResponseEntity<List<GradeDTO>> updateGrades(@RequestBody List<GradeDTO> grades) {
        log.debug("REST request to update Grades : {}", grades);
        List<GradeDTO> gradesDTO = gradeApplication.updateGrades(grades);
        return ResponseEntity.ok().body(gradesDTO);
    }
//

    @GetMapping
    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE
    })
    public ResponseEntity<List<GradeDTO>> getAllGrades() {
        log.debug("REST request to get all Grades");
        return new ResponseEntity<>(gradeApplication.findGrades(), HttpStatus.OK);
    }

    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE
    })
    @GetMapping(value = "/{name}")
    public ResponseEntity<GradeDTO> getGrade(@PathVariable String name) {
        log.debug("REST request to get Grade : {}", name);
        Optional<GradeDTO> optGrade = gradeApplication.findGradeByName(name);
        return optGrade.map(grade -> new ResponseEntity<>(grade, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable String id) {
        log.debug("REST request to delete Grade : {}", id);
        gradeApplication.deleteGrade(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
            .headers(HeaderUtil.createEntityDeletionAlert(Constants.APPLICATION_NAME, false, "grade", id))
            .build();
    }

    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE
    })
    @Transactional
    @GetMapping(value = "/nameAvailable/{name}")
    public Boolean nameAvailable(@PathVariable String name) {
        log.debug("REST request to get Client by name : {}", name);
        return gradeApplication.isGradeAvailableByName(name);
    }

    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE
    })
    @Transactional
    @GetMapping(value = "/diminutiveAvailable/{diminutive}")
    public Boolean diminutiveAvailable(@PathVariable String diminutive) {
        log.debug("REST request to get Client by diminutive : {}", diminutive);
        return gradeApplication.isGradeDiminutiveAvailable(diminutive);
    }

    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE
    })
    @GetMapping(value = "/allSeries")
    public ResponseEntity<List<GradeSerieVO>> getAllGradesSeries() {
        log.debug("REST request to get all Grades");
        List<GradeSerieVO> gradesSeries = gradeApplication.findAllSeries();
        return new ResponseEntity<>(gradesSeries, HttpStatus.OK);
    }
}
