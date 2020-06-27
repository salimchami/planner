package io.edukativ.myskoolin.infrastructure.schoolclasses;

import io.edukativ.myskoolin.domain.timetabling.Lesson;
import io.edukativ.myskoolin.infrastructure.timetabling.SchoolClassTimeSlotDbVO;
import io.edukativ.myskoolin.infrastructure.timetabling.SchoolClassTimeSlotVO;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        SubjectMapper.class,
})
public interface SchoolClassTimeSlotMapper {

    Lesson dbVoToDomain(SchoolClassTimeSlotDbVO schoolClassTimeSlot);

    Lesson voToDomain(SchoolClassTimeSlotVO schoolClassTimeSlot);

    SchoolClassTimeSlotVO dbVoToVo(SchoolClassTimeSlotDbVO schoolClassTimeSlot);

    SchoolClassTimeSlotVO domainToVo(Lesson lesson);

    SchoolClassTimeSlotDbVO voToDbVo(SchoolClassTimeSlotVO schoolClassTimeSlot);

    SchoolClassTimeSlotDbVO domainToDbVo(Lesson lesson);

    List<SchoolClassTimeSlotDbVO> domainsToDbVos(List<Lesson> timetable);
}
