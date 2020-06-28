package io.edukativ.myskoolin.infrastructure.students;

import io.edukativ.myskoolin.domain.schoolrooms.EnumSchoolRoomsTypes;
import io.edukativ.myskoolin.domain.students.Student;
import io.edukativ.myskoolin.infrastructure.app.mapper.AuthorityMapper;
import io.edukativ.myskoolin.infrastructure.app.mapper.SchoolingUserMapper;
import io.edukativ.myskoolin.infrastructure.app.mapper.UserMapper;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumSchoolRoomsTypesDb;
import io.edukativ.myskoolin.infrastructure.common.mapper.AddressMapper;
import io.edukativ.myskoolin.infrastructure.common.mapper.ObjectIdMapper;
import io.edukativ.myskoolin.infrastructure.grades.GradeMapper;
import io.edukativ.myskoolin.infrastructure.medical.MedicalInfosMapper;
import io.edukativ.myskoolin.infrastructure.absences.AbsenceMapper;
import io.edukativ.myskoolin.infrastructure.medical.InfirmaryStatisticsMapper;
import io.edukativ.myskoolin.infrastructure.schoolclasses.SchoolClassTimeSlotMapper;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectMapper;
import io.edukativ.myskoolin.infrastructure.timetabling.SchoolClassTimeTableMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {
        SchoolingUserMapper.class,
        AbsenceMapper.class,
        SubjectMapper.class,
        SchoolClassTimeSlotMapper.class,
        SchoolClassTimeTableMapper.class,
        InfirmaryStatisticsMapper.class,
        MedicalInfosMapper.class,
        GradeMapper.class,
        ObjectIdMapper.class,
        AuthorityMapper.class,
        AddressMapper.class,
}, config = UserMapper.class)
public interface StudentMapper {

    List<StudentDTO> dbDtosToDtos(List<StudentDbDTO> students);

    Student dtoToDomain(StudentDTO student);

    StudentDTO domainToDto(Student student);

    List<StudentDbDTO> domainsToDbDtos(List<Student> substitutedStudents);

    Student dbDtoToDomain(StudentDbDTO student);

    @Named("dbToVo")
    default EnumSchoolRoomsTypes dbToVo(EnumSchoolRoomsTypesDb type) {
        return EnumSchoolRoomsTypes.valueOf(type.name());
    }

    List<StudentDTO> domainsToDtos(List<Student> students);

    List<Student> dbDtosToDomains(List<StudentDbDTO> studentDbDTOS);

    StudentDTO dbDtoToDto(StudentDbDTO student);
}
