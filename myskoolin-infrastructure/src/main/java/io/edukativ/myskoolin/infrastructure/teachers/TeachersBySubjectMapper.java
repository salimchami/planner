package io.edukativ.myskoolin.infrastructure.teachers;

import io.edukativ.myskoolin.domain.teachers.TeachersBySubject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeachersBySubjectMapper {

    TeachersBySubjectVO dbVoToVo(TeachersBySubjectDbVO teachersBySubject);
    TeachersBySubject dbVoToDomain(TeachersBySubjectDbVO teachersBySubject);

    TeachersBySubjectDbVO voToDbVo(TeachersBySubjectVO teachersBySubject);
    TeachersBySubject voToDomain(TeachersBySubjectVO teachersBySubject);

    TeachersBySubjectDbVO domainToDbVo(TeachersBySubject teachersBySubject);
    TeachersBySubjectVO domainToVo(TeachersBySubject teachersBySubject);

}
