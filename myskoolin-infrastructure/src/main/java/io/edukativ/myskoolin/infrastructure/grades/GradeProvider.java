package io.edukativ.myskoolin.infrastructure.grades;

import io.edukativ.myskoolin.domain.grades.Grade;
import io.edukativ.myskoolin.domain.grades.GradeSPI;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GradeProvider implements GradeSPI {

    private final GradeRepository gradeRepository;
    private final GradeMapper gradeMapper;

    public GradeProvider(GradeRepository gradeRepository, GradeMapper gradeMapper) {
        this.gradeRepository = gradeRepository;
        this.gradeMapper = gradeMapper;
    }

    @Override
    public List<Grade> findNotDeletedByClientId(String clientId) {
        final List<GradeDbDTO> allNotDeleted = gradeRepository.findAllNotDeleted(new ObjectId(clientId));
        return gradeMapper.dbDtosToModels(allNotDeleted);
    }

    @Override
    public List<Grade> findAll() {
        final List<GradeDbDTO> all = gradeRepository.findAll();
        return gradeMapper.dbDtosToModels(all);
    }
}
