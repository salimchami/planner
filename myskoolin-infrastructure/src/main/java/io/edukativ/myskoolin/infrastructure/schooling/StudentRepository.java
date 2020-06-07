package io.edukativ.myskoolin.infrastructure.schooling;

import io.edukativ.myskoolin.infrastructure.schooling.StudentDbDTO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data MongoDB fr.wideapps.schoolme.repository for the User entity.
 */
@Repository
public interface StudentRepository extends MongoRepository<StudentDbDTO, String> {


    @Query("{ 'student' : {'$ne' : null}, 'deleted': false, 'clientId': ?0, 'student.schoolClassId': ?1 }")
    List<StudentDbDTO> findStudentsBySchoolClassId(ObjectId clientId, String schoolClassId);

    /**
     * Search teachers by login, email, firstname or lastname.
     *
     * @param search Search query
     * @return List of teachers
     */
    @Query("{ 'student' : {'$ne' : null}, $text: { $search: ?1 } }")
    List<StudentDbDTO> searchStudents(String search);

    /**
     * Search students by login, email, firstname or lastname.
     *
     * @param clientId User client id.
     * @param search   Search query
     * @return List of teachers
     */
    @Query("{ 'deleted': false, 'clientId': ?0, 'student' : {'$ne' : null}, $text: { $search: ?1 } }")
    List<StudentDbDTO> searchStudents(ObjectId clientId, String search);

    /**
     * Find all students
     *
     * @return List of students
     */
    @Query("{'student' : {'$ne' : null}}")
    List<StudentDbDTO> findAllStudents();

    /**
     * Find all not deleted students
     *
     * @return List of not deleted students
     */
    @Query("{ 'deleted': false, 'student' : {'$ne' : null}}")
    List<StudentDbDTO> findAllNotDeletedStudents();

    /**
     * Find all not deleted students
     *
     * @return List of not deleted students
     */
    @Query("{ 'deleted': false, 'student' : {'$ne' : null}, 'clientId': ?0 }")
    List<StudentDbDTO> findAllNotDeletedStudents(ObjectId clientId);

    /**
     * Find all not deleted students by ids
     *
     * @return List of not deleted students
     */
    @Query("{ 'deleted': false, 'student' : {'$ne' : null}, '_id': {$in: ?0} }")
    List<StudentDbDTO> findAllNotDeletedStudents(List<ObjectId> ids);

    /**
     * Find all students
     *
     * @return List of students
     */
    @Query("{ 'student' : {'$ne' : null}, 'clientId': ?0 }")
    List<StudentDbDTO> findAllStudents(ObjectId clientId);

}
