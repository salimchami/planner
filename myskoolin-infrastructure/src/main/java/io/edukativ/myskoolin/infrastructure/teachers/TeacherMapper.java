package io.edukativ.myskoolin.infrastructure.teachers;

import io.edukativ.myskoolin.domain.schoolrooms.EnumSchoolRoomsTypes;
import io.edukativ.myskoolin.domain.teachers.Teacher;
import io.edukativ.myskoolin.infrastructure.absences.AbsenceMapper;
import io.edukativ.myskoolin.infrastructure.app.mapper.AuthorityMapper;
import io.edukativ.myskoolin.infrastructure.app.mapper.SchoolingUserMapper;
import io.edukativ.myskoolin.infrastructure.app.mapper.UserMapper;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumSchoolRoomsTypesDb;
import io.edukativ.myskoolin.infrastructure.common.mapper.AddressMapper;
import io.edukativ.myskoolin.infrastructure.common.mapper.ObjectIdMapper;
import io.edukativ.myskoolin.infrastructure.grades.GradeMapper;
import io.edukativ.myskoolin.infrastructure.medical.InfirmaryStatisticsMapper;
import io.edukativ.myskoolin.infrastructure.medical.MedicalInfosMapper;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectMapper;
import io.edukativ.myskoolin.infrastructure.timetabling.LessonMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {
        SchoolingUserMapper.class,
        AbsenceMapper.class,
        SubjectMapper.class,
        LessonMapper.class,
        InfirmaryStatisticsMapper.class,
        MedicalInfosMapper.class,
        GradeMapper.class,
        ObjectIdMapper.class,
        AuthorityMapper.class,
        AddressMapper.class,
}, config = UserMapper.class)
public interface TeacherMapper {

    List<TeacherDTO> dbDtosToDtos(List<TeacherDbDTO> teachers);

    Teacher dtoToDomain(TeacherDTO teacher);

    TeacherDTO domainToDto(Teacher teacher);

    List<TeacherDbDTO> domainsToDbDtos(List<Teacher> substitutedTeachers);

    Teacher dbDtoToDomain(TeacherDbDTO teacher);

    @Named("dbToVo")
    default EnumSchoolRoomsTypes dbToVo(EnumSchoolRoomsTypesDb type) {
        return EnumSchoolRoomsTypes.valueOf(type.name());
    }

    List<TeacherDTO> domainsToDtos(List<Teacher> teachers);

    List<Teacher> dbDtosToDomains(List<TeacherDbDTO> teacherDbDTOS);

    TeacherDTO dbDtoToDto(TeacherDbDTO teacher);

    List<Teacher> dtosToDomains(List<TeacherDTO> teachers);

    List<TeacherDbDTO> dtosToDbDtos(List<TeacherDTO> teachers);
}
