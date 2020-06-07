package io.edukativ.myskoolin.infrastructure.config.dbmigrations.dev;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.google.common.collect.Sets;
import io.edukativ.myskoolin.domain.commons.AuthoritiesConstants;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import io.edukativ.myskoolin.infrastructure.common.vo.AddressDbVO;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.DbMigrationsConstants;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.DbMigrationsFindUtils;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.DbMigrationsInsertUtils;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.dev.util.DevDbMigrationsConstants;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Creates the initial database setup
 */
@ChangeLog(order = "006")
public class ChangeSet006Users {

    private static final String ADMIN_PASS = "admin";
    private static final String DEFAULT_PHONE = "00000000";

    @ChangeSet(order = "01", author = "sch", id = "01-dev-users")
    public void addUsers(MongoTemplate mongoTemplate) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        final UserDbDTO lecpe = new UserDbDTO.UserDbDTOBuilder()
                .id(DevDbMigrationsConstants.SCHOOL_LIFE_USER_ID_01.toString())
                .login("lecpe")
                .clientId(DevDbMigrationsConstants.CLIENT_01_ID)
                .password(passwordEncoder.encode(ADMIN_PASS))
                .gender("global.constants.sexes.male")
                .firstName("Jean")
                .lastName("LECPE")
                .homePhone(DEFAULT_PHONE)
                .cellPhone(DEFAULT_PHONE)
                .nationality("Francaise")
                .email("schme7700@gmail.com")
                .langKey(DbMigrationsConstants.LANG_KEY_EN)
                .authorities(Sets.newHashSet(DbMigrationsFindUtils.findAuthoritiesByIds(mongoTemplate, AuthoritiesConstants.SCHOOL_LIFE)))
                .address(new AddressDbVO("9, rue du capitaine ferber", "75020", "Paris", "France"))
                .birthDate(ZonedDateTime.of(1980, 8, 15, 12, 0, 0, 0, ZoneId.systemDefault()))
                .activated(true)
                .build();
        DbMigrationsInsertUtils.insertUser(mongoTemplate, lecpe);
    }

    @ChangeSet(order = "03", author = "sch", id = "03-dev-users")
    public void addAdmin(MongoTemplate mongoTemplate) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        final UserDbDTO lecpe = new UserDbDTO.UserDbDTOBuilder()
                .id(DevDbMigrationsConstants.ADMINISTRATION_USER_ID_01.toString())
                .login("leboss")
                .clientId(DevDbMigrationsConstants.CLIENT_01_ID)
                .password(passwordEncoder.encode(ADMIN_PASS))
                .gender("global.constants.sexes.male")
                .firstName("Jean")
                .lastName("LEBON")
                .homePhone(DEFAULT_PHONE)
                .cellPhone(DEFAULT_PHONE)
                .nationality("Francaise")
                .email("schme7700@gmail.com")
                .langKey(DbMigrationsConstants.LANG_KEY_FR)
                .authorities(Sets.newHashSet(DbMigrationsFindUtils.findAuthoritiesByIds(mongoTemplate, AuthoritiesConstants.ADMINISTRATION)))
                .address(new AddressDbVO("9, rue du capitaine ferber", "75020", "Paris", "France"))
                .birthDate(ZonedDateTime.of(1980, 8, 15, 12, 0, 0, 0, ZoneId.systemDefault()))
                .activated(true)
                .build();
        DbMigrationsInsertUtils.insertUser(mongoTemplate, lecpe);
    }
}
