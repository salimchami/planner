package io.edukativ.myskoolin.infrastructure.students;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
     * Find all not deleted students
     *
     * @return List of not deleted students
     */
    @Query("{ 'deleted': false, 'clientId': ?0 }")
    List<StudentDbDTO> findAllNotDeletedStudents(ObjectId clientId);

    /**
     * Find all not deleted students
     *
     * @return List of not deleted students
     */
    @Query("{ 'deleted': false, 'clientId': ?0, 'id': ?1 }")
    Optional<StudentDbDTO> findByIdAndClientId(ObjectId clientId, String id);

}
