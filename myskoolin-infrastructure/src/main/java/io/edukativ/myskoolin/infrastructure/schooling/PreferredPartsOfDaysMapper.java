package io.edukativ.myskoolin.infrastructure.schooling;

import io.edukativ.myskoolin.infrastructure.schooling.vo.PreferredPartsOfDaysDbVO;
import io.edukativ.myskoolin.infrastructure.schooling.vo.PreferredPartsOfDaysVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PreferredPartsOfDaysMapper {

    PreferredPartsOfDaysVO dbVoToVo(PreferredPartsOfDaysDbVO preferredPartsOfDays);
}
