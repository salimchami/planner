package io.edukativ.myskoolin.infrastructure.timetabling;

import io.edukativ.myskoolin.domain.timetabling.SchoolClassTimeTable;
import io.edukativ.myskoolin.infrastructure.schoolclasses.SchoolClassTimeSlotMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        SchoolClassTimeSlotMapper.class
})
public interface SchoolClassTimeTableMapper {

    SchoolClassTimeTableVO dbVoToVo(SchoolClassTimeTableDbVO param);

    SchoolClassTimeTable dbVoToDomain(SchoolClassTimeTableDbVO param);

    SchoolClassTimeTableDbVO voToDbVo(SchoolClassTimeTableVO param);

    SchoolClassTimeTable voToDomain(SchoolClassTimeTableVO param);

    SchoolClassTimeTableDbVO domainToDbVo(SchoolClassTimeTable param);

    SchoolClassTimeTableVO domainToVo(SchoolClassTimeTable param);

}
