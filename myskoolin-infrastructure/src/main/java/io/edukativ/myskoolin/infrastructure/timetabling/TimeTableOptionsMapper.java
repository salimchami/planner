package io.edukativ.myskoolin.infrastructure.timetabling;

import io.edukativ.myskoolin.domain.timetabling.TimeTableOptions;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {
        TimeSlotMapper.class
})
public interface TimeTableOptionsMapper {

    TimeTableOptionsVO dbVoToVo(TimeTableOptionsDbVO timeTableOptions);
    TimeTableOptions dbVoToDomain(TimeTableOptionsDbVO timeTableOptions);

    TimeTableOptionsDbVO voToDbDto(TimeTableOptionsVO timeTableOptions);
    TimeTableOptions voToDomain(TimeTableOptionsVO timeTableOptions);

    TimeTableOptionsVO domainToVo(TimeTableOptions timeTableOptions);
    TimeTableOptionsDbVO domainToDbVo(TimeTableOptions timeTableOptions);

}
