package io.edukativ.myskoolin.infrastructure.config.dbmigrations;

import io.edukativ.myskoolin.infrastructure.app.dto.AuthorityDbDTO;
import io.edukativ.myskoolin.infrastructure.commercial.dto.PricingDbDTO;
import io.edukativ.myskoolin.infrastructure.grades.GradeDbDTO;
import io.edukativ.myskoolin.infrastructure.schooling.dto.SubjectDbDTO;
import io.edukativ.myskoolin.infrastructure.schooling.dto.TeacherDbDTO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.*;
import java.util.stream.Collectors;

import static io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO.MONGO_FIELD_CLIENT_ID;

/**
 * Db migrations utils.
 */
public final class DbMigrationsFindUtils {

    private DbMigrationsFindUtils() {
    }

    public static List<AuthorityDbDTO> findAuthoritiesByIds(MongoTemplate mongoTemplate, String... ids) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").in(Arrays.asList(ids)));
        return mongoTemplate.find(query, AuthorityDbDTO.class, AuthorityDbDTO.MONGO_COLLECTION_NAME);
    }

    public static PricingDbDTO findPricingByTitleCode(MongoTemplate mongoTemplate, String titleCode) {
        final HashMap<String, String> criterias = new HashMap<>();
        criterias.put(PricingDbDTO.MONGO_FIELD_TITLE_CODE, titleCode);
        return DbMigrationsFindUtils.findOneWithCriterias(mongoTemplate, PricingDbDTO.MONGO_COLLECTION_NAME, PricingDbDTO.class, criterias);
    }

    public static <T, D> T findOneWithCriterias(MongoTemplate mongoTemplate, String collectionName, Class<T> clazz, Map<String, D> criterias) {
        Query query = new Query();
        criterias.forEach((field, value) -> query.addCriteria(Criteria.where(field).is(value)));
        return mongoTemplate.findOne(query, clazz, collectionName);
    }

    public static <T, D> List<T> findWithCriterias(MongoTemplate mongoTemplate, String collectionName, Class<T> clazz, Map<String, D> criterias) {
        Query query = new Query();
        criterias.forEach((field, value) -> query.addCriteria(Criteria.where(field).is(value)));
        return mongoTemplate.find(query, clazz, collectionName);
    }

    public static GradeDbDTO findGradeById(MongoTemplate mongoTemplate, ObjectId id) {
        final HashMap<String, ObjectId> ids = new HashMap<>();
        ids.put("_id", id);
        return DbMigrationsFindUtils.findOneWithCriterias(mongoTemplate, GradeDbDTO.MONGO_COLLECTION_NAME, GradeDbDTO.class, ids);
    }

    public static SubjectDbDTO findSubjectById(MongoTemplate mongoTemplate, ObjectId id) {
        final HashMap<String, ObjectId> ids = new HashMap<>();
        ids.put("_id", id);
        return DbMigrationsFindUtils.findOneWithCriterias(mongoTemplate, SubjectDbDTO.MONGO_COLLECTION_NAME, SubjectDbDTO.class, ids);
    }

    public static List<TeacherDbDTO> findTeachersByGrade(MongoTemplate mongoTemplate, ObjectId clientId, GradeDbDTO grade) {
        List<TeacherDbDTO> result = new ArrayList<>();
        Query teachersQuery = new Query();
        teachersQuery.addCriteria(Criteria.where(MONGO_FIELD_CLIENT_ID).is(clientId));

//        final List<TeacherDbDTO> users = mongoTemplate.find(teachersQuery, TeacherDbDTO.class, MONGO_COLLECTION_NAME);
        final List<TeacherDbDTO> teachers = MigrationTempData.teachersByClientId(clientId.toString());

        for (TeacherDbDTO user : teachers) {
            final List<SubjectDbDTO> subjects = user.getTaughtSubjects()
                .stream()
                .filter(subject -> subject.getGrade().getId().toString().equals(grade.getId().toString()))
                .collect(Collectors.toList());
            if (!subjects.isEmpty()) {
                result.add(user);
            }
        }
        return result;
    }

    public static List<SubjectDbDTO> findSubjectsByGrade(MongoTemplate mongoTemplate, ObjectId clientId, GradeDbDTO grade) {
        Map<String, Object> criterias = new HashMap<>();
        criterias.put(MONGO_FIELD_CLIENT_ID, clientId);
        criterias.put(SubjectDbDTO.MONGO_FIELD_GRADE, grade);
        return findWithCriterias(mongoTemplate, SubjectDbDTO.MONGO_COLLECTION_NAME, SubjectDbDTO.class, criterias);
    }
}
