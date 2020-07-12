package io.edukativ.myskoolin.infrastructure.teachers;

import io.edukativ.myskoolin.domain.teachers.Teacher;
import io.edukativ.myskoolin.infrastructure.absences.AbsenceMapper;
import io.edukativ.myskoolin.infrastructure.app.mapper.AuthorityMapper;
import io.edukativ.myskoolin.infrastructure.common.mapper.AddressMapper;
import io.edukativ.myskoolin.infrastructure.common.mapper.ObjectIdMapper;
import io.edukativ.myskoolin.infrastructure.grades.GradeMapper;
import io.edukativ.myskoolin.infrastructure.medical.InfirmaryStatisticsMapper;
import io.edukativ.myskoolin.infrastructure.medical.MedicalInfosMapper;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectMapper;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

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
    private TeacherMapperImplemented teacherMapper;

    public TeacherDbDTO domainToDbDto(Teacher teacher) {

        if (teacher == null) {
            return null;
        }

        TeacherDbDTO.TeacherDbDTOBuilder teacherBuilder = new TeacherDbDTO.TeacherDbDTOBuilder()
                .employedDate(teacher.getEmployedDate())
                .substitute(teacher.getSubstitute())
                .absences(absenceMapper.domainsToDbVos(teacher.getAbsences()))
                .taughtSubjects(subjectMapper.domainsToDbDtos(teacher.getTaughtSubjects()))
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
                .address(addressMapper.domainToDbVo(teacher.getAddress()))
                .birthDate(teacher.getBirthDate())
                .deleted(teacher.isDeleted())
                .archived(teacher.isArchived())
                .createdBy(teacher.getCreatedBy())

                .build();
    }

    public List<TeacherDTO> dbDtosToDtos(List<TeacherDbDTO> teachers) {
        return teachers.stream().map(this::dbDtoToDto).collect(Collectors.toList());
    }

    protected TeacherDTO dbDtoToDto(TeacherDbDTO teacherDbDTO) {
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setId(teacherDbDTO.getId());
        teacherDTO.setClientId(teacherDbDTO.getClientId().toString());
        teacherDTO.setLogin(teacherDbDTO.getLogin());
        teacherDTO.setGender(teacherDbDTO.getGender());
        teacherDTO.setFirstName(teacherDbDTO.getFirstName());
        teacherDTO.setLastName(teacherDbDTO.getLastName());
        teacherDTO.setCellPhone(teacherDbDTO.getCellPhone());
        teacherDTO.setHomePhone(teacherDbDTO.getHomePhone());
        teacherDTO.setNationality(teacherDbDTO.getNationality());
        teacherDTO.setEmail(teacherDbDTO.getEmail());
        teacherDTO.setImageUrl(teacherDbDTO.getImageUrl());
        teacherDTO.setLangKey(teacherDbDTO.getLangKey());
        teacherDTO.setCreatedBy(teacherDbDTO.getCreatedBy());
        teacherDTO.setCreatedDate(teacherDbDTO.getCreatedDate());
        teacherDTO.setLastModifiedBy(teacherDbDTO.getLastModifiedBy());
        teacherDTO.setLastModifiedDate(teacherDbDTO.getLastModifiedDate());
        teacherDTO.setAuthorities(authorityMapper.dbDtosToDtos(teacherDbDTO.getAuthorities()));
        teacherDTO.setAddress(addressMapper.dbVoToVo(teacherDbDTO.getAddress()));
        teacherDTO.setBirthDate(teacherDbDTO.getBirthDate());

        teacherDTO.setComment(teacherDbDTO.getComment());
        teacherDTO.setEmployedDate(teacherDbDTO.getEmployedDate());
        teacherDTO.setFamilySituation(teacherDbDTO.getFamilySituation());
        teacherDTO.setSubstitute(teacherDbDTO.getSubstitute());
        teacherDTO.setSubstitutedTeachers(dbDtosToDtos(teacherDbDTO.getSubstitutedTeachers()));
        teacherDTO.setAbsences(absenceMapper.dbVosToVos(teacherDbDTO.getAbsences()));
        teacherDTO.setTaughtSubjects(subjectMapper.dbDtosToDtos(teacherDbDTO.getTaughtSubjects()));
        teacherDTO.setProCellPhone(teacherDbDTO.getProCellPhone());
        teacherDTO.setProPhone(teacherDbDTO.getProPhone());
        teacherDTO.setProEmail(teacherDbDTO.getProEmail());
        teacherDTO.setInfirmaryStatistics(infirmaryStatisticsMapper.dbVoToVo(teacherDbDTO.getInfirmaryStatistics()));
        teacherDTO.setMedicalInfos(medicalInfosMapper.dbVoToVo(teacherDbDTO.getMedicalInfos()));
        teacherDTO.setExitDate(teacherDbDTO.getExitDate());
        teacherDTO.setExitReason(teacherDbDTO.getExitReason());
        teacherDTO.setGrades(gradeMapper.dbDtosToDtos(teacherDbDTO.getGrades()));
        return teacherDTO;

    }

    public List<Teacher> dbDtosToDomains(List<TeacherDbDTO> teachers) {
        return teachers.stream().map(this::dbDtoToDomain).collect(Collectors.toList());
    }

    protected Teacher dbDtoToDomain(TeacherDbDTO teacherDbDTO) {
        Teacher teacher = new Teacher();
        teacher.setComment(teacherDbDTO.getComment());
        teacher.setEmployedDate(teacherDbDTO.getEmployedDate());
        teacher.setFamilySituation(teacherDbDTO.getFamilySituation());
        teacher.setSubstitute(teacherDbDTO.getSubstitute());
        teacher.setSubstitutedTeachers(dbDtosToDomains(teacherDbDTO.getSubstitutedTeachers()));
        teacher.setAbsences(absenceMapper.dbVosToDomains(teacherDbDTO.getAbsences()));
        teacher.setTaughtSubjects(subjectMapper.dbDtosToDomains(teacherDbDTO.getTaughtSubjects()));
        teacher.setProCellPhone(teacherDbDTO.getProCellPhone());
        teacher.setProPhone(teacherDbDTO.getProPhone());
        teacher.setProEmail(teacherDbDTO.getProEmail());
        teacher.setInfirmaryStatistics(infirmaryStatisticsMapper.dbVoToDomain(teacherDbDTO.getInfirmaryStatistics()));
        teacher.setMedicalInfos(medicalInfosMapper.dbVoToDomain(teacherDbDTO.getMedicalInfos()));
        teacher.setExitDate(teacherDbDTO.getExitDate());
        teacher.setExitReason(teacherDbDTO.getExitReason());
        teacher.setGrades(gradeMapper.dbDtosToDomains(teacherDbDTO.getGrades()));
        teacher.setId(teacherDbDTO.getId());
        teacher.setLogin(teacherDbDTO.getLogin());
        teacher.setClientId(objectIdMapper.map(teacherDbDTO.getClientId()));
        teacher.setGender(teacherDbDTO.getGender());
        teacher.setFirstName(teacherDbDTO.getFirstName());
        teacher.setLastName(teacherDbDTO.getLastName());
        teacher.setCellPhone(teacherDbDTO.getCellPhone());
        teacher.setHomePhone(teacherDbDTO.getHomePhone());
        teacher.setNationality(teacherDbDTO.getNationality());
        teacher.setEmail(teacherDbDTO.getEmail());
        teacher.setLangKey(teacherDbDTO.getLangKey());
        teacher.setImageUrl(teacherDbDTO.getImageUrl());
        teacher.setActivationKey(teacherDbDTO.getActivationKey());
        teacher.setResetKey(teacherDbDTO.getResetKey());
        teacher.setResetDate(teacherDbDTO.getResetDate());
        teacher.setAuthorities(authorityMapper.dbDtosToDomains(teacherDbDTO.getAuthorities()));
        teacher.setAddress(addressMapper.dbVoToDomain(teacherDbDTO.getAddress()));
        teacher.setBirthDate(teacherDbDTO.getBirthDate());
        teacher.setCreatedBy(teacherDbDTO.getCreatedBy());
        return teacher;

    }

    public List<TeacherDbDTO> dtosToDbDtos(List<TeacherDTO> teachers) {
        return teachers.stream().map(this::dtoToDbDto).collect(Collectors.toList());
    }

    public TeacherDbDTO dtoToDbDto(TeacherDTO teacherDTO) {
        return null;
    }

    public List<Teacher> dtosToDomains(List<TeacherDTO> teachers) {
        return teachers.stream().map(this::dtoToDomain).collect(Collectors.toList());
    }

    public Teacher dtoToDomain(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        teacher.setComment(teacherDTO.getComment());
        teacher.setEmployedDate(teacherDTO.getEmployedDate());
        teacher.setFamilySituation(teacherDTO.getFamilySituation());
        teacher.setSubstitute(teacherDTO.getSubstitute());
        teacher.setSubstitutedTeachers(dtosToDomains(teacherDTO.getSubstitutedTeachers()));
        teacher.setAbsences(absenceMapper.vosToDomains(teacherDTO.getAbsences()));
        teacher.setTaughtSubjects(subjectMapper.dtosToDomains(teacherDTO.getTaughtSubjects()));
        teacher.setProCellPhone(teacherDTO.getProCellPhone());
        teacher.setProPhone(teacherDTO.getProPhone());
        teacher.setProEmail(teacherDTO.getProEmail());
        teacher.setInfirmaryStatistics(infirmaryStatisticsMapper.voToDomain(teacherDTO.getInfirmaryStatistics()));
        teacher.setMedicalInfos(medicalInfosMapper.voToDomain(teacherDTO.getMedicalInfos()));
        teacher.setExitDate(teacherDTO.getExitDate());
        teacher.setExitReason(teacherDTO.getExitReason());
        teacher.setGrades(gradeMapper.dtosToDomains(teacherDTO.getGrades()));
        teacher.setId(teacherDTO.getId());
        teacher.setLogin(teacherDTO.getLogin());
        teacher.setClientId(teacherDTO.getClientId());
        teacher.setGender(teacherDTO.getGender());
        teacher.setFirstName(teacherDTO.getFirstName());
        teacher.setLastName(teacherDTO.getLastName());
        teacher.setCellPhone(teacherDTO.getCellPhone());
        teacher.setHomePhone(teacherDTO.getHomePhone());
        teacher.setNationality(teacherDTO.getNationality());
        teacher.setEmail(teacherDTO.getEmail());
        teacher.setLangKey(teacherDTO.getLangKey());
        teacher.setImageUrl(teacherDTO.getImageUrl());
        teacher.setAuthorities(authorityMapper.dtosToDomains(teacherDTO.getAuthorities()));
        teacher.setAddress(addressMapper.voToDomain(teacherDTO.getAddress()));
        teacher.setBirthDate(teacherDTO.getBirthDate());
        teacher.setCreatedBy(teacherDTO.getCreatedBy());
        return teacher;
    }

    public List<TeacherDbDTO> domainsToDbDtos(List<Teacher> teachers) {
        return teachers.stream().map(this::domainToDbDto).collect(Collectors.toList());
    }

    public List<TeacherDTO> domainsToDtos(List<Teacher> teachers) {
        return teachers.stream().map(this::domainToDto).collect(Collectors.toList());
    }

    public TeacherDTO domainToDto(Teacher teacher) {
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setId(teacher.getId());
        teacherDTO.setClientId(teacher.getClientId());
        teacherDTO.setLogin(teacher.getLogin());
        teacherDTO.setGender(teacher.getGender());
        teacherDTO.setFirstName(teacher.getFirstName());
        teacherDTO.setLastName(teacher.getLastName());
        teacherDTO.setCellPhone(teacher.getCellPhone());
        teacherDTO.setHomePhone(teacher.getHomePhone());
        teacherDTO.setNationality(teacher.getNationality());
        teacherDTO.setEmail(teacher.getEmail());
        teacherDTO.setImageUrl(teacher.getImageUrl());
        teacherDTO.setLangKey(teacher.getLangKey());
        teacherDTO.setCreatedBy(teacher.getCreatedBy());
        teacherDTO.setAuthorities(authorityMapper.domainsToDtos(teacher.getAuthorities()));
        teacherDTO.setAddress(addressMapper.domainToVo(teacher.getAddress()));
        teacherDTO.setBirthDate(teacher.getBirthDate());

        teacherDTO.setComment(teacher.getComment());
        teacherDTO.setEmployedDate(teacher.getEmployedDate());
        teacherDTO.setFamilySituation(teacher.getFamilySituation());
        teacherDTO.setSubstitute(teacher.getSubstitute());
        teacherDTO.setSubstitutedTeachers(domainsToDtos(teacher.getSubstitutedTeachers()));
        teacherDTO.setAbsences(absenceMapper.domainsToVos(teacher.getAbsences()));
        teacherDTO.setTaughtSubjects(subjectMapper.domainsToDtos(teacher.getTaughtSubjects()));
        teacherDTO.setProCellPhone(teacher.getProCellPhone());
        teacherDTO.setProPhone(teacher.getProPhone());
        teacherDTO.setProEmail(teacher.getProEmail());
        teacherDTO.setInfirmaryStatistics(infirmaryStatisticsMapper.domainToVo(teacher.getInfirmaryStatistics()));
        teacherDTO.setMedicalInfos(medicalInfosMapper.domainToVo(teacher.getMedicalInfos()));
        teacherDTO.setExitDate(teacher.getExitDate());
        teacherDTO.setExitReason(teacher.getExitReason());
        teacherDTO.setGrades(gradeMapper.domainsToDtos(teacher.getGrades()));
        return teacherDTO;
    }
}
