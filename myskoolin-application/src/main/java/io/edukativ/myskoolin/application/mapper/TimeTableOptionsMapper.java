package io.edukativ.myskoolin.application.mapper;

import io.edukativ.myskoolin.application.dto.TimeTableOptionsDTO;
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
