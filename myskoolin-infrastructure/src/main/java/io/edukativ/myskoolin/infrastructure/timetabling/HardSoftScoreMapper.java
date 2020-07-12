package io.edukativ.myskoolin.infrastructure.timetabling;

import org.mapstruct.Mapper;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

@Mapper(componentModel = "spring")
public interface HardSoftScoreMapper {

    default HardSoftScoreVO domainToVo(HardSoftScore score) {
        if (score != null) {
            return new HardSoftScoreVO(score.getInitScore(), score.getHardScore(), score.getSoftScore());
        }
        return null;
    }

    default HardSoftScoreDbVO domainToDbVo(HardSoftScore score) {
        return new HardSoftScoreDbVO(score.getInitScore(), score.getHardScore(), score.getSoftScore());
    }

    default HardSoftScoreDbVO voToDbVo(HardSoftScoreVO score) {
        return new HardSoftScoreDbVO(score.getInitScore(), score.getHardScore(), score.getSoftScore());
    }

    default HardSoftScoreVO dbVoToVo(HardSoftScoreDbVO score) {
        return new HardSoftScoreVO(score.getInitScore(), score.getHardScore(), score.getSoftScore());
    }

}
