package io.edukativ.myskoolin.infrastructure.schoolclasses;

import io.edukativ.myskoolin.domain.timetabling.SchoolClassTimeSlot;
import io.edukativ.myskoolin.infrastructure.timetabling.SchoolClassTimeSlotDbVO;
import io.edukativ.myskoolin.infrastructure.timetabling.SchoolClassTimeSlotVO;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectMapper;
import org.mapstruct.Mapper;

import java.util.List;

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

    List<SchoolClassTimeSlotDbVO> domainsToDbVos(List<SchoolClassTimeSlot> timetable);
}
