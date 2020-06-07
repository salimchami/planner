package io.edukativ.myskoolin.infrastructure.common.mapper;

import io.edukativ.myskoolin.infrastructure.common.dto.EmailDTO;
import io.edukativ.myskoolin.infrastructure.common.vo.EmailDbVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailMapper {

    EmailDTO dbDtoToDto(EmailDbVO email);

    EmailDbVO dtoToDbDto(EmailDTO email);

}
