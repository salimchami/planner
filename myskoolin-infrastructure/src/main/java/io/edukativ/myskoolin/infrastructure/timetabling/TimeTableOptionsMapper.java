package io.edukativ.myskoolin.infrastructure.timetabling;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {
        TimeMapper.class,
        TimeSlotMapper.class
})
public interface TimeTableOptionsMapper {

    TimeTableOptionsVO dbDtoToDto(TimeTableOptionsDbVO timeTableOptions);

    TimeTableOptionsDbVO dtoToDbDto(TimeTableOptionsVO timeTableOptions);

}
