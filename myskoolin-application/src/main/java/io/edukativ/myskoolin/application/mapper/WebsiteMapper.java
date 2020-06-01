package io.edukativ.myskoolin.application.mapper;

import io.edukativ.myskoolin.application.dto.WebsiteDTO;
import io.edukativ.myskoolin.infrastructure.common.vo.WebsiteDbVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WebsiteMapper {

    WebsiteDTO dbDtoToDto(WebsiteDbVO website);

    WebsiteDbVO dtoToDbDto(WebsiteDTO website);

}
