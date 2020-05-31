package io.edukativ.myskoolin.infrastructure.config.dbmigrations.dev;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import io.edukativ.myskoolin.infrastructure.commercial.dto.ClientDbDTO;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.dev.util.DevChangeSetsUtil;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Creates the initial database setup
 */
@ChangeLog(order = "005")
public class ChangeSet005Clients {

    @ChangeSet(order = "01", author = "sch", id = "05-client-victor-hugo")
    public void addClientVictorHugo(MongoTemplate mongoTemplate) {
        ClientDbDTO client = DevChangeSetsUtil.defaultClient(mongoTemplate);
        mongoTemplate.insert(client);
    }

}
