package io.edukativ.myskoolin.infrastructure.teachers;

import io.edukativ.myskoolin.domain.teachers.Teacher;
import io.edukativ.myskoolin.infrastructure.app.mapper.AuthorityMapper;
import io.edukativ.myskoolin.infrastructure.common.mapper.AddressMapper;
import io.edukativ.myskoolin.infrastructure.common.mapper.ObjectIdMapper;
import io.edukativ.myskoolin.infrastructure.grades.GradeMapper;
import io.edukativ.myskoolin.infrastructure.medical.MedicalInfosMapper;
import io.edukativ.myskoolin.infrastructure.schooling.AbsenceMapper;
import io.edukativ.myskoolin.infrastructure.schooling.InfirmaryStatisticsMapper;
import io.edukativ.myskoolin.infrastructure.schooling.SchoolClassTimeSlotMapper;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectMapper;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class TeacherMapperImplemented {

    @Autowired
    private ObjectIdMapper objectIdMapper;
    @Autowired
    private AuthorityMapper authorityMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private AbsenceMapper absenceMapper;
    @Autowired
    private SubjectMapper subjectMapper;
    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private MedicalInfosMapper medicalInfosMapper;
    @Autowired
    private InfirmaryStatisticsMapper infirmaryStatisticsMapper;
    @Autowired
    private SchoolClassTimeSlotMapper schoolClassTimeSlotMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    public TeacherDbDTO domainToDbDto(Teacher teacher) {

        if (teacher == null) {
            return null;
        }

        TeacherDbDTO.TeacherDbDTOBuilder teacherBuilder = new TeacherDbDTO.TeacherDbDTOBuilder()
                .employedDate(teacher.getEmployedDate())
                .substitute(teacher.getSubstitute())
                .absences(absenceMapper.domainsToDbVos(teacher.getAbsences()))
                .taughtSubjects(subjectMapper.domainsToDbDtos(teacher.getTaughtSubjects()))
                .timetable(schoolClassTimeSlotMapper.domainsToDbVos(teacher.getTimetable()))
                .exitDate(teacher.getExitDate())
                .comment(teacher.getComment())
                .familySituation(teacher.getFamilySituation())
                .proCellPhone(teacher.getProCellPhone())
                .proPhone(teacher.getProPhone())
                .proEmail(teacher.getProEmail())
                .infirmaryStatistics(infirmaryStatisticsMapper.domainToDbVo(teacher.getInfirmaryStatistics()))
                .medicalInfos(medicalInfosMapper.domainToDbVo(teacher.getMedicalInfos()))
                .exitReason(teacher.getExitReason())
                .grades(gradeMapper.domainsToDbDtos(teacher.getGrades()));
        if (!teacher.getSubstitutedTeachers().isEmpty()) {
            teacherBuilder = teacherBuilder.substitutedTeachers(teacherMapper.domainsToDbDtos(teacher.getSubstitutedTeachers()));
        }
        return teacherBuilder
                .id(teacher.getId())
                .login(teacher.getLogin())
                .clientId(objectIdMapper.map(teacher.getClientId()))
                .password(teacher.getPassword())
                .gender(teacher.getGender())
                .firstName(teacher.getFirstName())
                .lastName(teacher.getLastName())
                .cellPhone(teacher.getCellPhone())
                .homePhone(teacher.getHomePhone())
                .nationality(teacher.getNationality())
                .email(teacher.getEmail())
                .activated(teacher.isActivated())
                .langKey(teacher.getLangKey())
                .imageUrl(teacher.getImageUrl())
                .activationKey(teacher.getActivationKey())
                .resetKey(teacher.getResetKey())
                .resetDate(teacher.getResetDate())
                .authorities(authorityMapper.domainsToDbDtos(teacher.getAuthorities()))
                .address(addressMapper.domainToDbDto(teacher.getAddress()))
                .birthDate(teacher.getBirthDate())
                .deleted(teacher.isDeleted())
                .archived(teacher.isArchived())
                .createdBy(teacher.getCreatedBy())

                .build();
    }
}
