package io.edukativ.myskoolin.infrastructure.timetabling;

import io.edukativ.myskoolin.domain.timetabling.SchoolClassTimeTable;
import io.edukativ.myskoolin.infrastructure.common.mapper.ObjectIdMapper;
import io.edukativ.myskoolin.infrastructure.schoolclasses.SchoolClassMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        LessonMapper.class,
        ObjectIdMapper.class,
        TimeSlotMapper.class,
        SchoolClassMapper.class,
})
public interface SchoolClassTimeTableMapper {

    SchoolClassTimeTableDTO dbVoToVo(SchoolClassTimeTableDbDTO param);

    @Mapping(target = "score", ignore = true)
    SchoolClassTimeTable dbVoToDomain(SchoolClassTimeTableDbDTO param);

    SchoolClassTimeTableDbDTO voToDbVo(SchoolClassTimeTableDTO param);

    SchoolClassTimeTable voToDomain(SchoolClassTimeTableDTO param);

    SchoolClassTimeTableDbDTO domainToDbVo(SchoolClassTimeTable param);

    @Mapping(source = "lessons", target = "staticTimeTable")
    SchoolClassTimeTableDTO domainToVo(SchoolClassTimeTable param);

    List<SchoolClassTimeTableDTO> domainsToVos(List<SchoolClassTimeTable> timeTables);
}
