package io.edukativ.myskoolin.infrastructure.config.dbmigrations.dev.sixieme;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumSchoolClassNotation;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.DbMigrationsFindUtils;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.MigrationTempData;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.dev.util.DevDbMigrationsConstants;
import io.edukativ.myskoolin.infrastructure.grades.GradeDbDTO;
import io.edukativ.myskoolin.infrastructure.schoolclasses.SchoolClassDbDTO;
import io.edukativ.myskoolin.infrastructure.students.StudentDbDTO;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectDbDTO;
import io.edukativ.myskoolin.infrastructure.teachers.TeacherDbDTO;
import io.edukativ.myskoolin.infrastructure.schooling.vo.TeachersBySubjectDbVO;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO.MONGO_COLLECTION_NAME;

@ChangeLog(order = "013")
public class ChangeSet013Schoolclasses {

    @ChangeSet(order = "01", author = "sch", id = "01-schoolClass-SixiemeUn")
    public void addSchoolClassSixiemeUn(MongoTemplate mongoTemplate) {
        GradeDbDTO grade = DbMigrationsFindUtils.findGradeById(mongoTemplate, DevDbMigrationsConstants.GRADE_SIXIEME_ID);
        List<TeacherDbDTO> teachers = DbMigrationsFindUtils.findTeachersByGrade(mongoTemplate, DevDbMigrationsConstants.CLIENT_01_ID, grade);
        List<SubjectDbDTO> subjects = DbMigrationsFindUtils.findSubjectsByGrade(mongoTemplate, DevDbMigrationsConstants.CLIENT_01_ID, grade);
        List<TeachersBySubjectDbVO> teachersBySubjects = new ArrayList<>();
        subjects.forEach(subject -> teachersBySubjects.add(new TeachersBySubjectDbVO(subject, teachers.stream()
                .filter(teacher -> teacher.getTaughtSubjects().contains(subject))
                .collect(Collectors.toList())))
        );

        SchoolClassDbDTO schoolClass = new SchoolClassDbDTO(DevDbMigrationsConstants.SCHOOLCLASS_SIXIEME_1_ID, DevDbMigrationsConstants.CLIENT_01_ID,
                ZonedDateTime.of(2018, 9, 2, 8, 0, 0, 0, ZoneId.systemDefault()),
                ZonedDateTime.of(2019, 7, 2, 8, 0, 0, 0, ZoneId.systemDefault()),
                Arrays.asList(
                        ZonedDateTime.of(2018, 12, 22, 18, 30, 0, 0, ZoneId.systemDefault()),
                        ZonedDateTime.of(2019, 3, 22, 18, 30, 0, 0, ZoneId.systemDefault()),
                        ZonedDateTime.of(2019, 6, 22, 18, 30, 0, 0, ZoneId.systemDefault())
                ), "6°1", "Sixième 1", false, Collections.emptyList(), grade, null,
                null, Collections.emptyList(), EnumSchoolClassNotation.QUARTER, teachersBySubjects);
        mongoTemplate.insert(schoolClass);
        MigrationTempData.schoolClasses.add(schoolClass);
//        List<StudentDbDTO> allStudents = mongoTemplate.findAll(StudentDbDTO.class, MONGO_COLLECTION_NAME);
        List<StudentDbDTO> allStudents = Collections.unmodifiableList(MigrationTempData.students);
        allStudents
                .forEach(user -> {
                    user.changeSchoolClassId(schoolClass.getId().toString());
                    mongoTemplate.save(user, MONGO_COLLECTION_NAME);
                });
    }

    @ChangeSet(order = "02", author = "sch", id = "02-schoolClass-SixiemeDeux")
    public void addSchoolClassSixiemeDeux(MongoTemplate mongoTemplate) {
        GradeDbDTO grade = DbMigrationsFindUtils.findGradeById(mongoTemplate, DevDbMigrationsConstants.GRADE_SIXIEME_ID);
        List<SubjectDbDTO> subjects = DbMigrationsFindUtils.findSubjectsByGrade(mongoTemplate, DevDbMigrationsConstants.CLIENT_01_ID, grade);
        List<TeacherDbDTO> teacherUsers = DbMigrationsFindUtils.findTeachersByGrade(mongoTemplate, DevDbMigrationsConstants.CLIENT_01_ID, grade);
        List<TeachersBySubjectDbVO> teachersBySubjects = new ArrayList<>();
        subjects.forEach(subject -> teachersBySubjects.add(new TeachersBySubjectDbVO(subject, teacherUsers.stream()
                .filter(teacher -> teacher.getTaughtSubjects().contains(subject))
                .collect(Collectors.toList()))));
        SchoolClassDbDTO schoolClass = new SchoolClassDbDTO(DevDbMigrationsConstants.SCHOOLCLASS_SIXIEME_2_ID, DevDbMigrationsConstants.CLIENT_01_ID,
                ZonedDateTime.of(2018, 9, 2, 8, 0, 0, 0, ZoneId.systemDefault()),
                ZonedDateTime.of(2019, 7, 2, 8, 0, 0, 0, ZoneId.systemDefault()),
                Arrays.asList(
                        ZonedDateTime.of(2018, 12, 22, 18, 30, 0, 0, ZoneId.systemDefault()),
                        ZonedDateTime.of(2019, 3, 22, 18, 30, 0, 0, ZoneId.systemDefault()),
                        ZonedDateTime.of(2019, 6, 22, 18, 30, 0, 0, ZoneId.systemDefault())
                ), "6°2", "Sixième 2", false, Collections.emptyList(), grade, null,
                null, Collections.emptyList(), EnumSchoolClassNotation.QUARTER, teachersBySubjects);
        mongoTemplate.insert(schoolClass);
        MigrationTempData.schoolClasses.add(schoolClass);
    }
}
