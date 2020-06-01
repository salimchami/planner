package io.edukativ.myskoolin.front.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing Grade.
 */
@RestController
@RequestMapping("/api")
class GradeResource {

    private final Logger log = LoggerFactory.getLogger(GradeResource.class);

//    @Timed
//    @PostMapping(value = "/grades")
//    public ResponseEntity<List<GradeDTO>> createGrades(@RequestBody List<GradeDTO> grades) throws URISyntaxException {
//        log.debug("REST request to save Grades : {}", grades);
//        List<Grade> gradesModel = grades.stream().map(GradeDTO::toGrade).collect(Collectors.toList());
//        List<Grade> result = gradeRepository.save(gradesModel);
//        return ResponseEntity.created(new URI("/api/grades"))
//            .body(result.stream().map(GradeDTO::new).collect(Collectors.toList()));
//    }
//
//    @Timed
//    @PutMapping(value = "/grades")
//    public ResponseEntity<List<GradeDTO>> updateGrades(@RequestBody List<GradeDTO> grades) throws URISyntaxException {
//        log.debug("REST request to update Grades : {}", grades);
//        grades.forEach(gradeDTO -> gradeDTO.setDeleted(false));
//        List<Grade> gradesModel = gradeMapper.gradesDtoToGrades(grades);
//        List<Grade> resultModelResult = gradeRepository.save(gradesModel);
//        List<GradeDTO> gradesDTO = gradeMapper.gradesToGradesDTO(resultModelResult);
//        return ResponseEntity.ok().body(gradesDTO);
//    }
//
//    @Timed
//    @GetMapping(value = "/grades")
//    public ResponseEntity<List<GradeDTO>> getAllGrades() {
//        log.debug("REST request to get all Grades");
//        List<Grade> gradesModel = gradeService.allGrades();
//        List<GradeDTO> grades = gradeMapper.gradesToGradesDTO(gradesModel);
//        grades.forEach(grade -> {
//            Integer nbSubjects = subjectService.countByGrade(grade.getId());
//            grade.setNbSubjects(nbSubjects);
//
//        });
//        return new ResponseEntity<>(grades, HttpStatus.OK);
//    }
//
//    @Timed
//    @GetMapping(value = "/grades/{name}")
//    public ResponseEntity<GradeDTO> getGrade(@PathVariable String name) {
//        log.debug("REST request to get Grade : {}", name);
//        Optional<GradeDTO> optGrade = gradeService.findOneByName(name);
//        return optGrade
//            .map(grade -> {
//                List<Subject> subjects = subjectService.findByGrade(new ObjectId(grade.getId()));
//                grade.setSubjects(subjectMapper.subjectsToSubjectsDTO(subjects));
//                return new ResponseEntity<>(
//                    grade,
//                    HttpStatus.OK);
//            })
//            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//    @Timed
//    @DeleteMapping(value = "/grades/{id}")
//    public ResponseEntity<Void> deleteGrade(@PathVariable String id) {
//        log.debug("REST request to delete Grade : {}", id);
//        gradeRepository.delete(id);
//        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("grade", id)).build();
//    }
//
//    @Timed
//    @Transactional
//    @GetMapping(value = "/grades/nameAvailable/{name}")
//    public Boolean nameAvailable(@PathVariable String name) {
//        log.debug("REST request to get Client by name : {}", name);
//        final Boolean[] result = {false};
//        Optional<User> currentUser = userService.getUserWithAuthorities();
//        currentUser.ifPresent(user -> {
//            Optional<Grade> grade = gradeRepository.findOneByName(name, false, currentUser.get().getClientId());
//            grade.ifPresent(grade1 -> result[0] = true);
//        });
//        return result[0];
//    }
//
//    @Timed
//    @Transactional
//    @GetMapping(value = "/grades/diminutiveAvailable/{diminutive}")
//    public Boolean diminutiveAvailable(@PathVariable String diminutive) {
//        log.debug("REST request to get Client by diminutive : {}", diminutive);
//        final Boolean[] result = {false};
//        Optional<User> currentUser = userService.getUserWithAuthorities();
//        currentUser.ifPresent(user -> {
//            Optional<Grade> grade = gradeRepository.findOneByDiminutive(diminutive, false, currentUser.get().getClientId());
//            grade.ifPresent(grade1 -> result[0] = true);
//        });
//        return result[0];
//    }
//
//    @Timed
//    @GetMapping(value = "/grades/allSeries")
//    public ResponseEntity<List<GradeSerie>> getAllGradesSeries() {
//        log.debug("REST request to get all Grades");
//        List<GradeSerie> gradesSeries = gradeService.findAllSeries();
//        return new ResponseEntity<>(gradesSeries, HttpStatus.OK);
//    }

}
