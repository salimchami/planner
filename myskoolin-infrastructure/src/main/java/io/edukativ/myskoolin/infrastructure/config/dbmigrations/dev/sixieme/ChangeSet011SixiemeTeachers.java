package io.edukativ.myskoolin.infrastructure.config.dbmigrations.dev.sixieme;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.google.common.collect.Sets;
import io.edukativ.myskoolin.domain.commons.AuthoritiesConstants;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumSex;
import io.edukativ.myskoolin.infrastructure.common.vo.AddressDbVO;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.DbMigrationsConstants;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.DbMigrationsFindUtils;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.MigrationTempData;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.dev.util.DevDbMigrationsConstants;
import io.edukativ.myskoolin.infrastructure.schooling.dto.TeacherDbDTO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;

import static io.edukativ.myskoolin.infrastructure.schooling.dto.TeacherDbDTO.MONGO_COLLECTION_NAME;

/**
 * Creates the initial database setup
 */
@ChangeLog(order = "011")
public class ChangeSet011SixiemeTeachers {

    private final PasswordEncoder passwordEncoder;

    public ChangeSet011SixiemeTeachers() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @ChangeSet(order = "01", author = "sch", id = "01-dev-teachers-sixieme-techno")
    public void addTeacherSixiemeTechno(MongoTemplate mongoTemplate) {
        createTeacherUser(mongoTemplate, DevDbMigrationsConstants.SUBJECT_SIXIEME_TECHNO_ID, "jack.nickolson", "Jack", "Nickolson", EnumSex.MALE.getCode());
    }

    @ChangeSet(order = "02", author = "sch", id = "02-dev-teachers-sixieme-francais")
    public void addTeacherSixiemeFrancais(MongoTemplate mongoTemplate) {
        createTeacherUser(mongoTemplate, DevDbMigrationsConstants.SUBJECT_SIXIEME_FRANCAIS_ID, "emilie.jolie", "Emilie", "JOLIE", EnumSex.FEMALE.getCode());
    }

    @ChangeSet(order = "03", author = "sch", id = "03-dev-teachers-sixieme-eps")
    public void addTeacherSixiemeEPS(MongoTemplate mongoTemplate) {
        createTeacherUser(mongoTemplate, DevDbMigrationsConstants.SUBJECT_SIXIEME_SPORT_ID, "brad.pitt", "Brad", "PITT", EnumSex.MALE.getCode());
    }

    @ChangeSet(order = "04", author = "sch", id = "04-dev-teachers-sixieme-anglais")
    public void addTeacherSixiemeEnglish(MongoTemplate mongoTemplate) {
        createTeacherUser(mongoTemplate, DevDbMigrationsConstants.SUBJECT_SIXIEME_ANGLAIS_ID, "angelina.jolie", "Angelina", "JOLIE", EnumSex.FEMALE.getCode());
    }

    @ChangeSet(order = "05", author = "sch", id = "05-dev-teachers-sixieme-Dessin")
    public void addTeacherSixiemeDessin(MongoTemplate mongoTemplate) {
        createTeacherUser(mongoTemplate, DevDbMigrationsConstants.SUBJECT_SIXIEME_ARTS_PLASTIQUES_ID, "johnny.depp", "Johnny", "DEPP", EnumSex.MALE.getCode());
    }

    @ChangeSet(order = "06", author = "sch", id = "06-dev-teachers-sixieme-histgeo")
    public void addTeacherSixiemeHistgeo(MongoTemplate mongoTemplate) {
        createTeacherUser(mongoTemplate, DevDbMigrationsConstants.SUBJECT_SIXIEME_HIST_GEO_ID, "leo.dicap", "Leonardo", "DICAPRIO", EnumSex.MALE.getCode());
    }

    @ChangeSet(order = "07", author = "sch", id = "07-dev-teachers-sixieme-maths")
    public void addTeacherSixiemeMaths(MongoTemplate mongoTemplate) {
        createTeacherUser(mongoTemplate, DevDbMigrationsConstants.SUBJECT_SIXIEME_MATHEMATIQUES_ID, "scarlett.johan", "Scarlett", "JOHANSSON", EnumSex.FEMALE.getCode());
    }

    @ChangeSet(order = "08", author = "sch", id = "08-dev-teachers-sixieme-educiv")
    public void addTeacherSixiemeEducationCiv(MongoTemplate mongoTemplate) {
        createTeacherUser(mongoTemplate, DevDbMigrationsConstants.SUBJECT_SIXIEME_EDUC_CIV_ID, "jennif.lawrence", "Jennifer", "LAWRENCE", EnumSex.FEMALE.getCode());
    }

    @ChangeSet(order = "09", author = "sch", id = "09-dev-teachers-sixieme-svt")
    public void addTeacherSixiemeSvt(MongoTemplate mongoTemplate) {
        createTeacherUser(mongoTemplate, DevDbMigrationsConstants.SUBJECT_SIXIEME_SVT_ID, "robert.de.niro", "Robert", "DE NIRO", EnumSex.MALE.getCode());
    }

    @ChangeSet(order = "10", author = "sch", id = "10-dev-teachers-sixieme-phys")
    public void addTeacherSixiemePhysiqueChimie(MongoTemplate mongoTemplate) {
        createTeacherUser(mongoTemplate, DevDbMigrationsConstants.SUBJECT_SIXIEME_PHYSIQUE_ID, "morgan.freeman", "Morgan", "FREEMAN", EnumSex.MALE.getCode());
    }

    @ChangeSet(order = "11", author = "sch", id = "11-dev-teachers-sixieme-music")
    public void addTeacherSixiemeMusic(MongoTemplate mongoTemplate) {
        createTeacherUser(mongoTemplate, DevDbMigrationsConstants.SUBJECT_SIXIEME_MUSIC_ID, "khatia.buniatishvili", "Khatia", "BUNIATISHVILI", EnumSex.FEMALE.getCode());
    }

    private void createTeacherUser(MongoTemplate mongoTemplate, ObjectId subjectId, String login, String firstName, String lastName, String sex) {
        TeacherDbDTO teacher = new TeacherDbDTO.TeacherDbDTOBuilder()
                .comment("RAS")
                .employedDate(ZonedDateTime.of(2018, 2, 27, 0, 0, 0, 0, ZoneId.systemDefault()))
                .familySituation("Célibataire")
                .substitute(false)
                .taughtSubjects(Collections.singletonList(DbMigrationsFindUtils.findSubjectById(mongoTemplate, subjectId)))
                .proCellPhone("0000000")
                .proPhone("0000000")
                .proEmail("thefrenchteacher@email.com")
                .grades(Collections.singletonList(DbMigrationsFindUtils.findGradeById(mongoTemplate, DevDbMigrationsConstants.GRADE_SIXIEME_ID)))

                .login(login)
                .password(passwordEncoder.encode("admin"))
                .clientId(DevDbMigrationsConstants.CLIENT_01_ID)
                .gender(sex)
                .firstName(firstName)
                .lastName(lastName)
                .cellPhone("00000000")
                .homePhone("00000000").nationality("Française").email("professeur@schoolme.net")
                .langKey(DbMigrationsConstants.LANG_KEY_FR)
                .authorities(Sets.newHashSet(DbMigrationsFindUtils.findAuthoritiesByIds(mongoTemplate, AuthoritiesConstants.TEACHERS)))
                .address(new AddressDbVO("9, rue du capitaine ferber", "75020", "Paris", "France"))
                .birthDate(ZonedDateTime.of(1978, 2, 27, 0, 0, 0, 0, ZoneId.systemDefault()))
                .build();
        mongoTemplate.insert(teacher, MONGO_COLLECTION_NAME);
        MigrationTempData.teachers.add(teacher);

    }
}
