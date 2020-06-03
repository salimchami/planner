package io.edukativ.myskoolin.infrastructure.grades;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data MongoDB fr.wideapps.schoolme.repository for the Grade entity.
 */
@Repository
public interface GradeRepository extends MongoRepository<GradeDbDTO,String> {

    /**
     * Find not deleted grade by name
     * @param name Grade name
     * @param deleted Not deleted grade
     * @return Not deleted grade by name
     */
    @Query("{ 'name' :  ?0, 'deleted': ?1, 'clientId': ?2 }")
    Optional<GradeDbDTO> findOneByName(@Param("name") String name, @Param("deleted") boolean deleted, @Param("clientId") ObjectId clientId);

    /**
     * Find all not deleted grades by client.
     * @return List of not deleted grades
     * @param clientId Client id
     */
    @Query("{ 'deleted': false, 'clientId': ?0 }")
    List<GradeDbDTO> findAllNotDeleted(@Param("clientId") ObjectId clientId);


    /**
     * Find not deleted grade by diminutive
     * @param diminutive Grade diminutive
     * @param deleted Not deleted grade
     * @return Not deleted grade by diminutive
     */
    @Query("{ 'diminutive' :  ?0, 'deleted': ?1, 'clientId': ?2 }")
    Optional<GradeDbDTO> findOneByDiminutive(@Param("diminutive") String diminutive, @Param("deleted") boolean deleted, @Param("clientId") ObjectId clientId);

    @Query("{ 'deleted': ?0, 'clientId': ?1 }")
    List<GradeDbDTO> findByClientId(@Param("deleted") boolean deleted, @Param("clientId") ObjectId clientId);

}
