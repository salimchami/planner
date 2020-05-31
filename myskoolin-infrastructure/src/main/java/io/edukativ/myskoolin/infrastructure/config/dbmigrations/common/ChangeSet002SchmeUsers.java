package io.edukativ.myskoolin.infrastructure.config.dbmigrations.common;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.google.common.collect.Sets;
import io.edukativ.myskoolin.domain.commons.AuthoritiesConstants;
import io.edukativ.myskoolin.infrastructure.app.dto.AuthorityDbDTO;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import io.edukativ.myskoolin.infrastructure.common.vo.AddressDbVO;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.DbMigrationsConstants;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.DbMigrationsFindUtils;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.dev.util.DevDbMigrationsConstants;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Creates the initial database setup
 */
@ChangeLog(order = "002")
public class ChangeSet002SchmeUsers {

    @ChangeSet(order = "01", author = "sch", id = "02-schoolme-admin-users")
    public void addUsers(MongoTemplate mongoTemplate) {
        List<AuthorityDbDTO> authorities = DbMigrationsFindUtils.findAuthoritiesByIds(mongoTemplate, AuthoritiesConstants.SCHOOLME_ADMIN);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        final String phone = "0123456789";
        UserDbDTO sch = new UserDbDTO(null,
                "sch",
                DevDbMigrationsConstants.CLIENT_01_ID,
                passwordEncoder.encode("schoolmeBitsUHard"),
                "global.constants.sexes.male",
                "Salim",
                "CHAMI",
                "0619134350",
                phone,
                "Française",
                "salim.chami@myskoolin.net",
                DbMigrationsConstants.LANG_KEY_EN,
                "",
                null, null, null,
                Sets.newHashSet(authorities),
                new AddressDbVO("9, rue du capitaine ferber", "75020", "Paris", "France"),
                ZonedDateTime.of(1977, 7, 16, 15, 0, 0, 0, ZoneId.systemDefault())
        );
        sch.setActivated(true);
        UserDbDTO osk = new UserDbDTO(null,
                "osk",
                DevDbMigrationsConstants.CLIENT_01_ID,
                passwordEncoder.encode("admin"),
                "global.constants.sexes.male",
                "Oussama",
                "SKALI",
                phone,
                phone,
                "Française",
                "houssama.skali@gmail.com",
                DbMigrationsConstants.LANG_KEY_FR,
                "",
                null, null, null,
                Sets.newHashSet(authorities),
                new AddressDbVO("9, rue du capitaine ferber", "75020", "Paris", "France"),
                ZonedDateTime.of(1977, 7, 16, 15, 0, 0, 0, ZoneId.systemDefault())
        );
        osk.setActivated(true);
        mongoTemplate.insert(Arrays.asList(sch, osk), UserDbDTO.MONGO_COLLECTION_NAME);
    }

}
