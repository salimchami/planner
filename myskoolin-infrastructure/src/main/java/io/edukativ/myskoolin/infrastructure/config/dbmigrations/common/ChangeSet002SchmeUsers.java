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
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.MigrationTempData;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static io.edukativ.myskoolin.infrastructure.app.dto.AbstractUserDbDTO.MONGO_COLLECTION_NAME;

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
        final UserDbDTO sch = new UserDbDTO.UserDbDTOBuilder()
                .login("sch")
                .password(passwordEncoder.encode("schoolmeBitsUHard"))
                .gender("global.constants.sexes.male")
                .firstName("Salim")
                .lastName("CHAMI")
                .cellPhone("0619134350")
                .nationality("Francaise")
                .email("salim.chami@myskoolin.net")
                .langKey(DbMigrationsConstants.LANG_KEY_EN)
                .authorities(Sets.newHashSet(authorities))
                .address(new AddressDbVO("9, rue du capitaine ferber", "75020", "Paris", "France"))
                .birthDate(ZonedDateTime.of(1977, 7, 16, 15, 0, 0, 0, ZoneId.systemDefault()))
                .activated(true)
                .build();

        final UserDbDTO osk = new UserDbDTO.UserDbDTOBuilder()
                .login("osk")
                .password(passwordEncoder.encode("admin"))
                .gender("global.constants.sexes.male")
                .firstName("Oussama")
                .lastName("SKALI")
                .cellPhone(phone)
                .nationality("Francaise")
                .email("houssama.skali@gmail.com")
                .langKey(DbMigrationsConstants.LANG_KEY_FR)
                .authorities(Sets.newHashSet(authorities))
                .address(new AddressDbVO("9, rue du capitaine ferber", "75020", "Paris", "France"))
                .birthDate(ZonedDateTime.of(1977, 7, 16, 15, 0, 0, 0, ZoneId.systemDefault()))
                .activated(true)
                .build();
        final List<UserDbDTO> users = Arrays.asList(sch, osk);
        mongoTemplate.insert(users, MONGO_COLLECTION_NAME);
        MigrationTempData.users.addAll(users);
    }

}
