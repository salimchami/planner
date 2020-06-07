package io.edukativ.myskoolin.infrastructure.schooling.repository;

import io.edukativ.myskoolin.infrastructure.schooling.dto.TeacherDbDTO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

/**
 * Spring Data MongoDB fr.wideapps.schoolme.repository for the User entity.
 */
@Repository
public interface TeacherRepository extends MongoRepository<TeacherDbDTO, String> {


    /**
     * Find all not deleted teachers
     *
     * @return List of not deleted teachers
     */
    @Query("{ 'deleted': false, 'clientId': ?0, 'teacher' : {'$ne' : null} }")
    List<TeacherDbDTO> findAllNotDeletedTeachers(ObjectId clientId);

    /**
     * Find all teachers
     *
     * @return List of teachers
     */
    @Query("{ 'clientId': ?0, 'teacher' : {'$ne' : null} }")
    List<TeacherDbDTO> findAllTeachers(ObjectId clientId);

    /**
     * Find all not deleted teachers
     *
     * @return List of not deleted teachers
     */
    @Query("{ 'deleted': false, 'teacher' : {'$ne' : null} }")
    List<TeacherDbDTO> findAllNotDeletedTeachers();

    /**
     * Find all teachers
     *
     * @return List of teachers
     */
    @Query("{ 'teacher' : {'$ne' : null} }")
    List<TeacherDbDTO> findAllTeachers();

    /**
     * Search teachers by login, email, firstname or lastname.
     * @param clientId User client id.
     * @param search Search query
     * @return List of teachers
     */
    @Query("{ 'deleted': false, 'clientId': ?0, 'teacher' : {'$ne' : null}, $text: { $search: ?1 } }")
    List<TeacherDbDTO> searchTeachers(ObjectId clientId, String search);

    /**
     * Search students by login, email, firstname or lastname.
     * @param clientId User client id.
     * @param search Search query
     * @return List of teachers
     */
    @Query("{ 'deleted': false, 'clientId': ?0, 'student' : {'$ne' : null}, $text: { $search: ?1 } }")
    List<TeacherDbDTO> searchStudents(ObjectId clientId, String search);

    /**
     * Search teachers by login, email, firstname or lastname.
     * @param search Search query
     * @return List of teachers
     */
    @Query("{ 'student' : {'$ne' : null}, $text: { $search: ?1 } }")
    List<TeacherDbDTO> searchStudents(String search);

    List<TeacherDbDTO> findAllByDeletedIsTrueAndCreatedDateBefore(Instant minus);


    @Query("{ '_id': {$in: ?0} }")
    List<TeacherDbDTO> findByIds(List<ObjectId> collect);

    @Query("{ 'student' : {'$ne' : null}, 'deleted': false, 'clientId': ?0, 'student.schoolClassId': ?1 }")
    List<TeacherDbDTO> findStudentsBySchoolClassId(ObjectId clientId, String schoolClassId);

    /**
     * Search teachers by login, email, firstname or lastname.
     * @param search Search query
     * @return List of teachers
     */
    @Query("{ 'teacher' : {'$ne' : null}, $text: { $search: ?1 } }")
    List<TeacherDbDTO> searchTeachers(String search);


    @Query("{ 'teacher' : {'$ne' : null}, 'deleted': false, 'clientId': ?0, 'teacher.grades.$id': {$in: ?1},  'taughtSubjects': {$in: ?2}}")
    List<TeacherDbDTO> findNotDeletedTeachersByGradesIdsAndSubjectsId(ObjectId clientId, List<String> gradesIds, List<String> subjectsIds);

    @Query("{ 'teacher' : {'$ne' : null}, 'deleted': false, 'clientId': ?0, 'teacher.grades.$id': ?1 }")
    List<TeacherDbDTO> findNotDeletedTeachersByGradeId(ObjectId clientId, ObjectId gradeId);

}
