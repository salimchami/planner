package io.edukativ.myskoolin.infrastructure.timetabling;

import io.edukativ.myskoolin.domain.timetabling.Lesson;
import io.edukativ.myskoolin.infrastructure.app.mapper.AuthorityMapper;
import io.edukativ.myskoolin.infrastructure.common.mapper.ObjectIdMapper;
import io.edukativ.myskoolin.infrastructure.schoolclasses.SchoolClassMapper;
import io.edukativ.myskoolin.infrastructure.schoolrooms.SchoolRoomMapper;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectMapper;
import io.edukativ.myskoolin.infrastructure.teachers.TeacherMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        ObjectIdMapper.class,
        SchoolRoomMapper.class,
        TeacherMapper.class,
        SubjectMapper.class,
        TimeSlotMapper.class,
        SchoolClassMapper.class,
        AuthorityMapper.class,
})
public interface LessonMapper {

    Lesson dbVoToDomain(LessonDbVO lesson);
    LessonVO dbVoToVo(LessonDbVO lesson);

    LessonDbVO domainToDbVo(Lesson lesson);
    LessonVO domainToVo(Lesson lesson);

    LessonDbVO voToDbVo(LessonVO lesson);
    Lesson voToDomain(LessonVO lesson);


}
