package io.edukativ.myskoolin.application.mapper;

import io.edukativ.myskoolin.application.dto.TimeSlotDTO;
import io.edukativ.myskoolin.infrastructure.schooling.vo.TimeSlotDbVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        TimeMapper.class
})
public interface TimeSlotMapper {

    TimeSlotDTO dbDtoToDto(TimeSlotDbVO timeSlot);

    TimeSlotDbVO dtoToDbDto(TimeSlotDTO timeSlot);

}
