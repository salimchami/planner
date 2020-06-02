package io.edukativ.myskoolin.infrastructure.temp;

import io.edukativ.myskoolin.infrastructure.schooling.vo.TimeTableOptionsDbVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        TimeMapper.class,
        TimeSlotMapper.class
})
public interface TimeTableOptionsMapper {

    TimeTableOptionsDTO dbDtoToDto(TimeTableOptionsDbVO timeTableOptions);

    TimeTableOptionsDbVO dtoToDbDto(TimeTableOptionsDTO timeTableOptions);

}
