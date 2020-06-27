package io.edukativ.myskoolin.infrastructure.subjects;

import io.edukativ.myskoolin.domain.subjects.Subject;
import io.edukativ.myskoolin.domain.subjects.SubjectSPI;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<Subject> searchSubjects(String clientId, String name) {
        final List<SubjectDbDTO> subjectDbDTOS = subjectRepository.searchSubjects(new ObjectId(clientId), name);
        return subjectMapper.dbDtosToDomains(subjectDbDTOS);
    }

    @Override
    public List<Subject> searchSubjects(String name) {
        final List<SubjectDbDTO> subjectDbDTOS = subjectRepository.searchSubjects(name);
        return subjectMapper.dbDtosToDomains(subjectDbDTOS);
    }

    @Override
    public List<Subject> findByGradeContaining(List<String> gradesId) {
        final List<SubjectDbDTO> subjects = subjectRepository.findByGradeContaining(gradesId
                .stream().map(ObjectId::new).collect(Collectors.toList()));
        return subjectMapper.dbDtosToDomains(subjects);
    }

    @Override
    public List<Subject> findByGradeInAndClientId(List<String> gradesId, String clientId) {
        final List<SubjectDbDTO> subjects = subjectRepository.findByGradeInAndClientId(gradesId
                .stream().map(ObjectId::new).collect(Collectors.toList()), new ObjectId(clientId));
        return subjectMapper.dbDtosToDomains(subjects);
    }

    @Override
    public Optional<Subject> findById(String id) {
        final Optional<SubjectDbDTO> optSubject = subjectRepository.findById(id);
        return optSubject.map(subjectMapper::dbDtoToDomain);
    }

    @Override
    public void save(Subject subject) {
        subjectRepository.save(subjectMapper.domainToDbDto(subject));
    }
}
