package io.edukativ.myskoolin.infrastructure.subjects;

import io.edukativ.myskoolin.domain.subjects.SubjectSPI;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class SubjectProvider implements SubjectSPI {

    private final SubjectRepository subjectRepository;

    public SubjectProvider(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Integer countByGrade(String gradeId) {
        return subjectRepository.countByGrade(new ObjectId(gradeId), false);
    }
}
