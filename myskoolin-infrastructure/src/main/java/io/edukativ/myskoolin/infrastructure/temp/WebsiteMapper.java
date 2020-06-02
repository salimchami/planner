package io.edukativ.myskoolin.infrastructure.temp;

import io.edukativ.myskoolin.infrastructure.temp.WebsiteDTO;
import io.edukativ.myskoolin.infrastructure.common.vo.WebsiteDbVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WebsiteMapper {

    WebsiteDTO dbDtoToDto(WebsiteDbVO website);

    WebsiteDbVO dtoToDbDto(WebsiteDTO website);

}
