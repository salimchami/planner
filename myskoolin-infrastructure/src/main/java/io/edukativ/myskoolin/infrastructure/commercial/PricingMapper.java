package io.edukativ.myskoolin.infrastructure.commercial;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {FeatureMapper.class})
public interface PricingMapper {

    PricingDTO pricingDbDTOToPricingDTO(PricingDbDTO grade);

    PricingDbDTO pricingDTOToPricingDbDTO(PricingDTO grade);

    List<PricingDTO> pricingsDbDTOToPricingsDTO(List<PricingDbDTO> all);
}
