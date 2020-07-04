package io.edukativ.myskoolin.infrastructure.timetabling;

import io.edukativ.myskoolin.domain.timetabling.SchoolClassTimeTable;
import io.edukativ.myskoolin.infrastructure.common.mapper.ObjectIdMapper;
import io.edukativ.myskoolin.infrastructure.schoolclasses.SchoolClassMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HardSoftScoreMapper {

    default HardSoftScoreVO map(HardSoftScore score) {
        return new HardSoftScoreVO(score.getHardScore(), score.getSoftScore());
    }

}
