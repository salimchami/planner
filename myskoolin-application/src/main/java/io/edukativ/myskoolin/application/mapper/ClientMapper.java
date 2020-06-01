package io.edukativ.myskoolin.application.mapper;

import io.edukativ.myskoolin.application.dto.ClientDTO;
import io.edukativ.myskoolin.infrastructure.commercial.dto.ClientDbDTO;
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
