package io.edukativ.myskoolin.infrastructure.config.dbmigrations.dev.sixieme;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.DbMigrationsFindUtils;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.MigrationTempData;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.dev.util.DevDbMigrationsConstants;
import io.edukativ.myskoolin.infrastructure.grades.GradeDbDTO;
import io.edukativ.myskoolin.infrastructure.common.dto.OptionDbDTO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Creates the initial database setup
 */
@ChangeLog(order = "010")
public class ChangeSet010SixiemeOptions {

    @ChangeSet(order = "01", author = "sch", id = "01-options-Sixieme-Allemand")
    public void addAllemand(MongoTemplate mongoTemplate) {
        OptionDbDTO option = new OptionDbDTO(
            DevDbMigrationsConstants.CLIENT_01_ID,
            "ALLEMAND",
            "ALLEMAND",
            DbMigrationsFindUtils.findOneWithCriterias(mongoTemplate, GradeDbDTO.MONGO_COLLECTION_NAME, GradeDbDTO.class, new HashMap<String, ObjectId>() {{
                put("_id", DevDbMigrationsConstants.GRADE_SIXIEME_ID);
            }}),
            null,
            BigDecimal.valueOf(1),
            DevDbMigrationsConstants.SUBJECT_ALLEMAND_COLOR,
            DevDbMigrationsConstants.SUBJECT_ALLEMAND_BGCOLOR,
            true,
            60,
            BigDecimal.valueOf(60 * 2L).intValue(),
            2,
            false,
            "LV2");
        option.setId(DevDbMigrationsConstants.SUBJECT_SIXIEME_ALLEMAND_ID);
        mongoTemplate.insert(option, OptionDbDTO.MONGO_COLLECTION_NAME);
        MigrationTempData.options.add(option);
    }
}
