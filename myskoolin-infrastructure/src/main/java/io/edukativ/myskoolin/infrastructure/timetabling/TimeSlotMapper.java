package io.edukativ.myskoolin.infrastructure.timetabling;

import io.edukativ.myskoolin.domain.timetabling.TimeSlot;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TimeSlotMapper {

    TimeSlotVO dbVoToVo(TimeSlotDbVO timeSlot);
    TimeSlot dbVoToDomain(TimeSlotDbVO timeSlot);

    TimeSlotDbVO voToDbVo(TimeSlotVO timeSlot);
    TimeSlot voToDomain(TimeSlotVO timeSlot);

    TimeSlotVO domainToVo(TimeSlot timeSlot);
    TimeSlotDbVO domainToDbVo(TimeSlot timeSlot);



}
