package io.edukativ.myskoolin.infrastructure.timetabling;

import io.edukativ.myskoolin.domain.timetabling.Time;
import io.edukativ.myskoolin.infrastructure.common.vo.TimeDbVO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TimeMapper {

    TimeVO dbVoToVo(TimeDbVO time);
    Time dbVoToDomain(TimeDbVO time);

    TimeDbVO voToDbVo(TimeVO time);
    Time voToDomain(TimeVO time);

    TimeDbVO domainToDbVo(Time time);
    TimeVO domainToVo(Time time);
}
