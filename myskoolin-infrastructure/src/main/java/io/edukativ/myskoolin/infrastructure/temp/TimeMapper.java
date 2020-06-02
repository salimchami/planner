package io.edukativ.myskoolin.infrastructure.temp;

import io.edukativ.myskoolin.infrastructure.temp.TimeDTO;
import io.edukativ.myskoolin.infrastructure.common.vo.TimeDbVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TimeMapper {

    TimeDTO dbDtoToDto(TimeDbVO time);

    TimeDbVO dtoToDbDto(TimeDTO time);

}
