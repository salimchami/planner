package io.edukativ.myskoolin.front.web.rest;

import io.edukativ.myskoolin.application.SchoolClassApplication;
import io.edukativ.myskoolin.domain.commons.AuthoritiesConstants;
import io.edukativ.myskoolin.domain.commons.MyskoolinLoggerSPI;
import io.edukativ.myskoolin.infrastructure.schoolclasses.SchoolClassDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing SchoolClass.
 */
@RestController
@RequestMapping("/api/school-classes")
public class SchoolClassResource {

    private final MyskoolinLoggerSPI logger;
    private final SchoolClassApplication schoolClassApplication;

    public SchoolClassResource(MyskoolinLoggerSPI logger, SchoolClassApplication schoolClassApplication) {
        this.logger = logger;
        this.schoolClassApplication = schoolClassApplication;
    }

//    @ResponseBody
//    @PostMapping
//    public ResponseEntity createSchoolClass(@RequestBody SchoolClassDTO schoolClass) throws URISyntaxException {
//        log.debug("REST request to save SchoolClass : {}", schoolClass);
//        if (schoolClass.getId() != null) {
//            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("schoolClass", "idexists", "A new schoolClass cannot already have an ID")).body(null);
//        } else {
//            Optional<SchoolClass> optDbSchoolClass = schoolClassService.findOneByNameNotDeleted(schoolClass.getName());
//            if (optDbSchoolClass.isPresent()) {
//                return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("student",
//                    "loginexists", "The new school class already exists - same")).body(null);
//            }
//        }
//        SchoolClass schoolClassToSave = schoolClassMapper.schoolClassDtoToSchoolClass(schoolClass);
//        Optional<SchoolClass> optSchoolClass = schoolClassService.save(schoolClassToSave, schoolClass.getStudents().stream().map(StudentDTO::getId).collect(Collectors.toList()));
//        if (optSchoolClass.isPresent()) {
//            return ResponseEntity.created(new URI("/api/school-classes/" + optSchoolClass.get().getId()))
//                .headers(HeaderUtil.createEntityCreationAlert("schoolClass", optSchoolClass.get().getId().toString()))
//                .body(optSchoolClass.get());
//        } else {
//            return ResponseEntity.badRequest().build();
//        }
//    }

//    @ResponseBody
//    @PutMapping
//    public ResponseEntity updateSchoolClass(@RequestBody SchoolClassDTO schoolClass) throws URISyntaxException {
//        log.debug("REST request to update SchoolClass : {}", schoolClass);
//        if (schoolClass.getId() == null) {
//            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("schoolClass",
//                "loginexists", "The schoolClass does'nt exists")).body(null);
//        }
//        SchoolClass schoolClassToUpdate = schoolClassMapper.schoolClassDtoToSchoolClass(schoolClass);
//        Optional<SchoolClass> optResult = schoolClassService.save(schoolClassToUpdate, schoolClass.getStudents().stream().map(StudentDTO::getId).collect(Collectors.toList()));
//        return optResult.map(result -> new ResponseEntity<>(
//            result,
//            HeaderUtil.createEntityUpdateAlert("schoolClass", schoolClass.getId()),
//            HttpStatus.OK))
//            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
//    }

    @GetMapping
    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE,
        AuthoritiesConstants.INFIRMARY,
        AuthoritiesConstants.TEACHERS,
    })
    @Transactional(readOnly = true)
    public ResponseEntity<List<SchoolClassDTO>> findAllSchoolClasses() {
        logger.debug("REST request to get a page of school-classes");
        List<SchoolClassDTO> schoolClasses = schoolClassApplication.findAll();
//        schoolClassesDTO.forEach(this::loadSchoolClassHeadTeachers);
//        populateHeadTeachers(schoolClassesDTO);
        return new ResponseEntity<>(schoolClasses, HttpStatus.OK);
    }

//    @GetMapping(value = "/school-classes/{id}")
//    public ResponseEntity<SchoolClassDTO> findSchoolClass(@PathVariable String id) {
//        log.debug("REST request to get SchoolClass : {}", id);
//        Optional<SchoolClass> optSchoolClass = schoolClassService.findOneById(id);
//        if (optSchoolClass.isPresent()) {
//            SchoolClassDTO schoolClassDTO = schoolClassMapper.schoolClassToSchoolClassDTO(optSchoolClass.get());
//            loadSchoolClassHeadTeachers(schoolClassDTO);
//            loadSchoolClassStudents(schoolClassDTO);
//            return new ResponseEntity<>(schoolClassDTO, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

//    @Secured({
//        AuthoritiesConstants.ADMINISTRATION,
//        AuthoritiesConstants.SCHOOL_LIFE,
//    })
//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<Void> deleteSchoolClass(@PathVariable String id) {
//        log.debug("REST request to delete SchoolClass : {}", id);
//        schoolClassService.delete(id);
//        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("schoolClass", id)).build();
//    }

//    private void populateHeadTeachers(List<SchoolClassDTO> schoolClassesDTO) {
//        schoolClassesDTO.forEach(schoolClassDTO -> {
//            if (schoolClassDTO.getHeadTeachers() != null && !schoolClassDTO.getHeadTeachers().isEmpty()) {
//                List<User> users = userRepository.findByIds(schoolClassDTO.getHeadTeachers()
//                    .stream()
//                    .map(headTeacher -> new ObjectId(headTeacher.getId()))
//                    .collect(Collectors.toList())
//                );
//                List<UserDTO> usersDTO = userMapper.usersToUserDTOs(users);
//                schoolClassDTO.setHeadTeachers(teacherMapper.usersDTOToTeachersDTO(usersDTO));
//            }
//        });
//    }
//
//    private void loadSchoolClassHeadTeachers(SchoolClassDTO schoolClassDTO) {
//        if (schoolClassDTO.getHeadTeachers() != null && !schoolClassDTO.getHeadTeachers().isEmpty()) {
//            List<User> users = userService.findByIds(schoolClassDTO.getHeadTeachers()
//                .stream()
//                .map(TeacherDTO::getId).collect(Collectors.toList()));
//            List<UserDTO> usersDTO = userMapper.usersToUserDTOs(users);
//            schoolClassDTO.setHeadTeachers(teacherMapper.usersDTOToTeachersDTO(usersDTO));
//        }
//    }
//
//    private void loadSchoolClassStudents(SchoolClassDTO schoolClassDTO) {
//        List<User> users = studentService.findBySchoolClassId(schoolClassDTO.getId());
//        schoolClassDTO.setStudents(studentMapper.usersToStudentsDto(users));
//    }

}
