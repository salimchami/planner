package io.edukativ.myskoolin.infrastructure.schooling.repository;

import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data MongoDB fr.wideapps.schoolme.repository for the User entity.
 */
@Repository
public interface UserRepository extends MongoRepository<UserDbDTO, String> {

    Optional<UserDbDTO> findOneByActivationKey(String activationKey);

    List<UserDbDTO> findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant dateTime);

    Optional<UserDbDTO> findOneByResetKey(String resetKey);

    Optional<UserDbDTO> findOneByEmailIgnoreCase(String email);

    Optional<UserDbDTO> findOneByLogin(String login);

    Page<UserDbDTO> findAllByLoginNot(Pageable pageable, String login);

    List<UserDbDTO> findAllByActivatedIsFalseAndCreatedDateBefore(Instant dateTime);

    Optional<UserDbDTO> findOneByEmail(String email);

    Optional<UserDbDTO> findOneByLoginIgnoreCase(String login);

    Optional<UserDbDTO> findOneByLoginIgnoreCaseAndEmailIgnoreCase(String login, String email);

    Optional<UserDbDTO> findOneById(ObjectId userId);

    /**
     * Find all students
     *
     * @return List of students
     */
    @Query("{'student' : {'$ne' : null}}")
    List<UserDbDTO> findAllStudents();

    /**
     * Find all not deleted students
     *
     * @return List of not deleted students
     */
    @Query("{ 'deleted': false, 'student' : {'$ne' : null}}")
    List<UserDbDTO> findAllNotDeletedStudents();

    /**
     * Find all not deleted students
     *
     * @return List of not deleted students
     */
    @Query("{ 'deleted': false, 'student' : {'$ne' : null}, 'clientId': ?0 }")
    List<UserDbDTO> findAllNotDeletedStudents(ObjectId clientId);

    /**
     * Find all not deleted students by ids
     *
     * @return List of not deleted students
     */
    @Query("{ 'deleted': false, 'student' : {'$ne' : null}, '_id': {$in: ?0} }")
    List<UserDbDTO> findAllNotDeletedStudents(List<ObjectId> ids);

    /**
     * Find all students
     *
     * @return List of students
     */
    @Query("{ 'student' : {'$ne' : null}, 'clientId': ?0 }")
    List<UserDbDTO> findAllStudents(ObjectId clientId);

    /**
     * Find all not deleted teachers
     *
     * @return List of not deleted teachers
     */
    @Query("{ 'deleted': false, 'clientId': ?0, 'teacher' : {'$ne' : null} }")
    List<UserDbDTO> findAllNotDeletedTeachers(ObjectId clientId);

    /**
     * Find all teachers
     *
     * @return List of teachers
     */
    @Query("{ 'clientId': ?0, 'teacher' : {'$ne' : null} }")
    List<UserDbDTO> findAllTeachers(ObjectId clientId);

    /**
     * Find all not deleted teachers
     *
     * @return List of not deleted teachers
     */
    @Query("{ 'deleted': false, 'teacher' : {'$ne' : null} }")
    List<UserDbDTO> findAllNotDeletedTeachers();

    /**
     * Find all teachers
     *
     * @return List of teachers
     */
    @Query("{ 'teacher' : {'$ne' : null} }")
    List<UserDbDTO> findAllTeachers();

    /**
     * Search teachers by login, email, firstname or lastname.
     * @param clientId User client id.
     * @param search Search query
     * @return List of teachers
     */
    @Query("{ 'deleted': false, 'clientId': ?0, 'teacher' : {'$ne' : null}, $text: { $search: ?1 } }")
    List<UserDbDTO> searchTeachers(ObjectId clientId, String search);

    /**
     * Search students by login, email, firstname or lastname.
     * @param clientId User client id.
     * @param search Search query
     * @return List of teachers
     */
    @Query("{ 'deleted': false, 'clientId': ?0, 'student' : {'$ne' : null}, $text: { $search: ?1 } }")
    List<UserDbDTO> searchStudents(ObjectId clientId, String search);

    /**
     * Search teachers by login, email, firstname or lastname.
     * @param search Search query
     * @return List of teachers
     */
    @Query("{ 'teacher' : {'$ne' : null}, $text: { $search: ?1 } }")
    List<UserDbDTO> searchTeachers(String search);

    /**
     * Search teachers by login, email, firstname or lastname.
     * @param search Search query
     * @return List of teachers
     */
    @Query("{ 'student' : {'$ne' : null}, $text: { $search: ?1 } }")
    List<UserDbDTO> searchStudents(String search);

    List<UserDbDTO> findAllByDeletedIsTrueAndCreatedDateBefore(Instant minus);


    @Query("{ 'teacher' : {'$ne' : null}, 'deleted': false, 'clientId': ?0, 'teacher.grades.$id': {$in: ?1},  'taughtSubjects': {$in: ?2}}")
    List<UserDbDTO> findNotDeletedTeachersByGradesIdsAndSubjectsId(ObjectId clientId, List<String> gradesIds, List<String> subjectsIds);

    @Query("{ 'teacher' : {'$ne' : null}, 'deleted': false, 'clientId': ?0, 'teacher.grades.$id': ?1 }")
    List<UserDbDTO> findNotDeletedTeachersByGradeId(ObjectId clientId, ObjectId gradeId);

    @Query("{ '_id': {$in: ?0} }")
    List<UserDbDTO> findByIds(List<ObjectId> collect);

    @Query("{ 'student' : {'$ne' : null}, 'deleted': false, 'clientId': ?0, 'student.schoolClassId': ?1 }")
    List<UserDbDTO> findStudentsBySchoolClassId(ObjectId clientId, String schoolClassId);
}
