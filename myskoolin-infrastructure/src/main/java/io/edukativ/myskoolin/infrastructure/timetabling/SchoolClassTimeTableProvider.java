package io.edukativ.myskoolin.infrastructure.timetabling;

import io.edukativ.myskoolin.domain.timetabling.SchoolClassTimeTable;
import io.edukativ.myskoolin.domain.timetabling.TimeTableSPI;
import io.edukativ.myskoolin.infrastructure.teachers.TeacherDbDTO;
import io.edukativ.myskoolin.infrastructure.teachers.TeacherMapperImplemented;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SchoolClassTimeTableProvider implements TimeTableSPI {

    private final SchoolClassTimeTableRepository schoolClassTimeTableRepository;
    private final SchoolClassTimeTableMapper schoolClassTimeTableMapper;
    private final TeacherMapperImplemented teacherMapperImplemented;

    public SchoolClassTimeTableProvider(SchoolClassTimeTableRepository schoolClassTimeTableRepository, SchoolClassTimeTableMapper schoolClassTimeTableMapper, TeacherMapperImplemented teacherMapperImplemented) {
        this.schoolClassTimeTableRepository = schoolClassTimeTableRepository;
        this.schoolClassTimeTableMapper = schoolClassTimeTableMapper;
        this.teacherMapperImplemented = teacherMapperImplemented;
    }

    @Override
    public Optional<SchoolClassTimeTable> findById(String timeTableId) {
        final Optional<SchoolClassTimeTableDbDTO> optTimetable = schoolClassTimeTableRepository.findById(timeTableId);
        return optTimetable.map(schoolClassTimeTableMapper::dbDtoToDomain);
    }

    @Override
    public SchoolClassTimeTable saveTimeTable(SchoolClassTimeTable schoolClassTimeTable) {
        final SchoolClassTimeTableDbDTO schoolClassTimeTableDbDTO = schoolClassTimeTableMapper.domainToDbDto(schoolClassTimeTable);
        mapTeachers(schoolClassTimeTable, schoolClassTimeTableDbDTO);
        final SchoolClassTimeTableDbDTO savedTimeTable = schoolClassTimeTableRepository.save(schoolClassTimeTableDbDTO);
        return schoolClassTimeTableMapper.dbDtoToDomain(savedTimeTable);
    }

    @Override
    public List<SchoolClassTimeTable> findAllByClientId(String clientId) {
        final List<SchoolClassTimeTableDbDTO> timetables = schoolClassTimeTableRepository.findByClientId(new ObjectId(clientId));
        return schoolClassTimeTableMapper.dbDtosToDomains(timetables);
    }

    private void mapTeachers(SchoolClassTimeTable schoolClassTimeTable, SchoolClassTimeTableDbDTO schoolClassTimeTableDbDTO) {
        schoolClassTimeTable.getLessons().forEach(lesson -> {
            final TeacherDbDTO teacherDbDTO = teacherMapperImplemented.domainToDbDto(lesson.getTeacher());
            schoolClassTimeTableDbDTO.getLessons().forEach(lessonDbVO -> {
                if (lesson.getId().equals(Long.valueOf(lessonDbVO.getId()))) {
                    lessonDbVO.setTeacher(teacherDbDTO);
                }
            });
        });
    }
}
