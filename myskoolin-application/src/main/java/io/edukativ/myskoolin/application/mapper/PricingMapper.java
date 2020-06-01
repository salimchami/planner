package io.edukativ.myskoolin.application.mapper;

import io.edukativ.myskoolin.application.dto.PricingDTO;
import io.edukativ.myskoolin.infrastructure.commercial.dto.PricingDbDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {FeatureMapper.class})
public interface PricingMapper {

    PricingDTO pricingDbDTOToPricingDTO(PricingDbDTO grade);

    PricingDbDTO pricingDTOToPricingDbDTO(PricingDTO grade);

    List<PricingDTO> pricingsDbDTOToPricingsDTO(List<PricingDbDTO> all);
}
