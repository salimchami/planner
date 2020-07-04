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
        HardSoftScoreMapper.class
})
public interface SchoolClassTimeTableMapper {

    @Mapping(source = "lessons", target = "staticTimeTable")
    SchoolClassTimeTableDTO dbVoToVo(SchoolClassTimeTableDbDTO param);

    @Mapping(target = "score", ignore = true)
    @Mapping(target = "schoolClasses", ignore = true)
    @Mapping(target = "teachers", ignore = true)
    @Mapping(target = "subjects", ignore = true)
    @Mapping(target = "schoolRooms", ignore = true)
    @Mapping(target = "timeSlots", ignore = true)
    SchoolClassTimeTable dbVoToDomain(SchoolClassTimeTableDbDTO param);

    @Mapping(target = "lessons", source = "staticTimeTable")
    SchoolClassTimeTableDbDTO voToDbVo(SchoolClassTimeTableDTO param);

    @Mapping(target = "score", ignore = true)
    @Mapping(target = "schoolClasses", ignore = true)
    @Mapping(target = "teachers", ignore = true)
    @Mapping(target = "subjects", ignore = true)
    @Mapping(target = "schoolRooms", ignore = true)
    @Mapping(target = "timeSlots", ignore = true)
    SchoolClassTimeTable voToDomain(SchoolClassTimeTableDTO param);

    SchoolClassTimeTableDbDTO domainToDbVo(SchoolClassTimeTable param);

    @Mapping(source = "lessons", target = "staticTimeTable")
    SchoolClassTimeTableDTO domainToVo(SchoolClassTimeTable param);

    List<SchoolClassTimeTableDTO> domainsToVos(List<SchoolClassTimeTable> timeTables);
}
