package io.edukativ.myskoolin.infrastructure.subjects;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data MongoDB fr.wideapps.schoolme.repository for the Subject entity.
 */
@Repository
public interface SubjectRepository extends MongoRepository<SubjectDbDTO, String> {

    /**
     * Find all not deleted grades
     *
     * @return List of not deleted grades
     */
    @Query("{ 'deleted': false, 'clientId': ?0 }")
    List<SubjectDbDTO> findAllNotDeleted(ObjectId clientId);

    /**
     * Search teachers by login, email, firstname or lastname.
     *
     * @param clientId User client id.
     * @param search   Search query
     * @return List of teachers
     */
    @Query("{ 'deleted': false, 'clientId': ?0, $text: { $search: ?1 } }")
    List<SubjectDbDTO> searchSubjects(ObjectId clientId, String search);

    /**
     * Search teachers by login, email, firstname or lastname.
     *
     * @param search Search query
     * @return List of teachers
     */
    @Query("{ $text: { $search: ?1 } }")
    List<SubjectDbDTO> searchSubjects(String search);

    @Query("{ 'name' :  ?0, 'deleted': ?1, 'clientId': ?2 }")
    Optional<SubjectDbDTO> findOneByName(@Param("name") String name, @Param("deleted") boolean deleted, @Param("clientId") ObjectId clientId);

    @Query("{ 'grade' : ?0, 'deleted': ?1, 'clientId': ?2 }")
    List<SubjectDbDTO> findByGradeAndClientId(@Param("grade") ObjectId gradeId, @Param("deleted") boolean deleted, @Param("clientId") ObjectId clientId);

    @Query("{ 'deleted': ?0, 'clientId': ?1 }")
    List<SubjectDbDTO> findByClientId(@Param("deleted") boolean deleted, @Param("clientId") ObjectId clientId);

    @Query("{ 'grade.$id' : ?0, 'deleted': ?1 }")
    List<SubjectDbDTO> findByGrade(ObjectId gradeId, boolean deleted);

    @Query(value = "{ 'grade.$id' : {$in: ?0}, 'deleted': false, 'clientId': ?1 }")
    List<SubjectDbDTO> findByGradeInAndClientId(List<ObjectId> gradesIds, ObjectId clientId);

    @Query("{'deleted': false, 'grade.$id' : {$in: ?0}}")
    List<SubjectDbDTO> findByGradeContaining(List<ObjectId> gradesIds);

    @Query(value = "{ 'grade.$id' : ?0, 'deleted': ?1 }", count = true)
    Integer countByGrade(ObjectId id, boolean deleted);
}
