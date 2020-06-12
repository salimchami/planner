package io.edukativ.myskoolin.infrastructure.common.mapper;

import io.edukativ.myskoolin.domain.entity.Address;
import io.edukativ.myskoolin.infrastructure.common.vo.AddressDbVO;
import io.edukativ.myskoolin.infrastructure.common.vo.AddressVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressVO map(AddressDbVO address);

    AddressDbVO map(AddressVO address);

    default Address dbDtoToDomain(AddressDbVO address) {
        return new Address(address.getName(),
                address.getPostalCode(), address.getCity(), address.getCountry());
    }
}
