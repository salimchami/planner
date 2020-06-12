package io.edukativ.myskoolin.infrastructure.subjects;

import io.edukativ.myskoolin.domain.entity.Subject;
import io.edukativ.myskoolin.domain.subjects.SubjectSPI;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SubjectProvider implements SubjectSPI {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    public SubjectProvider(SubjectRepository subjectRepository, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }

    @Override
    public Integer countByGrade(String gradeId) {
        return subjectRepository.countByGrade(new ObjectId(gradeId), false);
    }

    @Override
    public Subject createSubject(Subject subject) {
        final SubjectDbDTO subjectToSave = subjectMapper.domainToDbDto(subject);
        final SubjectDbDTO savedSubject = subjectRepository.save(subjectToSave);
        return subjectMapper.dbDtoToDomain(savedSubject);
    }

    @Override
    public Subject updateSubject(Subject subject) {
        final Optional<SubjectDbDTO> optDbSubject = subjectRepository.findById(subject.getId());
        return optDbSubject.map(dbSubject -> {
            final SubjectDbDTO subjectToSave = subjectMapper.domainToDbDto(subject);
            dbSubject.update(subjectToSave);
            final SubjectDbDTO savedSubject = subjectRepository.save(dbSubject);
            return subjectMapper.dbDtoToDomain(savedSubject);
        }).orElseThrow();
    }
}
