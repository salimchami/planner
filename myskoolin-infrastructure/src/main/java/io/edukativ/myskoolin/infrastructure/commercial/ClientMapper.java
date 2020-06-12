package io.edukativ.myskoolin.infrastructure.commercial;

import io.edukativ.myskoolin.infrastructure.common.mapper.AddressMapper;
import io.edukativ.myskoolin.infrastructure.common.mapper.ObjectIdMapper;
import io.edukativ.myskoolin.infrastructure.common.mapper.EmailMapper;
import io.edukativ.myskoolin.infrastructure.common.mapper.PhoneMapper;
import io.edukativ.myskoolin.infrastructure.schooling.TimeTableOptionsMapper;
import io.edukativ.myskoolin.infrastructure.common.mapper.WebsiteMapper;
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

    ClientDTO map(ClientDbDTO client);

    ClientDbDTO map(ClientDTO client);


}
