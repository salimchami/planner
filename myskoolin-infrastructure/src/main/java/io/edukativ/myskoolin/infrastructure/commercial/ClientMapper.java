package io.edukativ.myskoolin.infrastructure.commercial;

import io.edukativ.myskoolin.infrastructure.common.mapper.*;
import io.edukativ.myskoolin.infrastructure.schooling.TimeTableOptionsMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {
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
