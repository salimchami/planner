package io.edukativ.myskoolin.infrastructure.temp;

import io.edukativ.myskoolin.infrastructure.commercial.dto.ClientDbDTO;
import io.edukativ.myskoolin.infrastructure.common.mapper.AddressMapper;
import io.edukativ.myskoolin.infrastructure.common.mapper.ObjectIdMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        ObjectIdMapper.class,
        AddressMapper.class,
        TimeTableOptionsMapper.class,
        EmailMapper.class,
        PhoneMapper.class,
        WebsiteMapper.class,
})
public interface ClientMapper {

    ClientDTO dbDTOtoDTO(ClientDbDTO client);

    ClientDbDTO dTOtoDbDTO(ClientDTO client);


}
