package io.edukativ.myskoolin.infrastructure.schooling;

import io.edukativ.myskoolin.domain.vo.Absence;
import io.edukativ.myskoolin.infrastructure.schooling.vo.AbsenceDbVO;
import io.edukativ.myskoolin.infrastructure.schooling.vo.AbsenceVO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AbsenceMapper {

    Absence dbVoToDomain(AbsenceDbVO absence);

    Absence voToDomain(AbsenceVO absence);

    AbsenceVO dbVoToVo(AbsenceDbVO absence);

    AbsenceVO domainToVo(Absence absence);

    AbsenceDbVO voToDbVo(AbsenceVO absence);

    AbsenceDbVO domainToDbVo(Absence absence);

    List<AbsenceDbVO> domainsToDbVos(List<Absence> absences);
}
