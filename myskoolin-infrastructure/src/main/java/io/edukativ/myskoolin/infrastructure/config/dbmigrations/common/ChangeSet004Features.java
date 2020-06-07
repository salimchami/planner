package io.edukativ.myskoolin.infrastructure.config.dbmigrations.common;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import io.edukativ.myskoolin.domain.commons.AuthoritiesConstants;
import io.edukativ.myskoolin.infrastructure.app.dto.FeatureDbDTO;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.MigrationTempData;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.edukativ.myskoolin.domain.commons.AuthoritiesConstants.*;

/**
 * Creates the initial database setup
 */
@ChangeLog(order = "004")
public class ChangeSet004Features {

    @ChangeSet(order = "01", author = "sch", id = "04-features")
    public void addFeatures(MongoTemplate mongoTemplate){
        final List<FeatureDbDTO> features = Arrays.asList(
            new FeatureDbDTO("feature-0", "state_app", AuthoritiesConstants.ALL_AUTHORITIES),

            new FeatureDbDTO("feature-1", "state_students", Arrays.asList(AuthoritiesConstants.SCHOOLME_ADMIN,
                ADMINISTRATION, AuthoritiesConstants.SCHOOL_LIFE,
                INFIRMARY, TEACHERS)),

            new FeatureDbDTO("feature-2", "state_teachers", Arrays.asList(SCHOOLME_ADMIN,
                ADMINISTRATION, SCHOOL_LIFE,
                INFIRMARY, TEACHERS)),

            new FeatureDbDTO("feature-3", "state_classes", Arrays.asList(SCHOOLME_ADMIN,
                ADMINISTRATION, SCHOOL_LIFE,
                INFIRMARY, TEACHERS)),

            new FeatureDbDTO("feature-4", "state_schoolRooms", Arrays.asList(SCHOOLME_ADMIN,
                ADMINISTRATION, SCHOOL_LIFE,
                INFIRMARY, TEACHERS)),

            new FeatureDbDTO("feature-5", "state_home", ALL_AUTHORITIES),

            new FeatureDbDTO("feature-6", "state_clients", Collections.singletonList(SCHOOLME_ADMIN)),

            new FeatureDbDTO("feature-7", "state_client_new", Collections.singletonList(SCHOOLME_ADMIN)),

            new FeatureDbDTO("feature-8", "state_users", Collections.singletonList(SCHOOLME_ADMIN)),

            new FeatureDbDTO("feature-9", "state_password", Collections.singletonList(SCHOOLME_ADMIN)),

            new FeatureDbDTO("feature-10", "state_schoolRooms_edit", Arrays.asList(SCHOOLME_ADMIN,
                ADMINISTRATION, SCHOOL_LIFE)),

            new FeatureDbDTO("feature-11", "state_clientConf", Arrays.asList(ADMINISTRATION,
                SCHOOL_LIFE)),

            new FeatureDbDTO("feature-12", "state_grade", Arrays.asList(SCHOOLME_ADMIN,
                ADMINISTRATION, SCHOOL_LIFE)),

            new FeatureDbDTO("feature-13", "state_subjects", Arrays.asList(SCHOOLME_ADMIN,
                ADMINISTRATION, SCHOOL_LIFE)),

            new FeatureDbDTO("feature-14", "state_school_classes", Arrays.asList(SCHOOLME_ADMIN,
                ADMINISTRATION, SCHOOL_LIFE)),

            new FeatureDbDTO("feature-15", "state_timetables", Arrays.asList(SCHOOLME_ADMIN,
                ADMINISTRATION, SCHOOL_LIFE)));
        mongoTemplate.insert(features, FeatureDbDTO.MONGO_COLLECTION_NAME);
        MigrationTempData.features.addAll(features);
    }
}
