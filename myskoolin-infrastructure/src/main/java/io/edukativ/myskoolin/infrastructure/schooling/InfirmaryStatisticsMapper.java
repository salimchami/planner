package io.edukativ.myskoolin.infrastructure.schooling;

import io.edukativ.myskoolin.infrastructure.schooling.vo.InfirmaryStatisticsDbVO;
import io.edukativ.myskoolin.infrastructure.schooling.vo.InfirmaryStatisticsVO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {
})
public interface InfirmaryStatisticsMapper {

    InfirmaryStatisticsVO dbVoToVo(InfirmaryStatisticsDbVO infirmaryStatistics);

    InfirmaryStatisticsDbVO voToDbVo(InfirmaryStatisticsVO infirmaryStatistics);

}
