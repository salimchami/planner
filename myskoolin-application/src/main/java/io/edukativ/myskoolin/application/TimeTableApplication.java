package io.edukativ.myskoolin.application;

import io.edukativ.myskoolin.application.security.UserService;
import io.edukativ.myskoolin.domain.timetabling.SchoolClassTimeTable;
import io.edukativ.myskoolin.domain.timetabling.TimeTableSPI;
import io.edukativ.myskoolin.domain.timetabling.TimeTableSolverAPI;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import io.edukativ.myskoolin.infrastructure.commercial.ClientDbDTO;
import io.edukativ.myskoolin.infrastructure.commercial.ClientRepository;
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
import io.edukativ.myskoolin.infrastructure.timetabling.SchoolClassTimeTableDTO;
import io.edukativ.myskoolin.infrastructure.timetabling.SchoolClassTimeTableMapper;
import io.edukativ.myskoolin.infrastructure.timetabling.TimeTableOptionsDbVO;
import io.edukativ.myskoolin.infrastructure.timetabling.TimeTableOptionsMapper;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Component
public class TimeTableApplication {

    private final TimeTableSolverAPI timeTableSolverAPI;
    private final UserService userService;
    private final SchoolRoomRepository schoolRoomRepository;
    private final SchoolClassMapper schoolClassMapper;
    private final SchoolRoomMapper schoolRoomMapper;
    private final SubjectMapper subjectMapper;
    private final TeacherMapper teacherMapper;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final SchoolClassRepository schoolClassRepository;
    private final SchoolClassTimeTableMapper schoolClassTimeTableMapper;
    private final ClientRepository clientRepository;
    private final TimeTableOptionsMapper timeTableOptionsMapper;
    private final TimeTableSPI timeTableSPI;


    public TimeTableApplication(TimeTableSolverAPI timeTableSolverAPI, SchoolClassMapper schoolClassMapper,
                                UserService userService, SchoolRoomRepository schoolRoomRepository,
                                SchoolRoomMapper schoolRoomMapper, SubjectMapper subjectMapper,
                                TeacherMapper teacherMapper, SubjectRepository subjectRepository,
                                TeacherRepository teacherRepository, SchoolClassRepository schoolClassRepository,
                                SchoolClassTimeTableMapper schoolClassTimeTableMapper, ClientRepository clientRepository, TimeTableOptionsMapper timeTableOptionsMapper, TimeTableSPI timeTableSPI) {
        this.timeTableSolverAPI = timeTableSolverAPI;
        this.schoolClassMapper = schoolClassMapper;
        this.userService = userService;
        this.schoolRoomRepository = schoolRoomRepository;
        this.schoolRoomMapper = schoolRoomMapper;
        this.subjectMapper = subjectMapper;
        this.teacherMapper = teacherMapper;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
        this.schoolClassRepository = schoolClassRepository;
        this.schoolClassTimeTableMapper = schoolClassTimeTableMapper;
        this.clientRepository = clientRepository;
        this.timeTableOptionsMapper = timeTableOptionsMapper;
        this.timeTableSPI = timeTableSPI;
    }

    @Transactional
    public void solveNewTimeTablesForSchoolClasses() {
        final UserDbDTO currentUser = userService.currentUserWithAuthorities();
        final ObjectId clientId = currentUser.getClientId();
        final List<SchoolRoomDbDTO> schoolRooms = schoolRoomRepository.findByClientId(false, clientId);
        final List<SubjectDbDTO> subjects = subjectRepository.findAllNotDeleted(clientId);
        final List<TeacherDbDTO> teachers = teacherRepository.findAllNotDeletedTeachers(clientId);
        final List<SchoolClassDbDTO> schoolClasses = schoolClassRepository.findAllNotDeletedSchoolClasses(clientId);
        final Optional<ClientDbDTO> optClient = clientRepository.findById(clientId.toString());
        final TimeTableOptionsDbVO timeTableOptionsDbVO = optClient.map(ClientDbDTO::getTimeTableOptions).orElseThrow();
        timeTableSolverAPI.solveForAllSchoolClasses(clientId.toString(),
                schoolRoomMapper.dbDtosToDomains(schoolRooms),
                subjectMapper.dbDtosToDomains(subjects),
                teacherMapper.dbDtosToDomains(teachers),
                schoolClassMapper.dbDtosToDomains(schoolClasses), timeTableOptionsMapper.dbVoToDomain(timeTableOptionsDbVO));
    }

    @Transactional
    public void solveNewTimeTablesForSchoolClass(String id) {
        final UserDbDTO currentUser = userService.currentUserWithAuthorities();
        final ObjectId clientId = currentUser.getClientId();
        final List<SchoolRoomDbDTO> schoolRooms = schoolRoomRepository.findByClientId(false, clientId);
        final List<SubjectDbDTO> subjects = subjectRepository.findAllNotDeleted(clientId);
        final List<TeacherDbDTO> teachers = teacherRepository.findAllNotDeletedTeachers(clientId);
        final List<SchoolClassDbDTO> schoolClasses = schoolClassRepository.findAllNotDeletedSchoolClasses(clientId);
        final Optional<ClientDbDTO> optClient = clientRepository.findById(clientId.toString());
        final TimeTableOptionsDbVO timeTableOptionsDbVO = optClient.map(ClientDbDTO::getTimeTableOptions).orElseThrow();
        timeTableSolverAPI.solveForSchoolClass(id, clientId.toString(),
                schoolRoomMapper.dbDtosToDomains(schoolRooms),
                subjectMapper.dbDtosToDomains(subjects),
                teacherMapper.dbDtosToDomains(teachers),
                schoolClassMapper.dbDtosToDomains(schoolClasses),
                timeTableOptionsMapper.dbVoToDomain(timeTableOptionsDbVO));
    }

    public String solverStatus(String timeTableId) {
        return timeTableSolverAPI.solverStatus(timeTableId);
    }

    @Transactional
    public Optional<SchoolClassTimeTableDTO> timeTableBySchoolClassId(String schoolClassTimeTableId) {
        final Optional<SchoolClassTimeTable> schoolClassTimeTable = timeTableSolverAPI.timeTableBySchoolCLassId(schoolClassTimeTableId);
        return schoolClassTimeTable.map(schoolClassTimeTableMapper::domainToDto);
    }

    @Transactional
    public List<SchoolClassTimeTableDTO> timeTables() {
        final UserDbDTO currentUser = userService.currentUserWithAuthorities();
        final ObjectId clientId = currentUser.getClientId();
        final List<SchoolClassTimeTable> schoolClassTimeTables = timeTableSolverAPI.timeTables(clientId.toString());
        return schoolClassTimeTableMapper.domainsToDtos(schoolClassTimeTables);
    }

    public long countTimetables() {
        return timeTableSPI.countTimeTables();
    }

    public Instant lastGenerationDate() {
        return timeTableSPI.lastGenerationDate();
    }
}
