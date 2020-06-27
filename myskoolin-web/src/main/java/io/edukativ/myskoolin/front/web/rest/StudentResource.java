package io.edukativ.myskoolin.front.web.rest;

import io.edukativ.myskoolin.application.StudentApplication;
import io.edukativ.myskoolin.domain.commons.MyskoolinLoggerSPI;
import io.edukativ.myskoolin.infrastructure.students.StudentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentResource {

    private final MyskoolinLoggerSPI logger;
    private StudentApplication studentApplication;

    public StudentResource(MyskoolinLoggerSPI logger, StudentApplication studentApplication) {
        this.logger = logger;
        this.studentApplication = studentApplication;
    }

    //    private static final String STUDENT_ENTITY = "student";
//
//    @PostMapping
//    @ResponseBody
//    public ResponseEntity createStudent(@RequestBody StudentDTO studentDTO, HttpServletRequest request) throws URISyntaxException {
//        log.debug("REST request to save Student : {}", studentDTO);
//
//        if (studentDTO.getLogin() == null || studentDTO.getEmail() == null) {
//            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("student",
//                "loginnull", "The new student and its responsible user account must have a login and an email")).body(null);
//        } else {
//            Optional<User> optDbUser = userRepository.findOneByLoginIgnoreCaseAndEmailIgnoreCase(studentDTO.getLogin(), studentDTO.getEmail());
//            if (optDbUser.isPresent()) {
//                return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("student",
//                    "loginexists", "The new student already exists - same login or email")).body(null);
//            }
//        }
//
//        Optional<StudentDTO> optStudent = studentService.create(studentDTO, WebUtil.baseUrl(request));
//        if (optStudent.isPresent()) {
//            StudentDTO studentDto = optStudent.get();
//            return ResponseEntity.created(new URI("/api/students/" + studentDto.getId()))
//                .headers(HeaderUtil.createEntityCreationAlert("student", studentDto.getId()))
//                .body(studentDto);
//        } else {
//            return ResponseEntity.unprocessableEntity().build();
//        }
//    }
//
//    @ResponseBody
//    @PutMapping
//    public ResponseEntity updateStudent(@RequestBody StudentDTO user, HttpServletRequest request) throws URISyntaxException {
//        log.debug("REST request to update Student : {}", user);
//        Optional<User> dbUser = userRepository.findOneByLoginIgnoreCase(user.getLogin());
//        if (!dbUser.isPresent()) {
//            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("student",
//                "loginexists", "The student does'nt exists")).body(null);
//        }
//        Optional<StudentDTO> optStudent = studentService.update(user, WebUtil.baseUrl(request));
//        if (optStudent.isPresent()) {
//            StudentDTO updatedStudent = optStudent.get();
//            return ResponseEntity.ok()
//                .headers(HeaderUtil.createEntityUpdateAlert("student", user.getLogin()))
//                .body(updatedStudent);
//        } else {
//            return ResponseEntity.unprocessableEntity().build();
//        }
//    }
//
    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        logger.debug("REST request to get a page of Students");
        List<StudentDTO> students = studentApplication.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
//
//    @GetMapping(value = "/{id}")
//    public ResponseEntity<StudentDTO> findStudent(@PathVariable(name = "id") String id) {
//        log.debug("REST request to get Student : {}");
//        return studentService.findOneById(id)
//            .map(student -> new ResponseEntity<>(student, HttpStatus.OK))
//            .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
//    }
//
//    @GetMapping("/search/{search:" + DomainConstants.LOGIN_REGEX + "}")
//    public ResponseEntity<List<StudentDTO>> searchStudent(@PathVariable String search) {
//        log.debug("REST request to get User : {}", search);
//        List<User> users = studentService.searchByNamesLoginEmail(search);
//        List<StudentDTO> students = studentMapper.usersToStudentsDto(users);
//        return Optional.of(students).map(student -> new ResponseEntity<>(student, HttpStatus.OK))
//            .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
//    }
//
//    @GetMapping(value = "/responsible/login")
//    public ResponseEntity<UserDTO> getStudentResponsibleAccount(@RequestParam(value = "login", required = false) String login) {
//        log.debug("REST request to get Student : {}");
//        return studentService.findRespAccountByStudentLogin(login)
//            .map(student -> new ResponseEntity<>(student, HttpStatus.OK))
//            .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
//    }

}
