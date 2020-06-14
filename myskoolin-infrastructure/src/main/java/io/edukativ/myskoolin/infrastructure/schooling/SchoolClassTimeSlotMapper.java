package io.edukativ.myskoolin.infrastructure.schooling;

import io.edukativ.myskoolin.domain.vo.SchoolClassTimeSlot;
import io.edukativ.myskoolin.infrastructure.schooling.vo.SchoolClassTimeSlotDbVO;
import io.edukativ.myskoolin.infrastructure.schooling.vo.SchoolClassTimeSlotVO;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectMapper;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {
        SubjectMapper.class,
})
public interface SchoolClassTimeSlotMapper {

    SchoolClassTimeSlot dbVoToDomain(SchoolClassTimeSlotDbVO schoolClassTimeSlot);

    SchoolClassTimeSlot voToDomain(SchoolClassTimeSlotVO schoolClassTimeSlot);

    SchoolClassTimeSlotVO dbVoToVo(SchoolClassTimeSlotDbVO schoolClassTimeSlot);

    SchoolClassTimeSlotVO domainToVo(SchoolClassTimeSlot schoolClassTimeSlot);

    SchoolClassTimeSlotDbVO voToDbVo(SchoolClassTimeSlotVO schoolClassTimeSlot);

    SchoolClassTimeSlotDbVO domainToDbVo(SchoolClassTimeSlot schoolClassTimeSlot);
}
