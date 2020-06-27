package io.edukativ.myskoolin.infrastructure.medical;

import io.edukativ.myskoolin.domain.medical.MedicalInfos;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MedicalInfosMapper {


    MedicalInfosDbVO domainToDbVo(MedicalInfos medicalInfos);
}
