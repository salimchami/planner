package io.edukativ.myskoolin.infrastructure.timetabling;

import io.edukativ.myskoolin.domain.commons.vo.EnumPartsOfDay;
import io.edukativ.myskoolin.domain.timetabling.Time;
import io.edukativ.myskoolin.infrastructure.common.vo.TimeDbVO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TimeMapper {

    default TimeVO dbVoToVo(TimeDbVO time) {
        return new TimeVO(time.getHour(), time.getMinutes(), time.getSeconds(), time.getPartOfDay().getCode());
    }

    Time dbVoToDomain(TimeDbVO time);

    TimeDbVO voToDbVo(TimeVO time);

    default Time voToDomain(TimeVO time) {
        return new Time(time.getHour(), time.getMinutes(), time.getSeconds(), EnumPartsOfDay.partOfDayByCode(time.getPartOfDay()));
    }

    TimeDbVO domainToDbVo(Time time);

    default TimeVO domainToVo(Time time) {
        return new TimeVO(time.getHour(), time.getMinutes(), time.getSeconds(), time.getPartOfDay().getCode());
    }
}
