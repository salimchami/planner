package io.edukativ.myskoolin.infrastructure.teachers;

import io.edukativ.myskoolin.domain.teachers.TeachersBySubject;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TeachersBySubjectMapper {

    default TeachersBySubjectVO dbVoToVo(TeachersBySubjectDbVO teachersBySubject) {
        SubjectMapper subjectMapper = Mappers.getMapper(SubjectMapper.class);
        TeacherMapper teacherMapper = Mappers.getMapper(TeacherMapper.class);
        return new TeachersBySubjectVO(
                subjectMapper.dbDtoToDto(teachersBySubject.getSubject()),
                teacherMapper.dbDtosToDtos(teachersBySubject.getTeachers())
        );
    }

    default TeachersBySubject dbVoToDomain(TeachersBySubjectDbVO teachersBySubject) {
        SubjectMapper subjectMapper = Mappers.getMapper(SubjectMapper.class);
        TeacherMapper teacherMapper = Mappers.getMapper(TeacherMapper.class);
        return new TeachersBySubject(subjectMapper.dbDtoToDomain(teachersBySubject.getSubject()),
                teacherMapper.dbDtosToDomains(teachersBySubject.getTeachers()));
    }

    default List<TeachersBySubject> dbVoToDomain(List<TeachersBySubjectDbVO> teachers) {
        SubjectMapper subjectMapper = Mappers.getMapper(SubjectMapper.class);
        TeacherMapper teacherMapper = Mappers.getMapper(TeacherMapper.class);
        return teachers.stream()
                .map(teachersBySubject -> new TeachersBySubject(
                        subjectMapper.dbDtoToDomain(teachersBySubject.getSubject()),
                        teacherMapper.dbDtosToDomains(teachersBySubject.getTeachers())
                )).collect(Collectors.toList());
    }

    default TeachersBySubjectDbVO voToDbVo(TeachersBySubjectVO teachersBySubject) {
        SubjectMapper subjectMapper = Mappers.getMapper(SubjectMapper.class);
        TeacherMapper teacherMapper = Mappers.getMapper(TeacherMapper.class);
        return new TeachersBySubjectDbVO(
                subjectMapper.dtoToDbDto(teachersBySubject.getSubject()),
                teacherMapper.dtosToDbDtos(teachersBySubject.getTeachers())
        );
    }

    default TeachersBySubject voToDomain(TeachersBySubjectVO teachersBySubject) {
        SubjectMapper subjectMapper = Mappers.getMapper(SubjectMapper.class);
        TeacherMapper teacherMapper = Mappers.getMapper(TeacherMapper.class);
        return new TeachersBySubject(
                subjectMapper.dtoToDomain(teachersBySubject.getSubject()),
                teacherMapper.dtosToDomains(teachersBySubject.getTeachers())
        );
    }

    default TeachersBySubjectDbVO domainToDbVo(TeachersBySubject teachersBySubject) {
        SubjectMapper subjectMapper = Mappers.getMapper(SubjectMapper.class);
        TeacherMapper teacherMapper = Mappers.getMapper(TeacherMapper.class);
        return new TeachersBySubjectDbVO(
                subjectMapper.domainToDbDto(teachersBySubject.getSubject()),
                teacherMapper.domainsToDbDtos(teachersBySubject.getTeachers())
        );
    }

    default TeachersBySubjectVO domainToVo(TeachersBySubject teachersBySubject) {
        SubjectMapper subjectMapper = Mappers.getMapper(SubjectMapper.class);
        TeacherMapper teacherMapper = Mappers.getMapper(TeacherMapper.class);
        return new TeachersBySubjectVO(
                subjectMapper.domainToDto(teachersBySubject.getSubject()),
                teacherMapper.domainsToDtos(teachersBySubject.getTeachers())
        );
    }


}
