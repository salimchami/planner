package io.edukativ.myskoolin.infrastructure.schooling;

import io.edukativ.myskoolin.domain.vo.InfirmaryStatistics;
import io.edukativ.myskoolin.infrastructure.schooling.vo.InfirmaryStatisticsDbVO;
import io.edukativ.myskoolin.infrastructure.schooling.vo.InfirmaryStatisticsVO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {
})
public interface InfirmaryStatisticsMapper {

    InfirmaryStatisticsVO dbVoToVo(InfirmaryStatisticsDbVO infirmaryStatistics);

    InfirmaryStatisticsDbVO voToDbVo(InfirmaryStatisticsVO infirmaryStatistics);

    InfirmaryStatisticsDbVO domainToDbVo(InfirmaryStatistics infirmaryStatistics);
}
