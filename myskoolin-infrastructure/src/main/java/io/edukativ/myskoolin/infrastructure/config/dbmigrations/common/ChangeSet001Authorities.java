package io.edukativ.myskoolin.infrastructure.config.dbmigrations.common;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import io.edukativ.myskoolin.domain.commons.AuthoritiesConstants;
import io.edukativ.myskoolin.infrastructure.app.dto.AuthorityDbDTO;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates the initial database setup
 */
@ChangeLog(order = "001")
public class ChangeSet001Authorities {

    @ChangeSet(order = "01", author = "sch", id = "01-authorities")
    public void addAuthorities(MongoTemplate mongoTemplate) {
        List<AuthorityDbDTO> authorities = new ArrayList<>();
        AuthoritiesConstants.ALL_AUTHORITIES.forEach(auth -> authorities.add(new AuthorityDbDTO(auth)));
        mongoTemplate.insert(authorities, AuthorityDbDTO.MONGO_COLLECTION_NAME);
    }

}
