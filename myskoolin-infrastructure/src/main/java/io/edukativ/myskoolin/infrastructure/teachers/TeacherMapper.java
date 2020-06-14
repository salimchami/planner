package io.edukativ.myskoolin.infrastructure.teachers;

import io.edukativ.myskoolin.domain.vo.EnumSchoolRoomsTypes;
import io.edukativ.myskoolin.domain.vo.Teacher;
import io.edukativ.myskoolin.infrastructure.app.mapper.AuthorityMapper;
import io.edukativ.myskoolin.infrastructure.app.mapper.SchoolingUserMapper;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumSchoolRoomsTypesDb;
import io.edukativ.myskoolin.infrastructure.common.mapper.AddressMapper;
import io.edukativ.myskoolin.infrastructure.common.mapper.ObjectIdMapper;
import io.edukativ.myskoolin.infrastructure.grades.GradeMapper;
import io.edukativ.myskoolin.infrastructure.medical.MedicalInfosMapper;
import io.edukativ.myskoolin.infrastructure.schooling.AbsenceMapper;
import io.edukativ.myskoolin.infrastructure.schooling.InfirmaryStatisticsMapper;
import io.edukativ.myskoolin.infrastructure.schooling.SchoolClassTimeSlotMapper;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        SchoolingUserMapper.class,
        AbsenceMapper.class,
        SubjectMapper.class,
        SchoolClassTimeSlotMapper.class,
        InfirmaryStatisticsMapper.class,
        MedicalInfosMapper.class,
        GradeMapper.class,
        ObjectIdMapper.class,
        AuthorityMapper.class,
        AddressMapper.class,
})
public interface TeacherMapper {

    List<TeacherDTO> dbDtosToDtos(List<TeacherDbDTO> teachers);

    Teacher dtoToDomain(TeacherDTO teacher);

    TeacherDTO domainToDto(Teacher savedTeacher);

    TeacherDbDTO domainToDbDto(Teacher teacher);

    Teacher dbDtoToDomain(TeacherDbDTO savedTeacher);

    default EnumSchoolRoomsTypes dbToVo(EnumSchoolRoomsTypesDb type) {
        return EnumSchoolRoomsTypes.valueOf(type.name());
    }

    List<TeacherDTO> domainsToDtos(List<Teacher> teachers);

    List<Teacher> dbDtosToDomains(List<TeacherDbDTO> teacherDbDTOS);

    TeacherDTO dbDtoToDto(TeacherDbDTO teacher);
}
