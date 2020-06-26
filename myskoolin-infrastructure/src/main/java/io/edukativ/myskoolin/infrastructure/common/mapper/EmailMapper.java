package io.edukativ.myskoolin.infrastructure.common.mapper;

import io.edukativ.myskoolin.infrastructure.common.dto.EmailDTO;
import io.edukativ.myskoolin.infrastructure.common.vo.EmailDbVO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface EmailMapper {

    EmailDTO map(EmailDbVO email);

    EmailDbVO map(EmailDTO email);

}
