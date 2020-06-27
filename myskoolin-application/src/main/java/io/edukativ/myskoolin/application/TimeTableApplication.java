package io.edukativ.myskoolin.application;

import io.edukativ.myskoolin.application.security.UserService;
import io.edukativ.myskoolin.domain.timetabling.TimeTableGenerationAPI;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import io.edukativ.myskoolin.infrastructure.schoolclasses.SchoolClassDbDTO;
import io.edukativ.myskoolin.infrastructure.schoolclasses.SchoolClassMapper;
import io.edukativ.myskoolin.infrastructure.schoolclasses.SchoolClassRepository;
import io.edukativ.myskoolin.infrastructure.schoolrooms.SchoolRoomDbDTO;
import io.edukativ.myskoolin.infrastructure.schoolrooms.SchoolRoomMapper;
import io.edukativ.myskoolin.infrastructure.schoolrooms.SchoolRoomRepository;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectDbDTO;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectMapper;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectRepository;
import io.edukativ.myskoolin.infrastructure.teachers.TeacherDbDTO;
import io.edukativ.myskoolin.infrastructure.teachers.TeacherMapper;
import io.edukativ.myskoolin.infrastructure.teachers.TeacherRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class TimeTableApplication {

    private final TimeTableGenerationAPI timeTableGenerationAPI;
    private final UserService userService;
    private final SchoolRoomRepository schoolRoomRepository;
    private final SchoolClassMapper schoolClassMapper;
    private final SchoolRoomMapper schoolRoomMapper;
    private final SubjectMapper subjectMapper;
    private final TeacherMapper teacherMapper;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final SchoolClassRepository schoolClassRepository;

    public TimeTableApplication(TimeTableGenerationAPI timeTableGenerationAPI, SchoolClassMapper schoolClassMapper,
                                UserService userService, SchoolRoomRepository schoolRoomRepository,
                                SchoolRoomMapper schoolRoomMapper, SubjectMapper subjectMapper,
                                TeacherMapper teacherMapper, SubjectRepository subjectRepository,
                                TeacherRepository teacherRepository, SchoolClassRepository schoolClassRepository) {
        this.timeTableGenerationAPI = timeTableGenerationAPI;
        this.schoolClassMapper = schoolClassMapper;
        this.userService = userService;
        this.schoolRoomRepository = schoolRoomRepository;
        this.schoolRoomMapper = schoolRoomMapper;
        this.subjectMapper = subjectMapper;
        this.teacherMapper = teacherMapper;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
        this.schoolClassRepository = schoolClassRepository;
    }

    @Transactional
    public List<String> generateNewTimeTablesForSchoolClasses() {
        final UserDbDTO currentUser = userService.currentUserWithAuthorities();
        final ObjectId clientId = currentUser.getClientId();
        final List<SchoolRoomDbDTO> schoolRooms = schoolRoomRepository.findByClientId(false, clientId);
        final List<SubjectDbDTO> subjects = subjectRepository.findAllNotDeleted(clientId);
        final List<TeacherDbDTO> teachers = teacherRepository.findAllNotDeletedTeachers(clientId);
        final List<SchoolClassDbDTO> schoolClasses = schoolClassRepository.findAllNotDeletedSchoolClasses(clientId);
        return timeTableGenerationAPI.solveForAllSchoolClasses(clientId.toString(),
                schoolRoomMapper.dbDtosToDomains(schoolRooms),
                subjectMapper.dbDtosToDomains(subjects),
                teacherMapper.dbDtosToDomains(teachers),
                schoolClassMapper.dbDtosToDomains(schoolClasses));

    }

    @Transactional
    public String generateNewTimeTablesForSchoolClass(String id) {
        final UserDbDTO currentUser = userService.currentUserWithAuthorities();
        final ObjectId clientId = currentUser.getClientId();
        final List<SchoolRoomDbDTO> schoolRooms = schoolRoomRepository.findByClientId(false, clientId);
        final List<SubjectDbDTO> subjects = subjectRepository.findAllNotDeleted(clientId);
        final List<TeacherDbDTO> teachers = teacherRepository.findAllNotDeletedTeachers(clientId);
        final List<SchoolClassDbDTO> schoolClasses = schoolClassRepository.findAllNotDeletedSchoolClasses(clientId);
        return timeTableGenerationAPI.solveForSchoolClass(id, clientId.toString(),
                schoolRoomMapper.dbDtosToDomains(schoolRooms),
                subjectMapper.dbDtosToDomains(subjects),
                teacherMapper.dbDtosToDomains(teachers),
                schoolClassMapper.dbDtosToDomains(schoolClasses)
        );
    }
}
