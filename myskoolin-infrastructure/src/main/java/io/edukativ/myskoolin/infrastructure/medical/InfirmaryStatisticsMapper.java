package io.edukativ.myskoolin.infrastructure.medical;

import io.edukativ.myskoolin.domain.medical.InfirmaryStatistics;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {
})
public interface InfirmaryStatisticsMapper {

    InfirmaryStatisticsVO dbVoToVo(InfirmaryStatisticsDbVO infirmaryStatistics);

    InfirmaryStatisticsDbVO voToDbVo(InfirmaryStatisticsVO infirmaryStatistics);

    InfirmaryStatisticsDbVO domainToDbVo(InfirmaryStatistics infirmaryStatistics);
}
