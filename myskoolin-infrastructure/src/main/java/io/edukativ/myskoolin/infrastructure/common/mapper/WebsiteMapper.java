package io.edukativ.myskoolin.infrastructure.common.mapper;

import io.edukativ.myskoolin.infrastructure.common.dto.WebsiteDTO;
import io.edukativ.myskoolin.infrastructure.common.vo.WebsiteDbVO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface WebsiteMapper {

    WebsiteDTO dbDtoToDto(WebsiteDbVO website);

    WebsiteDbVO dtoToDbDto(WebsiteDTO website);

}
