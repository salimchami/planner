package io.edukativ.myskoolin.infrastructure.timetabling;

import io.edukativ.myskoolin.infrastructure.common.vo.TimeDbVO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TimeMapper {

    TimeDTO dbDtoToDto(TimeDbVO time);

    TimeDbVO dtoToDbDto(TimeDTO time);

}
