package io.edukativ.myskoolin.infrastructure.config.dbmigrations.dev.sixieme;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumDays;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumPartsOfDay;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumSchoolRoomsTypesDb;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.DbMigrationsFindUtils;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.MigrationTempData;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.dev.util.DevDbMigrationsConstants;
import io.edukativ.myskoolin.infrastructure.grades.GradeDbDTO;
import io.edukativ.myskoolin.infrastructure.timetabling.PreferredPartsOfDaysDbVO;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectDbDTO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import static io.edukativ.myskoolin.infrastructure.subjects.SubjectDbDTO.MONGO_COLLECTION_NAME;

/**
 * Creates the initial database setup
 */
@ChangeLog(order = "009")
public class ChangeSet009SixiemeSubjects {

    @ChangeSet(order = "01", author = "sch", id = "01-subjects-Sixieme-Techno")
    public void addTechnologie(MongoTemplate mongoTemplate) {
        SubjectDbDTO subject = new SubjectDbDTO(
                DevDbMigrationsConstants.CLIENT_01_ID,
                "TECHNOLOGIE",
                "TECHNO",
                DbMigrationsFindUtils.findOneWithCriterias(mongoTemplate, GradeDbDTO.MONGO_COLLECTION_NAME, GradeDbDTO.class, new HashMap<String, ObjectId>() {{
                    put("_id", DevDbMigrationsConstants.GRADE_SIXIEME_ID);
                }}),
                null,
                BigDecimal.valueOf(1), DevDbMigrationsConstants.SUBJECT_TECHNOLOGY_COLOR, DevDbMigrationsConstants.SUBJECT_TECHNOLOGY_BGCOLOR,
                false, 120, 60, BigDecimal.valueOf(60 * 2L).intValue(), 2,
                false, null, Collections.singletonList(EnumSchoolRoomsTypesDb.IT), false);
        subject.setId(DevDbMigrationsConstants.SUBJECT_SIXIEME_TECHNO_ID);
        subject.setPreferredPartsOfDaysInTimetables(
                Arrays.asList(
                        new PreferredPartsOfDaysDbVO(EnumDays.TUESDAY, Collections.singletonList(EnumPartsOfDay.PM)),
                        new PreferredPartsOfDaysDbVO(EnumDays.THURSDAY, Collections.singletonList(EnumPartsOfDay.PM))
                )
        );
        mongoTemplate.insert(subject, MONGO_COLLECTION_NAME);
        MigrationTempData.subjects.add(subject);
    }

    @ChangeSet(order = "02", author = "sch", id = "02-subjects-Sixieme-Francais")
    public void addFrancais(MongoTemplate mongoTemplate) {
        SubjectDbDTO subject = new SubjectDbDTO(
                DevDbMigrationsConstants.CLIENT_01_ID,
                "FRANÇAIS",
                "FRANÇAIS",
                DbMigrationsFindUtils.findOneWithCriterias(mongoTemplate, GradeDbDTO.MONGO_COLLECTION_NAME, GradeDbDTO.class, new HashMap<String, ObjectId>() {{
                    put("_id", DevDbMigrationsConstants.GRADE_SIXIEME_ID);
                }}),
                null,
                BigDecimal.valueOf(5), DevDbMigrationsConstants.SUBJECT_FRANCAIS_COLOR, DevDbMigrationsConstants.SUBJECT_FRANCAIS_BGCOLOR,
                false, 120, 60, BigDecimal.valueOf(60 * 4.5).intValue(), 3,
                false, null, Collections.singletonList(EnumSchoolRoomsTypesDb.NORMAL), false);
        subject.setId(DevDbMigrationsConstants.SUBJECT_SIXIEME_FRANCAIS_ID);
        subject.setPreferredPartsOfDaysInTimetables(
                Arrays.asList(
                        new PreferredPartsOfDaysDbVO(EnumDays.MONDAY, Collections.singletonList(EnumPartsOfDay.AM)),
                        new PreferredPartsOfDaysDbVO(EnumDays.TUESDAY, Collections.singletonList(EnumPartsOfDay.AM)),
                        new PreferredPartsOfDaysDbVO(EnumDays.THURSDAY, Collections.singletonList(EnumPartsOfDay.AM)),
                        new PreferredPartsOfDaysDbVO(EnumDays.FRIDAY, Collections.singletonList(EnumPartsOfDay.AM))
                )
        );
        mongoTemplate.insert(subject, MONGO_COLLECTION_NAME);
        MigrationTempData.subjects.add(subject);
    }

    @ChangeSet(order = "03", author = "sch", id = "03-subjects-Sixieme-Sport")
    public void addSport(MongoTemplate mongoTemplate) {
        SubjectDbDTO subject = new SubjectDbDTO(
                DevDbMigrationsConstants.CLIENT_01_ID,
                "EDUCATION PHYSIQUE ET SPORTIVE",
                "EPS",
                DbMigrationsFindUtils.findOneWithCriterias(mongoTemplate, GradeDbDTO.MONGO_COLLECTION_NAME, GradeDbDTO.class, new HashMap<String, ObjectId>() {{
                    put("_id", DevDbMigrationsConstants.GRADE_SIXIEME_ID);
                }}),
                null,
                BigDecimal.valueOf(2), DevDbMigrationsConstants.SUBJECT_SPORT_COLOR, DevDbMigrationsConstants.SUBJECT_SPORT_BGCOLOR,
                false, 120, 120, BigDecimal.valueOf(60 * 4L).intValue(), 2,
                false, null, Collections.singletonList(EnumSchoolRoomsTypesDb.SPORT), false);
        subject.setId(DevDbMigrationsConstants.SUBJECT_SIXIEME_SPORT_ID);
        subject.setPreferredPartsOfDaysInTimetables(
                Arrays.asList(
                        new PreferredPartsOfDaysDbVO(EnumDays.MONDAY, Collections.singletonList(EnumPartsOfDay.PM)),
                        new PreferredPartsOfDaysDbVO(EnumDays.TUESDAY, Collections.singletonList(EnumPartsOfDay.PM)),
                        new PreferredPartsOfDaysDbVO(EnumDays.THURSDAY, Collections.singletonList(EnumPartsOfDay.PM)),
                        new PreferredPartsOfDaysDbVO(EnumDays.FRIDAY, Collections.singletonList(EnumPartsOfDay.PM))
                )
        );
        mongoTemplate.insert(subject, MONGO_COLLECTION_NAME);
        MigrationTempData.subjects.add(subject);
    }

    @ChangeSet(order = "04", author = "sch", id = "04-subjects-Sixieme-Anglais")
    public void addAnglais(MongoTemplate mongoTemplate) {
        SubjectDbDTO subject = new SubjectDbDTO(
                DevDbMigrationsConstants.CLIENT_01_ID,
                "ANGLAIS LV1",
                "ANGLAIS LV1",
                DbMigrationsFindUtils.findOneWithCriterias(mongoTemplate, GradeDbDTO.MONGO_COLLECTION_NAME, GradeDbDTO.class, new HashMap<String, ObjectId>() {{
                    put("_id", DevDbMigrationsConstants.GRADE_SIXIEME_ID);
                }}),
                null,
                BigDecimal.valueOf(4), DevDbMigrationsConstants.SUBJECT_SPORT_COLOR, DevDbMigrationsConstants.SUBJECT_SPORT_BGCOLOR,
                true, 120, 60, BigDecimal.valueOf(60 * 4L).intValue(), 2,
                false, "LV1", Collections.singletonList(EnumSchoolRoomsTypesDb.NORMAL), false);
        subject.setId(DevDbMigrationsConstants.SUBJECT_SIXIEME_ANGLAIS_ID);
        mongoTemplate.insert(subject, MONGO_COLLECTION_NAME);
        MigrationTempData.subjects.add(subject);
    }

    @ChangeSet(order = "05", author = "sch", id = "05-subjects-Sixieme-Dessin")
    public void addDessin(MongoTemplate mongoTemplate) {
        SubjectDbDTO subject = new SubjectDbDTO(
                DevDbMigrationsConstants.CLIENT_01_ID,
                "ARTS PLASTIQUES",
                "ARTS PLASTIQUES",
                DbMigrationsFindUtils.findOneWithCriterias(mongoTemplate, GradeDbDTO.MONGO_COLLECTION_NAME, GradeDbDTO.class, new HashMap<String, ObjectId>() {{
                    put("_id", DevDbMigrationsConstants.GRADE_SIXIEME_ID);
                }}),
                null,
                BigDecimal.valueOf(1), DevDbMigrationsConstants.SUBJECT_ARTS_PLASTIQUES_COLOR,
                DevDbMigrationsConstants.SUBJECT_ARTS_PLASTIQUES_BGCOLOR,
                false, 60, 60, 60, 1,
                false, null, Collections.singletonList(EnumSchoolRoomsTypesDb.NORMAL), false);
        subject.setId(DevDbMigrationsConstants.SUBJECT_SIXIEME_ARTS_PLASTIQUES_ID);
        subject.setPreferredPartsOfDaysInTimetables(Collections.singletonList(
                new PreferredPartsOfDaysDbVO(EnumDays.FRIDAY, Arrays.asList(EnumPartsOfDay.AM, EnumPartsOfDay.PM))
        ));

        mongoTemplate.insert(subject, MONGO_COLLECTION_NAME);
        MigrationTempData.subjects.add(subject);
    }

    @ChangeSet(order = "06", author = "sch", id = "06-subjects-Sixieme-Histoire-Geo")
    public void addHistGeo(MongoTemplate mongoTemplate) {
        SubjectDbDTO subject = new SubjectDbDTO(
                DevDbMigrationsConstants.CLIENT_01_ID,
                "HISTOIRE-GEOGRAPHIE",
                "HISTOIRE-GEO",
                DbMigrationsFindUtils.findOneWithCriterias(mongoTemplate, GradeDbDTO.MONGO_COLLECTION_NAME, GradeDbDTO.class, new HashMap<String, ObjectId>() {{
                    put("_id", DevDbMigrationsConstants.GRADE_SIXIEME_ID);
                }}),
                null,
                BigDecimal.valueOf(4), DevDbMigrationsConstants.SUBJECT_HIST_GEO_COLOR,
                DevDbMigrationsConstants.SUBJECT_HIST_GEO_BGCOLOR,
                false, 60, 60, BigDecimal.valueOf(60 * 3L).intValue(), 3,
                false, null, Collections.singletonList(EnumSchoolRoomsTypesDb.NORMAL), false);
        subject.setId(DevDbMigrationsConstants.SUBJECT_SIXIEME_HIST_GEO_ID);
        subject.setPreferredPartsOfDaysInTimetables(Arrays.asList(
                new PreferredPartsOfDaysDbVO(EnumDays.MONDAY, Collections.singletonList(EnumPartsOfDay.AM)),
                new PreferredPartsOfDaysDbVO(EnumDays.TUESDAY, Collections.singletonList(EnumPartsOfDay.AM)),
                new PreferredPartsOfDaysDbVO(EnumDays.THURSDAY, Collections.singletonList(EnumPartsOfDay.AM)),
                new PreferredPartsOfDaysDbVO(EnumDays.FRIDAY, Collections.singletonList(EnumPartsOfDay.AM))
        ));
        mongoTemplate.insert(subject, MONGO_COLLECTION_NAME);
        MigrationTempData.subjects.add(subject);
    }

    @ChangeSet(order = "07", author = "sch", id = "07-subjects-Sixieme-Maths")
    public void addMaths(MongoTemplate mongoTemplate) {
        SubjectDbDTO subject = new SubjectDbDTO(
                DevDbMigrationsConstants.CLIENT_01_ID,
                "MATHEMATIQUES",
                "MATHS",
                DbMigrationsFindUtils.findOneWithCriterias(mongoTemplate, GradeDbDTO.MONGO_COLLECTION_NAME, GradeDbDTO.class, new HashMap<String, ObjectId>() {{
                    put("_id", DevDbMigrationsConstants.GRADE_SIXIEME_ID);
                }}),
                null,
                BigDecimal.valueOf(5), DevDbMigrationsConstants.SUBJECT_MATHS_COLOR,
                DevDbMigrationsConstants.SUBJECT_MATHS_BGCOLOR,
                false, 120, 60, BigDecimal.valueOf(60 * 4.5).intValue(), 5,
                false, null, Collections.singletonList(EnumSchoolRoomsTypesDb.NORMAL), false);
        subject.setId(DevDbMigrationsConstants.SUBJECT_SIXIEME_MATHEMATIQUES_ID);
        mongoTemplate.insert(subject, MONGO_COLLECTION_NAME);
        MigrationTempData.subjects.add(subject);
    }

    @ChangeSet(order = "08", author = "sch", id = "08-subjects-Sixieme-Educ-Civ")
    public void addEducCiv(MongoTemplate mongoTemplate) {
        final HashMap<String, ObjectId> ids = new HashMap<>();
        ids.put("_id", DevDbMigrationsConstants.GRADE_SIXIEME_ID);
        SubjectDbDTO subject = new SubjectDbDTO(
                DevDbMigrationsConstants.CLIENT_01_ID,
                "ENSEIGNEMENT MORAL ET CIVIQUE",
                "EDUCATION CIVIQUE",
                DbMigrationsFindUtils.findOneWithCriterias(mongoTemplate, GradeDbDTO.MONGO_COLLECTION_NAME, GradeDbDTO.class, ids),
                null,
                BigDecimal.valueOf(1), DevDbMigrationsConstants.SUBJECT_EDUCATION_CIVIQUE_COLOR,
                DevDbMigrationsConstants.SUBJECT_EDUCATION_CIVIQUE_BGCOLOR,
                false, 60, 60, BigDecimal.valueOf(60 * 3L).intValue(), 3,
                false, null, Collections.singletonList(EnumSchoolRoomsTypesDb.NORMAL), false);
        subject.setId(DevDbMigrationsConstants.SUBJECT_SIXIEME_EDUC_CIV_ID);
        mongoTemplate.insert(subject, MONGO_COLLECTION_NAME);
        MigrationTempData.subjects.add(subject);
    }

    @ChangeSet(order = "09", author = "sch", id = "09-subjects-Sixieme-SVT")
    public void addSVT(MongoTemplate mongoTemplate) {
        SubjectDbDTO subject = new SubjectDbDTO(
                DevDbMigrationsConstants.CLIENT_01_ID,
                "SCIENCES DE LA VIE ET DE LA TERRE",
                "SVT",
                DbMigrationsFindUtils.findOneWithCriterias(mongoTemplate, GradeDbDTO.MONGO_COLLECTION_NAME, GradeDbDTO.class, new HashMap<String, ObjectId>() {{
                    put("_id", DevDbMigrationsConstants.GRADE_SIXIEME_ID);
                }}),
                null,
                BigDecimal.valueOf(1), DevDbMigrationsConstants.SUBJECT_SVT_COLOR,
                DevDbMigrationsConstants.SUBJECT_SVT_BGCOLOR,
                false, 120, 120, BigDecimal.valueOf(60 * 4L).intValue(), 4,
                false, null, Collections.singletonList(EnumSchoolRoomsTypesDb.SCIENCES), false);
        subject.setId(DevDbMigrationsConstants.SUBJECT_SIXIEME_SVT_ID);
        mongoTemplate.insert(subject, MONGO_COLLECTION_NAME);
        MigrationTempData.subjects.add(subject);
    }

    @ChangeSet(order = "10", author = "sch", id = "10-subjects-Sixieme-Physique")
    public void addPhysique(MongoTemplate mongoTemplate) {
        SubjectDbDTO subject = new SubjectDbDTO(
                DevDbMigrationsConstants.CLIENT_01_ID,
                "PHYSIQUE-CHIMIE",
                "PHYSIQUE-CHIMIE",
                DbMigrationsFindUtils.findOneWithCriterias(mongoTemplate, GradeDbDTO.MONGO_COLLECTION_NAME, GradeDbDTO.class, new HashMap<String, ObjectId>() {{
                    put("_id", DevDbMigrationsConstants.GRADE_SIXIEME_ID);
                }}),
                null,
                BigDecimal.valueOf(1), DevDbMigrationsConstants.SUBJECT_PHYSIQUE_CHIMIE_COLOR,
                DevDbMigrationsConstants.SUBJECT_PHYSIQUE_CHIMIE_BGCOLOR,
                false, 120, 120, BigDecimal.valueOf(60 * 4L).intValue(), 4,
                false, null, Collections.singletonList(EnumSchoolRoomsTypesDb.SCIENCES), false);
        subject.setId(DevDbMigrationsConstants.SUBJECT_SIXIEME_PHYSIQUE_ID);
        mongoTemplate.insert(subject, MONGO_COLLECTION_NAME);
        MigrationTempData.subjects.add(subject);
    }

    @ChangeSet(order = "11", author = "sch", id = "11-subjects-Sixieme-Musique")
    public void addMusique(MongoTemplate mongoTemplate) {
        SubjectDbDTO subject = new SubjectDbDTO(
                DevDbMigrationsConstants.CLIENT_01_ID,
                "EDUCATION MUSICALE",
                "MUSIQUE",
                DbMigrationsFindUtils.findOneWithCriterias(mongoTemplate, GradeDbDTO.MONGO_COLLECTION_NAME, GradeDbDTO.class, new HashMap<String, ObjectId>() {{
                    put("_id", DevDbMigrationsConstants.GRADE_SIXIEME_ID);
                }}),
                null,
                BigDecimal.valueOf(1), DevDbMigrationsConstants.SUBJECT_MUSIQUE_COLOR,
                DevDbMigrationsConstants.SUBJECT_MUSIQUE_BGCOLOR,
                false, 60, 60, BigDecimal.valueOf(60).intValue(), 1,
                false, null, Collections.singletonList(EnumSchoolRoomsTypesDb.MUSIC), false);
        subject.setId(DevDbMigrationsConstants.SUBJECT_SIXIEME_MUSIC_ID);
        subject.setPreferredPartsOfDaysInTimetables(Arrays.asList(
                new PreferredPartsOfDaysDbVO(EnumDays.MONDAY, Collections.singletonList(EnumPartsOfDay.PM)),
                new PreferredPartsOfDaysDbVO(EnumDays.TUESDAY, Collections.singletonList(EnumPartsOfDay.PM)),
                new PreferredPartsOfDaysDbVO(EnumDays.THURSDAY, Collections.singletonList(EnumPartsOfDay.PM)),
                new PreferredPartsOfDaysDbVO(EnumDays.FRIDAY, Collections.singletonList(EnumPartsOfDay.PM))
        ));
        mongoTemplate.insert(subject, MONGO_COLLECTION_NAME);
        MigrationTempData.subjects.add(subject);
    }

}
