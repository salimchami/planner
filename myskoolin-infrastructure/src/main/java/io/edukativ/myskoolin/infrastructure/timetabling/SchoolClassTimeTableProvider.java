package io.edukativ.myskoolin.infrastructure.timetabling;

import io.edukativ.myskoolin.domain.timetabling.SchoolClassTimeTable;
import io.edukativ.myskoolin.domain.timetabling.TimeTableSPI;
import io.edukativ.myskoolin.infrastructure.teachers.TeacherDbDTO;
import io.edukativ.myskoolin.infrastructure.teachers.TeacherMapperImplemented;
import org.springframework.stereotype.Component;

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
        return optTimetable.map(schoolClassTimeTableMapper::dbVoToDomain);
    }

    @Override
    public SchoolClassTimeTable saveTimeTable(SchoolClassTimeTable schoolClassTimeTable) {
        final SchoolClassTimeTableDbDTO schoolClassTimeTableDbDTO = schoolClassTimeTableMapper.domainToDbVo(schoolClassTimeTable);
        mapTeachers(schoolClassTimeTable, schoolClassTimeTableDbDTO);
        final SchoolClassTimeTableDbDTO savedTimeTable = schoolClassTimeTableRepository.save(schoolClassTimeTableDbDTO);
        return schoolClassTimeTableMapper.dbVoToDomain(savedTimeTable);
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
