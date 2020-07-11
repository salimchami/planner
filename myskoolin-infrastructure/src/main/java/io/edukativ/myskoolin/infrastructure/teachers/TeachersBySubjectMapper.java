package io.edukativ.myskoolin.infrastructure.teachers;

import io.edukativ.myskoolin.domain.teachers.TeachersBySubject;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SubjectMapper.class, TeacherMapper.class})
public interface TeachersBySubjectMapper {

    TeachersBySubjectVO dbVoToVo(TeachersBySubjectDbVO teachersBySubject);

    TeachersBySubject dbVoToDomain(TeachersBySubjectDbVO teachersBySubject);

    List<TeachersBySubject> dbVoToDomain(List<TeachersBySubjectDbVO> teachersBySubject);

    TeachersBySubjectDbVO voToDbVo(TeachersBySubjectVO teachersBySubject);

    TeachersBySubject voToDomain(TeachersBySubjectVO teachersBySubject);

    TeachersBySubjectDbVO domainToDbVo(TeachersBySubject teachersBySubject);

    TeachersBySubjectVO domainToVo(TeachersBySubject teachersBySubject);


}
