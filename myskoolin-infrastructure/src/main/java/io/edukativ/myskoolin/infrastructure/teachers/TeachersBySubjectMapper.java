package io.edukativ.myskoolin.infrastructure.teachers;

import io.edukativ.myskoolin.domain.teachers.TeachersBySubject;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectMapper;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class TeachersBySubjectMapper {

    @Autowired
    private SubjectMapper subjectMapper;
    @Autowired
    private TeacherMapperImplemented teacherMapper;


    public TeachersBySubjectVO dbVoToVo(TeachersBySubjectDbVO teachersBySubject) {
        return new TeachersBySubjectVO(
                subjectMapper.dbDtoToDto(teachersBySubject.getSubject()),
                teacherMapper.dbDtosToDtos(teachersBySubject.getTeachers())
        );
    }

    public TeachersBySubject dbVoToDomain(TeachersBySubjectDbVO teachersBySubject) {
        return new TeachersBySubject(subjectMapper.dbDtoToDomain(teachersBySubject.getSubject()),
                teacherMapper.dbDtosToDomains(teachersBySubject.getTeachers()));
    }

    public List<TeachersBySubject> dbVoToDomain(List<TeachersBySubjectDbVO> teachers) {
        return teachers.stream()
                .map(teachersBySubject -> new TeachersBySubject(
                        subjectMapper.dbDtoToDomain(teachersBySubject.getSubject()),
                        teacherMapper.dbDtosToDomains(teachersBySubject.getTeachers())
                )).collect(Collectors.toList());
    }

    public TeachersBySubjectDbVO voToDbVo(TeachersBySubjectVO teachersBySubject) {
        return new TeachersBySubjectDbVO(
                subjectMapper.dtoToDbDto(teachersBySubject.getSubject()),
                teacherMapper.dtosToDbDtos(teachersBySubject.getTeachers())
        );
    }

    public TeachersBySubject voToDomain(TeachersBySubjectVO teachersBySubject) {
        return new TeachersBySubject(
                subjectMapper.dtoToDomain(teachersBySubject.getSubject()),
                teacherMapper.dtosToDomains(teachersBySubject.getTeachers())
        );
    }

    public TeachersBySubjectDbVO domainToDbVo(TeachersBySubject teachersBySubject) {
        return new TeachersBySubjectDbVO(
                subjectMapper.domainToDbDto(teachersBySubject.getSubject()),
                teacherMapper.domainsToDbDtos(teachersBySubject.getTeachers())
        );
    }

    public TeachersBySubjectVO domainToVo(TeachersBySubject teachersBySubject) {
        return new TeachersBySubjectVO(
                subjectMapper.domainToDto(teachersBySubject.getSubject()),
                teacherMapper.domainsToDtos(teachersBySubject.getTeachers())
        );
    }


}
