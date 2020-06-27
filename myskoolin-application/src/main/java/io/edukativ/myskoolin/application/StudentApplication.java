package io.edukativ.myskoolin.application;

import io.edukativ.myskoolin.application.security.UserService;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import io.edukativ.myskoolin.infrastructure.students.StudentDTO;
import io.edukativ.myskoolin.infrastructure.students.StudentDbDTO;
import io.edukativ.myskoolin.infrastructure.students.StudentMapper;
import io.edukativ.myskoolin.infrastructure.students.StudentRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentApplication {

    private final StudentRepository studentRepository;
    private final UserService userService;
    private final StudentMapper studentMapper;

    public StudentApplication(StudentRepository studentRepository, UserService userService,
                              StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.userService = userService;
        this.studentMapper = studentMapper;
    }

    public List<StudentDTO> findAll() {
        UserDbDTO currentUser = userService.currentUserWithAuthorities();
        final List<StudentDbDTO> students = studentRepository.findAllNotDeletedStudents(currentUser.getClientId());
        return studentMapper.dbDtosToDtos(students);
    }
}
