package io.edukativ.myskoolin.infrastructure.common.mapper;

import io.edukativ.myskoolin.infrastructure.common.dto.PhoneDTO;
import io.edukativ.myskoolin.infrastructure.common.vo.PhoneDbVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhoneMapper {

    PhoneDTO map(PhoneDbVO phone);

    PhoneDbVO map(PhoneDTO phone);

}
