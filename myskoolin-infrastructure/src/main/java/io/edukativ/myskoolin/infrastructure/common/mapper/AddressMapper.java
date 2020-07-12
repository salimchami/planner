package io.edukativ.myskoolin.infrastructure.common.mapper;

import io.edukativ.myskoolin.domain.commons.entity.Address;
import io.edukativ.myskoolin.infrastructure.common.vo.AddressDbVO;
import io.edukativ.myskoolin.infrastructure.common.vo.AddressVO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AddressMapper {

    AddressDbVO voToDbVo(AddressVO address);

    default Address dbVoToDomain(AddressDbVO address) {
        return new Address(address.getName(),
                address.getPostalCode(), address.getCity(), address.getCountry());
    }

    AddressDbVO domainToDbVo(Address address);

    AddressVO dbVoToVo(AddressDbVO address);

    Address voToDomain(AddressVO address);

    AddressVO domainToVo(Address address);
}
