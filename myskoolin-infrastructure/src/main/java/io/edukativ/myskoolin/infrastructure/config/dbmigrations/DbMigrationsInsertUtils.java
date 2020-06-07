package io.edukativ.myskoolin.infrastructure.config.dbmigrations;

import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import static io.edukativ.myskoolin.infrastructure.app.dto.AbstractUserDbDTO.MONGO_COLLECTION_NAME;

/**
 * Db migrations utils.
 */
public final class DbMigrationsInsertUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbMigrationsInsertUtils.class);

    private DbMigrationsInsertUtils() {
    }

    public static void insertUser(MongoTemplate mongoTemplate, UserDbDTO user) {
        LOGGER.debug("inserting a new user");
        mongoTemplate.insert(user, MONGO_COLLECTION_NAME);
        MigrationTempData.users.add(user);
    }

}
