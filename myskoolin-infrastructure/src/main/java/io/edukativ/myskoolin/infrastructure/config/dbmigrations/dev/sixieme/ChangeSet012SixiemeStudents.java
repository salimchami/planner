package io.edukativ.myskoolin.infrastructure.config.dbmigrations.dev.sixieme;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.google.common.collect.Sets;
import io.edukativ.myskoolin.domain.commons.AuthoritiesConstants;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumDays;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumSex;
import io.edukativ.myskoolin.infrastructure.common.vo.AddressDbVO;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.DbMigrationsConstants;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.DbMigrationsFindUtils;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.MigrationTempData;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.dev.util.DevDbMigrationsConstants;
import io.edukativ.myskoolin.infrastructure.students.StudentDbDTO;
import io.edukativ.myskoolin.infrastructure.schooling.vo.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Creates the initial database setup
 */
@ChangeLog(order = "012")
public class ChangeSet012SixiemeStudents {

    private static final String PERE = "Père";
    private static final String MERE = "Mère";
    private final PasswordEncoder passwordEncoder;
    private static final String DEFAULT_PHONE = "000000000";

    public ChangeSet012SixiemeStudents() {
        this.passwordEncoder = new BCryptPasswordEncoder();

    }

    @ChangeSet(order = "01", author = "sch", id = "01-dev-students-sixieme")
    public void addStudentSixieme1(MongoTemplate mongoTemplate) {
        String name = "HANKS";
        ResponsibleDbVO resp1 = createResponsible("Tom", name, EnumSex.MALE, PERE, "Acteur", true);
        ResponsibleDbVO resp2 = createResponsible("Patricia", name, EnumSex.FEMALE, MERE, "Actrice", false);
        createStudentUser(mongoTemplate, DevDbMigrationsConstants.STUDENT_1_SIXIEME_1_ID, "jack.hanks",
                "Jack", name, EnumSex.MALE.getCode(), createCanteenRegistration(), createMedicalInfos(),
                createSchoolingInfos(), createResidentialSchool(),
                resp1, resp2, DevDbMigrationsConstants.STUDENT_RESP_1_SIXIEME_1_ID,
                resp1.getFirstName().toLowerCase().concat(resp1.getLastName().toLowerCase()).replace(" ", "")
        );
    }


    @ChangeSet(order = "02", author = "sch", id = "02-dev-students-sixieme")
    public void addStudentSixieme2(MongoTemplate mongoTemplate) {
        String name = "CRUISE";
        ResponsibleDbVO resp1 = createResponsible("Tom", name, EnumSex.MALE, PERE, "Ingénieur", true);
        ResponsibleDbVO resp2 = createResponsible("Jennifer", name, EnumSex.FEMALE, MERE, "CTO", false);
        createStudentUser(mongoTemplate, DevDbMigrationsConstants.STUDENT_2_SIXIEME_1_ID, "james.cruise",
                "James", name, EnumSex.MALE.getCode(), createCanteenRegistration(), createMedicalInfos(),
                createSchoolingInfos(), createResidentialSchool(),
                resp1, resp2, DevDbMigrationsConstants.STUDENT_RESP_2_SIXIEME_1_ID,
                resp1.getFirstName().toLowerCase().concat(resp1.getLastName().toLowerCase()).replace(" ", "")
        );
    }

    @ChangeSet(order = "03", author = "sch", id = "03-dev-students-sixieme")
    public void addStudentSixieme3(MongoTemplate mongoTemplate) {
        String name = "WILLIS";
        ResponsibleDbVO resp1 = createResponsible("Bruce", name, EnumSex.MALE, PERE, "Chanteur", true);
        ResponsibleDbVO resp2 = createResponsible("Alicia", name, EnumSex.FEMALE, MERE, "CEO", false);
        createStudentUser(mongoTemplate, DevDbMigrationsConstants.STUDENT_3_SIXIEME_1_ID, "david.willis",

                "David", name, EnumSex.MALE.getCode(), createCanteenRegistration(), createMedicalInfos(),
                createSchoolingInfos(), createResidentialSchool(),
                resp1, resp2, DevDbMigrationsConstants.STUDENT_RESP_3_SIXIEME_1_ID,
                resp1.getFirstName().toLowerCase().concat(resp1.getLastName().toLowerCase()).replace(" ", "")
        );
    }

    @ChangeSet(order = "04", author = "sch", id = "04-dev-students-sixieme")
    public void addStudentSixieme4(MongoTemplate mongoTemplate) {
        String name = "WILLIAMS";
        ResponsibleDbVO resp1 = createResponsible("Robin", name, EnumSex.MALE, PERE, "Professeur", true);
        ResponsibleDbVO resp2 = createResponsible("Helena", name, EnumSex.FEMALE, MERE, "Conseillère d'orientation", false);
        createStudentUser(mongoTemplate, DevDbMigrationsConstants.STUDENT_4_SIXIEME_1_ID,
                "richard.williams", "Richard", name, EnumSex.MALE.getCode(), createCanteenRegistration(),
                createMedicalInfos(), createSchoolingInfos(), createResidentialSchool(),
                resp1, resp2, DevDbMigrationsConstants.STUDENT_RESP_4_SIXIEME_1_ID,
                resp1.getFirstName().toLowerCase().concat(resp1.getLastName().toLowerCase()).replace(" ", "")
        );
    }

    @ChangeSet(order = "05", author = "sch", id = "05-dev-students-sixieme")
    public void addStudentSixieme5(MongoTemplate mongoTemplate) {
        String name = "GRANT";
        ResponsibleDbVO resp1 = createResponsible("Cary", name, EnumSex.MALE, PERE, "Investisseur immobilier", true);
        ResponsibleDbVO resp2 = createResponsible("Lana", name, EnumSex.FEMALE, MERE, "Secrétaire", false);
        createStudentUser(mongoTemplate, DevDbMigrationsConstants.STUDENT_5_SIXIEME_1_ID,
                "robert.grant", "Robert", name, EnumSex.MALE.getCode(), createCanteenRegistration(),
                createMedicalInfos(), createSchoolingInfos(), createResidentialSchool(),
                resp1, resp2, DevDbMigrationsConstants.STUDENT_RESP_5_SIXIEME_1_ID,
                resp1.getFirstName().toLowerCase().concat(resp1.getLastName().toLowerCase()).replace(" ", "")
        );
    }

    @ChangeSet(order = "06", author = "sch", id = "06-dev-students-sixieme")
    public void addStudentSixieme6(MongoTemplate mongoTemplate) {
        String name = "PENN";
        ResponsibleDbVO resp1 = createResponsible("Sean", name, EnumSex.MALE, PERE, "Chimiste", true);
        ResponsibleDbVO resp2 = createResponsible("Mary", name, EnumSex.FEMALE, MERE, "Chimiste", false);
        createStudentUser(mongoTemplate, DevDbMigrationsConstants.STUDENT_6_SIXIEME_1_ID,
                "deborah.penn", "Deborah", name, EnumSex.FEMALE.getCode(), createCanteenRegistration(),
                createMedicalInfos(), createSchoolingInfos(), createResidentialSchool(),
                resp1, resp2, DevDbMigrationsConstants.STUDENT_RESP_6_SIXIEME_1_ID,
                resp1.getFirstName().toLowerCase().concat(resp1.getLastName().toLowerCase()).replace(" ", "")
        );
    }

    @ChangeSet(order = "07", author = "sch", id = "07-dev-students-sixieme")
    public void addStudentSixieme7(MongoTemplate mongoTemplate) {
        String name = "PACINO";
        ResponsibleDbVO resp1 = createResponsible("Al", name, EnumSex.MALE, PERE, "Investisseur", true);
        ResponsibleDbVO resp2 = createResponsible("Maria", name, EnumSex.FEMALE, MERE, "Informaticienne", false);
        createStudentUser(mongoTemplate, DevDbMigrationsConstants.STUDENT_7_SIXIEME_1_ID,
                "susan.pacino", "Susan", name, EnumSex.FEMALE.getCode(), createCanteenRegistration(),
                createMedicalInfos(), createSchoolingInfos(), createResidentialSchool(),
                resp1, resp2, DevDbMigrationsConstants.STUDENT_RESP_7_SIXIEME_1_ID,
                resp1.getFirstName().toLowerCase().concat(resp1.getLastName().toLowerCase()).replace(" ", "")
        );
    }

    @ChangeSet(order = "08", author = "sch", id = "08-dev-students-sixieme")
    public void addStudentSixieme8(MongoTemplate mongoTemplate) {
        String name = "DAMON";
        ResponsibleDbVO resp1 = createResponsible("Matt", name, EnumSex.MALE, PERE, "Procureur", true);
        ResponsibleDbVO resp2 = createResponsible("Donna", name, EnumSex.FEMALE, MERE, "Gardien de la paix", false);
        createStudentUser(mongoTemplate, DevDbMigrationsConstants.STUDENT_8_SIXIEME_1_ID,
                "lisa.damon", "Lisa", name, EnumSex.FEMALE.getCode(), createCanteenRegistration(),
                createMedicalInfos(), createSchoolingInfos(), createResidentialSchool(),
                resp1, resp2, DevDbMigrationsConstants.STUDENT_RESP_8_SIXIEME_1_ID,
                resp1.getFirstName().toLowerCase().concat(resp1.getLastName().toLowerCase()).replace(" ", "")
        );
    }

    @ChangeSet(order = "09", author = "sch", id = "09-dev-students-sixieme")
    public void addStudentSixieme9(MongoTemplate mongoTemplate) {
        String name = "DI CAPRIO";
        ResponsibleDbVO resp1 = createResponsible("Leonardo", name, EnumSex.MALE, PERE, "Pianiste", true);
        ResponsibleDbVO resp2 = createResponsible("Debra", name, EnumSex.FEMALE, MERE, "Chef d'orchestre", false);
        createStudentUser(mongoTemplate, DevDbMigrationsConstants.STUDENT_9_SIXIEME_1_ID,
                "linda.dicaprio", "Linda", name, EnumSex.FEMALE.getCode(), createCanteenRegistration(),
                createMedicalInfos(), createSchoolingInfos(), createResidentialSchool(),
                resp1, resp2, DevDbMigrationsConstants.STUDENT_RESP_9_SIXIEME_1_ID,
                resp1.getFirstName().toLowerCase().concat(resp1.getLastName().toLowerCase()).replace(" ", "")
        );
    }

    @ChangeSet(order = "10", author = "sch", id = "10-dev-students-sixieme")
    public void addStudentSixieme10(MongoTemplate mongoTemplate) {
        String name = "COSTNER";
        ResponsibleDbVO resp1 = createResponsible("Kevin", name, EnumSex.MALE, PERE, "Violoniste", true);
        ResponsibleDbVO resp2 = createResponsible("Maria", name, EnumSex.FEMALE, MERE, "Chef d'orchestre", false);
        createStudentUser(mongoTemplate, DevDbMigrationsConstants.STUDENT_10_SIXIEME_1_ID,
                "mary.costner", "Mary", name, EnumSex.FEMALE.getCode(), createCanteenRegistration(),
                createMedicalInfos(), createSchoolingInfos(), createResidentialSchool(),
                resp1, resp2, DevDbMigrationsConstants.STUDENT_RESP_10_SIXIEME_1_ID,
                resp1.getFirstName().toLowerCase().concat(resp1.getLastName().toLowerCase()).replace(" ", "")
        );
    }

    @ChangeSet(order = "11", author = "sch", id = "11-dev-students-sixieme")
    public void addStudentSixieme11(MongoTemplate mongoTemplate) {
        String name = "REAGAN";
        ResponsibleDbVO resp1 = createResponsible("Ronal", name, EnumSex.MALE, PERE, "Président", true);
        ResponsibleDbVO resp2 = createResponsible("Melania", name, EnumSex.FEMALE, MERE, "Avocat", false);
        createStudentUser(mongoTemplate, DevDbMigrationsConstants.STUDENT_11_SIXIEME_1_ID, "aurelia.reagan",
                "Aurelia", name, EnumSex.FEMALE.getCode(), createCanteenRegistration(), createMedicalInfos(),
                createSchoolingInfos(), createResidentialSchool(),
                resp1, resp2, DevDbMigrationsConstants.STUDENT_RESP_11_SIXIEME_1_ID,
                resp1.getFirstName().toLowerCase().concat(resp1.getLastName().toLowerCase()).replace(" ", "")
        );
    }

    private void createStudentUser(MongoTemplate mongoTemplate, ObjectId id, String login, String firstName,
                                   String lastName, String sex, CanteenRegistrationDbVO canteenRegistration,
                                   MedicalInfosDbVO medicalInfos, SchoolingInfosDbVO schoolingInfos,
                                   ResidentialSchoolDbVO residentialSchool,
                                   ResponsibleDbVO resp1, ResponsibleDbVO resp2, ObjectId respId, String responsibleAccountLogin) {

        Optional<ResponsibleDbVO> optRespUser = Stream.of(resp1, resp2).filter(ResponsibleDbVO::isRespAccount).findFirst();

        if (optRespUser.isPresent()) {
            ResponsibleDbVO responsibleAccount = optRespUser.get();
            String defaultPAssword = "admin";
            UserDbDTO respAccount = new UserDbDTO.UserDbDTOBuilder()
                    .id(respId.toString())
                    .clientId(DevDbMigrationsConstants.CLIENT_01_ID)
                    .login(responsibleAccountLogin)
                    .password(passwordEncoder.encode(defaultPAssword))
                    .firstName(responsibleAccount.getFirstName())
                    .lastName(responsibleAccount.getLastName())
                    .gender(responsibleAccount.getSex())
                    .birthDate(responsibleAccount.getBirthdate())
                    .address(responsibleAccount.getAddress())
                    .cellPhone(responsibleAccount.getCellPhone())
                    .homePhone(responsibleAccount.getPhone())
                    .nationality(responsibleAccount.getNationality())
                    .email(responsibleAccount.getEmail())
                    .langKey(DbMigrationsConstants.LANG_KEY_FR)
                    .activated(false)
                    .authorities(Sets.newHashSet(DbMigrationsFindUtils.findAuthoritiesByIds(mongoTemplate, AuthoritiesConstants.STUDENT_RESPONSIBLE)))
                    .build();

            StudentDbDTO student = new StudentDbDTO.StudentDbDTOBuilder()
                    .responsibles(Arrays.asList(resp1, resp2))
                    .responsibleAccount(respAccount)
                    .canteenRegistration(canteenRegistration)
                    .medicalInfos(medicalInfos)
                    .schoolingInfos(schoolingInfos)
                    .residentialSchool(residentialSchool)

                    .id(id.toString())
                    .clientId(DevDbMigrationsConstants.CLIENT_01_ID)
                    .login(login)
                    .password(passwordEncoder.encode(defaultPAssword))
                    .firstName(firstName)
                    .lastName(lastName)
                    .gender(sex)
                    .birthDate(ZonedDateTime.of(1998, 2, 27, 0, 0, 0, 0, ZoneId.systemDefault()))
                    .address(new AddressDbVO("9, rue du capitaine ferber", "75020", "Paris", "France"))
                    .cellPhone(DEFAULT_PHONE)
                    .nationality("Française")
                    .email("eleve@schoolme.net")
                    .langKey(DbMigrationsConstants.LANG_KEY_FR)
                    .activated(false)
                    .authorities(Sets.newHashSet(DbMigrationsFindUtils.findAuthoritiesByIds(mongoTemplate, AuthoritiesConstants.STUDENTS)))
                    .build();
            mongoTemplate.insert(respAccount);
            MigrationTempData.users.add(respAccount);
            mongoTemplate.insert(student);
            MigrationTempData.students.add(student);
        }
    }

    private ResponsibleDbVO createResponsible(String firstName, String lastName, EnumSex sex, String studentRelationship, String profession, boolean respAccount) {
        return new ResponsibleDbVO(firstName, lastName, sex.getCode(), "Française", respAccount, "resp@schoolme.net",
                ZonedDateTime.of(1974, 8, 30, 0, 0, 0, 0, ZoneId.systemDefault()),
                "secEmail@schoolme.net", "pro@schoolme.net",
                new AddressDbVO("9 Rue du Capitaine Ferber", "92130", "Issy-les-Moulineaux", "France"),
                DEFAULT_PHONE, DEFAULT_PHONE, DEFAULT_PHONE, DEFAULT_PHONE,
                studentRelationship, profession, "global.form.family-situations.married", 1, "RAS", false, null);
    }

    private CanteenRegistrationDbVO createCanteenRegistration() {
        return new CanteenRegistrationDbVO(true, false, true, false, Arrays.asList(EnumDays.MONDAY, EnumDays.TUESDAY,
                EnumDays.WEDNESDAY, EnumDays.THURSDAY, EnumDays.FRIDAY),
                ZonedDateTime.of(2018, 9, 1, 0, 0, 0, 0, ZoneId.systemDefault()),
                ZonedDateTime.of(2019, 7, 31, 0, 0, 0, 0, ZoneId.systemDefault())
        );
    }

    private MedicalInfosDbVO createMedicalInfos() {
        return new MedicalInfosDbVO();
    }

    private SchoolingInfosDbVO createSchoolingInfos() {
        return new SchoolingInfosDbVO();
    }

    private ResidentialSchoolDbVO createResidentialSchool() {
        return new ResidentialSchoolDbVO();
    }

}
