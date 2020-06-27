package io.edukativ.myskoolin.infrastructure.timetabling;

import io.edukativ.myskoolin.infrastructure.common.enums.EnumDays;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumPartsOfDay;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PreferredPartsOfDaysMapper {

    PreferredPartsOfDaysVO map(PreferredPartsOfDaysDbVO preferredPartsOfDays);

    EnumDays map(EnumDays days);
    EnumPartsOfDay map(EnumPartsOfDay days);
    List<EnumPartsOfDay> map(List<EnumPartsOfDay> days);
}
