package io.edukativ.myskoolin.infrastructure.config.dbmigrations.dev;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import io.edukativ.myskoolin.infrastructure.commercial.ClientDbDTO;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumDays;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumSchoolClassNotation;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.MigrationTempData;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.dev.util.DevDbMigrationsConstants;
import io.edukativ.myskoolin.infrastructure.grades.GradeDbDTO;
import io.edukativ.myskoolin.infrastructure.grades.GradeSerieDbVO;
import io.edukativ.myskoolin.infrastructure.schooling.vo.TimeTableOptionsDbVO;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Creates the initial database setup
 */
@ChangeLog(order = "007")
public class ChangeSet007Grades {

    private static final String GRADES = "grades";

    @ChangeSet(order = "01", author = "sch", id = "01-grades-sixieme")
    public void addGradeSixieme(MongoTemplate mongoTemplate) {
        final TimeTableOptionsDbVO timeTableOptions = ClientDbDTO.defaultTimeTableOptions();
        timeTableOptions.setCoursesTimeSlots(timeTableOptions.getCoursesTimeSlots()
            .stream()
            .filter(timeSlot -> !EnumDays.SATURDAY.equals(timeSlot.getDay()))
            .collect(Collectors.toList()));
        timeTableOptions.setExtraActivities(timeTableOptions.getExtraActivities()
            .stream()
            .filter(timeSlot -> !EnumDays.SATURDAY.equals(timeSlot.getDay()))
            .collect(Collectors.toList()));
        GradeDbDTO grade = new GradeDbDTO(DevDbMigrationsConstants.CLIENT_01_ID,
            "Sixième",
            1,
            "6°",
            EnumSchoolClassNotation.QUARTER,
            420,
            Collections.emptyList(),
            timeTableOptions
        );
        grade.setId(DevDbMigrationsConstants.GRADE_SIXIEME_ID);
        mongoTemplate.insert(grade);
        MigrationTempData.grades.add(grade);
    }

    @ChangeSet(order = "02", author = "sch", id = "02-grades-cinquieme")
    public void addGradesCinquieme(MongoTemplate mongoTemplate) {
        GradeDbDTO grade = new GradeDbDTO(
            DevDbMigrationsConstants.CLIENT_01_ID,
            "Cinquième",
            2,
            "5°",
            EnumSchoolClassNotation.QUARTER,
            420,
            Collections.emptyList(),
            ClientDbDTO.defaultTimeTableOptions()
        );
        grade.setId(DevDbMigrationsConstants.GRADE_CINQUIEME_ID);
        mongoTemplate.insert(grade);
        MigrationTempData.grades.add(grade);
    }

    @ChangeSet(order = "03", author = "sch", id = "03-grades-quatrieme")
    public void addGradesQuatrieme(MongoTemplate mongoTemplate) {
        GradeDbDTO grade = new GradeDbDTO(
            DevDbMigrationsConstants.CLIENT_01_ID,
            "Quatrieme",
            3,
            "4°",
            EnumSchoolClassNotation.QUARTER,
            480,
            Collections.emptyList(),
            ClientDbDTO.defaultTimeTableOptions()
        );
        grade.setId(DevDbMigrationsConstants.GRADE_QUATRIEME_ID);
        mongoTemplate.insert(grade);
        MigrationTempData.grades.add(grade);
    }

    @ChangeSet(order = "04", author = "sch", id = "04-grades-troisieme")
    public void addGradesTroisieme(MongoTemplate mongoTemplate) {
        GradeDbDTO grade = new GradeDbDTO(
            DevDbMigrationsConstants.CLIENT_01_ID,
            "Troisième",
            4,
            "3°",
            EnumSchoolClassNotation.QUARTER,
            480,
            Collections.emptyList(),
            ClientDbDTO.defaultTimeTableOptions()
        );
        grade.setId(DevDbMigrationsConstants.GRADE_TROISIEME_ID);
        mongoTemplate.insert(grade);
        MigrationTempData.grades.add(grade);
    }

    @ChangeSet(order = "05", author = "sch", id = "05-grades-seconde")
    public void addGradesSeconde(MongoTemplate mongoTemplate) {
        GradeDbDTO grade = new GradeDbDTO(
            DevDbMigrationsConstants.CLIENT_01_ID,
            "Seconde",
            5,
            "2°",
            EnumSchoolClassNotation.QUARTER,
            480,
            Collections.emptyList(),
            ClientDbDTO.defaultTimeTableOptions()
        );
        grade.setId(DevDbMigrationsConstants.GRADE_SECONDE_ID);
        mongoTemplate.insert(grade);
        MigrationTempData.grades.add(grade);
    }

    @ChangeSet(order = "06", author = "sch", id = "06-grades-premiere")
    public void addGradesPremiere(MongoTemplate mongoTemplate) {
        GradeDbDTO grade = new GradeDbDTO(
            DevDbMigrationsConstants.CLIENT_01_ID,
            "Première",
            6,
            "1ère",
            EnumSchoolClassNotation.QUARTER,
            480,
            Arrays.asList(
                new GradeSerieDbVO("Scientifique", "S"),
                new GradeSerieDbVO("Littéraire", "L"),
                new GradeSerieDbVO("Sciences Économiques et Sociales", "ES"),
                new GradeSerieDbVO("Sciences et Technologies du Management et de la Gestion", "STMG")
            ),
            ClientDbDTO.defaultTimeTableOptions()
        );
        grade.setId(DevDbMigrationsConstants.GRADE_PREMIERE_ID);
        mongoTemplate.insert(grade);
        MigrationTempData.grades.add(grade);
    }

    @ChangeSet(order = "07", author = "sch", id = "07-grades-terminale")
    public void addGradesTerminale(MongoTemplate mongoTemplate) {
        GradeDbDTO grade = new GradeDbDTO(
            DevDbMigrationsConstants.CLIENT_01_ID,
            "Terminale",
            7,
            "Tle",
            EnumSchoolClassNotation.QUARTER,
            540,
            Arrays.asList(
                new GradeSerieDbVO("Scientifique", "S"),
                new GradeSerieDbVO("Littéraire", "L"),
                new GradeSerieDbVO("Sciences Économiques et Sociales", "ES"),
                new GradeSerieDbVO("Sciences et Technologies du Management et de la Gestion", "STMG")
            ),
            ClientDbDTO.defaultTimeTableOptions()
        );
        grade.setId(DevDbMigrationsConstants.GRADE_TERMINALE_ID);
        mongoTemplate.insert(grade);
        MigrationTempData.grades.add(grade);
    }


}
