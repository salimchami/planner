package io.edukativ.myskoolin.infrastructure.schooling;

import io.edukativ.myskoolin.infrastructure.schooling.vo.TimeTableOptionsDbVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        TimeMapper.class,
        TimeSlotMapper.class
})
public interface TimeTableOptionsMapper {

    TimeTableOptionsVO dbDtoToDto(TimeTableOptionsDbVO timeTableOptions);

    TimeTableOptionsDbVO dtoToDbDto(TimeTableOptionsVO timeTableOptions);

}
