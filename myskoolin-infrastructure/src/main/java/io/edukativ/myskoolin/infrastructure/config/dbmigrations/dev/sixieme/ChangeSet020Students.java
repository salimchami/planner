package io.edukativ.myskoolin.infrastructure.config.dbmigrations.dev.sixieme;

import com.github.mongobee.changeset.ChangeLog;

/**
 * Creates the initial database setup
 */
@ChangeLog(order = "020")
public class
ChangeSet020Students {

    /*@ChangeSet(order = "01", author = "sch", id = "01-student-resp-homer")
    public void addStudentResp(DB db) {
        Optional<DBObject> clientEntityOpt = DbMigrationsFindUtils.findEntityById(db, Client.MONGO_COLLECTION_NAME, DevDbMigrationsConstants.CLIENT_01_ID);
        BasicDBObject bdoClient = new BasicDBObject(clientEntityOpt.orElseThrow(
            () -> new InvalidMongoDbApiUsageException("Client ".concat(DevDbMigrationsConstants.CLIENT_01_ID.toString()).concat(" not found"))).toMap());
        Client client = Client.fromDbObject(bdoClient);
        DBCollection usersCollection = db.getCollection("users");
        User resp = new User("homer.simpson", client.getId(),
            "$2a$10$nqdtZ8NW32e3QTPzWcQCJeDg2x1wgRKgL0RxB6FXvMsRizT2UbuS.",
            "global.constants.sexes.male", "Homer", "SIMPSON",
            ZonedDateTime.now(),
            new Address("9, rue du capitaine ferber", "75020", "Paris", "France"),
            "0000000000", "0000000000", "française", "homer.simpson@gmail.com",
            DbMigrationsConstants.LANG_KEY_EN,
            Sets.newHashSet(DbMigrationsFindUtils.findAuthoritiesByIds(db, STUDENT_RESPONSIBLE)), null, null);
        resp.setId(new ObjectId(DevDbMigrationsConstants.STUDENT_USER_01_RESP_USER_01_ID));
        DbMigrationsInsertUtils.insertUser(usersCollection, resp,
            Lists.newArrayList(DbMigrationsFindUtils.findAuthorityById(db, STUDENT_RESPONSIBLE)), DevDbMigrationsConstants.CLIENT_01_ID);
    }

    @ChangeSet(order = "02", author = "sch", id = "02-student-bart")
    public void addStudent(DB db) {
        Optional<DBObject> clientEntityOpt = DbMigrationsFindUtils.findEntityById(db, Client.MONGO_COLLECTION_NAME, DevDbMigrationsConstants.CLIENT_01_ID);
        BasicDBObject bdoClient = new BasicDBObject(clientEntityOpt.orElseThrow(
            () -> new InvalidMongoDbApiUsageException("Client ".concat(DevDbMigrationsConstants.CLIENT_01_ID.toString()).concat(" not found"))).toMap());
        Client client = Client.fromDbObject(bdoClient);
        DBCollection usersCollection = db.getCollection("users");
        Student student = new Student(
            null, Arrays.asList(
            getFirstResponsible(),
            getSecondResponsible()),
            DevDbMigrationsConstants.STUDENT_USER_01_RESP_USER_01,
            "RAS",
            new CanteenRegistration(true, true, true, true, Arrays.asList(EnumDays.MONDAY, EnumDays.TUESDAY),
                ZonedDateTime.now(), ZonedDateTime.now()),
            new MedicalInfos(),
            new SchoolingInfos(ZonedDateTime.now()),
            new ResidentialSchool(false, null, null, null)
        );
        User studentUser = new User("bart.simpson", client.getId(),
            "$2a$10$nqdtZ8NW32e3QTPzWcQCJeDg2x1wgRKgL0RxB6FXvMsRizT2UbuS.",
            "global.constants.sexes.male", "Bart", "SIMPSON",
            ZonedDateTime.of(2000, 7, 5, 0, 0, 0, 0, ZoneId.systemDefault()),
            new Address("9 Rue du Capitaine Ferber", "92130", "Issy-les-Moulineaux", "France"), "0000000000", "0000000000", "française", "bart.simpson@gmail.com",
            DbMigrationsConstants.LANG_KEY_EN,
            Sets.newHashSet(DbMigrationsFindUtils.findAuthoritiesByIds(db, STUDENTS)), student, null);
        studentUser.setId(DevDbMigrationsConstants.STUDENT_USER_01_ID);
        DbMigrationsInsertUtils.insertUser(usersCollection, studentUser,
            Lists.newArrayList(DbMigrationsFindUtils.findAuthorityById(db, STUDENTS)), DevDbMigrationsConstants.CLIENT_01_ID);
    }

*/
}
