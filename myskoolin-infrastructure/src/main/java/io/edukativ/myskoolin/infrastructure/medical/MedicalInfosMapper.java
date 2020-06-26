package io.edukativ.myskoolin.infrastructure.medical;

import io.edukativ.myskoolin.domain.vo.MedicalInfos;
import io.edukativ.myskoolin.infrastructure.schooling.vo.MedicalInfosDbVO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MedicalInfosMapper {


    MedicalInfosDbVO domainToDbVo(MedicalInfos medicalInfos);
}
