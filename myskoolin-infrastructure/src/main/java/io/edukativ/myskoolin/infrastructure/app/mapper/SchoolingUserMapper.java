package io.edukativ.myskoolin.infrastructure.app.mapper;

import io.edukativ.myskoolin.domain.entity.User;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDTO;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import io.edukativ.myskoolin.infrastructure.common.mapper.AddressMapper;
import io.edukativ.myskoolin.infrastructure.common.mapper.ObjectIdMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        ObjectIdMapper.class,
        AuthorityMapper.class,
        AddressMapper.class,
})
public interface SchoolingUserMapper {

    User dbDtoToDomain(UserDbDTO user);

    User dtoToDomain(UserDTO user);

    UserDTO dbDtoToDto(UserDbDTO user);

    UserDTO domainToDto(User user);

    UserDbDTO dtoToDbDto(UserDTO user);

    UserDbDTO domainToDbDto(User user);
}
