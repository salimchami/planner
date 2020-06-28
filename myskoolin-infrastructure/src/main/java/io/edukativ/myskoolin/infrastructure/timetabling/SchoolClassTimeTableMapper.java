package io.edukativ.myskoolin.infrastructure.timetabling;

import io.edukativ.myskoolin.domain.timetabling.SchoolClassTimeTable;
import io.edukativ.myskoolin.infrastructure.common.mapper.ObjectIdMapper;
import io.edukativ.myskoolin.infrastructure.schoolclasses.SchoolClassMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        LessonMapper.class,
        ObjectIdMapper.class,
        SchoolClassMapper.class,
})
public interface SchoolClassTimeTableMapper {

    SchoolClassTimeTableVO dbVoToVo(SchoolClassTimeTableDbDTO param);

    SchoolClassTimeTable dbVoToDomain(SchoolClassTimeTableDbDTO param);

    SchoolClassTimeTableDbDTO voToDbVo(SchoolClassTimeTableVO param);

    SchoolClassTimeTable voToDomain(SchoolClassTimeTableVO param);

    SchoolClassTimeTableDbDTO domainToDbVo(SchoolClassTimeTable param);

    SchoolClassTimeTableVO domainToVo(SchoolClassTimeTable param);

}
