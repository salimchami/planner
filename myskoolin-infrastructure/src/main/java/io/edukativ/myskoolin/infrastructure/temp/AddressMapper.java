package io.edukativ.myskoolin.infrastructure.temp;

import io.edukativ.myskoolin.infrastructure.temp.AddressDTO;
import io.edukativ.myskoolin.infrastructure.common.vo.AddressDbVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDTO dbDtoToDto(AddressDbVO address);

    AddressDbVO dtoToDbDto(AddressDTO address);

}
