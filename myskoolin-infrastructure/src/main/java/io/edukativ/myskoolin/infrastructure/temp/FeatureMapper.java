package io.edukativ.myskoolin.infrastructure.temp;

import io.edukativ.myskoolin.infrastructure.temp.FeatureDTO;
import io.edukativ.myskoolin.infrastructure.app.dto.FeatureDbDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FeatureMapper {

    FeatureDTO featureToFeatureDTO(FeatureDbDTO grade);

    FeatureDbDTO featureDTOToFeature(FeatureDTO grade);

    List<FeatureDTO> featuresToFeaturesDTO(List<FeatureDbDTO> all);

}
